import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

public class ChunkManager {
	private static ChunkManager instance;
	private HashMap<Vector, Tile> chunkList = new HashMap<Vector, Tile>();
	private Tile startingChunk;
	
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
	
	public boolean doesChunkExist(Vector locationKey) {
		return chunkList.containsKey(locationKey);
	}
	
	public void draw(Graphics g) {
		
		for (Map.Entry<Vector, Tile> entry : new HashMap<>(chunkList).entrySet()) 
		{ 
			entry.getValue().checkSections(); entry.getValue().draw(g); 
		
		}
	}

}
