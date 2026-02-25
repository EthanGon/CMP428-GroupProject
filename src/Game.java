import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;


public class Game extends Applet implements Runnable, KeyListener {
	
	Thread thread;
	Camera mainCam;
	Tile[] tiles;
	Image db;

	
	
	Player player = new Player(0,0,50,50);

	public void init() {
		this.setSize(1280, 720);
		
		mainCam = new Camera();
		mainCam.setPosition(player.x, player.y);
	
		
		
		tiles = new Tile[3];
		tiles[0] = new Tile(640,0);
		tiles[1] = new Tile(0,640);
		tiles[2] = new Tile(0,-640);
		
		
		thread = new Thread(this);
		thread.start();
		
		addKeyListener(this);
		requestFocus();

	}
	
		public void update(Graphics g) {
	    
		Dimension size = getSize();
		if (db == null || db.getWidth(this) != size.width || db.getHeight(this) != size.height) {
			db = createImage(size.width, size.height); // create buffer/image that matches screen
		}
		
		
		
		if (db != null) {
			Graphics g2 = db.getGraphics();
			paint(g2);
			g2.dispose();
			g.drawImage(db, 0, 0, null);
		} else {
			
			paint(g);
		}
		
		
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		
		tiles[0].draw(g);
		tiles[1].draw(g);
		tiles[2].draw(g);

		
		g.setColor(Color.red);
		player.draw(g);
	}

	
	public void run() {
		
		
		while (true) {
			
			if (player.RIGHT) player.x += player.moveSpeed;
			if (player.LEFT) player.x -= player.moveSpeed;
			if (player.UP) player.y -= player.moveSpeed;
			if (player.DOWN) player.y += player.moveSpeed;
		
			
			if (player.x >= 0) {
				mainCam.setPositionX(player.x);
			}
			
			if (player.y <= 640) {
				mainCam.setPositionY(player.y);
			}
			
			
			repaint();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_D) {player.RIGHT = true;}
		if (key == KeyEvent.VK_A) {player.LEFT = true;}
		if (key == KeyEvent.VK_W) {player.UP = true;}
		if (key == KeyEvent.VK_S) {player.DOWN = true;}
		System.out.printf("Camera Position: x=%d, y=%d\n", mainCam.x, mainCam.y);
		System.out.printf("Player Position: x=%d, y=%d\n", player.x, player.y);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_D) player.RIGHT = false;
		if (key == KeyEvent.VK_A) player.LEFT = false;
		if (key == KeyEvent.VK_W) player.UP = false;
		if (key == KeyEvent.VK_S) player.DOWN = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	// if I want to limit camera movement, I could say 
	// if 
	
	

}
