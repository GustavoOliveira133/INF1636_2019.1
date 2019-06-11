package gui;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class Dados {
	private static Random aleatorio =new Random();
	private Image i=null;
	private boolean flag=false;
	private boolean repetido=false;
	
	public Dados(String s) {
		try {
			i=ImageIO.read(new File(s));
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	public Image getImage() {
		return i;
	}
	
	public static int rolaDados() {
		return aleatorio.nextInt(6);
	}
	public void setFlag() {
		flag=true;
	}
	public void unsetFlag() {
		flag=false;
	}
	public void setRepetido() {
		repetido=true;
	}
	public void unsetRepetido() {
		repetido=false;
	}
	public boolean getFlag() {
		return flag;
	}
	public boolean getRepetido() {
		return repetido;
	}
}
