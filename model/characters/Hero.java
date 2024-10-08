package model.characters;

import java.awt.Point;
import java.util.ArrayList;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import model.collectibles.Supply;
import model.collectibles.Vaccine;

public abstract class Hero extends Character {

	private int actionsAvailable;
	private int maxActions;
	private ArrayList<Vaccine> vaccineInventory;
	private ArrayList<Supply> supplyInventory;
	private boolean specialAction;

	public Hero(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg);
		this.maxActions = maxActions;
		this.actionsAvailable = maxActions;
		this.vaccineInventory = new ArrayList<Vaccine>();
		this.supplyInventory = new ArrayList<Supply>();
		this.specialAction = false;

	}

	public boolean isSpecialAction() {
		return specialAction;
	}

	public void setSpecialAction(boolean specialAction) {
		this.specialAction = specialAction;
	}

	public int getActionsAvailable() {
		return actionsAvailable;
	}

	public void setActionsAvailable(int actionsAvailable) {
		this.actionsAvailable = actionsAvailable;
	}

	public int getMaxActions() {
		return maxActions;
	}

	public ArrayList<Vaccine> getVaccineInventory() {
		return vaccineInventory;
	}

	public ArrayList<Supply> getSupplyInventory() {
		return supplyInventory;
	}

	

	public void helpersetadjacentcells(Point cur)throws MovementException {

		int upperBoundX = cur.x + 1;
		int lowerBoundX = cur.x - 1;
		int upperBoundY = cur.y + 1;
		int lowerBoundY = cur.y - 1;
		
    Game.map[cur.x][cur.y].setVisible(true);
	if (upperBoundX > 14) {
			upperBoundX = 14;
		}
	if (upperBoundY > 14) {
			upperBoundY = 14;
		}
		if (lowerBoundX < 0) {
			lowerBoundX = 0;
		}
		if (lowerBoundY < 0) {
			lowerBoundY = 0;
		}
		
		for (int i = 0; i < 15; i++)
		{
			for(int j = 0; j < 15; j++)
			{
				if (Game.map[i][j] == null)
				Game.map[i][j] = new CharacterCell(null);
			}
		}
		
	
		Game.map[cur.x][lowerBoundY].setVisible(true);
		Game.map[cur.x][upperBoundY].setVisible(true);

		Game.map[upperBoundX][cur.y].setVisible(true);
		Game.map[upperBoundX][lowerBoundY].setVisible(true);
		Game.map[upperBoundX][upperBoundY].setVisible(true);

		Game.map[lowerBoundX][cur.y].setVisible(true);
		Game.map[lowerBoundX][lowerBoundY].setVisible(true);

		Game.map[lowerBoundX][upperBoundY].setVisible(true);
		

	}
	public void attack()throws InvalidTargetException, NotEnoughActionsException{
		
		
		if ( this.getTarget() instanceof Hero)
			throw new InvalidTargetException("Heroes cannot attack each other");
		else
			super.attack();
			this.setActionsAvailable(this.actionsAvailable-1);
        
	}
	


	
	public void move(Direction d) throws MovementException, NotEnoughActionsException {
		if (this.getActionsAvailable() < 1)
			throw new NotEnoughActionsException("You need at least one action point to move");
		int currx = (int) this.getLocation().getX();
		int curry = (int) this.getLocation().getY();
		int newx = currx;
		int newy = curry;
		
		
		if (d == Direction.UP){
			
			newx = newx + 1;
		}
	   
		else if (d == Direction.DOWN) {
			newx = newx - 1;
		}
		else if (d == Direction.LEFT) {
			newy = newy - 1;
		
		}
		else if (d == Direction.RIGHT){ 
			newy = newy + 1;
		}
		
		if((newy > 14 &&d==Direction.RIGHT))
	    	throw new MovementException("Can not move out of the board");
		
		if((newx > 14&&d==Direction.UP))
			throw new MovementException("Can not move out of the board");
		
		if((newx < 0 && d==Direction.DOWN))
			throw new MovementException("Can not move out of the board");
		
		if((newy < 0 &&d==Direction.LEFT))
			throw new MovementException("Can not move out of the board");
			
	    
		
		
		
		if( Game.map[newx][newy] instanceof CharacterCell&&((CharacterCell) Game.map[newx][newy]).getCharacter() != null) 
			throw new MovementException("target cell is not empty");
		
		if (Game.map[newx][newy] instanceof CharacterCell&&((CharacterCell) Game.map[newx][newy]).getCharacter() == null){
			this.setActionsAvailable(this.actionsAvailable-1);
			this.setLocation(new Point(newx, newy));
			Game.map[newx][newy] = new CharacterCell(this);
			Game.map[newx][newy].setVisible(true);
			Game.map[currx][curry] =new CharacterCell(null);
			helpersetadjacentcells(this.getLocation());
	        
		 } 
			
		else if(Game.map[newx][newy] instanceof TrapCell) {
			if (((TrapCell) Game.map[newx][newy]).getTrapDamage() > this.getCurrentHp()) {
				this.setActionsAvailable(this.actionsAvailable-1);
				Game.map[newx][newy] =new CharacterCell(null);
				Game.map[newx][newy] = new CharacterCell(this);
				this.onCharacterDeath();
				Game.map[currx][curry] =new CharacterCell(null);
				Game.map[newx][newy].setVisible(false);
				Game.map[currx][curry].setVisible(false);
				
				
				
				
				
				
				
			   
				
				
				
				
				
				
				
			}

			 else {
				 this.setActionsAvailable(this.actionsAvailable-1);
				int t = (int) ((TrapCell) Game.map[newx][newy]).getTrapDamage();
				this.setCurrentHp(this.getCurrentHp() - t);
				Game.map[newx][newy] =new CharacterCell(null);
				this.setLocation(new Point(newx, newy));
				Game.map[newx][newy] = new CharacterCell(this);
				Game.map[currx][curry] =new CharacterCell(null);
				Game.map[newx][newy].setVisible(true);
				helpersetadjacentcells(this.getLocation());
				
		     }
		
			    	
		}
		else if(Game.map[newx][newy] instanceof CollectibleCell&&Game.map[newx][newy] !=null ) {
			this.setActionsAvailable(this.actionsAvailable-1);
			((CollectibleCell) Game.map[newx][newy]).getCollectible().pickUp(this);
			
			 Game.map[newx][newy] = new CharacterCell(null);
			 this.setLocation(new Point(newx, newy));
			 Game.map[newx][newy] = new CharacterCell(this);
			 Game.map[currx][curry] =new CharacterCell(null);
			 Game.map[newx][newy].setVisible(true);
			 helpersetadjacentcells(this.getLocation());
		}
		
		
		
		if(this.getCurrentHp()<1){
		  this.onCharacterDeath();
		 Game.map[newx][newy].setVisible(false);
		 Game.map[currx][curry].setVisible(false);
		}
	    

	}
	
	
	
	

	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException {
		if(supplyInventory.isEmpty()){
			throw new NoAvailableResourcesException("cannot use special action no supply available");
		}
		else {
			setSpecialAction(true);
			int i = supplyInventory.size();
			supplyInventory.remove(supplyInventory.get(i - 1));
			
		}

		
	}

	public void cure() throws InvalidTargetException,NotEnoughActionsException, NoAvailableResourcesException {
		if (vaccineInventory.isEmpty())
			throw new NoAvailableResourcesException(
					"cannot use special action no supply available");
		
		else if (this.getTarget() == null)
			throw new InvalidTargetException("You cannot cure nothing");
		
		else if (this.getTarget().getLocation().x - this.getLocation().x > 1
				|| this.getTarget().getLocation().y - this.getLocation().y > 1
				|| this.getTarget().getLocation().x - this.getLocation().x < -1
				|| this.getTarget().getLocation().y - this.getLocation().y < -1)
			throw new InvalidTargetException("Cannot use special power");
		
		if (this.getActionsAvailable() > 0) {
			
			if (this.getTarget() instanceof Zombie) {
				this.setActionsAvailable(this.getActionsAvailable() - 1);
				Point p = this.getTarget().getLocation();
				this.getVaccineInventory().get(0).use(this);

				
//				int rIndex = (int) Math.random()*Game.availableHeroes.size();
//				Hero b = Game.availableHeroes.remove(rIndex);
//				 Game.heroes.add(b);

			} else
				throw new InvalidTargetException("Not zombie");

		}else if ( this.getActionsAvailable() < 1)
			throw new NotEnoughActionsException("You have no actions Available");

		
}

	public void onCharacterDeath() {
		//((CharacterCell)Game.map[(int)this.getLocation().getX()][(int)this.getLocation().getY()]).setCharacter(null);
		super.onCharacterDeath();

	}
	


}
