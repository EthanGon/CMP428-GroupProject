import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Player extends Mob {

    //int w;
    //int h;
    public boolean UP, DOWN, LEFT, RIGHT;
    public int moveSpeed = 5;
    public static Player playerInstance;

    // XP Progress
    public int level = 1;
    public int xp = 0;
    public int xpToNextLv = 10;

    // Health
    public int currentHealth;
    public boolean invincible = false;
    private int invincibleTimer = 0;

    // Shooting
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private int shootTimer = 0;
    private int shootCooldown = 40;

    // Upgrades
    private HealthUpgrade health = new HealthUpgrade();
    private StrengthUpgrade strength = new StrengthUpgrade();
    private DefenseUpgrade defense = new DefenseUpgrade();
    private Game game;

	final static String[] WALK_POSE = {
		"Player_R/Player_R",
		"Player_R/Player_R",
		"Player_L/Player_L",
		"Player_R/Player_R"
	};


    public Player(Game game, int x, int y, int w, int h) {
		//load images 
		super("../images/Player", x, y, w, h, 1, WALK_POSE);
        this.game = game;
        playerInstance = this;
        //this.x = x;
        //this.y = y;
        //this.w = w;
        //this.h = h;
        this.currentHealth = health.getCurrentHealth();
    }
	
	public static Player getPlayer() {
		return playerInstance;
	}
	
	/* Every object that uses the draw method need to follow this format
	 * screenX/Y ensure they're located visually on the screen, but they still keep they're real X/Y values
	 * 
	 * screenX - objectWidth, ScreenY - objectHeight 
	 * */
	
	@Override
    public void draw(Graphics g) {
		super.draw(g);
        /*
		*Removed to allow for player image to be drawn.
		int screenX = Camera.getInstance().projectX(x);
        int screenY = Camera.getInstance().projectY(y);
        g.setColor(Color.RED);
        g.fillRect(screenX - w/2, screenY - h/2, w, h);
		*/
        drawHUD(g);
    }

    public void drawHUD(Graphics g) {
        int barWidth = 200;
        int barHeight = 20;
        int barX = 10;
        int barY = Camera.getInstance().screenHeight - 40;

        int maxHealth = health.getCurrentHealth();
        float hpPercent = (float) currentHealth / maxHealth;

        // Health bar background
        g.setColor(Color.RED);
        g.fillRect(barX, barY, barWidth, barHeight);

        // Current HP
        g.setColor(Color.GREEN);
        g.fillRect(barX, barY, (int)(barWidth * hpPercent), barHeight);

        // Border
        g.setColor(Color.WHITE);
        g.drawRect(barX, barY, barWidth, barHeight);
        g.drawString(currentHealth + " / " + maxHealth, barX + 5, barY + 15);

        // XP bar
        int xpBarY = barY - 25;
        g.setColor(Color.DARK_GRAY);
        g.fillRect(barX, xpBarY, barWidth, 15);
        g.setColor(Color.YELLOW);
        g.fillRect(barX, xpBarY, (int)(barWidth * getXPPercent()), 15);
        g.setColor(Color.WHITE);
        g.drawRect(barX, xpBarY, barWidth, 15);
        g.drawString("LVL " + level, barX + 5, xpBarY + 12);
    }

    public void drawProjectiles(Graphics g) {
        for (Projectile p : projectiles) {
            p.draw(g);
        }
    }

    public void takeDamage(int amount) {
        if (invincible) return;
        currentHealth -= amount;
        invincible = true;
        invincibleTimer = 0;
        System.out.println("Player hit! HP: " + currentHealth);
        if (currentHealth <= 0) {
            System.out.println("Player is dead!");
            // TODO: game over screen
        }
    }

    public void updateInvincibility() {
        if (invincible) {
            invincibleTimer++;
            if (invincibleTimer >= 90) {
                invincible = false;
            }
        }
    }

    public void updateShooting(ArrayList<Monster> monsters) {
        shootTimer++;
        if (shootTimer >= shootCooldown) {
            Monster target = findNearestMonster(monsters);
            if (target != null) {
                int damage = strength.getCurrentStrength() * 2;
                projectiles.add(new Projectile(x, y, target.x, target.y, damage));
                shootTimer = 0;
            }
        }

        ArrayList<Projectile> expired = new ArrayList<>();
        for (Projectile p : projectiles) {
            p.update();
            float dist = (float) Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
            if (dist > 1000) p.expired = true;
            if (p.expired) expired.add(p);
        }
        projectiles.removeAll(expired);
    }

    private Monster findNearestMonster(ArrayList<Monster> monsters) {
        Monster nearest = null;
        float minDist = Float.MAX_VALUE;
        for (Monster m : monsters) {
            if (m.isDead()) continue;
            float dist = (float) Math.sqrt((m.x - x) * (m.x - x) + (m.y - y) * (m.y - y));
            if (dist < minDist) {
                minDist = dist;
                nearest = m;
            }
        }
        return nearest;
    }
	
	public ArrayList<Projectile> getProjectiles(){
		return projectiles;
		
	}
	
	public Rect getRect() {
		return new Rect(x - w / 2, y - h / 2, w, h);
	}
	
	public float getXPPercent() {
		return (float) xp / xpToNextLv;
	}
	
	public void addXP(int amount) {
		xp += amount;
		while(xp>=xpToNextLv) {
			xp -= xpToNextLv;
			levelUp();
		}
		
	}
	
	private void levelUp() {
		level++;
		xpToNextLv = (int)(10 * Math.pow(1.25, level - 1));		
		System.out.println("LEVEL UP! level: " + level);
		//added pause here to make upgrade menu only appear during level up. WF
		game.pause();
		game.showUpgradeMenu();
	}

	//Allows movement to be updated based on key input thus animation can cycle
	//WF.
	public void updateMovement(){
		if(UP){
			goUP(moveSpeed);
		}
		if(DOWN) {
			goDN(MoveSpeed);
		}
		if(LEFT) {
			goLT(moveSpeed);
		}
		if(RIGHT) {
			goRT(moveSpeed);
		}
	}
	
	//Helper functions to get the health, strength and defense upgrade
	public HealthUpgrade getHealthUpgrade() { return health;}
	public StrengthUpgrade getStrengthUpgrade() {return strength;}
	public DefenseUpgrade getDefenseUpgrade() {return defense;}
	
	public void printPosition() {
		System.out.printf("Player Position: x=%d, y=%d\n", x, y);
	}

	

}
