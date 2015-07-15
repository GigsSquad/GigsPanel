package it.coderunner.gigs.repository.tags.impl;

import org.springframework.stereotype.Repository;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.tag.Tag;
import it.coderunner.gigs.repository.StandardDatabaseRepository;
import it.coderunner.gigs.repository.tags.TagRepository;
import it.coderunner.gigs.repository.tags.Tags;

@Repository
public class DatabaseTagRepository extends StandardDatabaseRepository<Tag, Long> implements TagRepository{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4661892975416408436L;

	@Override
	public Tags findAll() {
		return new CriteriaTags(createCriteria(), createCriteria());
	}

	@Override
	public Class<? extends BaseEntity<Long>> getEntityClass() {
		return Tag.class;
	}
	


}
