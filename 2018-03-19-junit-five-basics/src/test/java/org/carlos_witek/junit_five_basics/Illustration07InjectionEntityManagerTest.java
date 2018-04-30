package org.carlos_witek.junit_five_basics;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.carlos_witek.junit_five_basics.internal.JPAExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(JPAExtension.class)
@PersistenceUnit(unitName = "junit_five_basics")
@TestInstance(Lifecycle.PER_CLASS)
public class Illustration07InjectionEntityManagerTest {

	@BeforeEach
	public void beforeEach( final EntityManager entityManager ) {
		entityManager.getTransaction().begin();
		entityManager.persist( new Person( 1L, "Pawel", "Witek" ) );
		entityManager.getTransaction().commit();
	}

	@AfterEach
	public void afterEach( final EntityManager entityManager ) {
		entityManager.getTransaction().begin();
		entityManager.createQuery( "delete from Person" ).executeUpdate();
		entityManager.getTransaction().commit();
	}

	@Test
	void test01( final EntityManager entityManager ) {
		assertNotNull( entityManager.find( Person.class, 1L ) );
	}

	@Test
	void test02( final EntityManager entityManager ) {
		assertNotNull( entityManager.find( Person.class, 1L ) );
	}

}
