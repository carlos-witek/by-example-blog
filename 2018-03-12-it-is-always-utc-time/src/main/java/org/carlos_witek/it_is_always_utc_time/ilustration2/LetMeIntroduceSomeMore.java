package org.carlos_witek.it_is_always_utc_time.ilustration2;

import static java.lang.String.format;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class LetMeIntroduceSomeMore {

	public static void main( String[] args ) {
		System.out.println( "Duration" );

		final LocalTime localTime = LocalTime.of( 12, 34, 56 );

		printDuration( localTime, localTime.plus( 1, ChronoUnit.HOURS ) );
		printDuration( localTime,
				localTime.plus( 1, ChronoUnit.HOURS ).plus( 2, ChronoUnit.MINUTES ) );
		printDuration( localTime,
				localTime.plus( 1, ChronoUnit.HOURS ).plus( 2, ChronoUnit.MINUTES ).plus( 20,
						ChronoUnit.SECONDS ) );
		printDuration( localTime,
				localTime.plus( 1, ChronoUnit.HOURS )
						.plus( 2, ChronoUnit.MINUTES )
						.plus( 20, ChronoUnit.SECONDS )
						.plus( 10, ChronoUnit.MILLIS ) );
		printDuration( localTime,
				localTime.plus( 1, ChronoUnit.HOURS )
						.plus( 2, ChronoUnit.MINUTES )
						.plus( 20, ChronoUnit.SECONDS )
						.plus( 10, ChronoUnit.MILLIS )
						.plus( 5, ChronoUnit.MICROS ) );

		printDuration( localTime, localTime.minus( 1, ChronoUnit.HOURS ) );
		printDuration( localTime,
				localTime.minus( 1, ChronoUnit.HOURS ).minus( 2, ChronoUnit.MINUTES ) );
		printDuration( localTime,
				localTime.minus( 1, ChronoUnit.HOURS ).minus( 2, ChronoUnit.MINUTES ).minus( 3,
						ChronoUnit.SECONDS ) );
		printDuration( localTime,
				localTime.minus( 1, ChronoUnit.HOURS )
						.minus( 2, ChronoUnit.MINUTES )
						.minus( 3, ChronoUnit.SECONDS )
						.minus( 4, ChronoUnit.MILLIS ) );
		printDuration( localTime,
				localTime.minus( 1, ChronoUnit.HOURS )
						.minus( 2, ChronoUnit.MINUTES )
						.minus( 3, ChronoUnit.SECONDS )
						.minus( 4, ChronoUnit.MILLIS )
						.minus( 5, ChronoUnit.MICROS ) );

		System.out.println();

		System.out.println( "Period" );

		final LocalDate localDate = LocalDate.of( 2019, Month.FEBRUARY, 28 );

		printPeriod( localDate, localDate.plus( 1, ChronoUnit.DAYS ) );
		printPeriod( localDate, localDate.plus( 1, ChronoUnit.DAYS ).plus( 2, ChronoUnit.WEEKS ) );
		printPeriod( localDate,
				localDate.plus( 1, ChronoUnit.DAYS ).plus( 2, ChronoUnit.WEEKS ).plus( 3,
						ChronoUnit.MONTHS ) );
		printPeriod( localDate,
				localDate.plus( 1, ChronoUnit.DAYS )
						.plus( 2, ChronoUnit.WEEKS )
						.plus( 3, ChronoUnit.MONTHS )
						.plus( 4, ChronoUnit.YEARS ) );

		printPeriod( localDate, localDate.minus( 1, ChronoUnit.DAYS ) );
		printPeriod( localDate,
				localDate.minus( 1, ChronoUnit.DAYS ).minus( 2, ChronoUnit.WEEKS ) );
		printPeriod( localDate,
				localDate.minus( 1, ChronoUnit.DAYS ).minus( 2, ChronoUnit.WEEKS ).minus( 3,
						ChronoUnit.MONTHS ) );
		printPeriod( localDate,
				localDate.minus( 1, ChronoUnit.DAYS )
						.minus( 2, ChronoUnit.WEEKS )
						.minus( 3, ChronoUnit.MONTHS )
						.minus( 4, ChronoUnit.YEARS ) );
	}

	// time based
	private static void printDuration( final Temporal startInclusive,
			final Temporal endExclusive ) {

		final Duration duration = Duration.between( startInclusive, endExclusive );
		System.out.println( format( "%1$-40s : %2$s",
				format( "%1$s => %2$s", startInclusive, endExclusive ), duration ) );
	}

	// date based
	private static void printPeriod( final LocalDate startDateInclusive,
			final LocalDate endDateExclusive ) {
		final Period period = Period.between( startDateInclusive, endDateExclusive );
		System.out.println( format( "%1$-40s : %2$s",
				format( "%1$s => %2$s", startDateInclusive, endDateExclusive ), period ) );
	}

}
