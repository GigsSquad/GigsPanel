package it.coderunner.gigs.service.gigs.impl;

import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.repository.gigs.GigRepository;
import it.coderunner.gigs.repository.gigs.Gigs;
import it.coderunner.gigs.service.gigs.IGigService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class GigService implements IGigService{
	
	@Autowired
	private GigRepository gigRepository;

	private static final long serialVersionUID = 6003016139844887099L;

	@Override
	public void delete(Gig gig) {
		gigRepository.delete(gig);
	}

	@Override
	public void saveOrUpdate(Gig gig) {
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
		gigRepository.save(gig);
	}

}
