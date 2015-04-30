package it.coderunner.gigs.repository.artists;

import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.repository.IRepository;

public interface ArtistRepository extends IRepository<Artist, Long> {

	Artists findAll();
}
