
public class ChunkSection extends Rect {
	
	public String dirName;
	public boolean spawned;

	public ChunkSection(int x, int y, int w, int h, String name) {
		super(x, y, w, h);
		this.dirName = name;
		
	}

}
