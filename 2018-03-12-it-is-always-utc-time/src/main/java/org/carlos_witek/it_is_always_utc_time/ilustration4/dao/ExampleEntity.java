package org.carlos_witek.it_is_always_utc_time.ilustration4.dao;

import com.google.common.base.MoreObjects;

public class ExampleEntity {

	private Long id;
	private String value;
	private String createdAt;
	private String updatedAt;

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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt( final String createdAt ) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt( final String updatedAt ) {
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
