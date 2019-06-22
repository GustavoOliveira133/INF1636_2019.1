package regras;

import gui.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;


public class Save {
	
	
	public static void guardaInfo (JButton salvar, int quantidadeJogadores,int[] jogadoresFalidos,int vez,Pino[] pinos, Casa[] casas) throws IOException {
		JFileChooser fileChooser = new JFileChooser();
	    int retval = fileChooser.showSaveDialog(salvar);
	    if (retval == JFileChooser.APPROVE_OPTION) {
	      File file = fileChooser.getSelectedFile();
	      if (file == null) {
	        return;
	      }
	      if (!file.getName().toLowerCase().endsWith(".txt")) {
	        file = new File(file.getParentFile(), file.getName() + ".txt");
	      }  
	      PrintWriter output = null;

			try {
				output = new PrintWriter(new FileWriter(file));

				output.println(quantidadeJogadores);
				output.println(vez);
				
				for(int i=0;i<quantidadeJogadores;i++) {
					if (jogadoresFalidos[i] != 1){
						output.printf("%d %d %d %d ",pinos[i].getPinoId(),pinos[i].getSaldo(),pinos[i].xPino(),pinos[i].getYPino());

						if(pinos[i].verificaCartaPrisao() == true)
						{
							output.printf("/1/ ");
						}
						else {
							output.printf("/0/ ");
						}
						if(pinos[i].getPrisao() == true) {
							output.printf("/2/");
						}
						else {
							output.printf("/0/");
						}
						output.printf(";");
						output.println("");
					}
				}
				for(int i=0;i<quantidadeJogadores;i++) {
					for(int j=0;j<36;j++) {
						if(casas[j].getDono() == i+1 && casas[j].getTipo() == 1) {
							output.printf("|%d %d %d|",casas[j].getIDCasa(),((Terreno) casas[j]).getQtdCasas(),((Terreno) casas[j]).getQtdHoteis());
						}	
					}
				}
				output.println("");
				for (int i=0;i<quantidadeJogadores;i++) {
					output.printf("%d ",jogadoresFalidos[i]);
				}

			}	
			catch (IOException e1) {
				e1.printStackTrace();
			}
			
			finally {
				if (output != null) {
					output.close();
				}
			}
	    }

		
					
	}

}
