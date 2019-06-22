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

				for (int i=0;i<quantidadeJogadores;i++) {
					output.println(jogadoresFalidos[i]);
				}
				for(int i=0;i<quantidadeJogadores;i++) {
					if (jogadoresFalidos[i] != 1){
						output.println(pinos[i].getPinoId());
						output.println(pinos[i].getSaldo());
						output.println(pinos[i].getCasaPino().getIDCasa());
						output.println(pinos[i].xPino());
						output.println(pinos[i].yPino());

						if(pinos[i].verificaCartaPrisao() == true)
						{
							output.println("1");
						}
						else {
							output.println("0 ");
						}
						if(pinos[i].getPrisao() == true) {
							output.println("2");
						}
						else {
							output.println("0");
						}
						for (int j=0;j<6;j++) {
							output.println(pinos[i].getQtdCorTerreno(j));
						}
					}
				}
				for(int i=0;i<quantidadeJogadores;i++) {
					for(int j=0;j<36;j++) {
						if(casas[j].getDono() == i+1 && casas[j].getTipo() == 1) {
							output.println(casas[j].getIDCasa());
							output.println(casas[j].getTipo());
							output.println(casas[j].getDono());
							output.println(((Terreno) casas[j]).getQtdCasas());
							output.println(((Terreno) casas[j]).getQtdHoteis());
						}
						else if(casas[j].getDono() == i+1 && casas[j].getTipo() == 2) {
							output.println(casas[j].getIDCasa());
							output.println(casas[j].getTipo());
							output.println(casas[j].getDono());
						}
					}
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
