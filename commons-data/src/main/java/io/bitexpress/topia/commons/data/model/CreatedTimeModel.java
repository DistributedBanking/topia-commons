package io.bitexpress.topia.commons.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;

@MappedSuperclass
public class CreatedTimeModel {
	public static final String[] FIELD_LIST = { "createdTime" };

	@Column(name = "created_time", updatable = false)
	@CreationTimestamp
	private Date createdTime;

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
