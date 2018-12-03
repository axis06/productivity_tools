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
PrintWriter file;

boolean debug = false;

public void setup(){
  
  oscP5 = new OscP5(this, 12000);
  myRemoteLocation = new NetAddress("127.0.0.1", 12000);

  int s = second();
  int m = minute();
  int h = hour();
  String t = h + nf(m, 2) + nf(s, 2);

  String fileName = createFileName();
  file = createWriter(fileName);
}
 
public void draw(){ 

}
 

public void oscEvent(OscMessage theOscMessage){
  float oneValue = theOscMessage.get(0).floatValue();
  float twoValue = theOscMessage.get(1).floatValue();
  println("OSC Message Recieved");
  if(debug){
    println(oneValue);
    println(twoValue);
  }
  file.print(oneValue + "," + twoValue);
}


public String createFileName() {
  String fileName = "log/";
  fileName += nf(year(), 2) + nf(month(), 2) + nf(day(), 2) +"-"+ nf(hour(), 2) + nf(minute(), 2) + nf(second(), 2);
  fileName += ".csv";
  return fileName;
}

 public void dispose() {
  file.flush();
  file.close();
  exit();
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
