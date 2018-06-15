package org.carlos_witek.back_to_the_future_ii.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.carlos_witek.back_to_the_future_ii.Settlement;
import org.carlos_witek.back_to_the_future_ii.model.Contest;
import org.carlos_witek.back_to_the_future_ii.model.ContestEntry;
import org.carlos_witek.back_to_the_future_ii.model.ContestPrize;

public class SettlementFaster implements Settlement {

	@Override
	public List<ContestPrize> settle( final Contest contest ) {
		TreeMap<Integer, List<ContestEntry>> entriesByScores = null;
		for ( int i = 0; i < 5; i++ ) {
			final long currentTimeMillis = System.currentTimeMillis();
			try {
				entriesByScores = toEntriesByScores( contest );
			} finally {
				System.out.println( System.currentTimeMillis() - currentTimeMillis );
			}
		}
		System.out.println();
		for ( int i = 0; i < 5; i++ ) {
			final long currentTimeMillis = System.currentTimeMillis();
			try {
				entriesByScores = toEntriesByScoresOld( contest );
			} finally {
				System.out.println( System.currentTimeMillis() - currentTimeMillis );
			}
		}
		System.out.println();

		final long currentTimeMillis = System.currentTimeMillis();
		try {
			return xxxxxx( contest, entriesByScores );
		} finally {
			System.out.println( System.currentTimeMillis() - currentTimeMillis );
		}
	}

	TreeMap<Integer, List<ContestEntry>> toEntriesByScores( final Contest contest ) {
		final List<ContestEntry> entries = contest.getEntries();

		ExecutorService executorService = Executors.newCachedThreadPool();

		TreeMap<Integer, List<ContestEntry>> map = new TreeMap<>( Collections.reverseOrder() );

		Builder<CompletableFuture<TreeMap<Integer, List<ContestEntry>>>> builder = Stream.builder();

		int fromIndex = 0;
		final int subListSize = 10_000;
		while ( fromIndex + subListSize < entries.size() ) {
			final int fromIndexFinal = fromIndex;
			final int toIndexFinal = fromIndex += subListSize;
			CompletableFuture<TreeMap<Integer, List<ContestEntry>>> supplyAsync = CompletableFuture
					.supplyAsync( () -> {
						List<ContestEntry> subList = entries.subList( fromIndexFinal,
								toIndexFinal );
						TreeMap<Integer, List<ContestEntry>> submap = toEntriesByScoresSub(
								subList );
						return submap;
					}, executorService );
			builder.accept( supplyAsync );
		}

		builder.build().map( CompletableFuture::join ).forEach( submap -> map.putAll( submap ) );
		;

		return map;
	}

	TreeMap<Integer, List<ContestEntry>> toEntriesByScoresSub( final List<ContestEntry> entries ) {

		TreeMap<Integer, List<ContestEntry>> entriesByScores = new TreeMap<>(
				Collections.reverseOrder() );
		for ( ContestEntry contestEntry : entries ) {
			entriesByScores.computeIfAbsent( contestEntry.getScore(), s -> new LinkedList<>() )
					.add( contestEntry );
		}

		return entriesByScores;
	}

	TreeMap<Integer, List<ContestEntry>> toEntriesByScoresOld( final Contest contest ) {
		final TreeMap<Integer, List<ContestEntry>> entriesByScores = contest.getEntries()
				.stream()
				.collect( Collectors.groupingBy( ContestEntry::getScore,
						() -> new TreeMap<>( Collections.reverseOrder() ), Collectors.toList() ) );
		return entriesByScores;
	}

	List<ContestPrize> xxxxxx( final Contest contest,
			final TreeMap<Integer, List<ContestEntry>> entriesByScores ) {
		final List<ContestPrize> contestPrizes = new LinkedList<>();

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
