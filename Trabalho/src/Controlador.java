
public class Controlador {
	private int quantidadeJogadores;
	private int vez=1;
	
	
	public void setJogadores(int j) {
		quantidadeJogadores=j;
	}
	public int getJogadores() {
		return quantidadeJogadores;
	}
	public int getVez() {
		return vez;
	}
	public void acabouTurno() {
		vez++;
		if (vez>quantidadeJogadores)
			vez=1;
	}
}
