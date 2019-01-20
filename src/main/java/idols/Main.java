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
		// �T�[�o�[���̏���������
		// exit������܂łЂ����當�����Ԃ�
		try {
			//ResourceBundle resouce = ResourceBundle.getBundle("./settings/winSettings");
	        //System.out.println(resouce.getString("port"));
			
	        
			int port = Integer.parseInt(Properties.GetInstance().GetBundle().getString("port"));
			System.out.println("Hello world...");
			System.out.println("Port:"+port);
			
			// �󓮓I�\�P�b�g(�T�[�o�[�\�P�b�g)
			ServerSocket ss_socket = new ServerSocket(port);
			// �󂯓���\�P�b�g
			Socket socket = ss_socket.accept();

			// ���o�͂̃��[�_�[�A���C�^�[
			Reader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Writer out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// ����M���s��
			int c;
			StringBuilder sb = new StringBuilder(1024);
			String getMSG;
			while ((c = in.read()) != -1) {
				if (c == END)
					break;
				sb.append((char) c);
			}

			getMSG = sb.toString();
			// ��ʂ�����ׂɑ啶���ɕϊ�����
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
