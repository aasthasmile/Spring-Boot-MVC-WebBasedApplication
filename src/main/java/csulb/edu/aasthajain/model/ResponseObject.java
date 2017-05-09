package csulb.edu.aasthajain.model;

public final class ResponseObject {
	private boolean success;
	private String message;
	
	public ResponseObject(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}
	
	public String getMessage() {
		return message;
	}

}
