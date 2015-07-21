package it.coderunner.gigs.repository.tags;

import it.coderunner.gigs.model.tag.Tag;
import it.coderunner.gigs.repository.IRepository;

public interface TagRepository extends IRepository<Tag, Long>{
	Tags findAll();
}
