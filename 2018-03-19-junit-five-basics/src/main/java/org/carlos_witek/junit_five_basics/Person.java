package org.carlos_witek.junit_five_basics;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {
	private long id;
	private String firstName;
	private String lastName;

	public Person() {
		super();
	}

	public Person( long id, String firstName, String lastName ) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Id
	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "Person [id=" )
				.append( id )
				.append( ", firstName=" )
				.append( firstName )
				.append( ", lastName=" )
				.append( lastName )
				.append( "]" );
		return builder.toString();
	}

}
