package org.carlos_witek.it_is_always_utc_time.illustration3;

public interface ExampleManager {

	public static final String LAST_SEEN_NEVER = "Hello, nice to meet you!";
	public static final String LAST_SEEN_TODAY = "Hello, back for more?";
	public static final String LAST_SEEN_YESTERDAY = "Hello, I haven't see you since yesterday!";
	public static final String LAST_SEEN_LAST_WEEK = "Hello, I haven't see you since last week!";
	public static final String LAST_SEEN_LONG_TIME_AGO = "Hello, I haven't see you in ages!";

	String getLastSeenMessage( Long userId );

}
