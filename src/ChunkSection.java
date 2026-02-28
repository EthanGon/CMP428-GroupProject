
public class ChunkSection extends Rect {
	
	public String dirName;
	public boolean spawned;
	public Tile parentChunk;

	public ChunkSection(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public ChunkSection(String name, Tile chunkAttachedTo) {
		super(0,0,0,0);
		this.dirName = name;
		this.parentChunk = chunkAttachedTo;
	}
	
	public void withinCameraRange() {
		if (!spawned && this.overlaps(Camera.getInstance().camBounds)) {
			parentChunk.spawnChunk(dirName);
			spawned = true;
		}
	}
	
	

}
