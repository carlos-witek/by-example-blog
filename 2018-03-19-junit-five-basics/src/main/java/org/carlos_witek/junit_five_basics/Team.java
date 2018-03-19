package org.carlos_witek.junit_five_basics;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Team {
	private long id;
	private Person lead;
	private Set<Person> members;

	public Team() {
		super();
	}

	public Team( final Person lead, final Set<Person> members ) {
		super();
		this.lead = lead;
		this.members = members;
	}

	@Id
	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public Person getLead() {
		return lead;
	}

	public void setLead( Person person1 ) {
		this.lead = person1;
	}

	public Set<Person> getMembers() {
		return members;
	}

	public void setMembers( Set<Person> members ) {
		this.members = members;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "Team [id=" )
				.append( id )
				.append( ", lead=" )
				.append( lead )
				.append( ", members=" )
				.append( members )
				.append( "]" );
		return builder.toString();
	}

}
