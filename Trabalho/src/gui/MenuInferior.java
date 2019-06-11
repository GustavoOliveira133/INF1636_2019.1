package gui;
import javax.swing.*;
import regras.*;
import java.awt.*;
import java.awt.event.*;

public class MenuInferior extends JPanel {
	private MenuInferior m=this;
	private JButton jogador2=new JButton("2 jogadores");
	private JButton jogador3=new JButton("3 jogadores");
	private JButton jogador4=new JButton("4 jogadores");
	private JButton jogador5=new JButton("5 jogadores");
	private JButton jogador6=new JButton("6 jogadores");
	JLabel inicio = new JLabel("Escolha a quantidade de jogadores:");
	private static Fachada ctrl=Fachada.getFachada();
	
	public MenuInferior(Tabuleiro t,Menu men) {
		m.setBounds(1000, 500, 500, 500);	
	//muda a fonte e seu tamanho do JLabel (texto que fala que eh inicio do jogo)
			inicio.setFont(new Font("Verdana",1,20));
			inicio.setBounds(30, 0, 400, 100);
			//ajustando as posições dos componentes do menu
			jogador2.setBounds(50, 80,150, 40);
			jogador3.setBounds(260, 80,150, 40);
			jogador4.setBounds(50, 160,150, 40);
			jogador5.setBounds(260, 160,150, 40);
			jogador6.setBounds(150, 240,150, 40);
			//adiciona o JLabel no painel
			m.add(inicio);
			//adiciona o botao para escolher 2 jogadores
			this.add(jogador2);
			//adiciona o action listener no botao 2 jogadores
			jogador2.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) {
					  //guarda em ctrl a quantidade de jogadores
					  ctrl.setJogadores(2);
					  t.criaPinos();
					  m.atualizaBotoess(ctrl,t,men);
				  } 
				} );
			//repete o mesmo processo do botao 2 jogadoes, para os outros botoes
			this.add(jogador3);

			jogador3.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  ctrl.setJogadores(3);
					  t.criaPinos();
					  m.atualizaBotoess(ctrl,t,men);				  } 
				} );
			

			this.add(jogador4);
			jogador4.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  ctrl.setJogadores(4);
					  t.criaPinos();
					  m.atualizaBotoess(ctrl,t,men);				  } 
				} );
			

			this.add(jogador5);
			jogador5.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  ctrl.setJogadores(5);
					  t.criaPinos();
					  m.atualizaBotoess(ctrl,t,men);				  } 
				} );
			
			this.add(jogador6);
			jogador6.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  ctrl.setJogadores(6);
					  t.criaPinos();
					  m.atualizaBotoess(ctrl,t,men);				  } 
				} );
			
			
			
			
			addMouseListener(new MouseListener() {
	    		public void mouseEntered(MouseEvent e) {}
	    		public void mousePressed(MouseEvent e) {}
	    		public void mouseReleased(MouseEvent e) {}
	    		public void mouseExited(MouseEvent e) {}
	    		public void mouseClicked(MouseEvent e) {
	/* *********** Mostrar coordenadas ****************************  */
	    			int x=e.getX();
	    			int y=e.getY();
	    			String msg=String.format("x=%d y=%d\n",x,y);
	     			JOptionPane.showMessageDialog(m,msg);
	    		}
	    	});
			
		}
	
	public void atualizaBotoess(Fachada ctrl, Tabuleiro t, Menu men) {
		remove(jogador2);
		remove(jogador3);
		remove(jogador4);
		remove(jogador5);
		remove(jogador6);
		remove(inicio);
		men.atualizaBotoes(ctrl,t);
		this.repaint();
		this.revalidate(); 
		
	}
	
}