import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.application.Platform;

class UFO extends GameObject implements Runnable{
  //attribute of UFO
  private int hitPoint = 3;
  public static int speed = 5;
  public ImageView ufoView;
  public ImageView ufoView2;
  public static double startTime = 0;
  public static double startTime2 = 0;
  public static boolean invisible = false;
  
  //default constructor of UFO
  UFO(){
  }
  
  //constructor of UFO
  UFO(double xCoord, double yCoord, ImageView ufoView, ImageView ufoView2){
    super.setX(xCoord);
    super.setY(yCoord);
    this.ufoView = ufoView;
    this.ufoView2 = ufoView2;
  }
  
  //method of moving UFO
  public void move(double xDirection, double yDirection){
    //restrict UFO movement inside the screen and below hit point & score bar 
    //change UFO coordinates
    this.setX((this.getX() + xDirection < 1 || this.getX() + xDirection > 736)? this.getX(): this.getX() + xDirection);
    this.setY((this.getY() + yDirection < 30 || this.getY() + yDirection > 464)? this.getY(): this.getY() + yDirection);
    
    GameMain.xCoord = this.getX();
    GameMain.yCoord = this.getY();
  }
  
  //task of handling UFO
  @Override
  public void run(){
    try {
     this.ufoView.requestFocus();
     this.ufoView2.requestFocus();
     
     while(!GameMain.isGameOver){
       //check if 10-second speedup effect has finished
       if (System.currentTimeMillis() - startTime > 10000){
         speed = 5;
       }
       
       Platform.runLater(()-> ufoShow());
       
       Thread.sleep(30);
     }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
 }
  
  //method of displaying UFO
   public void ufoShow(){
     //check if 10-second invisible effect has finished
     if (System.currentTimeMillis() - startTime2 > 10000){
         UFO.invisible = false;
         this.ufoView2.setLayoutX(-64); this.ufoView2.setLayoutY(-35);
     }
     
     //move and display UFO image according to arrow key input
     if (UFO.invisible == true){
       this.ufoView.setLayoutX(-64); this.ufoView.setLayoutY(-35);
       if(GameMain.keystate[0]==true) this.move(0, -speed); this.ufoView2.setLayoutX(this.getX()); this.ufoView2.setLayoutY(this.getY());
       if(GameMain.keystate[1]==true) this.move(0, speed); this.ufoView2.setLayoutX(this.getX()); this.ufoView2.setLayoutY(this.getY());
       if(GameMain.keystate[2]==true) this.move(-speed, 0); this.ufoView2.setLayoutX(this.getX()); this.ufoView2.setLayoutY(this.getY());
       if(GameMain.keystate[3]==true) this.move(speed, 0); this.ufoView2.setLayoutX(this.getX()); this.ufoView2.setLayoutY(this.getY());
     }
     else{
         
       if(GameMain.keystate[0]==true) this.move(0, -speed); this.ufoView.setLayoutX(this.getX()); this.ufoView.setLayoutY(this.getY());
       if(GameMain.keystate[1]==true) this.move(0, speed); this.ufoView.setLayoutX(this.getX()); this.ufoView.setLayoutY(this.getY());
       if(GameMain.keystate[2]==true) this.move(-speed, 0); this.ufoView.setLayoutX(this.getX()); this.ufoView.setLayoutY(this.getY());
       if(GameMain.keystate[3]==true) this.move(speed, 0); this.ufoView.setLayoutX(this.getX()); this.ufoView.setLayoutY(this.getY());
       
      }
   }
   
}