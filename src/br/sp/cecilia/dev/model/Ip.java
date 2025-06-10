package br.sp.cecilia.dev.model;


public class Ip {

	private String ip;
	private int cidr;
	private int bits;
	private int[] octetos = new int[4];

	//setters and getters
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp() {
		return ip;
	}
	
	public void setMask (int cidr) {
		this.cidr = cidr;
	}
	public int getMask() {
		return cidr;
	}
	
	public void setSubRede(int bits) {
		this.bits = bits;
	}
	public int getSubrede() {
		return bits;
	}
	
		//classificação de ips
	public String classificarIp() {
		String[] octeto = ip.split("\\.");
		int primeiroOcteto = Integer.parseInt(octeto[0]);

		if (primeiroOcteto >= 0 && primeiroOcteto <= 127) {
			return "Classe A";
		} else if (primeiroOcteto >= 128 && primeiroOcteto <= 191) {
			return "Classe B";
		} else if (primeiroOcteto >= 192 && primeiroOcteto <= 223) {
			return "Classe C";
		} else {
			return "Valor inválido!";
		}
	}
	
	
		//calcular máscara e tranformar o cidr em decimal
	public String cidrToMask() {
		int[] octetos = new int[4];
		int tempCidr = cidr;
		for (int i = 0; i  < 4; i++) {
			if(tempCidr >=8) {
				octetos[i] = 255;
				tempCidr -= 8;
			} else if(tempCidr > 0) {
				octetos[i] = 256 - (1 << (8 - tempCidr));
				tempCidr = 0;
			} else {
				octetos[i] = 0;
			}
			
		}
		return String.format("%d .%d .%d .%d" , octetos[0], octetos[1], octetos[2], octetos[3]);
	}
	
	//calcular máscara e tranformar o cidr em binário
	public String maskToBinary() {
		int[] octetos = new int [4];
		int tempCidr = cidr;
		for (int i = 0; i  < 4; i++) {
			if(tempCidr >=8) {
				octetos[i] = 255;
				tempCidr -= 8;
			} else if(tempCidr > 0) {
				octetos[i] = 256 - (1 << (8 - tempCidr));
				tempCidr = 0;
			} else {
				octetos[i] = 0;
			}
		}
		
		String[] octetoBinario = new String[4];
		for (int i = 0; i < 4; i++) {
			String binary = Integer.toBinaryString(octetos[i]);
			octetoBinario[i] = String.format("%8s", binary).replace(' ', '0');
			
		}
		
		return String.join(".", octetoBinario);
	}
	
		//calculo de ips disponíveis funcionando
		public int calcularIPsDisponiveis() {
			if (cidr < 0 || cidr > 32) {
				throw new IllegalArgumentException("CIDR deve estar entre 0 e 32");
			} else if (cidr == 31 || cidr == 32) {
				return 0;
			} else {
				return (int) Math.pow(2, 32 - cidr) - 2;
				}
		}
		
		//sub-redes
		 public int calcularSalto() {
		        if (cidr < 24 || cidr > 30) {
		            return 0;
		        }
		        int hostBits = 32 - cidr;
		        return (int) Math.pow(2, hostBits); // Total IPs per subnet, including network and broadcast
		    }
		

	    public String showNetwork() {
	        // Validate IP address
	        String[] octetosIp = ip.split("\\.");
	        if (octetosIp.length != 4) {
	            return "Error: Invalid IP address!";
	        }

	        try {
	            // Calculate subnet details
	            cidrToMask(); // Ensure mask is calculated
	            int ultimoOcteto = Integer.parseInt(octetosIp[3]);
	            int networkOctet = ultimoOcteto & octetos[3]; // Apply mask to get network address
	            int salto = calcularSalto();
	            int numSubRedes = calcularIPsDisponiveis();

	            StringBuilder resultado = new StringBuilder();

	            // Iterate through subnets
	            for (int i = 0; i < numSubRedes && (networkOctet + (i * salto)) <= 255; i++) {
	                int currentOctet = networkOctet + (i * salto);
	                resultado.append(String.format("\n SubRede: %d: %s.%s.%s.%d \n", 
	                    i + 1, octetosIp[0], octetosIp[1], octetosIp[2], currentOctet));

	                // List all usable IPs
	                int primeiroIp = currentOctet + 1;
	                int ultimoIp = currentOctet + salto - 2;
	                resultado.append(" IPs Disponiveis: \n");
	                for (int ip = primeiroIp; ip <= ultimoIp; ip++) {
	                    resultado.append(String.format(" - %s.%s.%s.%d \n", 
	                        octetosIp[0], octetosIp[1], octetosIp[2], ip));
	             
	                }

	                // Broadcast address
	                resultado.append(String.format("  Broadcast: %s.%s.%s.%d\n", 
	                    octetosIp[0], octetosIp[1], octetosIp[2], currentOctet + salto - 1));
	            }

	            return resultado.toString();
	        } catch (NumberFormatException e) {
	            return "Error: Invalid last octet!";
	        }
	    }
	}
		