import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import java.util.concurrent.*;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.media.AudioClip;

public class GameMain extends Application {
  private static int score = 0;
  public static boolean isGameOver = false;
  static boolean[] keystate = new boolean[4];
  public static Pane mainGamePane = new Pane();
  public static double xCoord = 370;
  public static double yCoord = 220;
  public static int hitPoint = 3;
  public static ImageView explodeView;
  public static AudioClip audioUI;
  public static AudioClip audioGameplay;
  public static AudioClip audioGameover;
  public static AudioClip audioGetPoint;
  public static AudioClip audioHitRock;
  public static AudioClip audioExplosion;
  public static AudioClip audioHeal;
  public static AudioClip audioSpeedup;
  public static AudioClip audioDisappear;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    Pane pane = new Pane();
    
    //create buttons
    Button btnStart = new Button("Start");
    btnStart.setStyle("-fx-pref-width: 150px; -fx-pref-height: 35px; -fx-font-family: Helvetica; -fx-font-size:18px; -fx-font-weight: bold; -fx-background-color: Linear-gradient(#7ebcea, #2f4b8f);");
    Button btnHow = new Button("How To Play");
    btnHow.setStyle("-fx-pref-width: 150px; -fx-pref-height: 35px; -fx-font-family: Helvetica; -fx-font-size:18px; -fx-font-weight: bold; -fx-background-color: Linear-gradient(#7ebcea, #2f4b8f);");
    Button btnRank = new Button("Ranking");
    btnRank.setStyle("-fx-pref-width: 150px; -fx-pref-height: 35px; -fx-font-family: Helvetica; -fx-font-size:18px; -fx-font-weight: bold; -fx-background-color: Linear-gradient(#7ebcea, #2f4b8f);");
    Button btnExit = new Button("Exit");
    btnExit.setStyle("-fx-pref-width: 150px; -fx-pref-height: 35px; -fx-font-family: Helvetica; -fx-font-size:18px; -fx-font-weight: bold; -fx-background-color: Linear-gradient(#7ebcea, #2f4b8f);");
    
    btnStart.setLayoutX(335);
    btnStart.setLayoutY(250);
    btnHow.setLayoutX(335);
    btnHow.setLayoutY(300);
    btnRank.setLayoutX(335);
    btnRank.setLayoutY(350);
    btnExit.setLayoutX(335);
    btnExit.setLayoutY(400);
    
    //create backgroud image and logo
    Image bg = new Image("image/bg.png");
    ImageView bgView = new ImageView(bg);
    
    Image logo = new Image("image/logo.png");
    ImageView logoView = new ImageView(logo);
    logoView.setLayoutX(325);
    logoView.setLayoutY(25);
    
    pane.getChildren().addAll(bgView, logoView, btnStart, btnHow, btnRank, btnExit);
    pane.setStyle("-fx-border-color: blue; -fx-background-color: lightblue;");
    
    //create sound effect
    audioGetPoint = new AudioClip(getClass().getResource("/getpoint.wav").toString());
    audioHitRock = new AudioClip(getClass().getResource("/hitrock.wav").toString());
    audioExplosion = new AudioClip(getClass().getResource("/explosion.wav").toString());
    audioHeal = new AudioClip(getClass().getResource("/heal.wav").toString());
    audioSpeedup = new AudioClip(getClass().getResource("/speedup.aiff").toString());
    audioDisappear = new AudioClip(getClass().getResource("/disappear.wav").toString());
    audioGameover = new AudioClip(getClass().getResource("/spaceloop.wav").toString());
    audioGameover.setCycleCount(AudioClip.INDEFINITE);
    GameMain.audioGameover.stop();
    
    //play background music
    audioUI = new AudioClip(getClass().getResource("/star_master_loop.wav").toString());
    audioUI.setCycleCount(AudioClip.INDEFINITE);
    audioUI.stop();
    audioUI.play();
    
    //when player click start button
    btnStart.setOnAction(e->startGame(primaryStage));
    
    //when player click how to play button
    btnHow.setOnAction(e->howToPlay(primaryStage));
    
    //when player click ranking button
    btnRank.setOnAction(e->showRank(primaryStage));
    
    //when player click exit button
    btnExit.setOnAction(e->exitGame(primaryStage));
    
    Scene scene = new Scene(pane, 800, 500);
    primaryStage.setTitle("Alien Run"); 
    primaryStage.setScene(scene); 
    primaryStage.setResizable(false);
    primaryStage.sizeToScene();
    primaryStage.show(); 
    primaryStage.setOnCloseRequest(req -> System.exit(0));
    
  }
  
  //start game
  public void startGame(Stage primaryStage){
    //initialization of variables
    mainGamePane = new Pane();
    hitPoint = 3;
    score = 0; 
    keystate = new boolean[4];
    score = 0;
    xCoord = 370;
    yCoord = 220;
    isGameOver = false;
    UFO.startTime = 0;
    UFO.startTime2 = 0;
    UFO.speed = 5;
    UFO.invisible = false;
    
    //play background music
    GameMain.audioUI.stop();
    audioGameplay = new AudioClip(getClass().getResource("/pas_de_deux.wav").toString());
    audioGameplay.setCycleCount(AudioClip.INDEFINITE);
    audioGameplay.stop();
    audioGameplay.play();
    
    //create background image
    Image bg = new Image("image/bg.png");
    ImageView bgView = new ImageView(bg);
    mainGamePane.getChildren().add(bgView);
    mainGamePane.setStyle("-fx-border-color: blue; -fx-background-color: lightblue;");
    
    //show hit points
    ImageView[] hpView = new ImageView[3];
    for (int i = 0; i < 3; i++){
     hpView[i] = new ImageView(new Image("image/heart.png"));
     hpView[i].setLayoutX((24+3)*i+5);
     hpView[i].setLayoutY(5);
     mainGamePane.getChildren().add(hpView[i]);
    }
     
     //show score
     Label lbScore = new Label("Score: "+String.valueOf(GameMain.getScore()));
     lbScore.setFont(Font.font("Tahoma",FontWeight.BOLD,20));
     lbScore.setTextFill(Color.WHITE);
     lbScore.setStyle("-webkit-text-stroke-color: #000");
     lbScore.setStyle("-webkit-text-stroke-width: 1px;");
     lbScore.setLayoutX(690); 
     lbScore.setLayoutY(8);
     GameMain.mainGamePane.getChildren().add(lbScore); 
     
    ExecutorService executor = Executors.newCachedThreadPool();
    
    //execute thread for handling hit point
    executor.execute(()-> hpTask(hpView));
    
    //execute thread for handling score
    executor.execute(()-> scoreTask(lbScore));
    
    //add Alien image
    Alien[] alien = new Alien[4];
    ImageView[] alienView = new ImageView[4];
    for (int i = 0; i < 4; i++){
      alien[i] = new Alien(0, 0);
      alienView[i] = new ImageView(new Image("image/alien.png"));
      GameMain.mainGamePane.getChildren().add(alienView[i]);
    }
    
    //execute thread for handling Alien objects
    executor.execute(()-> new Alien().alienTask(alien, alienView));
    
    //add Human image
    Human[] human = new Human[2];
    ImageView[] humanView = new ImageView[2];
    for (int i = 0; i < 2; i++){
      human[i] = new Human(0, 0);
      humanView[i] = new ImageView(new Image("image/human.png"));
      GameMain.mainGamePane.getChildren().add(humanView[i]);
    }
    
    //execute thread for handling Human objects
    executor.execute(()-> new Human().humanTask(human, humanView));
    
    //add Rock image
    Rock[] rock = new Rock[4];
    ImageView[] rockView = new ImageView[4];
    for (int i = 0; i < 4; i++){
      rock[i] = new Rock(0, 0);
      rockView[i] = new ImageView(new Image("image/rock.png"));
      GameMain.mainGamePane.getChildren().add(rockView[i]);
    }
    
    //execute thread for handling Rock objects
    executor.execute(()-> new Rock().rockTask(rock, rockView));
    
    //add UFO image
    ImageView ufoView = new ImageView(new Image("image/ufo.png"));
    GameMain.mainGamePane.getChildren().add(ufoView);
    ImageView ufoView2 = new ImageView(new Image("image/ufo2.png"));
    GameMain.mainGamePane.getChildren().add(ufoView2);
    ufoView.setLayoutX(370);
    ufoView.setLayoutY(220);
    ufoView2.setLayoutX(-64);
    ufoView2.setLayoutY(-35);
    
    //add special items image
    ImageView hpItemView = new ImageView(new Image("image/hpItem.png"));
    GameMain.mainGamePane.getChildren().add(hpItemView);
    hpItemView.setLayoutX(-64);
    hpItemView.setLayoutY(-64);
    
    ImageView speedItemView = new ImageView(new Image("image/speedItem.png"));
    GameMain.mainGamePane.getChildren().add(speedItemView);
    speedItemView.setLayoutX(-64);
    speedItemView.setLayoutY(-64);
    
    ImageView invisibleItemView = new ImageView(new Image("image/invisibleItem.png"));
    GameMain.mainGamePane.getChildren().add(invisibleItemView);
    invisibleItemView.setLayoutX(-64);
    invisibleItemView.setLayoutY(-64);
    
    //execute thread for handling special items
    executor.execute(new HPItem(-64, -64, hpItemView));
    executor.execute(new SpeedItem(-64, -64, speedItemView));
    executor.execute(new InvisibleItem(-64, -64, invisibleItemView));
    
    //execute thread for handling UFO
    executor.execute(new UFO(370, 220, ufoView, ufoView2));
    
    //add image of explosion
     explodeView = new ImageView(new Image("image/explode.png"));
     explodeView.setLayoutX(-128);
     explodeView.setLayoutY(-128);
     GameMain.mainGamePane.getChildren().add(explodeView); 
    
    //execute thread for handling gameover
    executor.execute(()-> gameLoop(primaryStage));
    
    executor.shutdown();
    
    Scene scene = new Scene(mainGamePane, 800, 500);
    
    //store the arrow keys states to an array
    scene.setOnKeyPressed(e -> { 
       switch (e.getCode()) {
          case UP:  keystate[0]=true;break;
          case DOWN: keystate[1]=true;break;
          case LEFT: keystate[2]=true;break;
          case RIGHT: keystate[3]=true; break;
       }
        
    });   
    scene.setOnKeyReleased(e ->{
       switch(e.getCode()){
          case UP:  keystate[0]=false;break;
          case DOWN: keystate[1]=false;break;
          case LEFT: keystate[2]=false;break;
          case RIGHT: keystate[3]=false; break;
       }
    });  
    
    primaryStage.setOnCloseRequest(req -> Platform.exit());
    primaryStage.setTitle("Alien Run"); 
    primaryStage.setScene(scene); 
    primaryStage.setResizable(false);
    primaryStage.show();  
    primaryStage.setOnCloseRequest(req -> System.exit(0));
     
  }
  
  //view instruction
  public void howToPlay(Stage primaryStage){
    Pane pane = new Pane();
    
    //create background image
    Image bg = new Image("image/bg.png");
    ImageView bgView = new ImageView(bg);
    
    //create instruction image
    Image howToPlay = new Image("image/instruction.png");
    ImageView howToPlayView = new ImageView(howToPlay);
    
    //create back button
    Button btnBack = new Button("Back");
    btnBack.setStyle("-fx-pref-width: 150px; -fx-pref-height: 35px; -fx-font-family: Helvetica; -fx-font-size:18px; -fx-font-weight: bold; -fx-background-color: Linear-gradient(#7ebcea, #2f4b8f);");
    btnBack.setLayoutX(335);
    btnBack.setLayoutY(400);
    
    //when player click back button
    btnBack.setOnAction(e->start(primaryStage));
    
    pane.getChildren().addAll(bgView, howToPlayView, btnBack);
    pane.setStyle("-fx-border-color: blue; -fx-background-color: white;");
    
    Scene scene = new Scene(pane, 800, 500);
    primaryStage.setTitle("Alien Run"); 
    primaryStage.setScene(scene); 
    primaryStage.setResizable(false);
    primaryStage.show();
    primaryStage.setOnCloseRequest(req -> System.exit(0));
  }
  
  //view ranking
  public void showRank(Stage primaryStage){
    Pane pane = new Pane();
    
    //create background image
    Image bg = new Image("image/bg.png");
    ImageView bgView = new ImageView(bg);
    pane.getChildren().add(bgView);
    
    //create high score table
    Label highScores = new Label("High Scores");
    highScores.setFont(Font.font("Tahoma",FontWeight.BOLD,24));
    highScores.setLayoutX(335);
    highScores.setLayoutY(110);
    highScores.setTextFill(Color.web("000000"));
    
    Rectangle scoreBack = new Rectangle(250,90,315,240);
    scoreBack.setFill(Color.web("BBAAFF"));
    scoreBack.setArcHeight(20);
    scoreBack.setArcWidth(20);
    
    //create back button
    Button btnBack = new Button("Back");
    btnBack.setStyle("-fx-pref-width: 150px; -fx-pref-height: 35px; -fx-font-family: Helvetica; -fx-font-size:18px; -fx-font-weight: bold; -fx-background-color: Linear-gradient(#7ebcea, #2f4b8f);");
    btnBack.setLayoutX(335);
    btnBack.setLayoutY(400);
    
    //when player click back button
    btnBack.setOnAction(e->start(primaryStage));
    
    pane.getChildren().addAll(scoreBack, btnBack, highScores);
    pane.setStyle("-fx-border-color: blue; -fx-background-color: white;");
    
    //read a file part
    try {
     File file = new File("rank.text");
     if(!file.exists()) {//when the file does not exists, make a new file
         file.createNewFile();
         FileWriter fw = new FileWriter(file);
      fw.write("0,Unknown\n0,Unknown\n0,Unknown\n0,Unknown\n0,Unknown");
      fw.flush();
      fw.close();
     }
     
     Scanner scanner = new Scanner(file);
  
     for (int i = 0; scanner.hasNextLine(); i++) {
      String[] line = scanner.nextLine().split(",");
         Label rank = new Label(line[0].trim());
         rank.setTextFill(Color.web("000000"));
            rank.setFont(Font.font("Tahoma",FontWeight.BOLD,24));
            rank.setLayoutX(490);
            rank.setLayoutY(160+i*30);
            
         Label name = new Label((i+1)+": "+line[1].trim());
         name.setTextFill(Color.web("000000"));
            name.setFont(Font.font("Tahoma",FontWeight.BOLD,24));
            name.setLayoutX(270);
            name.setLayoutY(160+i*30);
            
            pane.getChildren().addAll(rank, name);          
     }
     scanner.close();
 } catch (Exception e) {
  e.printStackTrace();
 }
   
    Scene scene = new Scene(pane, 800, 500);
    primaryStage.setTitle("Alien Run"); 
    primaryStage.setScene(scene); 
    primaryStage.setResizable(false);
    primaryStage.show();   
    primaryStage.setOnCloseRequest(req -> System.exit(0));
  }
  
  //exit game
  public void exitGame(Stage primaryStage){
      //stop background music and close window
      GameMain.audioUI.stop();
      GameMain.audioGameover.stop();
      primaryStage.close();
  }
  
  //method of getting score
  public static int getScore(){
    return score;
  }
  
  //method of adding score
  public static void addScore(int i){
    score += i;
  }
  
  //task of handling hp
  public void hpTask(ImageView[] hpView){  
    try{
      while(!GameMain.isGameOver){
        Platform.runLater(()-> hpShow(hpView));
        Thread.sleep(30);
      }
    }catch (InterruptedException e) {
       e.printStackTrace();
    }
  }
  
  //method of displaying hp
   public void hpShow(ImageView[] hpView){
    //display heart image(s) according to hit point
   if (GameMain.hitPoint >= 3){
     hpView[2].setLayoutX(59);
     hpView[1].setLayoutX(32);
     hpView[0].setLayoutX(5);
   }
   else if (GameMain.hitPoint == 2){
     hpView[2].setLayoutX(-24);
     hpView[1].setLayoutX(32);
     hpView[0].setLayoutX(5);
   }
   else if (GameMain.hitPoint == 1){
     hpView[2].setLayoutX(-24);
     hpView[1].setLayoutX(-24);
     hpView[0].setLayoutX(5);
   }
   else{
     //display explosion image at the UFO location
     GameMain.mainGamePane.getChildren().removeAll(hpView[0], hpView[1], hpView[2]);
     GameMain.explodeView.setLayoutX(GameMain.xCoord-32);
     GameMain.explodeView.setLayoutY(GameMain.yCoord-46.5);
     GameMain.audioGameplay.stop();
     GameMain.audioExplosion.play();
     GameMain.isGameOver = true;
   }
 }
   
   //task of handling score
   public void scoreTask(Label lbScore){  
    try{
      while(!GameMain.isGameOver){
        Platform.runLater(()-> scoreShow(lbScore));
        Thread.sleep(30);
      }
    }catch (InterruptedException e) {
       e.printStackTrace();
    }
  } 
  
  //method of showing current score
  public void scoreShow(Label lbScore){  
    //change score label
    lbScore.setText("Score: "+String.valueOf(GameMain.getScore()));
  } 
  
  //method of checking gameover
  public void gameLoop(Stage primaryStage){  
    while(!GameMain.isGameOver){
   try{
    Thread.sleep(1000);
   }catch(Exception e){
    System.err.println("Ref: GameOver");
    e.printStackTrace();
   }
  }
  //execute gameoverTask when gameover
  Platform.runLater(()-> gameoverTask(primaryStage));
 }
 
  //method of showing gameover page
  public void gameoverTask(Stage primaryStage){  
     //play gameover music 
     GameMain.audioGameover.play();
     Pane pane = new Pane();
     
     //create buttons
     Button btnRestart = new Button("Restart");
     btnRestart.setStyle("-fx-pref-width: 150px; -fx-pref-height: 35px; -fx-font-family: Helvetica; -fx-font-size:18px; -fx-font-weight: bold; -fx-background-color: Linear-gradient(#7ebcea, #2f4b8f);");
     Button btnBack = new Button("Back");
     btnBack.setStyle("-fx-pref-width: 150px; -fx-pref-height: 35px; -fx-font-family: Helvetica; -fx-font-size:18px; -fx-font-weight: bold; -fx-background-color: Linear-gradient(#7ebcea, #2f4b8f);");
     Button btnExit = new Button("Exit");
     btnExit.setStyle("-fx-pref-width: 150px; -fx-pref-height: 35px; -fx-font-family: Helvetica; -fx-font-size:18px; -fx-font-weight: bold; -fx-background-color: Linear-gradient(#7ebcea, #2f4b8f);");
     
     btnRestart.setLayoutX(550);
     btnRestart.setLayoutY(180);
     btnBack.setLayoutX(550);
     btnBack.setLayoutY(240);
     btnExit.setLayoutX(550);
     btnExit.setLayoutY(300);
     
     //when player click restart button
     btnRestart.setOnAction(e->restart(primaryStage));
     
     //when player click back button
     btnBack.setOnAction(e->start(primaryStage));
     
     //when player click exit button
     btnExit.setOnAction(e->exitGame(primaryStage));
     
     //create background image
     Image bg = new Image("image/bg.png");
     ImageView bgView = new ImageView(bg);
     
     //display gameover message
     Image gameover = new Image("image/gameover.png");
     ImageView gameoverView = new ImageView(gameover);
     gameoverView.setLayoutX(336);
     gameoverView.setLayoutY(82);
     
     Image gameover2 = new Image("image/gameover2.png");
     ImageView gameover2View = new ImageView(gameover2);
     gameover2View.setLayoutX(340);
     gameover2View.setLayoutY(220);
     
     //display high score
     Rectangle scoreBack = new Rectangle(95,145,155,220);
     scoreBack.setFill(Color.web("BBAAFF"));
     scoreBack.setArcHeight(20);
     scoreBack.setArcWidth(20);
     
     Label highScores = new Label("High Scores");
      highScores.setFont(Font.font("Tahoma",FontWeight.BOLD,20));
      highScores.setLayoutX(110);
      highScores.setLayoutY(220);
      highScores.setTextFill(Color.web("000000"));
      
      Label yourScore = new Label("Your Score");
      yourScore.setFont(Font.font("Tahoma",FontWeight.BOLD,20));
      yourScore.setLayoutX(110);
      yourScore.setLayoutY(155);
      yourScore.setTextFill(Color.web("000000"));
      
      int score = GameMain.getScore();
      Label scoreLabel = new Label(Integer.toString(score));
      scoreLabel.setFont(Font.font("Tahoma",FontWeight.BOLD,28));
      scoreLabel.setLayoutX(110);
      scoreLabel.setLayoutY(175);
      scoreLabel.setTextFill(Color.web("000000"));
     
     pane.getChildren().addAll(bgView, gameoverView, gameover2View, btnRestart, btnBack, btnExit, scoreBack, highScores, yourScore, scoreLabel);
   
     //read file
     try {
      File file = new File("rank.text");
      if(!file.exists()) {//if the file does not exist, make a new file
          file.createNewFile();
          FileWriter fw = new FileWriter(file);
       fw.write("0,Unknown\n0,Unknown\n0,Unknown\n0,Unknown\n0,Unknown");
       fw.flush();
       fw.close();
      }
      
     Scanner scanner = new Scanner(file);
     String[][] ranks = new String[5][];
   
      for (int i=0; scanner.hasNextLine(); i++) {
        ranks[i]= scanner.nextLine().split(",");//store names and scores into an array
      }
      
      scanner.close();
      
        for(int j=0; j<ranks.length;j++){
         if (score >= Integer.parseInt(ranks[j][0])){//if it is a high score
          for(int k=ranks.length-1; k>j;k--){
           ranks[k][0]=ranks[k-1][0];
           ranks[k][1]=ranks[k-1][1];
          }
          ranks[j][0]=String.valueOf(score);
          ranks[j][1]="Unknown";
          
          FileWriter fw = new FileWriter(file);
          String string = "";
          for(int k=0;k<ranks.length;k++){
           string += ranks[k][0]+","+ranks[k][1]+"\n";
          }
          
             fw.write(string);
             fw.flush();
             fw.close();
             break;
         }
        }
        boolean rank_indicator = true;
        for(int j=0; j<ranks.length;j++){
         Label rank = new Label((j+1)+": "+ranks[j][0].trim());
         rank.setTextFill(Color.web("000000"));
         
         //if the score is a high score, store the score and the name of the player. Also, change the colour of the ranking
            if(Integer.parseInt(ranks[j][0].trim())==score && rank_indicator){
             rank.setTextFill(Color.web("FF0000"));
             
             Label congrats = new Label("Congratulations on your high score!!\nPlease type your name.");
             congrats.setFont(Font.font("Tahoma",FontWeight.BOLD,16));
             congrats.setLayoutX(110);
             congrats.setLayoutY(395);
             congrats.setTextFill(Color.web("000000"));
             
             //create textfield for name input
             TextField name = new TextField();
             name.setLayoutX(440);
             name.setLayoutY(400);
             
             Button enter = new Button("Enter");
             enter.setLayoutX(610);
             enter.setLayoutY(400);
             enter.setOnAction(new EventHandler<ActionEvent>() {//
                 @Override
                 public void handle(ActionEvent e) {
                congrats.setLayoutY(405);
                   congrats.setText("Thank you for playing, "+name.getText().substring(0,(name.getText().length()<10)?name.getText().length():10)+"!");
                   
                    boolean rank_indicator_2 = true;
                    for(int k=0; k<ranks.length;k++){
                     if(score==Integer.parseInt(ranks[k][0].trim())&&rank_indicator_2){
                      rank_indicator_2=false;
                      ranks[k][1]=name.getText().substring(0,(name.getText().length()<10)?name.getText().length():10);//store the name
                     }
                    }
                   try{ //rewrite the file
                    FileWriter fw = new FileWriter(file);
                   String string = "";
                   for(int k=0;k<ranks.length;k++){
                    string += ranks[k][0]+","+ranks[k][1]+"\n";
                   }
                   
                   fw.write(string);
                   fw.flush();
                   fw.close();
                   }catch(IOException e1){
                    
                   }
                    
                  }
              });
             
             
             pane.getChildren().addAll(congrats,name,enter);
             
             rank_indicator = false;
            }
            rank.setFont(Font.font("Tahoma",FontWeight.BOLD,18));
            rank.setLayoutX(110);
            rank.setLayoutY(247+j*22);
            pane.getChildren().add(rank);
         }     
     } catch (Exception e) {
       e.printStackTrace();
     } 
     
     pane.setStyle("-fx-border-color: blue; -fx-background-color: lightblue;");
     
     Scene scene = new Scene(pane, 800, 500);
     primaryStage.setTitle("Game Over"); 
     primaryStage.setScene(scene); 
     primaryStage.setResizable(false);
     primaryStage.sizeToScene();
     primaryStage.show();
     primaryStage.setOnCloseRequest(req -> System.exit(0));
  }
  
  //restart game
  public void restart(Stage primaryStage){
      //stop gameover music
      GameMain.audioGameover.stop();
      //close window and start new game
      primaryStage.close();
      Platform.runLater(() -> startGame(primaryStage) );
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}