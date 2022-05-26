package testcode;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


public class FormTTC extends JFrame implements Runnable,ActionListener,WindowListener{
	private JLabel t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,s8,s9,s10,t11;
	private JTextField te,tl;
	private Container con;
	private JScrollPane talbles,talblenv;
	private JTable table,tablenv;
	private JButton b1,b2,b3,b4;
	private JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15;
	private JTextArea textArea;
	private JRadioButton cb1,cb2,cb3,cb4,cb5,cb6;
	String choncainao,chrome,selenium;
	Thread t;
	JScrollPane scrollPane;
	String check = "go";
	String column[]={"ID","userfb","passfb","token","userttc","passttc","số dư"}; 
	ArrayList<ThongTin> nhiuacc = new ArrayList<ThongTin>();
	Object data[][]= new Object[0][];
	doiacc acc = new doiacc();
	public FormTTC() {
		setVisible(true);
		setTitle("AUTO-TTC");
		con = this.getContentPane();
		t1 = new JLabel("DANH SÁCH ACC MUỐN CHẠY");
		t1.setForeground(Color.RED);
		t3 = new JLabel("Time khởi tạo");
		t4 = new JLabel("leo leo");
		t4.setForeground(Color.RED);
		cb1 = new JRadioButton("Trao Đổi Sub", true);
    	cb2 = new JRadioButton("Tương Tác Chéo", false);
    	cb3 = new JRadioButton("show", false);
    	cb4 = new JRadioButton("hidden", true);
    	cb5 = new JRadioButton("firefox", false);
    	cb6 = new JRadioButton("chrome", true);
    	ButtonGroup bg=new ButtonGroup();
    	bg.add(cb1);bg.add(cb2);
    	ButtonGroup bgf=new ButtonGroup();
    	bgf.add(cb3);bgf.add(cb4);
    	ButtonGroup bgc=new ButtonGroup();
    	bgc.add(cb5);bgc.add(cb6);
    	
		table = new JTable();
		table=new JTable(data,column); 
		table.setPreferredScrollableViewportSize(new java.awt.Dimension(520, 100));
		table.setFillsViewportHeight(true); 
		talbles = new JScrollPane(table);
		talbles.setVisible(true); 
		
		textArea = new JTextArea(16,58);
		textArea.setEditable(true); 
		scrollPane = new JScrollPane(textArea); 
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		textArea.setCaretPosition(textArea.getDocument().getLength());
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(true);
		p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p1.add(t1);
		p2 = new JPanel();
		p2.setLayout(new GridLayout(1, 4));
		p3 = new JPanel();
		p3.setLayout(new GridLayout(1, 2));
		p13 = new JPanel();
		p13.setLayout(new GridLayout(1, 2));
		p14 = new JPanel();
		p14.setLayout(new GridLayout(1, 2));
		p15 = new JPanel();
		p15.setLayout(new GridLayout(1, 2));
		p4 = new JPanel();
		p4.setLayout(new GridLayout(3, 1));
		p5 = new JPanel();
		p5.setLayout(new FlowLayout());
		p5.add(talbles);
		p6 = new JPanel();
		p6.setLayout(new GridLayout(3, 2));
		t2 = new JLabel("time");
		te = new JTextField(8);
		t11 = new JLabel("Luồng chạy");
		tl = new JTextField(5);
		p11 = new JPanel();
		p12 = new JPanel();
		p11.setLayout(new GridLayout(1, 2));
		p12.setLayout(new GridLayout(1, 2));
		p14.add(cb3);
		p14.add(cb4);
		p15.add(cb5);
		p15.add(cb6);
		p3.add(t2);
		p3.add(te);
		p13.add(t11);
		p13.add(tl);
		p11.add(t3);
		p11.add(t4);
		p12.add(cb1);
		p12.add(cb2);
		p6.add(p3);
		p6.add(p14);
		p6.add(p13);
		p6.add(p15);
		p6.add(p11);
		p6.add(p12);
		p9 = new JPanel();
		p9.setLayout(new GridLayout(2, 1));
		p7 = new JPanel();
		p7.setLayout(new FlowLayout());
		p7.add(p6);
		
		tablenv = new JTable();
		tablenv=new JTable(); 
		tablenv.setPreferredScrollableViewportSize(new java.awt.Dimension(430, 105));
		tablenv.setFillsViewportHeight(true); 
		talblenv = new JScrollPane(tablenv);
		talblenv.setVisible(true); 
		
		p10 = new JPanel();
		p10.setLayout(new FlowLayout());
		p10.add(talblenv);
		p9.add(p7);
		p9.add(scrollPane);
		b1 = new JButton("Run");
		b2 = new JButton("Stop");
		b3 = new JButton("Add");
		b4 = new JButton("Add FB");
		
		b1.setForeground(Color.BLUE);
		b2.setForeground(Color.BLUE);
		b3.setForeground(Color.BLUE);
		b4.setForeground(Color.BLUE);
		
		p2.add(b1);
		p2.add(b2);
		p2.add(b3);
		p2.add(b4);
		p4.add(p5);
		p4.add(p10);
		p4.add(p9);
		con.add(p1, "North");
		con.add(p4, "Center");
		con.add(p2, "South");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b1.setEnabled(false);
		b2.setEnabled(false);
		b4.setEnabled(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\runacc\\cona.png"));
		this.pack();
		this.setSize(540, 600);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		t = new Thread(this);
		t.start();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				t.stop();
				System.exit(0);
				super.windowClosing(e);
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b3) {
			if(textArea.getText().equals("")) {
				JOptionPane.showConfirmDialog(null, "Nhập dữ liệu vào!", "Lỗi rồi", -1, 2);
			}else {
				if(cb1.isSelected()) {
					choncainao = "tds";
				}
				else if(cb2.isSelected()) {
					choncainao = "ttc";
				}
				for(String line : textArea.getText().split("\\n")) {
					String userfb="", passfb="", token="", userttc="", passtcc="";
					userfb = line.substring(0,line.indexOf('|'));
					line = line.substring(line.indexOf("|")+1,line.length());
					passfb = line.substring(0,line.indexOf('|'));
					line = line.substring(line.indexOf("|")+1,line.length());
					token = line.substring(0,line.indexOf('|'));
					line = line.substring(line.indexOf("|")+1,line.length());
					userttc = line.substring(0,line.indexOf('|'));
					passtcc = line.substring(line.indexOf('|')+1,line.length());
					ArrayList<NickFb> thongtins = new ArrayList<NickFb>();
					thongtins.add(new NickFb(userfb, passfb, token));
					nhiuacc.add(new ThongTin(thongtins, userttc, passtcc,choncainao));
					
				}

				DefaultTableModel del = new DefaultTableModel();
				try {
					del.setColumnIdentifiers(column);
					String[] rows = new String[column.length];
					for(int i=0; i<nhiuacc.size(); i++) {
						rows[0] = (i+1)+"";
						rows[1] = nhiuacc.get(i).getNickfb().get(0).getUserfb();
						rows[2] = nhiuacc.get(i).getNickfb().get(0).getPassfb();
						rows[3] = nhiuacc.get(i).getNickfb().get(0).getToken();
						rows[4] = nhiuacc.get(i).getUserttc();
						rows[5] = nhiuacc.get(i).getPassttc();
						rows[6] = nhiuacc.get(i).getSodu();
						del.addRow(rows);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				table.setModel(del);
				textArea.setText("");	
				b4.setEnabled(true);
				b3.setEnabled(false);
				b1.setEnabled(true);
			}
		}else if(e.getSource() == b1) {
			if(te.getText().equals("")) {
				JOptionPane.showConfirmDialog(null, "Nhập time vào!", "Lỗi rồi", -1, 2);
			}else if(nhiuacc.size() == 0) {
				JOptionPane.showConfirmDialog(null, "Nhập dữ liệu vào!", "Lỗi rồi", -1, 2);
			}else if(Integer.parseInt(tl.getText()) > nhiuacc.size()) {
				JOptionPane.showConfirmDialog(null, "Số luồng chạy hơn nick đang add!", "Lỗi rồi", -1, 2);
			}else if(tl.getText().equals("")) {
				JOptionPane.showConfirmDialog(null, "Nhập luồng chạy!", "Lỗi rồi", -1, 2);
			}else {
				SimpleDateFormat formatter= new SimpleDateFormat("HH:mm") ;
				Date date = new Date(System.currentTimeMillis());
				String s = formatter.format(date);
				t4.setText(s);
				try {
					if(cb3.isSelected()) {
						chrome = "show";
					}
					else if(cb4.isSelected()) {
						chrome = "hidden";
					}
					if(cb5.isSelected()) {
						selenium = "firefox";
					}
					else if(cb6.isSelected()) {
						selenium = "chrome";
					}
					if(cb1.isSelected()) {
						choncainao = "tds";
					}
					else if(cb2.isSelected()) {
						choncainao = "ttc";
					}
					acc = new doiacc(nhiuacc, Integer.parseInt(te.getText()),Integer.parseInt(tl.getText()));
					acc.setChrome(chrome);
					acc.setSelenium(selenium);
					acc.setChoncainao(choncainao);
					acc.start();
				} catch (Exception es) {}
				b4.setEnabled(false);
				b3.setEnabled(false);
				b1.setEnabled(false);
				b2.setEnabled(true);
			}
			
		}else if(e.getSource() == b2) {
			acc.stops();
			b4.setEnabled(true);
			b3.setEnabled(false);
			b2.setEnabled(false);
			b1.setEnabled(true);
		}else if(e.getSource() == b4) {
			if(textArea.getText().equals("")) {
				JOptionPane.showConfirmDialog(null, "Nhập dữ liệu vào!", "Lỗi rồi", -1, 2);
			}else {
				for(String line : textArea.getText().split("\\n")) {
					String userfb="", passfb="", token="", userttc="", passtcc="";
					int id;
					userfb = line.substring(0,line.indexOf('|'));
					line = line.substring(line.indexOf("|")+1,line.length());
					passfb = line.substring(0,line.indexOf('|'));
					line = line.substring(line.indexOf("|")+1,line.length());
					token = line.substring(0,line.indexOf('|'));
					id = Integer.parseInt(line.substring(line.indexOf("|")+1,line.length()));
					id--;
					NickFb nick = new NickFb(userfb, passfb, token);
					nhiuacc.get(id).getNickfb().add(nick);
				}
				textArea.setText("");
			}
		}
	}
	@Override
	public void run() {
		boolean check = false;
		
		SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
		while(true) {
			Date date = new Date(System.currentTimeMillis());
			String s = formatter.format(date);
			t2.setText(s);
			if(nhiuacc.size()>0) {
				// đoạn code làm lỗi index table
				try {
					DefaultTableModel dels = new DefaultTableModel();
					dels.setRowCount(0);
					String[] rowsd = new String[5];
					String[] arr = new String[5];
					arr[0] = "ID";
					arr[1] = "Trang";
					arr[2] = "Nhiệm vụ";
					arr[3] = "Xu Tổng";
					arr[4] = "Xu Kiếm được";
					dels.setColumnIdentifiers(arr);
					for(int xs=acc.getD(); xs<acc.getSi(); xs++) {
						if(xs<nhiuacc.size()) {
							int sodu = Integer.parseInt(nhiuacc.get(xs).getSodu());
							int soducu = Integer.parseInt(nhiuacc.get(xs).getSoducu());
							rowsd[0] = (xs+1)+"";
							rowsd[1] = nhiuacc.get(xs).getNv_chon();
							rowsd[2] = nhiuacc.get(xs).getNhiemvu();
							rowsd[3] = sodu+"";
							rowsd[4] = (sodu-soducu)+"";
							dels.addRow(rowsd);
							if(nhiuacc.get(xs).getCheckxu().equals("stop")) {
								check = true;
							}
						}
					}
					tablenv.setModel(dels);
				} catch (Exception e) {
					System.out.println("huhu");
				}
//				System.out.println(acc.getD()+"|"+acc.getSi());
			}
			
			if(check) {
				try {
					DefaultTableModel del = new DefaultTableModel();
					del.setRowCount(0);
					try {
						del.setColumnIdentifiers(column);
						String[] rows = new String[column.length];
						for(int i=0; i<nhiuacc.size(); i++) {
							if(nhiuacc.get(i).getCheckxu().equals("stop")) {
								nhiuacc.get(i).setCheckxu("quit");
							}
							rows[0] = (i+1)+"";
							rows[1] = nhiuacc.get(i).getNickfb().get(0).getUserfb();
							rows[2] = nhiuacc.get(i).getNickfb().get(0).getPassfb();
							rows[3] = nhiuacc.get(i).getNickfb().get(0).getToken();
							rows[4] = nhiuacc.get(i).getUserttc();
							rows[5] = nhiuacc.get(i).getPassttc();
							rows[6] = nhiuacc.get(i).getSodu();
							del.addRow(rows);
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
					table.setModel(del);
				} catch (Exception e) {
					System.out.println("huhu");
				}
				
				check = false;
			}
			try {
				Thread.sleep(1000);
				
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new FormTTC();
	}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowClosing(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowOpened(WindowEvent e) {}

}
class setting extends Thread{
	public AutoTTC autoTTC;
	public String tsm = "go";
	public ChromeOptions options;
	public String chrome = "hidden";
	public String choncainao;
	public String selenium;
	public WebDriver fb;
	public WebDriver run;
	public int height;
	public int width;
	public setting(AutoTTC autoTTC, ChromeOptions options, int width, int height) {
		this.autoTTC = autoTTC;
		this.options = options;
		this.width = width;
		this.height = height;
	}
	public String getChrome() {
		return chrome;
	}
	public void setChrome(String chrome) {
		this.chrome = chrome;
	}
	public String getSelenium() {
		return selenium;
	}
	public void setSelenium(String selenium) {
		this.selenium = selenium;
	}
	public String getChoncainao() {
		return choncainao;
	}
	public void setChoncainao(String choncainao) {
		this.choncainao = choncainao;
	}
	@Override
	public void run() {
		try {
			if(selenium.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "D:\\runacc\\chromedriver.exe");
//				if(!choncainao.equals("tds")) {
					this.options = new ChromeOptions();
//					option.addArguments("user-data-dir=D:\\xn\\User Data");
					options.setExperimentalOption("useAutomationExtension", false);
					options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation")); 
					DesiredCapabilities capabilitiesfb = DesiredCapabilities.chrome();
					capabilitiesfb.setCapability(ChromeOptions.CAPABILITY, options);
//				}
				ChromeOptions optionshow = new ChromeOptions();
				optionshow.setExperimentalOption("useAutomationExtension", false);
				optionshow.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
				DesiredCapabilities capabilitieshow = DesiredCapabilities.chrome();
				capabilitieshow.setCapability(ChromeOptions.CAPABILITY, optionshow);
				
				ChromeOptions optionhidden = new ChromeOptions();
				optionhidden.setHeadless(true);
				optionhidden.addArguments("-incognito") ;
				optionhidden.setExperimentalOption("useAutomationExtension", false);
				optionhidden.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation")); 
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY, optionhidden);
				
				if(chrome.equals("hidden")) {
					fb = new ChromeDriver(optionhidden);
				}else if(chrome.equals("show")) {
					fb = new ChromeDriver(optionshow);
				}
//				if(!choncainao.equals("tds")) {
					run = new ChromeDriver(options);
//				}
			}else if(selenium.equals("firefox")) {
				FirefoxOptions optionfb = null;
				System.setProperty("webdriver.gecko.driver", "D:\\runacc\\geckodriver.exe");
//				if(!choncainao.equals("tds")) {
					optionfb = new FirefoxOptions();
//				}
				FirefoxOptions optionhidden = new FirefoxOptions();
				optionhidden.setHeadless(true);
				optionhidden.addArguments("-incognito");
				
				FirefoxOptions optionshow = new FirefoxOptions();
				
				if(chrome.equals("hidden")) {
					fb = new FirefoxDriver(optionhidden);
				}else if(chrome.equals("show")) {
					fb = new FirefoxDriver(optionshow);
				}
//				if(!choncainao.equals("tds")) {
					run = new FirefoxDriver(optionfb);
//				}
				
			}
			this.autoTTC.getThongtin().setDriverfb(fb);
			Dimension d = new Dimension(500,370);
			Dimension d1 = new Dimension(500,400);
			this.autoTTC.getThongtin().getDriverfb().manage().window().setSize(d);
			this.autoTTC.getThongtin().getDriverfb().manage().window().setPosition(new Point(width, height));	
//			if(!choncainao.equals("tds")) {
				this.autoTTC.getThongtin().setDriverttc(run);
				this.autoTTC.getThongtin().getDriverttc().manage().window().setSize(d1);
				this.autoTTC.getThongtin().getDriverttc().manage().window().setPosition(new Point(width, height));
//			}
			this.autoTTC.setting();
			if(tsm.equals("Stop")) {
				synchronized(this){
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void sr() {
		synchronized (this) {
			notify();
		}
	}
}
class doiacc extends Thread{
	public ArrayList<ThongTin> cauhinh;
	public int timedie;
	public String runs = "go";
	public int d=0;
	public int si=0;
	public int sl;
	String sdoi;
	boolean check = false;
	int dem = 0;
	public String chrome = "hidden";
	public String selenium;
	public String choncainao;
	public setting st[];
	public AutoTTC auto[];
	public ChromeOptions options[];
	public doiacc(ArrayList<ThongTin> cauhinh, int timedie,int luong) {
		this.cauhinh = cauhinh;
		this.timedie = timedie;
		this.sl = luong;
		this.si = this.sl;
		st = new setting[cauhinh.size()];
		auto = new AutoTTC[cauhinh.size()];
		options = new ChromeOptions[cauhinh.size()];
	}
	public doiacc() {}
	@Override
	public void run() {
		try {
			int minute = timedie;
			SimpleDateFormat formatter1= new SimpleDateFormat("HH:mm:ss") ;
			Date date1 = new Date(System.currentTimeMillis());
			String s1 = formatter1.format(date1);
			sdoi = s1;
			String hours,munites;
			hours = sdoi.substring(0,sdoi.indexOf(':'));
			sdoi = sdoi.substring(sdoi.indexOf(':')+1,sdoi.length());
			munites = sdoi.substring(0,sdoi.indexOf(':'));
			sdoi = Timerun(minute,hours,munites,sdoi);
			
			while(true) {
				SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss") ;
				Date date = new Date(System.currentTimeMillis());
				String s = formatter.format(date);
				if(runs.equals("go")) {
					if(si>cauhinh.size()) {
						d=0;
						si=sl;
					}
					try {
						int ds = 0;
						int ds2 = 0;
						for(int i=d; i<si; i++) {
							cauhinh.get(i).setCheckxu("run");
							options[i] = new ChromeOptions();
							auto[i] = new AutoTTC(cauhinh.get(i));
							if(ds2==0) {
								if(ds==2) {
									st[i] = new setting(auto[i], options[i], 920,0);
									st[i].setChrome(chrome);
									st[i].setSelenium(selenium);
									st[i].setChoncainao(choncainao);
									ds=0;
									ds2=1;
								}else {
									st[i] = new setting(auto[i], options[i], -60+(ds*500),0);
									st[i].setChrome(chrome);
									st[i].setSelenium(selenium);
									st[i].setChoncainao(choncainao);
									ds++;
								}
							}else if(ds2==1) {
								if(ds==2) {
									st[i] = new setting(auto[i], options[i], 920,370);
									st[i].setChrome(chrome);
									st[i].setSelenium(selenium);
									st[i].setChoncainao(choncainao);
									ds=0;
									ds2=0;
								}else {
									st[i] = new setting(auto[i], options[i], -60+(ds*500),370);
									st[i].setChrome(chrome);
									st[i].setSelenium(selenium);
									st[i].setChoncainao(choncainao);
									ds++;
								}
							}
						}
					} catch (Exception e) {}
					for(int i=d; i<si; i++) {
						if(i<cauhinh.size()) {
							st[i].start();
						}
					}
				}
				if(s.equals(this.sdoi)) {
					for(int j=d; j<si; j++) {
						if(j<cauhinh.size()) {
							Thread.sleep(2000);
							try {
								st[j].autoTTC.getThongtin().getDriverfb().quit();
								st[j].autoTTC.getThongtin().getDriverttc().quit();						
							} catch (Exception e2) {
								System.out.println("khong sao");
							}
							st[j].stop();
						}
					}
					d=d+sl;
					si=si+sl;
					runs="go";
					
					sdoi = s;
					hours = sdoi.substring(0,sdoi.indexOf(':'));
					sdoi = sdoi.substring(sdoi.indexOf(':')+1,sdoi.length());
					munites = sdoi.substring(0,sdoi.indexOf(':'));
					sdoi = Timerun(minute,hours,munites,sdoi);
				}else {
					runs="stop";
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public String Timerun(int minute, String hours, String munites, String sdoi) {
			int munitetime;
			int hourtime;
			
			munitetime = Integer.parseInt(munites)+minute;
			hourtime = Integer.parseInt(hours);
			if(minute == 0) {
				hourtime = hourtime - 1;
				if(hourtime>=24) {
					hourtime = hourtime-24;
				}
				if(hourtime<10) {
					sdoi = "0"+hourtime+":00:00";
				}else if(hourtime>=10) {
					sdoi = hourtime+":00:00";
				}
			}else {
				if(munitetime>=60) {
					hourtime = hourtime + munitetime/60;
					munitetime = munitetime%60;
				}
				if(hourtime>=24) {
					hourtime = hourtime-24;
				}
				if(hourtime<10) {
					if(munitetime<10) {
						sdoi = "0"+hourtime+":0"+munitetime+":00";
					}else if(munitetime>=10) {
						sdoi = "0"+hourtime+":"+munitetime+":00";
					}	
				}else if(hourtime>=10) {
					if(munitetime<10) {
						sdoi = hourtime+":0"+munitetime+":00";
					}else if(munitetime>=10) {
						sdoi = hourtime+":"+munitetime+":00";
					}
				}
			}
		return sdoi;
	}
	public void stops() {
		for(int j=d; j<si; j++) {
			try {
				st[j].autoTTC.getThongtin().getDriverfb().quit();
				st[j].autoTTC.getThongtin().getDriverttc().quit();						
			} catch (Exception e2) {
				System.out.println("khong sao");
			}
			st[j].stop();
		}
		this.stop();
	}
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	public int getSi() {
		return si;
	}
	public void setSi(int si) {
		this.si = si;
	}
	public String getRuns() {
		return runs;
	}
	public void setRuns(String runs) {
		this.runs = runs;
	}
	public String getChrome() {
		return chrome;
	}
	public void setChrome(String chrome) {
		this.chrome = chrome;
	}
	public String getSelenium() {
		return selenium;
	}
	public void setSelenium(String selenium) {
		this.selenium = selenium;
	}
	public String getChoncainao() {
		return choncainao;
	}
	public void setChoncainao(String choncainao) {
		this.choncainao = choncainao;
	}
}
