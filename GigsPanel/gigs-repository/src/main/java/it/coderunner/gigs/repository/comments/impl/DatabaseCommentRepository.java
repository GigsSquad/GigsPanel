package it.coderunner.gigs.repository.comments.impl;

import org.springframework.stereotype.Repository;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.comment.Comment;
import it.coderunner.gigs.repository.StandardDatabaseRepository;
import it.coderunner.gigs.repository.comments.Comments;
import it.coderunner.gigs.repository.comments.ICommentRepository;

@Repository
public class DatabaseCommentRepository extends StandardDatabaseRepository<Comment, Long> implements ICommentRepository{

	private static final long serialVersionUID = -4982420676002365918L;

	@Override
	public Comments findAll() {
		return new CriteriaComments(createCriteria(), createCriteria());
	}

	@Override
	public Class<? extends BaseEntity<Long>> getEntityClass() {
		return Comment.class;
	}

}
