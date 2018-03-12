package org.carlos_witek.it_is_always_utc_time.ilustration4.dao.example1;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "example")
public class Example1Entity {

	private Long id;
	private String value;
	private Date createdAt;
	private Date updatedAt;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue( String value ) {
		this.value = value;
	}

	@Column(nullable = false, updatable = false, columnDefinition = "timestamp")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt( Date createdAt ) {
		this.createdAt = createdAt;
	}

	@Column(nullable = false, columnDefinition = "timestamp")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt( Date updatedAt ) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper( this )
				.add( "id", id )
				.add( "value", value )
				.add( "createdAt", createdAt )
				.add( "updatedAt", updatedAt )
				.toString();
	}
}
