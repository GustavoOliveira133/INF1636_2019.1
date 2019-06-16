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
	private int jogada=0; //campo para verificar quantas tentativas o pino fez para tentar sair da prisao
	private String cor; 
	private boolean temCartaPrisao = false;
	private boolean mostraInformacoes = false; //mostra informacoes no tabuleiro do pino selecionado (clickado)
	private int id;
	private int saldo = 2458;
	private Image i=null;
	private int x; //posicao x que o pino se encontra
	private int y; //posicao y que o pino se encontra
	private boolean jaConstruiuCasa=false; //campo para verificar se o pino ja construi casa (no terreno) nesse turno
	private Casa c;
	private boolean foiParaPrisao = false;
	private int[] donoCor = {0,0,0,0,0,0}; //campo para a quantidade de terrenos da mesma cor que o pino é dono, sendo:
	/*
	 * donoCor[0] = laranja
	 * donoCor[1] = vermelho
	 * donoCor[2] = amarelo
	 * donoCor[3] = roxo
	 * donoCor[4] = azul
	 * donoCor[5] = verde
	 */
	
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
		if (c.getQtdPinosEntrou()==0) {
			x=c.getXCasa();
			return c.getXCasa();
		}
		else if(c.getQtdPinosEntrou()==1) {
			x=c.getXCasa()+35;
			return (c.getXCasa()+35);
		}
		else if (c.getQtdPinosEntrou()==2) {
			x=c.getXCasa()+70;
			return (c.getXCasa()+70);
		}
		else if (c.getQtdPinosEntrou()==3) {
			x=c.getXCasa();
			return c.getXCasa();
		}

		else if (c.getQtdPinosEntrou()==4) {
			x=c.getXCasa()+35;
			return (c.getXCasa()+35);
		}
		else {
			x=c.getXCasa()+70;
			return (c.getXCasa()+70);
		}
	}
	public int getYPino() {
		if (c.getQtdPinosEntrou()==0) {
			y=c.getYCasa();
			return c.getYCasa();
		}	
		else if(c.getQtdPinosEntrou()==1) {
			y=c.getYCasa();
			return c.getYCasa();
		}
			
		else if (c.getQtdPinosEntrou()==2) {
			y=c.getYCasa();
			return c.getYCasa();
		}
		
		else if (c.getQtdPinosEntrou()==3) {
			y=c.getYCasa()+35;
			return (c.getYCasa()+35);
		}
			
		else if (c.getQtdPinosEntrou()==4) {
			y=c.getYCasa()+35;
			return (c.getYCasa()+35);
		}
			
		else {
			y=c.getYCasa()+35;
			return (c.getYCasa()+35);
		}
	}
	public int xPino() {
		return x;
	}
	public int yPino() {
		return y;
	}
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
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
	public boolean verificaCartaPrisao() {
		return temCartaPrisao;
	}
	public void aumentaQtdJogadas() {
		jogada++;
	}
	public void zeraJogadas() {
		jogada=0;
	}
	public int getQtdJogadas() {
		return jogada;
	}
	public void aumentaCorTerreno(int i) {
		donoCor[i]++;
	}
	public int getQtdCorTerreno(int i) {
		return donoCor[i];
	}
	public void setConstruiuCasa() {
		jaConstruiuCasa=true;
	}
	public void unsetConstruiuCasa() {
		jaConstruiuCasa=false;
	}
	public boolean getConstruiuCasa() {
		return jaConstruiuCasa;
	}
	public boolean getMostraInfo() {
		return mostraInformacoes;
	}
	public void setMostraInfo() {
		mostraInformacoes=true;
	}
	public void unsetMostraInfo() {
		mostraInformacoes=false;
	}
}
