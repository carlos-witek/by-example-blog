package org.carlos_witek.it_is_always_utc_time.illustration3.attempt3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.carlos_witek.it_is_always_utc_time.TickTackClock;
import org.carlos_witek.it_is_always_utc_time.illustration3.ExampleManager;
import org.carlos_witek.it_is_always_utc_time.illustration3.attempt1.DateExampleManager;
import org.carlos_witek.it_is_always_utc_time.illustration3.attempt3.InstantExampleManager;
import org.junit.jupiter.api.Test;

public class InstantExampleManagerTest {

	@Test
	public void test_newUser() throws Exception {
		//Given
		final TickTackClock clock = new TickTackClock();
		final ExampleManager manager = new InstantExampleManager( clock );
		clock.tick( Instant.now() );

		//When
		final String lastSeenMessage = manager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_NEVER, lastSeenMessage );
	}

	@Test
	public void test_returningUser_afterFewMinutes() throws Exception {
		final Instant now = Instant.now();

		//Given
		final TickTackClock clock = new TickTackClock();
		final ExampleManager manager = new InstantExampleManager( clock );
		clock.tick( now );
		manager.getLastSeenMessage( 1L );
		clock.tick( now.plus( 1, ChronoUnit.MINUTES ) );

		//When
		final String lastSeenMessage = manager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_TODAY, lastSeenMessage );
	}

	@Test
	public void test_returningUser_afterADay() throws Exception {
		final Instant now = Instant.now();

		//Given
		final TickTackClock clock = new TickTackClock();
		final ExampleManager manager = new InstantExampleManager( clock );
		clock.tick( now );
		manager.getLastSeenMessage( 1L );
		clock.tick( now.plus( 1, ChronoUnit.DAYS ) );

		//When
		final String lastSeenMessage = manager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_YESTERDAY, lastSeenMessage );
	}

	@Test
	public void test_returningUser_afterAWeek() throws Exception {
		final Instant now = Instant.now();

		//Given
		final TickTackClock clock = new TickTackClock();
		final ExampleManager manager = new InstantExampleManager( clock );
		clock.tick( now );
		manager.getLastSeenMessage( 1L );
		clock.tick( now.plus( 8, ChronoUnit.DAYS ) );

		//When
		final String lastSeenMessage = manager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_LAST_WEEK, lastSeenMessage );
	}

}
