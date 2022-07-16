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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class Server {
	ServerSocket serverSocket;
	Socket socket;
	private Request request=new Request();
	private Response response=new Response() ;
	private FileOperations fileOperations=new FileOperations();
	private static int port;
	public Server(int port) {
		this.port=port;
		
	}
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
	/*public void makeRequestFromJson(JsonObject obj) throws IOException {
		Gson gson = new Gson(); 
		this.request = gson.fromJson(obj, Request.class);
		this.response.getHeader().setAction(this.request.getParameters().getAction());
		errors();
	}*/
	
	public void errors() throws IOException {
		System.out.println(this.request.getParameters().getFilePath());
		this.fileOperations.setFilePath(this.request.getParameters().getFilePath());
		if(this.fileOperations.error404()) {
		chosenAction();}
	}
	
	public void chosenAction() throws IOException {
		
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
		this.response=this.fileOperations.getResponse();
		this.response.getHeader().setAction(this.request.getParameters().getAction());
		outputConnection();
		//exam2();
	}
		
	public void addToLog() {
			//if(response.getHeader().getResponseCode()==ResponseCode.ok) {
				Log.saveRequest(request);
				 System.out.println(Log.getInstance());
				
			//}
			
		}
	public void makeRequestFromJson(JsonObject obj) {
		Gson gson = new Gson(); 
		this.request = gson.fromJson(obj, Request.class);
		
	}
	public void connection(ServerSocket serverSocket) throws IOException {
		//this.port=port;
		//try {
           // serverSocket = new ServerSocket(port);
           // while(true) {
            	 socket = serverSocket.accept();
            	 InputStream is = socket.getInputStream();
                 DataInputStream dis = new DataInputStream(is);
                 String s = dis.readUTF();
                 JsonObject json = new JsonParser().parse(s).getAsJsonObject();
                 makeRequestFromJson(json);
                 this.response.getHeader().setAction(this.request.getParameters().getAction());
                 this.response.getHeader().setFilePath(this.request.getParameters().getFilePath());
                 System.out.println( this.response);
         		errors();
                 //**************************
          //  }
        //} catch (IOException  e) {
         //   e.printStackTrace();
        //}
            }
	
	public void exam(JsonObject json) throws IOException, CloneNotSupportedException {
		makeRequestFromJson(json);
		//this.request=(Request)r.clone();
		 System.out.println( this.request);
         this.response.getHeader().setAction(this.request.getParameters().getAction());
        
         errors();
	}
	//public void inputConnection() {}
	public void outputConnection() {
		try {
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        String result = this.getResponse().responseToJason();
        addToLog();
        dos.writeUTF(result);
           }
      catch (IOException  e) {
      e.printStackTrace();
        }
	}
	public void exam2()  {
		 String result = this.getResponse().responseToJason();
		 System.out.println(this.getResponse().responseToJason());
		 JsonObject json = new JsonParser().parse(result).getAsJsonObject();
		 responseToObject(json);
	
	     addToLog();
	    
		
	}
            	
            
		
	
	

//***********************************************************fordelete************************************************	
	public void responseToObject(JsonObject obj) {
		Gson gson = new Gson(); 
		Response r = gson.fromJson(obj, Response.class);
		//System.out.println(r.getBody());
		displayResultsOnConsole(r);
	}
	public void displayResultsOnConsole(Response response){
		switch(response.getHeader().getResponseCode()) {
		case ok:
			lastImplementationForDisplay(response);
			
			break;
		case notFound:
			System.out.println("Error 404 (Not Found)");
			break;
		case InternalServerError:
			System.out.println("Error 500 (Internal Server Error)");
			break;
		case NotImplemented:
			System.out.println("Error 501 (Not Implemented)");
			break;
		}
	
	}
public void lastImplementationForDisplay(Response response) {
	switch(response.getHeader().getAction()) {
	case downloadFile:
		   try {
				Files.write(Paths.get("C:\\Users\\user\\OneDrive\\Desktop\\joumanaClient.txt"), response.getBody().getBytes(), StandardOpenOption.CREATE);
				System.out.println("downloaded successfully");
			} catch (IOException e) {
				System.out.println("Error download into local file");
				// TODO Auto-generated catch block
				}
		break;
	case fileUploading:
		System.out.println("uploaded successfully");
		break;
	case deleteFile:
		System.out.println("deleted successfully");
		break;
	case viewFolderContent:
		System.out.println("Folder Content: ");
		System.out.println(response.getBody());
		break;
	
	}
}
}
//***********************************************************fordelete************************************************
