import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
public class Menu extends JPanel {
	private Menu m=this;
	private JButton jogador2=new JButton("2 jogadores");
	private JButton jogador3=new JButton("3 jogadores");
	private JButton jogador4=new JButton("4 jogadores");
	private JButton jogador5=new JButton("5 jogadores");
	private JButton jogador6=new JButton("6 jogadores");
	private JButton dados=new JButton("Rolar os dados");
	JLabel inicio = new JLabel("Escolha a quantidade de jogadores:");
	JLabel acoes = new JLabel("Ações disponíveis para o turno:");
	JLabel info=null;
	private int d[]=new int [2];
	
	
	public Menu(Controlador ctrl,Tabuleiro t) {
		m.setBounds(1000, 0, 500, 500);
		//Cria um ActionListener para o botao "rolar os dados"
		dados.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				//Janela de dalogo para confirmar se deseja rolar os dados
  				Object[] options = { "Confirmar", "Cancelar" };
  				int opcao = JOptionPane.showOptionDialog(t, "Deseja rolar os dados e andar com o pino da vez?", "Confirmar rolar os dados", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
  				if(opcao==0) {
  					//Rola os dois dados e guarda o resultado
  					d[0]=Dados.rolaDados();
  					d[1]=Dados.rolaDados();
  					System.out.printf("Valor rolado nos dados:%d\n",d[0]+d[1]+2);
  					//chama o metodo na Classe Tabuleiro, que ira setar as flags e repetidos e mandar repaint
  					t.clicouNosDados(d[0],d[1]);
  				}
			  }
		} );
		//muda a fonte e seu tamanho do JLabel (texto que fala que eh inicio do jogo)
		inicio.setFont(new Font("Verdana",1,20));
		//ajustando as posições dos componentes do menu
		acoes.setBounds(10, 10, 500, 100);
		inicio.setBounds(50,0, 500, 100);
		jogador2.setBounds(50, 80,150, 40);
		jogador3.setBounds(260, 80,150, 40);
		jogador4.setBounds(50, 160,150, 40);
		jogador5.setBounds(260, 160,150, 40);
		jogador6.setBounds(150, 240,150, 40);
		dados.setBounds(150,100,150,40);
		//adiciona o JLabel no painel
		m.add(inicio);
		//adiciona o botao para escolher 2 jogadores
		this.add(jogador2);
		//adiciona o action listener no botao 2 jogadores
		jogador2.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  //guarda em ctrl a quantidade de jogadores
				  ctrl.setJogadores(2);
				  //retira todos os botoes de selecao de quantidade de jogadores
				  m.removeAll();
				  m.revalidate();
				  m.repaint();
				  //chama o metodo na classe Tabuleiro que cria os pinos de acordo com a quantidade de jogadores e os coloca no tabuleiro
				  t.criaPinos();
				  /* Vai criar o JLabel que informa o turno do jogador e outra que informa as acoes disponiveis
				   adiciona o botao de rolar os dados */
				  String msg=String.format("Turno do jogador %d",ctrl.getVez());
				  info=new JLabel(msg);
				  info.setFont(new Font("Verdana",1,20));
				  acoes.setFont(new Font("Verdana",1,20));
				  m.add(info);
				  m.add(acoes);
				  m.add(dados);
			  } 
			} );
		//repete o mesmo processo do botao 2 jogadoes, para os outros botoes
		this.add(jogador3);

		jogador3.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  ctrl.setJogadores(3);
				  m.removeAll();
				  m.revalidate();
				  m.repaint();
				  t.criaPinos();
				  String msg=String.format("Turno do jogador %d",ctrl.getVez());
				  info=new JLabel(msg);
				  info.setFont(new Font("Verdana",1,20));
				  acoes.setFont(new Font("Verdana",1,20));
				  m.add(info);
				  m.add(acoes);
				  m.add(dados);
			  } 
			} );
		

		this.add(jogador4);
		jogador4.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  ctrl.setJogadores(4);
				  m.removeAll();
				  m.revalidate();
				  m.repaint();
				  t.criaPinos();
				  String msg=String.format("Turno do jogador %d",ctrl.getVez());
				  info=new JLabel(msg);
				  info.setFont(new Font("Verdana",1,20));
				  acoes.setFont(new Font("Verdana",1,20));
				  m.add(info);
				  m.add(acoes);
				  m.add(dados);
			  } 
			} );
		

		this.add(jogador5);
		jogador5.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  ctrl.setJogadores(5);
				  m.removeAll();
				  m.revalidate();
				  m.repaint();
				  t.criaPinos();
				  String msg=String.format("Turno do jogador %d",ctrl.getVez());
				  info=new JLabel(msg);
				  info.setFont(new Font("Verdana",1,20));
				  acoes.setFont(new Font("Verdana",1,20));
				  m.add(info);
				  m.add(acoes);
				  m.add(dados);
			  } 
			} );
		
		this.add(jogador6);
		jogador6.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  ctrl.setJogadores(6);
				  m.removeAll();
				  m.revalidate();
				  m.repaint();
				  t.criaPinos();
				  String msg=String.format("Turno do jogador %d",ctrl.getVez());
				  info=new JLabel(msg);
				  info.setFont(new Font("Verdana",1,20));
				  acoes.setFont(new Font("Verdana",1,20));
				  m.add(info);
				  m.add(acoes);
				  m.add(dados);
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

}