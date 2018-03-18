package org.carlos_witek.it_is_always_utc_time.illustration4.dao;

import java.util.List;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.google.inject.persist.Transactional;

@Singleton
public class GenericDaoGuice {

	protected final EntityManager entityManager;

	@Inject
	public GenericDaoGuice( final EntityManager entityManager ) {
		this.entityManager = entityManager;
	}

	public final <T> T find( final Class<T> entityClass, final Object entityId ) {
		return entityManager.find( entityClass, entityId );
	}

	public final <T> List<T> findAll( final Class<T> entityClass ) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery( entityClass );
		criteriaQuery.from( entityClass );
		return entityManager.createQuery( criteriaQuery ).getResultList();
	}

	@Transactional
	public void createEntities( final Object... entities ) {
		createEntities( Stream.of( entities ) );
	}

	@Transactional
	public void createEntities( final Stream<Object> entities ) {
		entities.forEach( entity -> entityManager.persist( entity ) );
	}

	@Transactional
	public void updateEntities( final Object... entities ) {
		updateEntities( Stream.of( entities ) );
	}

	@Transactional
	public void updateEntities( final Stream<Object> entities ) {
		entities.forEach( entity -> entityManager.merge( entity ) );
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public void deleteEntities( final Class<?>... entityClasses ) {
		for ( Class<?> entityClass : entityClasses ) {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery( entityClass );
			criteriaQuery.from( entityClass );
			TypedQuery<?> query = entityManager.createQuery( criteriaQuery );

			deleteEntities( (Stream<Object>) query.getResultList().stream() );
		}
	}

	@Transactional
	public void deleteEntities( final Object... entities ) {
		deleteEntities( Stream.of( entities ) );
	}

	@Transactional
	public void deleteEntities( final Stream<Object> entities ) {
		entities.forEach( entity -> entityManager.remove( entity ) );
	}
}
