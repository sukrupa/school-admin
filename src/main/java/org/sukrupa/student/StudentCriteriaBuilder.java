package org.sukrupa.student;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;

public class StudentCriteriaBuilder {
    private final SessionFactory sessionFactory;

    private static final String STUDENT_CLASS = "studentClass";
    private static final String GENDER = "gender";
    private static final String CASTE = "caste";
    private static final String COMMUNITY_LOCATION = "communityLocation";
    private static final String NAME = "name";
    private static final String DATE_OF_BIRTH = "dateOfBirth";

    private static final String TALENTS = "talents";
    private static final String RELIGION = "religion";
    private static final String DESCRIPTION = "description";

    public StudentCriteriaBuilder(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Criteria orderedSearchCriteria(StudentSearchParameter searchParam) {
        return addOrderCriteria(generateSearchCriteria(searchParam));
    }

    public Criteria countMatchingResultsCriteria(StudentSearchParameter searchParam) {
        return generateSearchCriteria(searchParam).setProjection(Projections.rowCount());
    }

    private Criteria generateSearchCriteria(StudentSearchParameter searchParam) {
        Conjunction conjunction = createConjunction(searchParam.getStudentClass(), searchParam.getGender(),
                searchParam.getCaste(), searchParam.getCommunityLocation(), searchParam.getReligion());
        if (!searchParam.getAgeFrom().isEmpty()) {
            addAgeCriteria(Integer.parseInt(searchParam.getAgeFrom()), Integer.parseInt(searchParam.getAgeTo()), conjunction);
        }

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Student.class);
        criteria.add(conjunction);
        addTalentsSearchCriteria(criteria, searchParam.getTalent());
        return criteria;
    }

    private void addAgeCriteria(int ageFrom, int ageTo, Conjunction conjunction) {
        LocalDate birthDateFrom = computeBirthDateFromAge(ageFrom);
        LocalDate birthDateTo = computeBirthDateFromAge(getInclusiveUpperBoundAge(ageTo));
        conjunction.add(Restrictions.between(DATE_OF_BIRTH, birthDateTo, birthDateFrom));
    }

    private int getInclusiveUpperBoundAge(int ageTo) {
        return ageTo + 1;
    }

    private void addTalentsSearchCriteria(Criteria criteria, String talent) {
        if (!talent.isEmpty()) {
            criteria.createCriteria(TALENTS).add(Restrictions.eq(DESCRIPTION, talent));
        }
    }

    private LocalDate computeBirthDateFromAge(int age) {
        return new LocalDate().minusYears(age);
    }

    private Criteria addOrderCriteria(Criteria criteria) {
        return criteria.addOrder(Order.asc(GENDER).ignoreCase()).addOrder(Order.asc(NAME).ignoreCase());
    }

    private Conjunction createConjunction(String studentClass, String gender, String caste, String communityLocation, String religion) {
        Conjunction conjunction = Restrictions.conjunction();
        addRestrictionIfNotEmpty(STUDENT_CLASS, studentClass, conjunction);
        addRestrictionIfNotEmpty(GENDER, gender, conjunction);
        addRestrictionIfNotEmpty(CASTE, caste, conjunction);
        addRestrictionIfNotEmpty(COMMUNITY_LOCATION, communityLocation, conjunction);
        addRestrictionIfNotEmpty(RELIGION, religion, conjunction);
        return conjunction;
    }

    private void addRestrictionIfNotEmpty(String field, String parameter, Conjunction conjunction) {
        if (!parameter.isEmpty()) {
            conjunction.add(Restrictions.eq(field, parameter));
        }
    }
}