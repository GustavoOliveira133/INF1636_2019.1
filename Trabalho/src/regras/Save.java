package regras;

import gui.*;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;


public class Save {
	
	
	public static void guardaInfo (int quantidadeJogadores,int[] jogadoresFalidos,int vez,Pino[] pinos, Casa[] casas) throws IOException {
		OutputStream output = null;
		try {
			output = new FileOutputStream("src/informacoesPartida.txt");
			
			output.write('a');
			
			for(int i=0;i<3;i++) {
//				outputStream.println(pin.getTeam());
				char a = (char)quantidadeJogadores;
				output.format();
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
