package com.github.beatngu13.playground.hibernate;

import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPostInsertEventListenerImpl;
import org.hibernate.event.spi.PostInsertEvent;

public class CustomEnversPostInsertEventListenerImpl extends EnversPostInsertEventListenerImpl {

	public CustomEnversPostInsertEventListenerImpl(EnversService enversService) {
		super(enversService);
	}

	@Override
	public void onPostInsert(PostInsertEvent event) {
		if (event.getEntity() instanceof Book book) {
			System.out.printf("Not auditing book %s on (post) insert.%n", book.getId());
			return;
		}
		super.onPostInsert(event);
	}

}
