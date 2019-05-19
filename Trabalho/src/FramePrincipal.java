import java.awt.*;
import javax.swing.*;
public class FramePrincipal extends JFrame {
	public final int LARG_DEFAULT=1025;
	public final int ALT_DEFAULT=1055;
	
	public FramePrincipal() {
		setTitle("Banco Imobiliário");		
		 setDefaultCloseOperation(EXIT_ON_CLOSE);  
		
		/* Posicionamento da janela no centro do monitor */
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;	  
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		/*--------------------------------------------------*/
		getContentPane().add(new Tabuleiro());
		setVisible(true);
		

		
	}
}
