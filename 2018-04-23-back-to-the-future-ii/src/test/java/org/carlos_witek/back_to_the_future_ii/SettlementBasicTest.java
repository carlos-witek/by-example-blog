package org.carlos_witek.back_to_the_future_ii;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.carlos_witek.back_to_the_future_ii.impl.SettlementDefault;
import org.carlos_witek.back_to_the_future_ii.model.ContestPrize;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SettlementBasicTest {

	@ParameterizedTest
	@MethodSource("basicFixtures")
	void basic( final SettlementFixtures fixtures ) {
		final List<ContestPrize> prizes = new SettlementDefault().settle( fixtures.getContest() );
		assertParticipantPrizes( prizes, fixtures.getPrizes() );
	}

	static Stream<SettlementFixtures> basicFixtures() {

		final Stream.Builder<SettlementFixtures> builder = Stream.builder();

		builder.accept( new SettlementFixturesBuilder().withTitle( "Sample 1" )
				.withPrizes( "60.00", "40.00", "20.00" )
				.withEntry( "Player1", 77, "60.00" )
				.withEntry( "Player2", 66, "40.00" )
				.withEntry( "Player3", 55, "20.00" )
				.withEntry( "Player4", 44 )
				.build() );

		builder.accept( new SettlementFixturesBuilder().withTitle( "Sample 2" )
				.withPrizes( "60.00", "40.00", "20.00" )
				.withEntry( "Player1", 66, "50.00" )
				.withEntry( "Player2", 66, "50.00" )
				.withEntry( "Player3", 55, "20.00" )
				.withEntry( "Player4", 44 )
				.build() );

		builder.accept( new SettlementFixturesBuilder().withTitle( "Sample 3" )
				.withPrizes( "60.00", "40.00", "20.00" )
				.withEntry( "Player1", 77, "60.00" )
				.withEntry( "Player2", 55, "30.00" )
				.withEntry( "Player3", 55, "30.00" )
				.withEntry( "Player4", 44 )
				.build() );

		builder.accept( new SettlementFixturesBuilder().withTitle( "Sample 4" )
				.withPrizes( "60.00", "40.00", "20.00" )
				.withEntry( "Player1", 77, "60.00" )
				.withEntry( "Player2", 66, "40.00" )
				.withEntry( "Player3", 44, "10.00" )
				.withEntry( "Player4", 44, "10.00" )
				.build() );

		builder.accept( new SettlementFixturesBuilder().withTitle( "Sample 5" )
				.withPrizes( "60.00", "40.00", "20.00" )
				.withEntry( "Player1", 55, "40.00" )
				.withEntry( "Player2", 55, "40.00" )
				.withEntry( "Player3", 55, "40.00" )
				.withEntry( "Player4", 44 )
				.build() );

		builder.accept( new SettlementFixturesBuilder().withTitle( "Sample 6" )
				.withPrizes( "60.00", "40.00", "20.00" )
				.withEntry( "Player1", 77, "60.00" )
				.withEntry( "Player2", 44, "20.00" )
				.withEntry( "Player3", 44, "20.00" )
				.withEntry( "Player4", 44, "20.00" )
				.build() );

		return builder.build();
	}

	void assertParticipantPrizes( final List<ContestPrize> expected,
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

	void assertParticipantPrize( final ContestPrize expected, final ContestPrize actual ) {
		assertAll( "prize",
				() -> assertEquals( expected.getParticipantName(), actual.getParticipantName() ),
				() -> assertEquals( expected.getPrize(), actual.getPrize() ) );
	}

}
