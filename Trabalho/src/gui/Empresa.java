package gui;

public class Empresa extends Casa{
	private int x;
	private int y;
	private int id;
	private int qtdEntrou=0;
	private int valor;
	private int dono=-1;
	private int tipo;
	private int multi; //valor p/ multiplicar os dados (empresas)
	
	public Empresa (int x, int y, int tipo, int id, int preco) {
		super(x,y,tipo,id,preco);
		this.x=x;
		this.y=y;
		this.tipo=tipo;
		this.id=id;
		this.valor=preco;
	}
	
	public void setMulti(int m) { //coloca o valor multi (para empresas)
		multi = m;
	}
	public int getMulti() {
		return multi;
	}
}