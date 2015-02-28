package pong;


import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.Graphics;


public class PongObject {
public int xpos=0, ypos=0, width=100,height=100;
public double dx=10;
public double dy=10;
public double theta=0;
public double hypotenuse;
public Ellipse ball;
public Rectangle ballRec;

	public PongObject(int x, int y) {
		// TODO Auto-generated constructor stub
		xpos=x;
		ypos=y;
		
		calculateHypotenuse();
		calculateDx();
		calculateDy();
		calculateHypotenuse();
		
		ballRec = new Rectangle(xpos, ypos, width, height);
		
	}
	public void draw(Graphics graphics){
		updatePlace();
		graphics.fillOval(ballRec.getX(),ballRec.getY(),ballRec.getWidth(),ballRec.getHeight());
		graphics.drawString("Theta: "+theta, 30, 30);

	}
	public void drawFloats(Graphics graphics){
		float[] triangle = new float[6];
		float multiplier=1;
		triangle[0]=xpos*multiplier;
		triangle[1]=ypos*multiplier;
		triangle[2]=xpos*multiplier;
		triangle[3]=(float) (ypos+dy)*multiplier;
		triangle[4]=(float) (xpos+dx)*multiplier;
		triangle[5]=(float) (ypos+dy)*multiplier;
		Polygon tri = new Polygon(triangle);
		
		tri = (Polygon) tri.transform(Transform.createScaleTransform(10, 10));
		graphics.draw(tri);
		for(int i=0;i<ballRec.getPoints().length;i++){
			graphics.drawRect((int)ballRec.getPoints()[i], (int)ballRec.getPoints()[i], (int)ballRec.getPoints()[i], (int)ballRec.getPoints()[i]);
		}
	}
	public int getYpos() {
		return ypos;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public int getXpos() {
		return xpos;
	}
	public Ellipse getBall() {
		return ball;
	}
	public double getDx() {
		return dx;
	}
	public double getDy() {
		return dy;
	}
	public void calculateHypotenuse(){
		hypotenuse=Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2)));
	}
	public void calculateDx(){
		dx=(hypotenuse)*(Math.cos(theta));
	}
	public void calculateDy(){
		dy=(hypotenuse)*(Math.sin(theta));
	}
	public void calculateTheta(){
		theta=Math.asin((dy)/(hypotenuse));
	}
	public void setTheta(double theta){
		this.theta=theta;
		calculateHypotenuse();
		calculateDx();
		calculateDy();
		calculateHypotenuse();
	}
	public double getTheta() {
		return theta;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setXpos(int xpos) {
		this.xpos = xpos;
	}
	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	public void setBall(Ellipse ball) {
		this.ball = ball;
	}
	public void setDx(double dx) {
		this.dx = dx;
	}
	public void setDy(double dy) {
		this.dy = dy;
	}
	public void move(){
		
		xpos+=dx;
		ypos+=dy;
		if(ypos<=0){
			setDy(-getDy());
			calculateHypotenuse();
			calculateTheta();
		}
		if(ypos>=PongGame.worldSizeY-height){
			setDy(-getDy());
			calculateHypotenuse();
			calculateTheta();
			
		}
		updatePlace();
	}
	public void reset(){
		xpos=PongGame.worldSizeX/2;
		ypos=PongGame.worldSizeY/2;
		theta=0;
		calculateHypotenuse();
		calculateDx();
		calculateDy();
		calculateHypotenuse();	
	}
	public void checkTheta(){
		if(theta>=Math.PI*2){
			theta-=Math.PI*2;
		}
		if(theta<=(-1)*Math.PI*2){
			theta+=Math.PI*2;
		}
	}
	public boolean checkScore(int xpos, int ypos){
		boolean returned=false;
		if(PongGame.worldSizeX/2 >=xpos){
			if(this.xpos<=xpos){
				returned=true;
			}
		}//left side
		if(PongGame.worldSizeX/2 <=xpos){
			if(this.xpos>=xpos){
				returned=true;
			}
		}//right side
		
		
		return(returned);
	}
	public void updatePlace(){
		checkTheta();
		ball=new Ellipse(getXpos(), getYpos(), getWidth(), getHeight());
		ballRec=new Rectangle(getXpos(), getYpos(), getWidth(), getHeight());
	}
}
