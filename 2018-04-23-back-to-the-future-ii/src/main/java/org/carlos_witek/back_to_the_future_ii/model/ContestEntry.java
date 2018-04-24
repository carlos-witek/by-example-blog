package org.carlos_witek.back_to_the_future_ii.model;

import com.google.common.base.MoreObjects;

public class ContestEntry {
	private final String participantName;
	private final int score;

	public ContestEntry( final String participantName, final int score ) {
		this.participantName = participantName;
		this.score = score;
	}

	public String getParticipantName() {
		return participantName;
	}

	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper( this )
				.add( "participantName", participantName )
				.add( "score", score )
				.toString();
	}
}
