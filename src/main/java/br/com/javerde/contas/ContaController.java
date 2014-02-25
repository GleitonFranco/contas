package br.com.javerde.contas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class ContaController {
	ContaComposite contaRaiz;
	ContaComposite contaSelec;
	ContaComposite contaNula = new Conta("Selecione (ou nao) -", DebitoCredito.DEBITO, null);
	Calendar dataIni = Calendar.getInstance();
	Calendar dataFim = Calendar.getInstance();
	ContaComposite contaDe;
	ContaComposite contaPara;

	
	public ContaController(ContaComposite contaRaiz) {
		this.contaRaiz = contaRaiz;
		contaDe = contaRaiz;
		contaPara = contaRaiz;
		dataIni = Calendar.getInstance();
		dataFim = Calendar.getInstance();
	}
	
	public void novo() {
		contaRaiz = new Conta("Plano de Contas2", DebitoCredito.DEBITO, null);
		contaSelec = contaRaiz;
		contaDe = contaRaiz;
		contaPara = contaRaiz;
	}
	
	public void salva(String arq) {
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(arq));//"res/contas.ser"));
			os.writeObject(contaRaiz);
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void carrega(String arq) {
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(arq));
			contaRaiz = (ContaComposite) is.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		contaSelec = contaRaiz;
		contaDe = contaRaiz;
		contaPara = contaRaiz;
		
	}

	public Object[] getListaSubContas(ContaComposite conta) {
		List listaSubContas = conta.getContas(); 
		Object[] retorno = new Object[listaSubContas.size()+1];
		retorno[0] = contaNula;
		for (int i =1; i <= listaSubContas.size();i++) {
			retorno[i] = listaSubContas.get(i-1);
		}
		return retorno;
	}
	
	public ContaComposite getContaRaiz() {
		return contaRaiz;
	}

	public void setContaRaiz(ContaComposite contaRaiz) {
		this.contaRaiz = contaRaiz;
	}

	public ContaComposite getContaSelec() {
		return contaSelec;
	}

	public void setContaSelec(ContaComposite contaSelec) {
		this.contaSelec = contaSelec;
	}

	public Calendar getDataIni() {
		return dataIni;
	}

	public void setDataIni(Calendar dataIni) {
		this.dataIni = dataIni;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public ContaComposite getContaDe() {
		return contaDe;
	}

	public void setContaDe(ContaComposite contaDe) {
		this.contaDe = contaDe;
	}

	public ContaComposite getContaPara() {
		return contaPara;
	}

	public void setContaPara(ContaComposite contaPara) {
		this.contaPara = contaPara;
	}
	
//	public void addLancamento(Lancamento lanca) {
//		livroRazao.add(lanca);
//	}
//	
//	public List<Lancamento> getRazao() {
//		return livroRazao;
//	}
	
}
