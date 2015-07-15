package it.coderunner.gigs.repository.comments;

import it.coderunner.gigs.model.comment.Comment;
import it.coderunner.gigs.repository.IRepository;

public interface ICommentRepository extends IRepository<Comment, Long> {
	Comments findAll();
}
