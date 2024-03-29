package com.github.beatngu13.playground.cdi.decorator;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.weld.junit5.auto.ActivateScopes;
import org.jboss.weld.junit5.auto.AddEnabledDecorators;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;

@EnableAutoWeld
@AddPackages(MyService.class)
@AddEnabledDecorators(MyServiceDecorator.class)
@ActivateScopes(ApplicationScoped.class)
class MyServiceTest {

	@Inject
	MyService myService;

	@Test
	void test() {
		myService.foo();
		myService.bar();
	}

}
