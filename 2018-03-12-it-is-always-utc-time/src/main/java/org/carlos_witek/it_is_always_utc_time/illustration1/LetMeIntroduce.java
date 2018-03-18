package org.carlos_witek.it_is_always_utc_time.illustration1;

import static java.lang.String.format;

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

public class LetMeIntroduce {

	public static void main( String[] args ) {
		final long currentTimeMillis = System.currentTimeMillis();

		print( currentTimeMillis );

		TimeZone.setDefault( TimeZone.getTimeZone( "America/New_York" ) );
		print( currentTimeMillis );

		TimeZone.setDefault( TimeZone.getTimeZone( "Australia/Sydney" ) );
		print( currentTimeMillis );
	}

	private static void print( final long currentTimeMillis ) {
		System.out.println(
				"----------------------------------------------------------------------" );
		System.out.println( TimeZone.getDefault().getID() );
		System.out.println(
				"----------------------------------------------------------------------" );

		final Clock clock = Clock.fixed( Instant.ofEpochMilli( currentTimeMillis ),
				ZoneId.systemDefault() );

		printValue(currentTimeMillis);
		printValue( new Date( currentTimeMillis ) );
		printValue( Instant.ofEpochMilli( currentTimeMillis ) );
		printValue( LocalDate.now( clock ) );
		printValue( LocalTime.now( clock ) );
		printValue( LocalDateTime.now( clock ) );
		printValue( OffsetTime.now( clock ) );
		printValue( OffsetDateTime.now( clock ) );
		printValue( ZonedDateTime.now( clock ) );

		System.out.println(
				"----------------------------------------------------------------------" );
		System.out.println();
	}

	static void printValue( final Object value ) {
		System.out.println(
				format( "%1$-20s : %2$s", value.getClass().getSimpleName(), value.toString() ) );
	}

}
