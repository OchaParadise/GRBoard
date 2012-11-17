import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class TimerJFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static TimerJFrame frame = new TimerJFrame();
	
	public static void JFrametest(){
		Rectangle screen = frame.getGraphicsConfiguration().getBounds();
		
		int width, height;
		width = Integer.valueOf(ResolveIniFile.ReturnValue.WindowWidth);
		height = Integer.valueOf(ResolveIniFile.ReturnValue.WindowHeight);
		frame.setBounds(screen.x + screen.width/2  - frame.getSize().width/2,
						screen.y + screen.height/2 - frame.getSize().height/2,
						width, height);
		
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);		
	}
	
	public TimerJFrame(){
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image iconImg = tk.getImage("GRBicon.gif");
		setIconImage(iconImg);
		
		TimerJLabel label = new TimerJLabel();
		this.add(label, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
