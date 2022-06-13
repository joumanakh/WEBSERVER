package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
	public static void main(String[] args) throws IOException {
		int port=5557;
		Server server =new Server(port);
		try {
			ServerSocket serverSocket = new ServerSocket(port);
            while(true) {
		server.connection(serverSocket);
		}
            }
            catch (IOException  e) {
                e.printStackTrace();
            }
	}
}
