package org.carlos_witek.error_handling_in_executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Examples {

	public static class RunnableWithError implements Runnable {

		@Override
		public void run() {
			throw new RuntimeException( "" );

		}

	}

	public static void main( String[] args ) {

		final ExecutorService executorService = Executors.newSingleThreadExecutor();

		executorService.submit( new Runnable() {

			private AtomicLong atomicLong = new AtomicLong( 0 );

			@Override
			public void run() {
				try {
					final long value = atomicLong.incrementAndGet();
					System.out.println( "execution=" + value );
					throw new Exception( "exception=" + value );
				} catch ( Exception e ) {
					try {
						Thread.sleep( TimeUnit.SECONDS.toMillis( 1 ) );
					} catch ( InterruptedException e1 ) {
						e1.printStackTrace();
					}
					executorService.execute( this );
				} finally {
				}
			}
		} );

	}

}
