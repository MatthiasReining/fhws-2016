package de.fhws.app.business.log.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name=LogData.findAll, query="SELECT ld FROM LogData ld")
public class LogData {
	
	public static final String findAll = "LogData.findAll";
	

	@Id
	@GeneratedValue
	private long id;

	private Date insertTime;
	private String message;

	@Override
	public String toString() {
		return "LogData [id=" + id + ", insertTime=" + insertTime + ", message=" + message + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
