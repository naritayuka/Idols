package idols;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	// end mark
	private static final char END = '.';

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		// サーバー側の処理を書く
		// exitが来るまでひたすら文字列を返す
		try {
			//ResourceBundle resouce = ResourceBundle.getBundle("./settings/winSettings");
	        //System.out.println(resouce.getString("port"));
			
	        
			int port = Integer.parseInt(Properties.GetInstance().GetBundle().getString("port"));
			System.out.println("Hello world...");
			System.out.println("Port:"+port);
			
			// 受動的ソケット(サーバーソケット)
			ServerSocket ss_socket = new ServerSocket(port);
			// 受け入れソケット
			Socket socket = ss_socket.accept();

			// 入出力のリーダー、ライター
			Reader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Writer out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// 送受信を行う
			int c;
			StringBuilder sb = new StringBuilder(1024);
			String getMSG;
			while ((c = in.read()) != -1) {
				if (c == END)
					break;
				sb.append((char) c);
			}

			getMSG = sb.toString();
			// 区別をつける為に大文字に変換する
			out.write(getMSG.toUpperCase());
			out.flush();
			System.out.println("receive:" + getMSG);

			socket.close();
			ss_socket.close();

			System.exit(0);

		} catch (IOException err) {
			err.printStackTrace();
		}
	}

}
