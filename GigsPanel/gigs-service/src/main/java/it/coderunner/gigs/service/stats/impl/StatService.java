package it.coderunner.gigs.service.stats.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.coderunner.gigs.model.stat.Stat;
import it.coderunner.gigs.model.stat.StatType;
import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.repository.stat.StatRepository;
import it.coderunner.gigs.repository.stat.Stats;
import it.coderunner.gigs.service.stats.IStatService;

public class StatService implements IStatService {

	@Autowired
	private StatRepository statRepository;

	@Override
	public void delete(Stat stat) {
		statRepository.delete(stat);
	}

	@Override
	public void saveOrUpdate(Stat stat) {
		statRepository.saveOrUpdate(stat);
	}

	@Override
	public void save(Stat stat) {
		statRepository.save(stat);
	}

	@Override
	public Stat save(User user, String searchToken, StatType type,
			Double latitude, Double longitude) {
		Stat stat = uniqueObject(Stats.findAll().withUser(user)
				.withSearchToken(searchToken).withType(type)
				.withLatitude(latitude).withLongitude(longitude));
		if (stat == null) {
			stat = new Stat(user, searchToken, type, latitude, longitude);
		}
		return stat;
	}

	@Override
	public List<Stat> list(Stats stats) {
		return statRepository.findAll().merge(stats).list();
	}

	@Override
	public long count(Stats stats) {
		return statRepository.findAll().merge(stats).count();
	}

	@Override
	public Stat uniqueObject(Stats stats) {
		return statRepository.findAll().merge(stats).uniqueObject();
	}

}
