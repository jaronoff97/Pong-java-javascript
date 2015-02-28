package pong;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class PongPaddle {
public int xpos=0,ypos=0,width=50,height=200;
public int dx=15,dy=15;
public int baseXpos = 0;
public final int BASE_YPOS=50;
public Rectangle paddle;
public int score=0;
public int direction=0;
public Color color = Color.white;
public final Color DEFAULT_COLOR = Color.white;
public String name;

public Rectangle top;
public Rectangle center;
public Rectangle bottom;

	public PongPaddle(int x, int y, String n) {
		setXpos(x);
		setYpos(y);
		setName(n);
		paddle=new Rectangle(getXpos(), getYpos(), getWidth(), getHeight());


		/*top = new Rectangle(getXpos(), getYpos(), getWidth(), getHeight()/4);
		center = new Rectangle(getXpos(), (getYpos()+getHeight()/4), getWidth(), (getHeight())/2);
		bottom = new Rectangle(getXpos(), (getYpos()+(getHeight()*3)/4), getWidth(), (getHeight())/4);*/
		
		top = new Rectangle(getXpos(), getYpos(), getWidth(), getHeight()/3);
		center = new Rectangle(getXpos(), (getYpos()+getHeight()/3), getWidth(), (getHeight())/3);
		bottom = new Rectangle(getXpos(), (getYpos()+(getHeight()*2)/3), getWidth(), (getHeight())/3);
		
		// TODO Auto-generated constructor stub
	}
	public void moveUp(){
		direction=1;
	}
	public void moveDown(){
		direction=2;
	}
	public void move(){
		if(getYpos()>=PongGame.worldSizeY){
			setYpos(0);
		}
		if(getYpos()+getHeight()<=0){
			setYpos(PongGame.worldSizeY);
		}
		switch(direction){
		case 0: break;
		case 1:ypos-=dy;
		updatePlaces();
		break;
		case 2:	ypos+=dy;
		updatePlaces();
		break;
		default: updatePlaces(); 
		break;
		}
		
	}
	public void updatePlaces(){
		paddle=new Rectangle(getXpos(), getYpos(), getWidth(), getHeight());

		top = new Rectangle(getXpos(), getYpos(), getWidth(), getHeight()/3);
		center = new Rectangle(getXpos(), (getYpos()+getHeight()/3), getWidth(), (getHeight())/3);
		bottom = new Rectangle(getXpos(), (getYpos()+(getHeight()*2)/3), getWidth(), (getHeight())/3);
	}
	public void draw(Graphics graphics){
		graphics.setColor(color);
		//graphics.fill(paddle);
		graphics.setColor(DEFAULT_COLOR);
		drawScore(graphics);
		//drawFloats(graphics);
		graphics.setColor(Color.magenta);
		graphics.fill(top);
		graphics.setColor(Color.cyan);
		graphics.fill(center);
		graphics.setColor(Color.red);
		graphics.fill(bottom);
		graphics.setColor(Color.white);
	}
	/**
	 * @param graphics
	 */
	public void drawFloats(Graphics graphics) {
		for(int i=0;i<paddle.getPoints().length;i++){
			graphics.drawRect((int)paddle.getPoints()[i], (int)paddle.getPoints()[i], (int)paddle.getPoints()[i], (int)paddle.getPoints()[i]);
		}
	}
	public void drawScore( Graphics graphics){
		graphics.setColor(DEFAULT_COLOR);
		graphics.drawString(name+": "+score,baseXpos,BASE_YPOS);
	}
	public int Intersects(PongObject s){
		updatePlaces();
		int part=0;
		
		
		if(paddle.intersects(s.ballRec)){
			if(top.intersects(s.ballRec)){
				part=1;
			}
			if(center.intersects(s.ballRec)){
				part=2;
			}
			if(bottom.intersects(s.ballRec)){
				part=3;
			}
		}
		else{
			part=0;
		}
		return(part);
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
	public void setDx(int dx) {
		this.dx = dx;
	}
	public void setDy(int dy) {
		this.dy = dy;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setXpos(int xpos) {
		this.xpos = xpos;
		this.baseXpos=xpos;
	}
	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	public int getDx() {
		return dx;
	}
	public int getDirection() {
		return direction;
	}
	public int getDy() {
		return dy;
	}
	public int getHeight() {
		return height;
	}
	public String getName() {
		return name;
	}
	public int getWidth() {
		return width;
	}
	public int getXpos() {
		return xpos;
	}
	public int getYpos() {
		return ypos;
	}

}
