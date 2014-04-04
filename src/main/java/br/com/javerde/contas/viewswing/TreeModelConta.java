package br.com.javerde.contas.viewswing;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import br.com.javerde.contas.model.ContaComposite;

public class TreeModelConta implements TreeModel {
	private ContaComposite contaRaiz;
	private List<TreeModelListener> listeners = new ArrayList<TreeModelListener>();
	
	public TreeModelConta(ContaComposite contaRaiz) {
		this.contaRaiz = contaRaiz;
	}
	
	
	public void addTreeModelListener(TreeModelListener l) {
		listeners.add(l);
	}

	
	public Object getChild(Object conta, int index) {
		return ((ContaComposite)conta).getContas().get(index);
	}

	
	public int getChildCount(Object conta) {
		return ((ContaComposite)conta).getContas().size();
	}

	
	public int getIndexOfChild(Object conta, Object subconta) {
		return ((ContaComposite)conta).getContas().indexOf(subconta);
	}

	
	public Object getRoot() {
		return contaRaiz;
	}

	
	public boolean isLeaf(Object conta) {
		return ( ((ContaComposite)conta).getContas().size()==0 );
	}

	
	public void removeTreeModelListener(TreeModelListener l) {
		listeners.remove(l);
	}

	
	public void valueForPathChanged(TreePath arg0, Object arg1) {
 		
	}

}
