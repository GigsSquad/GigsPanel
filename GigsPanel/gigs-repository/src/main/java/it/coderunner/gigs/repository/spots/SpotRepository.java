package it.coderunner.gigs.repository.spots;

import it.coderunner.gigs.model.spot.Spot;
import it.coderunner.gigs.repository.IRepository;

public interface SpotRepository extends IRepository<Spot, Long> {
	Spots findAll();

}
