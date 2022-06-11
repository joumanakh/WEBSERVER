package server;

import java.util.Objects;

public class Parameters {
	private String filePath;
	private String contentType;
	private Action action;
	public Parameters(String filePath, String contentType, Action action) {
		this.filePath = filePath;
		this.contentType = contentType;
		this.action = action;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	@Override
	public int hashCode() {
		return Objects.hash(action, contentType, filePath);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parameters other = (Parameters) obj;
		return Objects.equals(action, other.action) && Objects.equals(contentType, other.contentType)
				&& Objects.equals(filePath, other.filePath);
	}
	@Override
	public String toString() {
		return "Parameters [filePath=" + filePath + ", contentType=" + contentType + ", action=" + action + "]";
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Parameters p=(Parameters)super.clone();
		p.filePath = filePath;
		p.contentType = contentType;
		p.action = action;
		return p;
	}
	
	
}
