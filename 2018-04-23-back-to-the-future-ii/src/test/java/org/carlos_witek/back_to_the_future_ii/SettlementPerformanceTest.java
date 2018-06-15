package org.carlos_witek.back_to_the_future_ii;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.carlos_witek.back_to_the_future_ii.impl.SettlementFaster;
import org.carlos_witek.back_to_the_future_ii.model.ContestPrize;

public class SettlementPerformanceTest {

	private static final int GAPS = 300_000;

	public static void main( String[] args ) {
		final SettlementFixtures fixtures = performanceFixtures();
		final Settlement settlement = new SettlementFaster();

		final List<ContestPrize> prizes;

		final long currentTimeMillis = System.currentTimeMillis();
		try {
			prizes = settlement.settle( fixtures.getContest() );
		} finally {
			System.out.println( System.currentTimeMillis() - currentTimeMillis );
		}

		assertParticipantPrizes( prizes, fixtures.getPrizes() );
	}

	private static SettlementFixtures performanceFixtures() {
		final Random random = new Random();

		String[] values = {	"85.00",
							"80.00",
							"75.00",
							"70.00",
							"65.00",
							"60.00",
							"55.00",
							"50.00",
							"45.00",
							"40.00",
							"35.00",
							"30.00",
							"25.00",
							"20.00",
							"15.00",
							"10.00" };

		final SettlementFixturesBuilder builder = new SettlementFixturesBuilder()
				.withTitle( "Sample 1" )
				.withPrizes( "90.00", values );

		int index = 1;
		for ( int i = 1; i < GAPS; i++ ) {
			builder.withEntry( String.format( "P%07d", index++ ), random.nextInt( 80 ) );
		}
		builder.withEntry( String.format( "P%07d", index++ ),
				100 + new BigDecimal( "90.00" ).intValue(), "90.00" );

		for ( int valuesi = 0; valuesi < values.length; valuesi++ ) {
			for ( int i = 1; i < GAPS; i++ ) {
				builder.withEntry( String.format( "P%07d", index++ ), random.nextInt( 80 ) );
			}
			builder.withEntry( String.format( "P%07d", index++ ),
					100 + new BigDecimal( values[valuesi] ).intValue(), values[valuesi] );
		}

		for ( int i = 1; i < GAPS; i++ ) {
			builder.withEntry( String.format( "P%07d", index++ ), random.nextInt( 80 ) );
		}

		return builder.build();
	}

	static void assertParticipantPrizes( final List<ContestPrize> expected,
			final List<ContestPrize> actual ) {
		final Iterator<ContestPrize> expectedIterator = expected.iterator();
		final Iterator<ContestPrize> actualIterator = actual.iterator();

		while ( expectedIterator.hasNext() && actualIterator.hasNext() ) {
			assertParticipantPrize( expectedIterator.next(), actualIterator.next() );
		}
		if ( expectedIterator.hasNext() ) {
			fail( String.format( "expected %s not matched", expectedIterator.next() ) );
		}
		if ( actualIterator.hasNext() ) {
			fail( String.format( "actual %s not matched", actualIterator.next() ) );
		}
	}

	static void assertParticipantPrize( final ContestPrize expected, final ContestPrize actual ) {
		assertAll( "prize",
				() -> assertEquals( expected.getParticipantName(), actual.getParticipantName() ),
				() -> assertEquals( expected.getPrize(), actual.getPrize() ) );
	}

}
