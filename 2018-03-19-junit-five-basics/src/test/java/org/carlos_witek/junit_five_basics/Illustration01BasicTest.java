package org.carlos_witek.junit_five_basics;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

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
		System.out.println( "test01" );
	}

	@Test
	@Disabled
	void test02() {
		System.out.println( "test02" );
	}

	@Test
	void test03() {
		System.out.println( "test03" );
	}

	@RepeatedTest(10)
	void test04() {
		System.out.println( "test04" );
	}

	@ParameterizedTest
	@ValueSource(strings = { "string-a", "string-b", "string-c" })
	void test05( final String string ) {
		System.out.println( "test05:" + string );
	}

	@ParameterizedTest
	@MethodSource
	void test06( final String string ) {
		System.out.println( "test06:" + string );
	}

	@ParameterizedTest
	@MethodSource("test06_2")
	void test06b( final String string ) {
		System.out.println( "test06:" + string );
	}

	static Stream<String> test06() {
		return Stream.of( "string-a", "string-b", "string-c" );
	}

	static Stream<String> test06_2() {
		return Stream.of( "string-d", "string-e", "string-f" );
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/org/carlos_witek/junit_five_basics/test07.csv", numLinesToSkip = 1)
	void test07a( final String string ) {
		System.out.println( "test07a:" + string );
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/org/carlos_witek/junit_five_basics/test07.csv", numLinesToSkip = 1)
	void test07b( final String string, final int value ) {
		System.out.println( "test07b:" + string + " " + value );
	}

	/**
	 * Only non-static nested classes (i.e. inner classes) can serve as @Nested
	 * test classes. Nesting can be arbitrarily deep, and those inner classes
	 * are considered to be full members of the test class family
	 */
	@Nested
	public class Nested1 {

		@BeforeEach
		private void beforeEach() {
			System.out.println( "nested1.beforeEach" );
		}

		@AfterEach
		private void afterEach() {
			System.out.println( "nested1.afterEach" );
		}

		@Test
		void test01() {
			System.out.println( "nested1.test1" );
		}

		@Test
		@Disabled
		void test02() {
			System.out.println( "nested1.test2" );
		}

		@Test
		void test03() {
			System.out.println( "nested1.test3" );
		}

	}

}
