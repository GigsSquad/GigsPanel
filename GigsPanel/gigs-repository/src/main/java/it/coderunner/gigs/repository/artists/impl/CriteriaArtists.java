package it.coderunner.gigs.repository.artists.impl;

import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.repository.CommonCriteriaQueryable;
import it.coderunner.gigs.repository.artists.Artists;
import it.coderunner.gigs.util.ClassUtils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class CriteriaArtists extends Artists {

	private static final long serialVersionUID = -122158665171325469L;
	protected Criteria criteria;
	protected Criteria criteria2;

	public CriteriaArtists(Criteria criteria, Criteria criteria2) {
		this.criteria = criteria;
		this.criteria2 = criteria2;
	}

	public Criteria modifyCriteria(Criteria criteria) {
		if (StringUtils.isNotBlank(name)) {
			criteria.add(Restrictions.eq("artist", name));
		}

		if (gig != null) {
			criteria.add(Restrictions.eq("gig", gig));
		}

		return criteria;
	}

	@Override
	public long count() {
		return CommonCriteriaQueryable.count(this, modifyCriteria(criteria));
	}

	@Override
	public List<Artist> list() {
		return CommonCriteriaQueryable.list(this, modifyCriteria(criteria), criteria2, ClassUtils.getMapAndCollectionsFrom(Artist.class));
	}

	@Override
	public Artist uniqueObject() {
		return CommonCriteriaQueryable.uniqueObject(this, modifyCriteria(criteria));
	}

}
