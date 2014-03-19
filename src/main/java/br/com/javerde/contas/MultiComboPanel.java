package br.com.javerde.contas;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MultiComboPanel extends JScrollPane {
	/**
	 *  Painel rolavel com gera��ao variavel de combos de contas
	 *  By: Gleiton Franco
	 *  Date: 2013-09-21
	 */
	private static final long serialVersionUID = 3824854901890992624L;
	List<JComboBox<ContaComposite>> cbList;
	List<ContaComposite> contasList;
	ContaComposite branco;
	JPanel painel = new JPanel();
	
	public MultiComboPanel(String label, ContaComposite conta, ContaComposite branco) {
		cbList = new ArrayList<JComboBox<ContaComposite>>();
		contasList = new ArrayList<ContaComposite>();
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
		contasList.add(conta);
		JComboBox<ContaComposite> cb = new JComboBox<ContaComposite>();
		cb.setModel(new DefaultComboBoxModel<ContaComposite>(getListaSubContas(conta)));
		cb.addItemListener(new SelecHandle());
		cbList.add(cb);
		painel.add(cb);
		painel.validate();
		setViewportView(painel);
		repaint();
		System.out.println(cb.getListeners(ItemListener.class));
	}
	
	public void carrega(ContaComposite conta) {
		for(JComboBox<ContaComposite> c : cbList) {
			painel.remove(c);
		}
		cbList = new ArrayList<JComboBox<ContaComposite>>();
		adicionaJComboBox(conta);
	}

	public void atualiza(ContaComposite raiz) {
		for (int i=0; i< cbList.size();i++) {
			JComboBox<ContaComposite> j = cbList.get(i); 
			j.removeAllItems();
			for(ContaComposite c : getListaSubContas(raiz)) {
				j.addItem(c);
			}
		}
		painel.revalidate();
	}
	
	private ContaComposite[] getListaSubContas(ContaComposite conta) {
		List<ContaComposite> listaSubContas = conta.getContas(); 
		ContaComposite[] retorno = new ContaComposite[listaSubContas.size()+1];
		retorno[0] = branco;
		for (int i =1; i <= listaSubContas.size();i++) {
			retorno[i] = listaSubContas.get(i-1);
		}
		return retorno;
	}
	

	
	public class SelecHandle  implements ItemListener {
		@SuppressWarnings("unchecked")
		public void itemStateChanged(ItemEvent e) {
			System.out.print("Adicionado: ");
			if (e.getStateChange()!= ItemEvent.SELECTED) return;
			JComboBox<ContaComposite> comboBoxSelec = (JComboBox<ContaComposite>)e.getSource();
			while (cbList.indexOf(comboBoxSelec)<cbList.size()-1) {
				painel.remove(cbList.get(cbList.size()-1));
				cbList.remove(cbList.size()-1);
				painel.validate();
				repaint();
			}
			ContaComposite contaSelec = (ContaComposite)comboBoxSelec.getSelectedItem();
			if (contaSelec.getContas().size()>0) {
				adicionaJComboBox(contaSelec);
			}
			System.out.println(contaSelec);
		}
	}
	
}
