package server;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Server {
	private Request request;
	private Response response;
	private FileOperations fileOperations;
	private static int port;
	public Server(Request request, Response response,FileOperations fileOperations) throws CloneNotSupportedException {
		this.request = (Request)request.clone();
		this.response =(Response)response.clone();
	    this.fileOperations=(FileOperations)fileOperations.clone();
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) throws CloneNotSupportedException {
		this.request = (Request)request.clone();
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) throws CloneNotSupportedException {
		this.response =(Response)response.clone();
	}
	
	public FileOperations getFileOperations() {
		return fileOperations;
	}
	public void setFileOperations(FileOperations fileOperations) {
		this.fileOperations = fileOperations;
	}
	@Override
	public int hashCode() {
		return Objects.hash(request, response);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Server other = (Server) obj;
		return Objects.equals(request, other.request) && Objects.equals(response, other.response);
	}
	@Override
	public String toString() {
		return "Server [request=" + request + ", response=" + response + "]";
	}
	//***************************functions***************
	public void makeRequestFromJason(JsonObject obj) throws IOException {
		Gson gson = new Gson(); 
		this.request = gson.fromJson(obj, Request.class);
		chosenAction();
		

	}
	
	public void chosenAction() throws IOException {
		this.fileOperations.setFilePath(this.request.getParameters().getFilePath());
		switch(this.request.getParameters().getAction()) {
		case downloadFile:
			this.fileOperations.downloadFile();
			break;
			
		case fileUploading:
			this.fileOperations.fileUploading(this.request.getBody());
		break;
		
		case deleteFile:
			this.fileOperations.deleteFile();
		break;
		
		case viewFolderContent:
			this.fileOperations.viewFolderContent();
		break;
		
		default:
			return;
		
	}
	}
		
	public void addToLog() {
			//if(response.getHeader().getResponseCode()==ResponseCode.ok) {
				Log.saveRequest(request);
				
			//}
			
		}
	
	public void connection(JSONObject obj,int port) {
		this.port=port;
		try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true) {
            	Socket socket = serverSocket.accept();
            	 InputStream is = socket.getInputStream();
                 DataInputStream dis = new DataInputStream(is);
                 String s = dis.readUTF();
                 JsonObject json = new JsonParser().parse(s).getAsJsonObject();
                 this.request.makeRequestFromJason(json);
                 //**************************
                 OutputStream os = socket.getOutputStream();
                 DataOutputStream dos = new DataOutputStream(os);
                 String result = this.getResponse().responseToJason();
                 addToLog();
                 dos.writeUTF(result);
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
            }
            	
            
		
	}
	

	

