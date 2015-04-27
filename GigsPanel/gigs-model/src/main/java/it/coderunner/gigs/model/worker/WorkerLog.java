package it.coderunner.gigs.model.worker;

import it.coderunner.gigs.model.BaseEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="worker_logs")
public class WorkerLog extends BaseEntity<String>{

	private static final long serialVersionUID = 6052724578771657144L;

	public WorkerLog(String id, String activityPeriod){
		this.id = id;
		this.activityPeriod = activityPeriod;
		this.timestamp = new Date();
	}
	/**
	 * nazwa workera systemowego
	 */
	@Id
	@Column(name="worker")
	@Getter @Setter
	private String id;
	
	@Getter @Setter
	private Date timestamp;
	
	@Column(name="activity_period")
	@Getter @Setter
	private String activityPeriod;
	
}
