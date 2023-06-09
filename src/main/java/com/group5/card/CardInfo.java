package com.group5.card;

import com.group5.component.Container;


public class CardInfo extends Container {

	/**
	 * Creates a Container that other containers
	 * @param width of container
	 * @param height of container
	 * @param containers to be held inside this container
	 */
	public CardInfo(int width, int height, Container...containers) {
		super(width, height);
		for (Container i : containers) this.add(i);
	}
}
