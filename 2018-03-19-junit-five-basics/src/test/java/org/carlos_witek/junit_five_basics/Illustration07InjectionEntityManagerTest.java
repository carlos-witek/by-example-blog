package org.carlos_witek.junit_five_basics;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.carlos_witek.junit_five_basics.internal.JPAExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(JPAExtension.class)
public class Illustration07InjectionEntityManagerTest {

	@BeforeEach
	@PersistenceUnit(unitName = "junit_five_basics")
	public void beforeEach( final EntityManager entityManager ) {
		System.out.println( "beforeEach:" + entityManager );

		entityManager.getTransaction().begin();
		entityManager.persist( new Person( 1L, "Pawel", "Witek" ) );
		entityManager.getTransaction().commit();
	}

	@Test
	@PersistenceUnit(unitName = "junit_five_basics")
	void test01( final EntityManager entityManager ) {
		System.out.println( "test01:" + entityManager.find( Person.class, 1L ) );
	}

}
