package com.github.beatngu13.playground.cdi.interceptor;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.interceptor.Interceptors;

@Interceptors(MyServiceInterceptor.class)
@ApplicationScoped
public class MyServiceImpl implements MyService {

	@Override
	public void foo(String s) {
		System.out.println(STR. "MyServiceImpl.foo(\{ s })" );
	}

	@Override
	public void bar(Integer i) {
		System.out.println(STR. "MyServiceImpl.bar(\{ i })" );
	}

}
