import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

public class Tabuleiro extends JPanel {
	private Tabuleiro t=this;
	private Image tabuleiro=null, teste=null;
	private Dados d[]=new Dados[6];
	private Pino pinos[]=null;
    private int dados[]=new int[2];
    private Controlador ctrl;

	
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
			pinos[0]=new Pino("pin0.png",880,880);
			pinos[1]=new Pino("pin1.png",915,880);
		}
		else if (qtd==3) {
			pinos[0]=new Pino("pin0.png",880,880);
			pinos[1]=new Pino("pin1.png",915,880);
			pinos[2]=new Pino("pin2.png",950,880);
		}
		else if(qtd==4) {
			pinos[0]=new Pino("pin0.png",880,880);
			pinos[1]=new Pino("pin1.png",915,880);
			pinos[2]=new Pino("pin2.png",950,880);
			pinos[3]=new Pino("pin3.png",880,930);
		}
		else if(qtd==5) {
			pinos[0]=new Pino("pin0.png",880,880);
			pinos[1]=new Pino("pin1.png",915,880);
			pinos[2]=new Pino("pin2.png",950,880);
			pinos[3]=new Pino("pin3.png",880,930);
			pinos[4]=new Pino("pin4.png",915,930);
		}
		else if(qtd==6) {
			pinos[0]=new Pino("pin0.png",880,880);
			pinos[1]=new Pino("pin1.png",915,880);
			pinos[2]=new Pino("pin2.png",950,880);
			pinos[3]=new Pino("pin3.png",880,930);
			pinos[4]=new Pino("pin4.png",915,930);
			pinos[5]=new Pino("pin5.png",950,930);
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
			gd2.drawImage(pinos[i].getImage(),pinos[i].getXPino()-800,pinos[i].getYPino(),null);
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
