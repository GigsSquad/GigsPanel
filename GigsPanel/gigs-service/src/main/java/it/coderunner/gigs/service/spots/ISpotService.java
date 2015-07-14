package it.coderunner.gigs.service.spots;

import it.coderunner.gigs.model.spot.Spot;
import it.coderunner.gigs.model.user.Country;
import it.coderunner.gigs.repository.spots.Spots;

import java.util.List;

public interface ISpotService {

	void delete(Spot spot);

	void saveOrUpdate(Spot spot);

	void save(Spot spot);

	Spot save(String city, String address, String club, Country country);
	
	Spot save(String city, String address, String club);
	
	Spot save(String city, String address);

	Spot save(String city);

	List<Spot> list(Spots spots);

	long count(Spots spots);

	Spot uniqueObject(Spots spots);

}
