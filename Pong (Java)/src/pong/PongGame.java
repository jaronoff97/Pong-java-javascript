package pong;
import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;




public class PongGame extends BasicGame {

public static int worldSizeX=1920;
public static int worldSizeY=1200;
public static PongObject ball;
public ArrayList<PongPaddle> players;
public final static boolean FULL_SCREEN=true;
public final static int DEFAULT_SPEED=10;
public boolean paused=false;
public SineWave sineWave;

	public PongGame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawString("Pong", 100, 10);
		g.drawLine(worldSizeX/2, 0, worldSizeX/2, worldSizeY);
		//g.setLineWidth(30);
		
		sineWave.draw(g, new Point(worldSizeX/2,0), new Point(worldSizeX/2,worldSizeY));
		//ball.drawFloats(g);
		ball.draw(g);
		for(PongPaddle pP: players){
			pP.draw(g);
		}
		
		gc.setTargetFrameRate(60);
		
		
		
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		ball=new PongObject(200, 200);
		players=new ArrayList<PongPaddle>();
		makePlayers();
		sineWave = new SineWave(200, 60, 60);
		gc.setIcon("/Users/JEAcomputer/Documents/Programming/Pong/src/pong/PongIcon.png");

	}
	public void Reset(){
		ball.reset();
	}
	@Override
	public void keyPressed(int key, char c) {
		System.out.println(c+" , "+key);
		if(c==('w')){
			players.get(0).moveUp();
		}
		if(c==('s')){
			players.get(0).moveDown();
		}
		if(key==(200)){
			players.get(1).moveUp();
		}
		if(key==(208)){
			players.get(1).moveDown();
		}
		if(key==(1)){
			paused=(!paused);
				
			}
			
		}
		
	@Override
	public void keyReleased(int key, char c) {
		if(c=='w' || c=='s'){
			players.get(0).setDirection(0);
		}
		if(key==(208) || key==(200)){
			players.get(1).setDirection(0);
		}
		
	};

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		// TODO Auto-generated method stub
		checkIntersections();
		ball.move();
		for(PongPaddle pP: players){
			pP.move();
		}
		gc.setPaused(paused);
		
	}
	public void makePlayers(){
		for(int i=0;i<2;i++){
			players.add(new PongPaddle(100+(i*(worldSizeX-300)), worldSizeY/2, "Player "+i));
			//players.get(i).setColor(new Color((i+1)*(int)(Math.random()*127)));
		}
		players.get(0).setColor(Color.yellow);
		players.get(1).setColor(Color.blue);
	}
	public int otherPlayer(int i){
		int returned=0;
		if(i==0){
			returned=1;
		}
		else{
			returned=0;
		}
		
		return(returned);
	}
	public void checkIntersections(){
		
		for(int i=0;i<players.size();i++){
			ball.updatePlace();
			
				ball.setDx(-(ball.getDx()));
			int part=players.get(i).Intersects(ball);
			switch(part){
			case 0: break;
			case 1:// ball.setTheta(( -1*(Math.PI)/3)*ball.getTheta());

						ball.setTheta(((Math.PI)*(Math.PI)*1/3)*ball.getTheta());
						ball.updatePlace();
						break;
			case 2: ball.setTheta((Math.PI)*(Math.PI-ball.getTheta()));
						ball.updatePlace();
						break;
			case 3: ball.setTheta(((Math.PI)*(Math.PI)*2/3)*ball.getTheta());
						//ball.setTheta((1*(Math.PI)*2/3));
						ball.updatePlace();
						break;
			default: break;
			}
			if(ball.checkScore(players.get(i).xpos, players.get(i).ypos)==true){
				players.get(otherPlayer(i)).score+=1;
				Reset();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new PongGame("PongGame"));
			appgc.setDisplayMode(worldSizeX, worldSizeY, FULL_SCREEN);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(PongGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
