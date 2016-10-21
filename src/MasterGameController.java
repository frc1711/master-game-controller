import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class MasterGameController
{	
	
	public static void main (String[] args)
	{
		try
		{
			String myHost = "localhost";
			int port = 1711;
			
			System.out.println("Connecting to server on port " + port);
			Socket connectionSock = new Socket(myHost, port);
			
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream()));
			DataOutputStream serverOutput = new DataOutputStream(connectionSock.getOutputStream());
			
			System.out.println("Connection made");
			serverOutput.writeBytes("Hello world");
			
			serverOutput.close();
			serverInput.close();
			connectionSock.close();
			
		}
		
		catch (IOException e)
		{
			//tells us what went wrong
			System.out.println(e.getMessage());
		}
	}
}