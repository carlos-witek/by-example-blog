package org.carlos_witek.it_is_always_utc_time.illustration4.dao.example3;

import java.time.Clock;
import java.time.Instant;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import org.carlos_witek.it_is_always_utc_time.illustration4.dao.ExampleDao;
import org.carlos_witek.it_is_always_utc_time.illustration4.dao.ExampleEntity;
import org.carlos_witek.it_is_always_utc_time.illustration4.dao.GenericDaoGuice;

import com.google.common.annotations.VisibleForTesting;

@Singleton
public class Example3Dao extends GenericDaoGuice implements ExampleDao {

	private Clock clock;

	@Inject
	public Example3Dao( final EntityManager entityManager ) {
		super( entityManager );
	}

	@VisibleForTesting
	Example3Dao( final EntityManager entityManager, final Clock clock ) {
		super( entityManager );
		this.clock = clock;
	}

	@Override
	public ExampleEntity findExample( final Long id ) {
		return Optional.ofNullable( find( Example3Entity.class, id ) )
				.map( this::toExampleEntity )
				.orElse( null );
	}

	public Example3Entity findExample2( final Long id ) {
		return find( Example3Entity.class, id );
	}

	@Override
	public Long createExample( final String value ) {
		final Instant now = Instant.now( clock );

		final Example3Entity entity = new Example3Entity();
		entity.setValue( value );
		entity.setCreatedAt( now );
		entity.setUpdatedAt( now );

		createEntities( entity );

		return entity.getId();
	}

	@Override
	public Long updateExample( final Long id, final String value ) {
		final Instant now = Instant.now( clock );

		final Example3Entity entity = find( Example3Entity.class, id );
		entity.setValue( value );
		entity.setUpdatedAt( now );

		return null;
	}

	private ExampleEntity toExampleEntity( final Example3Entity entity1 ) {
		final ExampleEntity entity = new ExampleEntity();
		entity.setId( entity1.getId() );
		entity.setValue( entity1.getValue() );
		entity.setCreatedAt( entity1.getCreatedAt().toString() );
		entity.setUpdatedAt( entity1.getUpdatedAt().toString() );
		return entity;
	}

}
