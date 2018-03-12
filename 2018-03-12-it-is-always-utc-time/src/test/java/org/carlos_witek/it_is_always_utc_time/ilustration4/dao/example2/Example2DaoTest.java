package org.carlos_witek.it_is_always_utc_time.ilustration4.dao.example2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Clock;
import java.time.Instant;

import org.carlos_witek.it_is_always_utc_time.AdjustableClock;
import org.carlos_witek.it_is_always_utc_time.ilustration4.dao.ExampleDao;
import org.carlos_witek.it_is_always_utc_time.ilustration4.dao.ExampleEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

public class Example2DaoTest {

	private Injector injector;

	@BeforeEach
	public void beforeEach() {
		final JpaPersistModule persistModule = new JpaPersistModule( "it-is-always-utc-time" );
		final AbstractModule daoModule = new AbstractModule() {
			@Override
			protected void configure() {
				super.configure();
				bind( Example2Dao.class );
				final AdjustableClock adjustableClock = new AdjustableClock();
				bind( Clock.class ).toInstance( adjustableClock );
				bind( AdjustableClock.class ).toInstance( adjustableClock );
			}
		};

		injector = Guice.createInjector( ImmutableList.of( persistModule, daoModule ) );
		injector.getInstance( PersistService.class ).start();
	}

	@AfterEach
	public void afterEach() {
		injector.getInstance( PersistService.class ).stop();
	}

	@Test
	void create() {
		final AdjustableClock adjustableClock = injector.getInstance( AdjustableClock.class );
		final ExampleDao exampleDao = injector.getInstance( Example2Dao.class );

		// GIVEN
		adjustableClock.setNextInstant( Instant.ofEpochMilli( 1 ) );

		final Long exampleId = exampleDao.createExample( "value1" );

		// THEN
		final ExampleEntity example = exampleDao.findExample( exampleId );
		assertEquals( exampleId, example.getId() );
		assertEquals( "value1", example.getValue() );
		assertEquals( "1970-01-01T00:00:00.001Z", example.getCreatedAt() );
		assertEquals( "1970-01-01T00:00:00.001Z", example.getUpdatedAt() );
	}

	@Test
	void update() {
		final AdjustableClock adjustableClock = injector.getInstance( AdjustableClock.class );
		final ExampleDao exampleDao = injector.getInstance( Example2Dao.class );

		// GIVEN
		adjustableClock.setNextInstant( Instant.ofEpochMilli( 1 ) );
		final Long exampleId = exampleDao.createExample( "value1" );
		adjustableClock.setNextInstant( Instant.ofEpochMilli( 2 ) );
		exampleDao.updateExample( exampleId, "value2" );

		// THEN
		final ExampleEntity example = exampleDao.findExample( exampleId );
		assertEquals( exampleId, example.getId() );
		assertEquals( "value2", example.getValue() );
		assertEquals( "1970-01-01T00:00:00.001Z", example.getCreatedAt() );
		assertEquals( "1970-01-01T00:00:00.002Z", example.getUpdatedAt() );
	}
}
