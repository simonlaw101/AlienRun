import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;
import java.lang.Math;
import javafx.application.Platform;

public class HPItem extends GameObject implements Runnable{
  public double xCoord;
  public double yCoord;
  public ImageView hpItemView;
  
  //default constructor of HPItem
  HPItem(){
  }

  //constructor of HPItem
  HPItem(double xCoord, double yCoord, ImageView hpItemView){
    super.setX(xCoord);
    super.setY(yCoord);
    this.hpItemView = hpItemView;
  }
  
  //task of handling HPItem
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
      this.hpItemView.setLayoutX(-64);
      this.hpItemView.setLayoutY(-64);
    }
    
    //generate item randomly
    if (this.getX() == -64 && this.getY() == -64 && (int)(Math.random()*200) == 0){
      this.setX((int)(Math.random()*737));
      this.setY((int)(Math.random()*408+29));
      this.hpItemView.setLayoutX(this.getX());
      this.hpItemView.setLayoutY(this.getY());
    }
  } 
}  
 
  
   