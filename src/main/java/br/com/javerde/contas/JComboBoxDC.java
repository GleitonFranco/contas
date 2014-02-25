package br.com.javerde.contas;

import javax.swing.JComboBox;

public class JComboBoxDC extends JComboBox<DebitoCredito> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8321784846933487828L;

	public JComboBoxDC() {
		super(DebitoCredito.values());
	}
	
	
}
