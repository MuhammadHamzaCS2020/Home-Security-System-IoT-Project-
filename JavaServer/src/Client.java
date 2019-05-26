
import java.io.*;
import java.net.*;
//Muhammad Hamza
//2016-UET-NML-CS-28

//           Protocol Syntax
//           For Signin: i//username//password
// 			 For signup: u//username//password//DoB//department
import java.util.Scanner;
public class Client {
	final int PORT=4444;
	final String IP="localhost";

	public Client() throws IOException {
		Socket client =new Socket(IP,PORT);
		PrintWriter pw=new PrintWriter(client.getOutputStream(),true);
		// These Are Msg formates that we are using for communication between hosts.

		String str="Sending//Motion//Distence"+":"+"Values//33//50"+":"+"Authentication//Hamza//12345678";
		//String str1="GET//255.255.255.255//2332"+":"+"Authentication//Hamza//12345678"+":"+"Motion";
		//String str2="GET//255.255.255.255//2332"+":"+"Authentication//Hamza//12345678"+":"+"Distence";
		//String str3="GET//255.255.255.255//2332"+":"+"Authentication//Hamza//12345678"+":"+"Both";
		//pw.println("Register//255.255.255.255//4444"+":"+"Authentication//Hamza//12345678");

		pw.println(str);
		BufferedReader br=new BufferedReader(new InputStreamReader(client.getInputStream()));
		System.out.println(br.readLine());

	}

	public static void main(String args[]) throws IOException{
		Client obj=new Client();
	}
}
