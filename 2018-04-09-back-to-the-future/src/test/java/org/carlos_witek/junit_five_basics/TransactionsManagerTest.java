package org.carlos_witek.junit_five_basics;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.carlos_witek.back_to_the_future.Transaction;
import org.carlos_witek.junit_five_basics.internal.JPAExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(JPAExtension.class)
public class TransactionsManagerTest {

	@BeforeEach
	@PersistenceUnit(unitName = "back_to_the_future_in_mem")
	public void beforeEach( final EntityManager entityManager ) {
		System.out.println( "beforeEach:" + entityManager );

		entityManager.getTransaction().begin();
		entityManager.persist( new Transaction( 1L, "Pawel", BigDecimal.ONE ) );
		entityManager.getTransaction().commit();
	}

	@Test
	@PersistenceUnit(unitName = "back_to_the_future_in_mem")
	void test01( final EntityManager entityManager ) {
		System.out.println( "test01:" + entityManager.find( Transaction.class, 1L ) );
	}

}
