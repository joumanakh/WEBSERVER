package server;

public enum ResponseCode {
	ok(200),notFound(404),MethodNotAllowed(405),InternalServerError(500),NotImplemented(501);
	private int value;
private ResponseCode(int value) {
	this.value=value;
}
}
