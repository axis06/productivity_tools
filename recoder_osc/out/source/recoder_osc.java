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

public class recoder_osc extends PApplet {



 
OscP5 oscP5;
NetAddress myRemoteLocation;
 
public void setup(){
  
  oscP5 = new OscP5(this, 12000);
  myRemoteLocation = new NetAddress("127.0.0.1", 12000);
}
 
public void draw(){

}
 

public void oscEvent(OscMessage theOscMessage){
  float oneValue = theOscMessage.get(0).floatValue();
  float twoValue = theOscMessage.get(1).floatValue();
  println("OSC Message Recieved");
  println(oneValue);
  println(twoValue);
}
  public void settings() {  size(120, 120); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "recoder_osc" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
