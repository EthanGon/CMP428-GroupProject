// A vector class to make it easier to manage positions instead of using arrays
public class Vector {
	int x;
	int y;
	
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    
	    if (!(o instanceof Vector)) return false;
	    Vector v = (Vector) o;
	    
	    
	    return x == v.x && y == v.y;
	}

	@Override
	public int hashCode() {
	    int result = Integer.hashCode(x);
	    result = 31 * result + Integer.hashCode(y);
	    return result;
	}


}
