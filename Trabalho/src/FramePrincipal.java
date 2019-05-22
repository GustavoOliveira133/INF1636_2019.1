import java.awt.*;
import javax.swing.*;
public class FramePrincipal extends JFrame {
	public final int LARG_TAB_DEFAULT=1025;
	public final int ALT_TAB_DEFAULT=1055;
	public final int LARG_MENU_DEFAULT=500;
	public final int ALT_MENU_DEFAULT=500;
	
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
		setBounds(x,y,LARG_MENU_DEFAULT,ALT_MENU_DEFAULT);
		/*--------------------------------------------------*/
		getContentPane().add(new Menu());
		
		setBounds(x,y,LARG_TAB_DEFAULT,ALT_TAB_DEFAULT);
		getContentPane().add(new Tabuleiro());
		setVisible(true);
		

		
	}
}
