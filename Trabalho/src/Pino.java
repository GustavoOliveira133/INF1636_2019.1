import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pino {
	/* Pino 1 - 'V'
	 * Pino 2 - 'A'
	 * Pino 3 - 'L'
	 * Pino 4 - 'M'
	 * Pino 5 - 'R'
	 * Pino 6 - 'C'	*/
	private char cor; 
	private int id;
	private int saldo = 2458;
	private Image i=null;
	private Casa c;
	
	public Pino(String s,Casa c, char cor,int id) {
		this.id = id;
		this.cor = cor;
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
		if (c.getQtdPinosEntrou()==0)
			return c.getXCasa();
		else if(c.getQtdPinosEntrou()==1)
			return (c.getXCasa()+35);
		else if (c.getQtdPinosEntrou()==2)
			return (c.getXCasa()+70);
		else if (c.getQtdPinosEntrou()==3)
			return c.getXCasa();
		else if (c.getQtdPinosEntrou()==4)
			return (c.getXCasa()+35);
		else
			return (c.getXCasa()+70);
	}
	public int getYPino() {
		if (c.getQtdPinosEntrou()==0)
			return c.getYCasa();
		else if(c.getQtdPinosEntrou()==1)
			return c.getYCasa();
		else if (c.getQtdPinosEntrou()==2)
			return c.getYCasa();
		else if (c.getQtdPinosEntrou()==3)
			return (c.getYCasa()+35);
		else if (c.getQtdPinosEntrou()==4)
			return (c.getYCasa()+35);
		else
			return (c.getYCasa()+35);
	}

	public void pinoMudouCasa(Casa c) {
		this.c=c;
	}
	public Casa getCasaPino() {
		return c;
	}
	public int getIdCasaPino() {
		return c.getIDCasa();
	}
	public void aumentaEntrou() {
		c.entrouPino();
	}
	public void zeraEntrou() {
		c.saiuPino();
	}
	public int getSaldo() {
		return saldo;
	}
	public void tiraSaldo(int i) {
		saldo-=i;
	}
	public void aumentaSaldo(int i) {
		saldo+=i;
	}
	public int getPinoId() {
		return id;
	}
}
