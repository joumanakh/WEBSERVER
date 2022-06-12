package server;

import java.util.Objects;

public class Header {
	private int requestId;
	private ResponseCode responseCode;
	private String contentType;
	public Header(int requestId, ResponseCode responseCode, String contentType) {
		this.requestId = requestId;
		this.responseCode = responseCode;
		this.contentType = contentType;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId=requestId;
	}
	public ResponseCode getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	@Override
	public int hashCode() {
		return Objects.hash(contentType, requestId, responseCode);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Header other = (Header) obj;
		return Objects.equals(contentType, other.contentType) && Objects.equals(requestId, other.requestId)
				&& responseCode == other.responseCode;
	}
	@Override
	public String toString() {
		return "Header [requestId=" + requestId + ", responseCode=" + responseCode + ", contentType=" + contentType
				+ "]";
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Header h=(Header)super.clone();
		h.requestId = requestId;
		h.responseCode = responseCode;
		h.contentType = contentType;
		return h;
	}
	

}
