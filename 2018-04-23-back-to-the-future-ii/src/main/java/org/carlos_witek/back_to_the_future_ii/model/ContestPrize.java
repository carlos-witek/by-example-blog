package org.carlos_witek.back_to_the_future_ii.model;

import java.math.BigDecimal;

import com.google.common.base.MoreObjects;

public class ContestPrize {
	private final String participantName;
	private final BigDecimal prize;

	public ContestPrize( final String participantName, final BigDecimal prize ) {
		this.participantName = participantName;
		this.prize = prize;
	}

	public String getParticipantName() {
		return participantName;
	}

	public BigDecimal getPrize() {
		return prize;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper( this )
				.add( "participantName", participantName )
				.add( "prize", prize )
				.toString();
	}
}
