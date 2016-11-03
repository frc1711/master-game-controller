import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.io.*;

public class MasterGameController
{	
	private String color;
	
	private String robot1;
	private String robot2;
	private String robot3;
	private String robot4;
	private String robot5;
	private String robot6;
	
	private boolean isEnabled;
	private int gameDuration;
	private long gameStart;
	private long gameEnd;

	public static void main (String[] args)
	{	
		//collect team color
		//collect robot IDs
		//set up robots
		//set robots to team color
		//set up timer and enable
		
		try
		{
			Thread robotThread1 = new Thread();
			{
				String myHost = "192.168.2.1"; //figure out actual hostname
				int port = 1711;
				
				System.out.println("Connecting to server on port " + port);
				Socket robotSocket1 = new Socket(myHost, port);
				
				BufferedReader robotInput1 = new BufferedReader(new InputStreamReader(robotSocket1.getInputStream()));
				
				DataOutputStream serverOutput1 = new DataOutputStream(robotSocket1.getOutputStream());
				
				System.out.println("connected");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				byte[] b = {(byte) 0xfe, 01, 01, 0xd, 0xa};
				serverOutput1.write(b);
				String s = robotInput1.readLine();
				byte[] b2 = s.getBytes();
				serverOutput1.close();
				robotInput1.close();
				robotSocket1.close();
				};
			robotThread1.start();
		}
		
		catch (IOException e)
		{
			//tells us what went wrong
			System.out.println(e.getMessage());
		}
	}
	
	private void setColor()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter team color");
		String userIn = keyboard.nextLine();
		if(colorOK(userIn.toLowerCase()))
			this.color = userIn;
		else
			System.out.println("Error. Invalid color.");
	}
	
	private String setRobotID()
	{
		Scanner keyboard = new Scanner(System.in);
		String userIn = keyboard.nextLine();
		String robotID = "0";
		if(idOK(userIn))
			robotID = userIn;
		return robotID;
	}
	
	private boolean idOK(String id)
	{
		//put here the reqs for a proper address
		if (Integer.parseInt(id) > 0 && Integer.parseInt(id) < 16)
		{
			//make sure there's no duplicates
			return true;
		}
		return true;
	}
	
	private void idCollector()
	{
		System.out.println("Please enter the ID for robot 1");
		this.robot1 = setRobotID();
		System.out.println("Please enter the ID for robot 2");
		this.robot2 = setRobotID();
		System.out.println("Please enter the ID for robot 3");
		this.robot3 = setRobotID();
		System.out.println("Please enter the ID for robot 4");
		this.robot4 = setRobotID();
		System.out.println("Please enter the ID for robot 5");
		this.robot5 = setRobotID();
		System.out.println("Please enter the ID for robot 6");
		this.robot6 = setRobotID();
	}
	
	private boolean colorOK(String color)
	{
		if(color.equals("blue"))
			return true;
		else if (color.equals("red"))
			return true;
		else
			return false;
	}
	
	private void beginGame()
	{
		gameStart = System.currentTimeMillis();
		gameEnd = (gameStart + (gameDuration * 1000));
	}
}