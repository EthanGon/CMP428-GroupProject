import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChunkManager {
	private static ChunkManager instance;
	private HashMap<Vector, Tile> chunkList = new HashMap<Vector, Tile>();
	private Tile startingChunk;
	
	private ArrayList<ChunkRequest> chunkQueue = new ArrayList<>();
	
	public ChunkManager() {
		Tile startTile = new Tile(0,0);
		
		instance = this;
		chunkList.put(startTile.getChunkVector(), startTile);
	}
	
	public static ChunkManager getInstance() {
		return instance;
	}
	
	public void addNewChunk(Vector location, Tile chunk) {
		chunkList.put(location, chunk);
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
				addNewChunk(pendingChunk.chunkLocation, new Tile(pendingChunk.chunkLocation.x, pendingChunk.chunkLocation.y));
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
		printMap();
		
		processChunkQueue();
	}
	
	private static class ChunkRequest {
		Vector chunkLocation;
		
		public ChunkRequest(Vector location) {
			this.chunkLocation = location;
		}
	}
	
	public void printMap() {
		for (Map.Entry<Vector, Tile> entry : chunkList.entrySet()) {
            System.out.println("Key: " + "(" + entry.getKey().x + "," + entry.getKey().y + ")" + ", Value: " + entry.getValue());
        }
		System.out.println("-------");
	}

}
