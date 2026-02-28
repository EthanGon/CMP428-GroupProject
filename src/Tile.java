import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class Tile {
	private int x;
	private int y;
	Image bgImage = new ImageIcon("t1.png").getImage();
	public Vector[] dir = new Vector[1];
	private HashMap<String, Vector> dirVector = new HashMap<String, Vector>();
	
	public ChunkSection[] collisionSections;
	int size = 64 * 15;
	
	
	public Tile(int x, int y) {
		collisionSections = new ChunkSection[8];
		
		this.x = x;
		this.y = y;
		
		collisionSections[0] = new ChunkSection("topCenter", this);
		collisionSections[1] = new ChunkSection("topLeft", this);
		collisionSections[2] = new ChunkSection("leftCenter", this);
		collisionSections[3] = new ChunkSection("bottomLeft", this);
		collisionSections[4] = new ChunkSection("bottomCenter", this);
		collisionSections[5] = new ChunkSection("bottomRight", this);
		collisionSections[6] = new ChunkSection("rightCenter", this);
		collisionSections[7] = new ChunkSection("topRight", this);
		
		dir[0] = new Vector(this.x, this.y - size);
		
		for (int i = 0; i < dir.length; i++) {
			dirVector.put(collisionSections[i].dirName, dir[i]);
		}
			
	}
	
	public void draw(Graphics g) {	
		int screenX = Camera.getInstance().projectX(x);
		int screenY = Camera.getInstance().projectY(y);
		
		
		
		g.drawImage(bgImage, screenX - size/2, screenY - size/2, size, size, null);
		displaySectionBox(collisionSections[0], 0, 0, 0, 100, g); // topCenter box
		displaySectionBox(collisionSections[1], 0, 0, -960 + 200, 100, g); // topLeft box
		displaySectionBox(collisionSections[2], 0, 0, -960 + 200, 860, g); // leftCenter box
		displaySectionBox(collisionSections[3], 0, size - (collisionSections[1].h), -960 + 200, 100, g); // bottomLeft box
		displaySectionBox(collisionSections[4], 0, size - (collisionSections[0].h), 0, 100, g); // bottomCenter box
		displaySectionBox(collisionSections[5], size - (collisionSections[2].w), size - (collisionSections[1].h), -960 + 200, 100, g); // bottomRight box
	    displaySectionBox(collisionSections[6], size - (collisionSections[2].w), 0, -960 + 200, 860, g); // right center box
	    displaySectionBox(collisionSections[7], size - (collisionSections[2].w), 0, -960 + 200, 100, g); // topRight box
		
	}
	
	public int[] getPosition() {
		int[] pos = new int[2];
		pos[0] = x;
		pos[1] = y;
		
		return pos;
	}
	
	public void displaySectionBox(ChunkSection section, int xOffset, int yOffset, int wOffset, int hOffset, Graphics g) {
		int screenX = Camera.getInstance().projectX(x);
		int screenY = Camera.getInstance().projectY(y);
		
		section.x = screenX - size/2 + xOffset;
		section.y = screenY - size/2 + yOffset;		
		section.w = size + wOffset;
		section.h = 100 + hOffset;
		
		
		g.setColor(Color.red);
		section.draw(g);
	}
	
	public Vector getChunkVector() {
		Vector v = new Vector(x, y);
		
		return v;
	}
	
	public void spawnChunk(String dir) {
		System.out.println("Spawn a chunk here: " + dir);
		
		try {
			if (!ChunkManager.getInstance().doesChunkExist(getChunkVector())) {
				ChunkManager.getInstance().addNewChunk(dirVector.get(dir), new Tile(dirVector.get(dir).x, dirVector.get(dir).y));
			}
		} catch (NullPointerException e) {
			System.out.println("Direction not yet set.");
		}
	}
	
	public void checkSections() {
		for (ChunkSection c : collisionSections) {
			c.withinCameraRange();
		}
	}
	
	
	

}
