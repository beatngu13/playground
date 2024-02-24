package com.github.beatngu13.playground.cdi;

import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;

@Decorator
public class MyServiceDecorator implements MyService {

	@Inject
	@Any
	@Delegate
	private MyServiceImpl myServiceImpl;

	@Override
	public void foo() {
		System.out.println("MyServiceDecorator.foo");
		myServiceImpl.foo();
	}

	@Override
	public void bar() {
		System.out.println("MyServiceDecorator.bar");
		myServiceImpl.bar();
	}

}
