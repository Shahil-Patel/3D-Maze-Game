public class Wall extends Location{
	private int state;
	private boolean painted;
	public Wall(int x,int y,int state,boolean painted){
		super(x,y);
		this.state=state;
		this.painted=painted;
	}
	public int getState(){
		return state;
	}
	public void setState(int state){
		this.state=state;
	}
	public boolean getPainted(){
		return painted;
	}
	public void setPainted(boolean painted){
		this.painted=painted;
	}
	public String toString(){
	//	return "State: "+state+" X,Y: "+x+""+y+" ";
		return state+" ";
	}
}