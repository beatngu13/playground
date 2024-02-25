package com.github.beatngu13.playground.cdi.interceptor;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.weld.junit5.auto.ActivateScopes;
import org.jboss.weld.junit5.auto.AddEnabledInterceptors;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;

@EnableAutoWeld
@AddPackages(com.github.beatngu13.playground.cdi.interceptor.MyService.class)
@AddEnabledInterceptors(MyServiceInterceptor.class)
@ActivateScopes(ApplicationScoped.class)
class MyServiceTest {

	@Inject
	MyService myService;

	@Test
	void test() {
		myService.foo("phew");
		myService.bar(42);
	}

}
