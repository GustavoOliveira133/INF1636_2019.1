import java.awt.*;
import javax.swing.*;
public class FramePrincipal extends JFrame {
	public final int LARG_TAB_DEFAULT=1025;
	public final int ALT_TAB_DEFAULT=1055;
	public final int LARG_MENU_DEFAULT=500;
	public final int ALT_MENU_DEFAULT=500;
	private Tabuleiro t;
	
	public FramePrincipal(Controlador ctrl) {
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
		t=new Tabuleiro(ctrl);
		setBounds(x-300,y,LARG_TAB_DEFAULT+300,ALT_TAB_DEFAULT);
		getContentPane().add(new Menu(ctrl,t),BorderLayout.LINE_END);
		getContentPane().add(t);
		
		
		
		setVisible(true);
		

		
	}

}
