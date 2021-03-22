import java.io.*;
import java.net.*;

class Server {
	String fname = "주문내역.txt";
	int port = 4000;
	ServerSocket ss;
	Socket s;
	InputStream is; //Node 
	BufferedInputStream bis; //Filter
	FileOutputStream fos; //Node 
	BufferedOutputStream bos; //Filter 
	
	Server(){
		try{
			ss = new ServerSocket(port);
			while(true){
				pln("파일 서버가 "+port+"번 포트에서 대기중..");
				s = ss.accept();

				is = s.getInputStream();
				bis = new BufferedInputStream(is, 4096);
				
				fos = new FileOutputStream(fname);
				bos = new BufferedOutputStream(fos, 4096);

				receive();
			}
		}catch(IOException ie){
		}
	}
	void receive(){ //socket -> file 
		byte bs[] = new byte[1024];
		int i=0;
		long total = 0L;
		try{
			while((i=bis.read(bs)) != -1){
				bos.write(bs, 0, i);
				pln("받는 중..."+ ( total+=i ) + "bytes");
			}
			bos.flush();
			pln("파일("+fname+": "+total+"bytes) 받기 완료!!");
		}catch(IOException ie){
		}//finally{
			//closeAll();	
		//}
	}
	/*
	void closeAll(){
		try{
			bis.close();
			bos.close();
			is.close();
			fos.close();
			s.close();
			ss.close();
		}catch(IOException ie){}
	}
	*/
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		new Server();
	}
}
