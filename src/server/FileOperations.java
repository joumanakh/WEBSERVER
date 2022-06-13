package server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class FileOperations {
 private String filePath;
 private Response response;

public FileOperations(String filePath) {
	this.filePath = filePath;
}

public String getFilePath() {
	return filePath;
}

public void setFilePath(String filePath) {
	this.filePath = filePath;
}

public Response getResponse() {
	return response;
}

public void setResponse(Response response) throws CloneNotSupportedException {
	this.response =(Response)response.clone();
}


@Override
public int hashCode() {
	return Objects.hash(filePath);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	FileOperations other = (FileOperations) obj;
	return Objects.equals(filePath, other.filePath);
}

@Override
public String toString() {
	return "FileOperations [filePath=" + filePath + "]";
}
 
@Override
protected Object clone() throws CloneNotSupportedException {
	FileOperations f=(FileOperations)super.clone();
	f.filePath = filePath;
	return f;
}

public void downloadFile()  {
    String content;
	try {
		content = new String(Files.readAllBytes(Paths.get(filePath)));
		response.setBody(content);
		response.getHeader().setResponseCode(ResponseCode.ok);
	} catch (IOException e) {
		response.getHeader().setResponseCode(ResponseCode.InternalServerError);
		//e.printStackTrace();
	}
    
}
public void fileUploading(String body)  {
    try {
		Files.write(Paths.get(filePath), body.getBytes(), StandardOpenOption.CREATE);
		response.getHeader().setResponseCode(ResponseCode.ok);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		response.getHeader().setResponseCode(ResponseCode.InternalServerError);
		//e.printStackTrace();
	}
}

//public void displayListFolders(){}


public void viewFolderContent()  {
	
	String[] pathnames;
	  File f = new File(filePath);
      pathnames = f.list();
      String body="";
      for (String pathname : pathnames) {
    	  body+=pathname+"/n";
    	  
      }
      response.setBody(body);
	
}

public void deleteFile(){
	File f= new File(filePath);
	if(!f.delete()) {
		response.getHeader().setResponseCode(ResponseCode.InternalServerError);
	}
	else {response.getHeader().setResponseCode(ResponseCode.ok);}
}


public boolean error404() throws IOException {
      Path path = Paths.get(filePath);
    if(!Files.exists(path)) {
    	  response.getHeader().setResponseCode(ResponseCode.notFound);
    	  return false;
      }
    return true;
}
}
