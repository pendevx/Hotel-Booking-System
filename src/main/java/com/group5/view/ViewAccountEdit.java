package com.group5.view;

import com.group5.card.CardAccountEdit;
import com.group5.component.GetCardComponent;
import com.group5.controller.Controller;
import javax.swing.JComponent;

public class ViewAccountEdit extends View implements GetCardComponent {
	private CardAccountEdit cardAccountEdit;

	/** 
	 * View that holds cards(panel) required for displaying the account edit
	 * @param controller 
	 */
	public ViewAccountEdit(Controller controller) {
		super(controller);
		init();
	}

	private void init() {
		super.resetBasePanel(); // reset panel
		this.cardAccountEdit = new CardAccountEdit(); // instantiate
		cardAccountEdit.cancelButton.addActionListener(getController()); // add controller
		cardAccountEdit.saveButton.addActionListener(getController()); // add controller
		addToBase(cardAccountEdit); // add to the base in abstract view
	}

	@Override
	// returns array of JComponents
	public JComponent[] getCardComponents() {
		JComponent[] components = new JComponent[2];
		components[0] = cardAccountEdit.emailNew;
		components[1] = cardAccountEdit.phoneNew;
		return components;
	}
	
	public CardAccountEdit getCard() { return this.cardAccountEdit; }
}
