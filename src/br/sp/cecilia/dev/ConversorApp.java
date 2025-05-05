package br.sp.cecilia.dev;

import br.sp.cecilia.dev.model.Ip;

public class ConversorApp {

	public static void main(String[] args) {
		Ip ip = new Ip();
		
		System.out.println("Calculadora de IP's");
		ip.setIp("192.168.0.0");
		System.out.println("IP: " + ip.getIp());
		ip.setMask(16);
		System.out.println("Máscara: /" + ip.getMask());
		System.out.println("Decimal: " + ip.cidrToMask());
		System.out.println("Binário: " + ip.maskToBinary());
		System.out.println("Classe: " + ip.classificarIp());
		System.out.println("Hosts: " + ip.calcularIPsDisponiveis());
		System.out.println("Sub-redes: " + ip.calcularSubredes(16, 8));

	}

}
