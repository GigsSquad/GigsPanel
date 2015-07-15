package it.coderunner.gigs.service.tags;

import it.coderunner.gigs.model.tag.Tag;
import it.coderunner.gigs.repository.tags.Tags;

import java.util.List;

public interface ITagService {
	void delete(Tag tag);

	void saveOrUpdate(Tag tag);

	void save(Tag tag);

	List<Tag> list(Tags tags);

	long count(Tags tags);

	Tag uniqueObject(Tags tags);
}
