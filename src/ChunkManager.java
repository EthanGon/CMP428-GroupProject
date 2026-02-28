import java.util.HashMap;

public class ChunkManager {
	private static ChunkManager instance;
	private HashMap<int[], Tile> chunkList = new HashMap<int[], Tile>();
	private Tile startingChunk;
	
	public ChunkManager(Tile startingChunk) {
		instance = this;
		chunkList.put(startingChunk.getPosition(), startingChunk);
	}
	
	public static ChunkManager getInstance() {
		return instance;
	}
	
	public void AddNewChunk(int[] location, Tile chunk) {
		chunkList.put(location, chunk);
	}

}
