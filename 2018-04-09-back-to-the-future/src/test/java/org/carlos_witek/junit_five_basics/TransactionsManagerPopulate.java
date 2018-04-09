package org.carlos_witek.junit_five_basics;

import java.math.BigDecimal;
import java.util.Random;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.carlos_witek.back_to_the_future.TransactionsManager;
import org.carlos_witek.junit_five_basics.internal.JPAExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(JPAExtension.class)
public class TransactionsManagerPopulate {

	@Test
	@PersistenceUnit(unitName = "back_to_the_future_in_local")
	void test01( final EntityManagerFactory entityManagerFactory ) {
		final Random random = new Random( 1 );

		final TransactionsManager transactionsManager = new TransactionsManager(
				entityManagerFactory );

		for ( long i = 0; i < 1_000_000; i++ ) {
			final long debitAccountNo = 11 + random.nextInt( 59 );
			final long creditAccountNo = 7 + debitAccountNo;

			final String debitAccount = "GB" + debitAccountNo;
			final String creditAccount = "GB" + creditAccountNo;
			transactionsManager.transfer( debitAccount, creditAccount, BigDecimal.ONE );
		}

	}

}
