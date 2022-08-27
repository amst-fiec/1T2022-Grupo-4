int Por = A0; 
int Vo = A1;
int Co = A2;
int Po = A3;//  
int pr = 0;   
int vo = 0;
int co = 0;
int po = 0;//  
  
void setup() { 
   pinMode(Por,INPUT); 
   while (!Serial) {
    ; 
  }
  
   Serial.begin(9600);
   
} 
void loop() {
  
  pr = ((analogRead(Por)* 0.0049)/1.81)*350;
  vo = ((analogRead(Vo)* 0.0049)/1.81)*40;
  co = ((analogRead(Co)* 0.0049)/1.81)*120;
  po = ((analogRead(Po)* 0.0049)/1.81)*3500;
  if (Serial.available() > 0) {
    Serial.write(pr);
    delay(5000);
    Serial.println(co);
    delay(5000);
    Serial.println(vo);
    delay(5000);
    Serial.println(po);
    delay(5000);
  }
  

  
  
} 
