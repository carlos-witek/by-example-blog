package org.carlos_witek.junit_five_basics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Illustration02AssertsTest {

	@Test
	void test01() {
		System.out.println( "test5" );

		Assertions.assertEquals( 1, 1 );
		Assertions.assertEquals( 1, 1, "comment" );
		Assertions.assertEquals( 1, 1, () -> "comment" );
		Assertions.assertEquals( 1, 2, () -> "comment" );
	}

	private static class Person {
		public long id;
		public String firstName;
		public String lastName;
	}

	@Test
	void test02() {
		System.out.println( "test6" );

		{
			Person expected = new Person();
			expected.id = 1;
			expected.firstName = "Adam";
			expected.lastName = "Black";

			Person actual = new Person();
			actual.id = 1;
			actual.firstName = "Adam";
			actual.lastName = "Black";

			Assertions.assertAll( "person", () -> Assertions.assertEquals( expected.id, actual.id ),
					() -> Assertions.assertEquals( expected.firstName, actual.firstName ),
					() -> Assertions.assertEquals( expected.lastName, actual.lastName ) );
		}

		{
			Person expected = new Person();
			expected.id = 1;
			expected.firstName = "Adam";
			expected.lastName = "Black";

			Person actual = new Person();
			actual.id = 1;
			actual.firstName = "Adam";
			actual.lastName = "Smith";

			Assertions.assertAll( "person", () -> Assertions.assertEquals( expected.id, actual.id ),
					() -> Assertions.assertEquals( expected.firstName, actual.firstName ),
					() -> Assertions.assertEquals( expected.lastName, actual.lastName ) );
		}
	}

	private static class Pair {
		public Person person1;
		public Person person2;
	}

	@Test
	void test03() {
		System.out.println( "test03" );

		{
			Pair actual = new Pair();
			actual.person1 = new Person();
			actual.person1.id = 1;
			actual.person1.firstName = "Adam";
			actual.person1.lastName = "Black";
			actual.person2 = new Person();
			actual.person2.id = 1;
			actual.person2.firstName = "Adam";
			actual.person2.lastName = "Black";

			Assertions.assertAll( "pair", () -> {
				Assertions.assertNotNull( actual.person1 );

				// Executed only if the previous assertion is valid.
				Assertions.assertAll( "person",
						() -> Assertions.assertEquals( "Adam", actual.person1.firstName ),
						() -> Assertions.assertEquals( "Black", actual.person1.lastName ) );
			}, () -> {
				Assertions.assertNotNull( actual.person2 );

				// Executed only if the previous assertion is valid.
				Assertions.assertAll( "person",
						() -> Assertions.assertEquals( "Adam", actual.person2.firstName ),
						() -> Assertions.assertEquals( "Black", actual.person2.lastName ) );
			} );
		}

		{
			Pair actual = new Pair();
			actual.person1 = new Person();
			actual.person1.id = 1;
			actual.person1.firstName = "Adam";
			actual.person1.lastName = "Black";

			Assertions.assertAll( "pair", () -> {
				Assertions.assertNotNull( actual.person1 );

				// Executed only if the previous assertion is valid.
				Assertions.assertAll( "person",
						() -> Assertions.assertEquals( "Adam", actual.person1.firstName ),
						() -> Assertions.assertEquals( "Black", actual.person1.lastName ) );
			}, () -> {
				Assertions.assertNotNull( actual.person2 );

				// Executed only if the previous assertion is valid.
				Assertions.assertAll( "person",
						() -> Assertions.assertEquals( "Adam", actual.person2.firstName ),
						() -> Assertions.assertEquals( "Black", actual.person2.lastName ) );
			} );
		}
	}

}
