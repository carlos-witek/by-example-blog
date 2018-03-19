package org.carlos_witek.junit_five_basics;

import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Illustration02AssertsTest {

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
			Team actual = new Team( new Person( 1, "Adam", "Black" ),
					Collections.singleton( new Person( 1, "Adam", "Black" ) ) );

			Assertions.assertAll( "team", () -> {
				Assertions.assertNotNull( actual.getLead() );

				// Executed only if the previous assertion is valid.
				Assertions.assertAll( "lead",
						() -> Assertions.assertEquals( "Adam", actual.getLead().getFirstName() ),
						() -> Assertions.assertEquals( "Black", actual.getLead().getLastName() ) );
			}, () -> {
				Assertions.assertNotNull( actual.getMembers() );
				Assertions.assertFalse( actual.getMembers().isEmpty() );

				// Executed only if the previous assertion is valid.
				final Person next = actual.getMembers().iterator().next();
				Assertions.assertAll( "member", () -> {
					Assertions.assertEquals( "Adam", next.getFirstName() );
				}, () -> Assertions.assertEquals( "Black", next.getLastName() ) );
			} );
		}

		{
			Team actual = new Team( new Person( 1, "Adam", "Black" ), Collections.emptySet() );

			Assertions.assertAll( "team", () -> {
				Assertions.assertNotNull( actual.getLead() );

				// Executed only if the previous assertion is valid.
				Assertions.assertAll( "lead",
						() -> Assertions.assertEquals( "Adam", actual.getLead().getFirstName() ),
						() -> Assertions.assertEquals( "Black", actual.getLead().getLastName() ) );
			}, () -> {
				Assertions.assertNotNull( actual.getMembers() );

				// Executed only if the previous assertion is valid.
				final Person next = actual.getMembers().iterator().next();
				Assertions.assertAll( "member", () -> {
					Assertions.assertEquals( "Adam", next.getFirstName() );
				}, () -> Assertions.assertEquals( "Black", next.getLastName() ) );
			} );
		}
	}

}
