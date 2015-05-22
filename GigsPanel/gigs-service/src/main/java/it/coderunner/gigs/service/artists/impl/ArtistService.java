package it.coderunner.gigs.service.artists.impl;

import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.repository.artists.ArtistRepository;
import it.coderunner.gigs.repository.artists.Artists;
import it.coderunner.gigs.service.artists.IArtistService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ArtistService implements IArtistService {

	private static final long serialVersionUID = -8002726576997506043L;

	@Autowired
	private ArtistRepository artistRepository;

	@Override
	public void delete(Artist artist) {
		artistRepository.delete(artist);
	}

	@Override
	public void saveOrUpdate(Artist artist) {
		artistRepository.saveOrUpdate(artist);
	}

	/**
	 * Jeśli nie ma danego artysty w bazie do go doda i nada mu id, 
	 * jeśli jest to zwróci tego artystę który już jest w bazie
	 */
	@Override
	public Artist save(String name) {
		Artist artist = uniqueObject(Artists.findAll().withName(name));
		if (artist == null) {
			artist = new Artist(name);
			saveOrUpdate(artist);
		}
		return artist;
	}

	@Override
	public List<Artist> list(Artists artists) {
		return artistRepository.findAll().merge(artists).list();
	}

	@Override
	public long count(Artists artists) {
		return artistRepository.findAll().merge(artists).count();
	}

	@Override
	public Artist uniqueObject(Artists artists) {
		return artistRepository.findAll().merge(artists).uniqueObject();
	}

	@Override
	public void save(Artist artist) {
		artistRepository.save(artist);
	}
}
