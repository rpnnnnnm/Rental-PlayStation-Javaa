package rentalps.view;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;
import rentalps.controller.AuthController;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;


public class LoginFrame extends JFrame {

    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;
    private void LoginProcessing () {
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());

        if(AuthController.login(user, pass)){
                
            try{

                new MenuFrame().setVisible(true);
                dispose();

            }catch(SQLException ex){

                Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE,null,ex);

            }
                
        }else{

            JOptionPane.showMessageDialog(
                    LoginFrame.this,
                    "Username atau Password salah!",
                    "Login Gagal",
                    JOptionPane.ERROR_MESSAGE);

        }    
}
    
    public LoginFrame() {

        setTitle("PSRent Pro");
        setSize(500, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Background
        getContentPane().setBackground(new Color(18,22,33));

        // Judul
        JLabel title = new JLabel("PSRent Pro");
        title.setBounds(0,40,500,40);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        add(title);

        JLabel sub = new JLabel("Rental PlayStation Management");
        sub.setBounds(0,75,500,20);
        sub.setHorizontalAlignment(SwingConstants.CENTER);
        sub.setForeground(new Color(170,170,170));
        sub.setFont(new Font("Segoe UI",Font.PLAIN,14));
        add(sub);

        // Card Login
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(80,120,330,210);
        card.setBackground(new Color(34,42,61));
        card.setBorder(new LineBorder(new Color(55,65,85),1));

        add(card);

        JLabel lblUser = new JLabel("Username");
        lblUser.setForeground(Color.WHITE);
        lblUser.setBounds(30,20,100,20);
        lblUser.setFont(new Font("Segoe UI",Font.PLAIN,13));
        card.add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(30,45,270,35);
        txtUser.setBackground(new Color(25,31,48));
        txtUser.setForeground(Color.WHITE);
        txtUser.setCaretColor(Color.WHITE);
        txtUser.setBorder(BorderFactory.createLineBorder(new Color(70,80,100)));
        txtUser.setFont(new Font("Segoe UI",Font.PLAIN,14));
        card.add(txtUser);

        JLabel lblPass = new JLabel("Password");
        lblPass.setForeground(Color.WHITE);
        lblPass.setBounds(30,90,100,20);
        lblPass.setFont(new Font("Segoe UI",Font.PLAIN,13));
        card.add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(30,115,270,35);
        txtPass.setBackground(new Color(25,31,48));
        txtPass.setForeground(Color.WHITE);
        txtPass.setCaretColor(Color.WHITE);
        txtPass.setBorder(BorderFactory.createLineBorder(new Color(70,80,100)));
        txtPass.setFont(new Font("Segoe UI",Font.PLAIN,14));
        card.add(txtPass);

        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(30,165,270,35);
        btnLogin.setBackground(new Color(59,130,246));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setFont(new Font("Segoe UI",Font.BOLD,14));

        card.add(btnLogin);

        btnLogin.addMouseListener(new java.awt.event.MouseAdapter(){

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt){
                btnLogin.setBackground(new Color(37,99,235));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt){
                btnLogin.setBackground(new Color(59,130,246));
            }

        });
        

        
        //Keyboard Enter listener
        txtPass.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                LoginProcessing();
        
      
    }
}
}); 
        //Button Listener
        btnLogin.addActionListener(e -> {
            LoginProcessing();

        });

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });

    }

}