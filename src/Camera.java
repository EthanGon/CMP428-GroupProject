
public class Camera {
	public int x;
	public int y;
	public static Camera instance;
	private final int SCREEN_WIDTH = 1280;
	private final int SCREEN_HEIGHT = 720;
	
	public Camera() {
		x = SCREEN_WIDTH/2;
		y = SCREEN_HEIGHT/2;
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
	

}
