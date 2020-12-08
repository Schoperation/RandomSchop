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
            System.out.println("Connected! Let's see if we can get the acknowledgement...");

            // Create streams...
            // First one writes text to the server, second one reads text from the server
            BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Wait for input...
            System.out.println("Waiting for server acknowledgement...");
            while (!inputStream.ready())
                ;
            System.out.println(inputStream.readLine());

            // Now ask for message to send
            Scanner scanner = new Scanner(System.in);
            String line = "";
            while (!line.toLowerCase().equals("over"))
            {
                line = scanner.nextLine();
                outputStream.write(line);
            }

            // Close
            outputStream.close();
            inputStream.close();
            scanner.close();
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
