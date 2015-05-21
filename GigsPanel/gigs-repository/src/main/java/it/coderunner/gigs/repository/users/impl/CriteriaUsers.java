package it.coderunner.gigs.repository.users.impl;

import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.repository.CommonCriteriaQueryable;
import it.coderunner.gigs.repository.users.Users;
import it.coderunner.gigs.util.ClassUtils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class CriteriaUsers extends Users{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3544776035574497650L;
	protected Criteria criteria;
	protected Criteria criteria2;

	public CriteriaUsers(Criteria criteria, Criteria criteria2) {
		this.criteria = criteria;
		this.criteria2 = criteria2;
	}

	public Criteria modifyCriteria(Criteria criteria) {
		if (StringUtils.isNotBlank(firstName)) {
			criteria.add(Restrictions.eq("firstName", firstName));
		}
		if (StringUtils.isNotBlank(lastName)) {
			criteria.add(Restrictions.eq("lastName", lastName));
		}
		if (StringUtils.isNotBlank(location)) {
			criteria.add(Restrictions.eq("location", location));
		}
		if (dateRegister!= null ) {
			criteria.add(Restrictions.eq("dateRegister", dateRegister));
		}

		return criteria;
	}

	@Override
	public long count() {
		return CommonCriteriaQueryable.count(this, modifyCriteria(criteria));
	}

	@Override
	public List<User> list() {
		return CommonCriteriaQueryable.list(this, modifyCriteria(criteria), criteria2, ClassUtils.getMapAndCollectionsFrom(User.class));
	}

	@Override
	public User uniqueObject() {
		return CommonCriteriaQueryable.uniqueObject(this, modifyCriteria(criteria));
	}
}
