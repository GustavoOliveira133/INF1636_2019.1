
public class Posicao {
	private int x,y,pino;
	
	public void guardaPosicao(int w, int z, int p) {
		x=w;
		y=z;
		pino=p;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
