import java.awt.Graphics;

public abstract class Mob {
	int x;
	int y;
	
	
	/*  With how this is designed this needs to be written before g.draw() is called
	 * 
	 * 	int screenX = x - Camera.getInstance().x + SCREEN WIDTH /2;
		int screenY = y - Camera.getInstance().y + SCREEN HIGHT /2;
		
		This makes sure that the object's x/y will be correctly offset by camera
		TODO (not needed): Could update this code so this line isn't repeated
		
	 * */
	public abstract void draw(Graphics g);

}
