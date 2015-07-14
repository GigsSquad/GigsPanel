package it.coderunner.gigs.repository.spots.impl;

import it.coderunner.gigs.model.spot.Spot;
import it.coderunner.gigs.repository.CommonCriteriaQueryable;
import it.coderunner.gigs.repository.spots.Spots;
import it.coderunner.gigs.util.ClassUtils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class CriteriaSpots extends Spots {

	private static final long serialVersionUID = 5636548482181308854L;
	protected Criteria criteria;
	protected Criteria criteria2;

	public CriteriaSpots(Criteria criteria, Criteria criteria2) {
		this.criteria = criteria;
		this.criteria2 = criteria2;
	}

	public Criteria modifyCriteria(Criteria criteria) {
		if (StringUtils.isNotBlank(city)) {
			criteria.add(Restrictions.eq("city", city));
		}
		if (StringUtils.isNotBlank(club)) {
			criteria.add(Restrictions.eq("club", club));
		}
		if (country != null) {
			criteria.add(Restrictions.eq("country", country));
		}

		return criteria;
	}

	@Override
	public long count() {
		return CommonCriteriaQueryable.count(this, modifyCriteria(criteria));
	}

	@Override
	public List<Spot> list() {
		return CommonCriteriaQueryable.list(this, modifyCriteria(criteria), criteria2, ClassUtils.getMapAndCollectionsFrom(Spot.class));
	}

	@Override
	public Spot uniqueObject() {
		return CommonCriteriaQueryable.uniqueObject(this, modifyCriteria(criteria));
	}

}
