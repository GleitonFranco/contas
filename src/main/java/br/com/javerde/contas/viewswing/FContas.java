package br.com.javerde.contas.viewswing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import br.com.javerde.contas.controller.ContaController;
import br.com.javerde.contas.model.Conta;
import br.com.javerde.contas.model.ContaComposite;
import br.com.javerde.contas.model.DebitoCredito;
import br.com.javerde.contas.model.Lancamento;

public class FContas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2009407377368041304L;
	ContaController controlador;
	
	TreeModel treeModel;
	JTree arvore;
	
	JTabbedPane abas = new JTabbedPane();
	JScrollPane rola;
	
	JPanel painel1 = new JPanel();
	JPanel painel2 = new JPanel();
	JPanel painel3 = new JPanel();

	JPanel painelN = new JPanel();
	JPanel painelL = new JPanel();
	JPanel painelC = new JPanel();
	JPanel painelC1 = new JPanel(new GridLayout(0,2,5,5));
	MultiComboPanel painelDe;
	MultiComboPanel painelPara;
	JScrollPane painelC2;
	JButton bAdicionarNo = new JButton("Adiciona Item");
	JButton bRemoverNo = new JButton("Remover Item");
	JButton bEditarNo = new JButton("Editar Item");
	
	// Componentes Lancamentos
	JTextField tfDe = new JTextField();
	JTextField tfPara = new JTextField();
	JTextField tfDataIni = new JTextField();
	JTextField tfDataFim = new JTextField();
	JTextField tfValor = new JTextField("100");
	JTextField tfHistorico = new JTextField("Um Lancamento ocorreu");
	JButton bNovo = new JButton("Novo");
	JButton bDeletar = new JButton("Delete");
	JButton bSalvar = new JButton("Salvar");
	JButton bCancelar = new JButton("Cancelar");
	
	TableModelLanca tmLanca;
	JTable tabela;
	
	// Componentes de MENU
	JMenuBar menu = new JMenuBar();
	JMenu menuArquivo = new JMenu("Arquivo");
	JMenuItem itemNovo = new JMenuItem("Novo");
	JMenuItem itemAbrir = new JMenuItem("Abrir");
	JMenuItem itemSalvar = new JMenuItem("Salvar");
	{
		menuArquivo.setMnemonic('a');
		menu.add(menuArquivo);
		menuArquivo.add(itemNovo);
		menuArquivo.add(itemAbrir);
		menuArquivo.add(itemSalvar);
	}

	// Painel de entrada ou edicao de contas
	ContaCadastroPanel painelDialog;
    JComboBox<DebitoCredito> cbDC = new JComboBox<DebitoCredito>(DebitoCredito.values()); 
    
    
	
	public FContas(ContaComposite contaRaiz, ContaComposite contaNula) {
		super("Plano de Contas");
		controlador = new ContaController(contaRaiz, contaNula);
		initComponents();
	}
	
	public void initComponents() {
//		controlador.carrega("/home/gleiton/contas.ser");
//		controlador.novo();
		initTree(controlador.getContaRaiz());
		arvore.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		controlador.getContaRaiz().addLanca(new Lancamento());
		tmLanca=new TableModelLanca(controlador.getContaRaiz().getLancamentos());
		tabela = new JTable(tmLanca);
		painelC2 = new JScrollPane(tabela);
		painelC2.setBackground(Color.BLUE);
		
		rola = new JScrollPane(arvore);
		rola.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		rola.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		rola.setViewportView(arvore);
	
		painelN.setLayout(new GridLayout(0,3));
		painelN.add(new JLabel("Comandos para a Arvore de Contas:"));
		painelN.add(new JLabel(""));
		painelN.add(new JLabel(""));
		painelN.add(bAdicionarNo);
		painelN.add(bRemoverNo);
		painelN.add(bEditarNo);

		painelL.setLayout(new BorderLayout());
		painelL.add(painelN,BorderLayout.NORTH);
		painelL.add(rola,BorderLayout.CENTER);
		painel1.setLayout(new GridLayout(1,2));
		painel1.add(painelL);
		
		JPanel painelC0 = new JPanel();
		painelC0.setBackground(Color.LIGHT_GRAY);
		painelC0.add(new JLabel("Lancamentos Contabeis"));
		
		painelDe = new MultiComboPanel("De (Debito):",controlador.getContaRaiz(), controlador.getContaNula());
		
		painelPara = new MultiComboPanel("Para (Crebito):",controlador.getContaRaiz(), controlador.getContaNula());
		
		painelC1.add(new JLabel("Data Inicio:"));
		painelC1.add(tfDataIni);
		
		painelC1.add(new JLabel("Data Fim:"));
		painelC1.add(tfDataFim);
		
		painelC1.add(new JLabel("Valor"));
		painelC1.add(tfValor);
		
		painelC1.add(new JLabel("Historico:"));
		painelC1.add(tfHistorico);
		
		painelC1.add(bNovo);
		painelC1.add(bDeletar);
		
		painelC1.add(bSalvar);
		painelC1.add(bCancelar);
		
		painelC.setLayout(new BoxLayout(painelC,BoxLayout.Y_AXIS));
		painelC.add(painelC0);
		painelC.add(painelDe);
		painelC.add(painelPara);
		painelC.add(painelC1);
		
		abas.addTab("Plano de Contas", painel1);
		abas.addTab("Lancamentos", painelC);
		abas.addTab("Histórico", painelC2);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		add(abas, BorderLayout.CENTER);
		setResizable(true);
		
		initListeners();
		initFormLanca();
		
		// Painel usado pelo Dialog
		painelDialog = new ContaCadastroPanel();
		
		// MENU
		setJMenuBar(menu);
	    
	}
	
	public void initListeners() {
		
		bAdicionarNo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ContaComposite pai;
				if (arvore.getSelectionCount()==0) {
					pai = controlador.getContaRaiz();
				} else {
					pai = (ContaComposite)arvore.getSelectionPath().getLastPathComponent();
				}
				
				painelDialog.novo(pai);
				JOptionPane.showMessageDialog(null, painelDialog);
				
				new Conta(painelDialog.getNome(),painelDialog.getDebitoCredito(),pai);
				initTree(controlador.getContaRaiz());
				rola.setViewportView(arvore);
				expandeTudo();
				painelDe.atualiza(controlador.getContaRaiz());
				painelPara.atualiza(controlador.getContaRaiz());
				System.out.println(painelDe.cbList.size());
				System.out.println(controlador.getContaRaiz().getContas().size());
			}
		});
		
		bRemoverNo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (arvore.getSelectionCount()==0) {
					JOptionPane.showMessageDialog(null, "Nenhum Item foi selecionado!");
					return;
				}
				ContaComposite conta = (ContaComposite)arvore.getSelectionPath().getLastPathComponent();
				ContaComposite pai = conta.getContaPai();
				pai.removeConta(conta);
				initTree(controlador.getContaRaiz());
				rola.setViewportView(arvore);
				expandeTudo();
				repaint();
			}
		});

		bEditarNo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (arvore.getSelectionCount()==0) {
					JOptionPane.showMessageDialog(null, "Nenhum Item foi selecionado!");
					return;
				}
				
				ContaComposite conta = (ContaComposite)arvore.getSelectionPath().getLastPathComponent();
				painelDialog.editar(conta);
				JOptionPane.showMessageDialog(null, painelDialog);
				conta.setNome(painelDialog.getNome());
				conta.setDebitoCredito(painelDialog.getDebitoCredito());
				initTree(controlador.getContaRaiz());
				rola.setViewportView(arvore);
				expandeTudo();
				repaint();
			}
		});

		
		// COMANDOS PARA OS LANCAMENTOS
		
		bNovo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				painelDe.atualiza(controlador.getContaRaiz());
				painelPara.atualiza(controlador.getContaRaiz());
				repaint();
			}
		});
		
		bSalvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Lancamento lanca = new Lancamento();
				lanca.contaDe = painelDe.getContaSelec();
				lanca.contaPara = painelPara.getContaSelec();
				lanca.valor = Double.parseDouble(tfValor.getText().replace(',','.'));
				lanca.historico = tfHistorico.getText();
				lanca.dataIni = controlador.getDataIni();
				lanca.dataVenc = controlador.getDataFim();
				lanca.contaDe.addLanca(lanca);
				lanca.contaPara.addLanca(lanca);
				controlador.getContaRaiz().addLanca(lanca);
				painelC2.revalidate();
				painelC2.repaint();
				tabela.revalidate();
				tabela.repaint();
				System.out.println(lanca);
				repaint();
			}
		});
		
		itemSalvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.showSaveDialog(FContas.this);
				File file = fc.getSelectedFile();
				System.out.println(file.getAbsolutePath());
				controlador.salva(file.getAbsolutePath());
			}
		});
		
		itemAbrir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(FContas.this);
				controlador.carrega(fc.getSelectedFile().getAbsolutePath());
				initTree(controlador.getContaRaiz());
				rola.setViewportView(arvore);
				painelDe.carrega(controlador.getContaRaiz());
				painelPara.carrega(controlador.getContaRaiz());
				initTable(controlador.getContaRaiz());
				repaint();
				for (Lancamento l : tmLanca.lancamentos)
					System.out.println(l);
			}
		});
		
		itemNovo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
//				controlador.novo();
//				initTree(controlador.getContaRaiz());
				for (int i = 0; i < arvore.getRowCount(); i++) {
					arvore.expandRow(i);
				}
				arvore.validate();
				rola.setViewportView(arvore);
				repaint();
			}
		});
		
	}
	
	public void initTree(ContaComposite raiz) {
		
		treeModel = new TreeModelConta(raiz);
		arvore = new JTree(treeModel);
		repaint();
		System.out.println("Iniciando - Raiz: "+raiz.getNomeCompleto());
		
		arvore.addTreeSelectionListener(new TreeSelectionListener() {
			
			public void valueChanged(TreeSelectionEvent e) {
				controlador.setContaSelec( (ContaComposite)arvore.getSelectionPath().getLastPathComponent());
				System.out.println("Selecionado-->"+controlador.getContaSelec().getNomeCompleto());
				initTable(controlador.getContaSelec());
			}
		});
		
	}
	
	public void initTable(ContaComposite conta) {
		tmLanca = new TableModelLanca(conta.getLancamentos());
		tabela = new JTable(tmLanca);
		painelC2.setViewportView(tabela);
		painelC2.validate();
		repaint();
		if (conta.getLancamentos().size()>0) for (Lancamento l: conta.getLancamentos()) {
			System.out.println("-"+l);
		}
	}
	
	public void initFormLanca() {
		tfDataIni.setText(String.format("%1$td/%1$tm/%1$tY", controlador.getDataIni()));
		tfDataFim.setText(String.format("%1$td/%1$tm/%1$tY", controlador.getDataFim()));
		
	}
	
	public void expandeTudo() {
		for (int i = 0; i < arvore.getRowCount(); i++) {
			arvore.expandRow(i);
		}
		arvore.validate();
		rola.setViewportView(arvore);
		repaint();
	}
	
}

