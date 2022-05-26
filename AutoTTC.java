package testcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.*;
import javax.swing.plaf.synth.SynthSplitPaneUI;

import org.openqa.selenium.*;

public class AutoTTC {
	public ThongTin thongtin;
	public ApiTds api;
	public AutoTTC(ThongTin thongtin) {
		this.thongtin = thongtin;
	}
	public ThongTin getThongtin() {
		return thongtin;
	}
	public void setThongtin(ThongTin thongtin) {
		this.thongtin = thongtin;
	}
	public ApiTds getApi() {
		return api;
	}
	public void setApi(ApiTds api) {
		this.api = api;
	}
	public void setting() {
		try {			
			int checkacc = 0;
			boolean star = true;
			
			WebDriver drivertds = getThongtin().getDriverttc();
			WebDriver driver = getThongtin().getDriverfb();
			String user = getThongtin().getNickfb().get(0).getUserfb();
			String pass = getThongtin().getNickfb().get(0).getPassfb();
			String token = getThongtin().getNickfb().get(0).getToken();
			api = new ApiTds(getThongtin().getUserttc(),getThongtin().getPassttc());
			String hka = openfb(driver, user, pass, token);
			if(thongtin.getNickfb().size()>1) {
				while(hka.equals("die")) {
					getThongtin().getNickfb().get(0).setTinhtrang(hka);
					ArrayList<NickFb> acc = getThongtin().getNickfb();
					int i=0;
					while(i<acc.size()-1) {
						Collections.swap(acc, i+1, i);
						i++;
					}
					int dem = 0;
					for(NickFb check : acc) {
						if(check.getTinhtrang().equals("die")) {
							dem++;
						}
					}
					if(dem>=acc.size()) {
						hka = "die";
						getThongtin().setNhiemvu(hka);
						break;
					}
					getThongtin().setNickfb(acc);
					driver.manage().deleteAllCookies();
					user = getThongtin().getNickfb().get(0).getUserfb();
					pass = getThongtin().getNickfb().get(0).getPassfb();
					token = getThongtin().getNickfb().get(0).getToken();
					hka = openfb(driver,user,pass,token);
				}
			}
			getThongtin().getNickfb().get(0).setTinhtrang(hka);
			int id_nv=1;
			int solan = 0;
			while(hka.equals("live")) {
				if(id_nv==1) {
					solan = 5;
				}else if(id_nv==2 || id_nv==4) {
					solan = 3;
				}else if(id_nv == 3 || id_nv == 6) {
					solan = 2;
				}
				if(!this.thongtin.getTamtrang1() && !this.thongtin.getTamtrang2() && !this.thongtin.getTamtrang3() && !this.thongtin.getTamtrang4()) {
					if(getThongtin().getNickfb().size()>1) {
						checkacc = 2;
					}else {
						hka = "die";
						break;
					}
				}
				if(!this.thongtin.getTamtrang2()) {
					if(id_nv == 1 || id_nv==2) {
						id_nv=3; solan = 2;
					}
				}
				if(!this.thongtin.getTamtrang1()) {
					if(id_nv==4) {
						id_nv=6; solan = 2;
					}
				}
				if(!this.thongtin.getTamtrang3()) {
					if(id_nv==3) {
						id_nv=4; solan = 3;
					}
				}
				if(!this.thongtin.getTamtrang4()) {
					if(id_nv==6) {
						checkacc++;
						id_nv=1; solan = 5;
					}
				}
				if(this.thongtin.getNv_chon().contains("tds")) {
//					apitds(id_nv+"", solan);
					if(checkacc==2) {
						checkacc=0;
						if(thongtin.getNickfb().size()>1) {
							ArrayList<NickFb> acc = getThongtin().getNickfb();
							int demacc = 0;
							for(NickFb check : acc) {
								if(check.getTinhtrang().equals("die")) {
									demacc++;
								}
							}
							if(demacc>=thongtin.getNickfb().size()) {
								hka = "die";
								break;
							}
							String checklive = changeacc(driver);
							while(checklive.equals("die")) {
								checklive = changeacc(driver);
							}
						}
					}
					if(star) {
						logintds(drivertds);
						setupacc(drivertds);
						star = false;
					}
					opentds(id_nv+"",solan,drivertds);
					
				}else if(this.thongtin.getNv_chon().contains("ttc")) {
					openttc(id_nv+"",solan);
				}
				
				if(id_nv==6) {
					id_nv=0;
					checkacc++;
				}
				id_nv++;
				if(id_nv==5) {
					id_nv=6;
				}
				Thread.sleep(3000);		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void logintds(WebDriver drivertds) {
		try {
			String users = getThongtin().getUserttc();
			String passs = getThongtin().getPassttc();
			String texttds = running(drivertds, users, passs);
			while(texttds.toUpperCase().indexOf("Bạn".toUpperCase())>=0) {
				texttds = running(drivertds, users, passs);
			}
		} catch (Exception e) {
			logintds(drivertds);
		}
	}
	public String running(WebDriver drivertds, String users, String passs) {
		String texttds = "";
		try {
			drivertds.manage().deleteAllCookies();
			drivertds.get("https://traodoisub.com/");
			drivertds.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			drivertds.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
			Thread.sleep(4000);
			drivertds.findElement(By.cssSelector("#memberModal button")).click();
			Thread.sleep(2000);
			drivertds.findElement(By.id("username")).sendKeys(users);
			drivertds.findElement(By.id("password")).sendKeys(passs);
			drivertds.findElement(By.id("loginclick")).click();
			Thread.sleep(2000);
			texttds = drivertds.findElement(By.cssSelector(".mb-0")).getAttribute("innerText");
		} catch (Exception e) {
			running(drivertds, users, passs);
		}
		return texttds;
	}
	public void apitds(String id_nv,int solan) throws InterruptedException {
		WebDriver driver = getThongtin().getDriverfb();
		int chedo=0;
		String tamtrang = "";
		String nv_start = "";
		if(id_nv.equals("1")) {
			tamtrang = "Like chéo";
			nv_start = "like";
			chedo=0;
		}else if(id_nv.equals("6")){
			tamtrang = "Like Page chéo";
			nv_start = "page";
			chedo=3;
		}else if(id_nv.equals("4")){
			tamtrang = "Theo dõi chéo";
			nv_start = "sub";
			chedo=1;
		}else if(id_nv.equals("2")){
			tamtrang = "Cảm xúc chéo";
			nv_start = "cx";
			chedo=0;
		}else if(id_nv.equals("3")){
			tamtrang = "Comment chéo";
			nv_start = "cmt";
			chedo=2;
		}
		ArrayList<Nhiemvu> bot = new ArrayList<Nhiemvu>();
		ArrayList<String> list = new ArrayList<String>();
		try {
			thongtin.setNhiemvu(tamtrang);
			int sodu = Integer.parseInt(getApi().sodu());
			thongtin.setSodu(sodu+"");
			if(thongtin.getCheckxu().equals("run")) {
				thongtin.setSoducu(sodu+"");
				thongtin.setCheckxu("stop");
			}
			getApi().setNhiemvu(nv_start);
			String camxuc = "";
			list = getApi().search_api(getApi().getnv());
			if(list.size() != 0) {
				for (int i=0; i<list.size()-1; i++) {
					if(id_nv.equals("3") || id_nv.equals("2")) {
						if(list.get(i+1).indexOf("LOVE")>=0) {
							camxuc = "Yêu Thích";
						}else if(list.get(i+1).indexOf("ANGRY")>=0) {
							camxuc = "Phẫn Nộ"; 
						}else if(list.get(i+1).indexOf("SAD")>=0) {
							camxuc = "Buồn"; 
						}else if(list.get(i+1).indexOf("LIKE")>=0) {
							camxuc = "Thích"; 
						}
						Nhiemvu san = new Nhiemvu(list.get(i),camxuc,null,list.get(i+1));
						bot.add(san);
						i++;
					}else {
						Nhiemvu san = new Nhiemvu(list.get(i),"Thích",null,null);
						bot.add(san);
					}
				}
				int i=0;
				int demsl=1;
				String vote_id = "";
				String changecx = "";
				while(i<bot.size() && demsl<solan) {
					Thread.sleep(2000);
					String linkreq = "";
					if(bot.get(i).getId_post().indexOf('_')>0) {
						String story = bot.get(i).getId_post().substring(0,bot.get(i).getId_post().indexOf('_'));
						vote_id = story;
						String id = bot.get(i).getId_post().substring(bot.get(i).getId_post().indexOf('_')+1,bot.get(i).getId_post().length());
						linkreq = "https://mbasic.facebook.com/story.php?story_fbid="+id+"&id="+story;
					}else {
						vote_id = bot.get(i).getId_post();
						linkreq = "https://mbasic.facebook.com/"+bot.get(i).getId_post();
					}
					String shl = getnv(driver,bot.get(i).getFeed(),linkreq,chedo,bot.get(i).getNoidung());
					if(shl.equals("die")) {
						thongtin.setNhiemvu("fb die");
						break;
					}
					if(shl.equals("Chặn")) {
						if(tamtrang.toUpperCase().equals("Theo dõi chéo".toUpperCase())) {
							getThongtin().setTamtrang1(false);
						}else if(tamtrang.toUpperCase().equals("Cảm xúc chéo".toUpperCase()) || tamtrang.toUpperCase().equals("Like chéo".toUpperCase())){
							getThongtin().setTamtrang2(false);
						}else if(tamtrang.toUpperCase().equals("Bình luận chéo".toUpperCase())){
							getThongtin().setTamtrang3(false);
						} else if(tamtrang.toUpperCase().equals("Like page chéo".toUpperCase())){
							getThongtin().setTamtrang4(false);
						}
						break;
					}
					if(!shl.equals("loadlai")) {
						if(bot.get(i).getFeed().indexOf("Yêu Thích")>=0) {
							changecx = "LOVE";
						}else if(bot.get(i).getFeed().indexOf("Phẫn Nộ")>=0) {
							changecx = "ANGRY"; 
						}else if(bot.get(i).getFeed().indexOf("Buồn")>=0) {
							changecx = "SAD"; 
						}else if(bot.get(i).getFeed().indexOf("Thích")>=0) {
							changecx = "LIKE"; 
						}
						System.out.println(getApi().nhanxu(vote_id,changecx));
						Thread.sleep(1000);
						demsl++;
					}
					i++;
				}
				Thread.sleep(2000);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	public void openttc(String id_nv,int solan) {
		try {
			WebDriver drivers = getThongtin().getDriverttc();
			WebDriver driver = getThongtin().getDriverfb();
			String user = getThongtin().getUserttc();
			String pass = getThongtin().getPassttc();
			drivers.get("http://tuongtaccheo.com/");
			drivers.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			drivers.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			Thread.sleep(2000);
			drivers.findElement(By.cssSelector(".modal-dialog button")).click();
			Thread.sleep(2000);
			drivers.findElement(By.id("name")).sendKeys(user);
			
			drivers.findElement(By.id("password")).sendKeys(pass);
			
			drivers.findElement(By.cssSelector("form>[type=\"submit\"]")).click();
			Thread.sleep(1000);
			try {
				
				drivers.switchTo().alert().accept();
				Thread.sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
			}
//			chọn nhiệm vụ
			drivers.findElement(By.cssSelector(".navbar-header>button")).click();
			String sodu = drivers.findElement(By.cssSelector("#navbar.navbar-collapse>ul:nth-child(2)>li:nth-child(2) strong")).getAttribute("innerText");
			thongtin.setSodu(sodu);
			if(thongtin.getCheckxu().equals("run")) {
				thongtin.setSoducu(sodu);
				thongtin.setCheckxu("stop");
			}
			
			drivers.findElement(By.cssSelector("#navbar.navbar-collapse>ul>li:nth-child(2)")).click();
			WebElement get = drivers.findElement(By.cssSelector("#navbar.navbar-collapse>ul>li:nth-child(2)>.dropdown-menu>li:nth-child("+id_nv+")"));
			String tamtrang = get.getAttribute("innerText").toString().trim();
			thongtin.setNhiemvu(tamtrang);
			get.click();
			drivers.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			drivers.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			Thread.sleep(3000);
			try {
				String nv_doc = drivers.switchTo().alert().getText();
				
				drivers.switchTo().alert().accept();
				if(nv_doc.toUpperCase().equals("Cần làm lại nhiệm vụ đã hủy".toUpperCase())) {
					drivers.manage().deleteAllCookies();
					Thread.sleep(1000);
					drivers = getThongtin().getDriverttc();
					id_nv=7+"";
					drivers.get("http://tuongtaccheo.com/");
					drivers.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					drivers.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
					Thread.sleep(2000);
					drivers.findElement(By.cssSelector(".modal-dialog button")).click();
					Thread.sleep(2000);
					drivers.findElement(By.id("name")).sendKeys(user);
					
					drivers.findElement(By.id("password")).sendKeys(pass);
					
					drivers.findElement(By.cssSelector("form>[type=\"submit\"]")).click();
					Thread.sleep(1000);
//					chọn nhiệm vụ
					drivers.findElement(By.cssSelector(".navbar-header>button")).click();
					drivers.findElement(By.cssSelector("#navbar.navbar-collapse>ul>li:nth-child(2)")).click();
					WebElement getp = drivers.findElement(By.cssSelector("#navbar.navbar-collapse>ul>li:nth-child(2)>.dropdown-menu>li:nth-child("+id_nv+")"));
					String tamtrang_ll = getp.getAttribute("innerText").toString().trim();
					thongtin.setNhiemvu(tamtrang_ll);
					getp.click();
					drivers.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					drivers.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				}
			} catch (Exception e) {}
	
			String id_post = "";
			String camxuc = "";
			
			java.util.List<WebElement> texts1 = drivers.findElements(By.cssSelector("#dspost>div>div>div"));
			
			ArrayList<Nhiemvu> bot = new ArrayList<Nhiemvu>();
			int idnvnew = Integer.parseInt(id_nv);
			int d=0;
			int chedo = 0;
			String noidung = "";
			for(WebElement s : texts1) {
				camxuc = s.getAttribute("innerText").toString().trim();
				id_post = s.getAttribute("id").toString();
				int id_nd = 0;
				if(id_post.length()>0) {
					if(idnvnew==1) {
						camxuc = "Thích";
					}else if(idnvnew==2) {
						if(camxuc.indexOf("LOVE")>=0) {
							camxuc = "Yêu Thích";
						}else if(camxuc.indexOf("TỨC GIẬN")>=0) {
							camxuc = "Phẫn Nộ";
						}
					}else if(idnvnew==4) {
						camxuc = "Theo dõi";
						chedo=1;
					}else if(idnvnew==3) {
						camxuc = "Bình luận";
						chedo=2;
						noidung = drivers.findElement(By.cssSelector("[id=\""+id_post+"nd"+id_nd+"\"]")).getAttribute("value");
						int dr = checklink(noidung);
						while(dr == 0) {
							id_nd++;
							noidung = drivers.findElement(By.cssSelector("[id=\""+id_post+"nd"+id_nd+"\"]")).getAttribute("value");
							dr = checklink(noidung);
						}
					}else if(idnvnew==6) {
						camxuc = "Thích";
						chedo=3;
					}else if(idnvnew==7) {
						if(camxuc.toUpperCase().indexOf("LOVE".toUpperCase())>=0) {
							camxuc = "Yêu Thích";
							chedo = 0;
						}else if(camxuc.toUpperCase().indexOf("TỨC GIẬN".toUpperCase())>=0) {
							camxuc = "Phẫn Nộ";
							chedo = 0;
						}else if(camxuc.toUpperCase().indexOf("LIKE".toUpperCase())>=0) {
							camxuc = "Thích";
							chedo = 0;
						}else if(camxuc.toUpperCase().indexOf("Bình luận".toUpperCase())>=0) {
							camxuc = "Bình luận";
							chedo=2;
							noidung = drivers.findElement(By.cssSelector("[id=\""+id_post+"nd"+id_nd+"\"]")).getAttribute("value");
							int dr = checklink(noidung);
							while(dr == 0) {
								id_nd++;
								noidung = drivers.findElement(By.cssSelector("[id=\""+id_post+"nd"+id_nd+"\"]")).getAttribute("value");
								dr = checklink(noidung);
							}
						}else if(camxuc.toUpperCase().indexOf("Theo dõi".toUpperCase())>=0) {
							camxuc = "Theo dõi";
							chedo=1;
						}
					}
					
					
					Nhiemvu san = new Nhiemvu(id_post,camxuc,s.findElement(By.cssSelector(".btn")),noidung);
					bot.add(san);
				}
			}

			int i=0;
			int demsl=1;
			while(i<bot.size() && demsl<solan) {
				Thread.sleep(2000);
				String linkreq = "";
				if(bot.get(i).getId_post().indexOf('_')>0) {
					String story = bot.get(i).getId_post().substring(0,bot.get(i).getId_post().indexOf('_'));
					String id = bot.get(i).getId_post().substring(bot.get(i).getId_post().indexOf('_')+1,bot.get(i).getId_post().length());
					linkreq = "https://mbasic.facebook.com/story.php?story_fbid="+id+"&id="+story;
				}else {
					linkreq = "https://mbasic.facebook.com/"+bot.get(i).getId_post();
				}
				String shl = getnv(driver,bot.get(i).getFeed(),linkreq,chedo,bot.get(i).getNoidung());
				if(shl.equals("die")) {
					thongtin.setNhiemvu("fb die");
					break;
				}
				if(shl.equals("Chặn")) {
					if(tamtrang.toUpperCase().equals("Theo dõi chéo".toUpperCase())) {
						getThongtin().setTamtrang1(false);
					}else if(tamtrang.toUpperCase().equals("Cảm xúc chéo".toUpperCase()) || tamtrang.toUpperCase().equals("Like chéo".toUpperCase())){
						getThongtin().setTamtrang2(false);
					}else if(tamtrang.toUpperCase().equals("Bình luận chéo".toUpperCase())){
						getThongtin().setTamtrang3(false);
					} else if(tamtrang.toUpperCase().equals("Like page chéo".toUpperCase())){
						getThongtin().setTamtrang4(false);
					}
					break;
				}
				if(!shl.equals("loadlai")) {
					WebElement k = bot.get(i).getId();
					k.click();
					Thread.sleep(2000);
					ArrayList<String> tabsa = new ArrayList<String> (drivers.getWindowHandles());
					drivers.switchTo().window(tabsa.get(1));
					drivers.close();
					drivers.switchTo().window(tabsa.get(0));
					Thread.sleep(1000);          
					if(chedo==0 || chedo==2) {
						drivers.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[1]/div/div["+(i+1)+"]/div/div/button")).click();
					}else if(chedo==1 || chedo==3) {			
						drivers.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div/div["+(i+1)+"]/div/div/button")).click();
					}	
					Thread.sleep(2000);
					
					demsl++;
				}
				i++;
			}
			Thread.sleep(4000);
			drivers.manage().deleteAllCookies();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void opentds(String id_nv,int solan, WebDriver drivers) {
		try {
			WebDriver driver = getThongtin().getDriverfb();
//			chọn nhiệm vụ
			int sodu = Integer.parseInt(getApi().sodu());
			thongtin.setSodu(sodu+"");
			if(thongtin.getCheckxu().equals("run")) {
				thongtin.setSoducu(sodu+"");
				thongtin.setCheckxu("stop");
			}
			String link="";
			String tamtrang = "";
			String nv_start = "";
			if(id_nv.equals("1")) {
				drivers.get("https://traodoisub.com/ex/like");
				tamtrang = "Like chéo";
				nv_start = "like";
				link = "#listlike>div>div>div";
			}else if(id_nv.equals("6")){
				drivers.get("https://traodoisub.com/ex/fanpage");
				tamtrang = "Like Page chéo";
				nv_start = "page";
				link = "#listpage>div>div>div";
			}else if(id_nv.equals("4")){
				drivers.get("https://traodoisub.com/ex/follow");
				tamtrang = "Theo dõi chéo";
				nv_start = "sub";
				link = "#listsub>div>div>div";
			}else if(id_nv.equals("2")){
				drivers.get("https://traodoisub.com/ex/reaction");
				tamtrang = "Cảm xúc chéo";
				nv_start = "cx";
				link = "#listcx>div>div>div";
			}else if(id_nv.equals("3")){
				drivers.get("https://traodoisub.com/ex/comment");
				tamtrang = "Comment chéo";
				nv_start = "cmt";
				link = "#listcx>div>div>div:nth-child(1)>div>div";
			}
			getApi().setNhiemvu(nv_start);
			thongtin.setNhiemvu(tamtrang);
			
			drivers.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
			Thread.sleep(1000);

			String id_post = "";
			String camxuc = "";
			
			java.util.List<WebElement> texts1 = drivers.findElements(By.cssSelector(link));
			
			ArrayList<Nhiemvu> bot = new ArrayList<Nhiemvu>();
			int idnvnew = Integer.parseInt(id_nv);
			int d=0;
			int chedo = 0;
			String noidung = "";
			for(WebElement s : texts1) {
				camxuc = s.getAttribute("innerText").toString().trim();
				id_post = s.getAttribute("id").toString();
				int id_nd = 0;
				if(id_post.length()>0) {
					if(idnvnew==1) {
						camxuc = "Thích";
					}else if(idnvnew==2) { 
						if(camxuc.indexOf("LOVE")>=0) {
							camxuc = "Yêu Thích";
						}else if(camxuc.indexOf("ANGRY")>=0) {
							camxuc = "Phẫn Nộ"; 
						}else if(camxuc.indexOf("SAD")>=0) {
							camxuc = "Buồn"; 
						}else if(camxuc.indexOf("LIKE")>=0) {
							camxuc = "Thích"; 
						}
					}else if(idnvnew==4) {
						camxuc = "Theo dõi";
						chedo=1;
					}else if(idnvnew==3) {
						camxuc = "Bình luận";
						chedo=2;
						noidung = drivers.findElement(By.cssSelector("[id=\""+id_post+"n\"]")).getAttribute("value");
					}else if(idnvnew==6) {
						camxuc = "Thích";
						chedo=3;
					}
					
					Nhiemvu san = new Nhiemvu(id_post,camxuc,s.findElement(By.cssSelector("button")),noidung);
					bot.add(san);
				}
			}

			int i=0;
			int demsl=1;
			String vote_id = "";
			String changecx = "";
			while(i<bot.size() && demsl<solan) {
				Thread.sleep(2000);
				String linkreq = "";
				if(bot.get(i).getId_post().indexOf('_')>0) {
					String story = bot.get(i).getId_post().substring(0,bot.get(i).getId_post().indexOf('_'));
					vote_id = story;
					String id = bot.get(i).getId_post().substring(bot.get(i).getId_post().indexOf('_')+1,bot.get(i).getId_post().length());
					linkreq = "https://mbasic.facebook.com/story.php?story_fbid="+id+"&id="+story;
				}else {
					vote_id = bot.get(i).getId_post();
					linkreq = "https://mbasic.facebook.com/"+bot.get(i).getId_post();
				}
				String shl = getnv(driver,bot.get(i).getFeed(),linkreq,chedo,bot.get(i).getNoidung());
				if(shl.equals("die")) {
					thongtin.setNhiemvu("fb die");
					break;
				}
				if(shl.equals("Chặn")) {
					if(tamtrang.toUpperCase().equals("Theo dõi chéo".toUpperCase())) {
						getThongtin().setTamtrang1(false);
					}else if(tamtrang.toUpperCase().equals("Cảm xúc chéo".toUpperCase()) || tamtrang.toUpperCase().equals("Like chéo".toUpperCase())){
						getThongtin().setTamtrang2(false);
					}else if(tamtrang.toUpperCase().equals("Bình luận chéo".toUpperCase())){
						getThongtin().setTamtrang3(false);
					} else if(tamtrang.toUpperCase().equals("Like page chéo".toUpperCase())){
						getThongtin().setTamtrang4(false);
					}
					break;
				}
				if(!shl.equals("loadlai")) {
					WebElement k = bot.get(i).getId();
					k.click();
					Thread.sleep(2000);
					ArrayList<String> tabsa = new ArrayList<String> (drivers.getWindowHandles());
					drivers.switchTo().window(tabsa.get(1));
					drivers.close();
					drivers.switchTo().window(tabsa.get(0));
					Thread.sleep(1000);          
					if(chedo==0 || chedo==1 || chedo==3) {
						drivers.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/div/div/div["+(i+1)+"]/div/button")).click();
					}else if(chedo==2) {		
						drivers.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/div/div/div["+(i+1)+"]/div[1]/div/div/button")).click();
					}	
//					if(bot.get(i).getFeed().indexOf("Yêu Thích")>=0) {
//						changecx = "LOVE";
//					}else if(bot.get(i).getFeed().indexOf("Phẫn Nộ")>=0) {
//						changecx = "ANGRY"; 
//					}else if(bot.get(i).getFeed().indexOf("Buồn")>=0) {
//						changecx = "SAD"; 
//					}else if(bot.get(i).getFeed().indexOf("Thích")>=0) {
//						changecx = "LIKE"; 
//					}
//					getApi().nhanxu(vote_id, changecx);
					Thread.sleep(2000);
					demsl++;
				}
				i++;
			}
			Thread.sleep(4000);
		} catch (Exception e) {
			WebDriver drivertds = getThongtin().getDriverttc();
			opentds(id_nv+"",solan,drivertds);
		}
	}
	public void setupacc(WebDriver drivers) {
		try {
			ArrayList<NickFb> acc = getThongtin().getNickfb();
			String user = acc.get(0).getUserfb().trim();
			String ktra = getApi().changeacc(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String openfb(WebDriver driver, String user,String pass,String fa2) {
		try {
			String thanchu = "Hello i think you guys have got some confusion in this and i want you to review my content and unlock my features are right to use. Thank you to the facebook team for taking care of this issue.";
			driver.manage().deleteAllCookies();
			driver.get("https://mbasic.facebook.com/");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			String parentGUID = driver.getWindowHandle();
			Thread.sleep(2000);
			driver.findElement(By.id("m_login_email")).sendKeys(user);
			driver.findElement(By.cssSelector("[type=\"password\"]")).sendKeys(pass);
			driver.findElement(By.cssSelector("[value=\"Đăng nhập\"]")).click();

			if(driver.getTitle().equals("Nhập mã đăng nhập để tiếp tục")) {
				String ma = giai2fa(driver,parentGUID,fa2);
				while(ma.equals("")) {
					ma = giai2fa(driver,parentGUID,fa2);
					Thread.sleep(3000);
				}
				nhapma(driver,parentGUID,ma);
			}
			String hka = "run";
			while(hka.equals("run")) {
				if(checkLogin(driver,driver.getTitle())==2) {
					thongtin.setNhiemvu("fb live");
					hka = "live";
					driver.get("https://www.facebook.com/help/contact/571927962827151");
					Thread.sleep(2000);
					driver.findElement(By.cssSelector("form:nth-child(1) textarea")).sendKeys(thanchu);
					driver.findElement(By.cssSelector("form:nth-child(1) [type=\"submit\"]")).click();
					return "live";
				}else if(checkLogin(driver,driver.getTitle())==3) {
					thongtin.setNhiemvu("fb die");
					hka = "die";
					return "die";
				}else if(checkLogin(driver,driver.getTitle())==0) {
					thongtin.setNhiemvu("fb die");
					hka = "die";
					return "die";
				}
			}
		} catch (Exception e) {return "die";}
		return "live";
	}
	public String changeacc(WebDriver driver) {
		String tinhtrang = "die";
		try {
			ArrayList<NickFb> acc = thongtin.getNickfb();
			int i=0;
			while(i<acc.size()-1) {
				Collections.swap(acc, i+1, i);
				i++;
			}
			String accs = acc.get(0).getTinhtrang().trim();
			String user = "";
			String pass = "";
			String fa2 = "";
			while(accs.equals("die")) {
				i=0;
				while(i<acc.size()-1) {
					Collections.swap(acc, i+1, i);
					i++;
				}
				accs = acc.get(0).getTinhtrang().trim();
			}
			getThongtin().setNickfb(acc);
			user = acc.get(0).getUserfb().trim();
			pass = acc.get(0).getPassfb().trim();
			fa2 = acc.get(0).getToken().trim();
			String ktra = getApi().changeacc(user);
			tinhtrang = openfb(driver, user, pass, fa2);
			acc.get(0).setTinhtrang(tinhtrang);
			getThongtin().setNickfb(acc);
			return tinhtrang;
		} catch (Exception e) {
			return tinhtrang;
		}
		
	}
	public static int checklink(String link) {
		if(link.indexOf("https://")>=0 || link.indexOf("http://")>=0 || link.equals("")) {
			return 0;
		}
		return 1;
	}
	public static String giai2fa(WebDriver driver, String parentGUID, String token) {
		String ma2fa = "";
		try {
			((JavascriptExecutor)driver).executeScript("window.open('http://2fa.live/', '-blank')");
			Thread.sleep(1000);
			Set<String> allGUID = driver.getWindowHandles();
			for(String guid : allGUID){
				if(!guid.equals(parentGUID)){
					driver.switchTo().window(guid);
					break;
				}
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.findElement(By.id("listToken")).sendKeys(token);
			driver.findElement(By.id("submit")).click();
			Thread.sleep(1000);
			String key = "";
			WebElement text = driver.findElement(By.id("output"));
			key = text.getAttribute("value");
			key = key.substring(key.indexOf("|")+1,key.length());
			ma2fa = key;
			driver.close();
			driver.switchTo().window(parentGUID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ma2fa;
	}
	public static int checkLogin(WebDriver drive, String postcheck) {
		switch (postcheck) {
			case "Nhớ trình duyệt": save(drive); return 1;
			case "Xem lại lần đăng nhập gần đây": submit(drive); return 1;	
			case "Facebook": return checkacc(drive);
			case "Tài khoản của bạn tạm thời bị khóa": return 3;
			case "Vui lòng xác nhận danh tính của bạn": return 3;
			default:
				return 0;
		}
	}
	public static void submit(WebDriver driver) {
		driver.findElement(By.cssSelector("#checkpointSubmitButton>[type=\"submit\"]")).click();
	}
	public static void save(WebDriver driver) {
		driver.findElement(By.cssSelector("[value=\"dont_save\"]")).click();
		submit(driver);
	}
	public static int checkacc(WebDriver driver) {
		String check = driver.findElement(By.cssSelector("#MChromeHeader")).getAttribute("innerText");
		if(check.toUpperCase().indexOf("Trang cá nhân".toUpperCase())>0) {
			return 2;
		}
		return 3;
	}
	public static void nhapma(WebDriver driver,String parentGUID, String key) {
		try {
			driver.switchTo().window(parentGUID);
			Thread.sleep(1000);
			driver.findElement(By.id("approvals_code")).sendKeys(key);
			submit(driver);
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static String getnv(WebDriver driver,String camxuc,String id_post,int chedo, String noidung) {
		try {
			boolean getthem = true;
			driver.get(id_post);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			Thread.sleep(2000);
			if(driver.getTitle().equals("mbasic.facebook.com")) {
				driver.get("https://mbasic.facebook.com/");
				return "loadlai";
			}else if (driver.getTitle().equals("Không tìm thấy nội dung")) {
				return "loadlai";
			}else if (driver.getTitle().equals("Không tìm thấy trang")) {
				return "loadlai";
			}else if (driver.getCurrentUrl().indexOf("checkpoint")>=0) {
				return "die";
			}else if(driver.getTitle().equals("Giờ bạn chưa dùng được tính năng này")) {
				return "Chặn";
			}else if(driver.getTitle().equals("Thao tác bị chặn")) {
				return "Chặn";
			}
			if(chedo==1) {
				try {
					java.util.List<WebElement> tsd = driver.findElements(By.cssSelector("#objects_container>div>div>div>div:nth-child(3) td"));
					for(WebElement s : tsd) {
						String cxuc = s.getAttribute("innerText").toString();
						if(cxuc.toUpperCase().indexOf("Theo dõi".toUpperCase())>=0) {
							s.click();
							getthem = false;
							break;
						}
					}
				}catch (Exception e) {}
			}else if(chedo == 0) {
				try {
					java.util.List<WebElement> texts1 = driver.findElements(By.cssSelector("#objects_container [role=\"presentation\"] tbody"));
					int d=0;
					for(WebElement s : texts1) {
						String camxuca = s.getAttribute("innerText").toString().trim();
						if(camxuca.toUpperCase().indexOf("Bày tỏ cảm xúc".toUpperCase())>=0) {
							java.util.List<WebElement> texts = s.findElements(By.cssSelector("td"));
							for(WebElement s1 : texts) {
								String bieucam = s1.getAttribute("innerText").toString().trim();
								if(bieucam.toUpperCase().equals("Bày tỏ cảm xúc".toUpperCase())) {
									s1.findElement(By.cssSelector("a")).click();
									Thread.sleep(500);
									java.util.List<WebElement> texts1s = driver.findElements(By.cssSelector("li"));
									for(WebElement sa : texts1s) {
										WebElement textsu = sa.findElement(By.cssSelector("td:nth-child(2)"));
										String cxuc = textsu.getAttribute("innerHTML").toString();
										if(cxuc.toUpperCase().indexOf(camxuc.trim().toUpperCase())>0) {
											sa.click();
											getthem = false;
											break;
										}
									}
									break;
								}
							}
							break;
						}
					}
				}catch (Exception e) {}
			}else if(chedo == 2){
				try {
					java.util.List<WebElement> texts1 = driver.findElements(By.cssSelector("#objects_container [role=\"presentation\"] tbody"));
					int d=0;
					for(WebElement s : texts1) {
						String camxuca = s.getAttribute("innerText").toString().trim();
						if(camxuca.toUpperCase().indexOf("Bình Luận".toUpperCase())>=0) {
							java.util.List<WebElement> texts = s.findElements(By.cssSelector("td"));
							for(WebElement s1 : texts) {
								String bieucam = s1.getAttribute("innerText").toString().trim();
								if(bieucam.toUpperCase().equals("Bình Luận".toUpperCase())) {
									s1.findElement(By.cssSelector("a")).click();
									Thread.sleep(1000);
									driver.findElement(By.cssSelector("[name=\"comment_text\"]")).sendKeys(noidung);
									Thread.sleep(500);
									driver.findElement(By.cssSelector("[name=\"post\"]")).click();
									getthem = false;
									break;
								}
							}
							break;
						}
					}
				}catch (Exception e) {}
			}else if(chedo == 3){
				try {
					java.util.List<WebElement> texts1 = driver.findElements(By.cssSelector("#objects_container [role=\"presentation\"] tbody td"));
					for(WebElement s : texts1) {
						String camxuca = s.getAttribute("innerText").toString().trim();
						if(camxuca.toUpperCase().indexOf("Thích".toUpperCase())>=0) {
							s.findElement(By.cssSelector("a")).click();
							getthem=false;
							break;
						}
					}
					int dem = 0;
					java.util.List<WebElement> checks = driver.findElements(By.cssSelector("#objects_container [role=\"presentation\"] tbody td"));
					for(WebElement s1 : checks) {
						String camxuca = s1.getAttribute("innerText").toString().trim();
						if(camxuca.toUpperCase().equals("Thích".toUpperCase())) {
							return "Chặn";
						}
						if(dem==3) break;
						dem++;
					}
				}catch (Exception e) {}
			}
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			if(driver.getTitle().equals("Thao tác bị chặn")) {
				return "Chặn";
			}else if(driver.getTitle().equals("Facebook")) {
				if(checkacc(driver)==3) return "Chặn";
			}else if(driver.getTitle().equals("Giờ bạn chưa dùng được tính năng này")) {
				return "Chặn";
			}else if(driver.getTitle().equals("Bạn hiện không thể thích Trang")) {
				return "Chặn";
			}else if(driver.getTitle().equals("Vui lòng xem lại bình luận của bạn.")) {
				return "loadlai";
			}
			if(getthem) {
				return "loadlai";
			}
		} catch (Exception e) {return "loadlai";}
		return "oke";
	}
}
