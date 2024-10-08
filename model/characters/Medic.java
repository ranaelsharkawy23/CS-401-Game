package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Medic extends Hero {

	public Medic(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);

	}
	public boolean isAdjacent(){
		if (this.getTarget().getLocation().x - this.getLocation().x > 1
				|| this.getTarget().getLocation().y - this.getLocation().y > 1
				|| this.getTarget().getLocation().x - this.getLocation().x < -1
				|| this.getTarget().getLocation().y - this.getLocation().y < -1)
			return true;
		return false;

	}

	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException {
		if(this.getTarget() instanceof Zombie || this.isAdjacent())
			throw new InvalidTargetException("invalid target");
		
		
		/*if (this.getTarget().getLocation().x - this.getLocation().x > 1
				|| this.getTarget().getLocation().y - this.getLocation().y > 1
				|| this.getTarget().getLocation().x - this.getLocation().x < -1
				|| this.getTarget().getLocation().y - this.getLocation().y < -1)
			throw new InvalidTargetException("Cannot use special power");
			*/

		else if (this.getActionsAvailable() > 0) {
			super.useSpecial();
			this.setSpecialAction(true);
			if(getTarget().equals(this))
				this.setCurrentHp(this.getMaxHp());
			
			else
				((Hero)getTarget()).setCurrentHp(getMaxHp());
			
				
			}
		
		else
			throw new NotEnoughActionsException("not enough actions");

	
}
	public void attack() throws InvalidTargetException,NotEnoughActionsException{
		if(this.getTarget()==null)
			  throw new InvalidTargetException("Heroes cannot attack nothing");
		    if ( this.getActionsAvailable() < 1)
				throw new NotEnoughActionsException("You have no actions Available");
	        if (this instanceof Hero && this.getTarget() instanceof Hero)
				throw new InvalidTargetException("Heroes cannot attack each other");
	   
		
	   
		   super.attack();
		  
	   }
		
		
	}


