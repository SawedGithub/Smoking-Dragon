package SmokingDragon;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class InGame extends Application{
	
	
//	PAUSE, CLOSE MENU, RESUME, QUIT BUTTONS
	private Image pauseBtnImg=new Image(getClass().getResourceAsStream("resource/pauseBtn.png"));
	private Image pauseMenuImg=new Image(getClass().getResourceAsStream("resource/pauseMenu.png"));
	private ImageView pauseBtn=new ImageView();
	private ImageView pauseMenu=new ImageView();
	private Image closeBtnImg=new Image(getClass().getResourceAsStream("resource/closeBtn.png"));
	private ImageView closeBtn=new ImageView();
	private ImageView closeBtn2=new ImageView();
	private Image menuBtnImg=new Image(getClass().getResourceAsStream("resource/menuBtn.png"));
	private ImageView resumeBtn=new ImageView();
	private ImageView quitBtn=new ImageView();
	private VBox menuBtnGroup=new VBox();
	private Pane pauseMenuGroup=new Pane();
	
	private Image bg=new Image(getClass().getResourceAsStream("resource/background1.png"));
	private BackgroundSize bgSize=new BackgroundSize(800,733, false, false, false, true);
	private BackgroundImage bgImg=new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT,
			BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
	private Background backg=new Background(bgImg);
	
	//IMAGE CONTAINING ALL SHOP, PURCHASE SLOT,EMPTY SLOT
	private Image shopSpritesheet=new Image(getClass().getResourceAsStream("resource/allShopSprite.png"));
	
	//EMPTY SHOP SLOT IMAGEVIEW
	private ImageView shopSlot1=new ImageView();
	private ImageView shopSlot2=new ImageView();
	private ImageView shopSlot3=new ImageView();
	private ImageView shopSlot4=new ImageView();
	private ImageView shopSlot5=new ImageView();
	private ImageView shopSlot6=new ImageView();
	
	//GIFT SHOP
	private ImageView giftShop=new ImageView();
	private Image giftSprite=new Image(getClass().getResourceAsStream("resource/giftSprite.png"));
	private ImageView giftMenu=new ImageView();
	private ImageView gift1=new ImageView();
	private ImageView gift2=new ImageView();
	private ImageView gift3=new ImageView();
	private ImageView closeGiftMenu=new ImageView();
	private int giftProgress=0;
	private HBox giftShops=new HBox(gift1,gift2,gift3);
	private Pane giftPane=new Pane(giftMenu, giftShops,closeGiftMenu);
	
	//COUNTER CHECK FOR EMPTY SLOTS
	private int slotCount1=0;
	private String slot1Upgrade="";
	private int slotCount2=0;
	private String slot2Upgrade="";
	private int slotCount3=0;
	private String slot3Upgrade="";
	private int slotCount4=0;
	private String slot4Upgrade="";
	private int slotCount5=0;
	private String slot5Upgrade="";
	private int slotCount6=0;
	private String slot6Upgrade="";
	
	//GROUPING EMPTY SLOTS 1 to 6
	private HBox slotBox1=new HBox();
	private HBox slotBox2=new HBox();
	private VBox allSlotBox=new VBox();
	
	//SHOP LIST: tier1
	private shopList skeletonMine=new shopList("skeletonMine",5, 20);
	private shopList goblinBar=new shopList("goblinBar",15, 50);
	private shopList lotteryDen=new shopList("lotteryDen",30, 100);
//	tier 2
	private shopList ancientForge=new shopList("ancientForge",70, 500);
	private shopList dragonSpa=new shopList("dragonSpa",450, 1500);
	private shopList casinoDen=new shopList("casinoDen",800, 4000);
//	tier 3
	private shopList necroAcadamy=new shopList("necroAcadamy",3500, 7000);
	private shopList monsterResort=new shopList("monsterResort",9500, 25000);
	private shopList ultracasinoDen=new shopList("ultracasinoDen",15000, 250000);
	
	//SHOP IMAGEVIEW
	private ImageView skelMine=new ImageView();
	private ImageView gobBar=new ImageView();
	private ImageView lottDen=new ImageView();
	private ImageView anciForge=new ImageView();
	private ImageView drgnSpa=new ImageView();
	private ImageView casiDen=new ImageView();
	private ImageView necroAca=new ImageView();
	private ImageView monResort=new ImageView();
	private ImageView UcasiDen=new ImageView();
	
	
	//GOLD LABELS, MAX GOLD= 9DIGITS
	private Text goldDisplay=new Text("example");
	private int currentGold=50;
	private int totalIncome=0;
	private float slot4PriceIncrease=200.0f;
	private float slot5PriceIncrease=400.0f;
	private float slot6PriceIncrease=600.0f;
	
	//POPS UP WHEN INSUFFICIENT GOLD
	private Text insufficientGoldLbl=new Text("example");
	private Image insufficientGoldImg=new Image(getClass().getResourceAsStream("resource/insufficientGoldMenu.png"));
	private ImageView insuffGold=new ImageView();
	private ImageView closeGoldMenu=new ImageView();
	private Pane popUpGoldMenu=new Pane(insuffGold,closeGoldMenu,insufficientGoldLbl);
	
	
	//PURCHASE MENU NODES
	private Image purchaseImg=new Image(getClass().getResourceAsStream("resource/purchaseBg1.png"));
	private ImageView purchasebackGround=new ImageView();
	private ImageView selectSlot1=new ImageView();
	private ImageView selectSlot2=new ImageView();
	private ImageView selectSlot3=new ImageView();
	private ImageView purchasebackGround2=new ImageView();
	private ImageView upgradeSlot=new ImageView();
	private ImageView sellSlot=new ImageView();
	
	//backGroundSetter PARENT NODES
	private Pane externalPane=new Pane();
	private Pane externalPane2=new Pane();
	private HBox selectSlotBox=new HBox();
	private HBox selectSlotBox2=new HBox();
	private BorderPane backGroundSetter=new BorderPane();
	private Pane root=new Pane();
	
	private delayedAnimationTimer timer = new delayedAnimationTimer(1000) {

	    @Override
	    public void handle() {
	    	doHandle();
	    	
	    }
	};
	
	protected void doHandle() {
//        System.out.println(totalIncome+"/s");
        currentGold+=totalIncome;
		goldDisplay.setText(""+currentGold);
	}
	
	private AnimationTimer checkGift;

	@Override
	public void start(Stage st) throws Exception {

		
		setUpNodes();
		shopEvent1();
		shopEvent2();
		shopEvent3();
		shopEvent4();
		shopEvent5();
		shopEvent6();
		giftEvent();

		pauseBtn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            
     		public void handle(MouseEvent t) {
     			System.out.println("Paused Game");
     			timer.stop();
     			checkGift.stop();
		        allSlotBox.setDisable(true);
		        giftShop.setDisable(true);
		        pauseMenuGroup.setVisible(true);
		        pauseMenuGroup.setDisable(false);
		        pauseBtn.setDisable(true);
		        t.consume();
            }
        });
		resumeBtn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            
     		public void handle(MouseEvent t) {
     			System.out.println("Resume Game");
     			timer.start();
     			checkGift.start();
		        allSlotBox.setDisable(false);
		        giftShop.setDisable(false);
		        pauseMenuGroup.setVisible(false);
		        pauseMenuGroup.setDisable(true);
		        pauseBtn.setDisable(false);
		        t.consume();
            }
        });
		quitBtn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
     		public void handle(MouseEvent t) {
     			st.close();
     			gameMenu backtoMenu=new gameMenu();
     			try {
					backtoMenu.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		slotBox1.getChildren().addAll(shopSlot1,shopSlot2,shopSlot3);
		slotBox2.getChildren().addAll(shopSlot4,shopSlot5,shopSlot6);
		slotBox1.setStyle("-fx-padding:90;"
				+ "-fx-spacing:20;");
		slotBox1.setAlignment(Pos.CENTER);
		slotBox2.setStyle("-fx-spacing:20;");
		slotBox2.setAlignment(Pos.TOP_CENTER);
		allSlotBox.getChildren().addAll(slotBox1,slotBox2);
		root.setBackground(backg);
		backGroundSetter.setCenter(allSlotBox);
		goldDisplay.setTextAlignment(TextAlignment.JUSTIFY);
		goldDisplay.setStyle("-fx-spacing:20;");
		goldDisplay.setLayoutX(300);
		goldDisplay.setLayoutY(527);
		goldDisplay.setText(""+currentGold);
		goldDisplay.setFont(
				Font.loadFont(
						getClass().getResourceAsStream("resource/SIEBB.TTF"),40));
		
		menuBtnGroup.getChildren().addAll(resumeBtn,quitBtn);
		pauseMenuGroup.getChildren().addAll(pauseMenu,menuBtnGroup);
		
		insufficientGoldLbl.setStyle("-fx-spacing:20;");
		insufficientGoldLbl.setTextAlignment(TextAlignment.JUSTIFY);
		insufficientGoldLbl.setLayoutX(100);
		insufficientGoldLbl.setLayoutY(100);
		insufficientGoldLbl.setFont(
				Font.loadFont(
						getClass().getResourceAsStream("resource/SIEBB.TTF"),40));
		
		
		root.getChildren().addAll(backGroundSetter,giftShop,
				goldDisplay,externalPane,externalPane2,
				pauseBtn,pauseMenuGroup,giftPane,popUpGoldMenu);
		
		Scene sc=new Scene(root,800,600);
		
		st.setTitle("Smoking Dragon");
		st.setScene(sc);
		st.setResizable(false);
		st.centerOnScreen();
		st.show();
		st.sizeToScene();
		
		timer.start();
		checkGift=new AnimationTimer() {
			
			public void handle(long now) {
				
				if(giftProgress>=3) {
					System.out.println("Game complete");
					timer.stop();
	     			checkGift.stop();
			        allSlotBox.setDisable(true);
			        giftShop.setDisable(true);
			        st.close();
			        gameEnding theEnd=new gameEnding();
			        try {
						theEnd.start(new Stage());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		};
		
		checkGift.start();
		
	}


	private void giftEvent() {
		giftShop.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {

			public void handle(MouseEvent arg0) {
				System.out.println("Gift shop pressed");
				
				giftPane.setVisible(true);
		        giftPane.setDisable(false);
		        allSlotBox.setDisable(true);
//		        giftShop.setVisible(false);
		        giftShop.setDisable(true);
				
				closeGiftMenu.setOnMouseClicked(e->{
					System.out.println("Gift Menu cancelled");
					giftPane.setVisible(false);
					giftPane.setDisable(true);
					allSlotBox.setDisable(false);
//					giftShop.setVisible(true);
					giftShop.setDisable(false);
				});
				
				gift1.setOnMouseClicked(e->{
					//Gift 1 costs: 50k, boosts income by 5%
					if(currentGold>=50000) {
						gift1.setOpacity(0.2);
						gift1.setDisable(true);
						currentGold-=50000;
						goldDisplay.setText(""+currentGold);
						totalIncome=(int)(totalIncome*(105.0f/100.0f));
						giftProgress+=1;
					}
					else {
						insufficientGold(50000);
					}
				});
				gift2.setOnMouseClicked(e->{
					//Gift 2 costs 500k, boosts income by 10%
					if(currentGold>=500000) {
						gift2.setOpacity(0.2);
						gift2.setDisable(true);
						currentGold-=500000;
						goldDisplay.setText(""+currentGold);
						totalIncome=(int)(totalIncome*(110.0f/100.0f));
						giftProgress+=1;
					}
					else {
						insufficientGold(500000);
					}
				});
				gift3.setOnMouseClicked(e->{
//					Gift 3 costs 3smil
					if(currentGold>=3000000) {
						gift3.setOpacity(0.2);
						gift3.setDisable(true);
						currentGold-=3000000;
						giftProgress+=1;
					}
					else {
						insufficientGold(3000000);
					}
				});
			}
			
		});
		
	}

	private void shopEvent1() {

		shopSlot1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				System.out.println("Tile pressed ");
		         
				closeBtn.setOnMouseClicked(e->{
					System.out.println("\nPurchase Menu cancelled");
					externalPane.setVisible(false);
   		         	externalPane.setDisable(true);
   		         	allSlotBox.setDisable(false);
				});
				closeBtn2.setOnMouseClicked(e->{
					System.out.println("Upgrade menu cancelled");
					externalPane2.setVisible(false);
					externalPane2.setDisable(true);
   		         	allSlotBox.setDisable(false);
				});
		         
		         
				switch (slotCount1) {
				case 0:
					allSlotBox.setDisable(true);
					externalPane.setVisible(true);
			        externalPane.setDisable(false);
					selectSlot1.setOnMouseClicked(new EventHandler<MouseEvent>()
					{
		               
						public void handle(MouseEvent t) {
		         			
							System.out.println("Selected Skeleton Mine");
							if(currentGold>=skeletonMine.getCost()) {
		                	
		   		         	externalPane.setVisible(false);
		   		         	externalPane.setDisable(true);
		   		         	allSlotBox.setDisable(false);
		   		         	shopSlot1.setViewport(skelMine.getViewport());
		   		         	
		   		         	//Affects Income/s
		   		         	totalIncome+=skeletonMine.getIncome();
		   		         	currentGold-=skeletonMine.getCost();
		   		         	goldDisplay.setText(""+currentGold);
		   		         	
		   		         	//Affects Shop TIER and UPGRADING/ SELLING
		   		         	slotCount1+=1;
		   		         	System.out.println("\n"+slotCount1);
		   		         	slot1Upgrade=skeletonMine.getName();
		   		         	System.out.println("Bought "+slot1Upgrade);
		   		         	}
		                	else {
		                		insufficientGold(skeletonMine.getCost());
		                		externalPane.setVisible(false);
			   		         	externalPane.setDisable(true);
			   		         	allSlotBox.setDisable(false);
		                	}
		   		         	t.consume();
		                }
		            });
		         	selectSlot2.setOnMouseClicked(new EventHandler<MouseEvent>()
		            {
		               
		         		public void handle(MouseEvent t) {
		         			
		                	System.out.println("Selected Goblin Bar");
		                	if(currentGold>=goblinBar.getCost()) {
		                		
		                	externalPane.setVisible(false);
			   		        externalPane.setDisable(true);
			   		        allSlotBox.setDisable(false);
			   		        shopSlot1.setViewport(gobBar.getViewport());
		   		         	
		   		         	totalIncome+=goblinBar.getIncome();
		   		         	currentGold-=goblinBar.getCost();
		   		         	goldDisplay.setText(""+currentGold);
		   		         	
		   		         	slotCount1+=1;
		   		         	System.out.println("\n"+slotCount1);
		   		         	slot1Upgrade=goblinBar.getName();
		   		         	System.out.println("Bought "+slot1Upgrade);
		   		         	}
		                	else {
		                		insufficientGold(goblinBar.getCost());
		                		externalPane.setVisible(false);
			   		         	externalPane.setDisable(true);
			   		         	allSlotBox.setDisable(false);
		                	}
		   		         	t.consume();
		                }
		            });
		         	
		         	selectSlot3.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent arg0) {
							System.out.println("Selected Lottery Den");
							if(currentGold>=lotteryDen.getCost()) {
								
								externalPane.setVisible(false);
				   		        externalPane.setDisable(true);
				   		        allSlotBox.setDisable(false);
				   		        shopSlot1.setViewport(lottDen.getViewport());
								
								totalIncome+=lotteryDen.getIncome();
								currentGold-=lotteryDen.getCost();
								goldDisplay.setText(""+currentGold);
								
								slotCount1+=1;
			   		         	System.out.println("\n"+slotCount1);
			   		         	slot1Upgrade=lotteryDen.getName();
			   		         	System.out.println("Bought "+slot1Upgrade);
							}
							else {
								insufficientGold(lotteryDen.getCost());
								externalPane.setVisible(false);
								externalPane.setDisable(true);
								allSlotBox.setDisable(false);
							}
							
						}

					});
					
					break;
				case 1:
					allSlotBox.setDisable(true);
					externalPane2.setVisible(true);
			        externalPane2.setDisable(false);
			        
			        switch (slot1Upgrade) {
					case "skeletonMine":
						upgradeSlot.setViewport(anciForge.getViewport());
							upgradeSlot.setOnMouseClicked(e->{
								
								System.out.println("Upgrade to "+ancientForge.getName());
								if(currentGold>=ancientForge.getCost()) {
									externalPane2.setVisible(false);
				   		         	externalPane2.setDisable(true);
				   		         	allSlotBox.setDisable(false);
				   		         	shopSlot1.setViewport(anciForge.getViewport());
									
									totalIncome+=ancientForge.getIncome();
									currentGold-=ancientForge.getCost();
									goldDisplay.setText(""+currentGold);
									
									slotCount1+=1;
				   		         	System.out.println("\n"+slotCount1);
				   		         	slot1Upgrade=ancientForge.getName();
									System.out.println("\nUpgraded to "+slot1Upgrade);
									
								}
								else {
									insufficientGold(ancientForge.getCost());
									externalPane2.setVisible(false);
				   		         	externalPane2.setDisable(true);
				   		         	allSlotBox.setDisable(false);
								}
							});
							
							sellSlot.setOnMouseClicked(e->{
								
								System.out.println("Selling "+slot1Upgrade);
								totalIncome-=skeletonMine.getIncome();
								currentGold+=skeletonMine.getCost();
								goldDisplay.setText(""+currentGold);
								
								slotCount1=0;
								System.out.println("Sold "+slot1Upgrade);
								slot1Upgrade="";
								System.out.println("Reset Slot\n");
								
								externalPane2.setVisible(false);
			   		         	externalPane2.setDisable(true);
			   		         	allSlotBox.setDisable(false);
			   		         	shopSlot1.setViewport(new Rectangle2D(0, 257, 128, 128));
							});
							
						break;

					case "goblinBar":
						upgradeSlot.setViewport(drgnSpa.getViewport());
							upgradeSlot.setOnMouseClicked(e->{
							
								System.out.println("Upgrade to "+dragonSpa.getName());
								if(currentGold>=dragonSpa.getCost()) {
									externalPane2.setVisible(false);
									externalPane2.setDisable(true);
									allSlotBox.setDisable(false);
									shopSlot1.setViewport(drgnSpa.getViewport());
								
									totalIncome+=dragonSpa.getIncome();
									currentGold-=dragonSpa.getCost();
									goldDisplay.setText(""+currentGold);
								
									slotCount1+=1;
									System.out.println("\n"+slotCount1);
									slot1Upgrade=dragonSpa.getName();
									System.out.println("\nUpgraded to "+slot1Upgrade);
								
								}
								else {
									insufficientGold(dragonSpa.getCost());
									externalPane2.setVisible(false);
									externalPane2.setDisable(true);
									allSlotBox.setDisable(false);
								}
							});
						
							sellSlot.setOnMouseClicked(e->{
							
								System.out.println("Selling "+slot1Upgrade);
								totalIncome-=goblinBar.getIncome();
								currentGold+=goblinBar.getCost();
								goldDisplay.setText(""+currentGold);
							
								slotCount1=0;
								System.out.println("Sold "+slot1Upgrade);
								slot1Upgrade="";
								System.out.println("Reset Slot\n");
							
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
								shopSlot1.setViewport(new Rectangle2D(0, 257, 128, 128));
							});
						break;
						
					case "lotteryDen":
						upgradeSlot.setViewport(casiDen.getViewport());
							upgradeSlot.setOnMouseClicked(e->{
							
								System.out.println("Upgrade to "+casinoDen.getName());
								if(currentGold>=casinoDen.getCost()) {
									externalPane2.setVisible(false);
									externalPane2.setDisable(true);
									allSlotBox.setDisable(false);
									shopSlot1.setViewport(casiDen.getViewport());
								
									totalIncome+=casinoDen.getIncome();
									currentGold-=casinoDen.getCost();
									goldDisplay.setText(""+currentGold);
								
									slotCount1+=1;
									System.out.println("\n"+slotCount1);
									slot1Upgrade=casinoDen.getName();
									System.out.println("\nUpgraded to "+slot1Upgrade);
								
								}
								else {
									insufficientGold(casinoDen.getCost());
									externalPane2.setVisible(false);
									externalPane2.setDisable(true);
									allSlotBox.setDisable(false);
								}
							});
							
							sellSlot.setOnMouseClicked(e->{
							
								System.out.println("Selling "+slot1Upgrade);
								totalIncome-=lotteryDen.getIncome();
								currentGold+=lotteryDen.getCost();
								goldDisplay.setText(""+currentGold);
							
								slotCount1=0;
								System.out.println("Sold "+slot1Upgrade);
								slot1Upgrade="";
								System.out.println("Reset Slot\n");
							
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
								shopSlot1.setViewport(new Rectangle2D(0, 257, 128, 128));
							});
						break;
					}
			        
					break;
				case 2:
					System.out.println("Final shop tier");
					allSlotBox.setDisable(true);
					externalPane2.setVisible(true);
			        externalPane2.setDisable(false);
					
					switch (slot1Upgrade) {
					
					case "ancientForge":
						upgradeSlot.setViewport(necroAca.getViewport());
							upgradeSlot.setOnMouseClicked(e->{

								System.out.println("Upgrade to "+necroAcadamy.getName());
								if(currentGold>=necroAcadamy.getCost()) {
									externalPane2.setVisible(false);
			   		         		externalPane2.setDisable(true);
			   		         		allSlotBox.setDisable(false);
			   		         		shopSlot1.setDisable(true);
			   		         		shopSlot1.setViewport(necroAca.getViewport());
			   		         		
			   		         		//Necro Acadamy refunds 50% of its cost after building
			   		         		totalIncome+=necroAcadamy.getIncome();
			   		         		currentGold-=(int)(necroAcadamy.getCost()*(50.0f/100.0f));
			   		         		goldDisplay.setText(""+currentGold);
								
			   		         		slotCount1=3;
			   		         		System.out.println("\n"+slotCount1);
			   		         		slot1Upgrade=necroAcadamy.getName();
			   		         		System.out.println("\nUpgraded to "+slot1Upgrade);
			   		         		
								}
								else {
									insufficientGold(necroAcadamy.getCost());
									externalPane2.setVisible(false);
			   		         		externalPane2.setDisable(true);
			   		         		allSlotBox.setDisable(false);
								}
							});

							sellSlot.setOnMouseClicked(e->{
							
								System.out.println("Selling "+slot1Upgrade);
								totalIncome-=ancientForge.getIncome();
								currentGold+=ancientForge.getCost();
								goldDisplay.setText(""+currentGold);
							
								slotCount1=1;
								System.out.println("Sold "+slot1Upgrade);
								slot1Upgrade=skeletonMine.getName();
								System.out.println("Reset Slot\n");
							
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
								shopSlot1.setViewport(skelMine.getViewport());
							});
						break;
						
					case "dragonSpa":
						upgradeSlot.setViewport(monResort.getViewport());
						upgradeSlot.setOnMouseClicked(e->{

							System.out.println("Upgrade to "+monsterResort.getName());
							if(currentGold>=monsterResort.getCost()) {
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
		   		         		shopSlot1.setDisable(true);
		   		         		shopSlot1.setViewport(monResort.getViewport());
		   		         		
		   		         		//Monster Resort gives 10% bonus to overall income
		   		         		totalIncome+=monsterResort.getIncome();
		   		         		totalIncome=(int)(totalIncome*(110.0f/100.0f));
		   		         		currentGold-=monsterResort.getCost();
		   		         		goldDisplay.setText(""+currentGold);
							
		   		         		slotCount1=3;
		   		         		System.out.println("\n"+slotCount1);
		   		         		slot1Upgrade=monsterResort.getName();
		   		         		System.out.println("\nUpgraded to "+slot1Upgrade);
		   		         		
							}
							else {
								insufficientGold(monsterResort.getCost());
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
							}
						});

						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=dragonSpa.getIncome();
							currentGold+=dragonSpa.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount1=1;
							System.out.println("Sold "+slot1Upgrade);
							slot1Upgrade=goblinBar.getName();
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot1.setViewport(gobBar.getViewport());
						});
						break;
					
					case "casinoDen":
						upgradeSlot.setViewport(UcasiDen.getViewport());
						upgradeSlot.setOnMouseClicked(e->{

							System.out.println("Upgrade to "+ultracasinoDen.getName());
							if(currentGold>=ultracasinoDen.getCost()) {
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
		   		         		shopSlot1.setDisable(true);
		   		         		shopSlot1.setViewport(UcasiDen.getViewport());
		   		         		
		   		         		//Ultra Casino gives a massive 30% boost to overall income
		   		         		totalIncome+=ultracasinoDen.getIncome();
		   		         		totalIncome=(int)(totalIncome*(130.0f/100.0f));
		   		         		currentGold-=ultracasinoDen.getCost();
		   		         		goldDisplay.setText(""+currentGold);
							
		   		         		slotCount1=3;
		   		         		System.out.println("\n"+slotCount1);
		   		         		slot1Upgrade=ultracasinoDen.getName();
		   		         		System.out.println("\nUpgraded to "+slot1Upgrade);
		   		         		
							}
							else {
								insufficientGold(ultracasinoDen.getCost());
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
							}
						});

						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=casinoDen.getIncome();
							currentGold+=casinoDen.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount1=1;
							System.out.println("Sold "+slot1Upgrade);
							slot1Upgrade=lotteryDen.getName();
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot1.setViewport(lottDen.getViewport());
						});
						break;
					}
					
					break;
				}
		        event.consume();
		         
		     }
		});
		
	}

	private void shopEvent2() {
		shopSlot2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

		public void handle(MouseEvent event) {
			System.out.println("Tile pressed ");
	         
			closeBtn.setOnMouseClicked(e->{
				System.out.println("\nPurchase Menu cancelled");
				externalPane.setVisible(false);
		         	externalPane.setDisable(true);
		         	allSlotBox.setDisable(false);
			});
			closeBtn2.setOnMouseClicked(e->{
				System.out.println("Upgrade menu cancelled");
				externalPane2.setVisible(false);
				externalPane2.setDisable(true);
		         	allSlotBox.setDisable(false);
			});
	         
	         
			switch (slotCount2) {
			case 0:
				allSlotBox.setDisable(true);
				externalPane.setVisible(true);
		        externalPane.setDisable(false);
				selectSlot1.setOnMouseClicked(new EventHandler<MouseEvent>()
				{
	               
					public void handle(MouseEvent t) {
	         			
						System.out.println("Selected Skeleton Mine");
						if(currentGold>=skeletonMine.getCost()) {
	                	
	   		         	externalPane.setVisible(false);
	   		         	externalPane.setDisable(true);
	   		         	allSlotBox.setDisable(false);
	   		         	shopSlot2.setViewport(skelMine.getViewport());
	   		         	
	   		         	//Affects Income/s
	   		         	totalIncome+=skeletonMine.getIncome();
	   		         	currentGold-=skeletonMine.getCost();
	   		         	goldDisplay.setText(""+currentGold);
	   		         	
	   		         	//Affects Shop TIER and UPGRADING/ SELLING
	   		         	slotCount2+=1;
	   		         	System.out.println("\n"+slotCount2);
	   		         	slot2Upgrade=skeletonMine.getName();
	   		         	System.out.println("Bought "+slot2Upgrade);
	   		         	}
	                	else {
	                		insufficientGold(skeletonMine.getCost());
	                		externalPane.setVisible(false);
		   		         	externalPane.setDisable(true);
		   		         	allSlotBox.setDisable(false);
	                	}
	   		         	t.consume();
	                }
	            });
	         	selectSlot2.setOnMouseClicked(new EventHandler<MouseEvent>()
	            {
	               
	         		public void handle(MouseEvent t) {
	         			
	                	System.out.println("Selected Goblin Bar");
	                	if(currentGold>=goblinBar.getCost()) {
	                		
	                	externalPane.setVisible(false);
		   		        externalPane.setDisable(true);
		   		        allSlotBox.setDisable(false);
		   		        shopSlot2.setViewport(gobBar.getViewport());
	   		         	
	   		         	totalIncome+=goblinBar.getIncome();
	   		         	currentGold-=goblinBar.getCost();
	   		         	goldDisplay.setText(""+currentGold);
	   		         	
	   		         	slotCount2+=1;
	   		         	System.out.println("\n"+slotCount2);
	   		         	slot2Upgrade=goblinBar.getName();
	   		         	System.out.println("Bought "+slot2Upgrade);
	   		         	}
	                	else {
	                		insufficientGold(goblinBar.getCost());
	                		externalPane.setVisible(false);
		   		         	externalPane.setDisable(true);
		   		         	allSlotBox.setDisable(false);
	                	}
	   		         	t.consume();
	                }
	            });
	         	
	         	selectSlot3.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						System.out.println("Selected Lottery Den");
						if(currentGold>=lotteryDen.getCost()) {
							
							externalPane.setVisible(false);
			   		        externalPane.setDisable(true);
			   		        allSlotBox.setDisable(false);
			   		        shopSlot2.setViewport(lottDen.getViewport());
							
							totalIncome+=lotteryDen.getIncome();
							currentGold-=lotteryDen.getCost();
							goldDisplay.setText(""+currentGold);
							
							slotCount2+=1;
		   		         	System.out.println("\nShop Level:"+slotCount2);
		   		         	slot2Upgrade=lotteryDen.getName();
		   		         	System.out.println("Bought "+slot2Upgrade);
						}
						else {
							insufficientGold(lotteryDen.getCost());
							externalPane.setVisible(false);
							externalPane.setDisable(true);
							allSlotBox.setDisable(false);
						}
						
					}

				});
				
				break;
			case 1:
				allSlotBox.setDisable(true);
				externalPane2.setVisible(true);
		        externalPane2.setDisable(false);
		        
		        switch (slot2Upgrade) {
				case "skeletonMine":
					upgradeSlot.setViewport(anciForge.getViewport());
						upgradeSlot.setOnMouseClicked(e->{
							
							System.out.println("Upgrade to "+ancientForge.getName());
							if(currentGold>=ancientForge.getCost()) {
								externalPane2.setVisible(false);
			   		         	externalPane2.setDisable(true);
			   		         	allSlotBox.setDisable(false);
			   		         	shopSlot2.setViewport(anciForge.getViewport());
								
								totalIncome+=ancientForge.getIncome();
								currentGold-=ancientForge.getCost();
								goldDisplay.setText(""+currentGold);
								
								slotCount2+=1;
			   		         	System.out.println("\n"+slotCount2);
			   		         	slot2Upgrade=ancientForge.getName();
								System.out.println("\nUpgraded to "+slot2Upgrade);
								
							}
							else {
								insufficientGold(ancientForge.getCost());
								externalPane2.setVisible(false);
			   		         	externalPane2.setDisable(true);
			   		         	allSlotBox.setDisable(false);
							}
						});
						
						sellSlot.setOnMouseClicked(e->{
							
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=skeletonMine.getIncome();
							currentGold+=skeletonMine.getCost();
							goldDisplay.setText(""+currentGold);
							
							slotCount2=0;
							System.out.println("Sold "+slot2Upgrade);
							slot2Upgrade="";
							System.out.println("Reset Slot\n");
							
							externalPane2.setVisible(false);
		   		         	externalPane2.setDisable(true);
		   		         	allSlotBox.setDisable(false);
		   		         	shopSlot2.setViewport(new Rectangle2D(0, 257, 128, 128));
						});
						
					break;

				case "goblinBar":
					upgradeSlot.setViewport(drgnSpa.getViewport());
						upgradeSlot.setOnMouseClicked(e->{
						
							System.out.println("Upgrade to "+dragonSpa.getName());
							if(currentGold>=dragonSpa.getCost()) {
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
								shopSlot2.setViewport(drgnSpa.getViewport());
							
								totalIncome+=dragonSpa.getIncome();
								currentGold-=dragonSpa.getCost();
								goldDisplay.setText(""+currentGold);
							
								slotCount2+=1;
								System.out.println("\n"+slotCount2);
								slot2Upgrade=dragonSpa.getName();
								System.out.println("\nUpgraded to "+slot2Upgrade);
							
							}
							else {
								insufficientGold(dragonSpa.getCost());
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
							}
						});
					
						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=goblinBar.getIncome();
							currentGold+=goblinBar.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount2=0;
							System.out.println("Sold "+slot2Upgrade);
							slot2Upgrade="";
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot2.setViewport(new Rectangle2D(0, 257, 128, 128));
						});
					break;
					
				case "lotteryDen":
					upgradeSlot.setViewport(casiDen.getViewport());
						upgradeSlot.setOnMouseClicked(e->{
						
							System.out.println("Upgrade to "+casinoDen.getName());
							if(currentGold>=casinoDen.getCost()) {
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
								shopSlot2.setViewport(casiDen.getViewport());
							
								totalIncome+=casinoDen.getIncome();
								currentGold-=casinoDen.getCost();
								goldDisplay.setText(""+currentGold);
							
								slotCount2+=1;
								System.out.println("\n"+slotCount2);
								slot2Upgrade=casinoDen.getName();
								System.out.println("\nUpgraded to "+slot2Upgrade);
							
							}
							else {
								insufficientGold(casinoDen.getCost());
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
							}
						});
						
						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=lotteryDen.getIncome();
							currentGold+=lotteryDen.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount2=0;
							System.out.println("Sold "+slot2Upgrade);
							slot2Upgrade="";
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot2.setViewport(new Rectangle2D(0, 257, 128, 128));
						});
					break;
				}
		        
				break;
			case 2:
				System.out.println("Final shop tier");
				allSlotBox.setDisable(true);
				externalPane2.setVisible(true);
		        externalPane2.setDisable(false);
				
				switch (slot2Upgrade) {
				
				case "ancientForge":
					upgradeSlot.setViewport(necroAca.getViewport());
						upgradeSlot.setOnMouseClicked(e->{

							System.out.println("Upgrade to "+necroAcadamy.getName());
							if(currentGold>=necroAcadamy.getCost()) {
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
		   		         		shopSlot2.setDisable(true);
		   		         		shopSlot2.setViewport(necroAca.getViewport());
		   		         		
		   		         		//Necro Acadamy refunds 50% of its cost after building
		   		         		totalIncome+=necroAcadamy.getIncome();
		   		         		currentGold-=(int)(necroAcadamy.getCost()*(50.0f/100.0f));
		   		         		goldDisplay.setText(""+currentGold);
							
		   		         		slotCount2=3;
		   		         		System.out.println("\n"+slotCount2);
		   		         		slot2Upgrade=necroAcadamy.getName();
		   		         		System.out.println("\nUpgraded to "+slot2Upgrade);
		   		         		
							}
							else {
								insufficientGold(necroAcadamy.getCost());
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
							}
						});

						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=ancientForge.getIncome();
							currentGold+=ancientForge.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount2=1;
							System.out.println("Sold "+slot2Upgrade);
							slot2Upgrade=skeletonMine.getName();
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot2.setViewport(skelMine.getViewport());
						});
					break;
					
				case "dragonSpa":
					upgradeSlot.setViewport(monResort.getViewport());
					upgradeSlot.setOnMouseClicked(e->{

						System.out.println("Upgrade to "+monsterResort.getName());
						if(currentGold>=monsterResort.getCost()) {
							externalPane2.setVisible(false);
	   		         		externalPane2.setDisable(true);
	   		         		allSlotBox.setDisable(false);
	   		         		shopSlot2.setDisable(true);
	   		         		shopSlot2.setViewport(monResort.getViewport());
	   		         		
	   		         		//Monster Resort gives 10% bonus to overall income
	   		         		totalIncome+=monsterResort.getIncome();
	   		         		totalIncome=(int)(totalIncome*(110.0f/100.0f));
	   		         		currentGold-=monsterResort.getCost();
	   		         		goldDisplay.setText(""+currentGold);
						
	   		         		slotCount2=3;
	   		         		System.out.println("\n"+slotCount2);
	   		         		slot2Upgrade=monsterResort.getName();
	   		         		System.out.println("\nUpgraded to "+slot2Upgrade);
	   		         		
						}
						else {
							insufficientGold(monsterResort.getCost());
							externalPane2.setVisible(false);
	   		         		externalPane2.setDisable(true);
	   		         		allSlotBox.setDisable(false);
						}
					});

					sellSlot.setOnMouseClicked(e->{
					
						System.out.println("Selling "+slot1Upgrade);
						totalIncome-=dragonSpa.getIncome();
						currentGold+=dragonSpa.getCost();
						goldDisplay.setText(""+currentGold);
					
						slotCount2=1;
						System.out.println("Sold "+slot2Upgrade);
						slot2Upgrade=goblinBar.getName();
						System.out.println("Reset Slot\n");
					
						externalPane2.setVisible(false);
						externalPane2.setDisable(true);
						allSlotBox.setDisable(false);
						shopSlot2.setViewport(gobBar.getViewport());
					});
					break;
				
				case "casinoDen":
					upgradeSlot.setViewport(UcasiDen.getViewport());
					upgradeSlot.setOnMouseClicked(e->{

						System.out.println("Upgrade to "+ultracasinoDen.getName());
						if(currentGold>=ultracasinoDen.getCost()) {
							externalPane2.setVisible(false);
	   		         		externalPane2.setDisable(true);
	   		         		allSlotBox.setDisable(false);
	   		         		shopSlot2.setDisable(true);
	   		         		shopSlot2.setViewport(UcasiDen.getViewport());
	   		         		
	   		         		//Ultra Casino gives a massive 30% boost to overall income
	   		         		totalIncome+=ultracasinoDen.getIncome();
	   		         		totalIncome=(int)(totalIncome*(130.0f/100.0f));
	   		         		currentGold-=ultracasinoDen.getCost();
	   		         		goldDisplay.setText(""+currentGold);
						
	   		         		slotCount2=3;
	   		         		System.out.println("\n"+slotCount2);
	   		         		slot2Upgrade=ultracasinoDen.getName();
	   		         		System.out.println("\nUpgraded to "+slot2Upgrade);
	   		         		
						}
						else {
							insufficientGold(ultracasinoDen.getCost());
							externalPane2.setVisible(false);
	   		         		externalPane2.setDisable(true);
	   		         		allSlotBox.setDisable(false);
						}
					});

					sellSlot.setOnMouseClicked(e->{
					
						System.out.println("Selling "+slot1Upgrade);
						totalIncome-=casinoDen.getIncome();
						currentGold+=casinoDen.getCost();
						goldDisplay.setText(""+currentGold);
					
						slotCount2=1;
						System.out.println("Sold "+slot2Upgrade);
						slot2Upgrade=lotteryDen.getName();
						System.out.println("Reset Slot\n");
					
						externalPane2.setVisible(false);
						externalPane2.setDisable(true);
						allSlotBox.setDisable(false);
						shopSlot2.setViewport(lottDen.getViewport());
					});
					break;
				}
				
				break;
			}
	        event.consume();
	         
	     }
	});
	}
	
	private void shopEvent3() {
		shopSlot3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

		public void handle(MouseEvent event) {
			System.out.println("Tile pressed ");
	         
			closeBtn.setOnMouseClicked(e->{
				System.out.println("\nPurchase Menu cancelled");
				externalPane.setVisible(false);
		         	externalPane.setDisable(true);
		         	allSlotBox.setDisable(false);
			});
			closeBtn2.setOnMouseClicked(e->{
				System.out.println("Upgrade menu cancelled");
				externalPane2.setVisible(false);
				externalPane2.setDisable(true);
		         	allSlotBox.setDisable(false);
			});
	         
	         
			switch (slotCount3) {
			case 0:
				allSlotBox.setDisable(true);
				externalPane.setVisible(true);
		        externalPane.setDisable(false);
				selectSlot1.setOnMouseClicked(new EventHandler<MouseEvent>()
				{
	               
					public void handle(MouseEvent t) {
	         			
						System.out.println("Selected Skeleton Mine");
						if(currentGold>=skeletonMine.getCost()) {
	                	
	   		         	externalPane.setVisible(false);
	   		         	externalPane.setDisable(true);
	   		         	allSlotBox.setDisable(false);
	   		         	shopSlot3.setViewport(skelMine.getViewport());
	   		         	
	   		         	//Affects Income/s
	   		         	totalIncome+=skeletonMine.getIncome();
	   		         	currentGold-=skeletonMine.getCost();
	   		         	goldDisplay.setText(""+currentGold);
	   		         	
	   		         	//Affects Shop TIER and UPGRADING/ SELLING
	   		         	slotCount3+=1;
	   		         	System.out.println("\n"+slotCount3);
	   		         	slot3Upgrade=skeletonMine.getName();
	   		         	System.out.println("Bought "+slot3Upgrade);
	   		         	}
	                	else {
	                		insufficientGold(skeletonMine.getCost());
	                		externalPane.setVisible(false);
		   		         	externalPane.setDisable(true);
		   		         	allSlotBox.setDisable(false);
	                	}
	   		         	t.consume();
	                }
	            });
	         	selectSlot2.setOnMouseClicked(new EventHandler<MouseEvent>()
	            {
	               
	         		public void handle(MouseEvent t) {
	         			
	                	System.out.println("Selected Goblin Bar");
	                	if(currentGold>=goblinBar.getCost()) {
	                		
	                	externalPane.setVisible(false);
		   		        externalPane.setDisable(true);
		   		        allSlotBox.setDisable(false);
		   		        shopSlot3.setViewport(gobBar.getViewport());
	   		         	
	   		         	totalIncome+=goblinBar.getIncome();
	   		         	currentGold-=goblinBar.getCost();
	   		         	goldDisplay.setText(""+currentGold);
	   		         	
	   		         	slotCount3+=1;
	   		         	System.out.println("\n"+slotCount3);
	   		         	slot3Upgrade=goblinBar.getName();
	   		         	System.out.println("Bought "+slot3Upgrade);
	   		         	}
	                	else {
	                		insufficientGold(goblinBar.getCost());
	                		externalPane.setVisible(false);
		   		         	externalPane.setDisable(true);
		   		         	allSlotBox.setDisable(false);
	                	}
	   		         	t.consume();
	                }
	            });
	         	
	         	selectSlot3.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						System.out.println("Selected Lottery Den");
						if(currentGold>=lotteryDen.getCost()) {
							
							externalPane.setVisible(false);
			   		        externalPane.setDisable(true);
			   		        allSlotBox.setDisable(false);
			   		        shopSlot3.setViewport(lottDen.getViewport());
							
							totalIncome+=lotteryDen.getIncome();
							currentGold-=lotteryDen.getCost();
							goldDisplay.setText(""+currentGold);
							
							slotCount3+=1;
		   		         	System.out.println("\nShop Level:"+slotCount3);
		   		         	slot3Upgrade=lotteryDen.getName();
		   		         	System.out.println("Bought "+slot3Upgrade);
						}
						else {
							insufficientGold(lotteryDen.getCost());
							externalPane.setVisible(false);
							externalPane.setDisable(true);
							allSlotBox.setDisable(false);
						}
						
					}

				});
				
				break;
			case 1:
				allSlotBox.setDisable(true);
				externalPane2.setVisible(true);
		        externalPane2.setDisable(false);
		        
		        switch (slot3Upgrade) {
				case "skeletonMine":
					upgradeSlot.setViewport(anciForge.getViewport());
						upgradeSlot.setOnMouseClicked(e->{
							
							System.out.println("Upgrade to "+ancientForge.getName());
							if(currentGold>=ancientForge.getCost()) {
								externalPane2.setVisible(false);
			   		         	externalPane2.setDisable(true);
			   		         	allSlotBox.setDisable(false);
			   		         	shopSlot3.setViewport(anciForge.getViewport());
								
								totalIncome+=ancientForge.getIncome();
								currentGold-=ancientForge.getCost();
								goldDisplay.setText(""+currentGold);
								
								slotCount3+=1;
			   		         	System.out.println("\n"+slotCount3);
			   		         	slot3Upgrade=ancientForge.getName();
								System.out.println("\nUpgraded to "+slot3Upgrade);
								
							}
							else {
								insufficientGold(ancientForge.getCost());
								externalPane2.setVisible(false);
			   		         	externalPane2.setDisable(true);
			   		         	allSlotBox.setDisable(false);
							}
						});
						
						sellSlot.setOnMouseClicked(e->{
							
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=skeletonMine.getIncome();
							currentGold+=skeletonMine.getCost();
							goldDisplay.setText(""+currentGold);
							
							slotCount3=0;
							System.out.println("Sold "+slot3Upgrade);
							slot3Upgrade="";
							System.out.println("Reset Slot\n");
							
							externalPane2.setVisible(false);
		   		         	externalPane2.setDisable(true);
		   		         	allSlotBox.setDisable(false);
		   		         	shopSlot3.setViewport(new Rectangle2D(0, 257, 128, 128));
						});
						
					break;

				case "goblinBar":
					upgradeSlot.setViewport(drgnSpa.getViewport());
						upgradeSlot.setOnMouseClicked(e->{
						
							System.out.println("Upgrade to "+dragonSpa.getName());
							if(currentGold>=dragonSpa.getCost()) {
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
								shopSlot3.setViewport(drgnSpa.getViewport());
							
								totalIncome+=dragonSpa.getIncome();
								currentGold-=dragonSpa.getCost();
								goldDisplay.setText(""+currentGold);
							
								slotCount3+=1;
								System.out.println("\n"+slotCount3);
								slot3Upgrade=dragonSpa.getName();
								System.out.println("\nUpgraded to "+slot3Upgrade);
							
							}
							else {
							insufficientGold(dragonSpa.getCost());
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
							}
						});
					
						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=goblinBar.getIncome();
							currentGold+=goblinBar.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount3=0;
							System.out.println("Sold "+slot3Upgrade);
							slot3Upgrade="";
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot3.setViewport(new Rectangle2D(0, 257, 128, 128));
						});
					break;
					
				case "lotteryDen":
					upgradeSlot.setViewport(casiDen.getViewport());
						upgradeSlot.setOnMouseClicked(e->{
						
							System.out.println("Upgrade to "+casinoDen.getName());
							if(currentGold>=casinoDen.getCost()) {
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
								shopSlot3.setViewport(casiDen.getViewport());
							
								totalIncome+=casinoDen.getIncome();
								currentGold-=casinoDen.getCost();
								goldDisplay.setText(""+currentGold);
							
								slotCount3+=1;
								System.out.println("\n"+slotCount3);
								slot3Upgrade=casinoDen.getName();
								System.out.println("\nUpgraded to "+slot3Upgrade);
							
							}
							else {
								insufficientGold(casinoDen.getCost());
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
							}
						});
						
						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=lotteryDen.getIncome();
							currentGold+=lotteryDen.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount3=0;
							System.out.println("Sold "+slot3Upgrade);
							slot3Upgrade="";
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot3.setViewport(new Rectangle2D(0, 257, 128, 128));
						});
					break;
				}
		        
				break;
			case 2:
				System.out.println("Final shop tier");
				allSlotBox.setDisable(true);
				externalPane2.setVisible(true);
		        externalPane2.setDisable(false);
				
				switch (slot3Upgrade) {
				
				case "ancientForge":
					upgradeSlot.setViewport(necroAca.getViewport());
						upgradeSlot.setOnMouseClicked(e->{

							System.out.println("Upgrade to "+necroAcadamy.getName());
							if(currentGold>=necroAcadamy.getCost()) {
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
		   		         		shopSlot3.setDisable(true);
		   		         		shopSlot3.setViewport(necroAca.getViewport());
		   		         		
		   		         		//Necro Acadamy refunds 50% of its cost after building
		   		         		totalIncome+=necroAcadamy.getIncome();
		   		         		currentGold-=(int)(necroAcadamy.getCost()*(50.0f/100.0f));
		   		         		goldDisplay.setText(""+currentGold);
							
		   		         		slotCount3=3;
		   		         		System.out.println("\n"+slotCount3);
		   		         		slot3Upgrade=necroAcadamy.getName();
		   		         		System.out.println("\nUpgraded to "+slot3Upgrade);
		   		         		
							}
							else {
								insufficientGold(necroAcadamy.getCost());
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
							}
						});

						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=ancientForge.getIncome();
							currentGold+=ancientForge.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount3=1;
							System.out.println("Sold "+slot3Upgrade);
							slot3Upgrade=skeletonMine.getName();
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot3.setViewport(skelMine.getViewport());
						});
					break;
					
				case "dragonSpa":
					upgradeSlot.setViewport(monResort.getViewport());
					upgradeSlot.setOnMouseClicked(e->{

						System.out.println("Upgrade to "+monsterResort.getName());
						if(currentGold>=monsterResort.getCost()) {
							externalPane2.setVisible(false);
	   		         		externalPane2.setDisable(true);
	   		         		allSlotBox.setDisable(false);
	   		         		shopSlot3.setDisable(true);
	   		         		shopSlot3.setViewport(monResort.getViewport());
	   		         		
	   		         		//Monster Resort gives 10% bonus to overall income
	   		         		totalIncome+=monsterResort.getIncome();
	   		         		totalIncome=(int)(totalIncome*(110.0f/100.0f));
	   		         		currentGold-=monsterResort.getCost();
	   		         		goldDisplay.setText(""+currentGold);
						
	   		         		slotCount3=3;
	   		         		System.out.println("\n"+slotCount3);
	   		         		slot3Upgrade=monsterResort.getName();
	   		         		System.out.println("\nUpgraded to "+slot3Upgrade);
	   		         		
						}
						else {
							insufficientGold(monsterResort.getCost());
							externalPane2.setVisible(false);
	   		         		externalPane2.setDisable(true);
	   		         		allSlotBox.setDisable(false);
						}
					});

					sellSlot.setOnMouseClicked(e->{
					
						System.out.println("Selling "+slot1Upgrade);
						totalIncome-=dragonSpa.getIncome();
						currentGold+=dragonSpa.getCost();
						goldDisplay.setText(""+currentGold);
					
						slotCount3=1;
						System.out.println("Sold "+slot3Upgrade);
						slot3Upgrade=goblinBar.getName();
						System.out.println("Reset Slot\n");
					
						externalPane2.setVisible(false);
						externalPane2.setDisable(true);
						allSlotBox.setDisable(false);
						shopSlot3.setViewport(gobBar.getViewport());
					});
					break;
				
				case "casinoDen":
					upgradeSlot.setViewport(UcasiDen.getViewport());
					upgradeSlot.setOnMouseClicked(e->{

						System.out.println("Upgrade to "+ultracasinoDen.getName());
						if(currentGold>=ultracasinoDen.getCost()) {
							externalPane2.setVisible(false);
	   		         		externalPane2.setDisable(true);
	   		         		allSlotBox.setDisable(false);
	   		         		shopSlot3.setDisable(true);
	   		         		shopSlot3.setViewport(UcasiDen.getViewport());
	   		         		
		   		         		//Ultra Casino gives a massive 30% boost to overall income
	   		         			totalIncome+=ultracasinoDen.getIncome();
	   		         			totalIncome=(int)(totalIncome*(130.0f/100.0f));
	   		        	 		currentGold-=ultracasinoDen.getCost();
	   		    	     		goldDisplay.setText(""+currentGold);
						
	   			         		slotCount3=3;
	   			         		System.out.println("\n"+slotCount3);
		   		         		slot3Upgrade=ultracasinoDen.getName();
		   		         		System.out.println("\nUpgraded to "+slot3Upgrade);
	   		         		
							}
							else {
								insufficientGold(ultracasinoDen.getCost());
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
							}
						});

						sellSlot.setOnMouseClicked(e->{
					
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=casinoDen.getIncome();
							currentGold+=casinoDen.getCost();
							goldDisplay.setText(""+currentGold);
					
							slotCount3=1;
							System.out.println("Sold "+slot3Upgrade);
							slot3Upgrade=lotteryDen.getName();
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot3.setViewport(lottDen.getViewport());
						});
						break;
					}
				
					break;
				}
		        event.consume();
	         
		     }
		});
	}
	
	private void shopEvent4() {
		shopSlot4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

		public void handle(MouseEvent event) {
			System.out.println("Tile pressed ");
	         
			closeBtn.setOnMouseClicked(e->{
				System.out.println("\nPurchase Menu cancelled");
				externalPane.setVisible(false);
		         	externalPane.setDisable(true);
		         	allSlotBox.setDisable(false);
			});
			closeBtn2.setOnMouseClicked(e->{
				System.out.println("Upgrade menu cancelled");
				externalPane2.setVisible(false);
				externalPane2.setDisable(true);
		         	allSlotBox.setDisable(false);
			});
	         
	         
			switch (slotCount4) {
			case 0:
				allSlotBox.setDisable(true);
				externalPane.setVisible(true);
		        externalPane.setDisable(false);
				selectSlot1.setOnMouseClicked(new EventHandler<MouseEvent>()
				{
	               
					public void handle(MouseEvent t) {
	         			
						System.out.println("Selected Skeleton Mine");
						if(currentGold>=(int)(skeletonMine.getCost()*(slot4PriceIncrease/100.0f))) {
	                	
	   		         	externalPane.setVisible(false);
	   		         	externalPane.setDisable(true);
	   		         	allSlotBox.setDisable(false);
	   		         	shopSlot4.setViewport(skelMine.getViewport());
	   		         	
	   		         	//Affects Income/s
	   		         	totalIncome+=skeletonMine.getIncome();
	   		         	currentGold-=(int)(skeletonMine.getCost()*(slot4PriceIncrease/100.0f));
	   		         	goldDisplay.setText(""+currentGold);
	   		         	
	   		         	//Affects Shop TIER and UPGRADING/ SELLING
	   		         	slotCount4+=1;
	   		         	System.out.println("\n"+slotCount4);
	   		         	slot4Upgrade=skeletonMine.getName();
	   		         	System.out.println("Bought "+slot4Upgrade);
	   		         	}
	                	else {
	                		insufficientGold((int)(skeletonMine.getCost()*(slot4PriceIncrease/100.0f)));
	                		externalPane.setVisible(false);
		   		         	externalPane.setDisable(true);
		   		         	allSlotBox.setDisable(false);
	                	}
	   		         	t.consume();
	                }
	            });
	         	selectSlot2.setOnMouseClicked(new EventHandler<MouseEvent>()
	            {
	               
	         		public void handle(MouseEvent t) {
	         			
	                	System.out.println("Selected Goblin Bar");
	                	if(currentGold>=(int)(goblinBar.getCost()*(slot4PriceIncrease/100.0f))) {
	                		
	                	externalPane.setVisible(false);
		   		        externalPane.setDisable(true);
		   		        allSlotBox.setDisable(false);
		   		        shopSlot4.setViewport(gobBar.getViewport());
	   		         	
	   		         	totalIncome+=goblinBar.getIncome();
	   		         	currentGold-=(int)(goblinBar.getCost()*(slot4PriceIncrease/100.0f));
	   		         	goldDisplay.setText(""+currentGold);
	   		         	
	   		         	slotCount4+=1;
	   		         	System.out.println("\n"+slotCount4);
	   		         	slot4Upgrade=goblinBar.getName();
	   		         	System.out.println("Bought "+slot4Upgrade);
	   		         	}
	                	else {
	                		insufficientGold((int)(goblinBar.getCost()*(slot4PriceIncrease/100.0f)));
	                		externalPane.setVisible(false);
		   		         	externalPane.setDisable(true);
		   		         	allSlotBox.setDisable(false);
	                	}
	   		         	t.consume();
	                }
	            });
	         	
	         	selectSlot3.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						System.out.println("Selected Lottery Den");
						if(currentGold>=(int)(lotteryDen.getCost()*(slot4PriceIncrease/100.0f))) {
							
							externalPane.setVisible(false);
			   		        externalPane.setDisable(true);
			   		        allSlotBox.setDisable(false);
			   		        shopSlot4.setViewport(lottDen.getViewport());
							
							totalIncome+=lotteryDen.getIncome();
							currentGold-=(int)(lotteryDen.getCost()*(slot4PriceIncrease/100.0f));
							goldDisplay.setText(""+currentGold);
							
							slotCount4+=1;
		   		         	System.out.println("\nShop Level:"+slotCount4);
		   		         	slot4Upgrade=lotteryDen.getName();
		   		         	System.out.println("Bought "+slot4Upgrade);
						}
						else {
							insufficientGold((int)(lotteryDen.getCost()*(slot4PriceIncrease/100.0f)));
							externalPane.setVisible(false);
							externalPane.setDisable(true);
							allSlotBox.setDisable(false);
						}
						
					}

				});
				
				break;
			case 1:
				allSlotBox.setDisable(true);
				externalPane2.setVisible(true);
		        externalPane2.setDisable(false);
		        
		        switch (slot4Upgrade) {
				case "skeletonMine":
					upgradeSlot.setViewport(anciForge.getViewport());
						upgradeSlot.setOnMouseClicked(e->{
							
							System.out.println("Upgrade to "+ancientForge.getName());
							if(currentGold>=ancientForge.getCost()) {
								externalPane2.setVisible(false);
			   		         	externalPane2.setDisable(true);
			   		         	allSlotBox.setDisable(false);
			   		         	shopSlot4.setViewport(anciForge.getViewport());
								
								totalIncome+=ancientForge.getIncome();
								currentGold-=ancientForge.getCost();
								goldDisplay.setText(""+currentGold);
								
								slotCount4+=1;
			   		         	System.out.println("\n"+slotCount4);
			   		         	slot4Upgrade=ancientForge.getName();
								System.out.println("\nUpgraded to "+slot4Upgrade);
								
							}
							else {
								insufficientGold(ancientForge.getCost());
								externalPane2.setVisible(false);
			   		         	externalPane2.setDisable(true);
			   		         	allSlotBox.setDisable(false);
							}
						});
						
						sellSlot.setOnMouseClicked(e->{
							
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=skeletonMine.getIncome();
							currentGold+=skeletonMine.getCost();
							goldDisplay.setText(""+currentGold);
							
							slotCount4=0;
							System.out.println("Sold "+slot4Upgrade);
							slot4Upgrade="";
							System.out.println("Reset Slot\n");
							
							externalPane2.setVisible(false);
		   		         	externalPane2.setDisable(true);
		   		         	allSlotBox.setDisable(false);
		   		         	shopSlot4.setViewport(new Rectangle2D(0, 257, 128, 128));
						});
						
					break;

				case "goblinBar":
					upgradeSlot.setViewport(drgnSpa.getViewport());
						upgradeSlot.setOnMouseClicked(e->{
						
							System.out.println("Upgrade to "+dragonSpa.getName());
							if(currentGold>=dragonSpa.getCost()) {
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
								shopSlot4.setViewport(drgnSpa.getViewport());
							
								totalIncome+=dragonSpa.getIncome();
								currentGold-=dragonSpa.getCost();
								goldDisplay.setText(""+currentGold);
							
								slotCount4+=1;
								System.out.println("\n"+slotCount4);
								slot4Upgrade=dragonSpa.getName();
								System.out.println("\nUpgraded to "+slot4Upgrade);
							
							}
							else {
								insufficientGold(dragonSpa.getCost());
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
							}
						});
					
						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=goblinBar.getIncome();
							currentGold+=goblinBar.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount4=0;
							System.out.println("Sold "+slot4Upgrade);
							slot4Upgrade="";
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot4.setViewport(new Rectangle2D(0, 257, 128, 128));
						});
					break;
					
				case "lotteryDen":
					upgradeSlot.setViewport(casiDen.getViewport());
						upgradeSlot.setOnMouseClicked(e->{
						
							System.out.println("Upgrade to "+casinoDen.getName());
							if(currentGold>=casinoDen.getCost()) {
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
								shopSlot4.setViewport(casiDen.getViewport());
							
								totalIncome+=casinoDen.getIncome();
								currentGold-=casinoDen.getCost();
								goldDisplay.setText(""+currentGold);
							
								slotCount4+=1;
								System.out.println("\n"+slotCount4);
								slot4Upgrade=casinoDen.getName();
								System.out.println("\nUpgraded to "+slot4Upgrade);
							
							}
							else {
								insufficientGold(casinoDen.getCost());
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
							}
						});
						
						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=lotteryDen.getIncome();
							currentGold+=lotteryDen.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount4=0;
							System.out.println("Sold "+slot4Upgrade);
							slot4Upgrade="";
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot4.setViewport(new Rectangle2D(0, 257, 128, 128));
						});
					break;
				}
		        
				break;
			case 2:
				System.out.println("Final shop tier");
				allSlotBox.setDisable(true);
				externalPane2.setVisible(true);
		        externalPane2.setDisable(false);
				
				switch (slot4Upgrade) {
				
				case "ancientForge":
					upgradeSlot.setViewport(necroAca.getViewport());
						upgradeSlot.setOnMouseClicked(e->{

							System.out.println("Upgrade to "+necroAcadamy.getName());
							if(currentGold>=necroAcadamy.getCost()) {
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
		   		         		shopSlot4.setDisable(true);
		   		         		shopSlot4.setViewport(necroAca.getViewport());
		   		         		
		   		         		//Necro Acadamy refunds 50% of its cost after building
		   		         		totalIncome+=necroAcadamy.getIncome();
		   		         		currentGold-=(int)(necroAcadamy.getCost()*(50.0f/100.0f));
		   		         		goldDisplay.setText(""+currentGold);
							
		   		         		slotCount4=3;
		   		         		System.out.println("\n"+slotCount4);
		   		         		slot4Upgrade=necroAcadamy.getName();
		   		         		System.out.println("\nUpgraded to "+slot4Upgrade);
		   		         		
							}
							else {
								insufficientGold(necroAcadamy.getCost());
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
							}
						});

						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=ancientForge.getIncome();
							currentGold+=ancientForge.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount4=1;
							System.out.println("Sold "+slot4Upgrade);
							slot4Upgrade=skeletonMine.getName();
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot4.setViewport(skelMine.getViewport());
						});
					break;
					
				case "dragonSpa":
					upgradeSlot.setViewport(monResort.getViewport());
					upgradeSlot.setOnMouseClicked(e->{

						System.out.println("Upgrade to "+monsterResort.getName());
						if(currentGold>=monsterResort.getCost()) {
							externalPane2.setVisible(false);
	   		         		externalPane2.setDisable(true);
	   		         		allSlotBox.setDisable(false);
	   		         		shopSlot4.setDisable(true);
	   		         		shopSlot4.setViewport(monResort.getViewport());
	   		         		
	   		         		//Monster Resort gives 10% bonus to overall income
	   		         		totalIncome+=monsterResort.getIncome();
	   		         		totalIncome=(int)(totalIncome*(110.0f/100.0f));
	   		         		currentGold-=monsterResort.getCost();
	   		         		goldDisplay.setText(""+currentGold);
						
	   		         		slotCount4=3;
	   		         		System.out.println("\n"+slotCount4);
	   		         		slot4Upgrade=monsterResort.getName();
	   		         		System.out.println("\nUpgraded to "+slot4Upgrade);
	   		         		
						}
						else {
							insufficientGold(monsterResort.getCost());
							externalPane2.setVisible(false);
	   		         		externalPane2.setDisable(true);
	   		         		allSlotBox.setDisable(false);
						}
					});

					sellSlot.setOnMouseClicked(e->{
					
						System.out.println("Selling "+slot1Upgrade);
						totalIncome-=dragonSpa.getIncome();
						currentGold+=dragonSpa.getCost();
						goldDisplay.setText(""+currentGold);
					
						slotCount4=1;
						System.out.println("Sold "+slot4Upgrade);
						slot4Upgrade=goblinBar.getName();
						System.out.println("Reset Slot\n");
					
						externalPane2.setVisible(false);
						externalPane2.setDisable(true);
						allSlotBox.setDisable(false);
						shopSlot4.setViewport(gobBar.getViewport());
					});
					break;
				
				case "casinoDen":
					upgradeSlot.setViewport(UcasiDen.getViewport());
					upgradeSlot.setOnMouseClicked(e->{

						System.out.println("Upgrade to "+ultracasinoDen.getName());
						if(currentGold>=ultracasinoDen.getCost()) {
							externalPane2.setVisible(false);
	   		         		externalPane2.setDisable(true);
	   		         		allSlotBox.setDisable(false);
	   		         		shopSlot4.setDisable(true);
	   		         		shopSlot4.setViewport(UcasiDen.getViewport());
	   		         		
		   		         		//Ultra Casino gives a massive 30% boost to overall income
	   		         			totalIncome+=ultracasinoDen.getIncome();
	   		         			totalIncome=(int)(totalIncome*(130.0f/100.0f));
	   		        	 		currentGold-=ultracasinoDen.getCost();
	   		    	     		goldDisplay.setText(""+currentGold);
						
	   			         		slotCount4=3;
	   			         		System.out.println("\n"+slotCount4);
		   		         		slot4Upgrade=ultracasinoDen.getName();
		   		         		System.out.println("\nUpgraded to "+slot4Upgrade);
	   		         		
							}
							else {
								insufficientGold(ultracasinoDen.getCost());
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
							}
						});

						sellSlot.setOnMouseClicked(e->{
					
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=casinoDen.getIncome();
							currentGold+=casinoDen.getCost();
							goldDisplay.setText(""+currentGold);
					
							slotCount4=1;
							System.out.println("Sold "+slot4Upgrade);
							slot4Upgrade=lotteryDen.getName();
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot4.setViewport(lottDen.getViewport());
						});
						break;
					}
				
					break;
				}
		        event.consume();
	         
		     }
		});
	}
	
	private void shopEvent5() {
		shopSlot5.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

		public void handle(MouseEvent event) {
			System.out.println("Tile pressed ");
	         
			closeBtn.setOnMouseClicked(e->{
				System.out.println("\nPurchase Menu cancelled");
				externalPane.setVisible(false);
		         	externalPane.setDisable(true);
		         	allSlotBox.setDisable(false);
			});
			closeBtn2.setOnMouseClicked(e->{
				System.out.println("Upgrade menu cancelled");
				externalPane2.setVisible(false);
				externalPane2.setDisable(true);
		         	allSlotBox.setDisable(false);
			});
	         
	         
			switch (slotCount5) {
			case 0:
				allSlotBox.setDisable(true);
				externalPane.setVisible(true);
		        externalPane.setDisable(false);
				selectSlot1.setOnMouseClicked(new EventHandler<MouseEvent>()
				{
	               
					public void handle(MouseEvent t) {
	         			
						System.out.println("Selected Skeleton Mine");
						if(currentGold>=(int)(skeletonMine.getCost()*(slot5PriceIncrease/100.0f))) {
	                	
	   		         	externalPane.setVisible(false);
	   		         	externalPane.setDisable(true);
	   		         	allSlotBox.setDisable(false);
	   		         	shopSlot5.setViewport(skelMine.getViewport());
	   		         	
	   		         	//Affects Income/s
	   		         	totalIncome+=skeletonMine.getIncome();
	   		         	currentGold-=(int)(skeletonMine.getCost()*(slot5PriceIncrease/100.0f));
	   		         	goldDisplay.setText(""+currentGold);
	   		         	
	   		         	//Affects Shop TIER and UPGRADING/ SELLING
	   		         	slotCount5+=1;
	   		         	System.out.println("\n"+slotCount5);
	   		         	slot5Upgrade=skeletonMine.getName();
	   		         	System.out.println("Bought "+slot5Upgrade);
	   		         	}
	                	else {
	                		insufficientGold((int)(skeletonMine.getCost()*(slot5PriceIncrease/100.0f)));
	                		externalPane.setVisible(false);
		   		         	externalPane.setDisable(true);
		   		         	allSlotBox.setDisable(false);
	                	}
	   		         	t.consume();
	                }
	            });
	         	selectSlot2.setOnMouseClicked(new EventHandler<MouseEvent>()
	            {
	               
	         		public void handle(MouseEvent t) {
	         			
	                	System.out.println("Selected Goblin Bar");
	                	if(currentGold>=(int)(goblinBar.getCost()*(slot5PriceIncrease/100.0f))) {
	                		
	                	externalPane.setVisible(false);
		   		        externalPane.setDisable(true);
		   		        allSlotBox.setDisable(false);
		   		        shopSlot5.setViewport(gobBar.getViewport());
	   		         	
	   		         	totalIncome+=goblinBar.getIncome();
	   		         	currentGold-=(int)(goblinBar.getCost()*(slot5PriceIncrease/100.0f));
	   		         	goldDisplay.setText(""+currentGold);
	   		         	
	   		         	slotCount5+=1;
	   		         	System.out.println("\n"+slotCount5);
	   		         	slot5Upgrade=goblinBar.getName();
	   		         	System.out.println("Bought "+slot5Upgrade);
	   		         	}
	                	else {
	                		insufficientGold((int)(goblinBar.getCost()*(slot5PriceIncrease/100.0f)));
	                		externalPane.setVisible(false);
		   		         	externalPane.setDisable(true);
		   		         	allSlotBox.setDisable(false);
	                	}
	   		         	t.consume();
	                }
	            });
	         	
	         	selectSlot3.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						System.out.println("Selected Lottery Den");
						if(currentGold>=(int)(lotteryDen.getCost()*(slot5PriceIncrease/100.0f))) {
							
							externalPane.setVisible(false);
			   		        externalPane.setDisable(true);
			   		        allSlotBox.setDisable(false);
			   		        shopSlot5.setViewport(lottDen.getViewport());
							
							totalIncome+=lotteryDen.getIncome();
							currentGold-=(int)(lotteryDen.getCost()*(slot5PriceIncrease/100.0f));
							goldDisplay.setText(""+currentGold);
							
							slotCount5+=1;
		   		         	System.out.println("\nShop Level:"+slotCount5);
		   		         	slot5Upgrade=lotteryDen.getName();
		   		         	System.out.println("Bought "+slot5Upgrade);
						}
						else {
							insufficientGold((int)(lotteryDen.getCost()*(slot5PriceIncrease/100.0f)));
							externalPane.setVisible(false);
							externalPane.setDisable(true);
							allSlotBox.setDisable(false);
						}
						
					}

				});
				
				break;
			case 1:
				allSlotBox.setDisable(true);
				externalPane2.setVisible(true);
		        externalPane2.setDisable(false);
		        
		        switch (slot5Upgrade) {
				case "skeletonMine":
					upgradeSlot.setViewport(anciForge.getViewport());
						upgradeSlot.setOnMouseClicked(e->{
							
							System.out.println("Upgrade to "+ancientForge.getName());
							if(currentGold>=ancientForge.getCost()) {
								externalPane2.setVisible(false);
			   		         	externalPane2.setDisable(true);
			   		         	allSlotBox.setDisable(false);
			   		         	shopSlot5.setViewport(anciForge.getViewport());
								
								totalIncome+=ancientForge.getIncome();
								currentGold-=ancientForge.getCost();
								goldDisplay.setText(""+currentGold);
								
								slotCount5+=1;
			   		         	System.out.println("\n"+slotCount5);
			   		         	slot5Upgrade=ancientForge.getName();
								System.out.println("\nUpgraded to "+slot5Upgrade);
								
							}
							else {
								insufficientGold(ancientForge.getCost());
								externalPane2.setVisible(false);
			   		         	externalPane2.setDisable(true);
			   		         	allSlotBox.setDisable(false);
							}
						});
						
						sellSlot.setOnMouseClicked(e->{
							
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=skeletonMine.getIncome();
							currentGold+=skeletonMine.getCost();
							goldDisplay.setText(""+currentGold);
							
							slotCount5=0;
							System.out.println("Sold "+slot5Upgrade);
							slot5Upgrade="";
							System.out.println("Reset Slot\n");
							
							externalPane2.setVisible(false);
		   		         	externalPane2.setDisable(true);
		   		         	allSlotBox.setDisable(false);
		   		         	shopSlot5.setViewport(new Rectangle2D(0, 257, 128, 128));
						});
						
					break;

				case "goblinBar":
					upgradeSlot.setViewport(drgnSpa.getViewport());
						upgradeSlot.setOnMouseClicked(e->{
						
							System.out.println("Upgrade to "+dragonSpa.getName());
							if(currentGold>=dragonSpa.getCost()) {
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
								shopSlot5.setViewport(drgnSpa.getViewport());
							
								totalIncome+=dragonSpa.getIncome();
								currentGold-=dragonSpa.getCost();
								goldDisplay.setText(""+currentGold);
							
								slotCount5+=1;
								System.out.println("\n"+slotCount5);
								slot5Upgrade=dragonSpa.getName();
								System.out.println("\nUpgraded to "+slot5Upgrade);
							
							}
							else {
								insufficientGold(dragonSpa.getCost());
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
							}
						});
					
						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=goblinBar.getIncome();
							currentGold+=goblinBar.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount5=0;
							System.out.println("Sold "+slot5Upgrade);
							slot5Upgrade="";
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot5.setViewport(new Rectangle2D(0, 257, 128, 128));
						});
					break;
					
				case "lotteryDen":
					upgradeSlot.setViewport(casiDen.getViewport());
						upgradeSlot.setOnMouseClicked(e->{
						
							System.out.println("Upgrade to "+casinoDen.getName());
							if(currentGold>=casinoDen.getCost()) {
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
								shopSlot5.setViewport(casiDen.getViewport());
							
								totalIncome+=casinoDen.getIncome();
								currentGold-=casinoDen.getCost();
								goldDisplay.setText(""+currentGold);
							
								slotCount5+=1;
								System.out.println("\n"+slotCount5);
								slot5Upgrade=casinoDen.getName();
								System.out.println("\nUpgraded to "+slot5Upgrade);
							
							}
							else {
								insufficientGold(casinoDen.getCost());
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
							}
						});
						
						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=lotteryDen.getIncome();
							currentGold+=lotteryDen.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount5=0;
							System.out.println("Sold "+slot5Upgrade);
							slot5Upgrade="";
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot5.setViewport(new Rectangle2D(0, 257, 128, 128));
						});
					break;
				}
		        
				break;
			case 2:
				System.out.println("Final shop tier");
				allSlotBox.setDisable(true);
				externalPane2.setVisible(true);
		        externalPane2.setDisable(false);
				
				switch (slot5Upgrade) {
				
				case "ancientForge":
					upgradeSlot.setViewport(necroAca.getViewport());
						upgradeSlot.setOnMouseClicked(e->{

							System.out.println("Upgrade to "+necroAcadamy.getName());
							if(currentGold>=necroAcadamy.getCost()) {
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
		   		         		shopSlot5.setDisable(true);
		   		         		shopSlot5.setViewport(necroAca.getViewport());
		   		         		
		   		         		//Necro Acadamy refunds 50% of its cost after building
		   		         		totalIncome+=necroAcadamy.getIncome();
		   		         		currentGold-=(int)(necroAcadamy.getCost()*(50.0f/100.0f));
		   		         		goldDisplay.setText(""+currentGold);
							
		   		         		slotCount5=3;
		   		         		System.out.println("\n"+slotCount5);
		   		         		slot5Upgrade=necroAcadamy.getName();
		   		         		System.out.println("\nUpgraded to "+slot5Upgrade);
		   		         		
							}
							else {
								insufficientGold(necroAcadamy.getCost());
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
							}
						});

						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=ancientForge.getIncome();
							currentGold+=ancientForge.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount5=1;
							System.out.println("Sold "+slot5Upgrade);
							slot5Upgrade=skeletonMine.getName();
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot5.setViewport(skelMine.getViewport());
						});
					break;
					
				case "dragonSpa":
					upgradeSlot.setViewport(monResort.getViewport());
					upgradeSlot.setOnMouseClicked(e->{

						System.out.println("Upgrade to "+monsterResort.getName());
						if(currentGold>=monsterResort.getCost()) {
							externalPane2.setVisible(false);
	   		         		externalPane2.setDisable(true);
	   		         		allSlotBox.setDisable(false);
	   		         		shopSlot5.setDisable(true);
	   		         		shopSlot5.setViewport(monResort.getViewport());
	   		         		
	   		         		//Monster Resort gives 10% bonus to overall income
	   		         		totalIncome+=monsterResort.getIncome();
	   		         		totalIncome=(int)(totalIncome*(110.0f/100.0f));
	   		         		currentGold-=monsterResort.getCost();
	   		         		goldDisplay.setText(""+currentGold);
						
	   		         		slotCount5=3;
	   		         		System.out.println("\n"+slotCount5);
	   		         		slot5Upgrade=monsterResort.getName();
	   		         		System.out.println("\nUpgraded to "+slot5Upgrade);
	   		         		
						}
						else {
							insufficientGold(monsterResort.getCost());
							externalPane2.setVisible(false);
	   		         		externalPane2.setDisable(true);
	   		         		allSlotBox.setDisable(false);
						}
					});

					sellSlot.setOnMouseClicked(e->{
					
						System.out.println("Selling "+slot1Upgrade);
						totalIncome-=dragonSpa.getIncome();
						currentGold+=dragonSpa.getCost();
						goldDisplay.setText(""+currentGold);
					
						slotCount5=1;
						System.out.println("Sold "+slot5Upgrade);
						slot5Upgrade=goblinBar.getName();
						System.out.println("Reset Slot\n");
					
						externalPane2.setVisible(false);
						externalPane2.setDisable(true);
						allSlotBox.setDisable(false);
						shopSlot5.setViewport(gobBar.getViewport());
					});
					break;
				
				case "casinoDen":
					upgradeSlot.setViewport(UcasiDen.getViewport());
					upgradeSlot.setOnMouseClicked(e->{

						System.out.println("Upgrade to "+ultracasinoDen.getName());
						if(currentGold>=ultracasinoDen.getCost()) {
							externalPane2.setVisible(false);
	   		         		externalPane2.setDisable(true);
	   		         		allSlotBox.setDisable(false);
	   		         		shopSlot5.setDisable(true);
	   		         		shopSlot5.setViewport(UcasiDen.getViewport());
	   		         		
		   		         		//Ultra Casino gives a massive 30% boost to overall income
	   		         			totalIncome+=ultracasinoDen.getIncome();
	   		         			totalIncome=(int)(totalIncome*(130.0f/100.0f));
	   		        	 		currentGold-=ultracasinoDen.getCost();
	   		    	     		goldDisplay.setText(""+currentGold);
						
	   			         		slotCount5=3;
	   			         		System.out.println("\n"+slotCount5);
		   		         		slot5Upgrade=ultracasinoDen.getName();
		   		         		System.out.println("\nUpgraded to "+slot5Upgrade);
	   		         		
							}
							else {
								insufficientGold(ultracasinoDen.getCost());
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
							}
						});

						sellSlot.setOnMouseClicked(e->{
					
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=casinoDen.getIncome();
							currentGold+=casinoDen.getCost();
							goldDisplay.setText(""+currentGold);
					
							slotCount5=1;
							System.out.println("Sold "+slot5Upgrade);
							slot5Upgrade=lotteryDen.getName();
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot5.setViewport(lottDen.getViewport());
						});
						break;
					}
				
					break;
				}
		        event.consume();
	         
		     }
		});
	}
	
	private void shopEvent6() {
		shopSlot6.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

		public void handle(MouseEvent event) {
			System.out.println("Tile pressed ");
	         
			closeBtn.setOnMouseClicked(e->{
				System.out.println("\nPurchase Menu cancelled");
				externalPane.setVisible(false);
		         	externalPane.setDisable(true);
		         	allSlotBox.setDisable(false);
			});
			closeBtn2.setOnMouseClicked(e->{
				System.out.println("Upgrade menu cancelled");
				externalPane2.setVisible(false);
				externalPane2.setDisable(true);
		         	allSlotBox.setDisable(false);
			});
	         
	         
			switch (slotCount6) {
			case 0:
				allSlotBox.setDisable(true);
				externalPane.setVisible(true);
		        externalPane.setDisable(false);
				selectSlot1.setOnMouseClicked(new EventHandler<MouseEvent>()
				{
	               
					public void handle(MouseEvent t) {
	         			
						System.out.println("Selected Skeleton Mine");
						if(currentGold>=(int)(skeletonMine.getCost()*(slot6PriceIncrease/100.0f))) {
	                	
	   		         	externalPane.setVisible(false);
	   		         	externalPane.setDisable(true);
	   		         	allSlotBox.setDisable(false);
	   		         	shopSlot6.setViewport(skelMine.getViewport());
	   		         	
	   		         	//Affects Income/s
	   		         	totalIncome+=skeletonMine.getIncome();
	   		         	currentGold-=(int)(skeletonMine.getCost()*(slot6PriceIncrease/100.0f));
	   		         	goldDisplay.setText(""+currentGold);
	   		         	
	   		         	//Affects Shop TIER and UPGRADING/ SELLING
	   		         	slotCount6+=1;
	   		         	System.out.println("\n"+slotCount6);
	   		         	slot6Upgrade=skeletonMine.getName();
	   		         	System.out.println("Bought "+slot6Upgrade);
	   		         	}
	                	else {
	                		insufficientGold((int)(skeletonMine.getCost()*(slot6PriceIncrease/100.0f)));
	                		externalPane.setVisible(false);
		   		         	externalPane.setDisable(true);
		   		         	allSlotBox.setDisable(false);
	                	}
	   		         	t.consume();
	                }
	            });
	         	selectSlot2.setOnMouseClicked(new EventHandler<MouseEvent>()
	            {
	               
	         		public void handle(MouseEvent t) {
	         			
	                	System.out.println("Selected Goblin Bar");
	                	if(currentGold>=(int)(goblinBar.getCost()*(slot6PriceIncrease/100.0f))) {
	                		
	                	externalPane.setVisible(false);
		   		        externalPane.setDisable(true);
		   		        allSlotBox.setDisable(false);
		   		        shopSlot6.setViewport(gobBar.getViewport());
	   		         	
	   		         	totalIncome+=goblinBar.getIncome();
	   		         	currentGold-=(int)(goblinBar.getCost()*(slot6PriceIncrease/100.0f));
	   		         	goldDisplay.setText(""+currentGold);
	   		         	
	   		         	slotCount6+=1;
	   		         	System.out.println("\n"+slotCount6);
	   		         	slot6Upgrade=goblinBar.getName();
	   		         	System.out.println("Bought "+slot6Upgrade);
	   		         	}
	                	else {
	                		insufficientGold((int)(goblinBar.getCost()*(slot6PriceIncrease/100.0f)));
	                		externalPane.setVisible(false);
		   		         	externalPane.setDisable(true);
		   		         	allSlotBox.setDisable(false);
	                	}
	   		         	t.consume();
	                }
	            });
	         	
	         	selectSlot3.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						System.out.println("Selected Lottery Den");
						if(currentGold>=(int)(lotteryDen.getCost()*(slot6PriceIncrease/100.0f))) {
							
							externalPane.setVisible(false);
			   		        externalPane.setDisable(true);
			   		        allSlotBox.setDisable(false);
			   		        shopSlot6.setViewport(lottDen.getViewport());
							
							totalIncome+=lotteryDen.getIncome();
							currentGold-=(int)(lotteryDen.getCost()*(slot6PriceIncrease/100.0f));
							goldDisplay.setText(""+currentGold);
							
							slotCount6+=1;
		   		         	System.out.println("\nShop Level:"+slotCount6);
		   		         	slot6Upgrade=lotteryDen.getName();
		   		         	System.out.println("Bought "+slot6Upgrade);
						}
						else {
							insufficientGold((int)(lotteryDen.getCost()*(slot6PriceIncrease/100.0f)));
							externalPane.setVisible(false);
							externalPane.setDisable(true);
							allSlotBox.setDisable(false);
						}
						
					}

				});
				
				break;
			case 1:
				allSlotBox.setDisable(true);
				externalPane2.setVisible(true);
		        externalPane2.setDisable(false);
		        
		        switch (slot6Upgrade) {
				case "skeletonMine":
					upgradeSlot.setViewport(anciForge.getViewport());
						upgradeSlot.setOnMouseClicked(e->{
							
							System.out.println("Upgrade to "+ancientForge.getName());
							if(currentGold>=ancientForge.getCost()) {
								externalPane2.setVisible(false);
			   		         	externalPane2.setDisable(true);
			   		         	allSlotBox.setDisable(false);
			   		         	shopSlot6.setViewport(anciForge.getViewport());
								
								totalIncome+=ancientForge.getIncome();
								currentGold-=ancientForge.getCost();
								goldDisplay.setText(""+currentGold);
								
								slotCount6+=1;
			   		         	System.out.println("\n"+slotCount6);
			   		         	slot6Upgrade=ancientForge.getName();
								System.out.println("\nUpgraded to "+slot6Upgrade);
								
							}
							else {
								insufficientGold(ancientForge.getCost());
								externalPane2.setVisible(false);
			   		         	externalPane2.setDisable(true);
			   		         	allSlotBox.setDisable(false);
							}
						});
						
						sellSlot.setOnMouseClicked(e->{
							
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=skeletonMine.getIncome();
							currentGold+=skeletonMine.getCost();
							goldDisplay.setText(""+currentGold);
							
							slotCount6=0;
							System.out.println("Sold "+slot6Upgrade);
							slot6Upgrade="";
							System.out.println("Reset Slot\n");
							
							externalPane2.setVisible(false);
		   		         	externalPane2.setDisable(true);
		   		         	allSlotBox.setDisable(false);
		   		         	shopSlot6.setViewport(new Rectangle2D(0, 257, 128, 128));
						});
						
					break;

				case "goblinBar":
					upgradeSlot.setViewport(drgnSpa.getViewport());
						upgradeSlot.setOnMouseClicked(e->{
						
							System.out.println("Upgrade to "+dragonSpa.getName());
							if(currentGold>=dragonSpa.getCost()) {
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
								shopSlot6.setViewport(drgnSpa.getViewport());
							
								totalIncome+=dragonSpa.getIncome();
								currentGold-=dragonSpa.getCost();
								goldDisplay.setText(""+currentGold);
							
								slotCount6+=1;
								System.out.println("\n"+slotCount6);
								slot6Upgrade=dragonSpa.getName();
								System.out.println("\nUpgraded to "+slot6Upgrade);
							
							}
							else {
								insufficientGold(dragonSpa.getCost());
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
							}
						});
					
						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=goblinBar.getIncome();
							currentGold+=goblinBar.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount6=0;
							System.out.println("Sold "+slot6Upgrade);
							slot6Upgrade="";
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot6.setViewport(new Rectangle2D(0, 257, 128, 128));
						});
					break;
					
				case "lotteryDen":
					upgradeSlot.setViewport(casiDen.getViewport());
						upgradeSlot.setOnMouseClicked(e->{
						
							System.out.println("Upgrade to "+casinoDen.getName());
							if(currentGold>=casinoDen.getCost()) {
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
								shopSlot6.setViewport(casiDen.getViewport());
							
								totalIncome+=casinoDen.getIncome();
								currentGold-=casinoDen.getCost();
								goldDisplay.setText(""+currentGold);
							
								slotCount6+=1;
								System.out.println("\n"+slotCount6);
								slot6Upgrade=casinoDen.getName();
								System.out.println("\nUpgraded to "+slot6Upgrade);
							
							}
							else {
								insufficientGold(casinoDen.getCost());
								externalPane2.setVisible(false);
								externalPane2.setDisable(true);
								allSlotBox.setDisable(false);
							}
						});
						
						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=lotteryDen.getIncome();
							currentGold+=lotteryDen.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount6=0;
							System.out.println("Sold "+slot6Upgrade);
							slot6Upgrade="";
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot6.setViewport(new Rectangle2D(0, 257, 128, 128));
						});
					break;
				}
		        
				break;
			case 2:
				System.out.println("Final shop tier");
				allSlotBox.setDisable(true);
				externalPane2.setVisible(true);
		        externalPane2.setDisable(false);
				
				switch (slot6Upgrade) {
				
				case "ancientForge":
					upgradeSlot.setViewport(necroAca.getViewport());
						upgradeSlot.setOnMouseClicked(e->{

							System.out.println("Upgrade to "+necroAcadamy.getName());
							if(currentGold>=necroAcadamy.getCost()) {
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
		   		         		shopSlot6.setDisable(true);
		   		         		shopSlot6.setViewport(necroAca.getViewport());
		   		         		
		   		         		//Necro Acadamy refunds 50% of its cost after building
		   		         		totalIncome+=necroAcadamy.getIncome();
		   		         		currentGold-=(int)(necroAcadamy.getCost()*(50.0f/100.0f));
		   		         		goldDisplay.setText(""+currentGold);
							
		   		         		slotCount6=3;
		   		         		System.out.println("\n"+slotCount6);
		   		         		slot6Upgrade=necroAcadamy.getName();
		   		         		System.out.println("\nUpgraded to "+slot6Upgrade);
		   		         		
							}
							else {
								insufficientGold(necroAcadamy.getCost());
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
							}
						});

						sellSlot.setOnMouseClicked(e->{
						
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=ancientForge.getIncome();
							currentGold+=ancientForge.getCost();
							goldDisplay.setText(""+currentGold);
						
							slotCount6=1;
							System.out.println("Sold "+slot6Upgrade);
							slot6Upgrade=skeletonMine.getName();
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot6.setViewport(skelMine.getViewport());
						});
					break;
					
				case "dragonSpa":
					upgradeSlot.setViewport(monResort.getViewport());
					upgradeSlot.setOnMouseClicked(e->{

						System.out.println("Upgrade to "+monsterResort.getName());
						if(currentGold>=monsterResort.getCost()) {
							externalPane2.setVisible(false);
	   		         		externalPane2.setDisable(true);
	   		         		allSlotBox.setDisable(false);
	   		         		shopSlot6.setDisable(true);
	   		         		shopSlot6.setViewport(monResort.getViewport());
	   		         		
	   		         		//Monster Resort gives 10% bonus to overall income
	   		         		totalIncome+=monsterResort.getIncome();
	   		         		totalIncome=(int)(totalIncome*(110.0f/100.0f));
	   		         		currentGold-=monsterResort.getCost();
	   		         		goldDisplay.setText(""+currentGold);
						
	   		         		slotCount6=3;
	   		         		System.out.println("\n"+slotCount6);
	   		         		slot6Upgrade=monsterResort.getName();
	   		         		System.out.println("\nUpgraded to "+slot6Upgrade);
	   		         		
						}
						else {
							insufficientGold(monsterResort.getCost());
							externalPane2.setVisible(false);
	   		         		externalPane2.setDisable(true);
	   		         		allSlotBox.setDisable(false);
						}
					});

					sellSlot.setOnMouseClicked(e->{
					
						System.out.println("Selling "+slot1Upgrade);
						totalIncome-=dragonSpa.getIncome();
						currentGold+=dragonSpa.getCost();
						goldDisplay.setText(""+currentGold);
					
						slotCount6=1;
						System.out.println("Sold "+slot6Upgrade);
						slot6Upgrade=goblinBar.getName();
						System.out.println("Reset Slot\n");
					
						externalPane2.setVisible(false);
						externalPane2.setDisable(true);
						allSlotBox.setDisable(false);
						shopSlot6.setViewport(gobBar.getViewport());
					});
					break;
				
				case "casinoDen":
					upgradeSlot.setViewport(UcasiDen.getViewport());
					upgradeSlot.setOnMouseClicked(e->{

						System.out.println("Upgrade to "+ultracasinoDen.getName());
						if(currentGold>=ultracasinoDen.getCost()) {
							externalPane2.setVisible(false);
	   		         		externalPane2.setDisable(true);
	   		         		allSlotBox.setDisable(false);
	   		         		shopSlot6.setDisable(true);
	   		         		shopSlot6.setViewport(UcasiDen.getViewport());
	   		         		
		   		         		//Ultra Casino gives a massive 30% boost to overall income
	   		         			totalIncome+=ultracasinoDen.getIncome();
	   		         			totalIncome=(int)(totalIncome*(130.0f/100.0f));
	   		        	 		currentGold-=ultracasinoDen.getCost();
	   		    	     		goldDisplay.setText(""+currentGold);
						
	   			         		slotCount6=3;
	   			         		System.out.println("\n"+slotCount6);
		   		         		slot6Upgrade=ultracasinoDen.getName();
		   		         		System.out.println("\nUpgraded to "+slot6Upgrade);
	   		         		
							}
							else {
								insufficientGold(ultracasinoDen.getCost());
								externalPane2.setVisible(false);
		   		         		externalPane2.setDisable(true);
		   		         		allSlotBox.setDisable(false);
							}
						});

						sellSlot.setOnMouseClicked(e->{
					
							System.out.println("Selling "+slot1Upgrade);
							totalIncome-=casinoDen.getIncome();
							currentGold+=casinoDen.getCost();
							goldDisplay.setText(""+currentGold);
					
							slotCount6=1;
							System.out.println("Sold "+slot6Upgrade);
							slot6Upgrade=lotteryDen.getName();
							System.out.println("Reset Slot\n");
						
							externalPane2.setVisible(false);
							externalPane2.setDisable(true);
							allSlotBox.setDisable(false);
							shopSlot6.setViewport(lottDen.getViewport());
						});
						break;
					}
				
					break;
				}
		        event.consume();
	         
		     }
		});
	}
	
	private void setUpNodes() {


		pauseBtn.setImage(pauseBtnImg);
		pauseBtn.setViewport(new Rectangle2D(0, 0,60,60));
		pauseBtn.setLayoutX(720);
		pauseBtn.setLayoutY(520);
		pauseMenu.setImage(pauseMenuImg);
		pauseMenu.setViewport(new Rectangle2D(0, 0,300,400));
		resumeBtn.setImage(menuBtnImg);
		resumeBtn.setViewport(new Rectangle2D(200,0,200,70));
		quitBtn.setImage(menuBtnImg);
		quitBtn.setViewport(new Rectangle2D(400,0,200,70));
		
		menuBtnGroup.setStyle("-fx-spacing:30;");
		menuBtnGroup.setLayoutX(50);
		menuBtnGroup.setLayoutY(120);
		pauseMenuGroup.setLayoutX(250);
		pauseMenuGroup.setLayoutY(150);
		pauseMenuGroup.setVisible(false);
		pauseMenuGroup.setDisable(true);
		
		shopSlot1.setImage(shopSpritesheet);
		shopSlot1.setViewport(new Rectangle2D(0, 257, 128, 128));
		shopSlot2.setImage(shopSpritesheet);
		shopSlot2.setViewport(new Rectangle2D(0, 257, 128, 128));
		shopSlot3.setImage(shopSpritesheet);
		shopSlot3.setViewport(new Rectangle2D(0, 257, 128, 128));
		shopSlot4.setImage(shopSpritesheet);
		shopSlot4.setViewport(new Rectangle2D(130, 257, 128, 128));
		shopSlot5.setImage(shopSpritesheet);
		shopSlot5.setViewport(new Rectangle2D(130, 257, 128, 128));
		shopSlot6.setImage(shopSpritesheet);
		shopSlot6.setViewport(new Rectangle2D(130, 257, 128, 128));
		
		
		skelMine.setImage(shopSpritesheet);
		skelMine.setViewport(new Rectangle2D(129, 0, 128, 128));
		gobBar.setImage(shopSpritesheet);
		gobBar.setViewport(new Rectangle2D(0, 0, 128, 128));
		lottDen.setImage(shopSpritesheet);
		lottDen.setViewport(new Rectangle2D(258, 0, 128, 128));
		anciForge.setImage(shopSpritesheet);
		anciForge.setViewport(new Rectangle2D( 0, 129, 128, 128));
		drgnSpa.setImage(shopSpritesheet);
		drgnSpa.setViewport(new Rectangle2D( 387, 0, 128, 128));
		casiDen.setImage(shopSpritesheet);
		casiDen.setViewport(new Rectangle2D( 129, 129, 128, 128));
		necroAca.setImage(shopSpritesheet);
		necroAca.setViewport(new Rectangle2D( 258, 129, 128, 128));
		monResort.setImage(shopSpritesheet);
		monResort.setViewport(new Rectangle2D( 387, 258, 128, 128));
		UcasiDen.setImage(shopSpritesheet);
		UcasiDen.setViewport(new Rectangle2D( 387, 129, 128, 128));
		
		purchasebackGround.setImage(purchaseImg);
		purchasebackGround.setViewport(new Rectangle2D(0,0,500,200));
		purchasebackGround2.setImage(purchaseImg);
		purchasebackGround2.setViewport(new Rectangle2D(0,200,400,200));
		selectSlot1.setImage(shopSpritesheet);
		selectSlot1.setViewport(skelMine.getViewport());
		selectSlot2.setImage(shopSpritesheet);
		selectSlot2.setViewport(gobBar.getViewport());
		selectSlot3.setImage(shopSpritesheet);
		selectSlot3.setViewport(lottDen.getViewport());
		closeBtn.setImage(closeBtnImg);
		closeBtn.setViewport(new Rectangle2D(0, 0, 32, 32));
		
		selectSlotBox.getChildren().addAll(selectSlot1,selectSlot2,selectSlot3);
		selectSlotBox.setStyle("-fx-spacing:40;");
		externalPane.getChildren().addAll(purchasebackGround,selectSlotBox,closeBtn);
		
		selectSlotBox.setLayoutX(20);
		selectSlotBox.setLayoutY(50);
		closeBtn.setLayoutX(468);
		closeBtn.setLayoutY(0);
		externalPane.setLayoutX(150);
		externalPane.setLayoutY(200);
		externalPane.setVisible(false);
		externalPane.setDisable(true);
		
		upgradeSlot.setImage(shopSpritesheet);
		
		//Not setting viewport until externalPane2 is called
		sellSlot.setImage(shopSpritesheet);
		sellSlot.setViewport(new Rectangle2D(258, 258, 128, 128));
		closeBtn2.setImage(closeBtnImg);
		closeBtn2.setViewport(new Rectangle2D(0, 0, 32, 32));
		selectSlotBox2.getChildren().addAll(upgradeSlot,sellSlot);
		selectSlotBox2.setStyle("-fx-spacing:40;");
		selectSlotBox2.setLayoutX(50);
		selectSlotBox2.setLayoutY(50);
		closeBtn2.setLayoutX(368);
		closeBtn2.setLayoutY(0);
		
		giftShop.setImage(shopSpritesheet);
		giftShop.setViewport(new Rectangle2D( 0, 386, 128, 127));
		gift1.setImage(giftSprite);
		gift1.setViewport(new Rectangle2D( 0, 0, 128, 128));
		gift2.setImage(giftSprite);
		gift2.setViewport(new Rectangle2D( 129, 0, 128, 128));
		gift3.setImage(giftSprite);
		gift3.setViewport(new Rectangle2D( 257, 0, 128, 128));
		closeGiftMenu.setImage(closeBtnImg);
		closeGiftMenu.setViewport(new Rectangle2D(0, 0, 32, 32));
		giftMenu.setImage(purchaseImg);
		giftMenu.setViewport(new Rectangle2D(0, 401, 500, 200));
		giftShops.setStyle("-fx-spacing:40;");
		giftShops.setLayoutX(20);
		giftShops.setLayoutY(50);
		closeGiftMenu.setLayoutX(468);
		closeGiftMenu.setLayoutY(0);
		giftShop.setLayoutX(600);
		giftShop.setLayoutY(200);
		giftPane.setLayoutX(150);
		giftPane.setLayoutY(200);
		giftPane.setVisible(false);
		giftPane.setDisable(true);
		
		insuffGold.setImage(insufficientGoldImg);
		insuffGold.setViewport(new Rectangle2D(0,0,400,150));
		closeGoldMenu.setImage(closeBtnImg);
		closeGoldMenu.setViewport(new Rectangle2D(0, 0, 32, 32));
		closeGoldMenu.setLayoutX(368);
		insufficientGoldLbl.setTextAlignment(TextAlignment.JUSTIFY);
		popUpGoldMenu.setVisible(false);
		popUpGoldMenu.setDisable(true);
		popUpGoldMenu.setLayoutX(200);
		popUpGoldMenu.setLayoutY(200);
		
		
		externalPane2.getChildren().addAll(purchasebackGround2,selectSlotBox2,closeBtn2);
		externalPane2.setLayoutX(150);
		externalPane2.setLayoutY(200);
		externalPane2.setVisible(false);
		externalPane2.setDisable(true);
		
		
	}

	private void insufficientGold(int cost) {
		popUpGoldMenu.setVisible(true);
		popUpGoldMenu.setDisable(false);
        allSlotBox.setDisable(true);
        giftShop.setDisable(true);
        giftPane.setDisable(true);
        externalPane.setDisable(true);
        externalPane2.setDisable(true);
        pauseBtn.setDisable(true);
		insufficientGoldLbl.setText(""+cost);
		giftPane.setVisible(false);
        giftPane.setDisable(true);
			
		closeGoldMenu.setOnMouseClicked(e->{
			popUpGoldMenu.setVisible(false);
			popUpGoldMenu.setDisable(true);
	        allSlotBox.setDisable(false);
	        giftShop.setDisable(false);
	        giftPane.setDisable(false);
	        externalPane.setDisable(false);
	        externalPane2.setDisable(false);
	        pauseBtn.setDisable(false);
			
		});
	}
	
	public static void main(String[] args) {
		launch(args);

	}
	

}
