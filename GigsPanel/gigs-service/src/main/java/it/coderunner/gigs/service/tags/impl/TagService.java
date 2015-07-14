package it.coderunner.gigs.service.tags.impl;

import it.coderunner.gigs.model.tag.Tag;
import it.coderunner.gigs.repository.tags.TagRepository;
import it.coderunner.gigs.repository.tags.Tags;
import it.coderunner.gigs.service.tags.ITagService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TagService implements ITagService {

	@Autowired
	private TagRepository tagRepository;
	
	@Override
	public void delete(Tag tag) {
		tagRepository.delete(tag);
		
	}

	@Override
	public void saveOrUpdate(Tag tag) {
		tagRepository.saveOrUpdate(tag);	}

	@Override
	public void save(Tag tag) {
		tag.setTag(tag.getTag().toLowerCase());//zmieniamy litery na male
		Tag findTag = uniqueObject(Tags.findAll().withTagName(tag.getTag()));
		if(findTag == null)
				tagRepository.save(tag);		
	}


	@Override
	public List<Tag> list(Tags tags) {
		return tagRepository.findAll().merge(tags).list();
	}

	@Override
	public long count(Tags tags) {
		return tagRepository.findAll().merge(tags).count();
	}

	@Override
	public Tag uniqueObject(Tags tags) {
		return tagRepository.findAll().merge(tags).uniqueObject();
	}

}
