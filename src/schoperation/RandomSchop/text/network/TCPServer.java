package schoperation.RandomSchop.text.network;

import java.io.*;
import java.net.InetAddress;
import java.net.*;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPServer
{
    /*
        Server for TCP test
     */
    public void askForInfo()
    {
        Scanner scanner = new Scanner(System.in);

        // Grab IP
        InetAddress ip = null;
        try
        {
            ip = InetAddress.getLocalHost();
            System.out.println("Your IPv4 Address is: " + ip);
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }

        // Ask for port
        System.out.println("Port number?");
        int port = scanner.nextInt();
        scanner.close();

        startServer(port, ip);
    }

    /**
     * Starts a simple TCP server socket on localhost at the designated port.
     * @param port
     */
    private void startServer(int port, InetAddress ip)
    {
        try
        {
            // Create new ServerSocket at specified port, and listen for requests from that port
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Created new server at IP: " + ip.getHostAddress() + ", port " + port);
            System.out.println("Waiting for client...");

            // When we get a request from some client, accept it
            // This method blocks execution until it receives a connection.
            Socket socket = serverSocket.accept();
            System.out.println("Accepted client! IP: " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());

            // Create streams...
            // First one writes text to the client, second one reads text from the client
            BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send an acknowlegement
            System.out.println("About to send acknowledgement...");
            outputStream.write("Hello from the other siiiiide...");

            // Wait for input...
            System.out.println("Waiting for client input...");
            while (!inputStream.ready())
                ;

            // Start reading and print it to console
            String line = "";
            while (!line.toLowerCase().equals("over"))
            {
                line = inputStream.readLine();
                System.out.println(line);
            }
            System.out.println("Closing server...");

            // Close the connection
            socket.close();
            inputStream.close();
            outputStream.close();
            serverSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
