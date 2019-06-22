package gui;
import javax.swing.*;
import regras.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.util.Random;
import javax.imageio.*;

public class Tabuleiro extends JPanel {
	private Tabuleiro t=this;
	private Image tabuleiro=null;
	private Dados d[]=new Dados[6];
	private Pino[] pinos;
	private static Fachada ctrl=Fachada.getFachada();
    private Casa casas[];
    private SorteReves cartasSR[] = new SorteReves[31];
    private static Random aleatorio =new Random();
	
	public Tabuleiro() {
		t.setBounds(0, 0, 1000, 1000);
		try {
			tabuleiro=ImageIO.read(new File("tabuleiroRJ.jpg"));
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		//Pega as casas
		casas=ctrl.getCasas();
		
		//Criando as cartas de sorte/reves
		//sorte
		cartasSR[0]=new SorteReves("chance1.png",0,25);
		cartasSR[1]=new SorteReves("sorte01.jpg",0,150);
		cartasSR[2]=new SorteReves("sorte02.jpg",0,100);
		cartasSR[3]=new SorteReves("sorte03.jpg",3,-1);
		cartasSR[4]=new SorteReves("sorte04.jpg",0,80);
		cartasSR[5]=new SorteReves("sorte05.jpg",0,100);
		cartasSR[6]=new SorteReves("sorte06.jpg",2,50);
		cartasSR[7]=new SorteReves("sorte07.jpg",0,50);
		cartasSR[8]=new SorteReves("sorte08.jpg",0,20);
		cartasSR[9]=new SorteReves("sorte09.jpg",0,25);
		cartasSR[10]=new SorteReves("sorte10.jpg",4,200);
		cartasSR[11]=new SorteReves("sorte11.jpg",0,100);
		cartasSR[12]=new SorteReves("sorte12.jpg",0,100);
		cartasSR[13]=new SorteReves("sorte13.jpg",0,45);
		cartasSR[14]=new SorteReves("sorte14.jpg",0,50);
		cartasSR[15]=new SorteReves("sorte15.jpg",0,200);
		//reves
		cartasSR[16]=new SorteReves("reves01.jpg",1,25);
		cartasSR[17]=new SorteReves("reves02.jpg",1,45);
		cartasSR[18]=new SorteReves("reves03.jpg",1,45);
		cartasSR[19]=new SorteReves("reves04.jpg",1,30);
		cartasSR[20]=new SorteReves("reves05.jpg",1,40);
		cartasSR[21]=new SorteReves("reves06.jpg",1,50);
		cartasSR[22]=new SorteReves("reves07.jpg",1,100);
		cartasSR[23]=new SorteReves("reves08.jpg",1,50);
		cartasSR[24]=new SorteReves("reves09.jpg",1,30);
		cartasSR[25]=new SorteReves("reves10.jpg",1,30);
		cartasSR[26]=new SorteReves("reves11.jpg",1,25);
		cartasSR[27]=new SorteReves("reves12.jpg",5,-1);
		cartasSR[28]=new SorteReves("reves13.jpg",1,100);
		cartasSR[29]=new SorteReves("reves14.jpg",1,50);
		cartasSR[30]=new SorteReves("reves15.jpg",1,15);
		
		
		//Criando os dados
		d[0]=new Dados("die_face_1.png");
		d[1]=new Dados("die_face_2.png");
		d[2]=new Dados("die_face_3.png");
		d[3]=new Dados("die_face_4.png");
		d[4]=new Dados("die_face_5.png");
		d[5]=new Dados("die_face_6.png");
		


		
		addMouseListener(new MouseListener() {
    		public void mouseEntered(MouseEvent e) {}
    		public void mousePressed(MouseEvent e) {}
    		public void mouseReleased(MouseEvent e) {}
    		public void mouseExited(MouseEvent e) {}
    		public void mouseClicked(MouseEvent e) {
    			//Pega as coordenadas do click do mouse e mostra na tela
    			int x=e.getX();
    			int y=e.getY(); 			
    			String msg=String.format("x=%d y=%d\n",x,y);
     			JOptionPane.showMessageDialog(t,msg);
    			
    			//Percorre o vetor de pinos e marca o pino selecionado para mostrar as infos
     			for(int i=0;i<ctrl.getJogadores();i++) {
     				if ((x >= (pinos[i].xPino()) && x <= (pinos[i].xPino()+25.0)) && (y >= (pinos[i].yPino()) && y <= (pinos[i].yPino()+35.0)) && (ctrl.getJogadorFalido(i)==0)) {
        				pinos[i].setMostraInfo();
     					repaint();
        			}
     			}
     			
     			//Percorre o vetor de terrenos e empresas e marca o terreno selecionado para mostrar as infos
     			for(int i=0;i<36;i++) {
     				if (casas[i].getTipo()==1) {
     					if (((Terreno) casas[i]).getPos()==0) { //terreno na vertical
     	  					if (x >= (((Terreno) casas[i]).getX()) && x <= (((Terreno) casas[i]).getX()+120.0) && (y >= (((Terreno) casas[i]).getY())) && y <= (((Terreno) casas[i]).getY()+92.0)) {
     	  						((Terreno) casas[i]).MostraCarta();
             					repaint();
         					}
     					}
     					else { //terreno na horizontal
     						if (x >= (((Terreno) casas[i]).getX()) && x <= (((Terreno) casas[i]).getX()+93.0) && (y >= (((Terreno) casas[i]).getY())) && y <= (((Terreno) casas[i]).getY()+120.0)) {
     	  						((Terreno) casas[i]).MostraCarta();
             					repaint();
         					}
     					}
        			}
     				else if ( casas[i].getTipo()==2) {
     					if (((Empresa) casas[i]).getPos()==0) { //empresa na vertical
     	  					if (x >= (((Empresa) casas[i]).getX()) && x <= (((Empresa) casas[i]).getX()+120.0) && (y >= (((Empresa) casas[i]).getY())) && y <= (((Empresa) casas[i]).getY()+92.0)) {
     	  						((Empresa) casas[i]).MostraCarta();
             					repaint();
         					}
     					}
     					else { //empresa na horizontal
     						if (x >= (((Empresa) casas[i]).getX()) && x <= (((Empresa) casas[i]).getX()+93.0) && (y >= (((Empresa) casas[i]).getY())) && y <= (((Empresa) casas[i]).getY()+120.0)) {
     	  						((Empresa) casas[i]).MostraCarta();
             					repaint();
         					}
     					}
     				}
     			}
    		}
    	});
	}
	
	public int clicouNosDados(int d1, int d2) { //se retornar 1, o jogador faliu
		//Pintar os dados no tabuleiro
		int pinoDaVez=ctrl.getVez()-1;
		int j=0; //vai retornar 0 se ngm falir e 2 se o jogo acabar
		
		//Pino na prisao, verifica se foi para prisao pela casa "va para prisao" e verifica se pode sair
		if ((pinos[pinoDaVez].getCasaPino().getTipo()==7) && (pinos[pinoDaVez].getPrisao()==true)) {
			if (d1 == d2) { /* dados iguais, anda normalmente */
				pinos[pinoDaVez].mudaFoiParaPrisao();
				clicouNosDados(d1,d2);
			}
			else if (pinos[pinoDaVez].verificaCartaPrisao()==true) { /* dados diferentes e tem a carta saida livre da prisao, anda */
				pinos[pinoDaVez].mudaFoiParaPrisao();
				pinos[pinoDaVez].gastaCartaPrisao();
				clicouNosDados(d1,d2);
			}
			else if (pinos[pinoDaVez].getQtdJogadas()>=4) { /* excedeu o numero de jogadas possiveis na prisao, paga (ou fali) e anda */
				if (pinos[pinoDaVez].getSaldo()<50) { //jogador faliu, eh retirado do jogo
					if (jogadorFaliu(pinoDaVez)==0) {
						j=1;
					}
					else {
						j=2; //jogo acabou
					}
				}
				else {
					pinos[pinoDaVez].mudaFoiParaPrisao();
					pinos[pinoDaVez].tiraSaldo(50);
					pinos[pinoDaVez].zeraJogadas();
					clicouNosDados(d1,d2);
				}
			}
			else { //pino precisa continuar na prisao
				d[d1].setFlag();
				d[d2].setFlag();
				pinos[pinoDaVez].aumentaQtdJogadas();
				repaint();
			}
		}
		else { //pino andou
			int casaNova = pinos[pinoDaVez].getIdCasaPino() + d1 + d2 + 2;

			if (casaNova > 35) {
				casaNova = casaNova - 36;
				pinos[pinoDaVez].aumentaSaldo(200); //passou pela casa inicial
				pinos[pinoDaVez].pinoMudouCasa(casas[casaNova]);
			}
			else if (casaNova <= 35 && casaNova!=27){
				pinos[pinoDaVez].pinoMudouCasa(casas[casaNova]);
			}
			
			if (casaNova == 27) { //casa nova = va para prisao
				JOptionPane.showMessageDialog(t,"Voce foi para a prisão");
				pinos[pinoDaVez].pinoMudouCasa(casas[9]); //pino vai pra bangu 1
				pinos[pinoDaVez].mudaFoiParaPrisao(); //indica que foi para prisao pela casa "va para prisao"
			}
			if (casaNova == 17) { //casa nova = evento receba x
				JOptionPane.showMessageDialog(t,"Voce recebeu R$200!");
				pinos[pinoDaVez].aumentaSaldo(200);
			}
			if (casaNova == 34) { //casa nova = evento pague x
				if (pinos[pinoDaVez].getSaldo()<200) { //jogador faliu, eh retirado do jogo
					if (jogadorFaliu(pinoDaVez)==0) {
						j=1;
					}
					else {
						j=2; //jogo acabou
					}
				}
				else {
					JOptionPane.showMessageDialog(t,"Voce perdeu R$200!");
					pinos[pinoDaVez].tiraSaldo(200);
				}
			}

			if (pinos[pinoDaVez].getCasaPino().getTipo()==3) { //pino foi para casa de sorte/reves
				int carta = Tabuleiro.sorteiaCarta();
				cartasSR[carta].setFoiTirada();
				if (cartasSR[carta].getTipo() == 0) { //carta sorte
					int valorModificar = cartasSR[carta].valorModificarSaldo();
					pinos[pinoDaVez].aumentaSaldo(valorModificar);
					String msg=String.format("Você teve sorte e recebeu: R$%d",valorModificar);
	     			JOptionPane.showMessageDialog(t,msg);
				}
				else if (cartasSR[carta].getTipo() == 1) { //carta reves
					int valorModificar = cartasSR[carta].valorModificarSaldo();
					if (valorModificar > pinos[pinoDaVez].getSaldo()) { //jogador faliu, eh retirado do jogo
						if (jogadorFaliu(pinoDaVez)==0) {
							j=1;
						}
						else {
							j=2; //jogo acabou
						}
					}
					else {
						pinos[pinoDaVez].tiraSaldo(valorModificar);
						String msg=String.format("Você teve azar e perdeu: R$%d",valorModificar);
		     			JOptionPane.showMessageDialog(t,msg);
					}
				}
				else if (cartasSR[carta].getTipo() == 2) { //carta sorte receba de cada um
					int valorModificar = (ctrl.getJogadores()-1) * 50; //50 de cada outro jogador
					pinos[pinoDaVez].aumentaSaldo(valorModificar);
					for (int i=0;i<ctrl.getJogadores();i++) {
						if (i!=pinoDaVez) {
							if (pinos[i].getSaldo()<50) { //jogador i faliu, eh retirado do jogo
								if (jogadorFaliu(pinoDaVez)==0) {
									j=1;
								}
								else {
									j=2; //jogo acabou
								};
							}
							else {
								pinos[i].tiraSaldo(50);
							}
						}							
					}
					String msg=String.format("Você teve sorte e recebeu: R$%d, todos os outros jogadores perderam R$50",valorModificar);
	     			JOptionPane.showMessageDialog(t,msg);
				}
				else if (cartasSR[carta].getTipo() == 3) { //carta saida livre da prisao
					pinos[pinoDaVez].recebeCartaPrisao();
	     			JOptionPane.showMessageDialog(t,"Você recebeu a carta passe livre da prisão!");
				}
				else if (cartasSR[carta].getTipo() == 4) { //carta vai para o ponto de partida
					pinos[pinoDaVez].pinoMudouCasa(casas[0]);
					pinos[pinoDaVez].aumentaSaldo(200);
	     			JOptionPane.showMessageDialog(t,"Você foi para o ponto de partida e recebeu $200!");
				}
				else if (cartasSR[carta].getTipo() == 5) { //carta vai para a prisao
					pinos[pinoDaVez].pinoMudouCasa(casas[9]); //pino vai pra bangu 1
					pinos[pinoDaVez].mudaFoiParaPrisao(); //indica que foi para prisao pela casa "va para prisao"
	     			JOptionPane.showMessageDialog(t,"Você foi para a prisão!!");
				}
			}
			
			//pino foi para terreno de outro dono (pagamento)
			if ((casas[casaNova].getTipo()==1) && (casas[casaNova].getDono()!=-1) && (casas[casaNova].getDono()!=pinos[pinoDaVez].getPinoId())) { 
				int valor = ((Terreno) casas[casaNova]).getValorAluguel(); //valor de aluguel a ser pago (com casas ou hotel, se tiver)
				if (valor > pinos[pinoDaVez].getSaldo()) { //jogador faliu, eh retirado do jogo
					if (jogadorFaliu(pinoDaVez)==0) {
						j=1;
					}
					else {
						j=2; //jogo acabou
					}
				}
				else {
					pinos[pinoDaVez].tiraSaldo(valor); //tira do pino da vez o valor a ser pago
					pinos[casas[casaNova].getDono()-1].aumentaSaldo(valor); //soma o valor a ser pago no saldo do dono da casa
					String msg=String.format("Você pagou R$%d para o jogador %d (%s)",valor,casas[casaNova].getDono(),pinos[casas[casaNova].getDono()-1].getCor());
	     			JOptionPane.showMessageDialog(t,msg);
				}
			}
			
			//pino foi para empresa de outras pessoa (pagamento)
			if ((casas[casaNova].getTipo()==2) && (casas[casaNova].getDono()!=-1) && (casas[casaNova].getDono()!=pinos[pinoDaVez].getPinoId())) { 
				int valorPagar = ((Empresa) casas[casaNova]).getMulti() * (d1+d2+2);
				if (valorPagar > pinos[pinoDaVez].getSaldo()) { //jogador faliu, eh retirado do jogo
					if (jogadorFaliu(pinoDaVez)==0) {
						j=1;
					}
					else {
						j=2; //jogo acabou
					}
				}
				else {
					pinos[pinoDaVez].tiraSaldo(valorPagar); //retira do pino da vez o valor tirado nos dados * multi
					pinos[casas[casaNova].getDono()-1].aumentaSaldo(valorPagar);
					String msg=String.format("Você pagou R$%d para o jogador %d (%s)",valorPagar,casas[casaNova].getDono(),pinos[casas[casaNova].getDono()-1].getCor());
	     			JOptionPane.showMessageDialog(t,msg);
				}
			}
		}
		if (d1>0 && d2>0 && d1<=6 && d2 <=6) {
			if(d1!=d2) {
				d[d1].setFlag();
				t.repaint();
				d[d2].setFlag();
			}
			else {
				d[d1].setFlag();
				d[d1].setRepetido();
			}
			
		}
		repaint();
		return j;
		}
		
	
	public void criaPinos() {
		pinos=ctrl.getPinos();
		repaint();
	}
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gd2=(Graphics2D) g;       
		//Pinta o tabuleiro
		gd2.drawImage(tabuleiro,0,0,null);
		
		//Pinta um retangulo para mostrar informações
		if (ctrl.getVez()!=-1) {
			
			gd2.setPaint(Color.black);
	        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(138, 138, 475, 250, 40, 40);
	        gd2.draw(roundedRectangle);
	        
			/*
			gd2.setPaint(Color.RED);
			gd2.fillRoundRect(183, 175, 370, 200, 40, 40);
			*/
		}
		int verificaSeClicouPino=0; //vai ser usado para verificar se clicou em um pino (preferencia em relacao ao terreno/empresa)
		//Verifica se algum pino foi selecionado (para mostrar informacoes no tabuleiro sobre este pino)
		for(int i=0;i<ctrl.getJogadores();i++) {
			int countE=0,countT=0; //contador de qtdTerrenos e qtdEmpresas
			int countLa=0, countVe=0, countAm=0, countRo=0, countAz=0, countVer=0; //contador de cores (laranja, vermelho, amarelo, roxo, azul e verde)
			
			if ((pinos[i].getMostraInfo()==true) && (ctrl.getJogadorFalido(i)==0)) {
				verificaSeClicouPino=1;
				Color beige=new Color(245,222,179);
				RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(460, 410, 380, 430, 40, 40);
		        gd2.setColor(beige);
		        gd2.fillRoundRect(460, 410, 380, 430, 40, 40);
		        gd2.setColor(Color.black);
		        gd2.draw(roundedRectangle);
		        String info = String.format("Pino selecionado %d (%s):",pinos[i].getPinoId(), pinos[i].getCor());
		        gd2.setFont(new Font("Verdana",1,15));
		        gd2.drawString(info, 490,440);
		        info= String.format("Saldo: R$%d",pinos[i].getSaldo());
		        gd2.drawString(info, 490,460);
		        for (int j=0;j<36;j++) {
		        	if (casas[j].getDono()==pinos[i].getPinoId() && casas[j].getTipo()==1) {
		        		countT++;
		        		if (((Terreno) casas[j]).getCor()==0) { //laranja
		        			countLa++;
		        		}
		        		else if (((Terreno) casas[j]).getCor()==1) { //vermelho
		        			countVe++;
		        		}
		        		else if (((Terreno) casas[j]).getCor()==2) { //amarelo
		        			countAm++;
		        		}
		        		else if (((Terreno) casas[j]).getCor()==3) { //roxo
		        			countRo++;
		        		}
		        		else if (((Terreno) casas[j]).getCor()==4) { //azul
		        			countAz++;
		        		}
		        		else { //verde
		        			countVer++;
		        		}
		        	}
		        	if (casas[j].getDono()==pinos[i].getPinoId() && casas[j].getTipo()==2) {
		        		countE++;
		        	}
		        }
		        info= String.format("Dono de %d terreno(s):",countT);
		        gd2.drawString(info, 490,490);
		        if (countT>0) {
		        	gd2.drawString("Cor dos terrenos que possui:", 490, 510);
		        	gd2.setPaint(Color.orange);
		        	gd2.fillRect(490, 520, 20, 20);
		        	gd2.setPaint(Color.red);
		        	gd2.fillRect(610, 520, 20, 20);
		        	gd2.setPaint(Color.yellow);
		        	gd2.fillRect(730, 520, 20, 20);
		        	gd2.setPaint(Color.magenta);
		        	gd2.fillRect(490, 560, 20, 20);
		        	gd2.setPaint(Color.blue);
		        	gd2.fillRect(610, 560, 20, 20);
		        	gd2.setPaint(Color.green);
		        	gd2.fillRect(730, 560, 20, 20);
		        	    
			        gd2.setPaint(Color.black);
		        	info= String.format("%d",countLa);
			        gd2.drawString(info, 515,535);
		        	info= String.format("%d",countVe);
			        gd2.drawString(info, 635,535);
		        	info= String.format("%d",countAm);
			        gd2.drawString(info, 755,535);
		        	info= String.format("%d",countRo);
			        gd2.drawString(info, 515,575);
		        	info= String.format("%d",countAz);
			        gd2.drawString(info, 635,575);
		        	info= String.format("%d",countVer);
			        gd2.drawString(info, 755,575);
			        info= String.format("Dono de %d empresa(s)",countE);
			        gd2.drawString(info, 490,610);
			        gd2.drawString("Possui a carta 'livre da prisão'?",490,630);
			        if (pinos[i].verificaCartaPrisao()==true) {
			        	gd2.drawString("Sim.",750,630);
			        }
			        else {
			        	gd2.drawString("Não",750,630);
			        }
		        }
		        else {
			        info= String.format("Dono de %d empresa(s)",countE);
			        gd2.drawString(info, 490,510);
			        gd2.drawString("Possui a carta 'livre da prisão'?",490,540);
			        if (pinos[i].verificaCartaPrisao()==true) {
			        	gd2.drawString("Sim.",750,540);
			        }
			        else {
			        	gd2.drawString("Não",750,540);
			        }
		        }
		        pinos[i].unsetMostraInfo();
		        break; //sai do for
			}
		}
		
		
		//Verifica se algum terreno/empresa foi selecionado (para mostrar informacoes no tabuleiro sobre esta propriedade)
		if (verificaSeClicouPino==0) { //se tiver clicado no pino, nao ira mostrar a carta da casa/empresa
			for(int i=0;i<36;i++) {
				if (casas[i].getTipo()==1) {
					if(((Terreno) casas[i]).getMostraCarta()==true) { //mostra esse terreno
						
						/* Desenha retangulo */
						Color beige=new Color(245,222,179);
						RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(460, 410, 380, 430, 40, 40);
				        gd2.setColor(beige);
				        gd2.fillRoundRect(460, 410, 380, 430, 40, 40);
				        gd2.setColor(Color.black);
				        gd2.draw(roundedRectangle);
				        gd2.setFont(new Font("Verdana",1,15));
				        /* *************** */
				        if (casas[i].getDono()==-1) {
				        	gd2.drawString("Este terreno não possui dono",490,440);
				        }
				        else {
				        	String dono= String.format("Este terreno pertence ao jogador %d",casas[i].getDono());
				        	String cor= String.format("(%s)", pinos[casas[i].getDono()-1].getCor());
				        	String qtdcasas = String.format("Casas: %d", ((Terreno) casas[i]).getQtdCasas());
				        	String qtdhoteis = String.format("Hoteis: %d", ((Terreno) casas[i]).getQtdHoteis());
					        gd2.drawString(dono, 490,440);
					        gd2.drawString(cor, 600, 455);
					        gd2.drawString(qtdcasas, 490, 780);
					        gd2.drawString(qtdhoteis, 490, 800);
				        }
				        gd2.drawImage(((Terreno) casas[i]).getImage(),550,490,200,260,null);
				        ((Terreno) casas[i]).unsetCarta();
				        break; //sai do for
					}
				}
				else if (casas[i].getTipo()==2) {
					if(((Empresa) casas[i]).getMostraCarta()==true) { //mostra essa empresa
						/* Desenha retangulo */
						Color beige=new Color(245,222,179);
						RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(460, 410, 380, 430, 40, 40);
				        gd2.setColor(beige);
				        gd2.fillRoundRect(460, 410, 380, 430, 40, 40);
				        gd2.setColor(Color.black);
				        gd2.draw(roundedRectangle);
				        /* *************** */
				        gd2.setFont(new Font("Verdana",1,15));
				        if (casas[i].getDono()==-1) {
				        	gd2.drawString("Esta empresa não possui dono",490,440);
				        }
				        else {
				        	String dono= String.format("Esta empresa pertence ao jogador %d",casas[i].getDono());
				        	String cor= String.format("(%s)", pinos[casas[i].getDono()-1].getCor());
					        gd2.drawString(dono, 490,440);
					        gd2.drawString(cor, 600, 455);
				        }
				        gd2.drawImage(((Empresa) casas[i]).getImage(),550,490,200,260,null);
				        ((Empresa) casas[i]).unsetCarta();
				        break; //sai do for
					}
				}
			}
		}


		//Pinta os pinos no tabuleiro de acordo com a quantidade de jogadores
		for(int i=0;i<ctrl.getJogadores();i++) {
			if (ctrl.getVez()==(i+1) && ctrl.getJogadorFalido(i)==0) {
				gd2.drawImage(pinos[i].getImage(),pinos[i].getXPino(),pinos[i].getYPino(),null);
				gd2.drawImage(pinos[i].getImage(),pinos[i].getXPino()-3,pinos[i].getYPino(),30,40,null);
			}
			else if (ctrl.getJogadorFalido(i)==0){
				gd2.drawImage(pinos[i].getImage(),pinos[i].getXPino(),pinos[i].getYPino(),null);
			}
			pinos[i].aumentaEntrou();
		}
		for(int i=0;i<ctrl.getJogadores();i++) {
			if (ctrl.getJogadorFalido(i)==0)
				pinos[i].zeraEntrou();
		}
		
		//Pinta a carta de sorte ou reves tirada
		int marca=0;
		for (int i=0;i<31;i++) {
			if (cartasSR[i].getFoiTirada()==true) {
				marca = i; //marca para retirá-la depois de pintar
				gd2.drawImage(cartasSR[i].getImage(),630,180,null);
			}
		}
		cartasSR[marca].unsetFoiTirada();
		
		//pinta o turno de qual jogador e sua cor
		if (ctrl.getVez()!=-1) {
			String turno = String.format("Turno do jogador %d (%s)",pinos[ctrl.getVez()-1].getPinoId(), pinos[ctrl.getVez()-1].getCor());
			if (ctrl.getVez() == 1) {
				gd2.setColor(Color.red);
			}
			else if (ctrl.getVez() == 2) {
				gd2.setColor(Color.blue);
			}
			else if (ctrl.getVez() == 3) {
				gd2.setColor(Color.orange);
			}
			else if (ctrl.getVez() == 4) {
				gd2.setColor(Color.yellow);
			}
			else if (ctrl.getVez() == 5) {
				gd2.setColor(Color.MAGENTA);
			}
			else {
				gd2.setColor(Color.gray);
			}
			gd2.setFont(new Font("Verdana",1,20));
			gd2.drawString(turno, 150, 180);
			
			// pinta o saldo do jogador
			String saldo = String.format("Saldo do jogador: R$%d", pinos[ctrl.getVez()-1].getSaldo());
			gd2.setColor(Color.black);
			gd2.drawString(saldo, 150, 230);
			gd2.setFont(new Font("Verdana",1,15));
			//pinta informacoes da casa atual
			if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==1) { //informa o pino que esta em um terreno
				//desenha um retangulo para posicionar a carta
		        RoundRectangle2D roundedRectangle2 = new RoundRectangle2D.Float(138, 388, 222, 280, 40, 40);
		        gd2.setColor(Color.white);
		        gd2.fillRoundRect(138, 388, 222, 280, 40, 40);
		        gd2.setColor(Color.black);
		        gd2.draw(roundedRectangle2);
				gd2.drawImage(((Terreno) pinos[ctrl.getVez()-1].getCasaPino()).getImage(),150,400,200,260,null); //pinta a carta do terreno
				if (pinos[ctrl.getVez()-1].getCasaPino().getDono()==-1) { //terreno nao tem dono
					String valorCasaAtual = String.format("Para comprá-lo é preciso pagar R$%d",pinos[ctrl.getVez()-1].getCasaPino().getValor());
					gd2.drawString("Este terreno não tem dono", 150, 280);
					gd2.drawString(valorCasaAtual, 150, 300);
				}
				else { //tem dono
					//desenha um retangulo para posicionar a carta
			        RoundRectangle2D roundedRectangle3 = new RoundRectangle2D.Float(138, 388, 222, 280, 40, 40);
			        gd2.setColor(Color.white);
			        gd2.fillRoundRect(138, 388, 222, 280, 40, 40);
			        gd2.setColor(Color.black);
			        gd2.draw(roundedRectangle3);
					gd2.drawImage(((Terreno) pinos[ctrl.getVez()-1].getCasaPino()).getImage(),150,400,200,260,null); //pinta a carta do terreno
					String casaAtual = String.format("Este terreno pertence ao jogador %d (%s) e tem:",pinos[ctrl.getVez()-1].getCasaPino().getDono(),pinos[pinos[ctrl.getVez()-1].getCasaPino().getDono()-1].getCor());
					String numCasas = String.format("%d casas", ((Terreno) pinos[ctrl.getVez()-1].getCasaPino()).getQtdCasas());
					String numHoteis = String.format("%d hotéis", ((Terreno) pinos[ctrl.getVez()-1].getCasaPino()).getQtdHoteis());
					gd2.drawString(casaAtual, 150, 280);
					gd2.drawString(numCasas, 150, 300);
					gd2.drawString(numHoteis, 150, 320);
				}
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==2) { //informa o pino que esta em uma empresa
				//desenha um retangulo para posicionar a carta
		        RoundRectangle2D roundedRectangle2 = new RoundRectangle2D.Float(138, 388, 222, 280, 40, 40);
		        gd2.setColor(Color.white);
		        gd2.fillRoundRect(138, 388, 222, 280, 40, 40);
		        gd2.setColor(Color.black);
		        gd2.draw(roundedRectangle2);
				gd2.drawImage(((Empresa) pinos[ctrl.getVez()-1].getCasaPino()).getImage(),150,400,200,260,null); //pinta a carta de empresa
				if (pinos[ctrl.getVez()-1].getCasaPino().getDono()==-1) { //a empresa nao tem dono
					String valorCasaAtual = String.format("Para comprá-la é preciso pagar R$%d",pinos[ctrl.getVez()-1].getCasaPino().getValor());
					gd2.drawString("Esta empresa não tem dono", 150, 280);
					gd2.drawString(valorCasaAtual, 150, 300);
				}
				else { //tem dono
					//desenha um retangulo para posicionar a carta
			        RoundRectangle2D roundedRectangle3 = new RoundRectangle2D.Float(138, 388, 222, 280, 40, 40);
			        gd2.setColor(Color.white);
			        gd2.fillRoundRect(138, 388, 222, 280, 40, 40);
			        gd2.setColor(Color.black);
			        gd2.draw(roundedRectangle3);
					gd2.drawImage(((Empresa) pinos[ctrl.getVez()-1].getCasaPino()).getImage(),150,400,200,260,null); //pinta a carta de empresa
					String casaAtual = String.format("Esta empresa pertence ao jogador %d (%s)",pinos[ctrl.getVez()-1].getCasaPino().getDono(),pinos[pinos[ctrl.getVez()-1].getCasaPino().getDono()-1].getCor());
					gd2.drawString(casaAtual, 150, 280);
				}
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==7 && pinos[ctrl.getVez()-1].getPrisao()==true) { //informa que o pino esta preso
				gd2.drawString("O pino está na prisao,", 150, 250);
				gd2.drawString("saia com 2 números iguais nos dados", 150, 270);
				String qtdJogadas = String.format("Tentativas restantes para tirar números iguais: %d",(4-pinos[ctrl.getVez()-1].getQtdJogadas()));
				gd2.drawString(qtdJogadas, 150, 310);
				if (pinos[ctrl.getVez()-1].getQtdJogadas()<4) {
					gd2.drawString("Ao esgotar as tentativas,", 150, 340);
					gd2.drawString("você pagará R$50 ao banco e andará", 150, 360);
				}
				else {
					gd2.drawString("Você pagará R$50 ao banco, e andará", 150, 340);
				}
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==7 && pinos[ctrl.getVez()-1].getPrisao()==false) { //informa que o pino esta na prisao mas nao esta preso
				gd2.drawString("O pino está na prisão, mas não está preso", 150, 280);
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==3) { //informa que o pino esta na casa de sorte/reves
				gd2.drawString("O pino está na casa de cartas sorte/revés", 150, 280);
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==0) { //informa que o pino esta na casa de inicio
				gd2.drawString("O pino está na casa de início", 150, 280);
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==8) { //informa q o pino esta na casa especial
				gd2.drawString("O pino está na casa de cerveja e pagode!", 150, 280);
			}
			else { //informa q o pino esta na casa de eventos
				gd2.drawString("O pino está na casa de eventos", 150, 280);
			}
			
			
		}

		int p=0; //valor para conferir se um dado nao-repetido ja foi pintado (sera usado mais adiante)
		/*Percorre o vetor de dados e verifica quais tem a flag == true (ou seja, tem que ser pintado).
		Depois de pintado a flag volta a ser false. Verifica tambem se o valor rolado nos dados eh repetido*/
		for(int j=0;j<6;j++) {
			if(d[j].getFlag()==true) {
				if(d[j].getRepetido()==true) {
					gd2.drawImage(d[j].getImage(),150,700,100,100,null);
					gd2.drawImage(d[j].getImage(),300,700,100,100,null);
					d[j].unsetFlag();
					d[j].unsetRepetido();
					break;
				}
				else if (p==0) {
					gd2.drawImage(d[j].getImage(),150,700,100,100,null);
					d[j].unsetFlag();
					p=1;
				}
				else if (p==1) {
					gd2.drawImage(d[j].getImage(),300,700,100,100,null);
					d[j].unsetFlag();
				}
				
			}
		}
	}
	
	public Pino getPinoDaVez() {
		return pinos[ctrl.getVez()-1];
	}
	
	public static int sorteiaCarta() {
		return aleatorio.nextInt(31);
	}
	public int jogadorFaliu(int i) {
		if (ctrl.jogadorFaliu(i)==0) { //jogador faliu, mas nao acabou o jogo
			for (int j=0;j<casas.length;j++) { //percorre as casas para ver se o pino era dono de alguma (agora sem dono)
				if (casas[i].getTipo()==1) {
					if (casas[i].getDono()==pinos[i].getPinoId()) {
						casas[i].mudaDono(-1);
						((Terreno) casas[i]).zeraCasasHotel();
					}
				}
				else if (casas[i].getTipo()==2) {
					if (casas[i].getDono()==pinos[i].getPinoId()) {
						casas[i].mudaDono(-1);
					}
				}
			}
			String fimJogo=String.format("Você faliu! Fim de jogo para o pino %d (%s)!",i,pinos[i].getCor());
			JOptionPane.showMessageDialog(t,fimJogo);
			pinos[i].zeraEntrou();
			ctrl.acabouTurno();
			repaint();
			return 0;
		}
		else { //jogador faliu e acabou o jogo
			ctrl.verificaGanhador();
			ctrl.novoJogo();
			return 1;
		}
	}
}
