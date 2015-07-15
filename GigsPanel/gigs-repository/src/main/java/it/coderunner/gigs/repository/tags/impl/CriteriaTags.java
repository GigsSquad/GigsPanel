package it.coderunner.gigs.repository.tags.impl;

import it.coderunner.gigs.model.tag.Tag;
import it.coderunner.gigs.repository.CommonCriteriaQueryable;
import it.coderunner.gigs.repository.tags.Tags;
import it.coderunner.gigs.util.ClassUtils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class CriteriaTags extends Tags{
	private static final long serialVersionUID = 5636548482181308854L;
	protected Criteria criteria;
	protected Criteria criteria2;

	public CriteriaTags(Criteria criteria, Criteria criteria2) {
		this.criteria = criteria;
		this.criteria2 = criteria2;
	}

	public Criteria modifyCriteria(Criteria criteria) {
		if (StringUtils.isNotBlank(tagName)) {
			criteria.add(Restrictions.eq("tagName", tagName));
		}

		return criteria;
	}

	@Override
	public long count() {
		return CommonCriteriaQueryable.count(this, modifyCriteria(criteria));
	}

	@Override
	public List<Tag> list() {
		return CommonCriteriaQueryable.list(this, modifyCriteria(criteria), criteria2, ClassUtils.getMapAndCollectionsFrom(Tag.class));
	}

	@Override
	public Tag uniqueObject() {
		return CommonCriteriaQueryable.uniqueObject(this, modifyCriteria(criteria));
	}
}
