package regras;

public class Fachada {
	Controlador ctrl;
	static Fachada f= null;
	
	private Fachada() {
		ctrl=new Controlador();
	}
	
	public static Fachada getFachada() {
		if(f==null)
			f=new Fachada();
		
		return f;
	}
	public void setJogadores(int j) {
		ctrl.setJogadores(j);
	}
	public int getJogadores() {
		return ctrl.getJogadores();
	}
	public int getVez() {
		return ctrl.getVez();
	}
	public void acabouTurno() {
		ctrl.acabouTurno();
	}
	public void jogadorFaliu(int i) {
		ctrl.jogadorFaliu(i);
	}
	public int getJogadorFalido (int i) {
		return ctrl.getJogadorFalido(i);
	}
}
