/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.carlos_witek.back_to_the_future_ii.internal;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.mockito.Mock;

/**
 * {@code MockitoExtension} showcases the {@link TestInstancePostProcessor} and
 * {@link ParameterResolver} extension APIs of JUnit 5 by providing dependency
 * injection support at the field level and at the method parameter level via
 * Mockito 2.x's {@link Mock @Mock} annotation.
 *
 * @since 5.0
 */
public class JPAExtension implements ParameterResolver, AfterTestExecutionCallback {

	@Override
	public boolean supportsParameter( ParameterContext parameterContext,
			ExtensionContext extensionContext ) {
		final Class<?> type = parameterContext.getParameter().getType();
		return type.equals( EntityManagerFactory.class ) || type.equals( EntityManager.class );
	}

	@Override
	public Object resolveParameter( ParameterContext parameterContext,
			ExtensionContext extensionContext ) {
		final Optional<String> persistenceUnitName = persistenceUnitName( extensionContext );
		if ( persistenceUnitName.isPresent() ) {

			final Store store = extensionContext
					.getStore( Namespace.create( JPAExtension.class, persistenceUnitName.get() ) );

			final EntityManagerFactory factory = (EntityManagerFactory) store.getOrComputeIfAbsent(
					"emf",
					key -> Persistence.createEntityManagerFactory( persistenceUnitName.get() ) );

			final Class<?> type = parameterContext.getParameter().getType();
			if ( type.equals( EntityManagerFactory.class ) ) {
				return factory;
			}

			if ( type.equals( EntityManager.class ) ) {
				final EntityManager entityManager = factory.createEntityManager();
				store.put( "em", entityManager );
				return entityManager;
			}

			throw new IllegalStateException();
		} else {
			throw new IllegalStateException( "PersistenceUnit is missing" );
		}

	}

	@Override
	public void afterTestExecution( final ExtensionContext context ) throws Exception {
		final Optional<String> persistenceUnitName = persistenceUnitName( context );
		if ( persistenceUnitName.isPresent() ) {
			final Store store = context
					.getStore( Namespace.create( JPAExtension.class, persistenceUnitName.get() ) );

			( (EntityManager) store.get( "em" ) ).close();
		}
	}

	private Optional<String> persistenceUnitName( final ExtensionContext context ) {
		final Optional<PersistenceUnit> persistenceUnit = context.getTestMethod()
				.map( method -> method.getAnnotation( PersistenceUnit.class ) );
		return persistenceUnit.map( PersistenceUnit::unitName );
	}

}
