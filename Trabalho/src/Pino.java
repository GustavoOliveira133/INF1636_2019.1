import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pino {
	private Image i=null;
	private Casa c;
	
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
		if (c.qtdPinos()==0)
			return c.getXCasa();
		else if(c.qtdPinos()==1)
			return (c.getXCasa()+35);
		else if (c.qtdPinos()==2)
			return (c.getXCasa()+70);
		else if (c.qtdPinos()==3)
			return c.getXCasa();
		else if (c.qtdPinos()==4)
			return (c.getXCasa()+35);
		else
			return (c.getXCasa()+70);
	}
	public int getYPino() {
		if (c.qtdPinos()==0)
			return c.getYCasa();
		else if(c.qtdPinos()==1)
			return c.getYCasa();
		else if (c.qtdPinos()==2)
			return c.getYCasa();
		else if (c.qtdPinos()==3)
			return (c.getYCasa()+35);
		else if (c.qtdPinos()==4)
			return (c.getYCasa()+35);
		else
			return (c.getYCasa()+35);
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
	public int getCasaPino() {
		return c.getIDCasa();
	}
}
