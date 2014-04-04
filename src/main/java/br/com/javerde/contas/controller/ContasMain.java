package br.com.javerde.contas.controller;

import br.com.javerde.contas.model.Conta;
import br.com.javerde.contas.model.DebitoCredito;
import br.com.javerde.contas.viewswing.FContas;


public class ContasMain {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		new FContas(new Conta("Plano de Contas", DebitoCredito.DEBITO, null), 
				new Conta("Selecione (ou nao) -", DebitoCredito.DEBITO, null))
				.setVisible(true);
	}
	

}
