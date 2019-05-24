import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

public class Tabuleiro extends JPanel {
	private Tabuleiro t=this;
	private Image tabuleiro=null;
	private Dados d[]=new Dados[6];
	private Pino pinos[]=null;
    private int dados[]=new int[2];
    private Controlador ctrl;
    private Casa casas[]=new Casa[37];

	
	public Tabuleiro(Controlador ctrl) {
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
		
		//Criando as casas
		casas[0]=new Casa(880,880,1,0);
		casas[1]=new Casa(790,900,0,1);
		casas[2]=new Casa(700,900,0,2);
		casas[3]=new Casa(610,900,0,3);
		casas[4]=new Casa(515,900,0,4);
		casas[5]=new Casa(420,900,0,5);
		casas[6]=new Casa(330,900,0,6);
		casas[7]=new Casa(237,900,0,7);
		casas[8]=new Casa(140,900,0,8);
		casas[9]=new Casa(20,880,1,9);
		casas[10]=new Casa(20,810,0,10);
		casas[11]=new Casa(20,715,0,11);
		casas[12]=new Casa(20,630,0,12);
		casas[13]=new Casa(20,540,0,13);
		casas[14]=new Casa(20,450,0,14);
		casas[15]=new Casa(20,360,0,15);
		casas[16]=new Casa(20,255,0,16);
		casas[17]=new Casa(20,165,0,17);
		casas[18]=new Casa(20,40,1,18);
		casas[19]=new Casa(140,30,0,19);
		casas[20]=new Casa(237,30,0,20);
		casas[21]=new Casa(330,30,0,21);
		casas[22]=new Casa(420,30,0,22);
		casas[23]=new Casa(515,30,0,23);
		casas[24]=new Casa(610,30,0,24);
		casas[25]=new Casa(700,30,0,25);
		casas[26]=new Casa(790,30,0,26);
		casas[27]=new Casa(880,40,1,27);
		casas[28]=new Casa(880,165,0,28);
		casas[29]=new Casa(880,255,0,29);
		casas[30]=new Casa(880,347,0,30);
		casas[31]=new Casa(880,440,0,31);
		casas[32]=new Casa(880,540,0,32);
		casas[33]=new Casa(880,620,0,33);
		casas[34]=new Casa(880,715,0,34);
		casas[35]=new Casa(880,810,0,35);
		
		
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
		pinos[pinoDaVez].pinoSaiuCasa();
		int casaNova = pinos[pinoDaVez].getCasaPino() + d1 + d2 + 2;
		if (casaNova > 35)
			casaNova = casaNova - 35;
		pinos[pinoDaVez].pinoMudouCasa(casas[casaNova]);
		pinos[pinoDaVez].pinoEntrouCasa();
		ctrl.acabouTurno();
		
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
			pinos[0]=new Pino("pin0.png",casas[0]);
			pinos[0].pinoEntrouCasa();
			pinos[1]=new Pino("pin1.png",casas[0]);
			pinos[1].pinoEntrouCasa();
		}
		else if (qtd==3) {
			pinos[0]=new Pino("pin0.png",casas[0]);
			pinos[0].pinoEntrouCasa();
			pinos[1]=new Pino("pin1.png",casas[0]);
			pinos[1].pinoEntrouCasa();
			pinos[2]=new Pino("pin2.png",casas[0]);
			pinos[2].pinoEntrouCasa();
		}
		else if(qtd==4) {
			pinos[0]=new Pino("pin0.png",casas[0]);
			pinos[0].pinoEntrouCasa();
			pinos[1]=new Pino("pin1.png",casas[0]);
			pinos[1].pinoEntrouCasa();
			pinos[2]=new Pino("pin2.png",casas[0]);
			pinos[2].pinoEntrouCasa();
			pinos[3]=new Pino("pin3.png",casas[0]);
			pinos[3].pinoEntrouCasa();
		}
		else if(qtd==5) {
			pinos[0]=new Pino("pin0.png",casas[0]);
			pinos[0].pinoEntrouCasa();
			pinos[1]=new Pino("pin1.png",casas[0]);
			pinos[1].pinoEntrouCasa();
			pinos[2]=new Pino("pin2.png",casas[0]);
			pinos[2].pinoEntrouCasa();
			pinos[3]=new Pino("pin3.png",casas[0]);
			pinos[3].pinoEntrouCasa();
			pinos[4]=new Pino("pin4.png",casas[0]);
			pinos[4].pinoEntrouCasa();
		}
		
		else if(qtd==6) {
			pinos[0]=new Pino("pin0.png",casas[0]);
			pinos[0].pinoEntrouCasa();
			pinos[1]=new Pino("pin1.png",casas[0]);
			pinos[1].pinoEntrouCasa();
			pinos[2]=new Pino("pin2.png",casas[0]);
			pinos[2].pinoEntrouCasa();
			pinos[3]=new Pino("pin3.png",casas[0]);
			pinos[3].pinoEntrouCasa();
			pinos[4]=new Pino("pin4.png",casas[0]);
			pinos[4].pinoEntrouCasa();
			pinos[5]=new Pino("pin5.png",casas[0]);
			pinos[0].pinoEntrouCasa();
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
			gd2.drawImage(pinos[i].getImage(),pinos[i].getXPino(),pinos[i].getYPino(),null);
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
}
