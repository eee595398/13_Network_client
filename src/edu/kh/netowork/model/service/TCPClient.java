package edu.kh.netowork.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

	
	public void clientStart() {
		
		//1.서버가 보낸 메시지를 받을 byte[] 준비// 클라이언트용 소켓 객체 생성 
		String serverIp = "127.0.0.1"; // loop back ip(내 컴퓨터를 가리키는 ip 주소)
		int port = 8500; // 서버 소켓이 기다리고 있는 포트 번호 
		
		//*필요한 변수 선언*
		Socket clientSocket = null; // 서버와 연결할 클라이언트 용 소켓을 저장할 변수 
		
		BufferedReader br = null; // 서버 -> 클라이언트로 읽어오는 보조 스트림
		
		PrintWriter pw = null; // 클라이언트 -> 서버로 출력하는 보조 스트림
		
		try {
			
			//2.DatagramSocket 객체 생성 -> 먼저 소켓이 필요함
			System.out.println("[Client]");
			
			
			clientSocket = new Socket(serverIp, port);
			
			
			
			//3.메시지 받을 DatagramPacket객체 준비
					br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					pw = new PrintWriter(clientSocket.getOutputStream());
					
			//4.스트림을 통해 읽고쓰기 
			// 4-1) 서버 접속 성공시 
			// 		서버가 출력한 "[서버 접속 성공]" 메세지 읽어오기 
			String message = br.readLine();
			System.out.println("서버로부터 받은 메세지 : " + message);
			
			// 4-2) 클라이언트-> 서버로 메세지 전송 
			Scanner sc = new Scanner(System.in);
			System.out.print("입력 : ");
			String input = sc.nextLine();
			
			
			pw.println(input);
			pw.flush();
			
				
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally {
			
			//5.소켓 닫음
			try {
				
				if(pw != null) pw.close();
				if(br != null) br.close();
				if(clientSocket != null) clientSocket.close();
				
				
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		
	

		
		
		
	}
	
	
}
