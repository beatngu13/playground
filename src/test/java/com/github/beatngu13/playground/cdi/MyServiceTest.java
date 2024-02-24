package com.github.beatngu13.playground.cdi;

import jakarta.inject.Inject;
import org.jboss.weld.junit5.auto.AddEnabledDecorators;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
@EnableAutoWeld
@AddPackages(MyService.class)
@AddEnabledDecorators(MyServiceDecorator.class)
class MyServiceTest {

	@Inject
	MyService myService;

	@Test
	void test() {
		myService.foo();
		myService.bar();
	}

}
