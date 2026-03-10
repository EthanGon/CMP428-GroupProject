import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Toolkit;
import java.awt.Frame;


public class Game extends Applet implements Runnable, KeyListener {
	private Thread thread;
	private Camera mainCam;
	private Image doubleBuffer;
	private ChunkManager worldManager;
	private Player player;//
	private Enemy e1 = new Enemy(0,0);
	private  enum game_state {paused , playing};
	game_state curr_state ;
	
	private Image pause_img;
	
	
	public void init() {


		player = new Player(this, 0, 0, 64, 64);//
		
		mainCam = new Camera();
		mainCam.setPosition(player.x, player.y);
		worldManager = new ChunkManager();
		
		this.setSize(1280, 720);
		pause_img = Toolkit.getDefaultToolkit().getImage("dark_paused.png");

		curr_state = game_state.playing;
		thread = new Thread(this);
		thread.start();
		
		addKeyListener(this);
		requestFocus();

		
	}
	
	
	// Ideally world should be drawn before anything object, and UI stuff should be drawn after those objects
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		worldManager.draw(g);
		
		// DRAW OBJECTS BELOW HERE (ensures world is drawn before objects)
		g.setColor(Color.red);
		player.draw(g);
		e1.draw(g);
		
		
	/*****	alternate pause screen using a image 	*****
	 ***** comment out showUpgradeMenu() in pause()	*****
	 *****  before testing this out ,               *****   \/
	 
		if(curr_state == game_state.paused) {
			g.setColor (Color.green );
			g.drawImage(pause_img, (getWidth() /2 ) -240,   (getHeight() /2)-250 , 479,499,null);

			Rect pause_rect = new Rect ( (getWidth() /2 ) -240 , (getHeight() /2)-250 , 480,500);
		
			pause_rect.set_show_origin(false);
			pause_rect.draw(g);
		
		}

		**/

		// DRAW OBJECTS ABOVE HERE 
		
		
		
		mainCam.drawCameraBounds(g);
		drawControls(g);
	}

	
	public void run() {
		
		while (true) {
			if(curr_state!= game_state.paused) {
				movePlayer();
				updateCameraPosition();
				checkCameraBounds();
			}
			
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

		//WF 3/5/26 pause functions that will pause the game for the upgradeMenu
//	public void pauseGame() {
//		paused = true;
//	}
	
	public void unpause() {
		
		curr_state = game_state.playing;
		System.out.println("game unpaused");	

	}
	
	public boolean check_paused() {
		return (curr_state== game_state.paused );
		
	}
	

	
	public void showUpgradeMenu() {

	    UpgradeMenu menu = new UpgradeMenu(
	        null,
	        player.getHealthUpgrade(),
	        player.getStrengthUpgrade(),
	        player.getDefenseUpgrade(),
	        this::unpause
	    );

	    menu.setVisible(true);
	}/////WF

	@Override
	public void keyPressed(KeyEvent e) {
		if ( curr_state == game_state.paused ) 
			return;
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_D) {player.RIGHT = true;}
		if (key == KeyEvent.VK_A) {player.LEFT = true;}
		if (key == KeyEvent.VK_W) {player.UP = true;}
		if (key == KeyEvent.VK_S) {player.DOWN = true;}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if ( curr_state == game_state.paused ) 
			return;
		int key = e.getKeyCode();

		
		if (key == KeyEvent.VK_D) player.RIGHT = false;
		if (key == KeyEvent.VK_A) player.LEFT = false;
		if (key == KeyEvent.VK_W) player.UP = false;
		if (key == KeyEvent.VK_S) player.DOWN = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		int key = e.getExtendedKeyCode();
		
		if (!(key== KeyEvent.VK_ESCAPE) ) 
			return;
		
		if(curr_state == game_state.playing) {
		pause();
		return;
		}
		unpause();
		
	}

	private  synchronized void pause() {
		
		 curr_state = game_state.paused ;
			 System.out.println("game paused");
			 player.RIGHT = false;
			 player.LEFT = false;
			 player.UP = false;
			 player.DOWN = false;
			showUpgradeMenu();
		
		
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
		
		g.drawString("Move: WASD\tESC to pause", 10, 40);
	}
	
}
