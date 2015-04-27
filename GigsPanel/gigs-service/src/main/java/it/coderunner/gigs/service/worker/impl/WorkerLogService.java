package it.coderunner.gigs.service.worker.impl;

import it.coderunner.gigs.model.worker.WorkerLog;
import it.coderunner.gigs.repository.worker.WorkerLogRepository;
import it.coderunner.gigs.service.worker.IWorkerLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class WorkerLogService implements IWorkerLogService{

	private static final long serialVersionUID = -1769419855208113858L;
	
	@Autowired
	private WorkerLogRepository workerLogRepository;

	@Override
	public void delete(WorkerLog log) {
		workerLogRepository.delete(log);
	}

	@Override
	public void saveOrUpdate(WorkerLog log) {
		workerLogRepository.saveOrUpdate(log);
	}

}
