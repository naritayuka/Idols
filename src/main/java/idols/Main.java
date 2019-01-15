package idols;

import java.util.*;
import java.io.*;
import java.net.*;
import org.json.*;

public class Main {

	// 80番ポートを使用
	private static final int LISTEN_PORT = 2929;
	// end mark
	private static final char END = '.';

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world...");
		System.out.println("Port:"+LISTEN_PORT);
		// サーバー側の処理を書く
		// exitが来るまでひたすら文字列を返す
		try {

			// 受動的ソケット(サーバーソケット)
			ServerSocket ss_socket = new ServerSocket(LISTEN_PORT);
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


			JSONObject obj = new JSONObject(" .... ");
			String pageName = obj.getJSONObject("pageInfo").getString("pageName");

			JSONArray arr = obj.getJSONArray("posts");
			for (int i = 0; i < arr.length(); i++)
			{
			    String post_id = arr.getJSONObject(i).getString("post_id");
			}
		} catch (IOException err) {
			err.printStackTrace();
		}
	}

}
