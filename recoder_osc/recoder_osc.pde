import oscP5.*;
import netP5.*;
 
OscP5 oscP5;
NetAddress myRemoteLocation;
 
void setup(){
  size(120, 120);
  oscP5 = new OscP5(this, 12000);
  myRemoteLocation = new NetAddress("127.0.0.1", 12000);
}
 
void draw(){

}
 

void oscEvent(OscMessage theOscMessage){
  float oneValue = theOscMessage.get(0).floatValue();
  float twoValue = theOscMessage.get(1).floatValue();
  println("OSC Message Recieved");
  println(oneValue);
  println(twoValue);
}
