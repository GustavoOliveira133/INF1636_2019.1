import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pino {
	private Image i=null;
	private Casa c;
	private int x;
	private int y;
	
	public Pino(String s,Casa c) {
		this.c=c;
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
	public int getXPino() {
		return c.getXCasa();
	}
	public int getYPino() {
		return c.getYCasa();
	}
	public void pinoEntrouCasa() {
		c.aumentaPinos();
	}
	public void pinoSaiuCasa() {
		c.diminuiPinos();
	}
	public void pinoMudouCasa(Casa c) {
		this.c=c;
	}
	public int quantidadePinos() {
		return c.qtdPinos();
	}
}
