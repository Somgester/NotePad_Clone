package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class About extends JFrame implements ActionListener {
 
    About(){
        setBounds(400,100,600,500);
        setLayout(null);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/NotepadIcon.png"));
        Image img = icon.getImage().getScaledInstance(150, 60, Image.SCALE_DEFAULT);
        ImageIcon icon2 = new ImageIcon(img);
        JLabel label = new JLabel(icon2);
        label.setBounds(50, 180, 70, 70);
        add(label);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/nayawin.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 80, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(70, 40, 500, 100);
        add(image);

        JLabel text = new JLabel("<html><br>Notepad<br>NoteKaPad<br>Build Using Javax<br>This Is About NotePad...<br> I Don't Know What To Write But Still I'm Writing This<br>&copy All Rights Reserved</html>");
        text.setBounds(150, 100, 500, 200);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        add(text);

        JButton b1 = new JButton("OK");
        b1.setBounds(200,350,120,25);
        b1.setBackground(Color.PINK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }

    public static void main(String[] args) {
        new About();
    }
}