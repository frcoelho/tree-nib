#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>
#include <stdlib.h>

ESP8266WebServer server(80);

void pwmControl();
void lightControl();
 
int pinMOTOR = 12; // GPIO12
int pinLIGHT = 14; // GPIO14

void setupWiFi(){
  
  String AP_NameString = "BARCO";
  
  char AP_NameChar[AP_NameString.length() + 1];
  AP_NameString.toCharArray(AP_NameChar,AP_NameString.length() + 1);
  char WiFiAPPSK[] = "fantasma";
 
  WiFi.hostname("PWMControl");
  
  WiFi.mode(WIFI_AP_STA);
  WiFi.softAP(AP_NameChar, WiFiAPPSK);

  Serial.println("");
  Serial.println("WiFi connected");
 
  // Atribuindo urls para funções
  // Quando não especificado método, uma função trata todos
  server.on("/pwm", pwmControl);
  server.on("/light", lightControl);
  
  // Start the server
  server.begin();
  
  Serial.println("Server started");
 
  // Print the IP address
  Serial.print("Use this URL to connect: ");
  Serial.print("http://");
  Serial.print(WiFi.localIP());
  Serial.print(" or ");
  Serial.print(WiFi.softAPIP());
  Serial.println("/");
}
 
void setup() {
  Serial.begin(115200);
  delay(10);

  pinMode(pinMOTOR, OUTPUT);
  pinMode(pinLIGHT, OUTPUT);
  analogWrite(pinMOTOR, 0);
  analogWrite(pinLIGHT, 0);
  
  setupWiFi();
}
 
void loop() {
  server.handleClient();
}

void pwmControl() {
  if (server.hasArg("value"))
  {
    analogWrite(pinMOTOR, (int)(((float)(server.arg("value").toFloat()/100)*1023)));
  }
  server.send ( 200, "text/json", "{success:true}" );
}

void lightControl() {
  if (server.hasArg("value"))
  {
    analogWrite(pinLIGHT, (int)(((float)(server.arg("value").toFloat()/100)*1023)));
  }
  server.send ( 200, "text/json", "{success:true}" );
}

