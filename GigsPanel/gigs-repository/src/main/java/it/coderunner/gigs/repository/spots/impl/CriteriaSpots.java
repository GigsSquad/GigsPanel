package it.coderunner.gigs.repository.spots.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import it.coderunner.gigs.model.spot.Spot;
import it.coderunner.gigs.repository.spots.Spots;

public class CriteriaSpots  extends Spots{

	
	protected Criteria criteria;
	protected Criteria criteria2;
	
	public CriteriaSpots(Criteria criteria, Criteria criteria2){
		this.criteria = criteria;
		this.criteria2 = criteria2;
	}
	
	public Criteria modifyCriteria(Criteria criteria){
		if (city != null){
			criteria.add(Restrictions.eq("city", city));
		}
		if (spot != null){
			criteria.add(Restrictions.eq("spot", spot));
		}
		if (country != null){
			criteria.add(Restrictions.eq("country", country));
		}
		
		return criteria;
	}
	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Spot> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spot uniqueObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
