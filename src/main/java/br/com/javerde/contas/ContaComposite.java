package br.com.javerde.contas;

import java.util.List;

public interface ContaComposite {

	public String getNome();
	public String getCodigo();
	public String getNomeCompleto();
	public String getPath();
	public DebitoCredito getDebitoCredito();
	public void setDebitoCredito(DebitoCredito dc);
	public double getCredito();
	public double getDebito();
	public double getCreditoTotal();
	public double getDebitoTotal();
	public void setNome(String s);
	public void setCredito(double c);
	public void setDebito(double d);
	public void addLanca(Lancamento lanca);
	public void removeLanca(Lancamento lanca);
	public void addConta(ContaComposite conta);
	public void removeConta(ContaComposite conta);
	public List<ContaComposite> getContas();
	public List<Lancamento> getLancamentos();
	public ContaComposite getContaPai();
	public void setContaPai(ContaComposite c);
	
}

