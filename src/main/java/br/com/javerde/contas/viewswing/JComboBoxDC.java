package br.com.javerde.contas.viewswing;

import javax.swing.JComboBox;

import br.com.javerde.contas.model.DebitoCredito;

public class JComboBoxDC extends JComboBox<DebitoCredito> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8321784846933487828L;

	public JComboBoxDC() {
		super(DebitoCredito.values());
	}
	
	
}
