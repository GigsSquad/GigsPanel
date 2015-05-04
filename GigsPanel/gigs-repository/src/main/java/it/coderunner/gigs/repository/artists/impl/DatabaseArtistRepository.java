package it.coderunner.gigs.repository.artists.impl;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.repository.StandardDatabaseRepository;
import it.coderunner.gigs.repository.artists.ArtistRepository;
import it.coderunner.gigs.repository.artists.Artists;

import org.springframework.stereotype.Repository;

@Repository
public class DatabaseArtistRepository extends StandardDatabaseRepository<Artist, Long> implements ArtistRepository {

	private static final long serialVersionUID = -3637779907382305769L;

	@Override
	public Artists findAll() {
		return new CriteriaArtists(createCriteria(), createCriteria());
	}

	@Override
	public Class<? extends BaseEntity<Long>> getEntityClass() {
		return Artist.class;
	}

	@Override
	public Artists findByNameLike(String name) {
		return new CriteriaArtists(createCriteria(), createCriteria());
	}

}
