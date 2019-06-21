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
			OutputStream output = null;
			try {
				output = new FileOutputStream(file);	
				output.write(vez);
				
				for(int i=0;i<quantidadeJogadores;i++) {
					if (jogadoresFalidos[i] == 1){
						
					}
					else {
						
						output.write(pinos[i].getPinoId());
						output.write(',');
						output.write(pinos[i].getSaldo());
						output.write(',');
						output.write(pinos[i].xPino());
						output.write(',');
						output.write(pinos[i].getYPino());
						//verificar se é o dono da casa
						for(int j=0;j<36;j++) 
						{
							if(casas[j].getDono() == i && casas[j].getTipo() == 1) {
							output.write(casas[j].getIDCasa());
							output.write('.');
							output.write(((Terreno) casas[j]).getQtdCasas());	
							output.write('.');
							output.write(((Terreno) casas[j]).getQtdHoteis());
							output.write(',');}
							
						}
						if(pinos[i].verificaCartaPrisao() == true)
						{
							output.write('/');
							output.write(1);
							output.write('/');
						}
						if(pinos[i].getPrisao() == true) {
							output.write('/');
							output.write(2);
							output.write('/');
						}
						output.write(';');

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
