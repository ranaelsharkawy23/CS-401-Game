package views;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.characters.Character;
import model.characters.Direction;
import model.characters.Hero;
import model.characters.Zombie;
import model.collectibles.Collectible;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;


public class views extends Application {
    static Scene sc;
	static Scene s;
	static GridPane grid;
    static Hero current;
    
    

    public static void startgame2(){
		  for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				Rectangle rec=new Rectangle(50,50);
  			rec.setRotate(90);
  			
  			rec.setFill(Color.BLACK);
  			rec.setStroke(Color.BLUEVIOLET);
  			grid.add(rec,i,j);
				grid.setRotate(-90);
				if(Game.map[i][j] instanceof CollectibleCell){
					Collectible coll=((CollectibleCell)Game.map[i][j]).getCollectible();
					if(coll instanceof Vaccine && Game.map[i][j].isVisible()){
					Image vac=new Image("file:vaccine.png")	;
					ImagePattern v=new ImagePattern(vac);
					rec.setFill(v);
					rec.setAccessibleText("V");
					}
					else if(coll instanceof Supply && Game.map[i][j].isVisible()){
						Image sup=new Image("file:supply.jfif.jpg")	;
						ImagePattern s=new ImagePattern(sup);
						rec.setFill(s);
				    }
				}
				else if(Game.map[i][j] instanceof CharacterCell){
					Character C=((CharacterCell)Game.map[i][j]).getCharacter();
					if(C instanceof Hero){
						Button yay=new Button();
						yay.setPrefWidth(i);
						yay.setPrefHeight(j);
						Image hero1=new Image("file:hero.game.png");
				    	ImageView herov=new ImageView(hero1);
				    	herov.setFitWidth(30);
						herov.setFitHeight(30);
						yay.setGraphic(herov);
						yay.setOnAction(new EventHandler<ActionEvent>(){
	                          public void handle(ActionEvent event) {
								current.setTarget(((CharacterCell) Game.map[(int) yay.getPrefWidth()][(int) yay.getPrefHeight()]).getCharacter());
									
								}
								
							});
							grid.add(yay, i, j);
					}
					else if(C instanceof Zombie && Game.map[i][j].isVisible()){
						Button boo=new Button();
						boo.setPrefWidth(i);
						boo.setPrefHeight(j);
						Image zomb=new Image("file:zombie1.jfif.jpg");
						ImageView z=new ImageView(zomb);
						z.setFitWidth(30);
						z.setFitHeight(30);
						boo.setGraphic(z);
						boo.setOnAction(new EventHandler<ActionEvent>(){
                        public void handle(ActionEvent event) {
							current.setTarget(((CharacterCell) Game.map[(int) boo.getPrefWidth()][(int) boo.getPrefHeight()]).getCharacter());
								
							}
							
						});
						grid.add(boo, i, j);
					
					}
					
				}
				
			}
		}
		
	}
	
    public static String Type(Character c){
		String t="";
		if(c.getName().equals("Joel Miller")){
			t="Fighter";
		}
		else if(c.getName().equals("Ellie Williams")){
			t="Medic";
		}
		else if(c.getName().equals("Tess")){
			t="Explorer";
		}
		else if(c.getName().equals("Riley Abel")){
			t="Explorer";
		}
		else if(c.getName().equals("Tommy Miller")){
			t="Explorer";
		}
		else if(c.getName().equals("Bill")){
			t="Medic";
		}
		else if(c.getName().equals("David")){
			t="Fighter";
		}
		else if(c.getName().equals("Henry Burell")){
			t="Medic";
		}
		return t;
	}
   
	public void start(Stage primaryStage) throws Exception {
		Game.loadHeroes("Heroes.csv");
		primaryStage.setTitle("Last of Us - Legacy");
	    
		Image i=new Image("file:The-Last-of-US-background2.jpg");
		ImageView mv= new ImageView(i);
		Group rt=new Group();
		rt.getChildren().addAll(mv);  
		
		
		
      Button b=new Button("START GAME");
      b.setLayoutX(600);
      b.setLayoutY(100);
      b.setStyle("-fx-font-size: 50px;");
		
		rt.getChildren().addAll(b);
		sc=new Scene(rt,1500,844);

	
		

		primaryStage.setScene(sc);
	    primaryStage.setResizable(false);
		primaryStage.show();

		
		
		VBox root1= new VBox();
      root1.setLayoutX(550);
      root1.setStyle("-fx-font-size: 18px;");
		
      
      b.setOnAction(new EventHandler<ActionEvent>(){

			
			public void handle(ActionEvent event) {
				Image i2=new Image("file:The-Last-of-US-background2.jpg");
				ImageView mv2= new ImageView(i2);
				Group root2=new Group();
				root2.getChildren().addAll(mv2); 
				
				
				Button h1=new Button("Joel Miller,FIGHTER,MaxHp:140,AttackDmg:5,maxActions:30");
				root1.setSpacing(5); 
			    root1.getChildren().addAll(h1);
		
			    h1.setOnAction(e->{
			    	current=Game.availableHeroes.get(0);
                    Game.startGame(current);
                    grid=new GridPane();
			    	startgame2();
                    Group lay=new Group();
			    	VBox label=new VBox();
			    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
			    	Background b=new Background(bg);
			    	label.setBackground(b);
			    	Label l1=new Label();
			    	Label l2=new Label();
			    	Label l3=new Label();
			    	Label l4=new Label();
			    	Label l5=new Label();
			    	Label l6=new Label();
			    	Label l7=new Label();
			    	String name="Name: "+current.getName();
			    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
			    	String actions="Actions: "+""+((current.getActionsAvailable()));
			    	String health="Health Pts: "+""+(current.getCurrentHp());
			    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
			    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
			    	String type="Type: "+Type(current);
			    	l1.setText(name);
			    	l1.setStyle("-fx-font-size: 30px;");
			    	l2.setText(attackdmg);
			    	l2.setStyle("-fx-font-size: 30px;");
			    	l3.setText(actions);
			    	l3.setStyle("-fx-font-size: 30px;");
			    	l4.setText(health);
			    	l4.setStyle("-fx-font-size: 30px;");
			    	l5.setText(vaccines);
			    	l5.setStyle("-fx-font-size: 30px;");
			    	l6.setText(supplies);
			    	l6.setStyle("-fx-font-size: 30px;");
			    	l7.setText(type);
			    	l7.setStyle("-fx-font-size: 30px;");
			    	label.setSpacing(10);
			    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7);
			    	lay.getChildren().addAll(grid,label);
			    	
			    	grid.setLayoutX(300);
			    	grid.setLayoutY(10);
			    	
			    	h1.setStyle("-fx-font-size: 20px;");
			    	Button my=new Button("Joel");
			    	
			    	my.setOnAction(new EventHandler<ActionEvent>(){

						public void handle(ActionEvent event) {
							current=Game.heroes.get(0);
							
						}
			    		
			    	});
			    	
			    	
			    	
			    	Button moveright=new Button("RIGHT");
			    	moveright.setStyle("-fx-font-size: 20px;");
			    	
			    	moveright.setOnAction(new EventHandler<ActionEvent>(){
			            
						
						public void handle(ActionEvent event) {
							try {
								if(current.getLocation().y+1>14) {
									popup("You Cannot Move Out of The Map");
								}
								if(Game.map[current.getLocation().x][current.getLocation().y+1] instanceof TrapCell){
									popup("You Have Entered A Trap Cell!");
								}
								 
								current.move(Direction.RIGHT);
								
								startgame2();
								if(Game.checkWin()==true){
									String wow="YOU WON YAYY!";
									popup2(wow);
									
								}
								else if(Game.checkGameOver()==true){
									String sad="LOSER YOU LOST STUPID!";
									popup2(sad);
								}
							
								
								
								VBox label=new VBox();
						    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
						    	Background b=new Background(bg);
						    	label.setBackground(b);
						    	Label l1=new Label();
						    	Label l2=new Label();
						    	Label l3=new Label();
						    	Label l4=new Label();
						    	Label l5=new Label();
						    	Label l6=new Label();
						    	Label l7=new Label();
						    	String name="Name: "+current.getName();
						    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
						    	String actions="Actions: "+""+((current.getActionsAvailable()));
						    	String health="Health Pts: "+""+(current.getCurrentHp());
						    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
						    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
						    	String type="Type: "+Type(current);
						    	l1.setText(name);
						    	l1.setStyle("-fx-font-size: 30px;");
						    	l2.setText(attackdmg);
						    	l2.setStyle("-fx-font-size: 30px;");
						    	l3.setText(actions);
						    	l3.setStyle("-fx-font-size: 30px;");
						    	l4.setText(health);
						    	l4.setStyle("-fx-font-size: 30px;");
						    	l5.setText(vaccines);
						    	l5.setStyle("-fx-font-size: 30px;");
						    	l6.setText(supplies);
						    	l6.setStyle("-fx-font-size: 30px;");
						    	l7.setText(type);
						    	l7.setStyle("-fx-font-size: 30px;");
						    	label.setSpacing(10);
						    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
						    	lay.getChildren().addAll(label);
						    	int index = 0;
						        
						        
						    	for(int i=1;i<Game.heroes.size();i++){
						    		index++;
						    		
						    		String num=""+index;
							    	Button hero2=new Button(num);
							    	hero2.setStyle("-fx-font-size: 15px;");
							    	hero2.setPrefWidth(index);
							    	hero2.setOnAction(new EventHandler<ActionEvent>(){
                                     
										
										public void handle(ActionEvent event) {
											current=Game.heroes.get((int) hero2.getPrefWidth());
											
                                           
											
										}
							    		
							    	});
							    	label.getChildren().addAll(hero2);
							}
								
								
						    	
								
							} catch (MovementException e) {
                                 
			                    popup(e.getMessage());   
								
							} catch (NotEnoughActionsException e) {
								
								 popup(e.getMessage());
							}
							
						}

						
			    		
			    	});

			    		
			    	
			    	

			    	Button moveleft=new Button("LEFT");
			    	moveleft.setStyle("-fx-font-size: 20px;");
                    moveleft.setOnAction(new EventHandler<ActionEvent>(){
			        
						
						public void handle(ActionEvent event) {
							if(current.getLocation().y-1<0) {
								popup("You Cannot Move Out Of The Map");
							}
							if(Game.map[current.getLocation().x][current.getLocation().y-1] instanceof TrapCell){
								popup("You Have Entered A Trap Cell!");
							} 
							try {

								current.move(Direction.LEFT);
								 
								startgame2();
								if(Game.checkWin()==true){
									String wow="YOU WON YAYY!";
									popup2(wow);
									
								}
								else if(Game.checkGameOver()==true){
									String sad="LOSER YOU LOST STUPID!";
									popup2(sad);
								}
								VBox label=new VBox();
						    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
						    	Background b=new Background(bg);
						    	label.setBackground(b);
						    	Label l1=new Label();
						    	Label l2=new Label();
						    	Label l3=new Label();
						    	Label l4=new Label();
						    	Label l5=new Label();
						    	Label l6=new Label();
						    	Label l7=new Label();
						    	String name="Name: "+current.getName();
						    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
						    	String actions="Actions: "+""+((current.getActionsAvailable()));
						    	String health="Health Pts: "+""+(current.getCurrentHp());
						    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
						    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
						    	String type="Type: "+Type(current);
						    	l1.setText(name);
						    	l1.setStyle("-fx-font-size: 30px;");
						    	l2.setText(attackdmg);
						    	l2.setStyle("-fx-font-size: 30px;");
						    	l3.setText(actions);
						    	l3.setStyle("-fx-font-size: 30px;");
						    	l4.setText(health);
						    	l4.setStyle("-fx-font-size: 30px;");
						    	l5.setText(vaccines);
						    	l5.setStyle("-fx-font-size: 30px;");
						    	l6.setText(supplies);
						    	l6.setStyle("-fx-font-size: 30px;");
						    	l7.setText(type);
						    	l7.setStyle("-fx-font-size: 30px;");
						    	label.setSpacing(10);
						    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
						    	lay.getChildren().add(label);
						    	int index = 0;
						        
						        
						    	for(int i=1;i<Game.heroes.size();i++){
						    		index++;
						    		
						    		String num=""+index;
							    	Button hero2=new Button(num);
							    	hero2.setStyle("-fx-font-size: 15px;");
							    	hero2.setPrefWidth(index);
							    	hero2.setOnAction(new EventHandler<ActionEvent>(){
                                     
										
										public void handle(ActionEvent event) {
											current=Game.heroes.get((int) hero2.getPrefWidth());
											
                                           
											
										}
							    		
							    	});
							    	label.getChildren().addAll(hero2);
							}
								
						    	
								
							} catch (MovementException e) {
								
								 popup(e.getMessage());
								
							} catch (NotEnoughActionsException e) {
								
								 popup(e.getMessage());
							}
							
						}

						
			    		
			    	});
			    	
			    	Button moveup=new Button("UP");
			    	moveup.setStyle("-fx-font-size: 20px;");
	                moveup.setOnAction(new EventHandler<ActionEvent>(){
			        
						
						public void handle(ActionEvent event) {
							try {
								if(current.getLocation().x+1>14) {
									popup("You Cannot Move Out Of The Map");
								}
								if(Game.map[current.getLocation().x+1][current.getLocation().y] instanceof TrapCell){
									popup("You Have Entered A Trap Cell!");
								} 

								current.move(Direction.UP);
								
								startgame2();
								if(Game.checkWin()==true){
									String wow="YOU WON YAYY!";
									popup2(wow);
									
								}
								else if(Game.checkGameOver()==true){
									String sad="LOSER YOU LOST STUPID!";
									popup2(sad);
								}
								VBox label=new VBox();
						    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
						    	Background b=new Background(bg);
						    	label.setBackground(b);
						    	Label l1=new Label();
						    	Label l2=new Label();
						    	Label l3=new Label();
						    	Label l4=new Label();
						    	Label l5=new Label();
						    	Label l6=new Label();
						    	Label l7=new Label();
						    	String name="Name: "+current.getName();
						    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
						    	String actions="Actions: "+""+((current.getActionsAvailable()));
						    	String health="Health Pts: "+""+(current.getCurrentHp());
						    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
						    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
						    	String type="Type: "+Type(current);
						    	l1.setText(name);
						    	l1.setStyle("-fx-font-size: 30px;");
						    	l2.setText(attackdmg);
						    	l2.setStyle("-fx-font-size: 30px;");
						    	l3.setText(actions);
						    	l3.setStyle("-fx-font-size: 30px;");
						    	l4.setText(health);
						    	l4.setStyle("-fx-font-size: 30px;");
						    	l5.setText(vaccines);
						    	l5.setStyle("-fx-font-size: 30px;");
						    	l6.setText(supplies);
						    	l6.setStyle("-fx-font-size: 30px;");
						    	l7.setText(type);
						    	l7.setStyle("-fx-font-size: 30px;");
						    	label.setSpacing(10);
						    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
						    	lay.getChildren().add(label);
						    	int index = 0;
						        
						        
						    	for(int i=1;i<Game.heroes.size();i++){
						    		index++;
						    		
						    		String num=""+index;
							    	Button hero2=new Button(num);
							    	hero2.setStyle("-fx-font-size: 15px;");
							    	hero2.setPrefWidth(index);
							    	hero2.setOnAction(new EventHandler<ActionEvent>(){
                                     
										
										public void handle(ActionEvent event) {
											current=Game.heroes.get((int) hero2.getPrefWidth());
											
                                           
											
										}
							    		
							    	});
							    	label.getChildren().addAll(hero2);
							}
								
						    	
								
							} catch (MovementException e) {
								
								 popup(e.getMessage());
								
							} catch (NotEnoughActionsException e) {
								 popup(e.getMessage());
							
							}
							
						}

						
			    		
			    	});
			    	
			    	Button movedown=new Button("DOWN");
			    	movedown.setStyle("-fx-font-size: 20px;");
                    movedown.setOnAction(new EventHandler<ActionEvent>(){
			        
						
						public void handle(ActionEvent event) {
							try {
								if(current.getLocation().x-1<0) {
									popup("You Cannot Move Out Of The Map");
								}
								if(Game.map[current.getLocation().x-1][current.getLocation().y] instanceof TrapCell){
									popup("You Have Entered A Trap Cell!");
								} 
								current.move(Direction.DOWN);
								
								startgame2();
								if(Game.checkWin()==true){
									String wow="YOU WON YAYY!";
									popup2(wow);
									
								}
								else if(Game.checkGameOver()==true){
									String sad="LOSER YOU LOST STUPID!";
									popup2(sad);
								}
								VBox label=new VBox();
						    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
						    	Background b=new Background(bg);
						    	label.setBackground(b);
						    	Label l1=new Label();
						    	Label l2=new Label();
						    	Label l3=new Label();
						    	Label l4=new Label();
						    	Label l5=new Label();
						    	Label l6=new Label();
						    	Label l7=new Label();
						    	String name="Name: "+current.getName();
						    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
						    	String actions="Actions: "+""+((current.getActionsAvailable()));
						    	String health="Health Pts: "+""+(current.getCurrentHp());
						    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
						    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
						    	String type="Type: "+Type(current);
						    	l1.setText(name);
						    	l1.setStyle("-fx-font-size: 30px;");
						    	l2.setText(attackdmg);
						    	l2.setStyle("-fx-font-size: 30px;");
						    	l3.setText(actions);
						    	l3.setStyle("-fx-font-size: 30px;");
						    	l4.setText(health);
						    	l4.setStyle("-fx-font-size: 30px;");
						    	l5.setText(vaccines);
						    	l5.setStyle("-fx-font-size: 30px;");
						    	l6.setText(supplies);
						    	l6.setStyle("-fx-font-size: 30px;");
						    	l7.setText(type);
						    	l7.setStyle("-fx-font-size: 30px;");
						    	label.setSpacing(10);
						    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
						    	lay.getChildren().add(label);
						    	int index = 0;
						        
						        
						    	for(int i=1;i<Game.heroes.size();i++){
						    		index++;
						    		
						    		String num=""+index;
							    	Button hero2=new Button(num);
							    	hero2.setStyle("-fx-font-size: 15px;");
							    	hero2.setPrefWidth(index);
							    	hero2.setOnAction(new EventHandler<ActionEvent>(){
                                     
										
										public void handle(ActionEvent event) {
											current=Game.heroes.get((int) hero2.getPrefWidth());
											
                                           
											
										}
							    		
							    	});
							    	label.getChildren().addAll(hero2);
							}
								
						    	
								
							} catch (MovementException e) {
								 
								 popup(e.getMessage());
								
							} catch (NotEnoughActionsException e) {
								 popup(e.getMessage());
								
							}
							
						}

						
			    		
			    	});
			    	
			    	Button hit=new Button("ATTACK");
			    	hit.setStyle("-fx-font-size: 20px;"); 
                    hit.setOnAction(new EventHandler<ActionEvent>(){
			        
						
						public void handle(ActionEvent event) {
							try {

								try {
									
									
									current.attack();
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
                                    int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
                                         
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
                                               
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
								} catch (InvalidTargetException e) {
									 popup(e.getMessage());	
								}
								
								
						    	
								
							
							
						}
							catch(NotEnoughActionsException e){
								 popup(e.getMessage());
							}

						}
			    		
			    	});
			    	
			    	Button cure=new Button("CURE");
			    	cure.setStyle("-fx-font-size: 20px;");
                    cure.setOnAction(new EventHandler<ActionEvent>(){
			        
						
						public void handle(ActionEvent event) {
							try {

								try {
									
									current.cure();
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							        int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
                                         
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
                                               
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								    	
								    	
								    	}
							    	lay.getChildren().addAll(label);
							    	
							    	
							    
							    	
							    	
							    	
								} catch (NoAvailableResourcesException e) {
									 popup(e.getMessage());
								} catch (InvalidTargetException e) {
									 popup(e.getMessage());
								}
							
								
						    	
								
							
								
								
							} catch (NotEnoughActionsException e) {
								 popup(e.getMessage());
								
							}
							
						}

						
			    		
			    	});
			    	
			    	
			    	
			    	
			    	Button specialaction=new Button("Special Action");
			    	specialaction.setStyle("-fx-font-size: 20px;");
			    	specialaction.setOnAction(new EventHandler<ActionEvent>(){

	
	                  public void handle(ActionEvent event) {
	                	   try {
							current.useSpecial();
							 startgame2();
							 if(Game.checkWin()==true){
								 String wow="YOU WON YAYY!";
									popup2(wow);
								 
								}
							 else if(Game.checkGameOver()==true){
								 String sad="LOSER YOU LOST STUPID!";
									popup2(sad);
								}
							 VBox label=new VBox();
						    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
						    	Background b=new Background(bg);
						    	label.setBackground(b);
						    	Label l1=new Label();
						    	Label l2=new Label();
						    	Label l3=new Label();
						    	Label l4=new Label();
						    	Label l5=new Label();
						    	Label l6=new Label();
						    	Label l7=new Label();
						    	String name="Name: "+current.getName();
						    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
						    	String actions="Actions: "+""+((current.getActionsAvailable()));
						    	String health="Health Pts: "+""+(current.getCurrentHp());
						    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
						    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
						    	String type="Type: "+Type(current);
						    	l1.setText(name);
						    	l1.setStyle("-fx-font-size: 30px;");
						    	l2.setText(attackdmg);
						    	l2.setStyle("-fx-font-size: 30px;");
						    	l3.setText(actions);
						    	l3.setStyle("-fx-font-size: 30px;");
						    	l4.setText(health);
						    	l4.setStyle("-fx-font-size: 30px;");
						    	l5.setText(vaccines);
						    	l5.setStyle("-fx-font-size: 30px;");
						    	l6.setText(supplies);
						    	l6.setStyle("-fx-font-size: 30px;");
						    	l7.setText(type);
						    	l7.setStyle("-fx-font-size: 30px;");
						    	label.setSpacing(10);
						    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
						    	lay.getChildren().add(label);
						    	int index = 0;
						        
						        
						    	for(int i=1;i<Game.heroes.size();i++){
						    		index++;
						    		
						    		String num=""+index;
							    	Button hero2=new Button(num);
							    	hero2.setStyle("-fx-font-size: 15px;");
							    	hero2.setPrefWidth(index);
							    	hero2.setOnAction(new EventHandler<ActionEvent>(){
                                     
										
										public void handle(ActionEvent event) {
											current=Game.heroes.get((int) hero2.getPrefWidth());
											
                                           
											
										}
							    		
							    	});
							    	label.getChildren().addAll(hero2);
							}
						} catch (NoAvailableResourcesException e) {
						     popup(e.getMessage());
							
						} catch (InvalidTargetException e) {
							 popup(e.getMessage());
						}
	                	   
							
	                	  
	                  }
			    		
			    	});
			    	
			    	Button EndTurn=new Button("End Turn");
			    	EndTurn.setStyle("-fx-font-size: 20px;");
			    	EndTurn.setOnAction(new EventHandler<ActionEvent>(){

			    		
		                  public void handle(ActionEvent event) {
		                	  
			                 try {
								Game.endTurn();
								startgame2();
								if(Game.checkWin()==true){
									String wow="YOU WON YAYY!";
									popup2(wow);
									
								}
								else if(Game.checkGameOver()==true){
									String sad="LOSER YOU LOST STUPID!";
									popup2(sad);
								}
								VBox label=new VBox();
						    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
						    	Background b=new Background(bg);
						    	label.setBackground(b);
						    	Label l1=new Label();
						    	Label l2=new Label();
						    	Label l3=new Label();
						    	Label l4=new Label();
						    	Label l5=new Label();
						    	Label l6=new Label();
						    	Label l7=new Label();
						    	String name="Name: "+current.getName();
						    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
						    	String actions="Actions: "+""+((current.getActionsAvailable()));
						    	String health="Health Pts: "+""+(current.getCurrentHp());
						    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
						    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
						    	String type="Type: "+Type(current);
						    	l1.setText(name);
						    	l1.setStyle("-fx-font-size: 30px;");
						    	l2.setText(attackdmg);
						    	l2.setStyle("-fx-font-size: 30px;");
						    	l3.setText(actions);
						    	l3.setStyle("-fx-font-size: 30px;");
						    	l4.setText(health);
						    	l4.setStyle("-fx-font-size: 30px;");
						    	l5.setText(vaccines);
						    	l5.setStyle("-fx-font-size: 30px;");
						    	l6.setText(supplies);
						    	l6.setStyle("-fx-font-size: 30px;");
						    	l7.setText(type);
						    	l7.setStyle("-fx-font-size: 30px;");
						    	label.setSpacing(10);
						    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
						    	lay.getChildren().add(label);
						    	int index = 0;
						        
						        
						    	for(int i=1;i<Game.heroes.size();i++){
						    		index++;
						    		
						    		String num=""+index;
							    	Button hero2=new Button(num);
							    	hero2.setStyle("-fx-font-size: 15px;");
							    	hero2.setPrefWidth(index);
							    	hero2.setOnAction(new EventHandler<ActionEvent>(){
                                     
										
										public void handle(ActionEvent event) {
											current=Game.heroes.get((int) hero2.getPrefWidth());
											
                                           
											
										}
							    		
							    	});
							    	label.getChildren().addAll(hero2);
							}
							} catch (NotEnoughActionsException e) {
								
								popup(e.getMessage());
							} catch (InvalidTargetException e) {
								popup(e.getMessage());
							}
		                	  
		                  
		                  }
				    	});
			    	
			    	HBox gameplay=new HBox(moveright,moveleft,moveup,movedown,hit,cure,specialaction,EndTurn);
			    	
			    	gameplay.setSpacing(20);
			    	gameplay.setLayoutY(780);
			    	gameplay.setStyle("-fx-font-size: 8px;");
			    	lay.getChildren().addAll(gameplay);
			    
			    	
			    	
			    	             
			    	
			    	s = new Scene(lay,1500,900);
			    	s.setFill(Color.BLACK);
					primaryStage.setScene(s);
                    primaryStage.show();  
			    });
                   
                
                Button h2=new Button("Ellie William,MEDIC,MaxHp:110,AttackDmg:6,maxActions:15");
                root1.setSpacing(10); 
				root1.getChildren().addAll(h2);
		
				 h2.setOnAction(e->{
				    	current=Game.availableHeroes.get(1);
	                    Game.startGame(current);
	                    grid=new GridPane();
				    	startgame2();
	                    Group lay=new Group();
				    	VBox label=new VBox();
				    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
				    	Background b=new Background(bg);
				    	label.setBackground(b);
				    	Label l1=new Label();
				    	Label l2=new Label();
				    	Label l3=new Label();
				    	Label l4=new Label();
				    	Label l5=new Label();
				    	Label l6=new Label();
				    	Label l7=new Label();
				    	String name="Name: "+current.getName();
				    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
				    	String actions="Actions: "+""+((current.getActionsAvailable()));
				    	String health="Health Pts: "+""+(current.getCurrentHp());
				    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
				    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
				    	String type="Type: "+Type(current);
				    	l1.setText(name);
				    	l1.setStyle("-fx-font-size: 30px;");
				    	l2.setText(attackdmg);
				    	l2.setStyle("-fx-font-size: 30px;");
				    	l3.setText(actions);
				    	l3.setStyle("-fx-font-size: 30px;");
				    	l4.setText(health);
				    	l4.setStyle("-fx-font-size: 30px;");
				    	l5.setText(vaccines);
				    	l5.setStyle("-fx-font-size: 30px;");
				    	l6.setText(supplies);
				    	l6.setStyle("-fx-font-size: 30px;");
				    	l7.setText(type);
				    	l7.setStyle("-fx-font-size: 30px;");
				    	label.setSpacing(10);
				    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7);
				    	lay.getChildren().addAll(grid,label);
				    	
				    	grid.setLayoutX(300);
				    	grid.setLayoutY(10);
				    	
				    	h2.setStyle("-fx-font-size: 20px;");
				    	Button my=new Button("Ellie");
				    	
				    	my.setOnAction(new EventHandler<ActionEvent>(){

							public void handle(ActionEvent event) {
								current=Game.heroes.get(0);
								
							}
				    		
				    	});
				    	
				    	
				    				    	
				    	
				    	Button moveright=new Button("RIGHT");
				    	moveright.setStyle("-fx-font-size: 20px;");
				    	
				    	moveright.setOnAction(new EventHandler<ActionEvent>(){
				            
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().y+1>14) {
										popup("You Cannot Move Out of The Map");
									}
									if(Game.map[current.getLocation().x][current.getLocation().y+1] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									}
									 
									current.move(Direction.RIGHT);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
								
									
									
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().addAll(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
									
							    	
									
								} catch (MovementException e) {
	                                 
				                    popup(e.getMessage());   
									
								} catch (NotEnoughActionsException e) {
									
									 popup(e.getMessage());
								}
								
							}

							
				    		
				    	});

				    		
				    	
				    	

				    	Button moveleft=new Button("LEFT");
				    	moveleft.setStyle("-fx-font-size: 20px;");
	                    moveleft.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								if(current.getLocation().y-1<0) {
									popup("You Cannot Move Out Of The Map");
								}
								if(Game.map[current.getLocation().x][current.getLocation().y-1] instanceof TrapCell){
									popup("You Have Entered A Trap Cell!");
								} 
								try {

									current.move(Direction.LEFT);
									 
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									
									 popup(e.getMessage());
								}
								
							}

							
				    		
				    	});
				    	
				    	Button moveup=new Button("UP");
				    	moveup.setStyle("-fx-font-size: 20px;");
		                moveup.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().x+1>14) {
										popup("You Cannot Move Out Of The Map");
									}
									if(Game.map[current.getLocation().x+1][current.getLocation().y] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									} 

									current.move(Direction.UP);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
								
								}
								
							}

							
				    		
				    	});
				    	
				    	Button movedown=new Button("DOWN");
				    	movedown.setStyle("-fx-font-size: 20px;");
	                    movedown.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().x-1<0) {
										popup("You Cannot Move Out Of The Map");
									}
									if(Game.map[current.getLocation().x-1][current.getLocation().y] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									} 
									current.move(Direction.DOWN);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									 
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
									
								}
								
							}

							
				    		
				    	});
				    	
				    	Button hit=new Button("ATTACK");
				    	hit.setStyle("-fx-font-size: 20px;"); 
	                    hit.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {

									try {
										
										
										current.attack();
										startgame2();
										if(Game.checkWin()==true){
											String wow="YOU WON YAYY!";
											popup2(wow);
											
										}
										else if(Game.checkGameOver()==true){
											String sad="LOSER YOU LOST STUPID!";
											popup2(sad);
										}
										VBox label=new VBox();
								    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
								    	Background b=new Background(bg);
								    	label.setBackground(b);
								    	Label l1=new Label();
								    	Label l2=new Label();
								    	Label l3=new Label();
								    	Label l4=new Label();
								    	Label l5=new Label();
								    	Label l6=new Label();
								    	Label l7=new Label();
								    	String name="Name: "+current.getName();
								    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
								    	String actions="Actions: "+""+((current.getActionsAvailable()));
								    	String health="Health Pts: "+""+(current.getCurrentHp());
								    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
								    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
								    	String type="Type: "+Type(current);
								    	l1.setText(name);
								    	l1.setStyle("-fx-font-size: 30px;");
								    	l2.setText(attackdmg);
								    	l2.setStyle("-fx-font-size: 30px;");
								    	l3.setText(actions);
								    	l3.setStyle("-fx-font-size: 30px;");
								    	l4.setText(health);
								    	l4.setStyle("-fx-font-size: 30px;");
								    	l5.setText(vaccines);
								    	l5.setStyle("-fx-font-size: 30px;");
								    	l6.setText(supplies);
								    	l6.setStyle("-fx-font-size: 30px;");
								    	l7.setText(type);
								    	l7.setStyle("-fx-font-size: 30px;");
								    	label.setSpacing(10);
								    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
								    	lay.getChildren().add(label);
	                                    int index = 0;
								        
								        
								    	for(int i=1;i<Game.heroes.size();i++){
								    		index++;
								    		
								    		String num=""+index;
									    	Button hero2=new Button(num);
									    	hero2.setStyle("-fx-font-size: 15px;");
									    	hero2.setPrefWidth(index);
									    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                         
												
												public void handle(ActionEvent event) {
													current=Game.heroes.get((int) hero2.getPrefWidth());
													
	                                               
													
												}
									    		
									    	});
									    	label.getChildren().addAll(hero2);
									}
									} catch (InvalidTargetException e) {
										 popup(e.getMessage());	
									}
									
									
							    	
									
								
								
							}
								catch(NotEnoughActionsException e){
									 popup(e.getMessage());
								}

							}
				    		
				    	});
				    	
				    	Button cure=new Button("CURE");
				    	cure.setStyle("-fx-font-size: 20px;");
	                    cure.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {

									try {
										
										current.cure();
										
										startgame2();
										if(Game.checkWin()==true){
											String wow="YOU WON YAYY!";
											popup2(wow);
											
										}
										else if(Game.checkGameOver()==true){
											String sad="LOSER YOU LOST STUPID!";
											popup2(sad);
										}
										VBox label=new VBox();
								    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
								    	Background b=new Background(bg);
								    	label.setBackground(b);
								    	Label l1=new Label();
								    	Label l2=new Label();
								    	Label l3=new Label();
								    	Label l4=new Label();
								    	Label l5=new Label();
								    	Label l6=new Label();
								    	Label l7=new Label();
								    	String name="Name: "+current.getName();
								    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
								    	String actions="Actions: "+""+((current.getActionsAvailable()));
								    	String health="Health Pts: "+""+(current.getCurrentHp());
								    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
								    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
								    	String type="Type: "+Type(current);
								    	l1.setText(name);
								    	l1.setStyle("-fx-font-size: 30px;");
								    	l2.setText(attackdmg);
								    	l2.setStyle("-fx-font-size: 30px;");
								    	l3.setText(actions);
								    	l3.setStyle("-fx-font-size: 30px;");
								    	l4.setText(health);
								    	l4.setStyle("-fx-font-size: 30px;");
								    	l5.setText(vaccines);
								    	l5.setStyle("-fx-font-size: 30px;");
								    	l6.setText(supplies);
								    	l6.setStyle("-fx-font-size: 30px;");
								    	l7.setText(type);
								    	l7.setStyle("-fx-font-size: 30px;");
								    	label.setSpacing(10);
								    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
								        int index = 0;
								        
								        
								    	for(int i=1;i<Game.heroes.size();i++){
								    		index++;
								    		
								    		String num=""+index;
									    	Button hero2=new Button(num);
									    	hero2.setStyle("-fx-font-size: 15px;");
									    	hero2.setPrefWidth(index);
									    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                         
												
												public void handle(ActionEvent event) {
													current=Game.heroes.get((int) hero2.getPrefWidth());
													
	                                               
													
												}
									    		
									    	});
									    	label.getChildren().addAll(hero2);
									    	
									    	
									    	}
								    	lay.getChildren().addAll(label);
								    	
								    	
								    
								    	
								    	
								    	
									} catch (NoAvailableResourcesException e) {
										 popup(e.getMessage());
									} catch (InvalidTargetException e) {
										 popup(e.getMessage());
									}
								
									
							    	
									
								
									
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
									
								}
								
							}

							
				    		
				    	});
				    	
				    	
				    	
				    	
				    	Button specialaction=new Button("Special Action");
				    	specialaction.setStyle("-fx-font-size: 20px;");
				    	specialaction.setOnAction(new EventHandler<ActionEvent>(){

		
		                  public void handle(ActionEvent event) {
		                	   try {
								current.useSpecial();
								 startgame2();
								 if(Game.checkWin()==true){
									 String wow="YOU WON YAYY!";
										popup2(wow);
									 
									}
								 else if(Game.checkGameOver()==true){
									 String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
								 VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
							} catch (NoAvailableResourcesException e) {
							     popup(e.getMessage());
								
							} catch (InvalidTargetException e) {
								 popup(e.getMessage());
							}
		                	   
								
		                	  
		                  }
				    		
				    	});
				    	
				    	Button EndTurn=new Button("End Turn");
				    	EndTurn.setStyle("-fx-font-size: 20px;");
				    	EndTurn.setOnAction(new EventHandler<ActionEvent>(){

				    		
			                  public void handle(ActionEvent event) {
			                	  
				                 try {
									Game.endTurn();
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
								} catch (NotEnoughActionsException e) {
									
									popup(e.getMessage());
								} catch (InvalidTargetException e) {
									popup(e.getMessage());
								}
			                	  
			                  
			                  }
					    	});
				    	
				    	HBox gameplay=new HBox(moveright,moveleft,moveup,movedown,hit,cure,specialaction,EndTurn);
				    	
				    	gameplay.setSpacing(20);
				    	gameplay.setLayoutY(780);
				    	gameplay.setStyle("-fx-font-size: 8px;");
				    	lay.getChildren().addAll(gameplay);
				    
				    	
				    	
				    	             
				    	
				    	s = new Scene(lay,1500,900);
				    	s.setFill(Color.BLACK);
						primaryStage.setScene(s);
	                    primaryStage.show();  
				    });
	                   
				
				
				Button h3=new Button("Tess,EXPLORER,MaxHp:80,AttackDmg:6,maxActions:20");
				root1.setSpacing(15); 
				root1.getChildren().add(h3);
				  h3.setOnAction(e->{
				    	current=Game.availableHeroes.get(2);
	                    Game.startGame(current);
	                    grid=new GridPane();
				    	startgame2();
	                    Group lay=new Group();
				    	VBox label=new VBox();
				    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
				    	Background b=new Background(bg);
				    	label.setBackground(b);
				    	Label l1=new Label();
				    	Label l2=new Label();
				    	Label l3=new Label();
				    	Label l4=new Label();
				    	Label l5=new Label();
				    	Label l6=new Label();
				    	Label l7=new Label();
				    	String name="Name: "+current.getName();
				    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
				    	String actions="Actions: "+""+((current.getActionsAvailable()));
				    	String health="Health Pts: "+""+(current.getCurrentHp());
				    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
				    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
				    	String type="Type: "+Type(current);
				    	l1.setText(name);
				    	l1.setStyle("-fx-font-size: 30px;");
				    	l2.setText(attackdmg);
				    	l2.setStyle("-fx-font-size: 30px;");
				    	l3.setText(actions);
				    	l3.setStyle("-fx-font-size: 30px;");
				    	l4.setText(health);
				    	l4.setStyle("-fx-font-size: 30px;");
				    	l5.setText(vaccines);
				    	l5.setStyle("-fx-font-size: 30px;");
				    	l6.setText(supplies);
				    	l6.setStyle("-fx-font-size: 30px;");
				    	l7.setText(type);
				    	l7.setStyle("-fx-font-size: 30px;");
				    	label.setSpacing(10);
				    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7);
				    	lay.getChildren().addAll(grid,label);
				    	
				    	grid.setLayoutX(300);
				    	grid.setLayoutY(10);
				    	
				    	h3.setStyle("-fx-font-size: 20px;");
				    	Button my=new Button("Tess");
				    	
				    	my.setOnAction(new EventHandler<ActionEvent>(){

							public void handle(ActionEvent event) {
								current=Game.heroes.get(0);
								
							}
				    		
				    	});
				    	
				    	
				    	
				    	Button moveright=new Button("RIGHT");
				    	moveright.setStyle("-fx-font-size: 20px;");
				    	
				    	moveright.setOnAction(new EventHandler<ActionEvent>(){
				            
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().y+1>14) {
										popup("You Cannot Move Out of The Map");
									}
									if(Game.map[current.getLocation().x][current.getLocation().y+1] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									}
									 
									current.move(Direction.RIGHT);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
								
									
									
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().addAll(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
									
							    	
									
								} catch (MovementException e) {
	                                 
				                    popup(e.getMessage());   
									
								} catch (NotEnoughActionsException e) {
									
									 popup(e.getMessage());
								}
								
							}

							
				    		
				    	});

				    		
				    	
				    	

				    	Button moveleft=new Button("LEFT");
				    	moveleft.setStyle("-fx-font-size: 20px;");
	                    moveleft.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								if(current.getLocation().y-1<0) {
									popup("You Cannot Move Out Of The Map");
								}
								if(Game.map[current.getLocation().x][current.getLocation().y-1] instanceof TrapCell){
									popup("You Have Entered A Trap Cell!");
								} 
								try {

									current.move(Direction.LEFT);
									 
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									
									 popup(e.getMessage());
								}
								
							}

							
				    		
				    	});
				    	
				    	Button moveup=new Button("UP");
				    	moveup.setStyle("-fx-font-size: 20px;");
		                moveup.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().x+1>14) {
										popup("You Cannot Move Out Of The Map");
									}
									if(Game.map[current.getLocation().x+1][current.getLocation().y] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									} 

									current.move(Direction.UP);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
								
								}
								
							}

							
				    		
				    	});
				    	
				    	Button movedown=new Button("DOWN");
				    	movedown.setStyle("-fx-font-size: 20px;");
	                    movedown.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().x-1<0) {
										popup("You Cannot Move Out Of The Map");
									}
									if(Game.map[current.getLocation().x-1][current.getLocation().y] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									} 
									current.move(Direction.DOWN);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									 
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
									
								}
								
							}

							
				    		
				    	});
				    	
				    	Button hit=new Button("ATTACK");
				    	hit.setStyle("-fx-font-size: 20px;"); 
	                    hit.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {

									try {
										
										
										current.attack();
										startgame2();
										if(Game.checkWin()==true){
											String wow="YOU WON YAYY!";
											popup2(wow);
											
										}
										else if(Game.checkGameOver()==true){
											String sad="LOSER YOU LOST STUPID!";
											popup2(sad);
										}
										VBox label=new VBox();
								    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
								    	Background b=new Background(bg);
								    	label.setBackground(b);
								    	Label l1=new Label();
								    	Label l2=new Label();
								    	Label l3=new Label();
								    	Label l4=new Label();
								    	Label l5=new Label();
								    	Label l6=new Label();
								    	Label l7=new Label();
								    	String name="Name: "+current.getName();
								    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
								    	String actions="Actions: "+""+((current.getActionsAvailable()));
								    	String health="Health Pts: "+""+(current.getCurrentHp());
								    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
								    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
								    	String type="Type: "+Type(current);
								    	l1.setText(name);
								    	l1.setStyle("-fx-font-size: 30px;");
								    	l2.setText(attackdmg);
								    	l2.setStyle("-fx-font-size: 30px;");
								    	l3.setText(actions);
								    	l3.setStyle("-fx-font-size: 30px;");
								    	l4.setText(health);
								    	l4.setStyle("-fx-font-size: 30px;");
								    	l5.setText(vaccines);
								    	l5.setStyle("-fx-font-size: 30px;");
								    	l6.setText(supplies);
								    	l6.setStyle("-fx-font-size: 30px;");
								    	l7.setText(type);
								    	l7.setStyle("-fx-font-size: 30px;");
								    	label.setSpacing(10);
								    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
								    	lay.getChildren().add(label);
	                                    int index = 0;
								        
								        
								    	for(int i=1;i<Game.heroes.size();i++){
								    		index++;
								    		
								    		String num=""+index;
									    	Button hero2=new Button(num);
									    	hero2.setStyle("-fx-font-size: 15px;");
									    	hero2.setPrefWidth(index);
									    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                         
												
												public void handle(ActionEvent event) {
													current=Game.heroes.get((int) hero2.getPrefWidth());
													
	                                               
													
												}
									    		
									    	});
									    	label.getChildren().addAll(hero2);
									}
									} catch (InvalidTargetException e) {
										 popup(e.getMessage());	
									}
									
									
							    	
									
								
								
							}
								catch(NotEnoughActionsException e){
									 popup(e.getMessage());
								}

							}
				    		
				    	});
				    	
				    	Button cure=new Button("CURE");
				    	cure.setStyle("-fx-font-size: 20px;");
	                    cure.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {

									try {
										
										current.cure();
										
										startgame2();
										if(Game.checkWin()==true){
											String wow="YOU WON YAYY!";
											popup2(wow);
											
										}
										else if(Game.checkGameOver()==true){
											String sad="LOSER YOU LOST STUPID!";
											popup2(sad);
										}
										VBox label=new VBox();
								    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
								    	Background b=new Background(bg);
								    	label.setBackground(b);
								    	Label l1=new Label();
								    	Label l2=new Label();
								    	Label l3=new Label();
								    	Label l4=new Label();
								    	Label l5=new Label();
								    	Label l6=new Label();
								    	Label l7=new Label();
								    	String name="Name: "+current.getName();
								    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
								    	String actions="Actions: "+""+((current.getActionsAvailable()));
								    	String health="Health Pts: "+""+(current.getCurrentHp());
								    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
								    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
								    	String type="Type: "+Type(current);
								    	l1.setText(name);
								    	l1.setStyle("-fx-font-size: 30px;");
								    	l2.setText(attackdmg);
								    	l2.setStyle("-fx-font-size: 30px;");
								    	l3.setText(actions);
								    	l3.setStyle("-fx-font-size: 30px;");
								    	l4.setText(health);
								    	l4.setStyle("-fx-font-size: 30px;");
								    	l5.setText(vaccines);
								    	l5.setStyle("-fx-font-size: 30px;");
								    	l6.setText(supplies);
								    	l6.setStyle("-fx-font-size: 30px;");
								    	l7.setText(type);
								    	l7.setStyle("-fx-font-size: 30px;");
								    	label.setSpacing(10);
								    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
								        int index = 0;
								        
								        
								    	for(int i=1;i<Game.heroes.size();i++){
								    		index++;
								    		
								    		String num=""+index;
									    	Button hero2=new Button(num);
									    	hero2.setStyle("-fx-font-size: 15px;");
									    	hero2.setPrefWidth(index);
									    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                         
												
												public void handle(ActionEvent event) {
													current=Game.heroes.get((int) hero2.getPrefWidth());
													
	                                               
													
												}
									    		
									    	});
									    	label.getChildren().addAll(hero2);
									    	
									    	
									    	}
								    	lay.getChildren().addAll(label);
								    	
								    	
								    
								    	
								    	
								    	
									} catch (NoAvailableResourcesException e) {
										 popup(e.getMessage());
									} catch (InvalidTargetException e) {
										 popup(e.getMessage());
									}
								
									
							    	
									
								
									
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
									
								}
								
							}

							
				    		
				    	});
				    	
				    	
				    	
				    	
				    	Button specialaction=new Button("Special Action");
				    	specialaction.setStyle("-fx-font-size: 20px;");
				    	specialaction.setOnAction(new EventHandler<ActionEvent>(){

		
		                  public void handle(ActionEvent event) {
		                	   try {
								current.useSpecial();
								 startgame2();
								 if(Game.checkWin()==true){
									 String wow="YOU WON YAYY!";
										popup2(wow);
									 
									}
								 else if(Game.checkGameOver()==true){
									 String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
								 VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
							} catch (NoAvailableResourcesException e) {
							     popup(e.getMessage());
								
							} catch (InvalidTargetException e) {
								 popup(e.getMessage());
							}
		                	   
								
		                	  
		                  }
				    		
				    	});
				    	
				    	Button EndTurn=new Button("End Turn");
				    	EndTurn.setStyle("-fx-font-size: 20px;");
				    	EndTurn.setOnAction(new EventHandler<ActionEvent>(){

				    		
			                  public void handle(ActionEvent event) {
			                	  
				                 try {
									Game.endTurn();
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
								} catch (NotEnoughActionsException e) {
									
									popup(e.getMessage());
								} catch (InvalidTargetException e) {
									popup(e.getMessage());
								}
			                	  
			                  
			                  }
					    	});
				    	
				    	HBox gameplay=new HBox(moveright,moveleft,moveup,movedown,hit,cure,specialaction,EndTurn);
				    	
				    	gameplay.setSpacing(20);
				    	gameplay.setLayoutY(780);
				    	gameplay.setStyle("-fx-font-size: 8px;");
				    	lay.getChildren().addAll(gameplay);
				    
				    	
				    	
				    	             
				    	
				    	s = new Scene(lay,1500,900);
				    	s.setFill(Color.BLACK);
						primaryStage.setScene(s);
	                    primaryStage.show();  
				    });
				
				Button h4=new Button("Riley,EXPLORER,MaxHp:90,AttackDmg:5,maxActions:25");
				root1.setSpacing(20); 
				root1.getChildren().add(h4);
				  h4.setOnAction(e->{
				    	current=Game.availableHeroes.get(3);
	                    Game.startGame(current);
	                    grid=new GridPane();
				    	startgame2();
	                    Group lay=new Group();
				    	VBox label=new VBox();
				    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
				    	Background b=new Background(bg);
				    	label.setBackground(b);
				    	Label l1=new Label();
				    	Label l2=new Label();
				    	Label l3=new Label();
				    	Label l4=new Label();
				    	Label l5=new Label();
				    	Label l6=new Label();
				    	Label l7=new Label();
				    	String name="Name: "+current.getName();
				    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
				    	String actions="Actions: "+""+((current.getActionsAvailable()));
				    	String health="Health Pts: "+""+(current.getCurrentHp());
				    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
				    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
				    	String type="Type: "+Type(current);
				    	l1.setText(name);
				    	l1.setStyle("-fx-font-size: 30px;");
				    	l2.setText(attackdmg);
				    	l2.setStyle("-fx-font-size: 30px;");
				    	l3.setText(actions);
				    	l3.setStyle("-fx-font-size: 30px;");
				    	l4.setText(health);
				    	l4.setStyle("-fx-font-size: 30px;");
				    	l5.setText(vaccines);
				    	l5.setStyle("-fx-font-size: 30px;");
				    	l6.setText(supplies);
				    	l6.setStyle("-fx-font-size: 30px;");
				    	l7.setText(type);
				    	l7.setStyle("-fx-font-size: 30px;");
				    	label.setSpacing(10);
				    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7);
				    	lay.getChildren().addAll(grid,label);
				    	
				    	grid.setLayoutX(300);
				    	grid.setLayoutY(10);
				    	
				    	h4.setStyle("-fx-font-size: 20px;");
				    	Button my=new Button("Riley");
				    	
				    	my.setOnAction(new EventHandler<ActionEvent>(){

							public void handle(ActionEvent event) {
								current=Game.heroes.get(0);
								
							}
				    		
				    	});
				    	
				    	
				    	
				    	Button moveright=new Button("RIGHT");
				    	moveright.setStyle("-fx-font-size: 20px;");
				    	
				    	moveright.setOnAction(new EventHandler<ActionEvent>(){
				            
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().y+1>14) {
										popup("You Cannot Move Out of The Map");
									}
									if(Game.map[current.getLocation().x][current.getLocation().y+1] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									}
									 
									current.move(Direction.RIGHT);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
								
									
									
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().addAll(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
									
							    	
									
								} catch (MovementException e) {
	                                 
				                    popup(e.getMessage());   
									
								} catch (NotEnoughActionsException e) {
									
									 popup(e.getMessage());
								}
								
							}

							
				    		
				    	});

				    		
				    	
				    	

				    	Button moveleft=new Button("LEFT");
				    	moveleft.setStyle("-fx-font-size: 20px;");
	                    moveleft.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								if(current.getLocation().y-1<0) {
									popup("You Cannot Move Out Of The Map");
								}
								if(Game.map[current.getLocation().x][current.getLocation().y-1] instanceof TrapCell){
									popup("You Have Entered A Trap Cell!");
								} 
								try {

									current.move(Direction.LEFT);
									 
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									
									 popup(e.getMessage());
								}
								
							}

							
				    		
				    	});
				    	
				    	Button moveup=new Button("UP");
				    	moveup.setStyle("-fx-font-size: 20px;");
		                moveup.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().x+1>14) {
										popup("You Cannot Move Out Of The Map");
									}
									if(Game.map[current.getLocation().x+1][current.getLocation().y] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									} 

									current.move(Direction.UP);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
								
								}
								
							}

							
				    		
				    	});
				    	
				    	Button movedown=new Button("DOWN");
				    	movedown.setStyle("-fx-font-size: 20px;");
	                    movedown.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().x-1<0) {
										popup("You Cannot Move Out Of The Map");
									}
									if(Game.map[current.getLocation().x-1][current.getLocation().y] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									} 
									current.move(Direction.DOWN);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									 
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
									
								}
								
							}

							
				    		
				    	});
				    	
				    	Button hit=new Button("ATTACK");
				    	hit.setStyle("-fx-font-size: 20px;"); 
	                    hit.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {

									try {
										
										
										current.attack();
										startgame2();
										if(Game.checkWin()==true){
											String wow="YOU WON YAYY!";
											popup2(wow);
											
										}
										else if(Game.checkGameOver()==true){
											String sad="LOSER YOU LOST STUPID!";
											popup2(sad);
										}
										VBox label=new VBox();
								    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
								    	Background b=new Background(bg);
								    	label.setBackground(b);
								    	Label l1=new Label();
								    	Label l2=new Label();
								    	Label l3=new Label();
								    	Label l4=new Label();
								    	Label l5=new Label();
								    	Label l6=new Label();
								    	Label l7=new Label();
								    	String name="Name: "+current.getName();
								    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
								    	String actions="Actions: "+""+((current.getActionsAvailable()));
								    	String health="Health Pts: "+""+(current.getCurrentHp());
								    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
								    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
								    	String type="Type: "+Type(current);
								    	l1.setText(name);
								    	l1.setStyle("-fx-font-size: 30px;");
								    	l2.setText(attackdmg);
								    	l2.setStyle("-fx-font-size: 30px;");
								    	l3.setText(actions);
								    	l3.setStyle("-fx-font-size: 30px;");
								    	l4.setText(health);
								    	l4.setStyle("-fx-font-size: 30px;");
								    	l5.setText(vaccines);
								    	l5.setStyle("-fx-font-size: 30px;");
								    	l6.setText(supplies);
								    	l6.setStyle("-fx-font-size: 30px;");
								    	l7.setText(type);
								    	l7.setStyle("-fx-font-size: 30px;");
								    	label.setSpacing(10);
								    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
								    	lay.getChildren().add(label);
	                                    int index = 0;
								        
								        
								    	for(int i=1;i<Game.heroes.size();i++){
								    		index++;
								    		
								    		String num=""+index;
									    	Button hero2=new Button(num);
									    	hero2.setStyle("-fx-font-size: 15px;");
									    	hero2.setPrefWidth(index);
									    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                         
												
												public void handle(ActionEvent event) {
													current=Game.heroes.get((int) hero2.getPrefWidth());
													
	                                               
													
												}
									    		
									    	});
									    	label.getChildren().addAll(hero2);
									}
									} catch (InvalidTargetException e) {
										 popup(e.getMessage());	
									}
									
									
							    	
									
								
								
							}
								catch(NotEnoughActionsException e){
									 popup(e.getMessage());
								}

							}
				    		
				    	});
				    	
				    	Button cure=new Button("CURE");
				    	cure.setStyle("-fx-font-size: 20px;");
	                    cure.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {

									try {
										
										current.cure();
										
										startgame2();
										if(Game.checkWin()==true){
											String wow="YOU WON YAYY!";
											popup2(wow);
											
										}
										else if(Game.checkGameOver()==true){
											String sad="LOSER YOU LOST STUPID!";
											popup2(sad);
										}
										VBox label=new VBox();
								    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
								    	Background b=new Background(bg);
								    	label.setBackground(b);
								    	Label l1=new Label();
								    	Label l2=new Label();
								    	Label l3=new Label();
								    	Label l4=new Label();
								    	Label l5=new Label();
								    	Label l6=new Label();
								    	Label l7=new Label();
								    	String name="Name: "+current.getName();
								    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
								    	String actions="Actions: "+""+((current.getActionsAvailable()));
								    	String health="Health Pts: "+""+(current.getCurrentHp());
								    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
								    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
								    	String type="Type: "+Type(current);
								    	l1.setText(name);
								    	l1.setStyle("-fx-font-size: 30px;");
								    	l2.setText(attackdmg);
								    	l2.setStyle("-fx-font-size: 30px;");
								    	l3.setText(actions);
								    	l3.setStyle("-fx-font-size: 30px;");
								    	l4.setText(health);
								    	l4.setStyle("-fx-font-size: 30px;");
								    	l5.setText(vaccines);
								    	l5.setStyle("-fx-font-size: 30px;");
								    	l6.setText(supplies);
								    	l6.setStyle("-fx-font-size: 30px;");
								    	l7.setText(type);
								    	l7.setStyle("-fx-font-size: 30px;");
								    	label.setSpacing(10);
								    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
								        int index = 0;
								        
								        
								    	for(int i=1;i<Game.heroes.size();i++){
								    		index++;
								    		
								    		String num=""+index;
									    	Button hero2=new Button(num);
									    	hero2.setStyle("-fx-font-size: 15px;");
									    	hero2.setPrefWidth(index);
									    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                         
												
												public void handle(ActionEvent event) {
													current=Game.heroes.get((int) hero2.getPrefWidth());
													
	                                               
													
												}
									    		
									    	});
									    	label.getChildren().addAll(hero2);
									    	
									    	
									    	}
								    	lay.getChildren().addAll(label);
								    	
								    	
								    
								    	
								    	
								    	
									} catch (NoAvailableResourcesException e) {
										 popup(e.getMessage());
									} catch (InvalidTargetException e) {
										 popup(e.getMessage());
									}
								
									
							    	
									
								
									
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
									
								}
								
							}

							
				    		
				    	});
				    	
				    	
				    	
				    	
				    	Button specialaction=new Button("Special Action");
				    	specialaction.setStyle("-fx-font-size: 20px;");
				    	specialaction.setOnAction(new EventHandler<ActionEvent>(){

		
		                  public void handle(ActionEvent event) {
		                	   try {
								current.useSpecial();
								 startgame2();
								 if(Game.checkWin()==true){
									 String wow="YOU WON YAYY!";
										popup2(wow);
									 
									}
								 else if(Game.checkGameOver()==true){
									 String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
								 VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
							} catch (NoAvailableResourcesException e) {
							     popup(e.getMessage());
								
							} catch (InvalidTargetException e) {
								 popup(e.getMessage());
							}
		                	   
								
		                	  
		                  }
				    		
				    	});
				    	
				    	Button EndTurn=new Button("End Turn");
				    	EndTurn.setStyle("-fx-font-size: 20px;");
				    	EndTurn.setOnAction(new EventHandler<ActionEvent>(){

				    		
			                  public void handle(ActionEvent event) {
			                	  
				                 try {
									Game.endTurn();
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
								} catch (NotEnoughActionsException e) {
									
									popup(e.getMessage());
								} catch (InvalidTargetException e) {
									popup(e.getMessage());
								}
			                	  
			                  
			                  }
					    	});
				    	
				    	HBox gameplay=new HBox(moveright,moveleft,moveup,movedown,hit,cure,specialaction,EndTurn);
				    	
				    	gameplay.setSpacing(20);
				    	gameplay.setLayoutY(780);
				    	gameplay.setStyle("-fx-font-size: 8px;");
				    	lay.getChildren().addAll(gameplay);
				    
				    	
				    	
				    	             
				    	
				    	s = new Scene(lay,1500,900);
				    	s.setFill(Color.BLACK);
						primaryStage.setScene(s);
	                    primaryStage.show();  
				    });
				
				Button h5=new Button("Tommy Miller,EXPLORER,MaxHp:95,AttackDmg:5,maxActions:25");
				root1.setSpacing(25); 
				root1.getChildren().add(h5);
				  h5.setOnAction(e->{
				    	current=Game.availableHeroes.get(4);
	                    Game.startGame(current);
	                    grid=new GridPane();
				    	startgame2();
	                    Group lay=new Group();
				    	VBox label=new VBox();
				    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
				    	Background b=new Background(bg);
				    	label.setBackground(b);
				    	Label l1=new Label();
				    	Label l2=new Label();
				    	Label l3=new Label();
				    	Label l4=new Label();
				    	Label l5=new Label();
				    	Label l6=new Label();
				    	Label l7=new Label();
				    	String name="Name: "+current.getName();
				    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
				    	String actions="Actions: "+""+((current.getActionsAvailable()));
				    	String health="Health Pts: "+""+(current.getCurrentHp());
				    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
				    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
				    	String type="Type: "+Type(current);
				    	l1.setText(name);
				    	l1.setStyle("-fx-font-size: 30px;");
				    	l2.setText(attackdmg);
				    	l2.setStyle("-fx-font-size: 30px;");
				    	l3.setText(actions);
				    	l3.setStyle("-fx-font-size: 30px;");
				    	l4.setText(health);
				    	l4.setStyle("-fx-font-size: 30px;");
				    	l5.setText(vaccines);
				    	l5.setStyle("-fx-font-size: 30px;");
				    	l6.setText(supplies);
				    	l6.setStyle("-fx-font-size: 30px;");
				    	l7.setText(type);
				    	l7.setStyle("-fx-font-size: 30px;");
				    	label.setSpacing(10);
				    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7);
				    	lay.getChildren().addAll(grid,label);
				    	
				    	grid.setLayoutX(300);
				    	grid.setLayoutY(10);
				    	
				    	h5.setStyle("-fx-font-size: 20px;");
				    	Button my=new Button("Tommy");
				    	
				    	my.setOnAction(new EventHandler<ActionEvent>(){

							public void handle(ActionEvent event) {
								current=Game.heroes.get(0);
								
							}
				    		
				    	});
				    	
				    	
				    	
				    	Button moveright=new Button("RIGHT");
				    	moveright.setStyle("-fx-font-size: 20px;");
				    	
				    	moveright.setOnAction(new EventHandler<ActionEvent>(){
				            
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().y+1>14) {
										popup("You Cannot Move Out of The Map");
									}
									if(Game.map[current.getLocation().x][current.getLocation().y+1] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									}
									 
									current.move(Direction.RIGHT);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
								
									
									
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().addAll(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
									
							    	
									
								} catch (MovementException e) {
	                                 
				                    popup(e.getMessage());   
									
								} catch (NotEnoughActionsException e) {
									
									 popup(e.getMessage());
								}
								
							}

							
				    		
				    	});

				    		
				    	
				    	

				    	Button moveleft=new Button("LEFT");
				    	moveleft.setStyle("-fx-font-size: 20px;");
	                    moveleft.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								if(current.getLocation().y-1<0) {
									popup("You Cannot Move Out Of The Map");
								}
								if(Game.map[current.getLocation().x][current.getLocation().y-1] instanceof TrapCell){
									popup("You Have Entered A Trap Cell!");
								} 
								try {

									current.move(Direction.LEFT);
									 
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									
									 popup(e.getMessage());
								}
								
							}

							
				    		
				    	});
				    	
				    	Button moveup=new Button("UP");
				    	moveup.setStyle("-fx-font-size: 20px;");
		                moveup.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().x+1>14) {
										popup("You Cannot Move Out Of The Map");
									}
									if(Game.map[current.getLocation().x+1][current.getLocation().y] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									} 

									current.move(Direction.UP);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
								
								}
								
							}

							
				    		
				    	});
				    	
				    	Button movedown=new Button("DOWN");
				    	movedown.setStyle("-fx-font-size: 20px;");
	                    movedown.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().x-1<0) {
										popup("You Cannot Move Out Of The Map");
									}
									if(Game.map[current.getLocation().x-1][current.getLocation().y] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									} 
									current.move(Direction.DOWN);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									 
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
									
								}
								
							}

							
				    		
				    	});
				    	
				    	Button hit=new Button("ATTACK");
				    	hit.setStyle("-fx-font-size: 20px;"); 
	                    hit.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {

									try {
										
										
										current.attack();
										startgame2();
										if(Game.checkWin()==true){
											String wow="YOU WON YAYY!";
											popup2(wow);
											
										}
										else if(Game.checkGameOver()==true){
											String sad="LOSER YOU LOST STUPID!";
											popup2(sad);
										}
										VBox label=new VBox();
								    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
								    	Background b=new Background(bg);
								    	label.setBackground(b);
								    	Label l1=new Label();
								    	Label l2=new Label();
								    	Label l3=new Label();
								    	Label l4=new Label();
								    	Label l5=new Label();
								    	Label l6=new Label();
								    	Label l7=new Label();
								    	String name="Name: "+current.getName();
								    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
								    	String actions="Actions: "+""+((current.getActionsAvailable()));
								    	String health="Health Pts: "+""+(current.getCurrentHp());
								    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
								    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
								    	String type="Type: "+Type(current);
								    	l1.setText(name);
								    	l1.setStyle("-fx-font-size: 30px;");
								    	l2.setText(attackdmg);
								    	l2.setStyle("-fx-font-size: 30px;");
								    	l3.setText(actions);
								    	l3.setStyle("-fx-font-size: 30px;");
								    	l4.setText(health);
								    	l4.setStyle("-fx-font-size: 30px;");
								    	l5.setText(vaccines);
								    	l5.setStyle("-fx-font-size: 30px;");
								    	l6.setText(supplies);
								    	l6.setStyle("-fx-font-size: 30px;");
								    	l7.setText(type);
								    	l7.setStyle("-fx-font-size: 30px;");
								    	label.setSpacing(10);
								    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
								    	lay.getChildren().add(label);
	                                    int index = 0;
								        
								        
								    	for(int i=1;i<Game.heroes.size();i++){
								    		index++;
								    		
								    		String num=""+index;
									    	Button hero2=new Button(num);
									    	hero2.setStyle("-fx-font-size: 15px;");
									    	hero2.setPrefWidth(index);
									    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                         
												
												public void handle(ActionEvent event) {
													current=Game.heroes.get((int) hero2.getPrefWidth());
													
	                                               
													
												}
									    		
									    	});
									    	label.getChildren().addAll(hero2);
									}
									} catch (InvalidTargetException e) {
										 popup(e.getMessage());	
									}
									
									
							    	
									
								
								
							}
								catch(NotEnoughActionsException e){
									 popup(e.getMessage());
								}

							}
				    		
				    	});
				    	
				    	Button cure=new Button("CURE");
				    	cure.setStyle("-fx-font-size: 20px;");
	                    cure.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {

									try {
										
										current.cure();
										
										startgame2();
										if(Game.checkWin()==true){
											String wow="YOU WON YAYY!";
											popup2(wow);
											
										}
										else if(Game.checkGameOver()==true){
											String sad="LOSER YOU LOST STUPID!";
											popup2(sad);
										}
										VBox label=new VBox();
								    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
								    	Background b=new Background(bg);
								    	label.setBackground(b);
								    	Label l1=new Label();
								    	Label l2=new Label();
								    	Label l3=new Label();
								    	Label l4=new Label();
								    	Label l5=new Label();
								    	Label l6=new Label();
								    	Label l7=new Label();
								    	String name="Name: "+current.getName();
								    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
								    	String actions="Actions: "+""+((current.getActionsAvailable()));
								    	String health="Health Pts: "+""+(current.getCurrentHp());
								    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
								    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
								    	String type="Type: "+Type(current);
								    	l1.setText(name);
								    	l1.setStyle("-fx-font-size: 30px;");
								    	l2.setText(attackdmg);
								    	l2.setStyle("-fx-font-size: 30px;");
								    	l3.setText(actions);
								    	l3.setStyle("-fx-font-size: 30px;");
								    	l4.setText(health);
								    	l4.setStyle("-fx-font-size: 30px;");
								    	l5.setText(vaccines);
								    	l5.setStyle("-fx-font-size: 30px;");
								    	l6.setText(supplies);
								    	l6.setStyle("-fx-font-size: 30px;");
								    	l7.setText(type);
								    	l7.setStyle("-fx-font-size: 30px;");
								    	label.setSpacing(10);
								    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
								        int index = 0;
								        
								        
								    	for(int i=1;i<Game.heroes.size();i++){
								    		index++;
								    		
								    		String num=""+index;
									    	Button hero2=new Button(num);
									    	hero2.setStyle("-fx-font-size: 15px;");
									    	hero2.setPrefWidth(index);
									    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                         
												
												public void handle(ActionEvent event) {
													current=Game.heroes.get((int) hero2.getPrefWidth());
													
	                                               
													
												}
									    		
									    	});
									    	label.getChildren().addAll(hero2);
									    	
									    	
									    	}
								    	lay.getChildren().addAll(label);
								    	
								    	
								    
								    	
								    	
								    	
									} catch (NoAvailableResourcesException e) {
										 popup(e.getMessage());
									} catch (InvalidTargetException e) {
										 popup(e.getMessage());
									}
								
									
							    	
									
								
									
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
									
								}
								
							}

							
				    		
				    	});
				    	
				    	
				    	
				    	
				    	Button specialaction=new Button("Special Action");
				    	specialaction.setStyle("-fx-font-size: 20px;");
				    	specialaction.setOnAction(new EventHandler<ActionEvent>(){

		
		                  public void handle(ActionEvent event) {
		                	   try {
								current.useSpecial();
								 startgame2();
								 if(Game.checkWin()==true){
									 String wow="YOU WON YAYY!";
										popup2(wow);
									 
									}
								 else if(Game.checkGameOver()==true){
									 String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
								 VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
							} catch (NoAvailableResourcesException e) {
							     popup(e.getMessage());
								
							} catch (InvalidTargetException e) {
								 popup(e.getMessage());
							}
		                	   
								
		                	  
		                  }
				    		
				    	});
				    	
				    	Button EndTurn=new Button("End Turn");
				    	EndTurn.setStyle("-fx-font-size: 20px;");
				    	EndTurn.setOnAction(new EventHandler<ActionEvent>(){

				    		
			                  public void handle(ActionEvent event) {
			                	  
				                 try {
									Game.endTurn();
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
								} catch (NotEnoughActionsException e) {
									
									popup(e.getMessage());
								} catch (InvalidTargetException e) {
									popup(e.getMessage());
								}
			                	  
			                  
			                  }
					    	});
				    	
				    	HBox gameplay=new HBox(moveright,moveleft,moveup,movedown,hit,cure,specialaction,EndTurn);
				    	
				    	gameplay.setSpacing(20);
				    	gameplay.setLayoutY(780);
				    	gameplay.setStyle("-fx-font-size: 8px;");
				    	lay.getChildren().addAll(gameplay);
				    
				    	
				    	
				    	             
				    	
				    	s = new Scene(lay,1500,900);
				    	s.setFill(Color.BLACK);
						primaryStage.setScene(s);
	                    primaryStage.show();  
				    });
				
				
				Button h6=new Button("Bill,MEDIC,MaxHp:100,AttackDmg:7,maxActions:10");
				root1.setSpacing(30); 
				root1.getChildren().add(h6);
				  h6.setOnAction(e->{
				    	current=Game.availableHeroes.get(5);
	                    Game.startGame(current);
	                    grid=new GridPane();
				    	startgame2();
	                    Group lay=new Group();
				    	VBox label=new VBox();
				    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
				    	Background b=new Background(bg);
				    	label.setBackground(b);
				    	Label l1=new Label();
				    	Label l2=new Label();
				    	Label l3=new Label();
				    	Label l4=new Label();
				    	Label l5=new Label();
				    	Label l6=new Label();
				    	Label l7=new Label();
				    	String name="Name: "+current.getName();
				    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
				    	String actions="Actions: "+""+((current.getActionsAvailable()));
				    	String health="Health Pts: "+""+(current.getCurrentHp());
				    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
				    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
				    	String type="Type: "+Type(current);
				    	l1.setText(name);
				    	l1.setStyle("-fx-font-size: 30px;");
				    	l2.setText(attackdmg);
				    	l2.setStyle("-fx-font-size: 30px;");
				    	l3.setText(actions);
				    	l3.setStyle("-fx-font-size: 30px;");
				    	l4.setText(health);
				    	l4.setStyle("-fx-font-size: 30px;");
				    	l5.setText(vaccines);
				    	l5.setStyle("-fx-font-size: 30px;");
				    	l6.setText(supplies);
				    	l6.setStyle("-fx-font-size: 30px;");
				    	l7.setText(type);
				    	l7.setStyle("-fx-font-size: 30px;");
				    	label.setSpacing(10);
				    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7);
				    	lay.getChildren().addAll(grid,label);
				    	
				    	grid.setLayoutX(300);
				    	grid.setLayoutY(10);
				    	
				    	h6.setStyle("-fx-font-size: 20px;");
				    	Button my=new Button("Bill");
				    	
				    	my.setOnAction(new EventHandler<ActionEvent>(){

							public void handle(ActionEvent event) {
								current=Game.heroes.get(0);
								
							}
				    		
				    	});
				    	
				    	
				    	
				    	Button moveright=new Button("RIGHT");
				    	moveright.setStyle("-fx-font-size: 20px;");
				    	
				    	moveright.setOnAction(new EventHandler<ActionEvent>(){
				            
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().y+1>14) {
										popup("You Cannot Move Out of The Map");
									}
									if(Game.map[current.getLocation().x][current.getLocation().y+1] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									}
									 
									current.move(Direction.RIGHT);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
								
									
									
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().addAll(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
									
							    	
									
								} catch (MovementException e) {
	                                 
				                    popup(e.getMessage());   
									
								} catch (NotEnoughActionsException e) {
									
									 popup(e.getMessage());
								}
								
							}

							
				    		
				    	});

				    		
				    	
				    	

				    	Button moveleft=new Button("LEFT");
				    	moveleft.setStyle("-fx-font-size: 20px;");
	                    moveleft.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								if(current.getLocation().y-1<0) {
									popup("You Cannot Move Out Of The Map");
								}
								if(Game.map[current.getLocation().x][current.getLocation().y-1] instanceof TrapCell){
									popup("You Have Entered A Trap Cell!");
								} 
								try {

									current.move(Direction.LEFT);
									 
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									
									 popup(e.getMessage());
								}
								
							}

							
				    		
				    	});
				    	
				    	Button moveup=new Button("UP");
				    	moveup.setStyle("-fx-font-size: 20px;");
		                moveup.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().x+1>14) {
										popup("You Cannot Move Out Of The Map");
									}
									if(Game.map[current.getLocation().x+1][current.getLocation().y] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									} 

									current.move(Direction.UP);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
								
								}
								
							}

							
				    		
				    	});
				    	
				    	Button movedown=new Button("DOWN");
				    	movedown.setStyle("-fx-font-size: 20px;");
	                    movedown.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().x-1<0) {
										popup("You Cannot Move Out Of The Map");
									}
									if(Game.map[current.getLocation().x-1][current.getLocation().y] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									} 
									current.move(Direction.DOWN);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									 
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
									
								}
								
							}

							
				    		
				    	});
				    	
				    	Button hit=new Button("ATTACK");
				    	hit.setStyle("-fx-font-size: 20px;"); 
	                    hit.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {

									try {
										
										
										current.attack();
										startgame2();
										if(Game.checkWin()==true){
											String wow="YOU WON YAYY!";
											popup2(wow);
											
										}
										else if(Game.checkGameOver()==true){
											String sad="LOSER YOU LOST STUPID!";
											popup2(sad);
										}
										VBox label=new VBox();
								    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
								    	Background b=new Background(bg);
								    	label.setBackground(b);
								    	Label l1=new Label();
								    	Label l2=new Label();
								    	Label l3=new Label();
								    	Label l4=new Label();
								    	Label l5=new Label();
								    	Label l6=new Label();
								    	Label l7=new Label();
								    	String name="Name: "+current.getName();
								    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
								    	String actions="Actions: "+""+((current.getActionsAvailable()));
								    	String health="Health Pts: "+""+(current.getCurrentHp());
								    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
								    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
								    	String type="Type: "+Type(current);
								    	l1.setText(name);
								    	l1.setStyle("-fx-font-size: 30px;");
								    	l2.setText(attackdmg);
								    	l2.setStyle("-fx-font-size: 30px;");
								    	l3.setText(actions);
								    	l3.setStyle("-fx-font-size: 30px;");
								    	l4.setText(health);
								    	l4.setStyle("-fx-font-size: 30px;");
								    	l5.setText(vaccines);
								    	l5.setStyle("-fx-font-size: 30px;");
								    	l6.setText(supplies);
								    	l6.setStyle("-fx-font-size: 30px;");
								    	l7.setText(type);
								    	l7.setStyle("-fx-font-size: 30px;");
								    	label.setSpacing(10);
								    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
								    	lay.getChildren().add(label);
	                                    int index = 0;
								        
								        
								    	for(int i=1;i<Game.heroes.size();i++){
								    		index++;
								    		
								    		String num=""+index;
									    	Button hero2=new Button(num);
									    	hero2.setStyle("-fx-font-size: 15px;");
									    	hero2.setPrefWidth(index);
									    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                         
												
												public void handle(ActionEvent event) {
													current=Game.heroes.get((int) hero2.getPrefWidth());
													
	                                               
													
												}
									    		
									    	});
									    	label.getChildren().addAll(hero2);
									}
									} catch (InvalidTargetException e) {
										 popup(e.getMessage());	
									}
									
									
							    	
									
								
								
							}
								catch(NotEnoughActionsException e){
									 popup(e.getMessage());
								}

							}
				    		
				    	});
				    	
				    	Button cure=new Button("CURE");
				    	cure.setStyle("-fx-font-size: 20px;");
	                    cure.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {

									try {
										
										current.cure();
										
										startgame2();
										if(Game.checkWin()==true){
											String wow="YOU WON YAYY!";
											popup2(wow);
											
										}
										else if(Game.checkGameOver()==true){
											String sad="LOSER YOU LOST STUPID!";
											popup2(sad);
										}
										VBox label=new VBox();
								    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
								    	Background b=new Background(bg);
								    	label.setBackground(b);
								    	Label l1=new Label();
								    	Label l2=new Label();
								    	Label l3=new Label();
								    	Label l4=new Label();
								    	Label l5=new Label();
								    	Label l6=new Label();
								    	Label l7=new Label();
								    	String name="Name: "+current.getName();
								    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
								    	String actions="Actions: "+""+((current.getActionsAvailable()));
								    	String health="Health Pts: "+""+(current.getCurrentHp());
								    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
								    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
								    	String type="Type: "+Type(current);
								    	l1.setText(name);
								    	l1.setStyle("-fx-font-size: 30px;");
								    	l2.setText(attackdmg);
								    	l2.setStyle("-fx-font-size: 30px;");
								    	l3.setText(actions);
								    	l3.setStyle("-fx-font-size: 30px;");
								    	l4.setText(health);
								    	l4.setStyle("-fx-font-size: 30px;");
								    	l5.setText(vaccines);
								    	l5.setStyle("-fx-font-size: 30px;");
								    	l6.setText(supplies);
								    	l6.setStyle("-fx-font-size: 30px;");
								    	l7.setText(type);
								    	l7.setStyle("-fx-font-size: 30px;");
								    	label.setSpacing(10);
								    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
								        int index = 0;
								        
								        
								    	for(int i=1;i<Game.heroes.size();i++){
								    		index++;
								    		
								    		String num=""+index;
									    	Button hero2=new Button(num);
									    	hero2.setStyle("-fx-font-size: 15px;");
									    	hero2.setPrefWidth(index);
									    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                         
												
												public void handle(ActionEvent event) {
													current=Game.heroes.get((int) hero2.getPrefWidth());
													
	                                               
													
												}
									    		
									    	});
									    	label.getChildren().addAll(hero2);
									    	
									    	
									    	}
								    	lay.getChildren().addAll(label);
								    	
								    	
								    
								    	
								    	
								    	
									} catch (NoAvailableResourcesException e) {
										 popup(e.getMessage());
									} catch (InvalidTargetException e) {
										 popup(e.getMessage());
									}
								
									
							    	
									
								
									
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
									
								}
								
							}

							
				    		
				    	});
				    	
				    	
				    	
				    	
				    	Button specialaction=new Button("Special Action");
				    	specialaction.setStyle("-fx-font-size: 20px;");
				    	specialaction.setOnAction(new EventHandler<ActionEvent>(){

		
		                  public void handle(ActionEvent event) {
		                	   try {
								current.useSpecial();
								 startgame2();
								 if(Game.checkWin()==true){
									 String wow="YOU WON YAYY!";
										popup2(wow);
									 
									}
								 else if(Game.checkGameOver()==true){
									 String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
								 VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
							} catch (NoAvailableResourcesException e) {
							     popup(e.getMessage());
								
							} catch (InvalidTargetException e) {
								 popup(e.getMessage());
							}
		                	   
								
		                	  
		                  }
				    		
				    	});
				    	
				    	Button EndTurn=new Button("End Turn");
				    	EndTurn.setStyle("-fx-font-size: 20px;");
				    	EndTurn.setOnAction(new EventHandler<ActionEvent>(){

				    		
			                  public void handle(ActionEvent event) {
			                	  
				                 try {
									Game.endTurn();
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
								} catch (NotEnoughActionsException e) {
									
									popup(e.getMessage());
								} catch (InvalidTargetException e) {
									popup(e.getMessage());
								}
			                	  
			                  
			                  }
					    	});
				    	
				    	HBox gameplay=new HBox(moveright,moveleft,moveup,movedown,hit,cure,specialaction,EndTurn);
				    	
				    	gameplay.setSpacing(20);
				    	gameplay.setLayoutY(780);
				    	gameplay.setStyle("-fx-font-size: 8px;");
				    	lay.getChildren().addAll(gameplay);
				    
				    	
				    	
				    	             
				    	
				    	s = new Scene(lay,1500,900);
				    	s.setFill(Color.BLACK);
						primaryStage.setScene(s);
	                    primaryStage.show();  
				    });
				
				
				
				Button h7=new Button("David,FIGHTER, MaxHp:150 , AttackDmg:4 ,maxActions:35");
				root1.setSpacing(35); 
				root1.getChildren().add(h7);
				  h7.setOnAction(e->{
				    	current=Game.availableHeroes.get(6);
	                    Game.startGame(current);
	                    grid=new GridPane();
				    	startgame2();
	                    Group lay=new Group();
				    	VBox label=new VBox();
				    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
				    	Background b=new Background(bg);
				    	label.setBackground(b);
				    	Label l1=new Label();
				    	Label l2=new Label();
				    	Label l3=new Label();
				    	Label l4=new Label();
				    	Label l5=new Label();
				    	Label l6=new Label();
				    	Label l7=new Label();
				    	String name="Name: "+current.getName();
				    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
				    	String actions="Actions: "+""+((current.getActionsAvailable()));
				    	String health="Health Pts: "+""+(current.getCurrentHp());
				    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
				    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
				    	String type="Type: "+Type(current);
				    	l1.setText(name);
				    	l1.setStyle("-fx-font-size: 30px;");
				    	l2.setText(attackdmg);
				    	l2.setStyle("-fx-font-size: 30px;");
				    	l3.setText(actions);
				    	l3.setStyle("-fx-font-size: 30px;");
				    	l4.setText(health);
				    	l4.setStyle("-fx-font-size: 30px;");
				    	l5.setText(vaccines);
				    	l5.setStyle("-fx-font-size: 30px;");
				    	l6.setText(supplies);
				    	l6.setStyle("-fx-font-size: 30px;");
				    	l7.setText(type);
				    	l7.setStyle("-fx-font-size: 30px;");
				    	label.setSpacing(10);
				    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7);
				    	lay.getChildren().addAll(grid,label);
				    	
				    	grid.setLayoutX(300);
				    	grid.setLayoutY(10);
				    	
				    	h7.setStyle("-fx-font-size: 20px;");
				    	Button my=new Button("David");
				    	
				    	my.setOnAction(new EventHandler<ActionEvent>(){

							public void handle(ActionEvent event) {
								current=Game.heroes.get(0);
								
							}
				    		
				    	});
				    	
				    	
				    	
				    	Button moveright=new Button("RIGHT");
				    	moveright.setStyle("-fx-font-size: 20px;");
				    	
				    	moveright.setOnAction(new EventHandler<ActionEvent>(){
				            
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().y+1>14) {
										popup("You Cannot Move Out of The Map");
									}
									if(Game.map[current.getLocation().x][current.getLocation().y+1] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									}
									 
									current.move(Direction.RIGHT);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
								
									
									
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().addAll(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
									
							    	
									
								} catch (MovementException e) {
	                                 
				                    popup(e.getMessage());   
									
								} catch (NotEnoughActionsException e) {
									
									 popup(e.getMessage());
								}
								
							}

							
				    		
				    	});

				    		
				    	
				    	

				    	Button moveleft=new Button("LEFT");
				    	moveleft.setStyle("-fx-font-size: 20px;");
	                    moveleft.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								if(current.getLocation().y-1<0) {
									popup("You Cannot Move Out Of The Map");
								}
								if(Game.map[current.getLocation().x][current.getLocation().y-1] instanceof TrapCell){
									popup("You Have Entered A Trap Cell!");
								} 
								try {

									current.move(Direction.LEFT);
									 
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									
									 popup(e.getMessage());
								}
								
							}

							
				    		
				    	});
				    	
				    	Button moveup=new Button("UP");
				    	moveup.setStyle("-fx-font-size: 20px;");
		                moveup.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().x+1>14) {
										popup("You Cannot Move Out Of The Map");
									}
									if(Game.map[current.getLocation().x+1][current.getLocation().y] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									} 

									current.move(Direction.UP);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
								
								}
								
							}

							
				    		
				    	});
				    	
				    	Button movedown=new Button("DOWN");
				    	movedown.setStyle("-fx-font-size: 20px;");
	                    movedown.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().x-1<0) {
										popup("You Cannot Move Out Of The Map");
									}
									if(Game.map[current.getLocation().x-1][current.getLocation().y] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									} 
									current.move(Direction.DOWN);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									 
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
									
								}
								
							}

							
				    		
				    	});
				    	
				    	Button hit=new Button("ATTACK");
				    	hit.setStyle("-fx-font-size: 20px;"); 
	                    hit.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {

									try {
										
										
										current.attack();
										startgame2();
										if(Game.checkWin()==true){
											String wow="YOU WON YAYY!";
											popup2(wow);
											
										}
										else if(Game.checkGameOver()==true){
											String sad="LOSER YOU LOST STUPID!";
											popup2(sad);
										}
										VBox label=new VBox();
								    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
								    	Background b=new Background(bg);
								    	label.setBackground(b);
								    	Label l1=new Label();
								    	Label l2=new Label();
								    	Label l3=new Label();
								    	Label l4=new Label();
								    	Label l5=new Label();
								    	Label l6=new Label();
								    	Label l7=new Label();
								    	String name="Name: "+current.getName();
								    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
								    	String actions="Actions: "+""+((current.getActionsAvailable()));
								    	String health="Health Pts: "+""+(current.getCurrentHp());
								    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
								    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
								    	String type="Type: "+Type(current);
								    	l1.setText(name);
								    	l1.setStyle("-fx-font-size: 30px;");
								    	l2.setText(attackdmg);
								    	l2.setStyle("-fx-font-size: 30px;");
								    	l3.setText(actions);
								    	l3.setStyle("-fx-font-size: 30px;");
								    	l4.setText(health);
								    	l4.setStyle("-fx-font-size: 30px;");
								    	l5.setText(vaccines);
								    	l5.setStyle("-fx-font-size: 30px;");
								    	l6.setText(supplies);
								    	l6.setStyle("-fx-font-size: 30px;");
								    	l7.setText(type);
								    	l7.setStyle("-fx-font-size: 30px;");
								    	label.setSpacing(10);
								    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
								    	lay.getChildren().add(label);
	                                    int index = 0;
								        
								        
								    	for(int i=1;i<Game.heroes.size();i++){
								    		index++;
								    		
								    		String num=""+index;
									    	Button hero2=new Button(num);
									    	hero2.setStyle("-fx-font-size: 15px;");
									    	hero2.setPrefWidth(index);
									    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                         
												
												public void handle(ActionEvent event) {
													current=Game.heroes.get((int) hero2.getPrefWidth());
													
	                                               
													
												}
									    		
									    	});
									    	label.getChildren().addAll(hero2);
									}
									} catch (InvalidTargetException e) {
										 popup(e.getMessage());	
									}
									
									
							    	
									
								
								
							}
								catch(NotEnoughActionsException e){
									 popup(e.getMessage());
								}

							}
				    		
				    	});
				    	
				    	Button cure=new Button("CURE");
				    	cure.setStyle("-fx-font-size: 20px;");
	                    cure.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {

									try {
										
										current.cure();
										
										startgame2();
										if(Game.checkWin()==true){
											String wow="YOU WON YAYY!";
											popup2(wow);
											
										}
										else if(Game.checkGameOver()==true){
											String sad="LOSER YOU LOST STUPID!";
											popup2(sad);
										}
										VBox label=new VBox();
								    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
								    	Background b=new Background(bg);
								    	label.setBackground(b);
								    	Label l1=new Label();
								    	Label l2=new Label();
								    	Label l3=new Label();
								    	Label l4=new Label();
								    	Label l5=new Label();
								    	Label l6=new Label();
								    	Label l7=new Label();
								    	String name="Name: "+current.getName();
								    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
								    	String actions="Actions: "+""+((current.getActionsAvailable()));
								    	String health="Health Pts: "+""+(current.getCurrentHp());
								    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
								    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
								    	String type="Type: "+Type(current);
								    	l1.setText(name);
								    	l1.setStyle("-fx-font-size: 30px;");
								    	l2.setText(attackdmg);
								    	l2.setStyle("-fx-font-size: 30px;");
								    	l3.setText(actions);
								    	l3.setStyle("-fx-font-size: 30px;");
								    	l4.setText(health);
								    	l4.setStyle("-fx-font-size: 30px;");
								    	l5.setText(vaccines);
								    	l5.setStyle("-fx-font-size: 30px;");
								    	l6.setText(supplies);
								    	l6.setStyle("-fx-font-size: 30px;");
								    	l7.setText(type);
								    	l7.setStyle("-fx-font-size: 30px;");
								    	label.setSpacing(10);
								    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
								        int index = 0;
								        
								        
								    	for(int i=1;i<Game.heroes.size();i++){
								    		index++;
								    		
								    		String num=""+index;
									    	Button hero2=new Button(num);
									    	hero2.setStyle("-fx-font-size: 15px;");
									    	hero2.setPrefWidth(index);
									    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                         
												
												public void handle(ActionEvent event) {
													current=Game.heroes.get((int) hero2.getPrefWidth());
													
	                                               
													
												}
									    		
									    	});
									    	label.getChildren().addAll(hero2);
									    	
									    	
									    	}
								    	lay.getChildren().addAll(label);
								    	
								    	
								    
								    	
								    	
								    	
									} catch (NoAvailableResourcesException e) {
										 popup(e.getMessage());
									} catch (InvalidTargetException e) {
										 popup(e.getMessage());
									}
								
									
							    	
									
								
									
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
									
								}
								
							}

							
				    		
				    	});
				    	
				    	
				    	
				    	
				    	Button specialaction=new Button("Special Action");
				    	specialaction.setStyle("-fx-font-size: 20px;");
				    	specialaction.setOnAction(new EventHandler<ActionEvent>(){

		
		                  public void handle(ActionEvent event) {
		                	   try {
								current.useSpecial();
								 startgame2();
								 if(Game.checkWin()==true){
									 String wow="YOU WON YAYY!";
										popup2(wow);
									 
									}
								 else if(Game.checkGameOver()==true){
									 String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
								 VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
							} catch (NoAvailableResourcesException e) {
							     popup(e.getMessage());
								
							} catch (InvalidTargetException e) {
								 popup(e.getMessage());
							}
		                	   
								
		                	  
		                  }
				    		
				    	});
				    	
				    	Button EndTurn=new Button("End Turn");
				    	EndTurn.setStyle("-fx-font-size: 20px;");
				    	EndTurn.setOnAction(new EventHandler<ActionEvent>(){

				    		
			                  public void handle(ActionEvent event) {
			                	  
				                 try {
									Game.endTurn();
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
								} catch (NotEnoughActionsException e) {
									
									popup(e.getMessage());
								} catch (InvalidTargetException e) {
									popup(e.getMessage());
								}
			                	  
			                  
			                  }
					    	});
				    	
				    	HBox gameplay=new HBox(moveright,moveleft,moveup,movedown,hit,cure,specialaction,EndTurn);
				    	
				    	gameplay.setSpacing(20);
				    	gameplay.setLayoutY(780);
				    	gameplay.setStyle("-fx-font-size: 8px;");
				    	lay.getChildren().addAll(gameplay);
				    
				    	
				    	
				    	             
				    	
				    	s = new Scene(lay,1500,900);
				    	s.setFill(Color.BLACK);
						primaryStage.setScene(s);
	                    primaryStage.show();  
				    });
				
				Button h8=new Button("Henry Burell,MEDIC,MaxHp:105,AttackDmg:6,maxActions:15");
				root1.setSpacing(40); 
				root1.getChildren().add(h8);
				  h8.setOnAction(e->{
				    	current=Game.availableHeroes.get(7);
	                    Game.startGame(current);
	                    grid=new GridPane();
				    	startgame2();
	                    Group lay=new Group();
				    	VBox label=new VBox();
				    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
				    	Background b=new Background(bg);
				    	label.setBackground(b);
				    	Label l1=new Label();
				    	Label l2=new Label();
				    	Label l3=new Label();
				    	Label l4=new Label();
				    	Label l5=new Label();
				    	Label l6=new Label();
				    	Label l7=new Label();
				    	String name="Name: "+current.getName();
				    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
				    	String actions="Actions: "+""+((current.getActionsAvailable()));
				    	String health="Health Pts: "+""+(current.getCurrentHp());
				    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
				    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
				    	String type="Type: "+Type(current);
				    	l1.setText(name);
				    	l1.setStyle("-fx-font-size: 30px;");
				    	l2.setText(attackdmg);
				    	l2.setStyle("-fx-font-size: 30px;");
				    	l3.setText(actions);
				    	l3.setStyle("-fx-font-size: 30px;");
				    	l4.setText(health);
				    	l4.setStyle("-fx-font-size: 30px;");
				    	l5.setText(vaccines);
				    	l5.setStyle("-fx-font-size: 30px;");
				    	l6.setText(supplies);
				    	l6.setStyle("-fx-font-size: 30px;");
				    	l7.setText(type);
				    	l7.setStyle("-fx-font-size: 30px;");
				    	label.setSpacing(10);
				    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7);
				    	lay.getChildren().addAll(grid,label);
				    	
				    	grid.setLayoutX(300);
				    	grid.setLayoutY(10);
				    	
				    	h8.setStyle("-fx-font-size: 20px;");
				    	Button my=new Button("Henry");
				    	
				    	my.setOnAction(new EventHandler<ActionEvent>(){

							public void handle(ActionEvent event) {
								current=Game.heroes.get(0);
								
							}
				    		
				    	});
				    	
				    	
				    	
				    	Button moveright=new Button("RIGHT");
				    	moveright.setStyle("-fx-font-size: 20px;");
				    	
				    	moveright.setOnAction(new EventHandler<ActionEvent>(){
				            
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().y+1>14) {
										popup("You Cannot Move Out of The Map");
									}
									if(Game.map[current.getLocation().x][current.getLocation().y+1] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									}
									 
									current.move(Direction.RIGHT);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
								
									
									
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().addAll(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
									
							    	
									
								} catch (MovementException e) {
	                                 
				                    popup(e.getMessage());   
									
								} catch (NotEnoughActionsException e) {
									
									 popup(e.getMessage());
								}
								
							}

							
				    		
				    	});

				    		
				    	
				    	

				    	Button moveleft=new Button("LEFT");
				    	moveleft.setStyle("-fx-font-size: 20px;");
	                    moveleft.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								if(current.getLocation().y-1<0) {
									popup("You Cannot Move Out Of The Map");
								}
								if(Game.map[current.getLocation().x][current.getLocation().y-1] instanceof TrapCell){
									popup("You Have Entered A Trap Cell!");
								} 
								try {

									current.move(Direction.LEFT);
									 
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									
									 popup(e.getMessage());
								}
								
							}

							
				    		
				    	});
				    	
				    	Button moveup=new Button("UP");
				    	moveup.setStyle("-fx-font-size: 20px;");
		                moveup.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().x+1>14) {
										popup("You Cannot Move Out Of The Map");
									}
									if(Game.map[current.getLocation().x+1][current.getLocation().y] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									} 

									current.move(Direction.UP);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
								
								}
								
							}

							
				    		
				    	});
				    	
				    	Button movedown=new Button("DOWN");
				    	movedown.setStyle("-fx-font-size: 20px;");
	                    movedown.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {
									if(current.getLocation().x-1<0) {
										popup("You Cannot Move Out Of The Map");
									}
									if(Game.map[current.getLocation().x-1][current.getLocation().y] instanceof TrapCell){
										popup("You Have Entered A Trap Cell!");
									} 
									current.move(Direction.DOWN);
									
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
									
							    	
									
								} catch (MovementException e) {
									 
									 popup(e.getMessage());
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
									
								}
								
							}

							
				    		
				    	});
				    	
				    	Button hit=new Button("ATTACK");
				    	hit.setStyle("-fx-font-size: 20px;"); 
	                    hit.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {

									try {
										
										
										current.attack();
										startgame2();
										if(Game.checkWin()==true){
											String wow="YOU WON YAYY!";
											popup2(wow);
											
										}
										else if(Game.checkGameOver()==true){
											String sad="LOSER YOU LOST STUPID!";
											popup2(sad);
										}
										VBox label=new VBox();
								    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
								    	Background b=new Background(bg);
								    	label.setBackground(b);
								    	Label l1=new Label();
								    	Label l2=new Label();
								    	Label l3=new Label();
								    	Label l4=new Label();
								    	Label l5=new Label();
								    	Label l6=new Label();
								    	Label l7=new Label();
								    	String name="Name: "+current.getName();
								    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
								    	String actions="Actions: "+""+((current.getActionsAvailable()));
								    	String health="Health Pts: "+""+(current.getCurrentHp());
								    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
								    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
								    	String type="Type: "+Type(current);
								    	l1.setText(name);
								    	l1.setStyle("-fx-font-size: 30px;");
								    	l2.setText(attackdmg);
								    	l2.setStyle("-fx-font-size: 30px;");
								    	l3.setText(actions);
								    	l3.setStyle("-fx-font-size: 30px;");
								    	l4.setText(health);
								    	l4.setStyle("-fx-font-size: 30px;");
								    	l5.setText(vaccines);
								    	l5.setStyle("-fx-font-size: 30px;");
								    	l6.setText(supplies);
								    	l6.setStyle("-fx-font-size: 30px;");
								    	l7.setText(type);
								    	l7.setStyle("-fx-font-size: 30px;");
								    	label.setSpacing(10);
								    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
								    	lay.getChildren().add(label);
	                                    int index = 0;
								        
								        
								    	for(int i=1;i<Game.heroes.size();i++){
								    		index++;
								    		
								    		String num=""+index;
									    	Button hero2=new Button(num);
									    	hero2.setStyle("-fx-font-size: 15px;");
									    	hero2.setPrefWidth(index);
									    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                         
												
												public void handle(ActionEvent event) {
													current=Game.heroes.get((int) hero2.getPrefWidth());
													
	                                               
													
												}
									    		
									    	});
									    	label.getChildren().addAll(hero2);
									}
									} catch (InvalidTargetException e) {
										 popup(e.getMessage());	
									}
									
									
							    	
									
								
								
							}
								catch(NotEnoughActionsException e){
									 popup(e.getMessage());
								}

							}
				    		
				    	});
				    	
				    	Button cure=new Button("CURE");
				    	cure.setStyle("-fx-font-size: 20px;");
	                    cure.setOnAction(new EventHandler<ActionEvent>(){
				        
							
							public void handle(ActionEvent event) {
								try {

									try {
										
										current.cure();
										
										startgame2();
										if(Game.checkWin()==true){
											String wow="YOU WON YAYY!";
											popup2(wow);
											
										}
										else if(Game.checkGameOver()==true){
											String sad="LOSER YOU LOST STUPID!";
											popup2(sad);
										}
										VBox label=new VBox();
								    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
								    	Background b=new Background(bg);
								    	label.setBackground(b);
								    	Label l1=new Label();
								    	Label l2=new Label();
								    	Label l3=new Label();
								    	Label l4=new Label();
								    	Label l5=new Label();
								    	Label l6=new Label();
								    	Label l7=new Label();
								    	String name="Name: "+current.getName();
								    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
								    	String actions="Actions: "+""+((current.getActionsAvailable()));
								    	String health="Health Pts: "+""+(current.getCurrentHp());
								    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
								    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
								    	String type="Type: "+Type(current);
								    	l1.setText(name);
								    	l1.setStyle("-fx-font-size: 30px;");
								    	l2.setText(attackdmg);
								    	l2.setStyle("-fx-font-size: 30px;");
								    	l3.setText(actions);
								    	l3.setStyle("-fx-font-size: 30px;");
								    	l4.setText(health);
								    	l4.setStyle("-fx-font-size: 30px;");
								    	l5.setText(vaccines);
								    	l5.setStyle("-fx-font-size: 30px;");
								    	l6.setText(supplies);
								    	l6.setStyle("-fx-font-size: 30px;");
								    	l7.setText(type);
								    	l7.setStyle("-fx-font-size: 30px;");
								    	label.setSpacing(10);
								    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
								        int index = 0;
								        
								        
								    	for(int i=1;i<Game.heroes.size();i++){
								    		index++;
								    		
								    		String num=""+index;
									    	Button hero2=new Button(num);
									    	hero2.setStyle("-fx-font-size: 15px;");
									    	hero2.setPrefWidth(index);
									    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                         
												
												public void handle(ActionEvent event) {
													current=Game.heroes.get((int) hero2.getPrefWidth());
													
	                                               
													
												}
									    		
									    	});
									    	label.getChildren().addAll(hero2);
									    	
									    	
									    	}
								    	lay.getChildren().addAll(label);
								    	
								    	
								    
								    	
								    	
								    	
									} catch (NoAvailableResourcesException e) {
										 popup(e.getMessage());
									} catch (InvalidTargetException e) {
										 popup(e.getMessage());
									}
								
									
							    	
									
								
									
									
								} catch (NotEnoughActionsException e) {
									 popup(e.getMessage());
									
								}
								
							}

							
				    		
				    	});
				    	
				    	
				    	
				    	
				    	Button specialaction=new Button("Special Action");
				    	specialaction.setStyle("-fx-font-size: 20px;");
				    	specialaction.setOnAction(new EventHandler<ActionEvent>(){

		
		                  public void handle(ActionEvent event) {
		                	   try {
								current.useSpecial();
								 startgame2();
								 if(Game.checkWin()==true){
									 String wow="YOU WON YAYY!";
										popup2(wow);
									 
									}
								 else if(Game.checkGameOver()==true){
									 String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
								 VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
							} catch (NoAvailableResourcesException e) {
							     popup(e.getMessage());
								
							} catch (InvalidTargetException e) {
								 popup(e.getMessage());
							}
		                	   
								
		                	  
		                  }
				    		
				    	});
				    	
				    	Button EndTurn=new Button("End Turn");
				    	EndTurn.setStyle("-fx-font-size: 20px;");
				    	EndTurn.setOnAction(new EventHandler<ActionEvent>(){

				    		
			                  public void handle(ActionEvent event) {
			                	  
				                 try {
									Game.endTurn();
									startgame2();
									if(Game.checkWin()==true){
										String wow="YOU WON YAYY!";
										popup2(wow);
										
									}
									else if(Game.checkGameOver()==true){
										String sad="LOSER YOU LOST STUPID!";
										popup2(sad);
									}
									VBox label=new VBox();
							    	BackgroundFill bg=new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,Insets.EMPTY);
							    	Background b=new Background(bg);
							    	label.setBackground(b);
							    	Label l1=new Label();
							    	Label l2=new Label();
							    	Label l3=new Label();
							    	Label l4=new Label();
							    	Label l5=new Label();
							    	Label l6=new Label();
							    	Label l7=new Label();
							    	String name="Name: "+current.getName();
							    	String attackdmg="Attack Dmg: "+""+(current.getAttackDmg());
							    	String actions="Actions: "+""+((current.getActionsAvailable()));
							    	String health="Health Pts: "+""+(current.getCurrentHp());
							    	String vaccines="Vaccines: "+""+current.getVaccineInventory().size();
							    	String supplies="Supplies: "+""+current.getSupplyInventory().size();
							    	String type="Type: "+Type(current);
							    	l1.setText(name);
							    	l1.setStyle("-fx-font-size: 30px;");
							    	l2.setText(attackdmg);
							    	l2.setStyle("-fx-font-size: 30px;");
							    	l3.setText(actions);
							    	l3.setStyle("-fx-font-size: 30px;");
							    	l4.setText(health);
							    	l4.setStyle("-fx-font-size: 30px;");
							    	l5.setText(vaccines);
							    	l5.setStyle("-fx-font-size: 30px;");
							    	l6.setText(supplies);
							    	l6.setStyle("-fx-font-size: 30px;");
							    	l7.setText(type);
							    	l7.setStyle("-fx-font-size: 30px;");
							    	label.setSpacing(10);
							    	label.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,my);
							    	lay.getChildren().add(label);
							    	int index = 0;
							        
							        
							    	for(int i=1;i<Game.heroes.size();i++){
							    		index++;
							    		
							    		String num=""+index;
								    	Button hero2=new Button(num);
								    	hero2.setStyle("-fx-font-size: 15px;");
								    	hero2.setPrefWidth(index);
								    	hero2.setOnAction(new EventHandler<ActionEvent>(){
	                                     
											
											public void handle(ActionEvent event) {
												current=Game.heroes.get((int) hero2.getPrefWidth());
												
	                                           
												
											}
								    		
								    	});
								    	label.getChildren().addAll(hero2);
								}
								} catch (NotEnoughActionsException e) {
									
									popup(e.getMessage());
								} catch (InvalidTargetException e) {
									popup(e.getMessage());
								}
			                  
			                  }
					    	});
				    	
				    	HBox gameplay=new HBox(moveright,moveleft,moveup,movedown,hit,cure,specialaction,EndTurn);
				    	
				    	gameplay.setSpacing(20);
				    	gameplay.setLayoutY(780);
				    	gameplay.setStyle("-fx-font-size: 8px;");
				    	lay.getChildren().addAll(gameplay);
				    
				    	
				    	
				    	             
				    	
				    	s = new Scene(lay,1500,900);
				    	s.setFill(Color.BLACK);
						primaryStage.setScene(s);
	                    primaryStage.show();  
				    });
				root2.getChildren().add(root1);

				 s = new Scene(root2,1500,900);
			
				primaryStage.setScene(s);
                
				primaryStage.show();
			
				
				}
			
				
			
        	
        });
          
            
		} 
	public static void popup(String error){
		 Stage stage2=new Stage();
		 stage2.setTitle("ERROR");
		 Pane r=new Pane();
		 Label warning=new Label();
		 warning.setText(error);
         warning.setAlignment(Pos.CENTER);
         r.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET,null,null)));
         Scene alert =new Scene(r);
         stage2.setWidth(500);
         stage2.setHeight(300);
         r.getChildren().add(warning);
         stage2.setScene(alert);
         stage2.show();
         Button close=new Button("CLOSE");
         close.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				
				stage2.setScene(s);
			}
        	 
         });
		
	}
	public static void popup2(String error){
		 Stage stage2=new Stage();
		 stage2.setTitle("Message");
		 Pane r=new Pane();
		 Label warning=new Label();
		 warning.setText(error);
        warning.setAlignment(Pos.CENTER);
        r.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET,null,null)));
        Scene alert =new Scene(r);
        stage2.setWidth(500);
        stage2.setHeight(300);
        r.getChildren().add(warning);
        stage2.setScene(alert);
        stage2.show();
        
        Button close=new Button("CLOSE");
        close.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				stage2.close();
//				Stage stage2 = (Stage) close.getScene().getWindow();
//				stage2.close();
				
			}
       	 
        });
		
	}

		


	
	public static void main(String[]args){
		launch(args);
	}
	
	public void stop() {
		
	}
	public void init(){
		
	}

}

       