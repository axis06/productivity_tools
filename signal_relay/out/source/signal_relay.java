import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.serial.*; 
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

public class signal_relay extends PApplet {






Serial port;
OscP5 oscP5;
NetAddress dest;

//シリアル
int serial_nameid = 9;
int serial_baudRate = 9600;

//OSC
int osc_port = 6448;
String osc_mes = "/serial";
boolean debug = false;


public void setup() {
  
  frameRate(60);
  showSerial();
  

  println("### serial to osc");
  println(" serial_name: " + Serial.list()[serial_nameid]+ "\n serial_boudrate: " + serial_baudRate);
  println(" osc_port: " + osc_port + "\n osc_mes: " + osc_mes + "\n");

  //シリアル通信
  port = new Serial(this, Serial.list()[serial_nameid], serial_baudRate);
  port.bufferUntil('\n');

  //OSC通信
  oscP5 = new OscP5(this, 9000);
  dest = new NetAddress("127.0.0.1", osc_port);

}

public void draw(){
}

public void showSerial(){
  for (int i = 0; i < Serial.list().length; i++) {
    println ("[" + i + "] "+Serial.list ()[i]);
  }
}

public void serialEvent(Serial port) { 
  String str = port.readStringUntil('\n');
  str = trim(str);

  OscMessage myMessage = new OscMessage(osc_mes);
  
  String toks[] = split(str.substring(0), ",");

  for(int i = 0; i < toks.length; i++){
    if(toks[i] != null){
      if(debug){println("[" + i + "] " + toks[i]);}
      myMessage.add((float)PApplet.parseInt(toks[i]));
    }
  }

}


  public void settings() {  size(640, 480); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "signal_relay" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
