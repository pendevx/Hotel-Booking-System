package com.group5.view;

import com.group5.component.Base;
import com.group5.controller.Controller;
import javax.swing.JPanel;

public abstract class View extends JPanel {

	private Base base = new Base();
	private Controller controller;

	/**
	 * Abstract class used to create view that holds panels inside it
	 * @param controller to control
	 */
	public View(Controller controller) {
		this.controller = controller;
	}

	// returns controller
	public Controller getController() {
		return this.controller;
	}

	// adds to the Base (Panel)
	public void addToBase(JPanel p) {
		this.base.add(p);
	}

	// adds to the Base with gaps (Panel)
	public void addToBaseWithGap(JPanel...panel) {
		for (JPanel p : panel) this.base.addWithGap(p);
	}

	// returns base
	public Base getBasePanel() {
		return this.base;
	}

	// removes all from base
	public void resetBasePanel() {
		if (getBasePanel() != null) getBasePanel().removeAll();
	}
}
