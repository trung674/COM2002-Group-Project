import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

public class Calendar extends JPanel {
	
	public Calendar(){
		Border padding = new EmptyBorder(0, 1, 0, 1);
        Border blackBorder = BorderFactory.createLineBorder(Color.black);
        this.setBorder(new CompoundBorder(padding, blackBorder));
        this.setPreferredSize(new Dimension(900, this.getHeight()));
        
        // Do something 
        
	}
}
