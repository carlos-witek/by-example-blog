package org.carlos_witek.back_to_the_future_ii;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.carlos_witek.back_to_the_future_ii.model.Contest;
import org.carlos_witek.back_to_the_future_ii.model.ContestEntry;
import org.carlos_witek.back_to_the_future_ii.model.ContestPrize;

import com.google.common.collect.Lists;

public class SettlementFixturesBuilder {

	private String title;
	private List<BigDecimal> prizes;
	private List<ContestEntry> contestEntries = new LinkedList<>();
	private List<ContestPrize> contestPrizes = new LinkedList<>();

	public SettlementFixturesBuilder withTitle( final String title ) {
		this.title = title;
		return this;
	}

	public SettlementFixturesBuilder withPrizes( final String first, final String... rest ) {
		this.prizes = Lists.asList( first, rest ).stream().map( BigDecimal::new ).collect(
				Collectors.toList() );
		return this;
	}

	public SettlementFixturesBuilder withEntry( final String participantName,
			final int participantScore ) {
		this.contestEntries.add( new ContestEntry( participantName, participantScore ) );
		return this;
	}

	public SettlementFixturesBuilder withEntry( final String participantName,
			final int participantScore, final String participantPrize ) {
		this.contestEntries.add( new ContestEntry( participantName, participantScore ) );
		this.contestPrizes
				.add( new ContestPrize( participantName, new BigDecimal( participantPrize ) ) );
		return this;
	}

	public SettlementFixtures build() {
		return new SettlementFixtures( new Contest( title, prizes, contestEntries ),
				contestPrizes );
	}

}
