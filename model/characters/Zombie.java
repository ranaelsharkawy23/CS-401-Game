package model.characters;

import java.awt.Point;
import model.world.CharacterCell;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

public class Zombie extends Character {
	static int ZOMBIES_COUNT = 1;

	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}


	
	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		
		for(int i=0;i<Game.map.length;i++) {
			for(int j=0;j<Game.map[i].length;j++) {
				if(Game.map[i][j] instanceof CharacterCell && ((CharacterCell)Game.map[i][j]).getCharacter() instanceof Hero && Math.abs(this.getLocation().x-i)<=1&&Math.abs(this.getLocation().y-i)<=1) {
					this.setTarget(((CharacterCell)Game.map[i][j]).getCharacter());
					break;
				}
				
			}
		}
	  if(this.getTarget() instanceof Hero) {
		  this.getTarget().setCurrentHp(this.getTarget().getCurrentHp()-this.getAttackDmg());
		  this.getTarget().defend(this);
		  if(this.getTarget().getCurrentHp()<=0)
			  this.getTarget().onCharacterDeath();
	  }
	  else
		  this.setTarget(null);
	      
	}

	public void onCharacterDeath()  {
		Point oldzomb=this.getLocation();
		super.onCharacterDeath();
		
		createzombie(oldzomb);

	}

	public void createzombie(Point p)  {
		
		Point newlocation = createpoint();
		if(newlocation!=p){
			Zombie z = new Zombie();
			Game.zombies.add(z);
		    z.setLocation(newlocation);
	        Game.map[newlocation.x][newlocation.y] = new CharacterCell(z);
		}
		else
			createzombie(p);

	}   

	public static Point createpoint(){

		
		int newxint = (int)(Math.random()*15);
		int newyint =(int)(Math.random()*15);
		Point newlocation = new Point(newxint, newyint);
		
		if (Game.map[newlocation.x][newlocation.y] instanceof CharacterCell &&((CharacterCell) Game.map[newlocation.x][newlocation.y]).getCharacter()==null)
			 newlocation = new Point(newxint, newyint);
		else
			createpoint();
		
			
		 return newlocation;
	}

}
