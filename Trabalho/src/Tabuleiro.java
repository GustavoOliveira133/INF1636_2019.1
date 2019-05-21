import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

public class Tabuleiro extends JPanel {
	private Tabuleiro t=this;
	private Image tabuleiro=null;
	private Pino pinos[]=new Pino[5];

	
	public Tabuleiro() {
		try {
			tabuleiro=ImageIO.read(new File("tabuleiroRJ.jpg"));
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		pinos[0]=new Pino("pin0.png",880.0,880.0);
		pinos[1]=new Pino("pin1.png",915.0,880.0);
		pinos[2]=new Pino("pin2.png",950.0,880.0);
		pinos[3]=new Pino("pin3.png",880.0,930.0);
		pinos[4]=new Pino("pin4.png",915.0,930.0);


		addMouseListener(new MouseListener() {
    		public void mouseEntered(MouseEvent e) {}
    		public void mousePressed(MouseEvent e) {}
    		public void mouseReleased(MouseEvent e) {}
    		public void mouseExited(MouseEvent e) {}
    		public void mouseClicked(MouseEvent e) {
/* *********** Teste para a seleção dos pinos ****************************  */
    			double x=e.getX();
    			double y=e.getY();
    			if ((x >= (pinos[0].getXPino()) && x <= (pinos[0].getXPino()+25.0)) && (y >= (pinos[0].getYPino()) && y <= (pinos[0].getYPino()+35.0))) {
    				System.out.println("Pino 1 Selecionado");
    			}
/* ************* Fim teste seleção dos pinos ******************************** */
    			String msg=String.format("x=%.1f y=%.1f\n",x,y);
     			JOptionPane.showMessageDialog(t,msg);
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
	}
}
