package schoperation.RandomSchop.text.network;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient
{
    /*
        Client for TCP test
     */
    public void askForInfo()
    {
        Scanner scanner = new Scanner(System.in);

        // Grab IP
        InetAddress localIp = null;
        try
        {
            localIp = InetAddress.getLocalHost();
            System.out.println("Your IPv4 Address is: " + localIp);
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }

        // Ask for address and port
        System.out.println("IP? xxx.xxx.x.xxx");
        String ip = scanner.nextLine();
        System.out.println("Port number?");
        int port = scanner.nextInt();
        scanner.close();

        createClient(ip, port);
    }

    /**
     * Create client with the specified address and port
     * @param address
     * @param port
     */
    private void createClient(String address, int port)
    {
        try
        {
            System.out.println("Connecting to " + address + " at port " + port + "...");
            Socket socket = new Socket(address, port);
            System.out.println("Connected!");

            // Create streams...
            // outToServer writes to the server... true means autoflush the buffer
            PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);

            // inFromServer reads from the server...
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // inFromConsole reads from the console...
            BufferedReader inFromConsole = new BufferedReader(new InputStreamReader(System.in));

            // Send messages made by user until they hit enter on a blank line
            System.out.println("You may now type message to the server. Enter in a blank line to stop.");
            String userIn;
            while ((userIn = inFromConsole.readLine()) != null)
            {
                outToServer.println(userIn); // Send to server
            }

            // Close
            outToServer.close();
            inFromServer.close();
            inFromConsole.close();
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
