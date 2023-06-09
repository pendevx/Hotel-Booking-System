package com.group5.component;

import javax.swing.JComponent;

public interface KeyPressListener {

	/**
	 * Adds listener to array of JComponents.
	 * @param components that will have listener added.
	 */
	abstract void addEnterKeyListener(JComponent... components);
}
