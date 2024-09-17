package com.github.beatngu13.playground.cdi.interceptor;

import jakarta.enterprise.context.Dependent;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@Dependent
public class MyServiceInterceptor {

	@AroundInvoke
	public Object aroundInvoke(InvocationContext context) throws Exception {
		System.out.printf("MyServiceInterceptor.aroundInvoke(%s)%n", context.getParameters()[0]);
		return context.proceed();
	}

}
