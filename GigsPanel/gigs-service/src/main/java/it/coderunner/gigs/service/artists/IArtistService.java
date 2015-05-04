package it.coderunner.gigs.service.artists;

import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.repository.artists.Artists;
import it.coderunner.gigs.service.IService;

import java.util.List;

public interface IArtistService extends IService {

	void delete(Artist artist);

	void save(Artist artist);

	void saveOrUpdate(Artist artist);
	
	List<Artist> list(Artists artists);

	long count(Artists artists);

	Artist uniqueObject(Artists artists);


}
