package com.group5.card;

import com.group5.component.Container;


public class CardInfo extends Container {

	public CardInfo(int width, int height, Container...containers) {
		super(width, height);
		for (Container i : containers) this.add(i);
	}
}
