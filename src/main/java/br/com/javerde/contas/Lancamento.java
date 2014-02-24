package br.com.javerde.contas;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Lancamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1635516074480302406L;
	ContaComposite contaDe;
	ContaComposite contaPara;
	Calendar dataIni;
	Calendar dataVenc;
	double valor;
	String historico;
	
	
	@Override
	public String toString() {
		if (contaDe!=null && contaPara!=null) {
			return contaDe.getNomeCompleto()+" Para:"+contaPara.getNomeCompleto()+"="+valor+" - "+historico;
		}
		return "";
		
	}
	
}
