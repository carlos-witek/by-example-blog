package org.carlos_witek.junit_five_basics;

import org.carlos_witek.junit_five_basics.internal.Pair;
import org.carlos_witek.junit_five_basics.internal.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Illustration03AssertsTest {

	@Test
	void test01() {
		System.out.println( "test01" );

		Assertions.assertEquals( 1, 1 );
		Assertions.assertEquals( 1, 1, "comment" );
		Assertions.assertEquals( 1, 1, () -> "comment" );
		Assertions.assertEquals( 1, 2, () -> "comment" );
	}

	@Test
	void test02() {
		System.out.println( "test02" );

		{
			Person expected = new Person( 1, "Adam", "Black" );
			Person actual = new Person( 1, "Adam", "Black" );

			Assertions.assertAll( "person",
					() -> Assertions.assertEquals( expected.getId(), actual.getId() ),
					() -> Assertions.assertEquals( expected.getFirstName(), actual.getFirstName() ),
					() -> Assertions.assertEquals( expected.getLastName(), actual.getLastName() ) );
		}

		{
			Person expected = new Person( 1, "Adam", "Black" );
			Person actual = new Person( 1, "Adam", "Smith" );

			Assertions.assertAll( "person",
					() -> Assertions.assertEquals( expected.getId(), actual.getId() ),
					() -> Assertions.assertEquals( expected.getFirstName(), actual.getFirstName() ),
					() -> Assertions.assertEquals( expected.getLastName(), actual.getLastName() ) );
		}
	}

	@Test
	void test03() {
		System.out.println( "test03" );

		{
			Pair actual = new Pair( new Person( 1, "Adam", "Black" ),
					new Person( 1, "Adam", "Black" ) );

			Assertions.assertAll( "pair", () -> {
				Assertions.assertNotNull( actual.getPerson1() );

				// Executed only if the previous assertion is valid.
				Assertions.assertAll( "person",
						() -> Assertions.assertEquals( "Adam", actual.getPerson1().getFirstName() ),
						() -> Assertions.assertEquals( "Black",
								actual.getPerson1().getLastName() ) );
			}, () -> {
				Assertions.assertNotNull( actual.getPerson2() );

				// Executed only if the previous assertion is valid.
				Assertions.assertAll( "person",
						() -> Assertions.assertEquals( "Adam", actual.getPerson2().getFirstName() ),
						() -> Assertions.assertEquals( "Black",
								actual.getPerson2().getLastName() ) );
			} );
		}

		{
			Pair actual = new Pair( new Person( 1, "Adam", "Black" ), null );

			Assertions.assertAll( "pair", () -> {
				Assertions.assertNotNull( actual.getPerson1() );

				// Executed only if the previous assertion is valid.
				Assertions.assertAll( "person",
						() -> Assertions.assertEquals( "Adam", actual.getPerson1().getFirstName() ),
						() -> Assertions.assertEquals( "Black",
								actual.getPerson1().getLastName() ) );
			}, () -> {
				Assertions.assertNotNull( actual.getPerson2() );

				// Executed only if the previous assertion is valid.
				Assertions.assertAll( "person",
						() -> Assertions.assertEquals( "Adam", actual.getPerson2().getFirstName() ),
						() -> Assertions.assertEquals( "Black",
								actual.getPerson2().getLastName() ) );
			} );
		}
	}

}
