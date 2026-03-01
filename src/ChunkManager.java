import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class ChunkManager {
	private static ChunkManager instance;
	private HashMap<Vector, Tile> chunkList = new HashMap<Vector, Tile>();
	private Tile startingChunk;
	private int chunks;
	private Image[] grassTextures = new Image[7];
	
	private ArrayList<ChunkRequest> chunkQueue = new ArrayList<>();
	
	public ChunkManager() {
		initGrassTexture();
		Tile startTile = new Tile(0,0, assignRandomTexture());
		
		instance = this;
		chunkList.put(startTile.getChunkVector(), startTile);
		chunks++;
	}
	
	public static ChunkManager getInstance() {
		return instance;
	}
	
	public void addNewChunk(Vector location, Tile chunk) {
		if (!doesChunkExist(location)) {
			chunkList.put(location, chunk);
		}
	}
	
	public void addChunkToQueue(Vector location) {
		chunkQueue.add(new ChunkRequest(location));
	}
	
	public boolean doesChunkExist(Vector locationKey) {
		return chunkList.containsKey(locationKey);
	}
	
	/* Process any new chunks that the camera can see. 
	 * Since the camera is always moving, you don't want to loop through map while drawing, this avoids the ConcurrentModificationException error
	 * */
	public void processChunkQueue() {
		
		for (ChunkRequest pendingChunk : chunkQueue) {
			
			if (!doesChunkExist(pendingChunk.chunkLocation)) {
				addNewChunk(pendingChunk.chunkLocation, new Tile(pendingChunk.chunkLocation.x, pendingChunk.chunkLocation.y, assignRandomTexture()));
			}
			
			
		}
		chunkQueue.clear();
	}
	
	public void draw(Graphics g) {
		
		for (Tile tile : chunkList.values()) { 
			if (tile.withinPlayerDistance()) {
				tile.draw(g);
				tile.checkSections();
			}
			
		}
		
		processChunkQueue();
	}
	
	private static class ChunkRequest {
		Vector chunkLocation;
		
		public ChunkRequest(Vector location) {
			this.chunkLocation = location;
		}
	}
	
	public void initGrassTexture() {
//		private Image[] grassTexutres = new ImageIcon("textures/grass/grass0.png").getImage();

		for (int i = 0; i < grassTextures.length; i++) {
			grassTextures[i] = new ImageIcon("textures/grass/grass" + i + ".png").getImage();
		}
	}
	
	public Image assignRandomTexture() {
		int randomIndex = (int) (Math.random() * grassTextures.length);
		
		return grassTextures[randomIndex];
	}
	
	public void printMap() {
//		for (Map.Entry<Vector, Tile> entry : chunkList.entrySet()) {
//            System.out.println("Key: " + "(" + entry.getKey().x + "," + entry.getKey().y + ")" + ", Value: " + entry.getValue());
//        }
		System.out.println("MAP SIZE: " + chunks);
		System.out.println("-------");
	}

}
