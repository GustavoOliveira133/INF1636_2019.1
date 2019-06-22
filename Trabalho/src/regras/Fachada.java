package regras;
import java.io.IOException;

import javax.swing.JButton;

import gui.*;
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
	public void acertaValoresPino(int id, int saldo, int casaPino, int x, int y, int cartaPrisao, int estaPreso,int donoCor[]) {
		ctrl.acertaValoresPino(id,saldo,casaPino,x,y,cartaPrisao,estaPreso,donoCor);
	}
	public void acertaValoresCasa(int idCasa,int tipo,int dono,int qtdCasas,int qtdHoteis) {
		ctrl.acertaValoresCasa(idCasa,tipo,dono,qtdCasas,qtdHoteis);
	}
	public int getJogadores() {
		return ctrl.getJogadores();
	}
	public void setQtdJogadores(int i) {
		ctrl.setQtdJogadores(i);
	}
	public int getVez() {
		return ctrl.getVez();
	}
	public void setVez(int i) {
		ctrl.setVez(i);
	}
	public void acabouTurno() {
		ctrl.acabouTurno();
	}
	public void setJogadoresFalidos(int n, int[] falidos) {
		ctrl.setJogadoresFalidos(n, falidos);
	}
	public int jogadorFaliu(int i) {
		return ctrl.jogadorFaliu(i);
	}
	public int getJogadorFalido (int i) {
		return ctrl.getJogadorFalido(i);
	}
	public Pino[] getPinos() {
		return ctrl.getPinos();
	}
	public Casa[] getCasas() {
		return ctrl.getCasas();
	}
	public void saveGame(JButton salvar) throws IOException {
		ctrl.saveGame(salvar);
	}
	public void instanciaCasas() {
		ctrl.instanciaCasas();
	}
	public String verificaGanhador() {
		return ctrl.verificaGanhador();
	}
	public void novoJogo() {
		ctrl.novoJogo();
	}
	public void loadGame(JButton carregar) throws IOException {
		ctrl.loadGame(carregar);
	}
}
