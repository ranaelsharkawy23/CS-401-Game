package model.characters;

import java.awt.Point;

import model.world.CharacterCell;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

public abstract class Character {
	private String name;
	private Point location;
	private int maxHp;
	private int currentHp;
	private int attackDmg;
	private Character target;

	public Character() {
	}

	public Character(String name, int maxHp, int attackDmg) {
		this.name = name;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.attackDmg = attackDmg;
	}

	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}

	public String getName() {
		return name;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		if (currentHp < 0)
			this.currentHp = 0;
		if(this.getCurrentHp()==0){
			this.onCharacterDeath();
		}
		else if (currentHp > maxHp)
			this.currentHp = maxHp;
		else
			this.currentHp = currentHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}
	
	public boolean adjacentCell(Point current, Point target) {
		boolean result=false;
	if((current.x + 1 == target.x || current.y + 1 == target.y) || (current.x - 1 == target.x || current.y - 1 == target.y
				|| (current.x + 1 == target.x && current.y + 1 == target.y) || (current.x - 1 == target.x && current.y - 1 == target.y)
				|| (current.x + 1 == target.x && current.y - 1 == target.y)
				|| (current.x - 1 == target.x && current.y + 1 == target.y))&&(current.x+1<15&&current.y+1<15&&target.x+1<15&&target.y+1<15&&current.x-1>=0&&current.y-1>=0&&target.x-1>=0&&target.y-1>=0))
				result=true;
		        
		return result;
		
	}

	public void attack()throws InvalidTargetException, NotEnoughActionsException{
		
		if(this.getTarget()==null || !adjacentCell(getLocation(),getTarget().getLocation()))
			throw new InvalidTargetException("out of range");
		
		else{
			if(adjacentCell(getLocation(),getTarget().getLocation())) {
				int targetHP = this.target.getCurrentHp();
				int a = targetHP- this.getAttackDmg();
				this.target.setCurrentHp(a);
				if(this.getTarget().getCurrentHp()<=0)
					this.getTarget().onCharacterDeath();
				target.defend(this);
				if(this.currentHp <= 0){
					this.onCharacterDeath();
				}
				
				
			}
			

		}
	
	}


	    
	   
	
		
		  
		    

	

	public void defend(Character c){
		this.setTarget(c);
		int d = this.getAttackDmg() / 2;
		c.setCurrentHp(c.getCurrentHp() - d);
		if (c.getCurrentHp() == 0) {
			c.onCharacterDeath();
			
		}
	}

	public void onCharacterDeath()  {
		if(this instanceof Hero){
		 Game.heroes.remove(this);
	     Game.map[this.getLocation().x][this.getLocation().y]=new CharacterCell(null);
	    
		}
		else if(this instanceof Zombie){
			 
		     Game.map[this.getLocation().x][this.getLocation().y]=new CharacterCell(null);
		     Game.zombies.remove(this);
		 }
		
	 }
}
