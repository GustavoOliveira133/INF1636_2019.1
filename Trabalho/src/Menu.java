import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.util.Scanner;
public class Menu extends JPanel {
	private Menu m=this;
	private Scanner manipulaDados=new Scanner(System.in);
	private JButton jogador2=new JButton("2 jogadores");
	private JButton jogador3=new JButton("3 jogadores");
	private JButton jogador4=new JButton("4 jogadores");
	private JButton jogador5=new JButton("5 jogadores");
	private JButton jogador6=new JButton("6 jogadores");
	private JButton dados=new JButton("Rolar os dados");
	private JButton turno=new JButton("Passar o turno");
	JLabel inicio = new JLabel("Escolha a quantidade de jogadores:");
	JLabel acoes = new JLabel("Ações disponíveis para o turno:");
	JButton terreno = new JButton("Comprar propriedade");
	private int d[]=new int [2];
	
	
	public Menu(Controlador ctrl,Tabuleiro t) {
		m.setBounds(1000, 0, 500, 1000);
		//Cria um ActionListener para o botao "comprar terreno"
		terreno.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  Object[] options = { "Confirmar", "Cancelar" };
					int opcao = JOptionPane.showOptionDialog(t, "Deseja comprar esta propriedade?", "Confirmar compra", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(opcao==0) {
						Pino p = t.getPinoDaVez();
						if ((p.getCasaPino().getValor()) - (p.getCasaPino().getValor()) < 0) {
							JOptionPane.showMessageDialog(m,"Saldo insuficiente");
						}
						else {
							p.tiraSaldo(p.getCasaPino().getValor());
							p.getCasaPino().mudaDono(p.getPinoId());
							String msg=String.format("Compra feita! Novo saldo: %d",p.getSaldo());
			     			JOptionPane.showMessageDialog(t,msg);
							m.atualizaBotoes(ctrl, t);
							t.repaint();
						}
					}
			  }
		} );
		
		//Cria um ActionListener para o botao "passar o turno"
		turno.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				//Janela de dalogo para confirmar se encerrar o turno
				Object[] options = { "Confirmar", "Cancelar" };
				int opcao = JOptionPane.showOptionDialog(t, "Deseja encerrar o turno?", "Confirmar fim do turno", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(opcao==0) {
					//Encerra o turno
					ctrl.acabouTurno();
					m.atualizaBotoes(ctrl,t);
					dados.setEnabled(true);
					t.repaint();
				}
			  }
		} );
		//Cria um ActionListener para o botao "rolar os dados"
		dados.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				//Janela de dalogo para confirmar se deseja rolar os dados
  				Object[] options = { "Confirmar", "Cancelar" };
  				int opcao = JOptionPane.showOptionDialog(t, "Deseja rolar os dados e andar com o pino da vez?", "Confirmar rolar os dados", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
  				if(opcao==0) {
  					//Rola os dois dados e guarda o resultado
  					//d[0]=Dados.rolaDados();
  					//d[1]=Dados.rolaDados();
  					
  					System.out.println("Digite 2 valores de dados\n");
  					d[0]=manipulaDados.nextInt();
  					/*while (d[0]<=0 || d[0]>=6) {
  						System.out.println("Valor invalido, digite entre 1 e 6\n");
  						d[0]=manipulaDados.nextInt();
  					}*/
  					d[1]=manipulaDados.nextInt();
  					/*while (d[1]<=0 || d[1]>=6) {
  						System.out.println("Valor invalido, digite entre 1 e 6\n");
  						d[1]=manipulaDados.nextInt();
  					}*/
  					d[0]-=1;
  					d[1]-=1;
  					
  					System.out.printf("Valor rolado nos dados:%d\n",d[0]+d[1]+2);
  					//chama o metodo na Classe Tabuleiro, que ira setar as flags e repetidos e mandar repaint
  					t.clicouNosDados(d[0],d[1]);
  					m.atualizaBotoes(ctrl,t);
  					dados.setEnabled(false);
  				}
			  }
		} );
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
				  m.atualizaBotoes(ctrl,t);
			  } 
			} );
		//repete o mesmo processo do botao 2 jogadoes, para os outros botoes
		this.add(jogador3);

		jogador3.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  ctrl.setJogadores(3);
				  t.criaPinos();
				  m.atualizaBotoes(ctrl,t);
			  } 
			} );
		

		this.add(jogador4);
		jogador4.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  ctrl.setJogadores(4);
				  t.criaPinos();
				  m.atualizaBotoes(ctrl,t);
			  } 
			} );
		

		this.add(jogador5);
		jogador5.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  ctrl.setJogadores(5);
				  t.criaPinos();
				  m.atualizaBotoes(ctrl,t);
			  } 
			} );
		
		this.add(jogador6);
		jogador6.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  ctrl.setJogadores(6);
				  t.criaPinos();
				  m.atualizaBotoes(ctrl,t);
			  } 
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
	
	public void atualizaBotoes(Controlador ctrl, Tabuleiro t) {
		this.removeAll();
		
		Pino p = t.getPinoDaVez();
		acoes.setBounds(20, 20, 300, 20);
		dados.setBounds(150,100,150,40);
		turno.setBounds(150, 200, 150, 40);
		terreno.setBounds(150,300,150,40);


		this.add(acoes);
		this.add(dados);
		this.add(turno);
		//Verifica se o botao de comprar terreno esta habilitado ou nao
		terreno.setEnabled(false);
		System.out.printf("Pino da vez: %d, casa: %d, tipo casa: %d, dono casa: %d\n",ctrl.getVez(), p.getCasaPino().getIDCasa(),p.getCasaPino().getTipo(),p.getCasaPino().getDono());
		if ((p.getCasaPino().getTipo()==1 && p.getCasaPino().getDono() == -1) || (p.getCasaPino().getTipo()==2 && p.getCasaPino().getDono() == -1)) {
			terreno.setEnabled(true);
		}
		this.add(terreno);
		this.revalidate();
		this.repaint();
	}
}