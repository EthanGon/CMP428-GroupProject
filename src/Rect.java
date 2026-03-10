import java.awt.*;

public class Rect {
    int x;
    int y;

    int w;
    int h;
	boolean show_origin = true;



    public Rect(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

 
    public void draw(Graphics g) {

    	if(show_origin)
        g.drawString(String.format("(%d, %d)", x,y), x, y);

        g.drawRect(x, y, w, h);
    }
    public void set_show_origin(boolean val) {
    	show_origin = val;
    }

    public boolean overlaps(Rect other) {
        return (  this.x <= other.x + other.w) && // this is left of other
                (other.x <=  this.x +  this.w) && //
                ( this.y <= other.y + other.h) &&
                (other.y <=  this.y +  this.h);

    }

    public boolean contains(int mx, int my) {
        return  (mx > x)   &&
                (mx < x+w) &&
                (my > y)   &&
                (my < y+h);
    }

 

}

