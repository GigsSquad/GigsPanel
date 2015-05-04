package it.coderunner.gigs.service.gigs.impl;

import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.repository.gigs.GigRepository;
import it.coderunner.gigs.repository.gigs.Gigs;
import it.coderunner.gigs.service.gigs.IGigService;

import java.util.Date;
import java.util.List;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j
@Transactional(rollbackFor = Exception.class)
public class GigService implements IGigService {

	@Autowired
	private GigRepository gigRepository;

	private static final long serialVersionUID = 6003016139844887099L;

	@Override
	public void delete(Gig gig) {
		gigRepository.delete(gig);
	}

	@Override
	public void saveOrUpdate(Gig gig) {
		// dodajemy aktualną datę jako aktualizację
		gig.setUpdated(new Date());
		gigRepository.saveOrUpdate(gig);
	}

	@Override
	public List<Gig> list(Gigs gigs) {
		return gigRepository.findAll().merge(gigs).list();
	}

	@Override
	public long count(Gigs gigs) {
		return gigRepository.findAll().merge(gigs).count();
	}

	@Override
	public Gig uniqueObject(Gigs gigs) {
		return gigRepository.findAll().merge(gigs).uniqueObject();
	}

	@Override
	public void save(Gig gig) {
		// dodajemy aktualną datę jako aktualizację
		gig.setUpdated(new Date());
		gigRepository.save(gig);
	}

	@Override
	public boolean saveIfNew(Gig gig) {
		// zwróci tego z bazy, lub nulla jak nie ma
		Gig gigFromDB = uniqueObject(Gigs.findAll().withArtist(gig.getArtist()).withDate(gig.getDate()).withSpot(gig.getSpot()));

		// jak null to dodaj
		if (gigFromDB == null) {
			saveOrUpdate(gig);
			log.info(String.format("DODAJE %-4.4s %-12.12s %-20.20s %-40.40s", gig.getAgency(), gig.getSpot().getCity(), gig.getSpot().getClub(), gig.getArtist().getName()));
			return true;
		}
		log.info(String.format("ISTNIEJE %-4.4s %-12.12s %-20.20s %-40.40s",  gig.getAgency(), gig.getSpot().getCity(), gig.getSpot().getClub(), gig.getArtist().getName()));
		return false;
	}

}
