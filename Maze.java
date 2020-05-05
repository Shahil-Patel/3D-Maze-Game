public class Maze{
	private Explorer character;
	private Wall[][] walls;
	public Maze(Explorer character, Wall[][] walls){
		this.character=character;
		this.walls=walls;
	}
	public Explorer getExplorer(){
		return character;
	}
	public Wall[][] getWalls(){
		return walls;
	}
	public void setWall(Wall[][] walls){
		this.walls=walls;
	}
	public void setExplorer(Explorer character){
		this.character=character;
	}
	public String toString(){
		return getWalls()+"";
	}
}