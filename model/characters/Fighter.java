package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;


public class Fighter extends Hero{

	
	public Fighter(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
	}

	public void attack() throws InvalidTargetException,NotEnoughActionsException{
		if(this.getTarget()==null ||  this.getTarget() instanceof Hero )
			  throw new InvalidTargetException("Heroes cannot attack nothing");
		if (this.getActionsAvailable() < 1)
				throw new NotEnoughActionsException("You have no actions Available");
	    if(isSpecialAction()==true && this.getActionsAvailable()>=0){
			   this.setActionsAvailable(this.getActionsAvailable()+1);
			   int hp=getCurrentHp();
			   super.attack();
			   setCurrentHp(hp);
		   }
//			super.attack();
//			//super.useSpecial();
//		   this.setActionsAvailable(this.getActionsAvailable()+1);
	   
	   else if(isSpecialAction()==false && this.getActionsAvailable()>0){
		   super.attack();
		   
		   
		   
		   
	   }
		
		
	}

}
	
	
	
	
	
	
	
	

	
	
	
	


