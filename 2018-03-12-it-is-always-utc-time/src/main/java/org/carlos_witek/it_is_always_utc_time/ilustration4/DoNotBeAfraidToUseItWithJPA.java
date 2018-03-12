package org.carlos_witek.it_is_always_utc_time.ilustration4;

import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

import org.carlos_witek.it_is_always_utc_time.ilustration4.dao.example1.Example1Dao;
import org.carlos_witek.it_is_always_utc_time.ilustration4.dao.example2.Example2Dao;

import com.google.inject.Injector;

public class DoNotBeAfraidToUseItWithJPA extends StarterGuice {

	@Override
	void execute( final Injector injector ) {

		final Example1Dao example1Dao = injector.getInstance( Example1Dao.class );
		final Example2Dao example2Dao = injector.getInstance( Example2Dao.class );

		{ // hard to read
			final Long exampleId = example1Dao.createExample( "test-value-1" );

			TimeZone defaultTimeZone = TimeZone.getDefault();
			System.out.println( TimeZone.getDefault().getID() );

			final Date example1CreatedAtDT = example1Dao.findExample1( exampleId ).getCreatedAt();
			System.out.println( example1CreatedAtDT );

			TimeZone.setDefault( TimeZone.getTimeZone( "Australia/Sydney" ) );

			System.out.println( TimeZone.getDefault().getID() );
			final Date example1CreatedAtAEDT = example1Dao.findExample1( exampleId ).getCreatedAt();
			System.out.println( example1CreatedAtAEDT );

			TimeZone.setDefault( defaultTimeZone );
		}

		System.out.println();

		{ // easy to read
			final Long exampleId = example1Dao.createExample( "test-value-2" );

			TimeZone defaultTimeZone = TimeZone.getDefault();
			System.out.println( TimeZone.getDefault().getID() );

			final Instant example2CreatedAtDT = example2Dao.findExample2( exampleId )
					.getCreatedAt();
			System.out.println( example2CreatedAtDT );

			TimeZone.setDefault( TimeZone.getTimeZone( "Australia/Sydney" ) );

			System.out.println( TimeZone.getDefault().getID() );
			final Instant example2CreatedAtAEDT = example2Dao.findExample2( exampleId )
					.getCreatedAt();
			System.out.println( example2CreatedAtAEDT );

			TimeZone.setDefault( defaultTimeZone );
		}

	}

}
