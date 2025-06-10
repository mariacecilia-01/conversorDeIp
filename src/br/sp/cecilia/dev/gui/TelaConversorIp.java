package br.sp.cecilia.dev.gui;

import javax.swing.*;

import br.sp.cecilia.dev.model.Ip;

import java.awt.*;
import java.awt.event.*;

public class TelaConversorIp extends JFrame {
	private JTextField textIp;
	private JTextField textCidr;
	private JLabel labelClassIp;
	private JLabel labelResultMask;
	private JLabel labelResultBinary;
	private JLabel labelIpsDisponiveis;
	private JLabel labelPrimeiroIp;
	private JLabel labelUltimoIp;
	private JLabel labelBroadcast;
	private JLabel labelSubRedes;
	private JLabel labelError;
	private JButton buttonConverter;
	private JButton buttonLimpar;
	private JLabel labelSubnets;
	private JPanel painel;
	private JTextArea textAreaSubnets; // New JTextArea for showNetwork output
	private JScrollPane scrollPaneSubnets; // Scroll pane for JTextArea

	public void criarTela() {
		 JFrame tela = new JFrame("Conversor de IPv4 e Máscara");
	        tela.setSize(500, 700); // Increased height to accommodate JTextArea
	        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        tela.setLocationRelativeTo(null);
	        tela.setResizable(false);
	        
	        painel = new JPanel();
	        painel.setLayout(null);
	        painel.setBackground(new Color(245, 245, 245)); // Fundo cinza claro
	        tela.setContentPane(painel);
	        
	        Font fonteTitulo = new Font("Segoe UI", Font.BOLD, 16);
	        Font fonteLabel = new Font("Segoe UI", Font.PLAIN, 14);
	        Font fonteInput = new Font("Segoe UI", Font.PLAIN, 14);
	        Font fonteErro = new Font("Segoe UI", Font.ITALIC, 12);
	        Font fonteTextArea = new Font("Segoe UI", Font.PLAIN, 12);
	        
	        JLabel titulo = new JLabel("Conversor de Endereços IPv4", SwingConstants.CENTER);
	        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
	        titulo.setForeground(new Color(255, 0, 127));
	        titulo.setBounds(0, 20, 500, 30);
	        painel.add(titulo);

	        // Campo de IP
	        JLabel labelIp = new JLabel("Endereço IP (Ex.: 192.168.0.0):");
	        labelIp.setFont(fonteTitulo);
	        labelIp.setBounds(30, 70, 300, 25);
	        painel.add(labelIp);

	        textIp = new JTextField();
	        textIp.setFont(fonteInput);
	        textIp.setBounds(30, 100, 300, 35);
	        textIp.setHorizontalAlignment(JTextField.CENTER);
	        painel.add(textIp);
	        
	        JLabel labelMask = new JLabel("CIDR (/):");
	        labelMask.setFont(fonteTitulo);
	        labelMask.setBounds(350, 70, 100, 25);
	        painel.add(labelMask);

	        textCidr = new JTextField();
	        textCidr.setFont(fonteInput);
	        textCidr.setBounds(350, 100, 100, 35);
	        textCidr.setHorizontalAlignment(JTextField.CENTER);
	        painel.add(textCidr);
	        
	        buttonConverter = new JButton("Calcular");
	        buttonConverter.setFont(fonteTitulo);
	        buttonConverter.setBackground(new Color(255, 105, 180));
	        buttonConverter.setForeground(Color.WHITE);
	        buttonConverter.setFocusPainted(false);
	        buttonConverter.setBounds(30, 150, 210, 40);
	        painel.add(buttonConverter);

	        buttonLimpar = new JButton("Limpar");
	        buttonLimpar.setFont(fonteTitulo);
	        buttonLimpar.setBackground(new Color(255, 203, 219));
	        buttonLimpar.setForeground(Color.WHITE);
	        buttonLimpar.setFocusPainted(false);
	        buttonLimpar.setBounds(260, 150, 210, 40);
	        painel.add(buttonLimpar);

	        labelClassIp = new JLabel("Classe do IP: ");
	        labelClassIp.setFont(fonteLabel);
	        labelClassIp.setBounds(30, 210, 440, 25);
	        painel.add(labelClassIp);

	        labelResultMask = new JLabel("Máscara: ");
	        labelResultMask.setFont(fonteLabel);
	        labelResultMask.setBounds(30, 240, 440, 25);
	        painel.add(labelResultMask);

	        labelResultBinary = new JLabel("Máscara Binária: ");
	        labelResultBinary.setFont(fonteLabel);
	        labelResultBinary.setBounds(30, 270, 440, 25);
	        painel.add(labelResultBinary);

	        labelIpsDisponiveis = new JLabel("IPs Disponíveis: ");
	        labelIpsDisponiveis.setFont(fonteLabel);
	        labelIpsDisponiveis.setBounds(30, 300, 440, 25);
	        painel.add(labelIpsDisponiveis);

	        labelSubRedes = new JLabel("Número de Sub-redes: ");
	        labelSubRedes.setFont(fonteLabel);
	        labelSubRedes.setBounds(30, 330, 440, 25);
	        painel.add(labelSubRedes);

	        labelSubnets = new JLabel("Detalhes das Sub-redes:");
	        labelSubnets.setFont(fonteTitulo);
	        labelSubnets.setForeground(new Color(255, 0, 127));
	        labelSubnets.setBounds(30, 360, 440, 25);
	        painel.add(labelSubnets);

	        textAreaSubnets = new JTextArea();
	        textAreaSubnets.setFont(fonteTextArea);
	        textAreaSubnets.setEditable(false);
	        textAreaSubnets.setLineWrap(true);
	        textAreaSubnets.setWrapStyleWord(true);
	        textAreaSubnets.setBackground(new Color(234, 206, 216));
	        scrollPaneSubnets = new JScrollPane(textAreaSubnets);
	        scrollPaneSubnets.setBounds(30, 390, 440, 150);
	        painel.add(scrollPaneSubnets);
	        
	        labelError = new JLabel("");
	        labelError.setFont(fonteErro);
	        labelError.setForeground(new Color(220, 53, 69));
	        labelError.setBounds(30, 640, 440, 25);
	        painel.add(labelError);
	        buttonConverter.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                Ip ip = new Ip();
	                String txtIp = textIp.getText().trim();
	                String txtCidr = textCidr.getText().trim();

	                // Validação do IP
	                if (!isValidIp(txtIp)) {
	                    JOptionPane.showMessageDialog(tela, "IP inválido! Use o formato correto (Ex.: 192.168.0.0)", "Erro", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                ip.setIp(txtIp);
	                String classificarIp = ip.classificarIp();
	                if (classificarIp.equals("Faltando número no IP.") || classificarIp.equals("Sem Classe")) {
	                    JOptionPane.showMessageDialog(tela, "IP inválido ou fora das classes válidas!", "Erro", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                // Validação do CIDR
	                try {
	                    int cidr = Integer.parseInt(txtCidr);
	                    if (cidr < 0 || cidr > 32) {
	                        JOptionPane.showMessageDialog(tela, "CIDR deve estar entre 0 e 32!", "Erro", JOptionPane.ERROR_MESSAGE);
	                        return;
	                    }

	                    ip.setMask(cidr);
	                    labelClassIp.setText("Classe do IP: " + classificarIp);
	                    labelResultMask.setText("Máscara: " + ip.cidrToMask());
	                    labelResultBinary.setText("Máscara Binária: " + ip.maskToBinary());
	                    labelIpsDisponiveis.setText("IPs Disponíveis: " + ip.calcularIPsDisponiveis());
	                    labelSubRedes.setText("Número de Sub-redes: " + ip.calcularSalto());
	                    textAreaSubnets.setText(ip.showNetwork()); // Display showNetwork output
	                    labelError.setText("");
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(tela, "CIDR inválido! Insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        });

	        // Ação do botão Limpar
	        buttonLimpar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                textIp.setText("");
	                textCidr.setText("");
	                labelClassIp.setText("Classe do IP: ");
	                labelResultMask.setText("Máscara: ");
	                labelResultBinary.setText("Máscara Binária: ");
	                labelIpsDisponiveis.setText("IPs Disponíveis: ");
	                labelPrimeiroIp.setText("Primeiro IP Válido: ");
	                labelUltimoIp.setText("Último IP Válido: ");
	                labelBroadcast.setText("IP de Broadcast: ");
	                labelSubRedes.setText("Número de Sub-redes: ");
	                textAreaSubnets.setText(""); // Clear JTextArea
	                labelError.setText("");
	            }
	        });

	        tela.setVisible(true);
	    }
	 private boolean isValidIp(String ip) {
	        if (ip == null || ip.isEmpty()) return false;
	        String[] octetos = ip.split("\\.");
	        if (octetos.length != 4) return false;

	        try {
	            for (String octeto : octetos) {
	                int valor = Integer.parseInt(octeto);
	                if (valor < 0 || valor > 255) return false;
	            }
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> new TelaConversorIp().criarTela());
	    }

	}
