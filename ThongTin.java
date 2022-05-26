package testcode;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class ThongTin {
	public ArrayList<NickFb> nickfb;
	public String userttc;
	public String passttc;
	public WebDriver driverfb;
	public WebDriver driverttc;
	public String sodu = "0";
	public String soducu = "0";
	public String checkxu = "run";
	public String nhiemvu = "Đang cập nhật";
	public boolean tamtrang1 = true;
	public boolean tamtrang2 = true;
	public boolean tamtrang3 = true;
	public boolean tamtrang4 = true;
	public String nv_chon;
	public ThongTin(ArrayList<NickFb> nickfb, String userttc, String passtcc, String nv_chon) {
		this.nickfb = nickfb;
		this.userttc = userttc;
		this.passttc = passtcc;
		this.nv_chon = nv_chon;
	}

	public ArrayList<NickFb> getNickfb() {
		return nickfb;
	}

	public void setNickfb(ArrayList<NickFb> nickfb) {
		this.nickfb = nickfb;
	}

	public String getUserttc() {
		return userttc;
	}

	public void setUserttc(String userttc) {
		this.userttc = userttc;
	}

	public String getPassttc() {
		return passttc;
	}

	public void setPassttc(String passttc) {
		this.passttc = passttc;
	}

	public WebDriver getDriverfb() {
		return driverfb;
	}

	public void setDriverfb(WebDriver driverfb) {
		this.driverfb = driverfb;
	}

	public WebDriver getDriverttc() {
		return driverttc;
	}

	public void setDriverttc(WebDriver driverttc) {
		this.driverttc = driverttc;
	}
	
	public String getSodu() {
		return sodu;
	}

	public void setSodu(String sodu) {
		this.sodu = sodu;
	}
	
	public String getSoducu() {
		return soducu;
	}

	public void setSoducu(String soducu) {
		this.soducu = soducu;
	}
	
	public String getCheckxu() {
		return checkxu;
	}

	public void setCheckxu(String checkxu) {
		this.checkxu = checkxu;
	}

	public boolean getTamtrang1() {
		return tamtrang1;
	}

	public void setTamtrang1(boolean tamtrang1) {
		this.tamtrang1 = tamtrang1;
	}

	public boolean getTamtrang2() {
		return tamtrang2;
	}

	public void setTamtrang2(boolean tamtrang2) {
		this.tamtrang2 = tamtrang2;
	}

	public boolean getTamtrang3() {
		return tamtrang3;
	}

	public void setTamtrang3(boolean tamtrang3) {
		this.tamtrang3 = tamtrang3;
	}

	public boolean getTamtrang4() {
		return tamtrang4;
	}

	public void setTamtrang4(boolean tamtrang4) {
		this.tamtrang4 = tamtrang4;
	}

	public String getNhiemvu() {
		return nhiemvu;
	}

	public void setNhiemvu(String nhiemvu) {
		this.nhiemvu = nhiemvu;
	}

	public String getNv_chon() {
		return nv_chon;
	}

	public void setNv_chon(String nv_chon) {
		this.nv_chon = nv_chon;
	}
}
