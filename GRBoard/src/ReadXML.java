

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ReadXML {

	/**
	 * @param args
	 */
	public static void getFeed(String titleStr[], String titleLink[], String titleSource[]){
		// TODO 自動生成されたメソッド・スタブ
		try{
			// ドキュメントビルダーファクトリを生成
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			// ドキュメントビルダーを生成
			DocumentBuilder builder = dbfactory.newDocumentBuilder();
			// パースを実行してDocumentオブジェクトを取得
			Document doc = builder.parse(new BufferedInputStream(new FileInputStream("Output.xml")));
			// ルート要素を取得(タグ名:title)
			Element root = doc.getDocumentElement();
			//System.out.println("ルート要素名:"+ root.getTagName());
			// 各ノードリストを取得
			NodeList entrylist = root.getElementsByTagName("entry");
			
			//System.out.println("ノードリストの数 : " + entrylist.getLength());
			
			// 一度全タイトルを配列に入れて、読みだすのは配列から
			for(int i = 0; i < entrylist.getLength(); i++){	
				Element element = (Element)entrylist.item(i);
				// title タグの内容を取得
				titleStr[i] = getChildren(element, "title");
				titleLink[i] = getChildren(element, "link");
				titleSource[i] = getChildren(element, "source");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static String getChildren(Element element, String tagName) {
		// linkの処理
		if(tagName == "link"){
			NodeList linklist = element.getElementsByTagName(tagName);
			// titleのリンクは1つ目
			Element linkele = (Element)linklist.item(0);
			String linkstr = linkele.getAttribute("href");
			return linkstr;
		}
		
		// sourceタグ内のtitle(ソース名)の処理
		if(tagName == "source"){
			NodeList sourcelist = element.getElementsByTagName(tagName);
			String sourcestr = null;
			for(int j = 0; j < sourcelist.getLength(); j++){
				Element sourceEle = (Element)sourcelist.item(j);
				sourcestr = getChildren(sourceEle, "title");
			}
			return sourcestr;
		}
		
		// link, source以外
		NodeList list = element.getElementsByTagName(tagName);
		Element el = (Element)list.item(0);
		return el.getFirstChild().getNodeValue();
	}
}
