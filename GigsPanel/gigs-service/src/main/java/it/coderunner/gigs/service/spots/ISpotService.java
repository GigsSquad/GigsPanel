package it.coderunner.gigs.service.spots;

import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.repository.gigs.Gigs;
import it.coderunner.gigs.service.IService;

import java.util.List;

public interface ISpotService {

	
		
		void delete(Gig gig);
		
		void saveOrUpdate(Gig gig);
		
		void save(Gig gig);

		List<Gig> list(Gigs gigs);

		long count(Gigs gigs);
		
		Gig uniqueObject(Gigs gigs);

	

}
