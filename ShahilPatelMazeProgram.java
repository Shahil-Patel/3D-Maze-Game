import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
public class ShahilPatelMazeProgram extends JPanel implements KeyListener,MouseListener
{
	JFrame frame;
	Wall[][] walls=null;
	Explorer explorer=null;
	Location eCord=null;
	int colorCode=225;
	Maze maze;
	int color1=128;
	int steps=0;
	boolean toggle=false;
	boolean map=false;
	private BufferedImage imgKey = null;
	private BufferedImage imgDoor = null;
	private BufferedImage imgMap = null;
	private BufferedImage imgPaint = null;
	boolean paint=false;
	Font h;
	boolean win=false;
	boolean key=false;
	int paintCount;
	int visible=3;
	int visTemp=3;
	int dir=1;
	int x=100,y=100;
	public ShahilPatelMazeProgram()
	{
		setBoard();
		frame=new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,800);
		frame.setVisible(true);
		frame.addKeyListener(this);
		try
		{
			imgDoor= ImageIO.read( new File("door.png" ));
			imgKey = ImageIO.read( new File("key.png" ));
			imgMap = ImageIO.read( new File("map.png" ));
			imgPaint = ImageIO.read( new File("paint.png" ));
		}
		catch ( IOException exc )
		{

		}
		this.addMouseListener(this);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.setColor(Color.BLACK);
		g.fillRect(0,0,1000,800);
		if(visible>4){
			visible=4;
		}
		if(visible<1){
			visible=1;
		}
		System.out.println("Location X: "+eCord.getX()+"\nLocation Y: "+eCord.getY()+"\n");
		int scaleY=100;
		colorCode=172;
		g.setColor(new Color(128,128,128));
		g.fillRect(50,50,900,650);
		if(maze.getWalls()[eCord.getY()][eCord.getX()].getState()==2){
			win=true;
		}
		if(maze.getWalls()[eCord.getY()][eCord.getX()].getState()==4){
			for(int x=0;x<maze.getWalls().length;x++){
				for(int y=0;y<maze.getWalls()[0].length;y++){
					if(maze.getWalls()[x][y].getState()==5){
						eCord=new Location(y+1,x);
					}
				}
			}
			explorer=new Explorer(20*eCord.getX()+190,20*eCord.getY()+100);
		}
		if(maze.getWalls()[eCord.getY()][eCord.getX()].getState()==5){
			for(int x=0;x<maze.getWalls().length;x++){
				for(int y=0;y<maze.getWalls()[0].length;y++){
					if(maze.getWalls()[x][y].getState()==4){
						eCord=new Location(y-1,x);
					}
				}
			}
			explorer=new Explorer(20*eCord.getX()+190,20*eCord.getY()+100);
		}
		if(maze.getWalls()[eCord.getY()][eCord.getX()].getState()==6){
			for(int x=0;x<maze.getWalls().length;x++){
				for(int y=0;y<maze.getWalls()[0].length;y++){
					if(maze.getWalls()[x][y].getState()==7){
						eCord=new Location(y+1,x);
						dir=1;
					}
				}
			}
			explorer=new Explorer(20*eCord.getX()+190,20*eCord.getY()+100);
		}
		if(maze.getWalls()[eCord.getY()][eCord.getX()].getState()==7){
			for(int x=0;x<maze.getWalls().length;x++){
				for(int y=0;y<maze.getWalls()[0].length;y++){
					if(maze.getWalls()[x][y].getState()==6){
						eCord=new Location(y-1,x);
					}
				}
			}
			explorer=new Explorer(20*eCord.getX()+190,20*eCord.getY()+100);
		}
		if(maze.getWalls()[eCord.getY()][eCord.getX()].getState()==8){
			maze.getWalls()[eCord.getY()][eCord.getX()].setState(1);
			for(int x=0;x<maze.getWalls().length;x++){
				for(int y=0;y<maze.getWalls()[0].length;y++){
					if(maze.getWalls()[x][y].getState()==9){
						maze.getWalls()[x][y].setState(1);
					}
				}
			}
			key=true;
		}
		if(maze.getWalls()[eCord.getY()][eCord.getX()].getState()==10){
			maze.getWalls()[eCord.getY()][eCord.getX()].setState(1);
			for(int x=0;x<maze.getWalls().length;x++){
				for(int y=0;y<maze.getWalls()[0].length;y++){
					if(maze.getWalls()[x][y].getState()==10){
						maze.getWalls()[x][y].setState(1);
					}
				}
			}
			map=true;
		}
		if(maze.getWalls()[eCord.getY()][eCord.getX()].getState()==11){
			maze.getWalls()[eCord.getY()][eCord.getX()].setState(1);
			for(int x=0;x<maze.getWalls().length;x++){
				for(int y=0;y<maze.getWalls()[0].length;y++){
					if(maze.getWalls()[x][y].getState()==11){
						maze.getWalls()[x][y].setState(1);
					}
				}
			}
			paint=true;
			paintCount=5;
		}
		if(maze.getWalls()[eCord.getY()][eCord.getX()].getState()!=2){
			for(int x=0;x<visible;x++){
				g.setColor(new Color(color1,0,0));
				int[] xVert={200,50,950,800}; //top left, bottom left, bottom right, top right
				int[] yVert={600,700,700,600}; //top left, bottom left, bottom right, top right
				int[] xVert2={200,50,950,800}; //top left, bottom left, bottom right, top right
				int[] yVert2={600,700,700,600}; //top left, bottom left, bottom right, top right
				for(int y=0;y<x;y++){
					scaleY=scaleY*3/4;
					yVert[1]=yVert[0];
					xVert[1]=xVert[0];
					xVert[2]=xVert[3];
					yVert[2]=yVert[3];
					xVert[0]=xVert[0]+scaleY*3/2; //top left x
					yVert[0]=yVert[0]-scaleY; //top left y
					yVert[3]=yVert[3]-scaleY; //top right y
					xVert[3]=xVert[3]-scaleY*3/2; //top right x
				}
				scaleY=100;
				if(color1-30>0){
					color1-=28;
				}
				xVert2[0]=1000-xVert[0];
				xVert2[1]=1000-xVert[1];
				xVert2[2]=1000-xVert[2];
				xVert2[3]=1000-xVert[3];
				yVert2[0]=750-yVert[0];
				yVert2[1]=750-yVert[1];
				yVert2[2]=750-yVert[2];
				yVert2[3]=750-yVert[3];
				int[] tempx={xVert[0],xVert[1],xVert2[2],xVert2[3]};
				int[] tempy={yVert[0],yVert[1],yVert2[2],yVert2[3]};
				int[] tempx2={0,0,0,0};
				int[] tempy2={0,0,0,0};
				for(int z=0;z<4;z++){
					tempx2[z]=1000-tempx[z];
					tempy2[z]=750-tempy[z];
				}
				g.fillPolygon(xVert,yVert,4);
				g.fillPolygon(xVert2,yVert2,4);
				colorCode-=26;
				g.setColor(new Color(colorCode,colorCode,colorCode));
				g.fillPolygon(tempx,tempy,4);
				g.fillPolygon(tempx2,tempy2,4);
				//0 is north //1 is east //2 is south //3 is west
				visible=visTemp;
				g.setColor(Color.BLACK);
				g.drawPolygon(tempx,tempy,4);
				g.drawPolygon(tempx2,tempy2,4);
				if(dir==0){
					if(maze.getWalls()[eCord.getY()-x][eCord.getX()-1].getState()!=0){
						g.setColor(new Color(color1+26,0,0));
						g.fillPolygon(tempx,tempy,4);
						g.setColor(Color.BLACK);
						g.drawLine(xVert[1],yVert[0],xVert[0],yVert[0]);
						g.setColor(new Color(colorCode-26,colorCode-26,colorCode-26));
						if(x+1==visible){
							g.setColor(Color.BLACK);
						}
						g.fillRect(xVert[1],yVert2[0],Math.abs(xVert[1]-xVert[0]),Math.abs(yVert2[0]-yVert[0]));
						g.setColor(Color.BLACK);
						g.drawLine(xVert[0]-1,yVert[0],xVert2[3]-1,yVert2[3]);
					}
					if(maze.getWalls()[eCord.getY()-x][eCord.getX()+1].getState()!=0){
						g.setColor(new Color(color1+26,0,0));
						g.fillPolygon(tempx2,tempy2,4);
						g.setColor(Color.BLACK);
						g.drawLine(xVert[3],yVert[3],xVert[2],yVert[3]);
						g.setColor(new Color(colorCode-26,colorCode-26,colorCode-26));
						if(x+1==visible){
							g.setColor(Color.BLACK);
						}
						g.fillRect(xVert2[0],yVert2[0],Math.abs(xVert[1]-xVert[0]),Math.abs(yVert2[0]-yVert[0]));
					}
					if(maze.getWalls()[eCord.getY()-x][eCord.getX()].getState()!=1){
						g.setColor(new Color(colorCode,colorCode,colorCode));
						visible--;
						int[] px={xVert2[1],xVert2[2],xVert[1],xVert[2]};
						int[] py={yVert2[1],yVert2[2],yVert[1],yVert[2]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()-x][eCord.getX()].getState()!=0){
						g.setColor(Color.BLACK);
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()-x][eCord.getX()].getState()==3){
						g.setColor(Color.YELLOW);
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()-x][eCord.getX()].getState()==2){
						g.setColor(Color.RED);
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()-x][eCord.getX()].getState()==4||maze.getWalls()[eCord.getY()-x][eCord.getX()].getState()==5){
						g.setColor(new Color(255,127,80));
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()-x][eCord.getX()].getState()==6||maze.getWalls()[eCord.getY()-x][eCord.getX()].getState()==7){
						g.setColor(Color.MAGENTA);
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()-x][eCord.getX()].getState()==8&&!key){
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.setColor(new Color(colorCode,colorCode,colorCode));
						g.fillPolygon(px,py,4);
						g.drawImage(imgKey,420,350,(int)(400/(1.5*x)),(int)(300/(1.5*x)),this);
					}
					if(maze.getWalls()[eCord.getY()-x][eCord.getX()].getState()==9&&!key){
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.setColor(new Color(colorCode,colorCode,colorCode));
						g.fillPolygon(px,py,4);
						g.drawImage(imgDoor,200,200,600,400,this);
					}
					if(maze.getWalls()[eCord.getY()-x][eCord.getX()].getState()==10&&!map){
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.setColor(new Color(colorCode,colorCode,colorCode));
						g.fillPolygon(px,py,4);
						g.drawImage(imgMap,400+20*x,320,(int)(400/(1.5*x)),(int)(300/(1.5*x)),this);
					}
					if(maze.getWalls()[eCord.getY()-x][eCord.getX()].getState()==11&&!paint){
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.setColor(new Color(colorCode,colorCode,colorCode));
						g.fillPolygon(px,py,4);
						g.drawImage(imgPaint,400+20*x,320,(int)(200/(1.5*x)),(int)(350/(1.5*x)),this);
					}
					if(maze.getWalls()[eCord.getY()-x][eCord.getX()].getPainted()==true){
						g.setColor(new Color(145,195,120));
						g.fillOval((xVert[1]+xVert[2])/2-(int)Math.sqrt(Math.abs(yVert[0]-yVert[2])),(yVert[0]+yVert[3])/2,Math.abs(yVert[0]-yVert[2]),Math.abs(yVert[0]-yVert[2]));
					}
				}
				else if(dir==1){
					if(maze.getWalls()[eCord.getY()-1][eCord.getX()+x].getState()!=0){
						g.setColor(new Color(color1+26,0,0));
						g.fillPolygon(tempx,tempy,4);
						g.setColor(Color.BLACK);
						g.drawLine(xVert[1],yVert[0],xVert[0],yVert[0]);
						g.setColor(new Color(colorCode-26,colorCode-26,colorCode-26));
						if(x+1==visible){
							g.setColor(Color.BLACK);
						}
						g.fillRect(xVert[1],yVert2[0],Math.abs(xVert[1]-xVert[0]),Math.abs(yVert2[0]-yVert[0]));
						g.setColor(Color.BLACK);
						g.drawLine(xVert[0]-1,yVert[0],xVert2[3]-1,yVert2[3]);
					}
					if(maze.getWalls()[eCord.getY()+1][eCord.getX()+x].getState()!=0){
						g.setColor(new Color(color1+26,0,0));
						g.fillPolygon(tempx2,tempy2,4);
						g.setColor(Color.BLACK);
						g.drawLine(xVert[3],yVert[3],xVert[2],yVert[3]);
						g.setColor(new Color(colorCode-26,colorCode-26,colorCode-26));
						if(x+1==visible){
							g.setColor(Color.BLACK);
						}
						g.fillRect(xVert2[0],yVert2[0],Math.abs(xVert[1]-xVert[0]),Math.abs(yVert2[0]-yVert[0]));
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()+x].getState()!=1){
						g.setColor(new Color(colorCode,colorCode,colorCode));
						visible--;
						int[] px={xVert2[1],xVert2[2],xVert[1],xVert[2]};
						int[] py={yVert2[1],yVert2[2],yVert[1],yVert[2]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()+x].getState()!=0){
						g.setColor(Color.BLACK);
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()+x].getState()==3){
						g.setColor(Color.YELLOW);
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()+x].getState()==2){
						g.setColor(Color.RED);
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()+x].getState()==4||maze.getWalls()[eCord.getY()][eCord.getX()+x].getState()==5){
						g.setColor(new Color(255,127,80));
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()+x].getState()==6||maze.getWalls()[eCord.getY()][eCord.getX()+x].getState()==7){
						g.setColor(Color.MAGENTA);
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()+x].getState()==8&&!key){
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.setColor(new Color(colorCode,colorCode,colorCode));
						g.fillPolygon(px,py,4);
						g.drawImage(imgKey,420,350,(int)(400/(1.5*x)),(int)(300/(1.5*x)),this);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()+x].getState()==9&&!key){
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.setColor(new Color(colorCode,colorCode,colorCode));
						g.fillPolygon(px,py,4);
						g.drawImage(imgDoor,200,200,600,400,this);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()+x].getState()==10&&!map){
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.setColor(new Color(colorCode,colorCode,colorCode));
						g.fillPolygon(px,py,4);
						g.drawImage(imgMap,400+20*x,320,(int)(400/(1.5*x)),(int)(300/(1.5*x)),this);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()+x].getState()==11&&!paint){
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.setColor(new Color(colorCode,colorCode,colorCode));
						g.fillPolygon(px,py,4);
						g.drawImage(imgPaint,400+20*x,320,(int)(200/(1.5*x)),(int)(350/(1.5*x)),this);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()+x].getPainted()==true){
						g.setColor(new Color(145,195,120));
						g.fillOval((xVert[1]+xVert[2])/2-(int)Math.sqrt(Math.abs(yVert[0]-yVert[2])),(yVert[0]+yVert[3])/2,Math.abs(yVert[0]-yVert[2]),Math.abs(yVert[0]-yVert[2]));
					}
				}
				else if(dir==2){
					if(maze.getWalls()[eCord.getY()+x][eCord.getX()+1].getState()!=0){
						g.setColor(new Color(color1+26,0,0));
						g.fillPolygon(tempx,tempy,4);
						g.setColor(Color.BLACK);
						g.drawLine(xVert[1],yVert[0],xVert[0],yVert[0]);
						g.setColor(new Color(colorCode-26,colorCode-26,colorCode-26));
						if(x+1==visible){
							g.setColor(Color.BLACK);
						}
						g.fillRect(xVert[1],yVert2[0],Math.abs(xVert[1]-xVert[0]),Math.abs(yVert2[0]-yVert[0]));
						g.setColor(Color.BLACK);
						g.drawLine(xVert[0]-1,yVert[0],xVert2[3]-1,yVert2[3]);
					}
					if(maze.getWalls()[eCord.getY()+x][eCord.getX()-1].getState()!=0){
						g.setColor(new Color(color1+26,0,0));
						g.fillPolygon(tempx2,tempy2,4);
						g.setColor(Color.BLACK);
						g.drawLine(xVert[3],yVert[3],xVert[2],yVert[3]);
						g.setColor(new Color(colorCode-26,colorCode-26,colorCode-26));
						if(x+1==visible){
							g.setColor(Color.BLACK);
						}
						g.fillRect(xVert2[0],yVert2[0],Math.abs(xVert[1]-xVert[0]),Math.abs(yVert2[0]-yVert[0]));
					}
					if(maze.getWalls()[eCord.getY()+x][eCord.getX()].getState()!=1){
						g.setColor(new Color(colorCode,colorCode,colorCode));
						visible--;
						int[] px={xVert2[1],xVert2[2],xVert[1],xVert[2]};
						int[] py={yVert2[1],yVert2[2],yVert[1],yVert[2]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()+x][eCord.getX()].getState()!=0){
						g.setColor(Color.BLACK);
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()+x][eCord.getX()].getState()==3){
						g.setColor(Color.YELLOW);
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()+x][eCord.getX()].getState()==2){
						g.setColor(Color.RED);
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()+x][eCord.getX()].getState()==4||maze.getWalls()[eCord.getY()+x][eCord.getX()].getState()==5){
						g.setColor(new Color(255,127,80));
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()+x][eCord.getX()].getState()==6||maze.getWalls()[eCord.getY()+x][eCord.getX()].getState()==7){
						g.setColor(Color.MAGENTA);
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()+x][eCord.getX()].getState()==8&&!key){
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.setColor(new Color(colorCode,colorCode,colorCode));
						g.fillPolygon(px,py,4);
						g.drawImage(imgKey,420,350,(int)(400/(1.5*x)),(int)(300/(1.5*x)),this);
					}
					if(maze.getWalls()[eCord.getY()+x][eCord.getX()].getState()==10&&!map){
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.setColor(new Color(colorCode,colorCode,colorCode));
						g.fillPolygon(px,py,4);
						g.drawImage(imgMap,400+20*x,320,(int)(400/(1.5*x)),(int)(300/(1.5*x)),this);
					}
					if(maze.getWalls()[eCord.getY()+x][eCord.getX()].getState()==11&&!paint){
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.setColor(new Color(colorCode,colorCode,colorCode));
						g.fillPolygon(px,py,4);
						g.drawImage(imgPaint,400+20*x,320,(int)(200/(1.5*x)),(int)(350/(1.5*x)),this);
					}
					if(maze.getWalls()[eCord.getY()+x][eCord.getX()].getPainted()==true){
						g.setColor(new Color(145,195,120));
						g.fillOval((xVert[1]+xVert[2])/2-(int)Math.sqrt(Math.abs(yVert[0]-yVert[2])),(yVert[0]+yVert[3])/2,Math.abs(yVert[0]-yVert[2]),Math.abs(yVert[0]-yVert[2]));
					}
				}
				else if(dir==3){
					if(maze.getWalls()[eCord.getY()+1][eCord.getX()-x].getState()!=0){
						g.setColor(new Color(color1+26,0,0));
						g.fillPolygon(tempx,tempy,4);
						g.setColor(Color.BLACK);
						g.drawLine(xVert[1],yVert[0],xVert[0],yVert[0]);
						g.setColor(new Color(colorCode-26,colorCode-26,colorCode-26));
						if(x+1==visible){
							g.setColor(Color.BLACK);
						}
						g.fillRect(xVert[1],yVert2[0],Math.abs(xVert[1]-xVert[0]),Math.abs(yVert2[0]-yVert[0]));
						g.setColor(Color.BLACK);
						g.drawLine(xVert[0]-1,yVert[0],xVert2[3]-1,yVert2[3]);
					}
					if(maze.getWalls()[eCord.getY()-1][eCord.getX()-x].getState()!=0){
						g.setColor(new Color(color1+26,0,0));
						g.fillPolygon(tempx2,tempy2,4);
						g.setColor(Color.BLACK);
						g.drawLine(xVert[3],yVert[3],xVert[2],yVert[3]);
						g.setColor(new Color(colorCode-26,colorCode-26,colorCode-26));
						if(x+1==visible){
							g.setColor(Color.BLACK);
						}
						g.fillRect(xVert2[0],yVert2[0],Math.abs(xVert[1]-xVert[0]),Math.abs(yVert2[0]-yVert[0]));
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()-x].getState()!=1){
						g.setColor(new Color(colorCode,colorCode,colorCode));
						visible--;
						int[] px={xVert2[1],xVert2[2],xVert[1],xVert[2]};
						int[] py={yVert2[1],yVert2[2],yVert[1],yVert[2]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()-x].getState()!=0){
						g.setColor(Color.BLACK);
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()-x].getState()==3){
						g.setColor(Color.YELLOW);
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()-x].getState()==2){
						g.setColor(Color.RED);
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()-x].getState()==4||maze.getWalls()[eCord.getY()][eCord.getX()-x].getState()==5){
						g.setColor(new Color(255,127,80));
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()-x].getState()==6||maze.getWalls()[eCord.getY()][eCord.getX()-x].getState()==7){
						g.setColor(Color.MAGENTA);
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.fillPolygon(px,py,4);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()-x].getState()==8&&!key){
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.setColor(new Color(colorCode,colorCode,colorCode));
						g.fillPolygon(px,py,4);
						g.drawImage(imgKey,420,350,(int)(400/(1.5*x)),(int)(300/(1.5*x)),this);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()-x].getState()==9&&!key){
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.setColor(new Color(colorCode,colorCode,colorCode));
						g.fillPolygon(px,py,4);
						g.drawImage(imgDoor,200,200,600,400,this);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()-x].getState()==10&&!map){
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.setColor(new Color(colorCode,colorCode,colorCode));
						g.fillPolygon(px,py,4);
						g.drawImage(imgMap,400+20*x,320,(int)(400/(1.5*x)),(int)(300/(1.5*x)),this);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()-x].getState()==11&&!paint){
						int[] px={xVert2[0],xVert2[3],xVert[0],xVert[3]};
						int[] py={yVert2[0],yVert2[3],yVert[0],yVert[3]};
						g.setColor(new Color(colorCode,colorCode,colorCode));
						g.fillPolygon(px,py,4);
						g.drawImage(imgPaint,400+20*x,320,(int)(200/(1.5*x)),(int)(350/(1.5*x)),this);
					}
					if(maze.getWalls()[eCord.getY()][eCord.getX()-x].getPainted()==true){
						g.setColor(new Color(145,195,120));
					//	g.fillPolygon(xVert,yVert,4);
						g.fillOval((xVert[1]+xVert[2])/2-(int)Math.sqrt(Math.abs(yVert[0]-yVert[2])),(yVert[0]+yVert[3])/2,Math.abs(yVert[0]-yVert[2]),Math.abs(yVert[0]-yVert[2]));
					}
				}
			}
		}
		color1=128;
		if(win){
			Font v = new Font("Helvetica", Font.PLAIN, 50);
			g.setFont(v);
			g.setColor(Color.WHITE);
			g.drawString("YOU WIN",390,375);
			g.drawString("STEPS: "+steps,390,425);
		}
		if(toggle&&!win){
			g.setColor(Color.LIGHT_GRAY);
			g.fillOval(700,450,200,200);
			g.setColor(Color.RED);
			h = new Font("Helvetica",Font.PLAIN,20);
			g.setFont(h);
			g.setColor(Color.WHITE);
			g.drawString("N",795,470);
			g.drawString("S",795,645);
			g.drawString("W",705,560);
			g.drawString("E",880,560);
			g.setColor(Color.BLACK);
			g.fillOval(790,540,20,20);
			if(dir==0){
				g.setColor(Color.RED);
				g.fillOval(795,470,10,90);
			}
			else if(dir==1){
				g.setColor(Color.RED);
				g.fillOval(790,545,90,10);
			}
			else if(dir==2){
				g.setColor(Color.RED);
				g.fillOval(795,540,10,90);
			}
			else if(dir==3){
				g.setColor(Color.RED);
				g.fillOval(720,545,90,10);
			}
			for(int x=0;x<maze.getWalls().length;x++){
				for(int y=0;y<maze.getWalls()[0].length;y++){
					if(maze.getWalls()[x][y].getState()==0){
						g.setColor(Color.GREEN);
						g.drawRect(20*y+190,20*x+100,20,20);
					}
					else if(maze.getWalls()[x][y].getState()==1){
						g.setColor(Color.GREEN);
					}
					else if(maze.getWalls()[x][y].getState()==2){
						g.setColor(Color.RED);
						g.fillRect(20*y+190,20*x+100,20,20);
					}
					else if(maze.getWalls()[x][y].getState()==3){
						g.setColor(Color.YELLOW);
						g.fillRect(20*y+190,20*x+100,20,20);
					}
					else if(maze.getWalls()[x][y].getState()==3){
						g.setColor(Color.YELLOW);
						g.fillRect(20*y+190,20*x+100,20,20);
					}
					else if(maze.getWalls()[x][y].getState()==4||maze.getWalls()[x][y].getState()==5){
						g.setColor(new Color(255,127,80));
						g.fillRect(20*y+190,20*x+100,20,20);
					}
					else if(maze.getWalls()[x][y].getState()==6||maze.getWalls()[x][y].getState()==7){
						g.setColor(Color.MAGENTA);
						g.fillRect(20*y+190,20*x+100,20,20);
					}
					else if(maze.getWalls()[x][y].getState()==8){
						g.setColor(new Color(175,75,0));
						g.fillRect(20*y+190,20*x+100,20,20);
					}
					else if(maze.getWalls()[x][y].getState()==9){
						g.setColor(new Color(175,75,0));
						g.fillRect(20*y+190,20*x+100,20,20);
					}
					else if(maze.getWalls()[x][y].getState()==10){
						g.setColor(new Color(245,245,220));
						g.fillRect(20*y+190,20*x+100,20,20);
					}
					else if(maze.getWalls()[x][y].getState()==11){
						g.setColor(new Color(145,195,120));
						g.fillRect(20*y+190,20*x+100,20,20);
					}
				}
			}
			for(int x=0;x<maze.getWalls().length;x++){
				for(int y=0;y<maze.getWalls()[0].length;y++){
					if(maze.getWalls()[x][y].getPainted()){
						g.setColor(new Color(145,195,120));
						g.fillOval(20*y+190,20*x+100,20,20);
					}
				}
			}
			g.setColor(Color.BLUE);
			g.fillOval(explorer.getX(),explorer.getY(),20,20);
		}
		if(key&&win==false){
			h = new Font("Helvetica",Font.PLAIN,30);
			g.setFont(h);
			g.setColor(Color.WHITE);
			g.drawString("Door Unlocked!",50,80);
		}
		if(map&&win==false){
			h = new Font("Helvetica",Font.PLAIN,30);
			g.setFont(h);
			g.setColor(Color.WHITE);
			g.drawString("Press SPACE for a HUD",625,80);
		}
		if(paint&&paintCount>0&&win==false){
			h = new Font("Helvetica",Font.PLAIN,30);
			g.setFont(h);
			g.setColor(Color.WHITE);
			g.drawString("Paint has "+paintCount+" sprays left: Press P",50,690);
		}
	}
	public void setBoard()
	{
		File name = new File("one.txt");
		int r=0;
		try
		{
			ArrayList<String> txtfile=new ArrayList<String>();
			int mazeLength=0;
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text;
			while((text=input.readLine())!= null)
			{
				r++;
				String[] temp=text.split("");
				for(int x=0;x<temp.length;x++)
					txtfile.add(temp[x]);
				mazeLength=text.length();
				System.out.println(text);
			}
			int count=0;
			Wall[][] walls=new Wall[r][mazeLength];
			for(int x=0;x<walls.length;x++){
				for(int y=0;y<walls[0].length;y++){
					if(txtfile.get(count).equals("#")){
						Wall w=new Wall(x,y,0,false);
						walls[x][y]=w;
					}
					else if(txtfile.get(count).equals(" ")){
						Wall w=new Wall(x,y,1,false);
						walls[x][y]=w;
					}
					else if(txtfile.get(count).equals("e")){
						Wall w=new Wall(x,y,2,false);
						walls[x][y]=w;
					}
					else if(txtfile.get(count).equals("E")){
						Wall w=new Wall(x,y,3,false);
						walls[x][y]=w;
						explorer=new Explorer(20*y+210,20*x+100);
						eCord=new Location(1,1);
					}
					else if(txtfile.get(count).equals("T")){
						Wall w=new Wall(x,y,4,false);
						walls[x][y]=w;
					}
					else if(txtfile.get(count).equals("t")){
						Wall w=new Wall(x,y,5,false);
						walls[x][y]=w;
					}
					else if(txtfile.get(count).equals("G")){
						Wall w=new Wall(x,y,6,false);
						walls[x][y]=w;
					}
					else if(txtfile.get(count).equals("g")){
						Wall w=new Wall(x,y,7,false);
						walls[x][y]=w;
					}
					else if(txtfile.get(count).equals("k")){
						Wall w=new Wall(x,y,8,false);
						walls[x][y]=w;
					}
					else if(txtfile.get(count).equals("d")){
						Wall w=new Wall(x,y,9,false);
						walls[x][y]=w;
					}
					else if(txtfile.get(count).equals("m")){
						Wall w=new Wall(x,y,10,false);
						walls[x][y]=w;
					}
					else if(txtfile.get(count).equals("p")){
						Wall w=new Wall(x,y,11,false);
						walls[x][y]=w;
					}
					count++;
				}
			}
			System.out.println(txtfile);
			maze=new Maze(explorer,walls);
		}
		catch (IOException io)
		{
			System.err.println("File error");
		}
	}
	public void setWalls()
	{
	}
	public void keyPressed(KeyEvent e)
	{
		if(win==false){
			System.out.println("Key Code: "+e.getKeyCode());
			switch(e.getKeyCode()){
				case 65:
					if(dir-1<0){
						dir=3;
					}
					else dir--;
				break;
				case 68:
					if(dir+1>3){
						dir=0;
					}
					else dir++;
				break;
			}
			if(e.getKeyCode()==71){
				visible--;
				visTemp++;
			}
			if(e.getKeyCode()==72){
				visible++;
				visTemp++;
			}
			if(e.getKeyCode()==80&&paint&&paintCount>0){
				if(maze.getWalls()[eCord.getY()][eCord.getX()].getPainted()==false){
					paintCount--;
					maze.getWalls()[eCord.getY()][eCord.getX()].setPainted(true);
				}
			}
			if(e.getKeyCode()==87){  //0 is north //1 is east //2 is south //3 is west
				if(dir==0&&!(maze.getWalls()[eCord.getY()-1][eCord.getX()].getState()==0)&&eCord.getY()-1>-1&&(maze.getWalls()[eCord.getY()-1][eCord.getX()].getState()!=9)){
					explorer.move(0,-20);
					eCord.subY();
					steps++;
				}
				if(dir==1&&!(maze.getWalls()[eCord.getY()][eCord.getX()+1].getState()==0)&&eCord.getX()+1<maze.getWalls()[0].length&&(maze.getWalls()[eCord.getY()][eCord.getX()+1].getState()!=9)){
					explorer.move(20,0);
					eCord.addX();
					steps++;
				}
				if(dir==2&&!(maze.getWalls()[eCord.getY()+1][eCord.getX()].getState()==0)&&eCord.getY()+1<maze.getWalls().length&&(maze.getWalls()[eCord.getY()+1][eCord.getX()].getState()!=9)){
					explorer.move(0,20);
					eCord.addY();
					steps++;
				}
				if(dir==3&&!(maze.getWalls()[eCord.getY()][eCord.getX()-1].getState()==0)&&eCord.getX()-1>-1&&maze.getWalls()[eCord.getY()][eCord.getX()-1].getState()!=3&&(maze.getWalls()[eCord.getY()][eCord.getX()-1].getState()!=9)){
					explorer.move(-20,0);
					eCord.subX();
					steps++;
				}
			}
			System.out.println("Explorer X (Pixel): "+explorer.getX()+"\nExplorer Y (Pixel): "+explorer.getY()+"\nMaze Length: "+maze.getWalls().length+"\nMaze Width: "+maze.getWalls()[0].length);
			System.out.println("Direction: "+dir);
			if(e.getKeyCode()==32&&map){
				if(toggle){
					toggle=false;
				}
				else toggle=true;
			}
/*			if(e.getKeyCode()==77){
				if(toggle){
					toggle=false;
				}
				else toggle=true;
			}		*/ //M FOR MINIMAP DEBUG
			repaint();
		}
	}
	public void keyReleased(KeyEvent e)
	{

	}
	public void keyTyped(KeyEvent e)
	{

	}
	public void mouseClicked(MouseEvent e)
	{

	}
	public void mousePressed(MouseEvent e)
	{

	}
	public void mouseReleased(MouseEvent e)
	{

	}
	public void mouseEntered(MouseEvent e)
	{

	}
	public void mouseExited(MouseEvent e)
	{

	}
	public static void main(String args[])
	{
		ShahilPatelMazeProgram app=new ShahilPatelMazeProgram();
	}
}