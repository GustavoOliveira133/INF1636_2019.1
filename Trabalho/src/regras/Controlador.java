package regras;

class Controlador {
	private int quantidadeJogadores;
	private int vez=-1;
	private int[] jogadoresFalidos;
	
	public void setJogadores(int j) {
		vez = 1;
		quantidadeJogadores=j;
		
		jogadoresFalidos = new int[quantidadeJogadores];
		for (int i=0;i<quantidadeJogadores;i++) {
			jogadoresFalidos[i]=0;
		}
	}
	public int getJogadores() {
		return quantidadeJogadores;
	}
	public int getVez() {
		return vez;
	}
	public void acabouTurno() {
		vez++;
		if (vez>quantidadeJogadores) {
			vez=1;
		}
		while (jogadoresFalidos[vez-1]==1) {
			vez++;	
			if (vez>quantidadeJogadores) {
				vez=1;
			}
		}
	}
	public void jogadorFaliu(int i) {
		jogadoresFalidos[i]=1;
	}
	public int getJogadorFalido (int i) {
		return jogadoresFalidos[i];
	}
}
