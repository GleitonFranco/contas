package br.com.javerde.contas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Conta implements ContaComposite, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4204298292371563272L;
	private List<ContaComposite> contas;
	private List<Lancamento> lancamentos;
	private ContaComposite contaPai;
	private String nome;
	private int codigo;
	private double credito;
	private double debito;
	private double creditoTotal;
	private double debitoTotal;
	
	
	public Conta(String nome, ContaComposite pai) {
		contaPai = pai;
		if (pai == null) {
			codigo = 0;
		} else {
			codigo = pai.getContas().size()+1;
			pai.addConta(this);
		}
		this.nome = nome;
	}
	
//	
	public String getNome() {
		return nome;
	}

//	
	public String getCodigo() {
		if (contaPai==null) {
			return "";
		} else {
			return contaPai.getCodigo()+(contaPai.getCodigo()==""?"":".")+codigo;
		}
	}

//	
	public String getNomeCompleto() {
		return getCodigo()+" - "+getNome();
	}

//	
	public String getPath() {
		return "";
	}

//	
	public double getCredito() {//lancamentos
		return credito;
	}

	
	public double getDebito() {//lancamentos
		return debito;
	}

	
	public double getCreditoTotal() { //Conta atual + subcontas
		return creditoTotal;
	}
	
	
	public double getDebitoTotal() {
		return debitoTotal;
	}

	
	public void setCredito(double credito) {
		this.credito += credito;
	}
	
	
	public void setDebito(double debito) {
		this.debito += debito;
	}
	
	
	public void addLanca(Lancamento lanca) {
		if (lancamentos==null) {
			lancamentos = new ArrayList<Lancamento>();
		}
		lancamentos.add(lanca);
	}

	
	public void removeLanca(Lancamento lanca) {
		lancamentos.remove(lanca);
	}

	
	public void addConta(ContaComposite conta) {
		if (contas==null) {
			contas = new ArrayList<ContaComposite>();
		}
		contas.add(conta);
	}

	
	public void removeConta(ContaComposite conta) {
		contas.remove(conta);
	}

	
	public String toString() {
		return getNomeCompleto();
	}

	
	public List<ContaComposite> getContas() {
		return contas==null?new ArrayList<ContaComposite>():contas;
	}
	
	
	public List<Lancamento> getLancamentos() {
		return lancamentos==null?new ArrayList<Lancamento>():lancamentos;
	}

	
	public void setNome(String s) {
		nome = s;
	}
	
	
	public ContaComposite getContaPai() {
		return contaPai;
	}
	
	
	public void setContaPai(ContaComposite c) {
		this.contaPai = c;
	}

}
