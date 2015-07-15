package it.coderunner.gigs.repository.tags;

import it.coderunner.gigs.model.tag.Tag;
import it.coderunner.gigs.repository.IRepository;
import it.coderunner.gigs.repository.spots.Spots;

public interface TagRepository extends IRepository<Tag, Long>{
	Tags findAll();
}
