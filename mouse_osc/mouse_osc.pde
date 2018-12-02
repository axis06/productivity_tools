import oscP5.*;
import netP5.*;
 
OscP5 oscP5;
NetAddress myRemoteLocation;
 
void setup(){
  size(500, 500);
 
  oscP5 = new OscP5(this,1234);//自分のポート番号
  myRemoteLocation = new NetAddress("127.0.0.1", 6448);//IPaddress,相手のポート番号
}
 
void draw(){
  background(255);
}
 
void mouseMoved(){
  OscMessage myMessage = new OscMessage("/wek/inputs");
  myMessage.add((float)mouseX);//add message
  myMessage.add((float)mouseY);
  oscP5.send(myMessage, myRemoteLocation); 
}