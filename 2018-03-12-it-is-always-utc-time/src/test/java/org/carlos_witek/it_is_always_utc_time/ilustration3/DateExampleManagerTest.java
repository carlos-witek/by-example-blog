package org.carlos_witek.it_is_always_utc_time.ilustration3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.carlos_witek.it_is_always_utc_time.ilustration3.attempt1.DateExampleManager;
import org.junit.jupiter.api.Test;

public class DateExampleManagerTest {

	ExampleManager instance() {
		return new DateExampleManager();
	}

	@Test
	public void test_newUser() throws Exception {
		//Given
		ExampleManager dateExampleManager = instance();

		//When
		String lastSeenMessage = dateExampleManager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_NEVER, lastSeenMessage );
	}

	@Test
	public void test_returningUser_afterFewMinutes() throws Exception {
		//Given
		ExampleManager dateExampleManager = instance();

		//When
		String lastSeenMessage = dateExampleManager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_TODAY, lastSeenMessage );
	}

	@Test
	public void test_returningUser_afterADay() throws Exception {
		//Given
		ExampleManager dateExampleManager = instance();

		//When
		String lastSeenMessage = dateExampleManager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_YESTERDAY, lastSeenMessage );
	}

	@Test
	public void test_returningUser_afterAWeek() throws Exception {
		//Given
		ExampleManager dateExampleManager = instance();

		//When
		String lastSeenMessage = dateExampleManager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_LAST_WEEK, lastSeenMessage );
	}
}
