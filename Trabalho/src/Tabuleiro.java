import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

public class Tabuleiro extends JPanel {
	private Tabuleiro t=this;
	private Image tabuleiro=null;
	private Pino pino1;
	private Pino pino2;
	private Pino pino3;
	private Pino pino4;
	private Pino pino5;
	
	public Tabuleiro() {
		try {
			tabuleiro=ImageIO.read(new File("tabuleiroRJ.jpg"));
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		pino1=new Pino("pin0.png",880,880);
		pino2=new Pino("pin1.png",915,880);
		pino3=new Pino("pin2.png",950,880);
		pino4=new Pino("pin3.png",880,930);
		pino5=new Pino("pin4.png",915,930);


		addMouseListener(new MouseListener() {
    		public void mouseEntered(MouseEvent e) {}
    		public void mousePressed(MouseEvent e) {}
    		public void mouseReleased(MouseEvent e) {}
    		public void mouseExited(MouseEvent e) {}
    		public void mouseClicked(MouseEvent e) {
    			String msg=String.format("x=%d y=%d\n",e.getX(),e.getY());
     			JOptionPane.showMessageDialog(t,msg);
    		}
    	});
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gd2=(Graphics2D) g;
		gd2.drawImage(tabuleiro,0,0,null);
		gd2.drawImage(pino1.getImage(),880,880,null);
		gd2.drawImage(pino2.getImage(),915,880,null);
		gd2.drawImage(pino3.getImage(),950,880,null);
		gd2.drawImage(pino4.getImage(),880,930,null);
		gd2.drawImage(pino5.getImage(),915,930,null);
	}
}
