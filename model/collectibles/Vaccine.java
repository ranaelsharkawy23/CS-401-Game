package model.collectibles;
import engine.Game;
import model.world.CharacterCell;
import model.characters.Hero;

public class Vaccine implements Collectible {

	public Vaccine() {
		
	}

	
	public void pickUp(Hero h) {
		h.getVaccineInventory().add(this);
		
		
	}

	
	public void use(Hero h) {
		if(!h.getVaccineInventory().isEmpty()){
		 
		h.getVaccineInventory().remove(this);
		
		Game.zombies.remove(h.getTarget());
		
		int rIndex = (int) Math.random()*Game.availableHeroes.size();
		
		Game.availableHeroes.get(rIndex).setLocation(h.getTarget().getLocation());
		
		Hero b = Game.availableHeroes.remove(rIndex);
		 
		Game.map[h.getTarget().getLocation().x][h.getTarget().getLocation().y]=new CharacterCell(b);
		 
		Game.heroes.add(b);
		}
	}

	
	
}
