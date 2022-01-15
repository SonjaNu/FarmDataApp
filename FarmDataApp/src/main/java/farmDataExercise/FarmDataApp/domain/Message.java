package farmDataExercise.FarmDataApp.domain;

//class for defining the format of the response message
//when endpoint gets a call, it returns the response in the proper format
public class Message {

	private String message;
	private String csvDownloadUri;

	public Message(String message, String csvDownloadUri) {
		this.message = message;
		this.csvDownloadUri = csvDownloadUri;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCsvDownloadUri() {
		return csvDownloadUri;
	}

	public void setCsvDownloadUri(String csvDownloadUri) {
		this.csvDownloadUri = csvDownloadUri;
	}

}
