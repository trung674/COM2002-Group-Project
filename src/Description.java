import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

public class Description extends JPanel {
	public Description(){
        Border padding = new EmptyBorder(0, 0, 0, 1);
        Border blackBorder = BorderFactory.createLineBorder(Color.black);
        this.setBorder(new CompoundBorder(padding, blackBorder));
        
        // Do something
	}
}
