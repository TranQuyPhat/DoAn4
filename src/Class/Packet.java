package Class;

import java.io.Serializable;

public class Packet implements Serializable {
	private int define_require; 
	private String name_send; 
	private String name_recerive;
	private String title ; 
	private String date ; 
	private String content; 
	private String File ; 
	private String FileImage;
	private String IP_client;
	private String messSent;
	private String messReceive;
	public Packet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Packet(String name_send, String name_recerive, String title, String date, String content, String file, String fileImage) {
		super();
		this.name_send = name_send;
		this.name_recerive = name_recerive; 
		this.title = title;
		this.date = date;
		this.content = content;
		File = file;
		FileImage = fileImage;
	}
	public Packet(int define_require,String name_sent,String IP_client) {
		super();
		this.define_require = define_require; 
		this.name_send=name_sent;
		this.IP_client = IP_client;
		
		
	}
	
	public Packet(int define_require, String name_send, String name_receive, String title, String date, String content, String file, String fileImage) {
		super();
		this.define_require = define_require; 
		this.name_send = name_send;
		this.name_recerive = name_receive; 
		this.title = title;
		this.date = date;
		this.content = content;
		File = file;
		FileImage = fileImage;
		
	}

	

	public Packet(String messSent) {
		super();
		this.messSent = messSent;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFile() {
		return File;
	}
	public void setFile(String file) {
		File = file;
	}
	public String getFileImage() {
		return FileImage;
	}
	public void setFileImage(String fileImage) {
		FileImage = fileImage;
	}
	public int getDefine_require() {
		return define_require;
	}
	public void setDefine_require(int define_require) {
		this.define_require = define_require;
	}
	public String getName_send() {
		return name_send;
	}
	public void setName_send(String name_send) {
		this.name_send = name_send;
	}
	public String getName_recerive() {
		return name_recerive;
	}
	public void setName_recerive(String name_recerive) {
		this.name_recerive = name_recerive;
	}
	public String getIP_client() {
		return IP_client;
	}
	public void setIP_client(String iP_client) {
		IP_client = iP_client;
	}
	public String getMessSent() {
		return messSent;
	}
	public void setMessSent(String messSent) {
		this.messSent = messSent;
	}
	public String getMessReceive() {
		return messReceive;
	}
	public void setMessReceive(String messReceive) {
		this.messReceive = messReceive;
	}
	
	
}
