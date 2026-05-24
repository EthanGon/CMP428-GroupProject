import java.awt.Color;
import java.awt.Graphics;

public class TankMonster extends Monster {
    public TankMonster(int x, int y) {
        super(x, y, 96, 96);
        maxHp = 150;
        currentHp = 150;
        moveSpeed = 1;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        int screenX = Camera.getInstance().projectX(x);
        int screenY = Camera.getInstance().projectY(y);
        g.setColor(Color.BLUE);
        g.drawRect(screenX - w/2, screenY - h/2, w, h);
    }
}