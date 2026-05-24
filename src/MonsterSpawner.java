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
	private WaveManager waveManager;


	
	public MonsterSpawner() {
	    this.enemySpawner = this;
	    this.waveManager = new WaveManager();
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
		waveManager.update();
		if (timer >= 57 * waveManager.getSpawnTime()) {
		    for (int i = 0; i < waveManager.getSpawnCount(); i++) {
		        int[] pos = randomEnemyPos();
		        activeMobs.add(spawnMonsterByType(pos[0], pos[1]));
		    }
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
					    mob.takeDamage(p.getDamage());
					    p.expired = true;
					    if (mob.isDead()) {
					        player.addXP(getXPForMonster(mob));
					        killCount++;
					    }
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
	
	private Monster spawnMonsterByType(int x, int y) {
	    int type = waveManager.getMonsterType();
	    switch (type) {
	        case 1: return new FastMonster(x, y);
	        case 2: return new TankMonster(x, y);
	        case 3: return new BossMonster(x, y);
	        default: return new Monster(x, y, 64, 64);
	    }
	}

	
	private int getXPForMonster(Monster mob) {
	    if (mob instanceof BossMonster) return 100;
	    if (mob instanceof TankMonster) return 25;
	    if (mob instanceof FastMonster) return 10;
	    return 5;
	}

	public WaveManager getWaveManager() { return waveManager; }
	
	public static MonsterSpawner getInstance() {return enemySpawner;}
	public int getKillCount() { return killCount;}
	// used for testing processDeadEnemies method
	public void killFirstEnemy() {this.activeMobs.get(0).toggleDeadState(true);
	}
	
}
