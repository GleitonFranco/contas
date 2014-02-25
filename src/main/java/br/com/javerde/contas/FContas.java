package br.com.javerde.contas;

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
	JTextField tfNome = new JTextField(50);
	JButton bSalvarTudo = new JButton("Salvar Tudo");
	JButton bCarregar = new JButton("Carregar Tudo");
	JButton bAdicionarNo = new JButton("Adiciona No'");
	JButton bRemoverNo = new JButton("Remover No'");
	JButton bEditarNo = new JButton("Editar No'");
	JButton bNovoPlano = new JButton("Novo Plano");
	
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
	
	TMLanca tmLanca;
	JTable tabela;

	// Painel de entrada ou edicao de contas
	ContaCadastroPanel painelDialog;
    JTextField tfNomeConta;
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
		tmLanca=new TMLanca(controlador.getContaRaiz().getLancamentos());
		tabela = new JTable(tmLanca);
		painelC2 = new JScrollPane(tabela);
		painelC2.setBackground(Color.BLUE);
		
		rola = new JScrollPane(arvore);
		rola.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		rola.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		rola.setViewportView(arvore);
//		rola.setPreferredSize(new Dimension(300, 2000));
		
		painelN.setLayout(new GridLayout(0,2));
		painelN.add(new JLabel("Comandos de Plano de Contas:"));
		painelN.add(new JLabel(""));
		painelN.add(bCarregar);
		painelN.add(bSalvarTudo);
		painelN.add(new JLabel("Comandos de Contas Individuais:"));
		painelN.add(new JLabel(""));
		painelN.add(new JLabel("Nome"));
		painelN.add(tfNome);
		painelN.add(bAdicionarNo);
		painelN.add(bRemoverNo);
		painelN.add(bEditarNo);
		painelN.add(bNovoPlano);

		painelL.setLayout(new BorderLayout());
		painelL.add(painelN,BorderLayout.NORTH);
		painelL.add(rola,BorderLayout.CENTER);
		painel1.setLayout(new GridLayout(1,2));
		painel1.add(painelL);
		
		JPanel painelC0 = new JPanel();
		painelC0.setBackground(Color.LIGHT_GRAY);
		painelC0.add(new JLabel("Lancamentos Contabeis"));
		
		painelDe = new MultiComboPanel("De (D��bito):",controlador.contaRaiz, controlador.contaNula);
		
		painelPara = new MultiComboPanel("Para (Cr��bito):",controlador.contaRaiz, controlador.contaNula);
		
		painelC1.add(new JLabel("Data In��cio:"));
		painelC1.add(tfDataIni);
		
		painelC1.add(new JLabel("Data Fim:"));
		painelC1.add(tfDataFim);
		
		painelC1.add(new JLabel("Valor"));
		painelC1.add(tfValor);
		
		painelC1.add(new JLabel("Hist��rico:"));
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
				repaint();
			}
		});
		
		bRemoverNo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (arvore.getSelectionCount()==0) {
					JOptionPane.showMessageDialog(null, "Nenhum n�� foi selecionado!");
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
					JOptionPane.showMessageDialog(null, "Nenhum n�� foi selecionado!");
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
				System.out.println(lanca);
				repaint();
			}
		});
		
		bSalvarTudo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				//new FileDialog(FContas.this, "Salvar", FileDialog.SAVE).getFile();
				
				fc.showSaveDialog(FContas.this);
				File file = fc.getSelectedFile();
				System.out.println(file.getAbsolutePath());
				controlador.salva(file.getAbsolutePath());
			}
		});
		
		bCarregar.addActionListener(new ActionListener() {
			
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
		
		bNovoPlano.addActionListener(new ActionListener() {
			
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
		
		treeModel = new ContaTreeModel(raiz);
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
//		painelC2.revalidate();
//		painelC2.repaint();

//		painelC2.remove(tabela);
		tmLanca = new TMLanca(conta.getLancamentos());
		tabela = new JTable(tmLanca);
//		painelC2.add(tabela);
		painelC2.setViewportView(tabela);
		painelC2.validate();
		repaint();
		if (conta.getLancamentos().size()>0) for (Lancamento l: conta.getLancamentos()) {
			System.out.println("-"+l);
		}
	}
	
	public void initFormLanca() {
		tfDataIni.setText(String.format("%tF", controlador.getDataIni()));
		tfDataFim.setText(String.format("%tF", controlador.getDataFim()));
		
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

