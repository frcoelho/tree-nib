#include <WiFi.h>
#include <aREST.h>
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
#define LEDC_CHANNEL_0         0
#define LEDC_CHANNEL_1         1
#define LEDC_CHANNEL_2         2
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
#define RELAY_EXPLOSION_PIN    23
#define R1_PIN                 33
#define R2_PIN                 34
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
#define LED_PIN_R_1            15
#define LED_PIN_G_1            2
#define LED_PIN_B_1            4
#define LED_PIN_R_2            16
#define LED_PIN_G_2            17
#define LED_PIN_B_2            5
#define LED_PIN_R_3            18
#define LED_PIN_G_3            19
#define LED_PIN_B_3            21
#define LED_PIN_R_4            13
#define LED_PIN_G_4            12
#define LED_PIN_B_4            14
#define LED_PIN_R_5            22
#define LED_PIN_G_5            25
#define LED_PIN_B_5            26
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
#define LEDC_TIMER_8_BIT       8
#define LEDC_BASE_FREQ         5000
#define NEED_UPDATE_STATUS     60000 // once a minute
#define BLINK_TIME             40
#define DANCE_TIME             500
#define EXPLOSION_TIME         1000
#define FADE_STEP              10
#define LED_OFF                0
#define LED_ON                 1
#define LED_BLINK              2
#define LED_FADE               3
#define LED_DANCE              4
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
int rgbstatus_r = 0;
int rgbstatus_g = 0;
int rgbstatus_b = 0;

int tmp_r = 0;
int tmp_g = 0;
int tmp_b = 0;
bool isUp = true;
int effect = LED_OFF;
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
aREST rest = aREST();
WiFiServer server(80);
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
int explosion(String command) {
  digitalWrite(RELAY_EXPLOSION_PIN,HIGH);
  delay(EXPLOSION_TIME);
  digitalWrite(RELAY_EXPLOSION_PIN,LOW);
}
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
int r1(String command) {
  if(command.toInt() == LED_OFF){
    digitalWrite(R1_PIN,LOW);
  }else if(command.toInt() == LED_ON){
    digitalWrite(R1_PIN,HIGH);
  }
}
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
int r2(String command) {
  if(command.toInt() == LED_OFF){
    digitalWrite(R2_PIN,LOW);
  }else if(command.toInt() == LED_ON){
    digitalWrite(R2_PIN,HIGH);
  }
}
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
int rgbColor(String command) {
  String rgb       = command;
  tmp_r = 0;
  tmp_g = 0;
  tmp_b = 0;
  isUp = true;
  effect = LED_ON;
  // Get rid of '#' and convert it to integer
  long number = (int) strtol( &rgb[0], NULL, 16);
  
  // Split them up into r, g, b values
  rgbstatus_r = number >> 16;
  rgbstatus_g = number >> 8 & 0xFF;
  rgbstatus_b = number & 0xFF;
}
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
int rgbFade(String command) {
  String rgb       = command;
  tmp_r = 0;
  tmp_g = 0;
  tmp_b = 0;
  isUp = true;
  effect = LED_FADE;
  // Get rid of '#' and convert it to integer
  long number = (int) strtol( &rgb[0], NULL, 16);
  
  // Split them up into r, g, b values
  rgbstatus_r = number >> 16;
  rgbstatus_g = number >> 8 & 0xFF;
  rgbstatus_b = number & 0xFF;
}
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
int rgbFlash(String command) {
  String rgb       = command;
  tmp_r = 0;
  tmp_g = 0;
  tmp_b = 0;
  isUp = true;
  effect = LED_BLINK;
  // Get rid of '#' and convert it to integer
  long number = (int) strtol( &rgb[0], NULL, 16);
  
  // Split them up into r, g, b values
  rgbstatus_r = number >> 16;
  rgbstatus_g = number >> 8 & 0xFF;
  rgbstatus_b = number & 0xFF;
}
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
int rgbDance(String command) {
  effect = LED_DANCE;
}
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
void setup() {
  String initialSetup = "";
  Serial.begin(115200);
  delay(10);
  // Setup timer and attach timer to a led pin
  ledcSetup(LEDC_CHANNEL_0, LEDC_BASE_FREQ, LEDC_TIMER_8_BIT);
  ledcSetup(LEDC_CHANNEL_1, LEDC_BASE_FREQ, LEDC_TIMER_8_BIT);
  ledcSetup(LEDC_CHANNEL_2, LEDC_BASE_FREQ, LEDC_TIMER_8_BIT);
  ledcAttachPin(LED_PIN_R_1, LEDC_CHANNEL_0);
  ledcAttachPin(LED_PIN_G_1, LEDC_CHANNEL_1);
  ledcAttachPin(LED_PIN_B_1, LEDC_CHANNEL_2);
  ledcAttachPin(LED_PIN_R_2, LEDC_CHANNEL_0);
  ledcAttachPin(LED_PIN_G_2, LEDC_CHANNEL_1);
  ledcAttachPin(LED_PIN_B_2, LEDC_CHANNEL_2);
  ledcAttachPin(LED_PIN_R_3, LEDC_CHANNEL_0);
  ledcAttachPin(LED_PIN_G_3, LEDC_CHANNEL_1);
  ledcAttachPin(LED_PIN_B_3, LEDC_CHANNEL_2);
  ledcAttachPin(LED_PIN_R_4, LEDC_CHANNEL_0);
  ledcAttachPin(LED_PIN_G_4, LEDC_CHANNEL_1);
  ledcAttachPin(LED_PIN_B_4, LEDC_CHANNEL_2);
  ledcAttachPin(LED_PIN_R_5, LEDC_CHANNEL_0);
  ledcAttachPin(LED_PIN_G_5, LEDC_CHANNEL_1);
  ledcAttachPin(LED_PIN_B_5, LEDC_CHANNEL_2);
  ledcWrite(LEDC_CHANNEL_0, rgbstatus_r);
  ledcWrite(LEDC_CHANNEL_1, rgbstatus_g);
  ledcWrite(LEDC_CHANNEL_2, rgbstatus_b);
  pinMode(RELAY_EXPLOSION_PIN, OUTPUT);
  pinMode(R1_PIN, OUTPUT);
  pinMode(R2_PIN, OUTPUT);
  digitalWrite(RELAY_EXPLOSION_PIN, LOW);
  digitalWrite(R1_PIN, LOW);
  digitalWrite(R2_PIN, LOW);
  
  WiFi.softAP("lustre", "fantasma");
  
  Serial.println("");
  Serial.print("IP address: ");
  Serial.println(WiFi.softAPIP());
 
  rest.function("rgb",rgbColor);
  rest.function("fade",rgbFade);
  rest.function("blink",rgbFlash);
  rest.function("dance",rgbDance);
  rest.function("r1",r1);
  rest.function("r2",r2);
  rest.function("explosion",explosion);
 
  server.begin();

}
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
void loop() {
  // Handle REST calls
  WiFiClient client = server.available();
  if(effect == LED_ON){

    ledcWrite(LEDC_CHANNEL_0, rgbstatus_r);
    ledcWrite(LEDC_CHANNEL_1, rgbstatus_g);
    ledcWrite(LEDC_CHANNEL_2, rgbstatus_b);
    
  }else if(effect == LED_OFF){

    ledcWrite(LEDC_CHANNEL_0, LED_OFF);
    ledcWrite(LEDC_CHANNEL_1, LED_OFF);
    ledcWrite(LEDC_CHANNEL_2, LED_OFF);
    
  }else if(effect == LED_BLINK){

    ledcWrite(LEDC_CHANNEL_0, LED_OFF);
    ledcWrite(LEDC_CHANNEL_1, LED_OFF);
    ledcWrite(LEDC_CHANNEL_2, LED_OFF);

    delay(BLINK_TIME);

    ledcWrite(LEDC_CHANNEL_0, rgbstatus_r);
    ledcWrite(LEDC_CHANNEL_1, rgbstatus_g);
    ledcWrite(LEDC_CHANNEL_2, rgbstatus_b);

    delay(BLINK_TIME);

  }else if(effect == LED_DANCE){

    ledcWrite(LEDC_CHANNEL_0, random(0, 255));
    ledcWrite(LEDC_CHANNEL_1, random(0, 255));
    ledcWrite(LEDC_CHANNEL_2, random(0, 255));

    delay(DANCE_TIME);

  }else if(effect == LED_FADE){
    if(( (rgbstatus_r != tmp_r) || (rgbstatus_g != tmp_g) || (rgbstatus_b != tmp_b) ) && isUp == true){
      ledcWrite(LEDC_CHANNEL_0, tmp_r);
      ledcWrite(LEDC_CHANNEL_1, tmp_g);
      ledcWrite(LEDC_CHANNEL_2, tmp_b);
      
      if(rgbstatus_r-tmp_r < FADE_STEP){
        tmp_r = rgbstatus_r; 
      }else{
        tmp_r += FADE_STEP;
      }
      
      if(rgbstatus_g-tmp_g < FADE_STEP){
        tmp_g = rgbstatus_g; 
      }else{
        tmp_g += FADE_STEP;
      }
      
      if(rgbstatus_b-tmp_b < FADE_STEP){
        tmp_b = rgbstatus_b; 
      }else{
        tmp_b += FADE_STEP;
      }
      if(rgbstatus_r==tmp_r && rgbstatus_g==tmp_g && rgbstatus_b==tmp_b){
        isUp = false;
      }
      delay(50);
    }
    if(( (tmp_r>0) || (tmp_g>0) || (tmp_b>0) ) && isUp == false){
      ledcWrite(LEDC_CHANNEL_0, tmp_r);
      ledcWrite(LEDC_CHANNEL_1, tmp_g);
      ledcWrite(LEDC_CHANNEL_2, tmp_b);
      
      tmp_r -= FADE_STEP;
      tmp_g -= FADE_STEP;
      tmp_b -= FADE_STEP;
      if(tmp_r < 0){
        tmp_r = 0; 
      }
      if(tmp_g < 0){
        tmp_g = 0; 
      }
      if(tmp_b < 0){
        tmp_b = 0; 
      }
      delay(50);
      if(0==tmp_r && 0==tmp_g && 0==tmp_b){
        isUp = true;
        ledcWrite(LEDC_CHANNEL_0, tmp_r);
        ledcWrite(LEDC_CHANNEL_1, tmp_g);
        ledcWrite(LEDC_CHANNEL_2, tmp_b);
        effect = LED_OFF;
      }
    }
  }
  if (!client) {
    return;
  }
  while(!client.available()){
    delay(1);
  }
  rest.handle(client);
}
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
