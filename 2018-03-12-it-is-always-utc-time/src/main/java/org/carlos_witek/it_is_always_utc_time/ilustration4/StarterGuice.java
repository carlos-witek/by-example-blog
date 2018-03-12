package org.carlos_witek.it_is_always_utc_time.ilustration4;

import org.carlos_witek.it_is_always_utc_time.ilustration4.dao.example1.Example1Dao;
import org.carlos_witek.it_is_always_utc_time.ilustration4.dao.example2.Example2Dao;

import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

public class StarterGuice {

	public static void main( String[] args ) {
		final JpaPersistModule persistModule = new JpaPersistModule( "it-is-always-utc-time" );
		final AbstractModule daoModule = new AbstractModule() {
			protected void configure() {
				bind( Example1Dao.class );
				bind( Example2Dao.class );
			};
		};

		final Injector injector = Guice
				.createInjector( ImmutableList.of( persistModule, daoModule ) );

		try {
			injector.getInstance( PersistService.class ).start();
			new StarterGuice().execute( injector );
		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			injector.getInstance( PersistService.class ).stop();
		}
	}

	void execute( final Injector injector ) {
	}

}
