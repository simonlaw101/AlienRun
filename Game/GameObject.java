import javafx.scene.image.Image;
  
public abstract class GameObject{
  //attribute of GameObject
  private double xCoord;
  private double yCoord;
  public double moveAngle;  //between 0 to 1 inclusive
  public double moveDirection;  //down, up, right or left
  
  //default constructor of GameObject
  GameObject(){
  }
  
  //method of getting x-coordinate
  public double getX(){
    return xCoord;
  }
  
  //method of getting y-coordinate
  public double getY(){
    return yCoord;
  }
  
  //method of setting x-coordinate
  public void setX(double x){
    xCoord = x;
  }
  
  //method of setting y-coordinate
  public void setY(double y){
    yCoord = y;
  }
    
  //method of moving GameObject
  public void move(double xDirection, double yDirection){
    xCoord += xDirection;
    yCoord += yDirection;
  }
  
  //method of checking whether GameObject is hit
  public boolean isHit(){
    if (this instanceof Rock && UFO.invisible == false){
      //check whether Rock is hit
      if (this.getX() > GameMain.xCoord - 64 && this.getX() < GameMain.xCoord + 64 && this.getY() > GameMain.yCoord - 64 && this.getY() < GameMain.yCoord +35){
        //play collision sound effect
        GameMain.audioHitRock.play();
        //deduct hit point by 1
        GameMain.hitPoint -= 1;
        return true;
      }
      else{
        return false;
      }
    }
    else if (this instanceof Human){
      //check whether Human is hit
      if (this.getX() > GameMain.xCoord - 24 && this.getX() < GameMain.xCoord + 64 && this.getY() > GameMain.yCoord - 24 && this.getY() < GameMain.yCoord +35){
        GameMain.explodeView.setLayoutX(GameMain.xCoord-32);
        GameMain.explodeView.setLayoutY(GameMain.yCoord-46.5);
        //stop background music
        GameMain.audioGameplay.stop();
        //play explosion sound effect
        GameMain.audioExplosion.play();
        GameMain.isGameOver = true;
        return true;
      }
      else{
        return false;
      }
    }
    else if (this instanceof Alien){
      //check whether Alien is hit
      if (this.getX() > GameMain.xCoord - 24 && this.getX() < GameMain.xCoord + 64 && this.getY() > GameMain.yCoord - 24 && this.getY() < GameMain.yCoord +35){
        //add 1 score
        GameMain.addScore(1);
        //play bonus sound effect
        GameMain.audioGetPoint.play();
        return true;
      }
      else{
        return false;
      }
    }
    else if (this instanceof HPItem){
      //check whether hp item  is hit
      if (this.getX() > GameMain.xCoord - 64 && this.getX() < GameMain.xCoord + 64 && this.getY() > GameMain.yCoord - 64 && this.getY() < GameMain.yCoord +35){
        if (GameMain.hitPoint!=3){
          //play healing sound effect
          GameMain.audioHeal.play();
          //add 1 hit point if hit point is less than 3
          GameMain.hitPoint += 1;
        }
        return true;
      }
      else{
        return false;
      }
    }
    else if (this instanceof SpeedItem){
      //check whether speed item  is hit
      if (this.getX() > GameMain.xCoord - 64 && this.getX() < GameMain.xCoord + 64 && this.getY() > GameMain.yCoord - 64 && this.getY() < GameMain.yCoord +35){
        //play speedup sound effect
        GameMain.audioSpeedup.play();
        //increase UFO speed and start timer
        UFO.speed = 8;
        UFO.startTime = System.currentTimeMillis();
        return true;
      }
      else{
        return false;
      }
    }
    else if (this instanceof InvisibleItem){
      //check whether invisible item  is hit
      if (this.getX() > GameMain.xCoord - 64 && this.getX() < GameMain.xCoord + 64 && this.getY() > GameMain.yCoord - 64 && this.getY() < GameMain.yCoord +35){
        //play sound effect and change UFO visibility state
        GameMain.audioDisappear.play();
        UFO.invisible = true;
        //start timer
        UFO.startTime2 = System.currentTimeMillis();
        return true;
      }
      else{
        return false;
      }
    }
    else{
      return false;
    }
  }
}