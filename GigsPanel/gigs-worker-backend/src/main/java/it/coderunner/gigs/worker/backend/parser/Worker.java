package it.coderunner.gigs.worker.backend.parser;

import it.coderunner.gigs.model.worker.WorkerLog;
import it.coderunner.gigs.service.worker.IWorkerLogService;

import java.io.Serializable;
import java.util.Date;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j
@Component
public abstract class Worker implements Serializable{

	private static final long serialVersionUID = -3830652836856717875L;

	@Autowired
	private IWorkerLogService workerLogService;
	
	/**
	 * Metoda odpowiedzialna za wykonywania działań konkretnego workera
	 */
	public abstract void process();
	
	/**
	 * Metoda odpowiedzialna za zapis daty ostatniej aktywności workera
	 */
	public void workerActivityLogEntry(String workerName, String activityPeriod) {
		WorkerLog workerLog = new WorkerLog(workerName, activityPeriod);
		workerLogService.saveOrUpdate(workerLog);
	}
	
	/**
	 * Zapis do logów, czy główny procesor workerów ciągle działa
	 * 
	 * @param worker
	 */
	@Scheduled(cron = "0 * * * * ?")
	public void isAlive() {
		log.info("Workers are alive @ " + new Date() + "\n------------------------------------------");
	}
}
