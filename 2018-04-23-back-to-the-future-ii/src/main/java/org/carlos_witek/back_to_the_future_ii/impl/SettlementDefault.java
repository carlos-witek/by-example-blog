package org.carlos_witek.back_to_the_future_ii.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.carlos_witek.back_to_the_future_ii.Settlement;
import org.carlos_witek.back_to_the_future_ii.model.Contest;
import org.carlos_witek.back_to_the_future_ii.model.ContestEntry;
import org.carlos_witek.back_to_the_future_ii.model.ContestPrize;

public class SettlementDefault implements Settlement {

	@Override
	public List<ContestPrize> settle( final Contest contest ) {
		final List<ContestPrize> contestPrizes = new LinkedList<>();

		final Map<Integer, List<ContestEntry>> entriesByScores = contest.getEntries()
				.stream()
				.collect( Collectors.groupingBy( ContestEntry::getScore,
						() -> new TreeMap<>( Collections.reverseOrder() ), Collectors.toList() ) );

		int position = 0;
		for ( final Integer entriesScore : entriesByScores.keySet() ) {
			final List<ContestEntry> entriesByScore = entriesByScores.get( entriesScore );

			final BigDecimal entriesPrizeSum = contest.getPrizes()
					.subList( position,
							Math.min( contest.getPrizes().size(),
									position += entriesByScore.size() ) )
					.stream()
					.reduce( BigDecimal.ZERO, BigDecimal::add );

			if ( entriesPrizeSum.compareTo( BigDecimal.ZERO ) < 1 ) {
				break;
			}

			final BigDecimal entryPrize = entriesPrizeSum
					.divide( BigDecimal.valueOf( entriesByScore.size() ), 2, RoundingMode.DOWN );

			List<ContestPrize> entryPrizes = entriesByScore.stream()
					.map( toContestEntryPrize( entryPrize ) )
					.collect( Collectors.toList() );

			contestPrizes.addAll( entryPrizes );
		}

		return contestPrizes;
	}

	private Function<ContestEntry, ContestPrize> toContestEntryPrize(
			final BigDecimal entryPrize ) {
		return entry -> new ContestPrize( entry.getParticipantName(), entryPrize );
	}

}
