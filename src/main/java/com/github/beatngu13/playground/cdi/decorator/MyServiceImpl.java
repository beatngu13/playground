package com.github.beatngu13.playground.cdi.decorator;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyServiceImpl implements MyService {

	@Override
	public void foo() {
		System.out.println("MyServiceImpl.foo");
	}

	@Override
	public void bar() {
		System.out.println("MyServiceImpl.bar");
	}

}
