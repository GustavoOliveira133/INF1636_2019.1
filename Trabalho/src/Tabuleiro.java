import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

public class Tabuleiro extends JPanel {
	private Tabuleiro t=this;
	private Image tabuleiro=null;
	private Dados d[]=new Dados[6];
	private Pino pinos[]=new Pino[5];
    private int dados[]=new int[2];

	
	public Tabuleiro() {
		try {
			tabuleiro=ImageIO.read(new File("tabuleiroRJ.jpg"));
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		//Criando os pinos
		pinos[0]=new Pino("pin0.png",880.0,880.0);
		pinos[1]=new Pino("pin1.png",915.0,880.0);
		pinos[2]=new Pino("pin2.png",950.0,880.0);
		pinos[3]=new Pino("pin3.png",880.0,930.0);
		pinos[4]=new Pino("pin4.png",915.0,930.0);
		
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
/* *********** Teste para a seleção dos pinos ****************************  */
    			double x=e.getX();
    			double y=e.getY();
    			for (int i=0;i<5;i++) {
    				if ((x >= (pinos[i].getXPino()) && x <= (pinos[i].getXPino()+25.0)) && (y >= (pinos[i].getYPino()) && y <= (pinos[i].getYPino()+35.0))) {
        				System.out.printf("Pino %d Selecionado\n",i+1);
        				
        				//Janela de diálogo para confirmar a seleção do pino
        				Object[] options = { "Confirmar", "Cancelar" };
        				int opcao = JOptionPane.showOptionDialog(t, "Deseja andar com o pino selecionado?", "Confirmar seleção de pino", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        				if(opcao==1 || opcao==-1) {
        					break;
        				}

        				
        				//Rolando os dados
        				dados[0]=Dados.rolaDados();
        				dados[1]=Dados.rolaDados();
        				
        				//Pintar os dados no tabuleiro
        				if(dados[0]!=dados[1]) {
            				d[dados[0]].setFlag();
            				repaint();
            				d[dados[1]].setFlag();
            				repaint();
        				}
        				else {
            				d[dados[0]].setFlag();
        					d[dados[0]].setRepetido();
        					repaint();
        				}
        				//Andar o pino (atualizar o tabuleiro)
        				
        				//Mostrar coordenadas clicadas
        				String msg1=String.format("Valor rolado nos dados:%d\n",(dados[0]+dados[1])+2);
        				JOptionPane.showMessageDialog(t, msg1);
        			}
    				d[dados[0]].unsetFlag();
    				d[dados[1]].unsetFlag();
    				d[dados[0]].unsetRepetido();
    			}
/* ************* Fim teste seleção dos pinos ******************************** */
    			//String msg=String.format("x=%.1f y=%.1f\n",x,y);
     			//JOptionPane.showMessageDialog(t,msg);
    		}
    	});
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gd2=(Graphics2D) g;
		gd2.drawImage(tabuleiro,0,0,null);
		gd2.drawImage(pinos[0].getImage(),880,880,null);
		gd2.drawImage(pinos[1].getImage(),915,880,null);
		gd2.drawImage(pinos[2].getImage(),950,880,null);
		gd2.drawImage(pinos[3].getImage(),880,930,null);
		gd2.drawImage(pinos[4].getImage(),915,930,null);
		int p=0;
		for(int i=0;i<6;i++) {
			if(d[i].getFlag()==true) {
				if(d[i].getRepetido()==true) {
					gd2.drawImage(d[i].getImage(),150,565,null);
					gd2.drawImage(d[i].getImage(),550,565,null);
					break;
				}
				else if (p==0) {
					gd2.drawImage(d[i].getImage(),150,565,null);
					p=1;
				}
				else if (p==1)
					gd2.drawImage(d[i].getImage(),550,565,null);
			}
		}
	}
}
