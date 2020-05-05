public class Location{
	private int x=0;
	private int y=0;
	public Location(int x,int y){
		this.x=x;
		this.y=y;
	}
	public int getX(){
		return x;
	}
	public void setX(int x){
		this.x=x;
	}
	public int getY(){
		return y;
	}
	public void setY(int y){
		this.y=y;
	}
	public void addX(){
		x++;
	}
	public void addY(){
		y++;
	}
	public void subX(){
		x--;
	}
	public void subY(){
		y--;
	}
}
