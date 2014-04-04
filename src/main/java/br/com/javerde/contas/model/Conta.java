package br.com.javerde.contas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Conta implements ContaComposite, Serializable {

	private static final long serialVersionUID = -4204298292371563272L;
	private List<ContaComposite> contas = new ArrayList<ContaComposite>();
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	private ContaComposite contaPai;
	private String nome;
	private int codigo;
	private DebitoCredito debitoCredito;
	private double credito;
	private double debito;
	private double creditoTotal;
	private double debitoTotal;
	
	public Conta(String nome, DebitoCredito dc, ContaComposite contaPai) {
		this.contaPai = contaPai;
		mountingParent(contaPai);
		this.nome = nome;
		this.debitoCredito=dc;
	}
	
//	
	public String getNome() {
		return nome;
	}
	
	private void mountingParent(ContaComposite conta){
		this.codigo = 0;
		if(!isRaiz()){
			this.codigo = conta.getContas().size()+1;
			conta.addConta(this);
		}
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
		return getCodigo()+" - "+getNome()+" ("+debitoCredito.getAbrev()+")";
	}

//	
	public DebitoCredito getDebitoCredito() {
		return debitoCredito;
	}
	
	public void setDebitoCredito(DebitoCredito dc) {
		debitoCredito=dc;
	}
	
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
		this.credito = credito;
	}
	
	public void addCredito(double credito) {
		this.credito += credito;
	}
	
	
	public void setDebito(double debito) {
		this.debito = debito;
	}
	
	public void addDebito(double debito) {
		this.debito += debito;
	}
	
	
	public void addLanca(Lancamento lanca) {
		lancamentos.add(lanca);
	}

	
	public void removeLanca(Lancamento lanca) {
		lancamentos.remove(lanca);
	}

	
	public void addConta(ContaComposite conta) {
		contas.add(conta);
	}

	
	public void removeConta(ContaComposite conta) {
		contas.remove(conta);
	}

	
	public String toString() {
		return getNomeCompleto();
	}

	
	public List<ContaComposite> getContas() {
		return contas;
	}
	
	
	public List<Lancamento> getLancamentos() {
		return lancamentos;
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

	public boolean isRaiz() {
		return getContaPai() == null;
	}
	
	public void exibirHierarquiaDeContas(){
		exibirHierarquiaDeContas(this);
	}
	
	public void exibirHierarquiaDeContas(ContaComposite conta) {
		System.out.println(conta.getNomeCompleto());
		if(conta.isRaiz() || !conta.getContas().isEmpty()){
			for (ContaComposite contaFilha : conta.getContas()) {
				exibirHierarquiaDeContas(contaFilha);
			}
		}
	}
}
