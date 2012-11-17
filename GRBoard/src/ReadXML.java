

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
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		try{
			// �h�L�������g�r���_�[�t�@�N�g���𐶐�
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			// �h�L�������g�r���_�[�𐶐�
			DocumentBuilder builder = dbfactory.newDocumentBuilder();
			// �p�[�X�����s����Document�I�u�W�F�N�g���擾
			Document doc = builder.parse(new BufferedInputStream(new FileInputStream("Output.xml")));
			// ���[�g�v�f���擾(�^�O��:title)
			Element root = doc.getDocumentElement();
			//System.out.println("���[�g�v�f��:"+ root.getTagName());
			// �e�m�[�h���X�g���擾
			NodeList entrylist = root.getElementsByTagName("entry");
			
			//System.out.println("�m�[�h���X�g�̐� : " + entrylist.getLength());
			
			// ��x�S�^�C�g����z��ɓ���āA�ǂ݂����͔̂z�񂩂�
			for(int i = 0; i < entrylist.getLength(); i++){	
				Element element = (Element)entrylist.item(i);
				// title �^�O�̓��e���擾
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
		// link�̏���
		if(tagName == "link"){
			NodeList linklist = element.getElementsByTagName(tagName);
			// title�̃����N��1��
			Element linkele = (Element)linklist.item(0);
			String linkstr = linkele.getAttribute("href");
			return linkstr;
		}
		
		// source�^�O����title(�\�[�X��)�̏���
		if(tagName == "source"){
			NodeList sourcelist = element.getElementsByTagName(tagName);
			String sourcestr = null;
			for(int j = 0; j < sourcelist.getLength(); j++){
				Element sourceEle = (Element)sourcelist.item(j);
				sourcestr = getChildren(sourceEle, "title");
			}
			return sourcestr;
		}
		
		// link, source�ȊO
		NodeList list = element.getElementsByTagName(tagName);
		Element el = (Element)list.item(0);
		return el.getFirstChild().getNodeValue();
	}
}
