package server;

import java.io.IOException;
import java.net.ServerSocket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		int port=5553;
		Server server =new Server(port);
		  // JsonObject json = new JsonParser().parse("{\"parameters\":{\"filePath\":\"C:\\\\Users\\\\user\\\\OneDrive\\\\Desktop\\\\joumanaServer.txt\",\"action\":\"downloadFile\"},\"header\":0,\"body\":\"joumana\"}").getAsJsonObject();
		   //JsonObject json = new JsonParser().parse("{\"parameters\":{\"filePath\":\"C:\\\\Users\\\\user\\\\OneDrive\\\\Desktop\\\\courses\",\"action\":\"viewFolderContent\"},\"header\":0}").getAsJsonObject();
		   //JsonObject json = new JsonParser().parse("{\"parameters\":{\"filePath\":\"C:\\\\Users\\\\user\\\\OneDrive\\\\Desktop\\\\courses\\\\forDelete.txt\",\"action\":\"deleteFile\"},\"header\":0}").getAsJsonObject();
		    // JsonObject json = new JsonParser().parse("{\"parameters\":{\"filePath\":\"C:\\\\Users\\\\user\\\\OneDrive\\\\Desktop\\\\joumanaClient.txt\",\"action\":\"fileUploading\"},\"header\":0}").getAsJsonObject();
		  // server.exam(json);
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
