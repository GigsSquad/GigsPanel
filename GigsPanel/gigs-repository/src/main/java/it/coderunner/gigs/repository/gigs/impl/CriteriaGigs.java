package it.coderunner.gigs.repository.gigs.impl;

import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.repository.CommonCriteriaQueryable;
import it.coderunner.gigs.repository.gigs.Gigs;
import it.coderunner.gigs.util.ClassUtils;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class CriteriaGigs extends Gigs{
	
	private static final long serialVersionUID = -5034137658126893764L;

	protected Criteria criteria;
	protected Criteria criteria2;
	
	public CriteriaGigs(Criteria criteria, Criteria criteria2){
		this.criteria = criteria;
		this.criteria2 = criteria2;
	}
	
	public Criteria modifyCriteria(Criteria criteria){
		if (artist != null){
			criteria.add(Restrictions.eq("artist", artist));
		}
		if (date != null){
			criteria.add(Restrictions.eq("date", date));
		}
		if (fromDate != null && tillDate != null){
			criteria.add(Restrictions.between("date", fromDate, tillDate));
		} else if (fromDate != null){
			criteria.add(Restrictions.ge("date", fromDate));
		} else if (tillDate != null){
			criteria.add(Restrictions.le("date", tillDate));
		}
		
		return criteria;
	}

	@Override
	public long count() {
		return CommonCriteriaQueryable.count(this, modifyCriteria(criteria));
	}

	@Override
	public List<Gig> list() {
		return CommonCriteriaQueryable.list(this, modifyCriteria(criteria), criteria2, ClassUtils.getMapAndCollectionsFrom(Gig.class));
	}

	@Override
	public Gig uniqueObject() {
		return CommonCriteriaQueryable.uniqueObject(this, modifyCriteria(criteria));
	}

}
