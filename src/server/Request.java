package server;

import java.io.IOException;
import java.util.Objects;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Request {
	private static int requestId=0;
	private Parameters parameters;
	private int header;
	private String body;
	public Request(Parameters parameters, String body) throws CloneNotSupportedException {

		this.parameters = (Parameters)parameters.clone();
		this.body = body;
	}
	public Request() {
		this.header=requestId;
		requestId++;
		
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
	@Override
	public Object clone() throws CloneNotSupportedException {
		Request r=(Request)super.clone();
		r.parameters = (Parameters)parameters.clone();
		r.header = header;
		r.body = body;
		return r;
	}
	
	public void makeRequestFromJason(JsonObject obj) {
		Gson gson = new Gson(); 
		Request r = gson.fromJson(obj, Request.class);
		
	}
	

}
