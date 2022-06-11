package server;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Objects;

public class Server {
	private Request request=new Request();;
	private Response response;
	private FileOperations fileOperations;
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
	public void makeRequest(JSONObject obj) throws CloneNotSupportedException, IOException {
		this.request.setParameters(new Parameters((String)obj.get("path"),"...",(Action)obj.get("Required Task Number")));
		this.request.setBody((String)obj.get("body"));
		chosenAction();
		

	}
	
	public void chosenAction() throws IOException {
		this.fileOperations.setFile(this.request.getParameters().getFilePath());
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

	
}
