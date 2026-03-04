import java.awt.Graphics;

public class Enemy extends Mob {
	
	int size;
	
	public Enemy(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}

	@Override
	public void draw(Graphics g) {
		int screenX = Camera.getInstance().projectX(x); 
		int screenY = Camera.getInstance().projectY(y);
		
		g.drawRect(screenX - size/2, screenY - size/2, size, size);
	}

}
