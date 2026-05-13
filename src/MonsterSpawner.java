import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;



public class MonsterSpawner {

	private int spawnTime = 4; // seconds
	private int timer = 0;
	private static MonsterSpawner enemySpawner;
	private ArrayList<Monster> activeMobs = new ArrayList<Monster>();
	private ArrayList<Monster> deadEnemies = new ArrayList<Monster>();
	private int killCount = 0;


	
	public MonsterSpawner() {
		this.enemySpawner = this;
		
	}
	
	public void draw(Graphics g) {
		for (Monster mob : activeMobs) {
			if (!mob.isDead()) {
				mob.draw(g);
			}
		}
		processDeadEnemies();
		
		
	}
	
	 public void processDeadEnemies() {
	        for (Monster mob : activeMobs) {
	            if (mob.isDead()) deadEnemies.add(mob);
	        }
	        for (Monster deadMob : deadEnemies) {
	            activeMobs.remove(deadMob);
	        }
	        deadEnemies.clear();
	    }
	
	// pseudo timer for spawning, not perfect since it can either be slightly above or below a second.
	public void processSpawner() {
		if (timer >= 57 * spawnTime) { 
			int[] enemyPos = randomEnemyPos();
			//Adjusted constructor to match for animation and sprite.
			//Monsters get drawn. WF.
			activeMobs.add(new Monster(enemyPos[0], enemyPos[1], 64, 64));
			timer = 0;
		}
		timer++;
		
		Player player = Player.getPlayer();
		player.updateInvincibility(); 
		player.updateShooting(activeMobs);
		
		for (Monster mob : activeMobs) {	
			if (!mob.isDead()) {
				mob.chase(player);
			
				if (mob.getRect().overlaps(player.getRect())) {
					player.takeDamage(10);
				}
			
				for (Projectile p : player.getProjectiles()) {
					if (!p.expired && p.getRect().overlaps(mob.getRect())) {
						mob.killEnemy();
						p.expired = true;
						player.addXP(5);
						killCount++;
					}
				}
			
			}
		}
		processDeadEnemies();
		
	}
	
	
	/* First sets the spawn point to be at the same position of player.
	 * Randomly picks a line on the border of the screen.
	 * Then picks a random points within that line to spawn the enemy. 
	 * */
	public int[] randomEnemyPos() {
		int newX = Player.getPlayer().x;
		int newY = Player.getPlayer().y;
		int rangeOffset = 50;
		
		Random rand = new Random();
		int xOffset = rand.nextInt(Camera.getInstance().screenWidth);
		int yOffset = rand.nextInt(Camera.getInstance().screenHeight);
		
		int[] result = new int[2];
		int direction = rand.nextInt(4);
	
		if (direction == 0 || direction == 2) { // Random enemy spawn point above or below screen
			newX = (newX - 32) - Camera.getInstance().screenWidth/2;
			
			
			result[0] = newX + xOffset;
			
			if (direction == 0) { // above
				result[1] = (newY - Camera.getInstance().screenHeight/2) - rangeOffset;
			} else { // below
				result[1] = (newY + Camera.getInstance().screenHeight/2) + rangeOffset;
			}
			

		} else { // spawn enemy left or right of screen
			newY = (newY - 32) - Camera.getInstance().screenHeight/2;
			
			result[1] = newY + yOffset;
			
			if (direction == 1) { // left 
				result[0] = (newX - Camera.getInstance().screenWidth/2) - rangeOffset;
			} else { // right
				result[0] = (newX + Camera.getInstance().screenWidth/2) + rangeOffset;
			}
		
			
		}
		
		return result;
		
	}
	
	public static MonsterSpawner getInstance() {return enemySpawner;}
	public int getKillCount() { return killCount;}
	// used for testing processDeadEnemies method
	public void killFirstEnemy() {this.activeMobs.get(0).toggleDeadState(true);
	}
	
}
