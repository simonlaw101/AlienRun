import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;
import java.lang.Math;
import javafx.application.Platform;

public class Alien extends GameObject{
  //default constructor of Alien
  Alien(){
  }
  
  //constructor of Alien
  Alien(double xCoord, double yCoord){
    this.setX(xCoord);
    this.setY(yCoord);
    this.moveAngle = Math.random();
    this.moveDirection = 0;
  }
    
  //method of moving Alien
  public void move(double xDirection, double yDirection){
    //calculate displacement
    double displacement = Math.sqrt(Math.pow(xDirection, 2) + Math.pow(yDirection, 2));
    //create random border
    int border = (int) (Math.random() * 4  + 1);
    //reset Alien position if out of screen, otherwise keep moving
    if (this.getX() < -24 || this.getX()  > 800 || this.getY()  < -24 || this.getY()  > 500){
       if (border==1){
        this.setX(Math.random()*801);
        this.setY(30);
        this.moveDirection = 1;
      }
      else if (border==2){
        this.setX(Math.random()*801);
        this.setY(500);
        this.moveDirection = 2;
      }
      else if (border==3){
        this.setX(0);
        this.setY(Math.random()*500);
        this.moveDirection = 3;
      }
      else{
        this.setX(800);
        this.setY(Math.random()*500);
        this.moveDirection = 4;
      }
    }
    else{
        if (this.moveDirection==1){
        this.setX(this.getX() + (int)(displacement*Math.cos(Math.PI*this.moveAngle)));
        this.setY(this.getY() + (int)(displacement*Math.sin(Math.PI*this.moveAngle)));
      }
      else if (this.moveDirection==2){
        this.setX(this.getX() + (int)(displacement*Math.cos(Math.PI*this.moveAngle)));
        this.setY(this.getY() + (int)(-displacement*Math.sin(Math.PI*this.moveAngle)));
      }
      else if (this.moveDirection==3){
        this.setX(this.getX() + (int)(displacement*Math.sin(Math.PI*this.moveAngle)));
        this.setY(this.getY() + (int)(displacement*Math.cos(Math.PI*this.moveAngle)));
      }
      else{
        this.setX(this.getX() + (int)(-displacement*Math.sin(Math.PI*this.moveAngle)));
        this.setY(this.getY() + (int)(displacement*Math.cos(Math.PI*this.moveAngle)));
      }  
    } 
  }  
  
  //task of handling Alien objects
  public void alienTask(Alien[] alien, ImageView[] alienView){  
    try{
      while(!GameMain.isGameOver){
        for (int j = 0; j < 24; j++){
        for (int i = 0; i < 4; i++){
          double x = alien[i].getX();
          double y = alien[i].getY();
          int k = i;
          int l = j;
          Alien object = alien[i];
          ImageView objectView = alienView[i];
          Platform.runLater(()-> alienShow(x, y, k, l, object, objectView));
        }
        Thread.sleep(80);
        }
      }
    }catch (InterruptedException e) {
       e.printStackTrace();
    }
  } 
  
  //method of displaying Alien image
 public void alienShow(double x, double y, int i, int j, Alien object, ImageView objectView){
   //move Alien
   object.move(10, 10);
   //check if Alien is hit
   if (object.isHit() == true){
     //move it out of screen and restart movement
     object.setX(-100);
     object.setY(-100);
     object.move(10, 10);
   }
   //display current position
   objectView.setLayoutX(object.getX());
   objectView.setLayoutY(object.getY());
   //rotate image by small angle
   objectView.setRotate(j*15 + i*15);
 }
  
}