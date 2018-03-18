package org.carlos_witek.it_is_always_utc_time.illustration3.attempt3;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.carlos_witek.it_is_always_utc_time.illustration3.ExampleManager;

public class InstantExampleManager implements ExampleManager {

	private final Clock clock;

	private Map<Long, Instant> lastSeenMillisMap = new HashMap<>();

	public InstantExampleManager() {
		this( Clock.systemDefaultZone() );
	}

	public InstantExampleManager( final Clock clock ) {
		this.clock = clock;
	}

	@Override
	public String getLastSeenMessage( final Long userId ) {
		final Instant now = Instant.now( clock );
		try {
			if ( lastSeenMillisMap.containsKey( userId ) ) {
				final Instant lastSeen = lastSeenMillisMap.get( userId );
				final Duration duration = Duration.between( lastSeen, now );

				final long durationDays = duration.toDays();
				if ( 14 < durationDays ) {
					return ExampleManager.LAST_SEEN_LONG_TIME_AGO;
				} else if ( 7 < durationDays && durationDays <= 14 ) {
					return ExampleManager.LAST_SEEN_LAST_WEEK;
				} else if ( 0 < durationDays && durationDays <= 2 ) {
					return ExampleManager.LAST_SEEN_YESTERDAY;
				}
				return ExampleManager.LAST_SEEN_TODAY;
			} else {
				return ExampleManager.LAST_SEEN_NEVER;
			}
		} finally {
			lastSeenMillisMap.put( userId, now );
		}
	}

}
