
public class Casa {
	private int x;
	private int y;
	private int id;
	private int numPinos=0;
	private int tipo;
	
	public Casa(int x, int y, int tipo, int id) {
		this.id=id;
		this.x=x;
		this.y=y;
		this.tipo=tipo;
	}
	
	public void aumentaPinos() {
		numPinos++;
	}
	public void diminuiPinos() {
		numPinos--;
	}
	public int getXCasa() {
		return x;
	}
	public int getYCasa() {
		return y;
	}
	public int qtdPinos() {
		return numPinos;
	}
	public int getIDCasa() {
		return id;
	}
}
