package org.carlos_witek.junit_five_basics;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Illustration01BasicTest {

	@BeforeAll
	private static void beforeAll() {
		System.out.println( "beforeAll" );
	}

	@AfterAll
	private static void afterAll() {
		System.out.println( "afterAll" );
	}

	@BeforeEach
	private void beforeEach() {
		System.out.println( "beforeEach" );
	}

	@AfterEach
	private void afterEach() {
		System.out.println( "afterEach" );
	}

	@Test
	void test01() {
		System.out.println( "test1" );
		Assertions.assertEquals( 2, 1 + 1 );
	}

	@Test
	void test02() {
		System.out.println( "test2" );
	}

	@Test
	@Disabled
	void test03() {
		System.out.println( "test3" );
	}

	@Test
	@DisplayName("test_four")
	void test04() {
		System.out.println( "test4" );
	}

}
