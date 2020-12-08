package schoperation.RandomSchop.text.network;

import java.util.Scanner;

public class NetworkTest
{
    /*
        Playing around with Java's basic networking capabilities
     */
    public void main()
    {
        // Ask if they are a client or server
        Scanner scanner = new Scanner(System.in);

        // Check if valid input
        String clientOrServer;
        while(true)
        {
            System.out.println("Client? (c) or Server? (s)");
            clientOrServer = scanner.nextLine();

            if (clientOrServer.toLowerCase().equals("c") || clientOrServer.toLowerCase().equals("s"))
                break;
            else
                System.out.println("Invalid input. Type either c for client or s for server.");
        }

        switch (clientOrServer.toLowerCase())
        {
            case "s":
                TCPServer server = new TCPServer();
                server.askForInfo();
                break;
            default:
                TCPClient client = new TCPClient();
                client.askForInfo();
                break;
        }
    }
}
