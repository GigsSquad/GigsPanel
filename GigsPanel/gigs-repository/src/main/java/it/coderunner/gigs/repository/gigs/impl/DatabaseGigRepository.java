package it.coderunner.gigs.repository.gigs.impl;

import org.springframework.stereotype.Repository;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.repository.StandardDatabaseRepository;
import it.coderunner.gigs.repository.gigs.IGigRepository;
import it.coderunner.gigs.repository.gigs.Gigs;

@Repository
public class DatabaseGigRepository extends StandardDatabaseRepository<Gig, Long> implements IGigRepository{

	private static final long serialVersionUID = -4852362959494788862L;

	@Override
	public Gigs findAll() {
		return new CriteriaGigs(createCriteria(), createCriteria());
	}

	@Override
	public Class<? extends BaseEntity<Long>> getEntityClass() {
		return Gig.class;
	}

}
