import java.io.*;
import java.net.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

// Feedを取得する
public class GetFeed {
	//setRequestProperty("Authorization", "GoogleLogin auth="+ auth_key);
	//でリクエストヘッダに追加。auth_keyはAuthKeyに入ってる
	//そのあとFeedをGet
	
	public static void getFeed(String authKey){
		// googlereaderの"すべてのアイテム"をとってくる
		String urlstr = "http://www.google.com/reader/atom/user/-/state/com.google/reading-list?n=" +
						 ResolveIniFile.ReturnValue.GetItemNumber; 
		// 末尾に?n=数字で取得件数変化
		try{
			URL url = new URL(urlstr);
			//URLConnection uc =	url.openConnection();
			//uc.setRequestProperty("Authorization", "GoogleLogin auth="+ authKey);
			//HttpURLConnection http = (HttpURLConnection)uc;
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			http.setRequestProperty("Authorization", "GoogleLogin auth="+ authKey);
			http.setRequestMethod("GET");
			http.connect();
			
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbfactory.newDocumentBuilder();
			
			Document doc = builder.parse(http.getInputStream());
			
			//FileWriter fileWriter = new FileWriter("test.xml");
			//BufferedWriter bw = new BufferedWriter(fileWriter);
			
			// xml保存
			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer transformer = tfactory.newTransformer();
			//transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			File file = new File("Output.xml");
			transformer.transform(new DOMSource(doc), new StreamResult(file));
		}
		catch(MalformedURLException e){
			System.err.println("err1");
			System.exit(-1);
		}
		catch(IOException e){
			System.err.println("err2");
			System.exit(-1);
		} catch (ParserConfigurationException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
