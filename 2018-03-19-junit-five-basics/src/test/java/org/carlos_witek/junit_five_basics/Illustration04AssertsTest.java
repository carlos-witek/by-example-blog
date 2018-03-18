package org.carlos_witek.junit_five_basics;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Illustration04AssertsTest {

	public static class ExampleManager {
		public String toLowerCase( final String string ) {
			if ( string == null )
				throw new NullPointerException( "string is empty" );
			return string.toLowerCase();
		}
	}

	@Test
	void test01() {
		System.out.println( "test01" );

		final ExampleManager manager = new ExampleManager();

		Throwable exception = Assertions.assertThrows( NullPointerException.class, () -> {
			manager.toLowerCase( null );
		} );
		Assertions.assertEquals( "string is empty", exception.getMessage() );
	}

	@Test
	void test02() {
		System.out.println( "test02" );

		// The following assertion succeeds.
		final String string = Assertions.assertTimeout( Duration.ofMillis( 300 ), () -> {
			Thread.sleep( 200 );
			return "assertTimeout";
		} );

		Assertions.assertEquals( "assertTimeout", string );
	}

	@Test
	void test03() {
		System.out.println( "test03" );

		// The following assertion succeeds.
		final String string = Assertions.assertTimeout( Duration.ofMillis( 100 ), () -> {
			Thread.sleep( 200 );
			return "assertTimeout";
		} );

		Assertions.assertEquals( "assertTimeout", string );
	}

	@Test
	void test04() {
		System.out.println( "test04" );

		// The following assertion succeeds.
		final String string = Assertions.assertTimeoutPreemptively( Duration.ofMillis( 300 ),
				() -> {
					Thread.sleep( 200 );
					return "assertTimeoutPreemptively";
				} );

		Assertions.assertEquals( "assertTimeoutPreemptively", string );
	}

	@Test
	void test05() {
		System.out.println( "test05" );

		// The following assertion succeeds.
		final String string = Assertions.assertTimeoutPreemptively( Duration.ofMillis( 100 ),
				() -> {
					Thread.sleep( 200 );
					return "assertTimeoutPreemptively";
				} );

		Assertions.assertEquals( "assertTimeoutPreemptively", string );
	}

}
