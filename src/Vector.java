
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
