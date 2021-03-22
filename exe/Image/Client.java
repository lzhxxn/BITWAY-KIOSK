import java.io.*;
import java.net.*;

class Client {  
	Socket s;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	FileInputStream fis; //Node 
	OutputStream os; //Node 
	BufferedInputStream bis; //Filter
	BufferedOutputStream bos; //Filter 

	String fname = "C:/Users/achoa/Desktop/Project/Kiosk/주문내역.txt";

	Client(){
		connect();
	}
	void connect(){ //서버와 접속
		try{
			p("서버IP(기본:192.168.0.17): ");
			String ip = br.readLine();
			if(ip != null) ip = ip.trim();
			if(ip.length() == 0) ip = "192.168.0.17";
			
			p("PORT(기본:4000): ");
			String portStr = br.readLine();
			if(portStr != null) portStr = portStr.trim();
			if(portStr.length() == 0) portStr = "4000";
			int port = Integer.parseInt(portStr);
			if(port<0 || port>65535){
				pln("범위가 유효하지 않은 포트임");
				connect();
				return;
			}

			s = new Socket(ip, port);

			ready();
		}catch(IOException ie){}
	}
	void ready(){
		try{
			fis = new FileInputStream(fname);
			bis = new BufferedInputStream(fis, 4096);
			os = s.getOutputStream();
			bos = new BufferedOutputStream(os, 4096);

			send();
		}catch(FileNotFoundException fe){
		}catch(IOException ie){}
	}
	void send(){ //file -> socket
		byte bs[] = new byte[1024];
		int i=0;
		long total = 0L;
		try{
			while((i=bis.read(bs)) != -1){
				bos.write(bs, 0, i);
				pln("전송 중..."+ ( total+=i ) + "bytes");
			}
			bos.flush();
			pln("파일("+fname+": "+total+"bytes) 전송 완료!!");
		}catch(IOException ie){
		}finally{
			closeAll();	
		}
	}
	void closeAll(){
		try{
			bis.close();
			bos.close();
			fis.close();
			os.close();
			s.close();
		}catch(IOException ie){}
	}
	void pln(String str){
		System.out.println(str);
	}
	void p(String str){
		System.out.print(str);
	}
	public static void main(String[] args) {
		new Client();
	}
}
