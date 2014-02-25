package br.com.javerde.contas;

public enum DebitoCredito {
	DEBITO(-1,"D"),
	CREDITO(1,"C");
	
	DebitoCredito(int valor, String abrev) {
		this.valor=valor;
		this.abrev=abrev;
	}
	
	private int valor; 
	private String abrev;
	
	public int getValor() {
		return valor;
	}
	
	public String getAbrev() {
		return abrev;
	}
	
	public String getDescr() {
		return (abrev=="D"?"Debito":"Credito");
	}

	@Override
	public String toString() {
		return (abrev=="D"?"Debito":"Credito");
	}
	
}
