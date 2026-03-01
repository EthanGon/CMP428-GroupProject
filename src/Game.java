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
	Tile[] tiles = new Tile[1];
	Image doubleBuffer;
	
	Player player = new Player(0,0,50,50);
	ChunkManager worldManager;
	
	

	public void init() {
		mainCam = new Camera();
		mainCam.setPosition(player.x, player.y);
		worldManager = new ChunkManager();
		
		this.setSize(1280, 720);
		
		
		thread = new Thread(this);
		thread.start();
		
		addKeyListener(this);
		requestFocus();

		
	}
	
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		worldManager.draw(g);
		
		g.setColor(Color.red);
		player.draw(g);
		
		mainCam.drawCameraBounds(g);
		drawControls(g);
	}

	
	public void run() {
		
		while (true) {
			
			movePlayer();
			updateCameraPosition();
			checkCameraBounds();
			
			
			repaint();
			
			// as of right now this is fixed, but later we could use deltaTime method
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
		
		if (key == KeyEvent.VK_0) {
			ChunkManager.getInstance().printMap();
		}
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_D) player.RIGHT = false;
		if (key == KeyEvent.VK_A) player.LEFT = false;
		if (key == KeyEvent.VK_W) player.UP = false;
		if (key == KeyEvent.VK_S) player.DOWN = false;
		
	}

	public void updateCameraPosition() {
		mainCam.setPosition(player.x, player.y);
	}
	
	public void checkCameraBounds() {
		// Ensures the camera will remain at the center of the screen even when the window changes size
		if (mainCam.screenWidth != this.getWidth() || mainCam.screenHeight != this.getHeight()) {
			mainCam.updateScreenSize(this.getWidth(), this.getHeight());
		}
	}
	
	public void movePlayer() {
		if (player.RIGHT) player.x += player.moveSpeed;
		if (player.LEFT) player.x -= player.moveSpeed;
		if (player.UP) player.y -= player.moveSpeed;
		if (player.DOWN) player.y += player.moveSpeed;
	}
	
	// draws onto the double buffer first, then when that is done, draw onto screen
	public void update(Graphics g) {
		
		Dimension size = getSize();
		if (doubleBuffer == null || doubleBuffer.getWidth(this) != size.width || doubleBuffer.getHeight(this) != size.height) {
			doubleBuffer = createImage(size.width, size.height); 
		}
			
		if (doubleBuffer != null) {
			Graphics g2 = doubleBuffer.getGraphics();
			paint(g2);
			g2.dispose();
			g.drawImage(doubleBuffer, 0, 0, null);
		} else {
				
			paint(g);
		}
			
	}
	
	public void drawControls(Graphics g) {
		Font newFont = g.getFont().deriveFont(25f);
		g.setFont(newFont);
		
		g.drawString("Move: WASD", 10, 40);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
}
