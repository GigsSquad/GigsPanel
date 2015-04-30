package it.coderunner.gigs.service.spots.impl;

import it.coderunner.gigs.model.spot.Spot;
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

}
