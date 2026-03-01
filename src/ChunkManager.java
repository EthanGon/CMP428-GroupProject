import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;

public class ChunkManager {
	private static ChunkManager instance;
	private ArrayList<ChunkRequest> chunkQueue = new ArrayList<>();
	private HashMap<Vector, Tile> chunkList = new HashMap<Vector, Tile>();
	private Image[] grassTextures = new Image[7];
	private int chunks;
	

	
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
	
	
	/* Adds a new chunk to the chunk list using it's location as the key. 
	 * The key/location will be used to check if a chunk exist at that location already. 
	 */
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
	
	// Loads all the images from the textures/grass folder, this assumes grass files will be named "grass#.png"
	public void initGrassTexture() {
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
