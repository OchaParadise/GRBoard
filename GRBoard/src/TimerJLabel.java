import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class TimerJLabel extends JLabel implements MouseListener{
	/**
	 * 
	 */
	public static int count = 0;
		
	private static final long serialVersionUID = 1L;
	
	int ItemCount = Integer.valueOf(ResolveIniFile.ReturnValue.GetItemNumber);
	int RefreshCycle = Integer.valueOf(ResolveIniFile.ReturnValue.RefreshCycle);
	int FontSize = Integer.valueOf(ResolveIniFile.ReturnValue.FontSize);
	
	String[] feedTitle = new String[ItemCount];
	String[] feedLink = new String[ItemCount];
	String[] sourceName = new String[ItemCount];
	
	public TimerJLabel(){
		ReadXML.getFeed(feedTitle, feedLink, sourceName); 
		this.setFont(new Font("Dialog", Font.BOLD, FontSize));
		addMouseListener(this);
		
		Timer t = new Timer();
        t.schedule(new TimerLabelTask(), 0, RefreshCycle);                      
	}
    public void setTxt(boolean getfeedflag){
    	if(getfeedflag == true){
    		TimerJFrame.frame.setTitle(TimerJLabel.count+1 + "/" + ResolveIniFile.ReturnValue.GetItemNumber +
    								   " 【" + sourceName[count] + "】");
    		this.setText(feedTitle[count]);
    		count++;
    	}
    	else{
    		this.setText("再取得...");
    		GetFeed.getFeed(GRBoardMain.AuthKey);
    	}
    }
    
    class TimerLabelTask extends TimerTask {
        public void run(){
            setTxt(true);
            if(count >= ItemCount){
            	setTxt(false);
            	count = 0;
            }
        }
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ
	
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ
	
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}
	@Override
	// クリックしたらブラウザで開く
	public void mousePressed(MouseEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ
		if(!Desktop.isDesktopSupported()){
			//System.out.println("not supported");
			return;
		}
		try {
			Desktop.getDesktop().browse(new URI(feedLink[TimerJLabel.count - 1]));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ
	
	}
}
