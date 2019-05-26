#include "FirebaseESP8266.h"
#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>

#include "DHT.h"
//For DHT11
#define DHTPIN 4     // what digital pin we're connected to
#define DHTTYPE DHT11   // DHT 22 
DHT dht(DHTPIN, DHTTYPE);

#define FIREBASE_HOST "https://internetot-97be8.firebaseio.com/" //Without http:// or https://
#define FIREBASE_AUTH "eteZpYHMhqk8zI2mUlsf5PGx7KsmqLShHfbcRlwq"
FirebaseData firebaseData;


ESP8266WiFiMulti WiFiMulti;


 // what digital pin we're connected to
 int count=0; 
 float temperature,humidity;


void setup()
{
   Serial1.begin(9600);
  //Initializing Sensor
  dht.begin();
  
  // We start by connecting to a WiFi network
  WiFi.mode(WIFI_STA);
  WiFiMulti.addAP("Cell Phone", "1234567890");
  
  while (WiFiMulti.run() != WL_CONNECTED) 
  {
    Serial.print(".");
    delay(500);
    Serial1.begin(9600);
  }

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Firebase.reconnectWiFi(true);

  //Size and its write timeout e.g. tiny (1s), small (10s), medium (30s) and large (60s).
  Firebase.setwriteSizeLimit(firebaseData, "tiny");

  Serial.begin(9600);        // Open serial channel at 9600 baud rate

}


void loop() 
{
 
  Serial.println("Establishing Connection");
  const uint16_t port = 4444;
  const char * host = "192.168.40.125"; //"192.168.0.166"; //"192.168.40.219";////// // IP Address of the server

  // Use WiFiClient class to create TCP connections
  WiFiClient client;

  if (!client.connect(host, port)) 
  {
    Serial.println("Connection Failed To Main Server");
    Serial.println("Wait 5 sec...");
    delay(5000);
    return;
  }

  
   temperature = dht.readTemperature();
   humidity = dht.readHumidity();
  // Check if any reads failed and exit early (to try again).
  if (isnan(temperature) || isnan(humidity))
  {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }
  Serial.println("Temperature: " + String(temperature));
  Serial.println("Humidity: " + String(humidity));

  Serial.println("Sending Data to Server");
  client.print("Sending//Temperature//Humidity:Values//");
  client.print(temperature);
  client.print("//");
  client.print(humidity);
  client.print(":Authentication//Hamza//12345678");
  
  delay(3000);

  Serial.println("Closing TCP Connection");
  client.stop();

  Serial.println("Delay of 1 sec");
  delay(1000);
  Serial.println("\n");




 ////////////////////Firebase Variables
  String path = "/IOT";
  String jsonStr = "";
  ////////////////////Sending data to Firebase
    if (Firebase.setDouble(firebaseData, path + "/Temperature/Value:"+count,temperature) && Firebase.setDouble(firebaseData, path + "/Humidity/Value:"+count,humidity))
    {
      Serial.println("\n\n PASSED");
   
    }
    else
    {
      Serial.println("FAILED");
    }

   
  count = count+1;
}
