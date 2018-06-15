package org.carlos_witek.back_to_the_future_iii;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

	public static void main( String[] args ) {
		printCurrentThreadName( "poin1" );

		ExecutorService executorService = Executors.newCachedThreadPool();

		final CompletableFuture<Void> future1a = CompletableFuture.runAsync( task( "poin2", 2 ),
				executorService );

		final CompletableFuture<Void> future1b = CompletableFuture.runAsync( task( "poin3", 2 ),
				executorService );
		future1b.join();


		final CompletableFuture<Void> future2 = future1a.thenRun( task( "poin5", 3 ) );
		future2.join();

	}

	private static Runnable task( final String string, final long millis ) {
		return () -> {
			printCurrentThreadName( string );
			if ( millis > 0 ) {
				try {
					Thread.sleep( millis );
				} catch ( InterruptedException e ) {
					e.printStackTrace();
				}
			}
		};
	}

	private static void printCurrentThreadName( final String string ) {
		System.out.println( string + "  :  " + Thread.currentThread().getName() );
	}

}
