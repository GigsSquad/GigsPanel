package it.coderunner.gigs.repository.stat;

import it.coderunner.gigs.model.stat.Stat;
import it.coderunner.gigs.repository.IRepository;

public interface StatRepository extends IRepository<Stat, Long> {
	Stats findAll();
}
