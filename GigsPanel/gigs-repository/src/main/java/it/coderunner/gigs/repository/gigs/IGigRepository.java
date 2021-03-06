package it.coderunner.gigs.repository.gigs;

import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.repository.IRepository;

public interface IGigRepository extends IRepository<Gig, Long>{
	
	Gigs findAll();

}
