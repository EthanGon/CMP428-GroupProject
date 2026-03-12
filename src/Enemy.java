import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemy {
	
	private int x;
	private int y;
	private final int SIZE = 64;
    private Image enemySprite; 
	
	public Enemy(int x, int y) {
		this.enemySprite = new ImageIcon("textures/enemies/enemyTemp.png").getImage();
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		int screenX = Camera.getInstance().projectX(x); 
		int screenY = Camera.getInstance().projectY(y);
		
		g.drawImage(enemySprite, screenX - SIZE/2, screenY - SIZE/2, SIZE, SIZE, null); 
	}
	
	

}
