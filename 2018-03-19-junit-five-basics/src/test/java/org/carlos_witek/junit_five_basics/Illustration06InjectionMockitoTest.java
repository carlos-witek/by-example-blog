package org.carlos_witek.junit_five_basics;

import org.carlos_witek.junit_five_basics.internal.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
public class Illustration06InjectionMockitoTest {

	@BeforeEach
	public void beforeEach( @Mock PersonManager manager ) {
		Mockito.when( manager.findPerson( 1L ) ).thenReturn( new Person( 1, "Adam", "Black" ) );
		Mockito.when( manager.findPerson( 2L ) ).thenReturn( new Person( 2, "Bob", "Cold" ) );
	}

	@Test
	void test01( @Mock PersonManager manager ) {
		System.out.println( "test1:" + manager.findPerson( 1L ) );
		System.out.println( "test1:" + manager.findPerson( 2L ) );
		System.out.println( "test1:" + manager.findPerson( 3L ) );
	}

}
