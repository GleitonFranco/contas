package br.com.javerde.contas.controller;

import br.com.javerde.contas.model.Conta;
import br.com.javerde.contas.model.ContaComposite;
import br.com.javerde.contas.model.DebitoCredito;
import br.com.javerde.contas.viewswing.FContas;


public class ContasMain {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		/*ContaComposite raiz = new Conta("A",  DebitoCredito.DEBITO , null);
		
		ContaComposite filhoDaRaiz = new Conta("A_1",  DebitoCredito.DEBITO, raiz);
		
		ContaComposite filhoDaRaizFilho = new Conta("A_1_1",  DebitoCredito.CREDITO, filhoDaRaiz);
		ContaComposite filhoDaRaizFilho1 = new Conta("A_1_1_2",  DebitoCredito.CREDITO, filhoDaRaiz);
		ContaComposite filhoDaRaizFilho2 = new Conta("A_1_1_3",  DebitoCredito.CREDITO, filhoDaRaiz);
		ContaComposite filhoDaRaizFilho21 = new Conta("A_1_1_3_1",  DebitoCredito.CREDITO, filhoDaRaizFilho2);
		ContaComposite filhoDaRaizFilho22 = new Conta("A_1_1_3_2",  DebitoCredito.CREDITO, filhoDaRaizFilho2);
		ContaComposite filhoDaRaizFilho3 = new Conta("A_1_1_4",  DebitoCredito.CREDITO, filhoDaRaiz);
		
		ContaComposite filhoDaRaiz2 = new Conta("A_2",  DebitoCredito.DEBITO, raiz);
		
		raiz.exibirHierarquiaDeContas();*/
		
		new FContas(new Conta("Plano de Contas", DebitoCredito.DEBITO, null), 
				new Conta("Selecione (ou nao) -", DebitoCredito.DEBITO, null))
				.setVisible(true);
	}
}
