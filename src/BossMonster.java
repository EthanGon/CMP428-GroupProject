import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BossMonster extends Monster {
    public BossMonster(int x, int y) {
        super(x, y, 128, 128);
        maxHp = 500;
        currentHp = 500;
        moveSpeed = 1;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        int screenX = Camera.getInstance().projectX(x);
        int screenY = Camera.getInstance().projectY(y);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(screenX - w/2, screenY - h/2, w, h);
    }
}