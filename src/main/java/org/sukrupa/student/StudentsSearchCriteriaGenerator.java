package org.sukrupa.student;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
class StudentsSearchCriteriaGenerator {
    private static final String ID = "id";
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

    @Autowired
    public StudentsSearchCriteriaGenerator(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Criteria createOrderedCriteriaFrom(StudentSearchParameter searchParam) {
        return addOrderCriteria(generateSearchCriteria(searchParam));
    }

    public Criteria createCountCriteriaBasedOn(StudentSearchParameter searchParam) {
        return generateSearchCriteria(searchParam).setProjection(Projections.rowCount());
    }

    private Criteria generateSearchCriteria(StudentSearchParameter searchParam) {
        Conjunction conjunction = createConjunction(searchParam.getName(), searchParam.getStudentClass(), searchParam.getGender(),
                searchParam.getCaste(), searchParam.getCommunityLocation(), searchParam.getReligion());
        if (!StudentSearchParameter.WILDCARD_CHARACTER.equals(searchParam.getAgeFrom())) {
            addAgeCriteria(Integer.parseInt(searchParam.getAgeFrom()), Integer.parseInt(searchParam.getAgeTo()), conjunction);
        }

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Student.class);
        criteria.add(conjunction);
        addTalentsSearchCriteria(criteria, searchParam.getTalents());
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

    private void addTalentsSearchCriteria(Criteria criteria, List<Talent> talents) {
        if (talents.isEmpty()) {
            return;
        }

        List<String> descriptions = new ArrayList<String>();
        for(Talent talent : talents){
            descriptions.add(talent.getDescription());
        }
        criteria.createCriteria(TALENTS).add(Restrictions.in(DESCRIPTION, descriptions)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    }

    private LocalDate computeBirthDateFromAge(int age) {
        return new LocalDate().minusYears(age);
    }

    private Criteria addOrderCriteria(Criteria criteria) {
        return criteria.addOrder(Order.asc(GENDER).ignoreCase()).addOrder(Order.asc(NAME).ignoreCase());
    }

    private Conjunction createConjunction(String name, String studentClass, String gender, String caste, String communityLocation, String religion) {
        Conjunction conjunction = Restrictions.conjunction();

        addRestrictionIfNotWildcard(NAME, name, conjunction);
        addRestrictionIfNotWildcard(STUDENT_CLASS, studentClass, conjunction);
        addRestrictionIfNotWildcard(GENDER, gender, conjunction);
        addRestrictionIfNotWildcard(CASTE, caste, conjunction);
        addRestrictionIfNotWildcard(COMMUNITY_LOCATION, communityLocation, conjunction);
        addRestrictionIfNotWildcard(RELIGION, religion, conjunction);
        return conjunction;
    }

    private void addRestrictionIfNotWildcard(String field, String parameter, Conjunction conjunction) {
        if (!StudentSearchParameter.WILDCARD_CHARACTER.equals(parameter)) {
            SimpleExpression equalToParam = Restrictions.eq(field, parameter);
            if (parameter.isEmpty()) {
                Disjunction disj = Restrictions.disjunction();
                disj.add(Restrictions.isNull(field));
                disj.add(equalToParam);
                conjunction.add(disj);
            }
            else {
                conjunction.add(equalToParam);
            }
        }
    }
}