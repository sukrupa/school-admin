package org.sukrupa.student;

import com.google.common.collect.Sets;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sukrupa.platform.collection.CollectionTransformation;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.sukrupa.platform.collection.CollectionTransformation.genericHashSetFrom;

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

    private Session session() {
		return sessionFactory.getCurrentSession();
	}

    public void save(Talent newTalent) {
        // WIP #356 - adding new talents

    }
}
