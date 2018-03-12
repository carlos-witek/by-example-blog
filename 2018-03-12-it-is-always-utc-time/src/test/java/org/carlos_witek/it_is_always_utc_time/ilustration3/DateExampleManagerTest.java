package org.carlos_witek.it_is_always_utc_time.ilustration3;

import org.carlos_witek.it_is_always_utc_time.ilustration3.attempt1.DateExampleManager;

public class DateExampleManagerTest extends ExampleManagerTest {

	@Override
	DateExampleManager instance() {
		return new DateExampleManager();
	}

}
