/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sabint.metrotuner;

import java.awt.Dimension;
import java.awt.Insets;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Rae
 */
public class ImageButton extends JButton {
    int x, y;
    String name;
    
    ImageButton(String name, int x, int y) {
        this.name = name;
        
        loadIcons();
        
        setMargin(new Insets(0,0,0,0));
        setIconTextGap(0);
        setBorderPainted(false);
        setBorder(null);
        setText(null);
        setLocation(x, y);
    }
    
    private void loadIcons () {
      
        URL normalURL =  ClassLoader.getSystemResource("images/normal/" + name + ".gif");
        ImageIcon normalIcon = new ImageIcon(normalURL);
        //ImageIcon normalIcon = new ImageIcon("images/normal/" + name + ".gif");
        
        URL pressedURL =  ClassLoader.getSystemResource ("images/down/" + name + ".gif");
        ImageIcon pressedIcon = new ImageIcon(pressedURL);
        //ImageIcon pressedIcon = new ImageIcon("images/down/" + name + ".gif");
        
        URL overURL =  ClassLoader.getSystemResource("images/over/" + name + ".gif");
        ImageIcon overIcon = new ImageIcon(overURL);
        //ImageIcon overIcon = new ImageIcon("images/over/" + name + ".gif");
        
        setIcon(normalIcon);
        setRolloverIcon(overIcon);
        setPressedIcon(pressedIcon);
        setDisabledIcon(normalIcon);
        setRolloverSelectedIcon(overIcon);
        setDisabledSelectedIcon(overIcon);
        
        Dimension d = new Dimension(normalIcon.getImage().getWidth(null), normalIcon.getImage().getHeight(null));
        setSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
        setPreferredSize(d);
    }
}
