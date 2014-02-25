package br.com.javerde.contas;

public class ContasMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new FContas(new Conta("Plano de Contas", DebitoCredito.DEBITO, null)).setVisible(true);
	}
	

}
