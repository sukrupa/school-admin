package org.sukrupa.student;

import com.google.common.collect.Sets;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class TalentRepository {

	private SessionFactory sessionFactory;

	@Autowired
	public TalentRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public Set<Talent> findTalents(Set<String> talentsDecriptions) {
		if (talentsDecriptions == null) {
			return Sets.newHashSet();
		}

		Disjunction disjunction = Restrictions.disjunction();
		for (String description : talentsDecriptions) {
			disjunction.add(Restrictions.eq("description", description));
		}
		Criteria criteria = session().createCriteria(Talent.class).add(disjunction);
        return genericHashSetFrom(criteria.list());
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<T> genericHashSetFrom(Collection collection) {
        return new HashSet<T>(collection);
    }

    private Session session() {
		return sessionFactory.getCurrentSession();
	}

    public void save(Talent newTalent) {
        // WIP #356 - adding new talents

    }
}
