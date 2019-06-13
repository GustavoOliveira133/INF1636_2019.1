package gui;
import javax.swing.*;
import regras.*;
import java.awt.event.*;
import java.util.Scanner;

public class Menu extends JPanel {
	private Menu m=this;
	/*private JButton jogador2=new JButton("2 jogadores");
	private JButton jogador3=new JButton("3 jogadores");
	private JButton jogador4=new JButton("4 jogadores");
	private JButton jogador5=new JButton("5 jogadores");
	private JButton jogador6=new JButton("6 jogadores");*/
	private JButton dados=new JButton("Rolar os dados");
	private JButton turno=new JButton("Passar o turno");
	/*JLabel inicio = new JLabel("Escolha a quantidade de jogadores:");*/
	JLabel acoes = new JLabel("Ações disponíveis para o turno:");
	JButton terreno = new JButton("Comprar propriedade");
	JButton salvar = new JButton("Salvar");
	JButton bcasa = new JButton("Construir Casa");
	JButton hotel = new JButton("Contruir Hotel");
	private int d[]=new int [2];
	private static Fachada ctrl=Fachada.getFachada();
	
	public Menu(Tabuleiro t) {
		m.setBounds(1000, 0, 500, 500);
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
							if (p.getCasaPino().getTipo()==1) { //casa eh um terreno
								Terreno casa=(Terreno) p.getCasaPino();
								p.tiraSaldo(casa.getValor()); //tira o saldo do pino, equivalente ao valor da casa
								casa.mudaDono(p.getPinoId()); //coloca o novo dono da casa
								p.aumentaCorTerreno(casa.getCor()); //aumenta a quantidade da cor dessa casa que o pino eh dono
								String msg=String.format("Compra feita! Novo saldo: %d",p.getSaldo());
				     			JOptionPane.showMessageDialog(t,msg);
								m.atualizaBotoes(ctrl, t);
								t.repaint();
							}
							else { //casa eh uma empresa
								Empresa casa=(Empresa) p.getCasaPino();
								p.tiraSaldo(casa.getValor()); //tira o saldo do pino, equivalente ao valor da casa
								casa.mudaDono(p.getPinoId()); //coloca o novo dono da casa
								String msg=String.format("Compra feita! Novo saldo: %d",p.getSaldo());
				     			JOptionPane.showMessageDialog(t,msg);
								m.atualizaBotoes(ctrl, t);
								t.repaint();
							}
						}
					}
			  }
		} );
		
		//Cria um ActionListener para o botao "construir casa"
		bcasa.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				//Janela de dalogo para confirmar se encerrar o turno
				Pino p = t.getPinoDaVez();
				if (p.getCasaPino().getTipo()==1) {
					Terreno estaCasa= (Terreno) p.getCasaPino();
					Object[] options = { "Confirmar", "Cancelar" };
					String msg1=String.format("Deseja construir uma casa por R$%d?", estaCasa.getValorAluguel());
					int opcao = JOptionPane.showOptionDialog(t, msg1, "Confirmar construir casa", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(opcao==0) {
						//Constroi a casa
						estaCasa.construiuCasa();
						p.tiraSaldo(estaCasa.getValorAluguel());
						JOptionPane.showMessageDialog(t,"Casa construida com sucesso");
						bcasa.setEnabled(false);
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
  					
  					/* Escolhe dois valores para os dados */
  					/*
  					Scanner s = new Scanner( System.in );
  					System.out.printf("Entre com 2 valores para os dados");
  					d[0] = s.nextInt()-1;
  					d[1] = s.nextInt()-1;
  					System.out.printf("Valor rolado nos dados:%d\n",d[0]+d[1]+2);
  				    //chama o metodo na Classe Tabuleiro, que ira setar as flags e repetidos e mandar repaint
  					t.clicouNosDados(d[0],d[1]);
  					m.atualizaBotoes(ctrl,t);
  					dados.setEnabled(false);
  					*/
  					
  					//Rola os dois dados e guarda o resultado
  					
  					d[0]=Dados.rolaDados();
  					d[1]=Dados.rolaDados();
  					System.out.printf("Valor rolado nos dados:%d\n",d[0]+d[1]+2);
  					//chama o metodo na Classe Tabuleiro, que ira setar as flags e repetidos e mandar repaint
  					t.clicouNosDados(d[0],d[1]);
  					m.atualizaBotoes(ctrl,t);
  					dados.setEnabled(false);
  					
  				}
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
	
	public void atualizaBotoes(Fachada ctrl, Tabuleiro t) {
		
		Pino p = t.getPinoDaVez();
		acoes.setBounds(150, 20, 300, 20);
		dados.setBounds(50,100,150,40);
		turno.setBounds(50, 200, 150, 40);
		terreno.setBounds(50,300,150,40);
		salvar.setBounds(50, 400, 150, 40);
		bcasa.setBounds(250,100,150,40);
		hotel.setBounds(250,200,150,40);
		salvar.setEnabled(false);
		bcasa.setEnabled(false);
		hotel.setEnabled(false);
		terreno.setEnabled(false);
		
		this.add(bcasa);
		this.add(hotel);
		this.add(salvar);
		this.add(acoes);
		this.add(dados);
		this.add(turno);
		this.add(terreno);
		//Verifica se o botao de comprar terreno esta habilitado ou nao
		System.out.printf("Pino da vez: %d, casa: %d, tipo casa: %d, dono casa: %d\n",ctrl.getVez(), p.getCasaPino().getIDCasa(),p.getCasaPino().getTipo(),p.getCasaPino().getDono());
		if ((p.getCasaPino().getTipo()==1 && p.getCasaPino().getDono() == -1) || (p.getCasaPino().getTipo()==2 && p.getCasaPino().getDono() == -1)) {
			terreno.setEnabled(true);
		}
		/* verifica se pino pode construir casa */
		if ((p.getCasaPino().getTipo()==1) && (p.getCasaPino().getDono()==p.getPinoId())) {
			Terreno estaCasa = (Terreno) p.getCasaPino();
			int cor = estaCasa.getCor();
			if (p.getQtdCorTerreno(cor)>=3) {
				bcasa.setEnabled(true);
			}
		}
		
		this.repaint();
		this.revalidate();
	}
	
	
}