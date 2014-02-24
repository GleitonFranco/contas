package br.com.javerde.contas;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MultiComboPanel extends JScrollPane {
	/**
	 *  Painel rolavel com geraçao variavel de combos de contas
	 *  By: Gleiton Franco
	 *  Date: 2013-09-21
	 */
	private static final long serialVersionUID = 3824854901890992624L;
	List<JComboBox> cbList;
	ContaComposite branco;
	JPanel painel = new JPanel();
	
	public MultiComboPanel(String label, ContaComposite conta, ContaComposite branco) {
		cbList = new ArrayList<JComboBox>();
		painel.setLayout(new FlowLayout(FlowLayout.LEFT));
		painel.add(new JLabel(label));
		add(painel);
		this.branco = branco;
		adicionaJComboBox(conta);
		setViewportView(painel);
	}
	
	public ContaComposite getContaSelec() {
		if ( (ContaComposite)cbList.get(cbList.size()-1).getSelectedItem()==branco) {
			return (ContaComposite)cbList.get(cbList.size()-2).getSelectedItem();
		} else {
			return (ContaComposite)cbList.get(cbList.size()-1).getSelectedItem();
		}
	}
	
	public void adicionaJComboBox(ContaComposite conta) {
		JComboBox JComboBox = new JComboBox(getListaSubContas(conta));
		JComboBox.addItemListener(new SelecHandle());
		cbList.add( JComboBox );
		painel.add(JComboBox);
		painel.validate();
		setViewportView(painel);
		repaint();
		System.out.println(JComboBox.getListeners(ItemListener.class));
	}
	
	public void carrega(ContaComposite conta) {
		for(JComboBox c : cbList) {
			painel.remove(c);
		}
		cbList = new ArrayList<JComboBox>();
		adicionaJComboBox(conta);
	}
	
	private Object[] getListaSubContas(ContaComposite conta) {
		List listaSubContas = conta.getContas(); 
		Object[] retorno = new Object[listaSubContas.size()+1];
		retorno[0] = branco;
		for (int i =1; i <= listaSubContas.size();i++) {
			retorno[i] = listaSubContas.get(i-1);
		}
		return retorno;
	}
	
	
	public class SelecHandle  implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			System.out.print("Adicionado: ");
			if (e.getStateChange()!= ItemEvent.SELECTED) return;
			JComboBox JComboBoxSelec = (JComboBox)e.getSource();
			while (cbList.indexOf(JComboBoxSelec)<cbList.size()-1) {
				painel.remove(cbList.get(cbList.size()-1));
				cbList.remove(cbList.size()-1);
				painel.validate();
				repaint();
			}
			ContaComposite contaSelec = (ContaComposite)JComboBoxSelec.getSelectedItem();
			if (contaSelec.getContas().size()>0) {
				adicionaJComboBox(contaSelec);
			}
			System.out.println(contaSelec);
		}
	}
	
}
