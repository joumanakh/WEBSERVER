package server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class FileOperations {
 private String file;

public FileOperations(String file) {
	this.file = file;
}

public String getFile() {
	return file;
}

public void setFile(String file) {
	this.file = file;
}

@Override
public int hashCode() {
	return Objects.hash(file);
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
	return Objects.equals(file, other.file);
}

@Override
public String toString() {
	return "FileOperations [file=" + file + "]";
}
 
@Override
protected Object clone() throws CloneNotSupportedException {
	FileOperations f=(FileOperations)super.clone();
	f.file = file;
	return f;
}

public void downloadFile(){
	
	
}
public void fileUploading(String body) throws IOException {
    Files.write(Paths.get(file), body.getBytes(), StandardOpenOption.CREATE);
}

public void dispalyListFolders(){}
public void viewFolderContent(){}
public void deleteFile(){}
}
