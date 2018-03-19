package org.carlos_witek.junit_five_basics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class Illustration05InjectionTestInfoTest {

	@BeforeEach
	public void beforeEach( final TestInfo testInfo ) {
		System.out.println( "beforeEach:" + testInfo.getDisplayName() );
		System.out.println( "beforeEach:" + testInfo.getTestMethod() );
		System.out.println( "beforeEach:" + testInfo.getTags() );
	}

	@Test
	@DisplayName("test1name")
	@Tag("value")
	void test1( final TestInfo testInfo ) {
		System.out.println( "test1:" + testInfo.getDisplayName() );
		System.out.println( "test1:" + testInfo.getTestMethod() );
		System.out.println( "test1:" + testInfo.getTags() );
	}

	@Test
	void test2() {
	}

}
