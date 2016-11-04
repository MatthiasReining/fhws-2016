package de.fhws.app.business.log.entity;

import java.util.Date;

/**
 * <pre>
 *  CREATE TABLE loginfo (
 *  insertTime date,
 *  message varchar2(255)
 *  );
 * </pre>
 *
 */
public class LogInfo {

	private Date inserttime;
	private String message;

	public Date getInserttime() {
		return inserttime;
	}

	public void setInserttime(Date insertTime) {
		this.inserttime = insertTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
