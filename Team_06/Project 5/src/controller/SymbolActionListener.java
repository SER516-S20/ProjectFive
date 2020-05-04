package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Symbol;

/**
 *
 * @author Dhananjay
 * @since 04-25-2020
 * @Description: This will set the selected shape from the toolbar.
 */
public class SymbolActionListener {

	public SymbolActionListener(Symbol symbol) {
		
		symbol.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Collector.getInstance().setSymbolSelected(symbol.getText());
			}
		});
	}

}
