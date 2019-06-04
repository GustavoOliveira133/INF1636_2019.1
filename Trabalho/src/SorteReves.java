import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SorteReves {
	private boolean foiTirada=false;
	private int valor;
	private Image i=null;
	private int tipo; 
	/*
	 * 0 = sorte
	 * 1 = reves
	 * 2 = sorte receba de cada um
	 * 3 = saida livre prisao
	 * 4 = avanca ate o ponto de partida e recebe 200
	 * 5 = vai para prisao
	 */
	
	public SorteReves (String s, int tipo, int valor) {
		this.valor=valor;
		this.tipo=tipo; 
		try {
			i=ImageIO.read(new File(s));
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	public Image getImage() {
		return i;
	}
	
	public int valorModificarSaldo() {
		return valor;
	}
	public int getTipo() {
		return tipo;
	}
	public void setFoiTirada() {
		foiTirada = true;
	}
	public void unsetFoiTirada() {
		foiTirada = false;
	}
	public boolean getFoiTirada() {
		return foiTirada;
	}
}


