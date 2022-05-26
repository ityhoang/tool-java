package testcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.SearchContext;

public class ApiTds {
	public String user;
	public String pass;
	public String nhiemvu;
	public String id_baiviet;
	public String id_fb;
	public String camxuc;
	public String GetNv = "https://reqtds.000webhostapp.com/getnv.php?";
	public String NhanXu = "https://reqtds.000webhostapp.com/nhantien.php?";
	public String Setting = "https://reqtds.000webhostapp.com/setting.php?";
	public String Sodu = "https://reqtds.000webhostapp.com/sodu.php?";
	public ApiTds() {}
	public ApiTds(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getNhiemvu() {
		return nhiemvu;
	}
	public void setNhiemvu(String nhiemvu) {
		this.nhiemvu = nhiemvu;
	}
	public String getId_baiviet() {
		return id_baiviet;
	}
	public void setId_baiviet(String id_baiviet) {
		this.id_baiviet = id_baiviet;
	}
	public String getId_fb() {
		return id_fb;
	}
	public void setId_fb(String id_fb) {
		this.id_fb = id_fb;
	}
	public String getCamxuc() {
		return camxuc;
	}
	public void setCamxuc(String camxuc) {
		this.camxuc = camxuc;
	}
	public String getnv() throws IOException {
		URL obj = new URL(GetNv+"user="+getUser()+"&nv="+getNhiemvu());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setDoOutput(true);
		con.getContent();
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} else {
			return "error";
		}
	}
	public String nhanxu(String idpost,String camxuc) throws IOException {
		URL obj = new URL(NhanXu+"user="+getUser()+"&pass="+getPass()+"&nv="+getNhiemvu()+"&id="+idpost+"&camxuc="+camxuc);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setDoOutput(true);
		con.getContent();
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} else {
			return "error";
		}
	}
	public String sodu() throws IOException {
		URL obj = new URL(Sodu+"user="+getUser());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setDoOutput(true);
		con.getContent();
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} else {
			return "error";
		}
	}
	public String changeacc(String idfb) throws IOException {
		URL obj = new URL(Setting+"user="+getUser()+"&id="+idfb);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setDoOutput(true);
		con.getContent();
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} else {
			return "error";
		}
	}
	public static ArrayList<String> search_api(String s) {
		if(s.length()>4) {
			int d=0;
			s = s.substring(2, s.length());
			ArrayList<String> list_nv = new ArrayList<String>();
			for(int i=0; i<s.length(); i++) {
				if(s.charAt(i) == ',') {
					d++;
				}
			}
			for(int j=0; j<d; j++) {
				list_nv.add(s.substring(0,s.indexOf(',')-1));
				s=s.substring(s.indexOf(',')+2, s.length());
			}
			list_nv.add(s.substring(0,s.length()-2));
			return list_nv;
		}else {
			return null;
		}
	}
}
