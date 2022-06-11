package server;

public enum Action {
	downloadFile(1),fileUploading(2),deleteFile(3),viewFolderContent(4);
	private int value;
private Action(int value) {
	this.value=value;
}
}
