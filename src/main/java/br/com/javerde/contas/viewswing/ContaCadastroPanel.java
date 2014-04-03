package br.com.javerde.contas.viewswing;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.javerde.contas.ContaComposite;
import br.com.javerde.contas.DebitoCredito;

public class ContaCadastroPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7933008088450039387L;
	
	JComboBoxDC cbDC = new JComboBoxDC();
	JTextField tfNomeConta = new JTextField(50);
	
	public ContaCadastroPanel() {
		this.add(new JLabel("Nome:"));
	    this.add(tfNomeConta);
	    this.add(new JLabel("Debito/Credito:"));
	    this.add(cbDC);
	}
	
	public void limpar() {
		tfNomeConta.setText("");
		cbDC.setSelectedItem(DebitoCredito.DEBITO);
	}

	public void novo(ContaComposite contaPai) {
		tfNomeConta.setText("");
		cbDC.setSelectedItem(contaPai.getDebitoCredito());
		
	}
	
	public void editar(ContaComposite conta) {
		tfNomeConta.setText(conta.getNome());
		cbDC.setSelectedItem(conta.getDebitoCredito());
	}
	
	public String getNome() {
		return tfNomeConta.getText();
	}
	
	public DebitoCredito getDebitoCredito() {
		return (DebitoCredito)cbDC.getSelectedItem();
	}
	

}
