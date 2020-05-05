public class Explorer extends Location{
	public Explorer(int x,int y){
		super(x,y);
	}
	public void move(int xAdd,int yAdd){
		setX(getX()+xAdd);
		setY(getY()+yAdd);
	}
}