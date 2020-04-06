import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;
import java.lang.Math;
import javafx.application.Platform;

public class Rock extends GameObject{
    
  //default constructor of Rock
  Rock(){
  }

  //constructor of Rock
  Rock(double xCoord, double yCoord){
    this.setX(xCoord);
    this.setY(yCoord);
    this.moveAngle = Math.random();
    this.moveDirection = 0;
  }
    
  //method of moving Rock
  public void move(double xDirection, double yDirection){
    //calculate displacement
    double displacement = Math.sqrt(Math.pow(xDirection, 2) + Math.pow(yDirection, 2));
    //create random border
    int border = (int) (Math.random() * 4  + 1);
    //reset Rock position if out of screen, otherwise keep moving
    if (this.getX() < -64 || this.getX()  > 800 || this.getY()  < -64 || this.getY()  > 500){
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
  
  //task of handling Rock objects
  public void rockTask(Rock[] rock, ImageView[] rockView) {  
    try{
      while(!GameMain.isGameOver){
        for (int j = 0; j < 24; j++){
          for (int i = 0; i < 4; i++){
            double x = rock[i].getX();
            double y = rock[i].getY();
            int k = i;
            int l = j;
            Rock object = rock[i];
            ImageView objectView = rockView[i];
            Platform.runLater(()-> rockShow(x, y, k, l, object, objectView));
          }
          Thread.sleep(80);
        }
      }
    }catch (InterruptedException e) {
       e.printStackTrace();
    }
  } 
  
  //method of displaying Rock image
  public void rockShow(double x, double y, int i, int j, Rock object, ImageView objectView){
   //move Rock
   object.move(10, 10);
   //check if Rock is hit
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
