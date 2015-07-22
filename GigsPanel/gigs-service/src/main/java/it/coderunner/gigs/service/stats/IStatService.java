package it.coderunner.gigs.service.stats;

import java.util.List;

import it.coderunner.gigs.model.stat.Stat;
import it.coderunner.gigs.model.stat.StatType;
import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.repository.stat.Stats;

public interface IStatService {

	void delete(Stat stat);
	
	void saveOrUpdate(Stat stat);
	
	void save(Stat stat);
	
	Stat save(User user, String searchToken, StatType type, Double latitude, Double longitude);

	List<Stat> list(Stats stats);
	
	long count(Stats stats);
	
	Stat uniqueObject(Stats stats);
}
