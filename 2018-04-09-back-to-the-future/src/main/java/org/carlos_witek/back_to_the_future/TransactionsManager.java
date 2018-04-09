package org.carlos_witek.back_to_the_future;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class TransactionsManager {

	private final EntityManagerFactory entityManagerFactory;

	public TransactionsManager( final EntityManagerFactory entityManagerFactory ) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void transfer( final String debitAccount, final String creditAccount,
			final BigDecimal amount ) {
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist( new Transaction( 0, debitAccount, amount ) );
			entityManager.persist( new Transaction( 0, creditAccount, amount ) );
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

	public Page<Transaction> getLedgerEntries( final String account,
			PageDescription pageDescription ) {
		if ( pageDescription == null )
			pageDescription = new PageDescription();

		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			final List<Transaction> elements = entityManager
					.createQuery( "from Transaction t where t.account = :account",
							Transaction.class )
					.setParameter( "account", account )
					.setMaxResults( pageDescription.getLimit() )
					.setFirstResult( (int) pageDescription.getStartIndex() )
					.getResultList();

			final Long totalCount = entityManager
					.createQuery( "select count(*) from Transaction t where t.account = :account",
							Long.class )
					.setParameter( "account", account )
					.getSingleResult();

			return new Page<>( elements, totalCount, pageDescription );

		} finally {
			entityManager.close();
		}
	}

}
