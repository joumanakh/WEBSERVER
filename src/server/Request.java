package server;

import java.util.Objects;

public class Request {
	private Parameters parameters;
	private int header;
	private String body;
	public Request(Parameters parameters, int header, String body) throws CloneNotSupportedException {

		this.parameters = (Parameters)parameters.clone();
		this.header = header;
		this.body = body;
	}
	public Parameters getParameters() {
		return parameters;
	}
	public void setParameters(Parameters parameters)throws CloneNotSupportedException {
		this.parameters = (Parameters)parameters.clone();
	}
	public int getHeader() {
		return header;
	}
	public void setHeader(int header) {
		this.header = header;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public int hashCode() {
		return Objects.hash(body, header, parameters);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return Objects.equals(body, other.body) && header == other.header
				&& Objects.equals(parameters, other.parameters);
	}
	@Override
	public String toString() {
		return "Request [parameters=" + parameters + ", header=" + header + ", body=" + body + "]";
	}
	
	

}
