package it.coderunner.gigs.service.comments;

import it.coderunner.gigs.model.comment.Comment;
import it.coderunner.gigs.repository.comments.Comments;
import it.coderunner.gigs.service.IService;

import java.util.List;

public interface ICommentService extends IService{
	void delete(Comment comment);
	
	void saveOrUpdate(Comment comment);
	
	boolean save(Comment comment);
	
	List<Comment> list(Comments comments);
	
	long count(Comments comments);
	
	Comment uniqueObject(Comments comments);
}
