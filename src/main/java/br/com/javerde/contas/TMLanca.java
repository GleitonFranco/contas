package br.com.javerde.contas;

import java.util.Calendar;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TMLanca extends AbstractTableModel {

	public List<Lancamento> lancamentos;
	
	public TMLanca(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	
	public int getColumnCount() {
		return 6;
	}

	
	public int getRowCount() {
		return lancamentos.size();
	}

	
	public Object getValueAt(int lin, int col) {
		Object o=null;
		switch (col) {
			case 0 : o=lancamentos.get(lin).contaDe;
			break;
			case 1 : o=lancamentos.get(lin).contaPara;
			break;
			case 2 : o=String.format("%1$td/%1$tm/%1$tY",lancamentos.get(lin).dataIni);//.getTime();
			break;
			case 3 : o=String.format("%1$td/%1$tm/%1$tY",lancamentos.get(lin).dataVenc);//.getTime();
			break;
			case 4 : o=lancamentos.get(lin).historico;
			break;
			case 5 : o=String.format("%,.2f",lancamentos.get(lin).valor);
			break;
		}
		return o;
	}

	
	public String getColumnName(int col) {
		String s = "";
		switch (col) {
			case 0 : s="De";
			break;
			case 1 : s="Para";
			break;
			case 2 : s="DataIni";
			break;
			case 3 : s="DataFim";
			break;
			case 4 : s="Historico";
			break;
			case 5 : s="Valor";
			break;
		}
		return s;
	}

	
	public Class<?> getColumnClass(int col) {
//		switch (col) {
//			case 0 : return String.class;
//			case 1 : return Conta.class;
//			case 2 : return Calendar.class;
//			case 3 : return Calendar.class;
//			case 4 : return String.class;
//			case 5 : return Double.class;
//		}
		return String.class;
	}

	
}
