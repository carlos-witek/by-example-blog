package org.carlos_witek.it_is_always_utc_time.ilustration4.dao.example3;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "example")
public class Example3Entity {

	private Long id;
	private String value;
	private Instant createdAt;
	private Instant updatedAt;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId( final Long id ) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue( final String value ) {
		this.value = value;
	}

	@Column(nullable = false, updatable = false, columnDefinition = "timestamp")
	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt( final Instant createdAt ) {
		this.createdAt = createdAt;
	}

	@Column(nullable = false, columnDefinition = "timestamp")
	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt( final Instant updatedAt ) {
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
