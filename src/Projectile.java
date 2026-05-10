import java.awt.Color;
import java.awt.Graphics;

public class Projectile {
    public int x, y;
    private float velX, velY;
    private int speed = 8;
    private int size = 10;
    public boolean expired = false;
    private int damage;

    public Projectile(int startX, int startY, int targetX, int targetY, int damage) {
        this.x = startX;
        this.y = startY;
        this.damage = damage;

        // Calculate direction toward target
        float dx = targetX - startX;
        float dy = targetY - startY;
        float dist = (float) Math.sqrt(dx * dx + dy * dy);
        if (dist != 0) {
            velX = (dx / dist) * speed;
            velY = (dy / dist) * speed;
        }
    }

    public void update() {
        x += velX;
        y += velY;
    }

    public void draw(Graphics g) {
        int screenX = Camera.getInstance().projectX(x);
        int screenY = Camera.getInstance().projectY(y);
        g.setColor(Color.YELLOW);
        g.fillOval(screenX - size/2, screenY - size/2, size, size);
    }

    public Rect getRect() {
        return new Rect(x - size/2, y - size/2, size, size);
    }

    public int getDamage() { return damage; }
}