package org.carlos_witek.it_is_always_utc_time.ilustration3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.carlos_witek.it_is_always_utc_time.AdjustableClock;
import org.carlos_witek.it_is_always_utc_time.ilustration3.attempt1.DateExampleManager;
import org.carlos_witek.it_is_always_utc_time.ilustration3.attempt3.InstantExampleManager;
import org.junit.jupiter.api.Test;

public class InstantExampleManagerTest {

	@Test
	public void test_newUser() throws Exception {
		//Given
		final AdjustableClock clock = new AdjustableClock();
		ExampleManager dateExampleManager = new InstantExampleManager( clock );

		//When
		String lastSeenMessage = dateExampleManager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_NEVER, lastSeenMessage );
	}

	@Test
	public void test_returningUser_afterFewMinutes() throws Exception {
		//Given
		final AdjustableClock clock = new AdjustableClock();
		ExampleManager dateExampleManager = new InstantExampleManager( clock );

		dateExampleManager.getLastSeenMessage( 1L );
		// clock.setNextInstant( Instant.now().plus( 1, ChronoUnit.MINUTES ) );

		//When
		String lastSeenMessage = dateExampleManager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_TODAY, lastSeenMessage );
	}

	@Test
	public void test_returningUser_afterADay() throws Exception {
		//Given
		final AdjustableClock clock = new AdjustableClock();
		ExampleManager dateExampleManager = new InstantExampleManager( clock );

		//When
		String lastSeenMessage = dateExampleManager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_YESTERDAY, lastSeenMessage );
	}

	@Test
	public void test_returningUser_afterAWeek() throws Exception {
		//Given
		final AdjustableClock clock = new AdjustableClock();
		ExampleManager dateExampleManager = new InstantExampleManager( clock );

		//When
		String lastSeenMessage = dateExampleManager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_LAST_WEEK, lastSeenMessage );
	}

}
