
public class Casa {
	private int qtdCasas = 0;
	private int qtdHoteis = 0;
	private int x;
	private int y;
	private int id;
	private int qtdEntrou=0;
	private int valor;
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
}
