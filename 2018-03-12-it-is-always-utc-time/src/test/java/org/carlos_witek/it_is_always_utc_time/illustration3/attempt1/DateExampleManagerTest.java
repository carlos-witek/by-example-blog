package org.carlos_witek.it_is_always_utc_time.illustration3.attempt1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.carlos_witek.it_is_always_utc_time.illustration3.ExampleManager;
import org.carlos_witek.it_is_always_utc_time.illustration3.attempt1.DateExampleManager;
import org.junit.jupiter.api.Test;

public class DateExampleManagerTest {

	@Test
	public void test_newUser() throws Exception {
		//Given
		final ExampleManager dateExampleManager = new DateExampleManager();

		//When
		final String lastSeenMessage = dateExampleManager.getLastSeenMessage( 1L );

		//Then
		assertEquals( DateExampleManager.LAST_SEEN_NEVER, lastSeenMessage );
	}
}
