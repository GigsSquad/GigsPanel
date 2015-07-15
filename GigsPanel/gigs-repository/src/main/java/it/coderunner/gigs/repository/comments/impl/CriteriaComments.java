package it.coderunner.gigs.repository.comments.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import it.coderunner.gigs.model.comment.Comment;
import it.coderunner.gigs.repository.CommonCriteriaQueryable;
import it.coderunner.gigs.repository.comments.Comments;
import it.coderunner.gigs.util.ClassUtils;

public class CriteriaComments extends Comments{

	private static final long serialVersionUID = 7282074197445065505L;
	protected Criteria criteria;
	protected Criteria criteria2;

	public CriteriaComments(Criteria criteria, Criteria criteria2) {
		this.criteria = criteria;
		this.criteria2 = criteria2;
	}

	public Criteria modifyCriteria(Criteria criteria){
		if(comment != null){
			criteria.add(Restrictions.eq("comment", comment));
		}
		if(dateAdded != null){
			criteria.add(Restrictions.eq("date_added", dateAdded));
		}
		if(gig != null){
			criteria.add(Restrictions.eq("gig", gig));
		}
		if(user != null){
			criteria.add(Restrictions.eq("user", user));
		}
		return criteria;
	}
	
	@Override
	public long count() {
		return CommonCriteriaQueryable.count(this, modifyCriteria(criteria));
	}

	@Override
	public List<Comment> list() {
		return CommonCriteriaQueryable.list(this, modifyCriteria(criteria), criteria2, ClassUtils.getMapAndCollectionsFrom(Comment.class));
	}

	@Override
	public Comment uniqueObject() {
		return CommonCriteriaQueryable.uniqueObject(this, modifyCriteria(criteria));
	}

}
