package rentalps.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import rentalps.db.DatabaseHelper;

public class MenuFrame extends JFrame {

    public MenuFrame() throws SQLException {

        // ======================
        // WINDOW
        // ======================

        setTitle("PSRent Pro Dashboard");
        setSize(520,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(new Color(18,22,33));

        // ======================
        // HEADER
        // ======================

        JLabel lblTitle = new JLabel("PSRent Pro Dashboard");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(25,20,320,35);
        add(lblTitle);

        JLabel lblJam = new JLabel();
        lblJam.setBounds(390,22,90,20);
        lblJam.setForeground(new Color(210,210,210));
        lblJam.setFont(new Font("Segoe UI", Font.BOLD,14));
        add(lblJam);

        Timer timer = new Timer(1000, e -> {
            lblJam.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        });
        timer.start();

        JLabel lblTanggal = new JLabel(
                new SimpleDateFormat("EEEE, dd MMMM yyyy").format(new Date())
        );
        lblTanggal.setBounds(25,58,260,18);
        lblTanggal.setForeground(new Color(180,190,210));
        lblTanggal.setFont(new Font("Segoe UI",Font.PLAIN,13));
        add(lblTanggal);

        JLabel lblDesc = new JLabel("Rental Management System");
        lblDesc.setBounds(25,78,220,18);
        lblDesc.setForeground(new Color(130,140,160));
        lblDesc.setFont(new Font("Segoe UI",Font.PLAIN,12));
        add(lblDesc);

        JPanel line = new JPanel();
        line.setBounds(25,103,460,2);
        line.setBackground(new Color(59,130,246));
        add(line);

        // =======================
        // Dashboard
        // =======================
        JPanel panelDashboard = new JPanel();
        panelDashboard.setLayout(null);
        panelDashboard.setBounds(20,120,470,285);

        panelDashboard.setBackground(new Color(24,30,48));
        panelDashboard.setBorder(BorderFactory.createEmptyBorder());

            try {

            Color cardColor = new Color(30,41,59);
            Color borderColor = new Color(55,65,81);

            JPanel[] cards = new JPanel[5];

            for (int i = 0; i < cards.length; i++) {
                cards[i] = new JPanel(null);
                cards[i].setBackground(cardColor);
                cards[i].setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(borderColor,1),
                        BorderFactory.createEmptyBorder(10,10,10,10)
                ));
            }

            cards[0].setBounds(15,15,205,80);
            cards[1].setBounds(245,15,205,80);
            cards[2].setBounds(15,105,205,80);
            cards[3].setBounds(245,105,205,80);
            cards[4].setBounds(130,195,205,80);

            // ================= TOTAL =================

            JLabel lbl1 = new JLabel("TOTAL KONSOL");
            lbl1.setBounds(15,8,150,15);
            lbl1.setForeground(Color.LIGHT_GRAY);
            lbl1.setFont(new Font("Segoe UI",Font.PLAIN,12));

            JLabel angka1 = new JLabel(String.valueOf(DatabaseHelper.getTotalKonsol()));
            angka1.setBounds(15,28,120,35);
            angka1.setForeground(Color.WHITE);
            angka1.setFont(new Font("Segoe UI",Font.BOLD,34));

            cards[0].add(lbl1);
            cards[0].add(angka1);

            // ================= TERSEDIA =================

            JLabel lbl2 = new JLabel("TERSEDIA");
            lbl2.setBounds(15,8,120,15);
            lbl2.setForeground(Color.LIGHT_GRAY);
            lbl2.setFont(new Font("Segoe UI",Font.PLAIN,12));

            JLabel angka2 = new JLabel(String.valueOf(DatabaseHelper.getTotalTersedia()));
            angka2.setBounds(15,28,120,35);
            angka2.setForeground(Color.WHITE);
            angka2.setFont(new Font("Segoe UI",Font.BOLD,34));

            cards[1].add(lbl2);
            cards[1].add(angka2);

            // ================= DISEWA =================

            JLabel lbl3 = new JLabel("SEDANG DISEWA");
            lbl3.setBounds(15,8,150,15);
            lbl3.setForeground(Color.LIGHT_GRAY);
            lbl3.setFont(new Font("Segoe UI",Font.PLAIN,12));

            JLabel angka3 = new JLabel(String.valueOf(DatabaseHelper.getTotalDisewa()));
            angka3.setBounds(15,28,120,35);
            angka3.setForeground(Color.WHITE);
            angka3.setFont(new Font("Segoe UI",Font.BOLD,34));

            cards[2].add(lbl3);
            cards[2].add(angka3);

            // ================= PENDAPATAN =================

            JLabel lbl4 = new JLabel("PENDAPATAN");
            lbl4.setBounds(15,8,150,15);
            lbl4.setForeground(Color.LIGHT_GRAY);
            lbl4.setFont(new Font("Segoe UI",Font.PLAIN,12));

            JLabel angka4 = new JLabel(
                    "Rp " + String.format("%,.0f",
                    DatabaseHelper.getPendapatan()));

            angka4.setBounds(15,30,175,22);
            angka4.setForeground(Color.WHITE);
            angka4.setFont(new Font("Segoe UI",Font.BOLD,18));

            cards[3].add(lbl4);
            cards[3].add(angka4);

            // ================= TRANSAKSI =================

            JLabel lbl5 = new JLabel("TOTAL TRANSAKSI");
            lbl5.setBounds(15,8,150,15);
            lbl5.setForeground(Color.LIGHT_GRAY);
            lbl5.setFont(new Font("Segoe UI",Font.PLAIN,12));

            JLabel angka5 = new JLabel(
                    String.valueOf(DatabaseHelper.getTotalTransaksi()));

            angka5.setBounds(15,28,120,35);
            angka5.setForeground(Color.WHITE);
            angka5.setFont(new Font("Segoe UI",Font.BOLD,34));

            cards[4].add(lbl5);
            cards[4].add(angka5);

            for (JPanel card : cards) {
                panelDashboard.add(card);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        add(panelDashboard);
  
        // =======================
        // Tombol Menu
        // =======================

        JButton btnPelanggan = new JButton("Kelola Pelanggan");
        btnPelanggan.setBounds(120,420,250,46);
        styleButton(btnPelanggan);

        JButton btnKonsol = new JButton("Kelola Konsol");
        btnKonsol.setBounds(120,475,250,46);
        styleButton(btnKonsol);

        JButton btnRental = new JButton("Transaksi Rental");
        btnRental.setBounds(120,530,250,46);
        styleButton(btnRental);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(120,585,250,46);

        btnLogout.setBackground(new Color(220,53,69));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.setBorderPainted(false);
        btnLogout.setFont(new Font("Segoe UI",Font.BOLD,14));

        add(btnPelanggan);
        add(btnKonsol);
        add(btnRental);
        add(btnLogout);

        JLabel footer = new JLabel("© 2026 PSRent Pro");
        footer.setBounds(190,640,150,18);
        footer.setForeground(new Color(120,120,120));
        footer.setFont(new Font("Segoe UI",Font.PLAIN,11));
        add(footer);

        // =======================
        // Event Button
        // =======================

        btnPelanggan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PelangganFrame().setVisible(true);
            }
        });

        btnKonsol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new KonsolFrame().setVisible(true);
            }
        });

        btnRental.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RentalFrame().setVisible(true);
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame().setVisible(true);
                dispose();
            }
        });
        
            }

        // =======================
        // Style Button
        // =======================
        private void styleButton(JButton btn) {

        Color normal = new Color(34,42,61);
        Color hover = new Color(59,130,246);

        btn.setBackground(normal);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(hover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(normal);
            }

        });
    }
}