package org.carlos_witek.it_is_always_utc_time.ilustration3.attempt2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.carlos_witek.it_is_always_utc_time.ilustration3.attempt1.DateExampleManager;
import org.junit.jupiter.api.Test;

public class MillisExampleManagerTest {

	public static class MillisExampleManagerSpy extends MillisExampleManager {

		public Long currentTimeMillis;

		@Override
		long currentTimeMillis() {
			if ( currentTimeMillis == null ) {
				return super.currentTimeMillis();
			}
			try {
				return currentTimeMillis;
			} finally {
				currentTimeMillis = null;
			}
		}
	}

	@Test
	public void test_newUser() throws Exception {
		//Given
		final MillisExampleManagerSpy manager = new MillisExampleManagerSpy();

		//When
		final String lastSeenMessage = manager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_NEVER, lastSeenMessage );
	}

	@Test
	public void test_returningUser_afterFewMinutes() throws Exception {
		//Given
		final MillisExampleManagerSpy manager = new MillisExampleManagerSpy();
		manager.currentTimeMillis = 1L;
		manager.getLastSeenMessage( 1L );
		manager.currentTimeMillis = 1L + TimeUnit.HOURS.toMillis( 23 );

		//When
		final String lastSeenMessage = manager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_TODAY, lastSeenMessage );
	}

	@Test
	public void test_returningUser_afterADay() throws Exception {
		//Given
		final MillisExampleManagerSpy manager = new MillisExampleManagerSpy();
		manager.currentTimeMillis = 1000L;
		manager.getLastSeenMessage( 1L );
		manager.currentTimeMillis = 1000L + TimeUnit.DAYS.toMillis( 1 );

		//When
		final String lastSeenMessage = manager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_YESTERDAY, lastSeenMessage );
	}

	@Test
	public void test_returningUser_afterAWeek() throws Exception {
		//Given
		final MillisExampleManagerSpy manager = new MillisExampleManagerSpy();
		manager.currentTimeMillis = 1000L;
		manager.getLastSeenMessage( 1L );
		manager.currentTimeMillis = 1000L + TimeUnit.DAYS.toMillis( 8 );

		//When
		final String lastSeenMessage = manager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_LAST_WEEK, lastSeenMessage );
	}

}
