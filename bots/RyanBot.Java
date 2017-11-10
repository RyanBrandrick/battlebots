package bots;
import java.awt.Graphics;
import java.awt.Image;

import arena.BattleBotArena;
import arena.BotInfo;
import arena.Bullet;

/**
 * 
 */

/**
 * @author Ryan
 *
 */
public class RyanBot extends Bot {

	/**
	 * 
	 */
	Image up, down, left, right, current;
	int choice = 9;
	int xPos = (int)(Math.floor(Math.random()*300)+200);
	int yPos = (int)(Math.floor(Math.random()*300)+200);
	public RyanBot() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see bots.Bot#newRound()
	 */
	@Override
	public void newRound() {
		// TODO Auto-generated method stub
		xPos = (int)(Math.floor(Math.random()*300)+200);
		yPos = (int)(Math.floor(Math.random()*300)+200);
	}

	/* (non-Javadoc)
	 * @see bots.Bot#getMove(arena.BotInfo, boolean, arena.BotInfo[], arena.BotInfo[], arena.Bullet[])
	 */
	@Override
	public int getMove(BotInfo me, boolean shotOK, BotInfo[] liveBots, BotInfo[] deadBots, Bullet[] bullets) {
		// TODO Auto-generated method stub
		Bullet nearestBull = bullets[0];
		BotInfo nearestBot = liveBots[0];
		for(int i = 0;i<deadBots.length;i++) {
			if((me.getX()!=xPos)||(me.getX()!=xPos+1)||(me.getX()!=xPos+2)) {
				if(me.getX()>xPos&&me.getX()>xPos+1&&me.getX()>xPos+2) {
					choice = BattleBotArena.LEFT;
					if(Math.abs(deadBots[i].getX()-me.getX())<50){
						if(Math.abs(BattleBotArena.TOP_EDGE-me.getY())>100) {
							choice = BattleBotArena.UP;
						}else {
							choice = BattleBotArena.DOWN;
						}
					}
				}
				else if(me.getX()<xPos&&me.getX()<xPos+1&&me.getX()<xPos+2) {
					choice = BattleBotArena.RIGHT;
					if(Math.abs(deadBots[i].getX()-me.getX())<50){
						if(Math.abs(BattleBotArena.TOP_EDGE-me.getY())>100) {
							choice = BattleBotArena.UP;
						}else {
							choice = BattleBotArena.DOWN;
						}
					}
				}
			}
			if((me.getY()!=yPos)||(me.getY()!=yPos+1)||(me.getY()!=yPos+2)) {
				if(me.getY()>yPos&&me.getY()>yPos+1&&me.getY()>yPos+2) {
					choice = BattleBotArena.UP;
					if(Math.abs(deadBots[i].getY()-me.getY())<50){
						if(Math.abs(BattleBotArena.LEFT_EDGE-me.getX())>100) {
							choice = BattleBotArena.LEFT;
						}else {
							choice = BattleBotArena.RIGHT;
						}
					}
				}
				else if(me.getY()<yPos&&me.getY()<yPos+1&&me.getY()<yPos+2) {
					choice = BattleBotArena.DOWN;
					if(Math.abs(deadBots[i].getY()-me.getY())<50){
						if(Math.abs(BattleBotArena.LEFT_EDGE-me.getX())>100) {
							choice = BattleBotArena.LEFT;
						}else {
							choice = BattleBotArena.RIGHT;
						}
					}
				}
			}
		}
		if(shotOK) {
			if((xPos+2>=me.getX()&&me.getX()>=xPos)&&(yPos+2>=me.getY()&&me.getY()>=yPos)){
				for(int i = 0;i<liveBots.length;i++) {
					if(me.getX()+13>=liveBots[i].getX()&&me.getX()+13<=liveBots[i].getX()+26) {
						if(liveBots[i].getY()>me.getY()) {
							choice = BattleBotArena.FIREDOWN;
						}
						else if(liveBots[i].getY()<me.getY()) {
							choice = BattleBotArena.FIREUP;
						}
					}
					if(me.getY()+13>=liveBots[i].getY()&&me.getY()+13<=liveBots[i].getY()+26) {
						if(liveBots[i].getX()>me.getX()) {
							choice = BattleBotArena.FIRERIGHT;
						}
						else if(liveBots[i].getX()<me.getX()) {
							choice = BattleBotArena.FIRELEFT;
						}
					}
				}
			}
		}
		
		for(int i = 0; i<bullets.length; i++) {
			//still need to check for walls
			if(bullets.length>0) {
				if(Math.sqrt(Math.pow(me.getX()-bullets[i].getX(),2)+Math.pow(me.getY()-bullets[i].getY(),2))<Math.sqrt(Math.pow(me.getX()-nearestBull.getX(),2)+Math.pow(me.getY()-nearestBull.getY(),2))){
				//if((Math.sqrt(Math.pow(me.getX(), 2)+Math.pow(bullets[i].getX(), 2))+Math.sqrt(Math.pow(me.getY(), 2)+Math.pow(bullets[i].getY(), 2))<Math.sqrt(Math.pow(me.getX(), 2)+Math.pow(nearestBull.getX(), 2))+Math.sqrt(Math.pow(me.getX(), 2)+Math.pow(nearestBull.getY(), 2)))&&((bullets[i].getXSpeed()>0&&me.getX()>bullets[i].getX())||(bullets[i].getXSpeed()<0&&me.getX()<bullets[i].getX())||(bullets[i].getYSpeed()>0&&me.getY()>bullets[i].getY())||(bullets[i].getYSpeed()<0&&me.getY()<bullets[i].getY()))) {
					nearestBull = bullets[i];
				}
				if((nearestBull.getXSpeed()>0&&me.getX()>nearestBull.getX())||(nearestBull.getXSpeed()<0&&me.getX()<nearestBull.getX())&&Math.abs(me.getX()-nearestBull.getX())<100) {
					if((nearestBull.getY()>=me.getY()+13)&&(nearestBull.getY()<me.getY()+63)) {
						//move down
						choice = BattleBotArena.UP;
						if(Math.abs(me.getY()-BattleBotArena.TOP_EDGE)<100) {
							choice = BattleBotArena.DOWN;
						}
					}
					else if((nearestBull.getY()<me.getY()+13)&&(nearestBull.getY()>me.getY()-37)){
						//move up
						choice = BattleBotArena.DOWN;
						if(Math.abs(me.getY()-BattleBotArena.BOTTOM_EDGE)<100) {
							choice = BattleBotArena.UP;
						}
					}
				}
				//still need to check for walls
				else if((nearestBull.getYSpeed()>0&&me.getY()>nearestBull.getY())||(nearestBull.getYSpeed()<0&&me.getY()<nearestBull.getY())&&Math.abs(me.getY()-nearestBull.getY())<100) {
					if((nearestBull.getX()>=me.getX()+13)&&(nearestBull.getX()<me.getX()+63)) {
						//move down
						choice = BattleBotArena.LEFT;
						if(Math.abs(me.getX()-BattleBotArena.LEFT_EDGE)<100) {
							choice = BattleBotArena.RIGHT;
						}
					}
					else if((nearestBull.getX()<me.getX()+13)&&(nearestBull.getX()>me.getX()-37)){
						//move up
						choice = BattleBotArena.RIGHT;
						if(Math.abs(me.getX()-BattleBotArena.RIGHT_EDGE)<100) {
							choice = BattleBotArena.LEFT;
						}
					}
				}
			}
			if(((Math.abs(me.getX()-nearestBull.getX())>100)&&(Math.abs(me.getY()-nearestBull.getY())>100))||bullets.length==0){
				for(int j =0; j<liveBots.length; j++) {
					if((Math.sqrt(Math.pow(me.getX()-nearestBot.getX(), 2)))>(Math.sqrt(Math.pow(me.getX()-liveBots[i].getX(), 2)))){
						nearestBot = liveBots[i];
					}
				}
				//choice = (int)(Math.random()*3+1);
			}
		}
		return choice;
	}

	/* (non-Javadoc)
	 * @see bots.Bot#draw(java.awt.Graphics, int, int)
	 */
	@Override
	public void draw(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		g.drawImage(current, x, y, Bot.RADIUS*2, Bot.RADIUS*2, null);

	}

	/* (non-Javadoc)
	 * @see bots.Bot#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "RyanBot";
	}

	/* (non-Javadoc)
	 * @see bots.Bot#getTeamName()
	 */
	@Override
	public String getTeamName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see bots.Bot#outgoingMessage()
	 */
	@Override
	public String outgoingMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see bots.Bot#incomingMessage(int, java.lang.String)
	 */
	@Override
	public void incomingMessage(int botNum, String msg) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see bots.Bot#imageNames()
	 */
	@Override
	public String[] imageNames() {
		// TODO Auto-generated method stub
		String[] images = {"Penrose.png"};
		return images;
	}

	/* (non-Javadoc)
	 * @see bots.Bot#loadedImages(java.awt.Image[])
	 */
	@Override
	public void loadedImages(Image[] images) {
		// TODO Auto-generated method stub
		current = up = images[0];
		/*down = images[1];
		left = images[2];
		right = images[3];
		*/
	}

}
