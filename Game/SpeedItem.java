import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;
import java.lang.Math;
import javafx.application.Platform;

public class SpeedItem extends GameObject implements Runnable{
  public double xCoord;
  public double yCoord;
  public ImageView speedItemView;
  
  //default constructor of SpeedItem
  SpeedItem(){
  }

  //constructor of SpeedItem 
  SpeedItem(double xCoord, double yCoord, ImageView speedItemView){
    super.setX(xCoord);
    super.setY(yCoord);
    this.speedItemView = speedItemView;
  }
  
  //task of handling SpeedItem
  @Override
  public void run(){  
    try{
      while(!GameMain.isGameOver){
        Platform.runLater(()-> itemShow());       
        Thread.sleep(80);
      }
    }catch (InterruptedException e) {
       e.printStackTrace();
    }
  } 
  
  //method of displaying item image
  public void itemShow(){  
    //remove item from screen if being hit
    if (this.isHit()==true){
      this.setX(-64);
      this.setY(-64);
      this.speedItemView.setLayoutX(-64);
      this.speedItemView.setLayoutY(-64);
    }
    
    //generate item randomly
    if (this.getX() == -64 && this.getY() == -64 && (int)(Math.random()*200) == 0){
      this.setX((int)(Math.random()*737));
      this.setY((int)(Math.random()*408+29));
      this.speedItemView.setLayoutX(this.getX());
      this.speedItemView.setLayoutY(this.getY());
    }
  } 
  
}
