import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class Tile {
	private int x;
	private int y;
	private Image bgImage = new ImageIcon("t1.png").getImage();
	private Rect tileBounds;
	public Vector[] dir = new Vector[8];
	private HashMap<String, Vector> dirVector = new HashMap<String, Vector>();
	
	public ChunkSection[] collisionSections;
	int size = 64 * 15;
	
	
	public Tile(int x, int y) {

		this.x = x;
		this.y = y;
		
		tileBounds = new Rect(this.x, this.y, size, size);
		collisionSections = new ChunkSection[8];
		
		
		
		initDirectionNames();
		initDirections();
		
		
			
	}
	
	public void draw(Graphics g) {	
		int screenX = Camera.getInstance().projectX(x);
		int screenY = Camera.getInstance().projectY(y);
		
		
		// draws all the boxes on each cardinal and inter-cardinal directions
		g.drawImage(bgImage, screenX - size/2, screenY - size/2, size, size, null);
		displaySectionBox(collisionSections[0], 0, 0, 0, 100, g); // topCenter box
		displaySectionBox(collisionSections[1], 0, 0, -960 + 200, 100, g); // topLeft box
		displaySectionBox(collisionSections[2], 0, 0, -960 + 200, 860, g); // leftCenter box
		displaySectionBox(collisionSections[3], 0, size - (collisionSections[1].h), -960 + 200, 100, g); // bottomLeft box
		displaySectionBox(collisionSections[4], 0, size - (collisionSections[0].h), 0, 100, g); // bottomCenter box
		displaySectionBox(collisionSections[5], size - (collisionSections[2].w), size - (collisionSections[1].h), -960 + 200, 100, g); // bottomRight box
	    displaySectionBox(collisionSections[6], size - (collisionSections[2].w), 0, -960 + 200, 860, g); // right center box
	    displaySectionBox(collisionSections[7], size - (collisionSections[2].w), 0, -960 + 200, 100, g); // topRight box
	    drawTileBounds(g);
	    
	    
		
	}
	
	
	public void drawTileBounds(Graphics g) {
		// I know I'm rewriting this a lot but I'm lazy rn ok
		int screenX = Camera.getInstance().projectX(x);
		int screenY = Camera.getInstance().projectY(y);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.GREEN);
		float boundThickness = 5.0f;
		g2.setStroke(new BasicStroke(boundThickness));
		
	    tileBounds.x = screenX - size/2;
	    tileBounds.y = screenY - size/2;
	    tileBounds.draw(g);
	}
	
	
	
	public void displaySectionBox(ChunkSection section, int xOffset, int yOffset, int wOffset, int hOffset, Graphics g) {
		
		
		section.x = x - size/2 + xOffset;
		section.y = y - size/2 + yOffset;		
		section.w = size + wOffset;
		section.h = 100 + hOffset;
		
		int screenX = Camera.getInstance().projectX(section.x);
		int screenY = Camera.getInstance().projectY(section.y);
		
		
		
		g.setColor(Color.red);
		g.drawRect(screenX, screenY, section.w, section.h);
	}
	
	public int[] getPosition() {
		int[] pos = new int[2];
		pos[0] = x;
		pos[1] = y;
		
		return pos;
	}
	
	// used to convert tile's position to a vector
	public Vector getChunkVector() {
		Vector v = new Vector(x, y);
		
		return v;
	}
	
	public void spawnChunk(String dir) {
		
		Vector targetLocation = dirVector.get(dir);
		
		if (targetLocation == null) return;
		
		if (!ChunkManager.getInstance().doesChunkExist(targetLocation)) {
			ChunkManager.getInstance().addChunkToQueue(targetLocation);
		}
	}
	
	public void checkSections() {
		for (ChunkSection c : collisionSections) {
			c.withinCameraRange();
		}
	}
	
	public void initDirectionNames() {
		collisionSections[0] = new ChunkSection("topCenter", this);
		collisionSections[1] = new ChunkSection("topLeft", this);
		collisionSections[2] = new ChunkSection("leftCenter", this);
		collisionSections[3] = new ChunkSection("bottomLeft", this);
		collisionSections[4] = new ChunkSection("bottomCenter", this);
		collisionSections[5] = new ChunkSection("bottomRight", this);
		collisionSections[6] = new ChunkSection("rightCenter", this);
		collisionSections[7] = new ChunkSection("topRight", this);
	}
	
	public void initDirections() {
		dirVector.put("topCenter", 		new Vector(x, y - size)); 
		dirVector.put("topLeft", 		new Vector(x - size, y - size)); 
		dirVector.put("leftCenter",		new Vector(x - size, y)); 
		dirVector.put("bottomLeft", 	new Vector(x - size, y + size)); 
		dirVector.put("bottomCenter", 	new Vector(x, y + size)); 
		dirVector.put("bottomRight", 	new Vector(x + size, y + size)); 
		dirVector.put("rightCenter", 	new Vector(x + size, y)); 
		dirVector.put("topRight", 		new Vector(x + size, y - size));
	}
	
	public boolean touchingCamera() {
		return this.tileBounds.overlaps(Camera.getInstance().camBounds);
	}
	
	
	
	

}
