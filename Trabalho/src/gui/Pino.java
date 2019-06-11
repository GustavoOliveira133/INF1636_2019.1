package gui;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pino {
	/* Pino 1 - 'Vermelho'
	 * Pino 2 - 'Azul'
	 * Pino 3 - 'Laranja'
	 * Pino 4 - 'Amarelo'
	 * Pino 5 - 'Roxo'
	 * Pino 6 - 'Cinza'	*/
	private String cor; 
	private boolean temCartaPrisao = false;
	private int id;
	private int saldo = 2458;
	private Image i=null;
	private Casa c;
	private boolean foiParaPrisao = false;
	
	public Pino(String s,Casa c, String cor,int id) {
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
	public void mudaFoiParaPrisao () {
		if (foiParaPrisao == true)
			foiParaPrisao = false;
		else
			foiParaPrisao = true;
	}
	public boolean getPrisao () {
		return foiParaPrisao;
	}
	public String getCor() {
		return cor;
	}
	public void recebeCartaPrisao() {
		temCartaPrisao=true;
	}
	public void gastaCartaPrisao() {
		temCartaPrisao=false;
	}
}
