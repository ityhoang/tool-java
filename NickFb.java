package testcode;

public class NickFb {
	public String userfb;
	public String passfb;
	public String token;
	public String tinhtrang = "active";
	public NickFb(String userfb, String passfb, String token) {
		this.userfb = userfb;
		this.passfb = passfb;
		this.token = token;
	}
	public String getUserfb() {
		return userfb;
	}
	public void setUserfb(String userfb) {
		this.userfb = userfb;
	}
	public String getPassfb() {
		return passfb;
	}
	public void setPassfb(String passfb) {
		this.passfb = passfb;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTinhtrang() {
		return tinhtrang;
	}
	public void setTinhtrang(String tinhtrang) {
		this.tinhtrang = tinhtrang;
	}
}
