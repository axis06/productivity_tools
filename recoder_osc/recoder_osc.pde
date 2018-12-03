import oscP5.*;
import netP5.*;
 
OscP5 oscP5;
NetAddress myRemoteLocation;
PrintWriter file;

boolean debug = false;

void setup(){
  size(120, 120);
  oscP5 = new OscP5(this, 12000);
  myRemoteLocation = new NetAddress("127.0.0.1", 12000);

  int s = second();
  int m = minute();
  int h = hour();
  String t = h + nf(m, 2) + nf(s, 2);

  String fileName = createFileName();
  file = createWriter(fileName);
}
 
void draw(){ 

}
 

void oscEvent(OscMessage theOscMessage){
  float oneValue = theOscMessage.get(0).floatValue();
  float twoValue = theOscMessage.get(1).floatValue();
  println("OSC Message Recieved");
  if(debug){
    println(oneValue);
    println(twoValue);
  }
  file.print(oneValue + "," + twoValue);
}


String createFileName() {
  String fileName = "log/";
  fileName += nf(year(), 2) + nf(month(), 2) + nf(day(), 2) +"-"+ nf(hour(), 2) + nf(minute(), 2) + nf(second(), 2);
  fileName += ".csv";
  return fileName;
}

 void dispose() {
  file.flush();
  file.close();
  exit();
 }