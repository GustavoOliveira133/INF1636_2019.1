import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

public class Tabuleiro extends JPanel {
	private Tabuleiro t=this;
	private Image tabuleiro=null;
	private Image []pinos=new Image[5];
	
	public Tabuleiro() {
		try {
			tabuleiro=ImageIO.read(new File("tabuleiroRJ.jpg"));
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		try {
			pinos[0]=ImageIO.read(new File("pin0.png"));
			pinos[1]=ImageIO.read(new File("pin1.png"));
			pinos[2]=ImageIO.read(new File("pin2.png"));
			pinos[3]=ImageIO.read(new File("pin3.png"));
			pinos[4]=ImageIO.read(new File("pin4.png"));

		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
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
		gd2.drawImage(pinos[0],880,880,null);
		gd2.drawImage(pinos[1],915,880,null);
		gd2.drawImage(pinos[2],950,880,null);
		gd2.drawImage(pinos[3],880,930,null);
		gd2.drawImage(pinos[4],915,930,null);
	}
}
