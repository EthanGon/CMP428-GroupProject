import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Tile {
	private int x;
	private int y;
	Image bgImage = new ImageIcon("t1.png").getImage();
	private Tile north;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		int size = 64 * 15;
		
		
		int screenX = Camera.getInstance().projectX(x);
		int screenY = Camera.getInstance().projectY(y);
		
		
		
//		g.drawImage(bgImage, screenX - (Camera.getInstance().screenWidth/2), screenY - (Camera.getInstance().screenHeight/2), null);
		g.drawImage(bgImage, screenX - size/2, screenY - size/2, size, size, null);
		
		g.setColor(Color.red);
		Rect topColl = new Rect(screenX - size/2, screenY - size/2, size, 300);
		
		
		
		topColl.draw(g);
		
	}
	
	
	

}
