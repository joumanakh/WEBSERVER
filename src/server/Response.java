package server;

import java.util.Objects;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

public class Response {
	private Header header;
	private String body;
	public Response(Header header, String body) throws CloneNotSupportedException {
		this.header = (Header)header.clone();
		this.body = body;
	}
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
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
		return Objects.hash(body, header);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Response other = (Response) obj;
		return Objects.equals(body, other.body) && Objects.equals(header, other.header);
	}
	@Override
	public String toString() {
		return "Response [header=" + header + ", body=" + body + "]";
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Response r=(Response)super.clone();
		r.header = (Header)header.clone();
		r.body = body;
		return r;
	}
	public String responseToJason() {
		
		String jasonResponse=new Gson().toJson(this);
		return jasonResponse;
	}
}
