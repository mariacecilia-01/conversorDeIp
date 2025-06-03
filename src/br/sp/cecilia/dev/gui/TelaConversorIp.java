package br.sp.cecilia.dev.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.sp.cecilia.dev.model.Ip;

public class TelaConversorIp {
	private JLabel lblTitulo;
	private JLabel lblIp;
	private JLabel lblCidr;
	
	private JTextField txtIp;
	private JTextField txtCidr;
	
	private JButton btnCalcular;
	private JButton btnLimpar;
	
	private JLabel lblTitleMascara;
	private JLabel lblMascara;
	private JLabel lblTituloBinario;
	private JLabel lblBinario;
	private JLabel lblTituloClasse;
	private JLabel lblClasse;
	private JLabel lblTituloHosts;
	private JLabel lblHosts;
	
	private Font fontTitulo = new Font("Nunito", Font.BOLD, 24);
	private Font fontTexto = new Font("Nunito", Font.BOLD, 16);
	
	
	public void criarTela() {
		JFrame tela = new JFrame();
		tela.setTitle("Calculadora de IP's");
		tela.setSize(500,750);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setResizable(false);
		tela.setLayout(null);
		tela.setLocationRelativeTo(null);
		
		Container painel = tela.getContentPane();

		lblTitulo = new JLabel("Calculadora de IP's");
		lblTitulo.setFont(fontTitulo);
		lblTitulo.setBounds(140, 10, 300, 100);
		
		lblIp = new JLabel("IP:");
		lblIp.setFont(fontTexto);
		lblIp.setBounds(20, 90, 50, 50);
		txtIp = new JTextField();
		txtIp.setBounds(50, 100, 260, 30);
		
		lblCidr = new JLabel("CIDR (/):");
		lblCidr.setFont(fontTexto);
		lblCidr.setBounds(315, 90, 80, 50);
		txtCidr = new JTextField();
		txtCidr.setBounds(380, 100, 70, 30);
		
		btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(20, 150, 220, 60);
		btnCalcular.setFont(fontTitulo);
		btnCalcular.setBackground(Color.PINK);
		btnCalcular.setForeground(Color.WHITE);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(250, 150, 220, 60);
		btnLimpar.setFont(fontTitulo);
		btnLimpar.setBackground(Color.PINK);
		btnLimpar.setForeground(Color.WHITE);
		
		lblTitleMascara = new JLabel("Máscara:");
		lblTitleMascara.setBounds(20, 220, 100, 60);
		lblTitleMascara.setFont(fontTexto);
		lblMascara = new JLabel();
		lblMascara.setBounds(150, 220, 200, 60);
		lblMascara.setBackground(Color.WHITE);
		
		lblTituloBinario = new JLabel("Binário:");
		lblTituloBinario.setBounds(20, 250, 100, 60);
		lblTituloBinario.setFont(fontTexto);
		lblBinario = new JLabel();
		lblBinario.setBounds(150, 250, 200, 60);
		lblBinario.setBackground(Color.WHITE);
		
		lblTituloClasse = new JLabel("Classe:");
		lblTituloClasse.setBounds(20, 280, 100, 60);
		lblTituloClasse.setBackground(Color.WHITE);
		lblTituloClasse.setFont(fontTexto);
		lblClasse = new JLabel();
		lblClasse.setBounds(150, 280, 100, 60);
		lblClasse.setBackground(Color.WHITE);
		
		lblTituloHosts = new JLabel("Hosts");
		lblTituloHosts.setBounds(20, 310, 100, 60);
		lblTituloHosts.setBackground(Color.WHITE);
		lblTituloHosts.setFont(fontTexto);
		lblHosts = new JLabel();
		lblHosts.setBounds(150, 310, 100, 60);
		lblHosts.setBackground(Color.WHITE);
		
		btnCalcular.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			Ip ip = new Ip();
			String textIp = txtIp.getText().trim();
			String textCidr = txtCidr.getText().trim();
			
			if (!isValidIp(txtIp)) {
				JOptionPane.showMessageDialog(tela, "IP inválido! Use o formato correto! (Ex.: 192.168.0.0)", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
			ip.setIp(textIp);
			String classificarIp = ip.classificarIp();
			if (classificarIp.equals("Faltando número no IP.") || classificarIp.equals("Sem classe")) {
				JOptionPane.showMessageDialog(tela, "IP inválido ou fora das classes válidas!", "Erro", JOptionPane.ERROR_MESSAGE);
				
			}
				
			}
		})
		
		
		
		
		painel.add(lblTitulo);
		painel.add(lblIp);
		painel.add(txtIp);
		painel.add(lblCidr);
		painel.add(txtCidr);
		painel.add(btnCalcular);
		painel.add(btnLimpar);
		painel.add(lblTitleMascara);
		painel.add(lblMascara);
		painel.add(lblTituloBinario);
		painel.add(lblBinario);
		painel.add(lblTituloClasse);
		painel.add(lblClasse);
		painel.add(lblTituloHosts);
		painel.add(lblHosts);
		
		
		tela.setVisible(true);
	}
		
	}
