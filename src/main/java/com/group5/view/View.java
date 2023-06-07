package com.group5.view;

import com.group5.component.Base;
import com.group5.mvc.Controller;
import javax.swing.JPanel;

public abstract class View extends JPanel {

	private Base base = new Base();
	private Controller controller;

	public View(Controller controller) {
		this.controller = controller;
	}

	public Controller getController() {
		return this.controller;
	}

	public void addToBase(JPanel p) {
		this.base.add(p);
	}

	public void addToBaseWithGap(JPanel...panel) {
		for (JPanel p : panel) this.base.addWithGap(p);
	}

	public Base getBasePanel() {
		return this.base;
	}

	public void resetBasePanel() {
		if (getBasePanel() != null) getBasePanel().removeAll();
	}
}
