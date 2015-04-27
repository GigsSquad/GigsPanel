package it.coderunner.gigs.repository.worker.impl;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.worker.WorkerLog;
import it.coderunner.gigs.repository.Queryable;
import it.coderunner.gigs.repository.StandardDatabaseRepository;
import it.coderunner.gigs.repository.worker.WorkerLogRepository;

import org.springframework.stereotype.Repository;

@Repository
public class DatabaseWorkerLogRepository extends StandardDatabaseRepository<WorkerLog, String> implements WorkerLogRepository{

	private static final long serialVersionUID = 8253432448626928843L;
	
	@Override
	public Class<? extends BaseEntity<String>> getEntityClass() {
		return WorkerLog.class;
	}

	@Override
	public Queryable<WorkerLog, String> findAll() {
		return null;
	}
	
	
}
