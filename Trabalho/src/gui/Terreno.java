package gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Terreno extends Casa{
	/* Cores:
	 * 0 = laranja
	 * 1 = vermelho
	 * 2 = amarelo
	 * 3 = roxo
	 * 4 = azul
	 * 5 = verde
	 */
	private int cor;
	private boolean mostraCarta=false;
	private int qtdCasas = 0;
	private int qtdHoteis = 0;
	private Image i=null;
	private int posX;
	private int posY;
	private int[] valorTotalAluguel = new int[6]; //valor total a ser pago (v[0] = aluguel, v[1] =com 1casa ... v[5] =com hotel)
	private int cadaCasa; //valor a ser pago para contruir cada casa
	private int cadaHotel;//valor a ser pago para contruir cada hotel
	private int posicao; //0 = vertical, 1=horizontal
	
	public Terreno (int x, int y, int tipo, int id, int preco, int cor) {
		super(x,y,tipo,id,preco);
		this.cor=cor;
	}
	
	public int getQtdCasas() {
		return qtdCasas;
	}
	public int getQtdHoteis() {
		return qtdHoteis;
	}
	public int getValorAluguel() {
		if (qtdCasas==0) {
			return valorTotalAluguel[0];
		}
		else if (qtdCasas==1) {
			return valorTotalAluguel[1];
		}
		else if (qtdCasas==1) {
			return valorTotalAluguel[2];
		}
		else if (qtdCasas==1) {
			return valorTotalAluguel[3];
		}
		else if (qtdCasas==1) {
			return valorTotalAluguel[4];
		}
		else {
			return valorTotalAluguel[5];
		}
		
	}
	public void setValoresTerreno(int a,int c1,int c2,int c3,int c4, int h, int cadaC, int cadaH) {
		valorTotalAluguel[0]=a;
		valorTotalAluguel[1]=c1;
		valorTotalAluguel[2]=c2;
		valorTotalAluguel[3]=c3;
		valorTotalAluguel[4]=c4;
		valorTotalAluguel[5]=h;
		cadaCasa=cadaC;
		cadaHotel=cadaH;
	}
	public int getCor() {
		return cor;
	}
	public void construiuCasa() {
		qtdCasas++;
	}
	public void construiuHotel() {
		qtdHoteis++;
	}
	public void colocaImagem(String s) {
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
	public void setXY(int x, int y, int posicao) {
		this.posicao=posicao;
		posX=x;
		posY=y;
	}
	public int getX() {
		return posX;
	}
	public int getY() {
		return posY;
	}
	public int getPos() {
		return posicao;
	}
	public void MostraCarta() {
		mostraCarta=true;
	}
	public void unsetCarta() {
		mostraCarta = false;
	}
	public boolean getMostraCarta() {
		return mostraCarta;
	}
}
