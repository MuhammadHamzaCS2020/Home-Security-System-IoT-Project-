//Muhammad Hamza
//2016-UET-NML-CS-28

import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
	static int clientRequests=0;
	public MainServer() {
		try
		{
			final int PORT=4444;
			ServerSocket ss=new ServerSocket(PORT);
			System.out.println("Waiting for requests");
			while(true){
				Socket client=ss.accept();
				clientRequests++;
				System.out.println("Server Accepted Request# "+clientRequests);
				ServerThreads ct = new ServerThreads(client);
				ct.start();
			}
		}
		catch(Exception e)
		{
			System.err.println(e);
		}

	}

	// Main Function
	public static void main(String args[]){
		MainServer obj=new MainServer();
	}
}


/*
 CREATE TABLE Devices(
  ID int NOT NULL AUTO_INCREMENT,
  IP varchar(50),
  PORT varchar(20),
  NAME varchar(50),
  PASSWORD varchar(50),
  PRIMARY KEY (ID)
);

*/

/*
CREATE TABLE sensorValues(
	ID int NOT NULL AUTO_INCREMENT,
	Device varchar(20),
	Temperature varchar(50),
	Humidity varchar(20),
	dateTime TIMESTAMP,
	PRIMARY KEY (ID)
);
*/
