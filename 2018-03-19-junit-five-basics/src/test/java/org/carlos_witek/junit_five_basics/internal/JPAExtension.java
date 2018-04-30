/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.carlos_witek.junit_five_basics.internal;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class JPAExtension
		implements BeforeAllCallback, AfterAllCallback, ParameterResolver, AfterEachCallback {

	@Override
	public void beforeAll( final ExtensionContext context ) throws Exception {
		final Optional<String> persistenceUnitName = persistenceUnitName( context );

		if ( persistenceUnitName.isPresent() ) {
			final Store store = context
					.getStore( Namespace.create( JPAExtension.class, persistenceUnitName.get() ) );

			store.getOrComputeIfAbsent( "emf",
					key -> Persistence.createEntityManagerFactory( persistenceUnitName.get() ) );
		}
	}

	@Override
	public void afterAll( final ExtensionContext context ) throws Exception {
		final Optional<String> persistenceUnitName = persistenceUnitName( context );

		if ( persistenceUnitName.isPresent() ) {
			final Store store = context
					.getStore( Namespace.create( JPAExtension.class, persistenceUnitName.get() ) );

			Optional.ofNullable( store.get( "emf", EntityManagerFactory.class ) )
					.ifPresent( EntityManagerFactory::close );
		}
	}

	@Override
	public boolean supportsParameter( final ParameterContext parameterContext,
			final ExtensionContext extensionContext ) {
		return EntityManager.class.equals( parameterContext.getParameter().getType() );
	}

	@Override
	public Object resolveParameter( final ParameterContext parameterContext,
			final ExtensionContext extensionContext ) {
		System.out.println( "resolveParameter"+ extensionContext.toString() );
		final Optional<String> persistenceUnitName = persistenceUnitName( extensionContext );

		if ( persistenceUnitName.isPresent() ) {
			final Store store = extensionContext
					.getStore( Namespace.create( JPAExtension.class, persistenceUnitName.get() ) );
			

			return store.getOrComputeIfAbsent( "em",
					key -> Optional.ofNullable( store.get( "emf", EntityManagerFactory.class ) )
							.map( EntityManagerFactory::createEntityManager )
							.orElse( null ) );
		} else {
			throw new ParameterResolutionException( "PersistenceUnit is missing" );
		}
	}

	@Override
	public void afterEach( final ExtensionContext context ) throws Exception {
		System.out.println( "afterEach"+ context.toString() );
		final Optional<String> persistenceUnitName = persistenceUnitName( context );

		if ( persistenceUnitName.isPresent() ) {
			final Store store = context
					.getStore( Namespace.create( JPAExtension.class, persistenceUnitName.get() ) );

			Optional.ofNullable( store.get( "em", EntityManager.class ) )
					.ifPresent( EntityManager::close );
			store.remove( "em", EntityManager.class );
		}
	}

	Optional<String> persistenceUnitName( final ExtensionContext context ) {
		final Optional<String> persistenceUnitName = context.getTestClass()
				.map( method -> method.getAnnotation( PersistenceUnit.class ) )
				.map( PersistenceUnit::unitName );
		return persistenceUnitName;
	}
}
