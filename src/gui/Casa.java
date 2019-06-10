package gui;

public class Casa {
	private int qtdCasas = 0;
	private int qtdHoteis = 0;
	private int x;
	private int y;
	private int id;
	private int qtdEntrou=0;
	private int valor;
	private int[] valorTotalAluguel = new int[6]; //valor total a ser pago (v[0] = aluguel, v[1] =com 1casa ... v[5] =com hotel)
	private int cadaCasa; //valor a ser pago para contruir cada casa
	private int cadaHotel;//valor a ser pago para contruir cada hotel
	private int multi; //valor p/ multiplicar os dados (empresas)
	private int dono=-1;
	private int tipo;
	/* Tipos de casa: 
	 * 0 - Casa inicial
	 * 1 - Terrenos
	 * 2 - Empresa
	 * 3 - Sorte/Reves
	 * 4 - Eventos (pague x)
	 * 5 - Eventos (receba x)
	 * 6 - prisao (vá para bangu I)
	 * 7 - bangu I
	 * 8 - casa cerveja/pagode
	 */
	
	public Casa(int x, int y, int tipo, int id, int preco) {
		this.valor=preco;
		this.id=id;
		this.x=x;
		this.y=y;
		this.tipo=tipo;
	}
	
	public int getDono() {
		return dono;
	}
	public void mudaDono(int i) {
		dono = i;
	}
	public int getValor() {
		return valor;
	}
	public int getTipo() {
		return tipo;
	}
	public int getXCasa() {
		return x;
	}
	public int getYCasa() {
		return y;
	}
	public int getIDCasa() {
		return id;
	}
	public int getQtdPinosEntrou() {
		return qtdEntrou;
	}
	public void entrouPino() {
		qtdEntrou++;
	}
	public void saiuPino() {
		qtdEntrou=0;
	}
	public int getQtdCasas() {
		return qtdCasas;
	}
	public int getQtdHoteis() {
		return qtdHoteis;
	}
	public void setMulti(int m) { //coloca o valor multi (para empresas)
		multi = m;
	}
	public int getMulti() {
		return multi;
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
		valorTotalAluguel[0] = a;
		valorTotalAluguel[1]=c1;
		valorTotalAluguel[2]=c2;
		valorTotalAluguel[3]=c3;
		valorTotalAluguel[4]=c4;
		valorTotalAluguel[5]=h;
		cadaCasa=cadaC;
		cadaHotel=cadaH;
	}
}
