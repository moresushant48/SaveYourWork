package io.moresushant48.model;

public class Email {

	private static String myEmail = "moresushant48@gmail.com";
	
	private String name;
	private String email;
	private String message;
	
	public static String getMyEmail() {
		return myEmail;
	}
	public void setMyEmail(String myEmail) {
		Email.myEmail = myEmail;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
