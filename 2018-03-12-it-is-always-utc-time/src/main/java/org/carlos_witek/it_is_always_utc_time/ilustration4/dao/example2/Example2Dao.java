package org.carlos_witek.it_is_always_utc_time.ilustration4.dao.example2;

import java.util.Date;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import org.carlos_witek.it_is_always_utc_time.ilustration4.dao.ExampleDao;
import org.carlos_witek.it_is_always_utc_time.ilustration4.dao.ExampleEntity;
import org.carlos_witek.it_is_always_utc_time.ilustration4.dao.GenericDaoGuice;
import org.carlos_witek.it_is_always_utc_time.ilustration4.dao.example1.Example1Entity;

@Singleton
public class Example2Dao extends GenericDaoGuice implements ExampleDao {

	@Inject
	public Example2Dao( final EntityManager entityManager ) {
		super( entityManager );
	}

	@Override
	public ExampleEntity findExample( final Long id ) {
		return Optional.ofNullable( find( Example2Entity.class, id ) )
				.map( this::toExampleEntity )
				.orElse( null );
	}

	public Example2Entity findExample2( final Long id ) {
		return find( Example2Entity.class, id );
	}

	@Override
	public Long createExample( final String value ) {
		final Date now = new Date();

		final Example1Entity entity = new Example1Entity();
		entity.setValue( value );
		entity.setCreatedAt( now );
		entity.setUpdatedAt( now );

		createEntities( entity );

		return entity.getId();
	}

	@Override
	public Long updateExample( final Long id, final String value ) {
		final Date now = new Date();

		final Example1Entity entity = find( Example1Entity.class, id );
		entity.setValue( value );
		entity.setUpdatedAt( now );

		return null;
	}

	private ExampleEntity toExampleEntity( final Example2Entity entity1 ) {
		final ExampleEntity entity = new ExampleEntity();
		entity.setId( entity1.getId() );
		entity.setValue( entity1.getValue() );
		entity.setCreatedAt( entity1.getCreatedAt().toString() );
		entity.setUpdatedAt( entity1.getUpdatedAt().toString() );
		return entity;
	}

}
