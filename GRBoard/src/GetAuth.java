import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

// Authを取得する
public class GetAuth {
	// SID,LSID,Authを取得する(必要なのはAuthだけ)		
	public static String GetSID(){
		String s_substr = null;
		String urlString = "https://www.google.com/accounts/ClientLogin";
		
		// "PersonalInfo.ini"を読み込む
		ResolveIniFile.ReadIniFile();
		
		try{
			URL url = new URL(urlString);
			URLConnection uc = url.openConnection();
			uc.setDoOutput(true);    // post可能にする
			
			// 参照:http://code.google.com/p/pyrfeed/wiki/GoogleReaderAPI
			// setRequestPropertyでkeyとvalueを渡すわけではない？
			/*
			uc.setRequestProperty("service", "reader");                 　　　　　　　　　　　　 // 必須っぽい
			uc.setRequestProperty("Email", "mailAddress");
			uc.setRequestProperty("passwd", "Passward");
			uc.setRequestProperty("source", "test");                        // 任意らしい?
			uc.setRequestProperty("continue", "http://www.google.com/");    // 任意らしい?
			*/
			OutputStream os = uc.getOutputStream();    // post用のoutputstreamを取得
			
			String postStr = "service=reader" +
							 "&Email=" + ResolveIniFile.ReturnValue.mailAddr +
							 "&Passwd=" + ResolveIniFile.ReturnValue.passWord +
							 "&source=testclient" +
							 "&continue=http://www.google.com/";
			
			PrintStream ps = new PrintStream(os);
			ps.print(postStr);    // データをpostする	
			ps.close();
			
			InputStream is = uc.getInputStream();    // postした結果を取得
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
