import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
public class Menu extends JPanel{
	private Menu m=this;
	private JButton jogador1 = null;
	private JButton jogador2 = null;
	private JButton jogador3 = null;
	private JButton jogador4 = null;
	private JButton jogador5 = null;
	private JButton jogador6 = null;
	int jogadores;
	
	public Menu(Controlador ctrl) {
		setLayout(new FlowLayout());
		jogador1=new JButton("1 jogador");
		jogador1.setLocation(0,0);
		this.add(jogador1);
		
		jogador2=new JButton("2 jogadores");
		jogador1.setLocation(0,200);
		this.add(jogador2);
		
		jogador3=new JButton("3 jogadores");
		jogador3.setBounds(0,300,100,100);
		this.add(jogador3);
		
		jogador4=new JButton("4 jogadores");
		jogador4.setBounds(0,400,100,100);
		this.add(jogador4);
		
		jogador5=new JButton("5 jogadores");
		jogador5.setBounds(0,500,100,100);
		this.add(jogador5);
		
		jogador6=new JButton("6 jogadores");
		jogador6.setBounds(0,600,100,100);
		this.add(jogador6);
		
		
		
		
		addMouseListener(new MouseListener() {
    		public void mouseEntered(MouseEvent e) {}
    		public void mousePressed(MouseEvent e) {}
    		public void mouseReleased(MouseEvent e) {}
    		public void mouseExited(MouseEvent e) {}
    		public void mouseClicked(MouseEvent e) {
/* *********** Teste para a seleÃ§Ã£o dos pinos ****************************  */
    			int x=e.getX();
    			int y=e.getY();
    		
/* ************* Fim teste seleÃ§Ã£o dos pinos ******************************** */
    			String msg=String.format("x=%d y=%d\n",x,y);
     			JOptionPane.showMessageDialog(m,msg);
    		}
    	});
		
	}
	
	
	


}