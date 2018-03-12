package org.carlos_witek.it_is_always_utc_time;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public final class AdjustableClock extends Clock {

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
		return instant == null ? Clock.system( getZone() ).instant() : instant;
	}

	public void setNextInstant( Instant instant ) {
		this.instant = instant;
	}
}
