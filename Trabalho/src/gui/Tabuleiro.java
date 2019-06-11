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
    private Casa casas[]=new Casa[36];
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
		
		//Criando as casas (passa o x,y,tipo da casa, seu id e valor
		/* Tipos de casa: 
		 * 0 - Casa inicial
		 * 1 - Terrenos
		 * 2 - Empresa
		 * 3 - Sorte/Reves
		 * 4 - Eventos (pague x)
		 * 5 - Eventos (receba x)
		 * 6 - prisao (vá para bangu I)
		 * 7 - bangu I
		 * 8 - casa cerveja/pagode
		 */
		//Construtor (x,y,tipo,idCasa,Preco) - Construtor Terreno (x,y,tipo,idCasa,Preco,Cor)
		casas[0]=new Casa(20,880,0,0,0); //casa inicial
		casas[1]=new Terreno(15,800,1,1,220,0); //t curicica
		casas[2]=new Casa(15,695,3,2,0);
		casas[3]=new Empresa(15,600,2,3,200);
		casas[4]=new Terreno(15,505,1,4,300,1); //t leme
		casas[5]=new Terreno(15,410,1,5,220,2);//t vilar carioca
		casas[6]=new Casa(15,320,3,6,0);
		casas[7]=new Empresa(15,230,2,7,150);
		casas[8]=new Terreno(15,140,1,8,140,3); //t morro do 18
		casas[9]=new Casa(15,30,7,9,0); //prisao
		casas[10]=new Terreno(130,10,1,10,60,4); //t guapore
		casas[11]=new Terreno(223,10,1,11,180,0); //t tanque
		casas[12]=new Terreno(316,10,1,12,300,1); //t botafogo
		casas[13]=new Casa(409,10,3,13,0);
		casas[14]=new Terreno(502,10,1,14,260,5); //t batan
		casas[15]=new Empresa(595,10,2,15,200);
		casas[16]=new Terreno(688,10,1,16,220,2); //t barbante
		casas[17]=new Casa(781,10,5,17,200); //casa de evento receba x
		casas[18]=new Casa(880,10,8,18,0); //casa cerveja pagode
		casas[19]=new Empresa(870,130,2,19,200);
		casas[20]=new Terreno(870,225,1,20,180,0); //t gardenia azul
		casas[21]=new Terreno(870,318,1,21,140,3); //t caixa dagua
		casas[22]=new Terreno(870,411,1,22,120,5); //t kelsons
		casas[23]=new Casa(870,504,3,23,0);
		casas[24]=new Empresa(870,597,2,24,200);
		casas[25]=new Terreno(870,690,1,25,60,4); //t quitungo
		casas[26]=new Terreno(870,783,1,26,260,1);//t rio das pedras
		casas[27]=new Casa(880,870,6,27,0); //casa va para prisao
		casas[28]=new Terreno(780,875,1,28,160,3); //t fuba
		casas[29]=new Casa(687,875,3,29,0);
		casas[30]=new Terreno(594,875,1,30,240,2); //t carobinha
		casas[31]=new Empresa(501,875,2,31,150);
		casas[32]=new Terreno(408,875,1,32,100,5); //t fumace
		casas[33]=new Casa(315,875,3,33,0);
		casas[34]=new Casa(222,875,4,34,200); //casa de evento pague x
		casas[35]=new Terreno(129,875,1,35,100,4); //t cidade alta
		//Coloca multi nas empresas
		((Empresa)casas[3]).setMulti(50);
		((Empresa)casas[7]).setMulti(40);
		((Empresa) casas[15]).setMulti(50);
		((Empresa) casas[19]).setMulti(50);
		((Empresa) casas[24]).setMulti(50);
		((Empresa) casas[31]).setMulti(40);
		//coloca os valores nos terrenos (aluguel,1casa,2casas,3casas,4casas,hotel,cadaCasa,cadaHotel)
		((Terreno) casas[1]).setValoresTerreno(28,150,450,1000,1200,1400,200,200); //curicica
		((Terreno) casas[4]).setValoresTerreno(26,130,390,900,1100,1275,200,200); //leme
		((Terreno) casas[5]).setValoresTerreno(18,90,250,700,875,1050,150,150); //vilar carioca
		((Terreno) casas[8]).setValoresTerreno(10,50,150,450,625,750,100,100); //morro do 18
		((Terreno) casas[10]).setValoresTerreno(4,20,60,180,320,450,50,50); //guapore
		((Terreno) casas[11]).setValoresTerreno(14,70,200,550,750,950,100,100); //tanque
		((Terreno) casas[12]).setValoresTerreno(26,130,390,900,1100,1275,200,200);//botafogo
		((Terreno) casas[14]).setValoresTerreno(22,110,330,800,975,1150,150,150);//batan
		((Terreno) casas[16]).setValoresTerreno(18,90,250,700,875,1050,150,150);//barbante
		((Terreno) casas[20]).setValoresTerreno(14,70,200,550,750,950,100,100);//gardenia azul
		((Terreno) casas[21]).setValoresTerreno(10,50,150,450,625,750,100,100);//caixa dagua
		((Terreno) casas[22]).setValoresTerreno(8,40,100,300,450,600,50,50);//kelsons
		((Terreno) casas[25]).setValoresTerreno(4,20,60,180,320,450,50,50);//quitungo
		((Terreno) casas[26]).setValoresTerreno(22,110,330,800,975,1150,150,150);//rio das pedras
		((Terreno) casas[28]).setValoresTerreno(12,60,180,500,700,900,100,100);//fuba
		((Terreno) casas[30]).setValoresTerreno(20,100,300,750,925,1100,150,150);//carobinha
		((Terreno) casas[32]).setValoresTerreno(6,30,90,270,400,500,50,50);//fumace
		((Terreno) casas[35]).setValoresTerreno(6,30,90,270,400,500,50,50);//cidade alta
		
		
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
    		}
    	});
	}
	
	public void clicouNosDados(int d1, int d2) {
		//Pintar os dados no tabuleiro
		int pinoDaVez=ctrl.getVez()-1;
		
		//Pino na prisao, verifica se foi para prisao pela casa "va para prisao" e verifica se pode sair
		if ((pinos[pinoDaVez].getCasaPino().getTipo()==7) && (pinos[pinoDaVez].getPrisao()==true)) {
			if (d1 == d2) { /* dados iguais, anda normalmente */
				int casaNova = pinos[pinoDaVez].getIdCasaPino() + d1 + d2 + 2;
				pinos[pinoDaVez].pinoMudouCasa(casas[casaNova]);
				pinos[pinoDaVez].mudaFoiParaPrisao();
				d[d1].setFlag();
				d[d1].setRepetido();
				repaint();
			}
			else if (pinos[pinoDaVez].verificaCartaPrisao()==true) { /* dados diferentes e tem a carta saida livre da prisao, anda */
				int casaNova = pinos[pinoDaVez].getIdCasaPino() + d1 + d2 + 2;
				pinos[pinoDaVez].pinoMudouCasa(casas[casaNova]);
				pinos[pinoDaVez].mudaFoiParaPrisao();
				pinos[pinoDaVez].gastaCartaPrisao();
				d[d1].setFlag();
				d[d2].setFlag();
				repaint();
			}
			else if (pinos[pinoDaVez].getQtdJogadas()>=4) { /* excedeu o numero de jogadas possiveis na prisao, paga e anda */
				int casaNova = pinos[pinoDaVez].getIdCasaPino() + d1 + d2 + 2;
				pinos[pinoDaVez].pinoMudouCasa(casas[casaNova]);
				pinos[pinoDaVez].mudaFoiParaPrisao();
				pinos[pinoDaVez].tiraSaldo(50);
				pinos[pinoDaVez].zeraJogadas();
				d[d1].setFlag();
				d[d2].setFlag();
				repaint();
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
				JOptionPane.showMessageDialog(t,"Voce perdeu R$200!");
				pinos[pinoDaVez].tiraSaldo(200);
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
					pinos[pinoDaVez].tiraSaldo(valorModificar);
					String msg=String.format("Você teve azar e perdeu: R$%d",valorModificar);
	     			JOptionPane.showMessageDialog(t,msg);
				}
				else if (cartasSR[carta].getTipo() == 2) { //carta sorte receba de cada um
					int valorModificar = (ctrl.getJogadores()-1) * 50; //50 de cada outro jogador
					pinos[pinoDaVez].aumentaSaldo(valorModificar);
					for (int i=0;i<ctrl.getJogadores();i++) {
						if (i!=pinoDaVez) {
							pinos[i].tiraSaldo(50);
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
			
			if ((casas[casaNova].getTipo()==1) && (casas[casaNova].getDono()!=-1) && (casas[casaNova].getDono()!=pinos[pinoDaVez].getPinoId())) { //pino foi para terreno de outro dono (pagamento)
				int valor = ((Terreno) casas[casaNova]).getValorAluguel(); //valor de aluguel a ser pago (com casas ou hotel, se tiver)
				pinos[pinoDaVez].tiraSaldo(valor); //tira do pino da vez o valor a ser pago
				pinos[casas[casaNova].getDono()-1].aumentaSaldo(valor); //soma o valor a ser pago no saldo do dono da casa
				String msg=String.format("Você pagou R$%d para o jogador %d (%s)",valor,casas[casaNova].getDono(),pinos[casas[casaNova].getDono()-1].getCor());
     			JOptionPane.showMessageDialog(t,msg);
			}
			
			if ((casas[casaNova].getTipo()==2) && (casas[casaNova].getDono()!=-1) && (casas[casaNova].getDono()!=pinos[pinoDaVez].getPinoId())) { //pino foi para empresa de outras pessoa (pagamento)
				int valorPagar = ((Empresa) casas[casaNova]).getMulti() * (d1+d2+2);
				pinos[pinoDaVez].tiraSaldo(valorPagar); //retira do pino da vez o valor tirado nos dados * multi
				pinos[casas[casaNova].getDono()-1].aumentaSaldo(valorPagar);
				String msg=String.format("Você pagou R$%d para o jogador %d (%s)",valorPagar,casas[casaNova].getDono(),pinos[casas[casaNova].getDono()-1].getCor());
     			JOptionPane.showMessageDialog(t,msg);
			}
		}
		
			if(d1!=d2) {
				d[d1].setFlag();
				t.repaint();
				d[d2].setFlag();
			}
			else {
				d[d1].setFlag();
				d[d1].setRepetido();
			}
			repaint();
		}
		
	
	
	public void criaPinos() {
		//Criar os pinos de acordo com a quantidade de jogadores selecionado e pinta-los no tabuleiro
		int qtd=ctrl.getJogadores();
		pinos=new Pino[qtd];

		if (qtd==2) {
			pinos[0]=new Pino("pin0.png",casas[0],"Vermelho",1);
			pinos[1]=new Pino("pin1.png",casas[0],"Azul",2);
		}
		else if (qtd==3) {
			pinos[0]=new Pino("pin0.png",casas[0],"Vermelho",1);
			pinos[1]=new Pino("pin1.png",casas[0],"Azul",2);
			pinos[2]=new Pino("pin2.png",casas[0],"Laranja",3);
		}
		else if(qtd==4) {
			pinos[0]=new Pino("pin0.png",casas[0],"Vermelho",1);
			pinos[1]=new Pino("pin1.png",casas[0],"Azul",2);
			pinos[2]=new Pino("pin2.png",casas[0],"Laranja",3);
			pinos[3]=new Pino("pin3.png",casas[0],"Amarelo",4);
		}
		else if(qtd==5) {
			pinos[0]=new Pino("pin0.png",casas[0],"Vermelho",1);
			pinos[1]=new Pino("pin1.png",casas[0],"Azul",2);
			pinos[2]=new Pino("pin2.png",casas[0],"Laranja",3);
			pinos[3]=new Pino("pin3.png",casas[0],"Amarelo",4);
			pinos[4]=new Pino("pin4.png",casas[0],"Roxo",5);
		}
		
		else if(qtd==6) {
			pinos[0]=new Pino("pin0.png",casas[0],"Vermelho",1);
			pinos[1]=new Pino("pin1.png",casas[0],"Azul",2);
			pinos[2]=new Pino("pin2.png",casas[0],"Laranja",3);
			pinos[3]=new Pino("pin3.png",casas[0],"Amarelo",4);
			pinos[4]=new Pino("pin4.png",casas[0],"Roxo",5);
			pinos[5]=new Pino("pin5.png",casas[0],"Cinza",6);
		}
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
	        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(183, 175, 395, 200, 40, 40);
	        gd2.draw(roundedRectangle);
	        
			/*
			gd2.setPaint(Color.RED);
			gd2.fillRoundRect(183, 175, 370, 200, 40, 40);
			*/
		}
		

		//Pinta os pinos no tabuleiro de acordo com a quantidade de jogadores
		for(int i=0;i<ctrl.getJogadores();i++) {
			if (ctrl.getVez()==(i+1)) {
				gd2.drawImage(pinos[i].getImage(),pinos[i].getXPino(),pinos[i].getYPino(),null);
				gd2.drawImage(pinos[i].getImage(),pinos[i].getXPino()-3,pinos[i].getYPino(),30,40,null);
			}
			else {
				gd2.drawImage(pinos[i].getImage(),pinos[i].getXPino(),pinos[i].getYPino(),null);
			}
			pinos[i].aumentaEntrou();
		}
		for(int i=0;i<ctrl.getJogadores();i++) {
			pinos[i].zeraEntrou();
		}
		
		//Pinta a carta de sorte ou reves tirada
		int marca=0;
		for (int i=0;i<31;i++) {
			if (cartasSR[i].getFoiTirada()==true) {
				marca = i; //marca para retirá-la depois de pintar
				gd2.drawImage(cartasSR[i].getImage(),610,180,null);
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
			gd2.drawString(turno, 200, 200);
			
			// pinta o saldo do jogador
			String saldo = String.format("Saldo do jogador: %d", pinos[ctrl.getVez()-1].getSaldo());
			gd2.setColor(Color.black);
			gd2.drawString(saldo, 200, 250);
			
			//pinta informacoes da casa atual
			if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==1) { //informa o pino que esta em um terreno
				if (pinos[ctrl.getVez()-1].getCasaPino().getDono()==-1) { //terreno nao tem dono
					String valorCasaAtual = String.format("Para comprá-lo é preciso pagar R$%d",pinos[ctrl.getVez()-1].getCasaPino().getValor());
					gd2.drawString("Este terreno não tem dono", 200, 300);
					gd2.drawString(valorCasaAtual, 200, 350);
				}
				else { //tem dono
					String casaAtual = String.format("Este terreno pertence ao jogador %d (%s) e tem:",pinos[ctrl.getVez()-1].getCasaPino().getDono(),pinos[pinos[ctrl.getVez()-1].getCasaPino().getDono()-1].getCor());
					String numCasas = String.format("%d casas", ((Terreno) pinos[ctrl.getVez()-1].getCasaPino()).getQtdCasas());
					String numHoteis = String.format("%d hotéis", ((Terreno) pinos[ctrl.getVez()-1].getCasaPino()).getQtdHoteis());
					gd2.drawString(casaAtual, 200, 300);
					gd2.drawString(numCasas, 200, 330);
					gd2.drawString(numHoteis, 200, 350);
				}
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==2) { //informa o pino que esta em uma empresa
				if (pinos[ctrl.getVez()-1].getCasaPino().getDono()==-1) { //a empresa nao tem dono
					String valorCasaAtual = String.format("Para comprá-la é preciso pagar R$%d",pinos[ctrl.getVez()-1].getCasaPino().getValor());
					gd2.drawString("Esta empresa não tem dono", 200, 300);
					gd2.drawString(valorCasaAtual, 200, 350);
				}
				else {
					String casaAtual = String.format("Esta empresa pertence ao jogador %d (%s)",pinos[ctrl.getVez()-1].getCasaPino().getDono(),pinos[pinos[ctrl.getVez()-1].getCasaPino().getDono()-1].getCor());
					gd2.drawString(casaAtual, 200, 300);
				}
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==7 && pinos[ctrl.getVez()-1].getPrisao()==true) { //informa que o pino esta preso
				gd2.drawString("O pino está na prisao, saia com 2 números iguais nos dados", 200, 300);
				String qtdJogadas = String.format("Tentativas restantes para tirar números iguais: %d",(4-pinos[ctrl.getVez()-1].getQtdJogadas()));
				gd2.drawString(qtdJogadas, 200, 330);
				if (pinos[ctrl.getVez()-1].getQtdJogadas()<4) {
					gd2.drawString("Ao esgotar as tentativas, você pagará R$50 ao banco e andará", 200, 350);
				}
				else {
					gd2.drawString("Você pagará R$50 ao banco, caso não tire 2 valores iguais, e andará", 200, 350);
				}
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==7 && pinos[ctrl.getVez()-1].getPrisao()==false) { //informa que o pino esta na prisao mas nao esta preso
				gd2.drawString("O pino está na prisao, mas não está preso", 200, 300);
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==3) { //informa que o pino esta na casa de sorte/reves
				gd2.drawString("O pino está na casa das cartas sorte/revés", 200, 300);
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==0) { //informa que o pino esta na casa de inicio
				gd2.drawString("O pino está na casa de início", 200, 300);
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==8) { //informa q o pino esta na casa especial
				gd2.drawString("O pino está na casa de cerveja e pagode!", 200, 300);
			}
			else { //informa q o pino esta na casa de eventos
				gd2.drawString("O pino está na casa de eventos", 200, 300);
			}
			
			
		}

		int p=0; //valor para conferir se um dado nao-repetido ja foi pintado (sera usado mais adiante)
		/*Percorre o vetor de dados e verifica quais tem a flag == true (ou seja, tem que ser pintado).
		Depois de pintado a flag volta a ser false. Verifica tambem se o valor rolado nos dados eh repetido*/
		for(int j=0;j<6;j++) {
			if(d[j].getFlag()==true) {
				if(d[j].getRepetido()==true) {
					gd2.drawImage(d[j].getImage(),250,700,100,100,null);
					gd2.drawImage(d[j].getImage(),550,700,100,100,null);
					d[j].unsetFlag();
					d[j].unsetRepetido();
					break;
				}
				else if (p==0) {
					gd2.drawImage(d[j].getImage(),250,700,100,100,null);
					d[j].unsetFlag();
					p=1;
				}
				else if (p==1) {
					gd2.drawImage(d[j].getImage(),550,700,100,100,null);
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
	
}
