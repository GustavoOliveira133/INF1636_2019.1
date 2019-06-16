package gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Empresa extends Casa{
	private Image i=null;
	private boolean mostraCarta=false;
	private int posX;
	private int posY;
	private int posicao; //0 = vertical, 1=horizontal
	private int multi; //valor p/ multiplicar os dados (empresas)
	
	public Empresa (int x, int y, int tipo, int id, int preco) {
		super(x,y,tipo,id,preco);
	}
	
	public void setMulti(int m) { //coloca o valor multi (para empresas)
		multi = m;
	}
	public int getMulti() {
		return multi;
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