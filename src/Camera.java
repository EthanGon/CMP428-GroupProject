
public class Camera {
	public int x;
	public int y;
	public static Camera instance;
	public int screenWidth = 1280;
	public int screenHeight = 720;
	
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
	 * 
	 * ONE ISSUE: this doesnt account for screen size switch, so either keep fixed size, or in game loop set screenWidth/HEIGHT 
	 * to current applet size w/e size doesn't match 
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

}
