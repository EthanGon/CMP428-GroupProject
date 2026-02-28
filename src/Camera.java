import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Camera {
	public int x;
	public int y;
	public static Camera instance;
	public int screenWidth = 1280;
	public int screenHeight = 720;
	Rect camBounds = new Rect(0,0, screenWidth, screenHeight);
	
	public Camera() {
		x = screenWidth/2;
		y = screenHeight/2;
		instance = this;
		System.out.printf("Camera Position: x=%d, y=%d\n", x, y);
	}
	
	public static Camera getInstance() {
		return instance;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPositionX(int x) {
		this.x = x;
	}
	
	public void setPositionY(int y) {
		this.y = y;
	}
	
	
	/* Used to convert object x/y values to be correct value based on camera position and screen dimension
	 * Made these functions so you don't have to worry about correct value when drawing
	 * */
	public int projectX(int x) {
		return x - this.x + screenWidth/2; 
	}
	
	public int projectY(int y) {
		return y - this.y + screenHeight/2; 
	}
	
	public void updateScreenSize(int w, int h) {
		this.screenWidth = w;
		this.screenHeight = h;
	}
	
	public void drawCameraBounds(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.magenta);
		
		float boundsThickness = 5.0f;
		g2.setStroke(new BasicStroke(boundsThickness));
		
		
		camBounds.draw(g2);
		
	}

}
