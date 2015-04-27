package it.coderunner.gigs.service.worker;

import it.coderunner.gigs.model.worker.WorkerLog;
import it.coderunner.gigs.service.IService;

public interface IWorkerLogService extends IService{
	
	void delete(WorkerLog log);
	
	void saveOrUpdate(WorkerLog log);
}
