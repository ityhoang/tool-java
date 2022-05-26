package testcode;

import org.openqa.selenium.WebElement;

//	100051982284336|$dCjtvbod705|JR6X QA6G WLVE ESF5 DMA7 SHF6 EKBO WJG6
//	100049024217464|0356650605ndtTH|QRWW C74C WOGF DSE7 LH4P AKQH JYPX AESE
//	100049024217464|0356650605ndtTH|QRWW C74C WOGF DSE7 LH4P AKQH JYPX AESE
//	100048866105881|marcussaunders1529|2HEQ T34V PSI2 UNFB NYNN LOM3 BPOD RXPK
//	100048866646154|0356650605ndtTH|CWXF 6WGR CS5L AGWT P2LA 5S46 W3UW 55WO
//	100049019148384|0356650605ndtTH|SDM5 RV42 QGDI 5D7C MD6X GOKG 6NRW L4D5
//	100051643727302|@admin@
//	100049019148384|0356650605ndtTH|SDM5 RV42 QGDI 5D7C MD6X GOKG 6NRW L4D5
//	100051634396311|@admin@| |ha550|01208162405TH
//	100052076659662|$Ndaahnt523|6JV2 SJN5 MM2J FSCF OZ3B DP5N 5F56 YBQ3
//	public static String user = "100052076659662";//100049366924252
//	public static String pass = "$Ndaahnt523";//leeestrada2348
//	public static String token = "6JV2 SJN5 MM2J FSCF OZ3B DP5N 5F56 YBQ3";//2WR7 WEAS AZAE HYNM A7AY FI6S 2XZD I3VV

public class Nhiemvu {
	public String id_post;
	public String feed;
	public String Noidung;
	public WebElement id;
	public Nhiemvu(String id_post,String feed, WebElement id,String Noidung) {
		this.id_post = id_post;
		this.feed = feed;
		this.id = id;
		this.Noidung = Noidung;
	}
	public String getId_post() {
		return id_post;
	}
	public void setId_post(String id_post) {
		this.id_post = id_post;
	}
	public String getFeed() {
		return feed;
	}
	public void setFeed(String feed) {
		this.feed = feed;
	}
	public WebElement getId() {
		return id;
	}
	public void setId(WebElement id) {
		this.id = id;
	}
	public String getNoidung() {
		return Noidung;
	}
	public void setNoidung(String noidung) {
		Noidung = noidung;
	}
}
