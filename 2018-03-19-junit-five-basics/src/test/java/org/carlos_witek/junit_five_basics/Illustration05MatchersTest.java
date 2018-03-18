package org.carlos_witek.junit_five_basics;

import org.carlos_witek.junit_five_basics.internal.Person;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class Illustration05MatchersTest {

	@Test
	void test01() {
		System.out.println( "test01" );

		{
			Person actual = new Person( 1, "Adam", "Black" );

			MatcherAssert.assertThat( actual,
					Matchers.allOf( Matchers.hasProperty( "firstName", Matchers.is( "Adam" ) ),
							Matchers.hasProperty( "lastName", Matchers.is( "Black" ) ) ) );
		}
	}

}
