import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

// Auth���擾����
public class GetAuth {
	// SID,LSID,Auth���擾����(�K�v�Ȃ̂�Auth����)		
	public static String GetSID(){
		String s_substr = null;
		String urlString = "https://www.google.com/accounts/ClientLogin";
		
		// "PersonalInfo.ini"��ǂݍ���
		ResolveIniFile.ReadIniFile();
		
		try{
			URL url = new URL(urlString);
			URLConnection uc = url.openConnection();
			uc.setDoOutput(true);    // post�\�ɂ���
			
			// �Q��:http://code.google.com/p/pyrfeed/wiki/GoogleReaderAPI
			// setRequestProperty��key��value��n���킯�ł͂Ȃ��H
			/*
			uc.setRequestProperty("service", "reader");                 �@�@�@�@�@�@�@�@�@�@�@�@ // �K�{���ۂ�
			uc.setRequestProperty("Email", "mailAddress");
			uc.setRequestProperty("passwd", "Passward");
			uc.setRequestProperty("source", "test");                        // �C�ӂ炵��?
			uc.setRequestProperty("continue", "http://www.google.com/");    // �C�ӂ炵��?
			*/
			OutputStream os = uc.getOutputStream();    // post�p��outputstream���擾
			
			String postStr = "service=reader" +
							 "&Email=" + ResolveIniFile.ReturnValue.mailAddr +
							 "&Passwd=" + ResolveIniFile.ReturnValue.passWord +
							 "&source=testclient" +
							 "&continue=http://www.google.com/";
			
			PrintStream ps = new PrintStream(os);
			ps.print(postStr);    // �f�[�^��post����	
			ps.close();
			
			InputStream is = uc.getInputStream();    // post�������ʂ��擾
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String s;
			int BReaderCount = 0;
			while((s = reader.readLine()) != null){
				if(BReaderCount == 2){
					s_substr = s.substring(5);
				}
				BReaderCount++;
			}
			reader.close();
		}
		catch(MalformedURLException e){
			System.err.println("Invailed URL format");
			System.exit(-1);
		}
		catch(IOException e){
			System.err.println("Can't connect");
			System.exit(-1);
		}
		
		return s_substr;
	}
}
