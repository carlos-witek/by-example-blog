package org.carlos_witek.it_is_always_utc_time.ilustration3.attempt2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.carlos_witek.it_is_always_utc_time.ilustration3.ExampleManager;

public class MillisExampleManager implements ExampleManager {

	private Map<Long, Long> lastSeenMillisMap = new HashMap<>();

	@Override
	public String getLastSeenMessage( final Long userId ) {
		final long currentMillis = System.currentTimeMillis();
		try {
			if ( lastSeenMillisMap.containsKey( userId ) ) {
				final Long lastSeenMillis = lastSeenMillisMap.get( userId );
				final long duration = currentMillis - lastSeenMillis;

				long days = TimeUnit.MILLISECONDS.toDays( duration );
				if ( 14 < days ) {
					return ExampleManager.LAST_SEEN_LONG_TIME_AGO;
				} else if ( 7 < days && days <= 14 ) {
					return ExampleManager.LAST_SEEN_LAST_WEEK;
				} else if ( 0 < days && days <= 2 ) {
					return ExampleManager.LAST_SEEN_YESTERDAY;
				}
				return ExampleManager.LAST_SEEN_TODAY;
			} else {
				return ExampleManager.LAST_SEEN_NEVER;
			}
		} finally {
			lastSeenMillisMap.put( userId, currentMillis );
		}
	}

}
