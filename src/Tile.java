import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Tile {
	private int x;
	private int y;
	Image bgImage = new ImageIcon("t1.png").getImage();
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		int screenX = Camera.getInstance().projectX(x);
		int screenY = Camera.getInstance().projectY(y);
		
		g.drawImage(bgImage, screenX - (1280/2), screenY - (720/2), null);
	}
	

}
