package it.coderunner.gigs.repository.spots.impl;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.spot.Spot;
import it.coderunner.gigs.repository.StandardDatabaseRepository;
import it.coderunner.gigs.repository.spots.SpotRepository;
import it.coderunner.gigs.repository.spots.Spots;

import org.springframework.stereotype.Repository;

@Repository
public class DatabaseSpotRepository extends StandardDatabaseRepository<Spot, Long> implements SpotRepository{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4661892975416408436L;

	@Override
	public Spots findAll() {
		return new CriteriaSpots(createCriteria(), createCriteria());
	}

	@Override
	public Class<? extends BaseEntity<Long>> getEntityClass() {
		return Spot.class;
	}
	


}
