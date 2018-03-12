package org.carlos_witek.it_is_always_utc_time.ilustration2;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

public class LetMeIntroduceSomeMore {

	public static void main( String[] args ) {
		final long currentTimeMillis = System.currentTimeMillis();

		print( currentTimeMillis );

		TimeZone.setDefault( TimeZone.getTimeZone( "America/New_York" ) );
		print( currentTimeMillis );

		TimeZone.setDefault( TimeZone.getTimeZone( "Australia/Sydney" ) );
		print( currentTimeMillis );
	}

	private static void print( final long currentTimeMillis ) {
		System.out.println( TimeZone.getDefault().getID() );

		final Date date = new Date( currentTimeMillis );
		System.out.println( date );
		System.out.println();

		final Instant instant = Instant.ofEpochMilli( currentTimeMillis );
		System.out.println( instant );
		System.out.println();

		final Clock clock = Clock.fixed( instant, ZoneId.systemDefault() );

		final LocalDate localDate = LocalDate.now( clock );
		System.out.println( localDate );
		final LocalTime localTime = LocalTime.now( clock );
		System.out.println( localTime );
		final LocalDateTime localDateTime = LocalDateTime.now( clock );
		System.out.println( localDateTime );
		System.out.println();

		final OffsetTime offsetTime = OffsetTime.now( clock );
		System.out.println( offsetTime );
		final OffsetDateTime offsetDateTime = OffsetDateTime.now( clock );
		System.out.println( offsetDateTime );
		final ZonedDateTime zonedDateTime = ZonedDateTime.now( clock );
		System.out.println( zonedDateTime );
		System.out.println();
		System.out.println( "-------------------------------" );
		System.out.println();
	}

}
