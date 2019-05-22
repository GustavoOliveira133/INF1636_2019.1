import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
public class Menu extends JPanel{
	private Image teste=null;
	int jogadores;
	
	public Menu() {
		try {
			teste=ImageIO.read(new File("chance1.png"));
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	//segurar ate clicarem quantos jogadores
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gd2=(Graphics2D) g;
		gd2.drawImage(teste,0,0,null);
	}
}