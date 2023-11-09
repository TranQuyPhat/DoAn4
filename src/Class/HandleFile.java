package Class;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import constant.Constant;

public class HandleFile {

	Constant constant = new Constant();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int  p = Integer.parseInt(new HandleFile().readFile("server_DB\\"+"phan\\"+"infor.txt"));
		System.out.println(p);
		//Vector<Packet> packet = new HandleFile().parseFile("server_DB\\van\\phan.txt");
		//System.out.println(packet.get(0).getName_recerive()); 
	}
	
	
    public void CreateDirectory(String path, String nameFolder) {
    	try {
    	  	File x = new File(path + "\\" + nameFolder);
    	  	System.out.println(x.exists());
	  		  if (!x.exists()) {
	  			  if ( x.mkdir()) {
	  				  System.out.println("Create folder success!");
	  	                
	  			  } else {
	  				  System.out.println("ERROR!");
	  	          }
	  			  	path = path + "\\" + nameFolder;         
	  		  }else {
	  			  System.out.println("Folder already exists");
	  		  }
		} catch (Exception e) {
			// TODO: handle exception
		}
  
    }
	
    public void createFile(String path, String nameFile) {
    	File file = new File(path + "\\" + nameFile);
    	try {
			if( file.createNewFile()) {
			//	System.out.println("Tạo file thành côcng");
			}else {
				System.out.println("lỗi rồi ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
    public void writeFile(String path, String content) {
    	try {
    		String s = readFile(path); 
    		
			FileOutputStream file = new FileOutputStream(path);
			String string = s + content;
			byte[] a = string.getBytes(); // ep kieu String ve mang byte
		
			file.write(a); // ghi mang byte vao file
			//System.out.print("Da ghi thanh cong!");
			file.close();
		} catch (Exception e2) {
			System.out.print(e2);// In lỗi ra màn hình
		}
    }
    
    public String readFile(String path) {
    	 BufferedReader br = null;
    	 String content = ""; 
    	 if(!checkFileExist(path)) {
    	//	 System.out.println("file ko tồn tại");
    		 return null ; 
    	 }else {
	         try {   
	             br = new BufferedReader(new FileReader(path));       
	             int num=0;
	             char ch;
	             while((num = br.read()) != -1)
	             {    
	                 ch = (char) num;
	                // System.out.println(ch); 
	                 content = content + "" + ch ; 
	             }
	         }catch(Exception e) {
	        	 System.out.println(e.getMessage());
	         }
    	 }
    	 return content ; 
    }
    
    public void createAndWriteFile(String path,String nameFile, String content) {
    	if(!checkFileExist(path + nameFile+".txt" ))
			createFile(path, nameFile+".txt");
		writeFile(path + nameFile+".txt", content); 
    }
    
    public boolean checkFileExist(String path) {
	  	File file = new File(path);
	  	return file.exists();  
    }
    
    public Vector<Packet> parseFile(String path) {
    	Vector<Packet> packet = new Vector<Packet>(); 
    	BufferedReader br = null;
    	 if(!checkFileExist(path)) {
    	//	 System.out.println("file ko tồn tại");
    		 return null ; 
    	 }else {
	         try {   
	             br = new BufferedReader(new FileReader(path));       
	             int num=0;
	             String content =""; 
	             char ch;
	             while((num = br.read()) != -1)
	             {    
	            	 if(num == 13 || num == 0) {
	            	    String[] s = content.split(new Constant().SPLIT_S) ;
	            	    if( s.length > 1)
	            	    	packet.add(new Packet(s[0], s[1], s[2], s[3] , "Jlkjsf", null, null));
	            	    content = ""; 
	            	 }
	            	 else{
		                 ch = (char) num;
			                 content = content + "" + ch ; 
	            	 }

	             }
	         }catch(Exception e) {
	        	 System.out.println(e.getMessage());
	         }
    	 }
    	return packet ;
    }

}
