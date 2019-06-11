package gui;
import java.awt.*;
import javax.swing.*;
import regras.*;

public class FramePrincipal extends JFrame {
	public final int LARG_TAB_DEFAULT=1000;
	public final int ALT_TAB_DEFAULT=1055;
	public final int LARG_MENU_DEFAULT=500;
	public final int ALT_MENU_DEFAULT=500;
	private Tabuleiro t;
	private Menu m;
	private MenuInferior menuInf;
	
	
	public FramePrincipal() {
		setTitle("Banco Imobiliário");		
		setDefaultCloseOperation(EXIT_ON_CLOSE);  
		
		/* Posicionamento da janela no centro do monitor */
		
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_TAB_DEFAULT/2;
		int y=sa/2-ALT_TAB_DEFAULT/2;	  
		
		/*--------------------------------------------------*/
		this.setResizable(false);
		t=new Tabuleiro();
		t.setLayout(null);
		m=new Menu(t);
		m.setLayout(null);
		menuInf=new MenuInferior(t,m);
		menuInf.setLayout(null);
		setBounds(x-300,y,LARG_TAB_DEFAULT+LARG_MENU_DEFAULT,ALT_TAB_DEFAULT);
		getContentPane().add(m);
		getContentPane().add(menuInf);
		getContentPane().add(t);
		
		
		
		setVisible(true);
		

		
	}

}