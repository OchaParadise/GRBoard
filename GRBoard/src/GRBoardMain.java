
public class GRBoardMain {

	/**
	 * @param args
	 */
	public static String AuthKey = null;
	
	public static void main(String[] args) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
		AuthKey = GetAuth.GetSID();
		//System.out.println("Gotten AuthKey is \n" + AuthKey);
		//System.out.println("*** Got AuthKey ***");
		
		GetFeed.getFeed(AuthKey);
		//System.out.println("*** Got Feed ***");
		
		//GReaderViewerReadXML.GReaderViewerReadXML("OutputTest.xml");
		//System.out.println("*** Fin ***");
		
		// �E�B���h�E�ɕ\��
		TimerJFrame.JFrametest();
	}

}
