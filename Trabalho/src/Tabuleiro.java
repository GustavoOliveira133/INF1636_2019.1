import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

public class Tabuleiro extends JPanel {
	private Tabuleiro t=this;
	private Image tabuleiro=null;
	private Dados d[]=new Dados[6];
	private Pino[] pinos;
    private Controlador ctrl;
    private Casa casas[]=new Casa[36];
	
	public Tabuleiro(Controlador ctrl) {
		t.setBounds(0, 0, 1000, 1000);
		this.ctrl=ctrl;
		try {
			tabuleiro=ImageIO.read(new File("tabuleiroRJ.jpg"));
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		
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
		casas[0]=new Casa(20,880,0,0,0);
		casas[1]=new Casa(20,810,1,1,220);
		casas[2]=new Casa(20,715,3,2,0);
		casas[3]=new Casa(20,630,2,3,200);
		casas[4]=new Casa(20,505,1,4,300);
		casas[5]=new Casa(20,450,1,5,220);
		casas[6]=new Casa(20,360,3,6,0);
		casas[7]=new Casa(20,255,2,7,150);
		casas[8]=new Casa(20,165,1,8,140);
		casas[9]=new Casa(20,40,7,9,0);
		casas[10]=new Casa(140,30,1,10,60);
		casas[11]=new Casa(237,30,1,11,180);
		casas[12]=new Casa(330,30,1,12,300);
		casas[13]=new Casa(420,30,3,13,0);
		casas[14]=new Casa(515,30,1,14,260);
		casas[15]=new Casa(610,30,2,15,200);
		casas[16]=new Casa(700,30,1,16,220);
		casas[17]=new Casa(790,30,5,17,200);
		casas[18]=new Casa(880,40,8,18,0);
		casas[19]=new Casa(880,165,2,19,200);
		casas[20]=new Casa(880,255,1,20,180);
		casas[21]=new Casa(880,347,1,21,140);
		casas[22]=new Casa(880,440,1,22,120);
		casas[23]=new Casa(880,540,3,23,0);
		casas[24]=new Casa(880,620,2,24,200);
		casas[25]=new Casa(880,715,1,25,60);
		casas[26]=new Casa(880,810,1,26,260);
		casas[27]=new Casa(880,870,6,27,0);
		casas[28]=new Casa(790,900,1,28,160);
		casas[29]=new Casa(700,900,3,29,0);
		casas[30]=new Casa(610,900,1,30,240);
		casas[31]=new Casa(515,900,2,31,150);
		casas[32]=new Casa(420,900,1,32,100);
		casas[33]=new Casa(330,900,3,33,0);
		casas[34]=new Casa(237,900,4,34,200);
		casas[35]=new Casa(140,900,1,35,100);
		
		
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
			if (d1 != d2 ) { /* && n tem a carta saida livre da prisao */
				d[d1].setFlag();
				d[d2].setFlag();
				repaint();
			}
			else { //pino anda normalmente
				int casaNova = pinos[pinoDaVez].getIdCasaPino() + d1 + d2 + 2;
				pinos[pinoDaVez].pinoMudouCasa(casas[casaNova]);
				pinos[pinoDaVez].mudaFoiParaPrisao();
				d[d1].setFlag();
				d[d1].setRepetido();
				repaint();
			}
		}
		else {
			int casaNova = pinos[pinoDaVez].getIdCasaPino() + d1 + d2 + 2;
			
			if (casaNova > 35) {
				casaNova = casaNova - 35;
				pinos[pinoDaVez].aumentaSaldo(200); //passou pela casa inicial
			}
			if (casaNova == 27) { //casa nova = va para prisao
				JOptionPane.showMessageDialog(t,"Voce foi para a prisao");
				pinos[pinoDaVez].pinoMudouCasa(casas[9]); //pino vai pra bangu 1
				pinos[pinoDaVez].mudaFoiParaPrisao(); //indica que foi para prisao pela casa "va para prisao"
			}
			else {
				pinos[pinoDaVez].pinoMudouCasa(casas[casaNova]);
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
		
	}
	
	public void criaPinos() {
		//Criar os pinos de acordo com a quantidade de jogadores selecionado e pinta-los no tabuleiro
		int qtd=ctrl.getJogadores();
		pinos=new Pino[qtd];
		/* Pino 1 - 'V'
		 * Pino 2 - 'A'
		 * Pino 3 - 'L'
		 * Pino 4 - 'M'
		 * Pino 5 - 'R'
		 * Pino 6 - 'C'	*/
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
		
		//pinta o turno de qual jogador e sua cor
		if (ctrl.getVez()!=-1) {
			String turno = String.format("Turno do jogador 1 (%s)", pinos[ctrl.getVez()-1].getCor());
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
			if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==1 || pinos[ctrl.getVez()-1].getCasaPino().getTipo()==2) { //informa o pino que esta em um terreno ou empresa
				if (pinos[ctrl.getVez()-1].getCasaPino().getDono()==-1) { //a empresa/terreno nao tem dono
					String valorCasaAtual = String.format("Para compra-la é preciso pagar R$%d",pinos[ctrl.getVez()-1].getCasaPino().getValor());
					gd2.drawString("A propriedade atual nao tem dono", 200, 300);
					gd2.drawString(valorCasaAtual, 200, 350);
				}
				else {
					String casaAtual = String.format("A propriedade atual pertence ao jogador %d (%s) e tem %d casas e %d hoteis",pinos[ctrl.getVez()-1].getCasaPino().getDono(),pinos[pinos[ctrl.getVez()-1].getCasaPino().getDono()-1].getCor(), pinos[ctrl.getVez()-1].getCasaPino().getQtdCasas(),pinos[ctrl.getVez()-1].getCasaPino().getQtdHoteis());
					gd2.drawString(casaAtual, 200, 300);
				}
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==7 && pinos[ctrl.getVez()-1].getPrisao()==true) { //informa que o pino esta preso
				gd2.drawString("O pino esta na prisao, saia com 2 numeros iguais nos dados", 200, 300);
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==7 && pinos[ctrl.getVez()-1].getPrisao()==false) { //informa que o pino esta na prisao mas nao esta preso
				gd2.drawString("O pino esta na prisao, mas nao esta preso", 200, 300);
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==3) { //informa que o pino esta na casa de sorte/reves
				gd2.drawString("O pino esta na casa das cartas sorte/reves", 200, 300);
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==0) { //informa que o pino esta na casa de inicio
				gd2.drawString("O pino esta na casa de inicio", 200, 300);
			}
			else if (pinos[ctrl.getVez()-1].getCasaPino().getTipo()==8) { //informa q o pino esta na casa especial
				gd2.drawString("O pino esta na casa de cerveja e pagode!", 200, 300);
			}
			else { //informa q o pino esta na casa de eventos
				gd2.drawString("O pino esta na casa de eventos", 200, 300);
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
}
