import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import oscP5.*; 
import netP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class mouse_osc extends PApplet {



 
OscP5 oscP5;
NetAddress myRemoteLocation;
 
public void setup(){
  
 
  oscP5 = new OscP5(this,1234);//自分のポート番号
  myRemoteLocation = new NetAddress("127.0.0.1", 6448);//IPaddress,相手のポート番号
}
 
public void draw(){
  background(255);
}
 
public void mouseMoved(){
  OscMessage myMessage = new OscMessage("/wek/inputs");
  myMessage.add((float)mouseX);//add message
  myMessage.add((float)mouseY);
  oscP5.send(myMessage, myRemoteLocation); 
}
  public void settings() {  size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "mouse_osc" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
