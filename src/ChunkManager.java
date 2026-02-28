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
	
	public ChunkManager(Tile startingChunk) {
		instance = this;
		chunkList.put(startingChunk.getChunkVector(), startingChunk);
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
	
	public void processChunkQueue() {
		
		for (ChunkRequest pendingChunk : chunkQueue) {
			
			if (!doesChunkExist(pendingChunk.chunkLocation)) {
				addNewChunk(pendingChunk.chunkLocation, new Tile(pendingChunk.chunkLocation.x, pendingChunk.chunkLocation.y));
			}
			
			
		}
		chunkQueue.clear();
	}
	
	public void draw(Graphics g) {
		
		for (Tile tile : new HashMap<>(chunkList).values()) { 
			tile.checkSections(); 
			tile.draw(g); 
		}
		
		processChunkQueue();
	}
	
	private static class ChunkRequest {
		Vector chunkLocation;
		
		public ChunkRequest(Vector location) {
			this.chunkLocation = location;
		}
	}

}
