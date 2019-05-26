
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerThreads extends Thread {
	private Socket Device;
	private DataBaseManager DBConnection; 
	final String IP="localhost";



	public ServerThreads(Socket device){
		this.Device = device;
		DBConnection=new DataBaseManager();
	}

	// This Function Accept a string and convert it into Integer....
	public int ConvertStringToInt(String str) {
		int sum=0,i=0,temp;
		while(i<str.length()) {
			temp=str.charAt(i)-48;
			sum=sum*10;
			sum+=temp;
			i++;
		}
		return sum;
	}

	public void run()
	{
		try {
			InputStreamReader isr=new InputStreamReader(Device.getInputStream());
			BufferedReader br =new BufferedReader(isr);
			String str=br.readLine();
			System.out.println(str);
			clientRequest(str); 
			//PrintStream ps=new PrintStream(Device.getOutputStream());
			//ps.println(this.clientRequestParsing(str));
			//System.out.println("fgdsfgisf 12345");
			System.out.println(str);
			//ps.println(this.clientRequest(str));
			System.out.println();	
		}
		catch (Exception e)
		{}
	}




	private String clientRequest(String str) throws UnknownHostException, IOException {
		String[] lines=str.split(":");
		String[] firstline=lines[0].split("//");
		System.out.println("fgdsfgisf  kshfskfd");
		System.out.println(firstline[0]);


		if(firstline[0].equals("Register") || firstline[0].equals("register")){			
			String[] auth=lines[1].split("//");
			System.out.println(auth[1]+" "+auth[2]);
			DBConnection.addNewDevice(firstline[1], firstline[2],auth[1], auth[2]);  
		}
		else if(firstline[0].equals("Sending") || firstline[0].equals("sending")){
			String[] values=lines[1].split("//");
			String[] auth=lines[2].split("//");
			System.out.println(firstline[1]+" "+firstline[2]);
			System.out.println(values[1]+" "+values[2]);
			System.out.println(auth[1]+" "+auth[2]);
			System.out.println("hgdfdjsgff");
			DBConnection.sensorValues(firstline[1], firstline[2],values[1],values[2],auth[1], auth[2]);
		}
		else if(firstline[0].equals("GET") || firstline[0].equals("get")) {
			String[] values=lines[1].split("//");
			String[] auth=lines[2].split("//");
			System.out.println(firstline[1]+" "+firstline[2]);
			System.out.println(values[1]+" "+values[2]);
			System.out.println(auth[1]+" "+auth[2]);
			DBConnection.sensorValues(firstline[1], firstline[2],values[1],values[2],auth[1], auth[2]);
		}
		else {
			System.out.println("Something Went Wrong");
		}
		return "FALSE";
	}

}
