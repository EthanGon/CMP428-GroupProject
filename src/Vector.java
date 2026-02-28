// A vector class to make it easier to manage positions instead of using arrays
public class Vector {
	int x;
	int y;
	
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isEqual(Vector v) {
		return this.x == v.x && this.y == v.y;
	}

}
