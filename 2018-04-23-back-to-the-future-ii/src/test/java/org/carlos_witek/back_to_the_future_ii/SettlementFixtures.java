package org.carlos_witek.back_to_the_future_ii;

import java.util.List;

import org.carlos_witek.back_to_the_future_ii.model.Contest;
import org.carlos_witek.back_to_the_future_ii.model.ContestPrize;

import com.google.common.base.MoreObjects;

public class SettlementFixtures {

	private final Contest contest;
	private final List<ContestPrize> prizes;

	public SettlementFixtures( final Contest contest, final List<ContestPrize> prizes ) {
		this.contest = contest;
		this.prizes = prizes;
	}

	public Contest getContest() {
		return contest;
	}

	public List<ContestPrize> getPrizes() {
		return prizes;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper( this )
				.add( "contest", contest )
				.add( "prizes", prizes )
				.toString();
	}

}
