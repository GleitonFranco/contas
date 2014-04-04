package br.com.javerde.contas.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Lancamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1635516074480302406L;
	public ContaComposite contaDe;
	public ContaComposite contaPara;
	public Calendar dataIni;
	public Calendar dataVenc;
	public double valor;
	public String historico;
	
	
	@Override
	public String toString() {
		if (contaDe!=null && contaPara!=null) {
			return contaDe.getNomeCompleto()+" Para:"+contaPara.getNomeCompleto()+"="+valor+" - "+historico;
		}
		return "";
		
	}
	
}
