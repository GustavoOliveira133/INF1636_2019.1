import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pino {
	private Image i=null;
	private double x, y;
	
	public Pino(String s,double w, double z) {
		x=w;
		y=z;
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
	public double getXPino() {
		return x;
	}
	public double getYPino() {
		return y;
	}
}
