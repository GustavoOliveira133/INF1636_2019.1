
package gui;
import javax.swing.*;
import regras.*;

import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;
import java.util.Scanner;

public class Menu extends JPanel {
	private Menu m=this;
	private boolean telaInicio = true;
	private JButton dados=new JButton("Rolar os dados");
	private JButton turno=new JButton("Passar o turno");
	JLabel acoes = new JLabel("A��es dispon�veis para o turno:");
	JButton propriedade = new JButton("Comprar propriedade");
	JButton vender = new JButton("Vender propriedade");
	JButton bcasa = new JButton("Construir Casa");
	JButton hotel = new JButton("Contruir Hotel");
	JButton salvar = new JButton("Salvar Jogo");
	private JButton jogador2=new JButton("2 jogadores");
	private JButton jogador3=new JButton("3 jogadores");
	private JButton jogador4=new JButton("4 jogadores");
	private JButton jogador5=new JButton("5 jogadores");
	private JButton jogador6=new JButton("6 jogadores");
	JLabel inicio = new JLabel("Escolha a quantidade de jogadores:");
	JButton acabar = new JButton("Acabar Jogo");
	JButton carregar = new JButton("Carregar Jogo");
	JLabel entreComValor = new JLabel ("Entre com o valor dos dados");
	JButton ok = new JButton("Ok");
	JButton aleatorio = new JButton("Aleatorio");
	private int d[]=new int [2];
	private static Fachada ctrl=Fachada.getFachada();
	
	public Menu(Tabuleiro t) {
		m.setBounds(1000, 0, 500, 1000);
		
		String[] vm={"1","2","3","4","5","6"};
		JComboBox valores = new JComboBox(vm);
		JComboBox valores2 = new JComboBox(vm);
		aleatorio.setLayout(null);
		valores.setLayout(null);
		valores.setBounds(50,850,100,40);
		valores2.setLayout(null);
		valores2.setBounds(165,850,100,40);
		aleatorio.setBounds(50,900,150,40);
		entreComValor.setFont(new Font("Verdana",1,15));
	  	ok.setLayout(null);
	  	entreComValor.setLayout(null);
	  	entreComValor.setBounds(50,800,400,50);
	  	ok.setBounds(300, 850, 50, 40);
	  	m.add(aleatorio);
	    m.add(entreComValor);
	    m.add(ok);
	    m.add(valores);
	    m.add(valores2);
	    entreComValor.setEnabled(false);
	    ok.setEnabled(false);
		valores.setEnabled(false);
		valores2.setEnabled(false);
		aleatorio.setEnabled(false);
	    
		//muda a fonte e seu tamanho do JLabel (texto que fala que eh inicio do jogo)
		inicio.setFont(new Font("Verdana",1,20));
		inicio.setBounds(30, 0, 400, 100);
		m.add(inicio);
		//ajustando as posi��es dos componentes do menu
		jogador2.setBounds(50, 80,150, 40);
		jogador3.setBounds(260, 80,150, 40);
		jogador4.setBounds(50, 160,150, 40);
		jogador5.setBounds(260, 160,150, 40);
		jogador6.setBounds(150, 240,150, 40);
		

		this.add(acabar);
		acabar.setBounds(150, 410,150, 50);
		
		this.add(carregar);
		carregar.setBounds(150, 550,150, 60);
		
		this.add(salvar);
		salvar.setBounds(150,700,150,60);
			
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
		acabar.setVisible(false);
		acoes.setVisible(false);
		salvar.setVisible(false);
		turno.setVisible(false);
		dados.setVisible(false);
		vender.setVisible(false);
		bcasa.setVisible(false);
		hotel.setVisible(false);
		propriedade.setVisible(false);
		
		//adiciona o botao para escolher 2 jogadores
		this.add(jogador2);
		//adiciona o action listener no botao 2 jogadores
		jogador2.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  //guarda em ctrl a quantidade de jogadores
				  ctrl.setJogadores(2);
				  t.criaPinos();
				  m.atualizaBotoess(ctrl,t);
				  telaInicio = false;
			  } 
			} );
		//repete o mesmo processo do botao 2 jogadoes, para os outros botoes
		this.add(jogador3);

		jogador3.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  ctrl.setJogadores(3);
				  t.criaPinos();
				  m.atualizaBotoess(ctrl,t);
				  telaInicio = false;
			  } 
			} );
		

		this.add(jogador4);
		jogador4.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  ctrl.setJogadores(4);
				  t.criaPinos();
				  m.atualizaBotoess(ctrl,t);
				  telaInicio = false;} 
			} );
		

		this.add(jogador5);
		jogador5.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  ctrl.setJogadores(5);
				  t.criaPinos();
				  m.atualizaBotoess(ctrl,t);
				  telaInicio = false;} 
			} );
		
		this.add(jogador6);
		jogador6.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  ctrl.setJogadores(6);
				  t.criaPinos();
				  m.atualizaBotoess(ctrl,t);
				  telaInicio = false;} 
			} );
		
		acabar.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  String msg = ctrl.verificaGanhador();
				  JOptionPane.showMessageDialog(t,msg);
				  ctrl.novoJogo();
				  acabar.setVisible(false);
				  escondeBotoes();
				  m.insereBotoesInicio();
				  telaInicio = true;
				  t.repaint();
			  } 
			} );
		
		
		//Cria um ActionListener para o botao "vender propriedade"
		vender.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
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
							m.atualizaBotoes(ctrl, t);
							t.repaint();
						}
						else { //casa eh uma empresa
							Empresa casa=(Empresa) p.getCasaPino();
							p.aumentaSaldo((casa.getValor()*90)/100); //aumenta o saldo do pino, equivalente a 90% do valor da casa
							casa.mudaDono(-1); //coloca o novo dono da casa
							m.atualizaBotoes(ctrl, t);
							t.repaint();
						}
					}
			  }
		} );
		
		//Cria um ActionListener para o botao "comprar propriedade"
				propriedade.addActionListener(new ActionListener() { 
					  public void actionPerformed(ActionEvent e) {
							Pino p = t.getPinoDaVez();
							if (p.getCasaPino().getTipo()==1) { //casa eh um terreno
								Terreno casa=(Terreno) p.getCasaPino();
								p.tiraSaldo(casa.getValor()); //tira o saldo do pino, equivalente ao valor da casa
								casa.mudaDono(p.getPinoId()); //coloca o novo dono da casa
								p.aumentaCorTerreno(casa.getCor()); //aumenta a quantidade da cor dessa casa que o pino eh dono
								m.atualizaBotoes(ctrl, t);
								t.repaint();
							}
							else { //casa eh uma empresa
								Empresa casa=(Empresa) p.getCasaPino();
								p.tiraSaldo(casa.getValor()); //tira o saldo do pino, equivalente ao valor da casa
								casa.mudaDono(p.getPinoId()); //coloca o novo dono da casa
								m.atualizaBotoes(ctrl, t);
								t.repaint();
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
						hotel.setEnabled(false);
						t.repaint();
					}
				}
			  }
		} );
		salvar.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  try {
					ctrl.saveGame(salvar);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				salvar.setEnabled(true);		
				t.repaint();
				
			  }
		} );
		
		carregar.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  try {
					  ctrl.loadGame(carregar);
					  t.criaCasas();
					  t.criaPinos();
					  if (telaInicio==true) {
						  m.atualizaBotoess(ctrl,t);
						  turno.setEnabled(false);
						  telaInicio = false;
						  dados.setEnabled(true);
					  }
					  else {
						  m.atualizaBotoes(ctrl, t);
						  turno.setEnabled(false);
						  dados.setEnabled(true);
					  }
				  } catch (IOException e1) {
					  e1.printStackTrace();
				  }

			  } 
			} );
		
		//Cria um ActionListener para o botao "passar o turno"
		turno.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				
					//Encerra o turno
					ctrl.acabouTurno();
					m.atualizaBotoes(ctrl,t);
					dados.setEnabled(true);
					turno.setEnabled(false);
					salvar.setEnabled(true);
					t.repaint();
				
			  }
		} );
		
		ok.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				int d1 = Integer.parseInt((String)valores.getSelectedItem()); 
				int d2 = Integer.parseInt((String)valores2.getSelectedItem()); 
				d[0] = d1-1;
					d[1] = d2-1; 
					System.out.printf("Valor rolado nos dados:%d\n",d[0]+d[1]+2);
				    //chama o metodo na Classe Tabuleiro, que ira setar as flags e repetidos e mandar repaint
					int c = t.clicouNosDados(d[0],d[1]); //se c = 1, quer dizer que o jogador faliu e o botao de dados nao eh desabilitado
					m.atualizaBotoes(ctrl,t);
					if (c==0) {
						dados.setEnabled(false);
						turno.setEnabled(true);
						salvar.setEnabled(false);
					}
					else if (c==2) {
					  String msg = ctrl.verificaGanhador();
					  JOptionPane.showMessageDialog(t,msg);
					  ctrl.novoJogo();
					  acabar.setVisible(false);
					  escondeBotoes();
					  m.insereBotoesInicio();
					  telaInicio=true;
					  t.repaint();
					}
					else if (c==1) {
						turno.setEnabled(false);
					}
					ok.setEnabled(false);
					valores.setEnabled(false);
					valores2.setEnabled(false);
					aleatorio.setEnabled(false);
			  }
		} );
		
		aleatorio.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
					//Rola os dois dados e guarda o resultado
					d[0]=Dados.rolaDados();
					d[1]=Dados.rolaDados();
					System.out.printf("Valor rolado nos dados:%d\n",d[0]+d[1]+2);
					//chama o metodo na Classe Tabuleiro, que ira setar as flags e repetidos e mandar repaint
					int c = t.clicouNosDados(d[0],d[1]); //se c = 1, quer dizer que o jogador faliu e o botao de dados nao eh desabilitado
					m.atualizaBotoes(ctrl,t);
					if (c==0) {
						dados.setEnabled(false);
						turno.setEnabled(true);
						salvar.setEnabled(false);
					}
					else if (c==2) {
					  String msg = ctrl.verificaGanhador();
					  JOptionPane.showMessageDialog(t,msg);
					  ctrl.novoJogo();
					  acabar.setVisible(false);
					  telaInicio=true;
					  escondeBotoes();
					  m.insereBotoesInicio();
					  t.repaint();
					}
					else if (c==1) {
						turno.setEnabled(false);
					}
					ok.setEnabled(false);
					valores.setEnabled(false);
					valores2.setEnabled(false);
					aleatorio.setEnabled(false);
			  }
		} );
		
		//Cria um ActionListener para o botao "rolar os dados"
		dados.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  Pino p = t.getPinoDaVez();
				  p.unsetConstruiuCasa(); //pino pode voltar a construir casa
				  aleatorio.setEnabled(true);
				  valores.setEnabled(true);
				  valores2.setEnabled(true);
				    entreComValor.setEnabled(true);
				    ok.setEnabled(true);
  				    m.repaint();
  					m.revalidate();
  				}
			  
		} );
	
		

	
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
		salvar.setVisible(true);
	}
	public void escondeBotoes() {
		turno.setVisible(false);
		dados.setVisible(false);
		vender.setVisible(false);
		bcasa.setVisible(false);
		hotel.setVisible(false);
		propriedade.setVisible(false);
		acoes.setVisible(false);
		salvar.setVisible(false);
	}
	public void mostraBotaoDado() {
		dados.setEnabled(true);
	}
	public void insereBotoesInicio() {
		jogador2.setVisible(true);
		jogador3.setVisible(true);
		jogador4.setVisible(true);
		jogador5.setVisible(true);
		jogador6.setVisible(true);
		inicio.setVisible(true);
		acabar.setVisible(false);
	}
	public void atualizaBotoess(Fachada ctrl, Tabuleiro t) {
		jogador2.setVisible(false);
		jogador3.setVisible(false);
		jogador4.setVisible(false);
		jogador5.setVisible(false);
		jogador6.setVisible(false);
		inicio.setVisible(false);
		acabar.setVisible(true);
		mostraBotaoDado();
		exibeBotoes();
		atualizaBotoes(ctrl,t);

		this.repaint();
		this.revalidate(); 
	}
}