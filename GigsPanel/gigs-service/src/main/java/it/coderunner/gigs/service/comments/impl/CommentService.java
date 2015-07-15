package it.coderunner.gigs.service.comments.impl;

import it.coderunner.gigs.model.comment.Comment;
import it.coderunner.gigs.repository.comments.Comments;
import it.coderunner.gigs.repository.comments.ICommentRepository;
import it.coderunner.gigs.service.comments.ICommentService;

import java.util.List;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j
@Transactional(rollbackFor = Exception.class)
public class CommentService implements ICommentService {
	@Autowired
	private ICommentRepository commentRepository;

	private static final long serialVersionUID = -4097842595215634487L;

	@Override
	public void delete(Comment comment) {
		commentRepository.delete(comment);
	}

	@Override
	public void saveOrUpdate(Comment comment) {
		commentRepository.saveOrUpdate(comment);
	}

	@Override
	public boolean save(Comment comment) {
		Comment commentFromDB = uniqueObject(Comments.findAll().withId(comment.getId()));
		
		if(commentFromDB == null){
			commentRepository.save(comment);
			log.info(String.format("Dodaje ", comment.getComment()));
			return true;
		}
		log.info(String.format("Istnieje ", comment.getComment()));
		
		return false;
	}

	@Override
	public List<Comment> list(Comments comments) {
		return commentRepository.findAll().merge(comments).list();
	}

	@Override
	public long count(Comments comments) {
		return commentRepository.findAll().merge(comments).count();
	}

	@Override
	public Comment uniqueObject(Comments comments) {
		return commentRepository.findAll().merge(comments).uniqueObject();
	}

}
