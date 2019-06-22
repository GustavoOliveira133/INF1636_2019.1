package regras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import gui.Casa;
import gui.Pino;
import gui.Terreno;

public class Load {
	private static Fachada ctrl=Fachada.getFachada();
	
	public static void carregaInfo (JButton carregar) throws IOException {
		int quantidadeJogadores, jogadoresFalidos[], vez;
		int id, novoSaldo, casaPino, novoX, novoY,cartaPrisao,estaPreso,donoCor[]=new int[6];
		int idCasa,tipo,dono,qtdCasas,qtdHoteis;
		
		JFileChooser fileChooser = new JFileChooser();
	    int retval = fileChooser.showSaveDialog(carregar);
	    if (retval == JFileChooser.APPROVE_OPTION) {
	      File file = fileChooser.getSelectedFile();
	      if (file == null) {
	        return;
	      }
	      if (!file.getName().toLowerCase().endsWith(".txt")) {
	        file = new File(file.getParentFile(), file.getName() + ".txt");
	      }  
	      Scanner input = null;

			try {
				input =new Scanner(new BufferedReader(new FileReader(file)));
				
				quantidadeJogadores = input.nextInt();
				//System.out.printf("Quantidade jogadores carregados:%d\n",quantidadeJogadores);
				ctrl.instanciaCasas();
				ctrl.setJogadores(quantidadeJogadores);
				
				vez = input.nextInt();
				ctrl.setVez(vez);
				//System.out.printf("vez:%d\n",vez);
				
				jogadoresFalidos = new int[quantidadeJogadores];
				for (int i=0;i<quantidadeJogadores;i++) {
					jogadoresFalidos[i] = input.nextInt();
				}
				ctrl.setJogadoresFalidos(quantidadeJogadores, jogadoresFalidos);
				
				for (int i=0;i<quantidadeJogadores;i++) {
					if (jogadoresFalidos[i]==1) {
						i++;
					}
					else {
						id = input.nextInt();
						id--;
						novoSaldo = input.nextInt();
						casaPino = input.nextInt();
						novoX = input.nextInt();
						novoY = input.nextInt();
						cartaPrisao = input.nextInt();
						estaPreso = input.nextInt();
						for (int j=0;j<6;j++) {
							donoCor[j] = input.nextInt();
						}
						ctrl.acertaValoresPino(id,novoSaldo,casaPino,novoX,novoY,cartaPrisao,estaPreso,donoCor);
					}
				}
				
				while (input.hasNextInt()) {
					qtdCasas=-1;
					qtdHoteis=-1;
					idCasa = input.nextInt();
					tipo = input.nextInt();
					dono = input.nextInt();
					if (tipo == 1) {
						qtdCasas=input.nextInt();
						qtdHoteis=input.nextInt();
					}
					ctrl.acertaValoresCasa(idCasa,tipo,dono,qtdCasas,qtdHoteis);
				}
			}	
			catch (IOException e1) {
				e1.printStackTrace();
			}
			
			finally {
				if (input != null) {
					input.close();
				}
			}
	    }
	}
}

    
