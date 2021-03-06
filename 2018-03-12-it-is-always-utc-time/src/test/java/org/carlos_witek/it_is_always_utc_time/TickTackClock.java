package org.carlos_witek.it_is_always_utc_time;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public final class TickTackClock extends Clock {

	private Instant instant;

	@Override
	public ZoneId getZone() {
		return ZoneId.systemDefault();
	}

	@Override
	public Clock withZone( ZoneId zone ) {
		return this;
	}

	@Override
	public Instant instant() {
		return instant;
	}

	public void tick( final Instant instant ) {
		this.instant = instant;
	}
}
