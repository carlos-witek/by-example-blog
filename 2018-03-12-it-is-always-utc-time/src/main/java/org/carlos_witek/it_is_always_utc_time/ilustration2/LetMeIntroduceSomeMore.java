package org.carlos_witek.it_is_always_utc_time.ilustration2;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
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

		// time based
		final LocalDateTime startDateTime = LocalDate.of( 2018, 01, 01 )
				.atTime( LocalTime.of( 00, 00, 00 ) );
		final LocalDateTime finishDateTime = LocalDate.of( 2018, 02, 02 )
				.atTime( LocalTime.of( 01, 02, 03 ) );

		final Duration duration = Duration.between( startDateTime, finishDateTime );
		System.out.println( duration );
		System.out.println( toString( duration ) );

		System.out.println();

		// date based
		final LocalDate startDate = LocalDate.of( 2016, 01, 01 );
		final LocalDate finishDate = LocalDate.of( 2018, 02, 02 );

		Period period = Period.between( startDate, finishDate );
		System.out.println( period );

		System.out.println();

		System.out.println( "-------------------------------" );
		System.out.println();
	}

	private static String toString( Duration duration ) {
		final long minutes = duration.toMinutes();
		final long hours = duration.toHours();
		return String.format( "%dD %dH %dM", duration.toDays(), hours % 24, minutes % 60 );
	}

}
