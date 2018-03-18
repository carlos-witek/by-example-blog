package org.carlos_witek.it_is_always_utc_time.illustration3.attempt1;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.carlos_witek.it_is_always_utc_time.illustration3.ExampleManager;

public class DateExampleManager implements ExampleManager {

	private Map<Long, Date> lastSeenDates = new HashMap<>();

	@Override
	public String getLastSeenMessage( final Long userId ) {
		final Date currentDate = new Date();
		try {
			if ( lastSeenDates.containsKey( userId ) ) {
				final Date lastSeenDate = lastSeenDates.get( userId );
				final long duration = currentDate.getTime() - lastSeenDate.getTime();

				final long days = TimeUnit.MILLISECONDS.toDays( duration );
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
			lastSeenDates.put( userId, currentDate );
		}
	}

}
