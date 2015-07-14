package it.coderunner.gigs.service.spots.impl;

import it.coderunner.gigs.model.spot.Spot;
import it.coderunner.gigs.model.user.Country;
import it.coderunner.gigs.repository.spots.SpotRepository;
import it.coderunner.gigs.repository.spots.Spots;
import it.coderunner.gigs.service.spots.ISpotService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SpotService implements ISpotService {

	@Autowired
	private SpotRepository spotRepository;

	@Override
	public void delete(Spot spot) {
		spotRepository.delete(spot);
	}

	@Override
	public void saveOrUpdate(Spot spot) {
		spotRepository.saveOrUpdate(spot);
	}

	@Override
	public void save(Spot spot) {
		spotRepository.save(spot);
	}

	@Override
	public List<Spot> list(Spots spots) {
		return spotRepository.findAll().merge(spots).list();
	}

	@Override
	public long count(Spots spots) {
		return spotRepository.findAll().merge(spots).count();
	}

	@Override
	public Spot uniqueObject(Spots spots) {
		return spotRepository.findAll().merge(spots).uniqueObject();
	}

	/**
	 * Jeśli nie ma danego spotu w bazie do go doda i nada mu id, jeśli jest to
	 * zwróci tego spot który już jest w bazie
	 */
	@Override
	public Spot save(String city, String address, String club, Country country) {
		Spot spot = uniqueObject(Spots.findAll().withCity(city)
				.withAddress(address).withClub(club).withCountry(country));
		
		if (spot == null) {
			spot = new Spot(city, address, club, country);
			saveOrUpdate(spot);
		}
		return spot;
	}
	
	@Override
	public Spot save(String city, String address, String club) {
		Spot spot = uniqueObject(Spots.findAll().withCity(city)
				.withAddress(address).withClub(club));
		if (spot == null) {
			spot = new Spot(city, address, club, null);
			saveOrUpdate(spot);
		}
		return spot;
	}

	@Override
	public Spot save(String city, String address) {
		Spot spot = uniqueObject(Spots.findAll().withCity(city)
				.withAddress(address));
		if (spot == null) {
			spot = new Spot(city, address, "", null);
			saveOrUpdate(spot);
		}
		return spot;
	}

	@Override
	public Spot save(String city) {
		Spot spot = uniqueObject(Spots.findAll().withCity(city));
		if (spot == null) {
			spot = new Spot(city, "", "", null);
			saveOrUpdate(spot);
		}
		return spot;
	}

}
