package gui;
import javax.swing.*;
import regras.*;

import java.awt.Font;
import java.awt.event.*;
import java.util.Scanner;

public class Menu extends JPanel {
	private Menu m=this;
	private JButton dados=new JButton("Rolar os dados");
	private JButton turno=new JButton("Passar o turno");
	JLabel acoes = new JLabel("Ações disponíveis para o turno:");
	JButton propriedade = new JButton("Comprar propriedade");
	JButton vender = new JButton("Vender propriedade");
	JButton bcasa = new JButton("Construir Casa");
	JButton hotel = new JButton("Contruir Hotel");
	private int d[]=new int [2];
	private static Fachada ctrl=Fachada.getFachada();
	
	
	public Menu(Tabuleiro t) {

		m.setBounds(1000, 0, 500, 500);
		this.add(acoes);
		acoes.setBounds(80, 20, 350, 20);

		this.add(hotel);
		hotel.setBounds(250,200,160,40);

		this.add(vender);
		vender.setBounds(250, 300, 160, 40);

		this.add(dados);
		dados.setBounds(50,100,160,40);

		this.add(turno);
		turno.setBounds(50, 200, 160, 40);

		this.add(propriedade);
		propriedade.setBounds(50,300,160,40);

		this.add(bcasa);
		bcasa.setBounds(250,100,160,40);
		
		acoes.setFont(new Font("Verdana",1,18));
		acoes.setVisible(false);
		turno.setVisible(false);
		dados.setVisible(false);
		vender.setVisible(false);
		bcasa.setVisible(false);
		hotel.setVisible(false);
		propriedade.setVisible(false);
		//Cria um ActionListener para o botao "vender propriedade"
		vender.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  Object[] options = { "Confirmar", "Cancelar" };
					int opcao = JOptionPane.showOptionDialog(t, "Deseja vender esta propriedade?", "Confirmar venda", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(opcao==0) {
						Pino p = t.getPinoDaVez();
						if ((p.getCasaPino().getValor()) - (p.getCasaPino().getValor()) < 0) {
							JOptionPane.showMessageDialog(m,"Saldo insuficiente");
						}
						else {
							if (p.getCasaPino().getTipo()==1) { //casa eh um terreno
								Terreno casa=(Terreno) p.getCasaPino();
								int valor = casa.getValor() + (casa.getQtdCasas()*casa.getValorConstroiCasa()) + (casa.getQtdHoteis()*casa.getValorConstroiHotel());
								p.aumentaSaldo((valor*90)/100); //vende por 90% do valor da casa + casas + hoetel
								casa.mudaDono(-1); //coloca a casa sem dono
								p.diminuiCorTerreno(casa.getCor()); //diminui a quantidade da cor dessa casa que o pino eh dono
								casa.zeraCasasHotel();//zera a quantidade de casas/hotel nessa casa
								String msg=String.format("Venda feita! Novo saldo: %d",p.getSaldo());
				     			JOptionPane.showMessageDialog(t,msg);
								m.atualizaBotoes(ctrl, t);
								t.repaint();
							}
							else { //casa eh uma empresa
								Empresa casa=(Empresa) p.getCasaPino();
								p.aumentaSaldo((casa.getValor()*90)/100); //aumenta o saldo do pino, equivalente a 90% do valor da casa
								casa.mudaDono(-1); //coloca o novo dono da casa
								String msg=String.format("Venda feita! Novo saldo: %d",p.getSaldo());
				     			JOptionPane.showMessageDialog(t,msg);
								m.atualizaBotoes(ctrl, t);
								t.repaint();
							}
						}
					}
			  }
		} );
		
		//Cria um ActionListener para o botao "comprar propriedade"
				propriedade.addActionListener(new ActionListener() { 
					  public void actionPerformed(ActionEvent e) {
						  Object[] options = { "Confirmar", "Cancelar" };
							 int opcao = JOptionPane.showOptionDialog(t, "Deseja comprar esta propriedade?", "Confirmar compra", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
							if(opcao==0) {
								Pino p = t.getPinoDaVez();
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
				} );
		
		//Cria um ActionListener para o botao "construir casa"
		bcasa.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				Pino p = t.getPinoDaVez();
				if (p.getCasaPino().getTipo()==1) {
					Terreno estaCasa= (Terreno) p.getCasaPino();
					Object[] options = { "Confirmar", "Cancelar" };
					String msg1=String.format("Deseja construir uma casa por R$%d?", estaCasa.getValorConstroiCasa());
					int opcao = JOptionPane.showOptionDialog(t, msg1, "Confirmar construir casa", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(opcao==0) {
						//Constroi a casa
						estaCasa.construiuCasa();
						p.tiraSaldo(estaCasa.getValorConstroiCasa());
						JOptionPane.showMessageDialog(t,"Casa construida com sucesso");
						p.setConstruiuCasa(); //mostra que construiu casa (so vai poder construir outra, no proximo turno)
						bcasa.setEnabled(false);
						t.repaint();
					}
				}
			  }
		} );
		
		//Cria um ActionListener para o botao "construir hotel"
		hotel.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				Pino p = t.getPinoDaVez();
				if (p.getCasaPino().getTipo()==1) {
					Terreno estaCasa= (Terreno) p.getCasaPino();
					Object[] options = { "Confirmar", "Cancelar" };
					String msg1=String.format("Deseja construir um hotel por R$%d?", estaCasa.getValorConstroiHotel());
					int opcao = JOptionPane.showOptionDialog(t, msg1, "Confirmar construir hotel", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(opcao==0) {
						//Constroi o hotel
						estaCasa.construiuHotel();
						p.tiraSaldo(estaCasa.getValorConstroiHotel());
						JOptionPane.showMessageDialog(t,"Hotel construido com sucesso");
						hotel.setEnabled(false);
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
					turno.setEnabled(false);
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
  					Pino p = t.getPinoDaVez();
  					p.unsetConstruiuCasa(); //pino pode voltar a construir casa
  					
  					
  					/* Escolhe dois valores para os dados */
  					/*
  					Scanner s = new Scanner( System.in );
  					System.out.printf("Entre com 2 valores para os dados");
  					d[0] = s.nextInt()-1;
  					d[1] = s.nextInt()-1;
  					System.out.printf("Valor rolado nos dados:%d\n",d[0]+d[1]+2);
  				    //chama o metodo na Classe Tabuleiro, que ira setar as flags e repetidos e mandar repaint
  					int c = t.clicouNosDados(d[0],d[1]); //se c = 1, quer dizer que o jogador faliu e o botao de dados nao eh desabilitado
  					m.atualizaBotoes(ctrl,t);
  					if (c!=1)
  						dados.setEnabled(false);
  					*/
  		
  					//Rola os dois dados e guarda o resultado
  					
  					d[0]=Dados.rolaDados();
  					d[1]=Dados.rolaDados();
  					System.out.printf("Valor rolado nos dados:%d\n",d[0]+d[1]+2);
  					//chama o metodo na Classe Tabuleiro, que ira setar as flags e repetidos e mandar repaint
  					int c = t.clicouNosDados(d[0],d[1]); //se c = 1, quer dizer que o jogador faliu e o botao de dados nao eh desabilitado
  					m.atualizaBotoes(ctrl,t);
  					if (c!=1)
  						dados.setEnabled(false);
  						turno.setEnabled(true);
  					
  				}
			  }
		} );
	
		
		addMouseListener(new MouseListener() {
    		public void mouseEntered(MouseEvent e) {}
    		public void mousePressed(MouseEvent e) {}
    		public void mouseReleased(MouseEvent e) {}
    		public void mouseExited(MouseEvent e) {}
    		public void mouseClicked(MouseEvent e) {
/* **** Mostrar coordenadas ***********  */
    			int x=e.getX();
    			int y=e.getY();
    			String msg=String.format("x=%d y=%d\n",x,y);
     			JOptionPane.showMessageDialog(m,msg);
    		}
    	});
	
	}
	
	public void atualizaBotoes(Fachada ctrl, Tabuleiro t) {
		Pino p = t.getPinoDaVez();
		turno.setEnabled(false);
		vender.setEnabled(false);
		bcasa.setEnabled(false);
		hotel.setEnabled(false);
		propriedade.setEnabled(false);
		/*
		this.add(bcasa);
		this.add(hotel);
		this.add(salvar);
		this.add(acoes);
		this.add(dados);
		this.add(turno);
		this.add(terreno);
		*/
		//Verifica se o botao de comprar terreno esta habilitado ou nao
		System.out.printf("Pino da vez: %d, casa: %d, tipo casa: %d, dono casa: %d\n",ctrl.getVez(), p.getCasaPino().getIDCasa(),p.getCasaPino().getTipo(),p.getCasaPino().getDono());
		if ((p.getCasaPino().getTipo()==1 && p.getCasaPino().getDono() == -1) || (p.getCasaPino().getTipo()==2 && p.getCasaPino().getDono() == -1)) {
			propriedade.setEnabled(true);
			turno.setEnabled(true);

		}
		/* verifica se pino pode construir casa */
		if ((p.getCasaPino().getTipo()==1) && (p.getCasaPino().getDono()==p.getPinoId())) {
			Terreno estaCasa = (Terreno) p.getCasaPino();
			int cor = estaCasa.getCor();
			if (p.getQtdCorTerreno(cor)>=3) {
				if (estaCasa.getQtdCasas()<4) {
					if (p.getConstruiuCasa()==false) {
						bcasa.setEnabled(true);
						turno.setEnabled(true);

					}
				}		
				else {
					if (estaCasa.getQtdHoteis()<1) {
						hotel.setEnabled(true);	
						turno.setEnabled(true);

					}
				}
					
			}
		}
		
		/* verifica se pino pode vender casa */
		if (((p.getCasaPino().getTipo()==1) || (p.getCasaPino().getTipo()==2)) && (p.getCasaPino().getDono()==p.getPinoId())) {
			vender.setEnabled(true);
			turno.setEnabled(true);

		}
		
		
		this.repaint();
		this.revalidate();
	}
	public void exibeBotoes() {
		turno.setVisible(true);
		dados.setVisible(true);
		vender.setVisible(true);
		bcasa.setVisible(true);
		hotel.setVisible(true);
		propriedade.setVisible(true);
		acoes.setVisible(true);
	}

}