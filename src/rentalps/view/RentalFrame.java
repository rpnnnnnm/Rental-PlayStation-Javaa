package rentalps.view;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import rentalps.controller.RentalController;
import rentalps.db.DatabaseHelper;
import rentalps.model.Pelanggan;
import rentalps.model.UnitPlayStation;
import rentalps.util.InputTidakValidException;
import rentalps.util.StokKosongException;


public class RentalFrame extends JFrame {

    private JComboBox<String> cmbPelanggan;
    private JComboBox<String> cmbKonsol;

    private JTextField txtDurasi;

    private JTable table;
    private DefaultTableModel model;

    private List<Pelanggan> pelangganList;
    private List<UnitPlayStation> konsolList;


    public RentalFrame(){

        setTitle("Transaksi Rental");
        setSize(760,540);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(new Color(15,23,42));


        // ===========================
        // TITLE
        // ===========================

        JLabel title = new JLabel("TRANSAKSI RENTAL");
        title.setBounds(25,20,350,35);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI",Font.BOLD,26));
        add(title);



        // ===========================
        // FORM PANEL
        // ===========================

        JPanel form = new JPanel(null);
        form.setBounds(20,70,705,145);
        form.setBackground(new Color(30,41,59));
        form.setBorder(BorderFactory.createLineBorder(new Color(51,65,85)));
        add(form);


        JLabel lblPelanggan = new JLabel("Pelanggan");
        lblPelanggan.setBounds(20,20,100,20);
        lblPelanggan.setForeground(Color.WHITE);
        form.add(lblPelanggan);


        cmbPelanggan = new JComboBox<>();
        cmbPelanggan.setBounds(110,18,200,30);
        styleCombo(cmbPelanggan);
        form.add(cmbPelanggan);


        JLabel lblKonsol = new JLabel("Konsol");
        lblKonsol.setBounds(350,20,80,20);
        lblKonsol.setForeground(Color.WHITE);
        form.add(lblKonsol);


        cmbKonsol = new JComboBox<>();
        cmbKonsol.setBounds(420,18,200,30);
        styleCombo(cmbKonsol);
        form.add(cmbKonsol);


        JLabel lblDurasi = new JLabel("Durasi");
        lblDurasi.setBounds(20,75,80,20);
        lblDurasi.setForeground(Color.WHITE);
        form.add(lblDurasi);


        txtDurasi = new JTextField();
        txtDurasi.setBounds(110,73,100,30);
        styleTextField(txtDurasi);
        form.add(txtDurasi);


        JButton btnSewa = new JButton("Sewa");
        btnSewa.setBounds(270,70,120,35);
        styleButton(btnSewa,new Color(59,130,246));
        form.add(btnSewa);


        JButton btnKembali = new JButton("Kembalikan");
        btnKembali.setBounds(430,70,140,35);
        styleButton(btnKembali,new Color(239,68,68));
        form.add(btnKembali);



        // ===========================
        // TABLE
        // ===========================

        model = new DefaultTableModel(
                new String[]{
                    "ID",
                    "Kode",
                    "Tipe",
                    "Status"
                },0);


        table = new JTable(model);

        table.setRowHeight(30);
        table.setBackground(new Color(30,41,59));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(51,65,85));

        table.setSelectionBackground(new Color(59,130,246));
        table.setSelectionForeground(Color.WHITE);

        table.getTableHeader().setBackground(new Color(15,23,42));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,13));

        table.setFillsViewportHeight(true);


        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20,235,705,245);

        scroll.getViewport().setBackground(new Color(30,41,59));
        scroll.setBorder(BorderFactory.createLineBorder(new Color(51,65,85)));

        add(scroll);


        loadCombo();
        loadTable();



        // ===========================
        // EVENT SEWA
        // ===========================

        btnSewa.addActionListener(e -> {

            try{

                int pIndex = cmbPelanggan.getSelectedIndex();
                int kIndex = cmbKonsol.getSelectedIndex();

                if(pIndex < 0 || kIndex < 0){

                    JOptionPane.showMessageDialog(
                            this,
                            "Pilih pelanggan dan konsol!"
                    );

                    return;
                }


                int pelangganId =
                        pelangganList.get(pIndex).getId();

                int konsolId =
                        konsolList.get(kIndex).getId();


                int durasi =
                        Integer.parseInt(
                                txtDurasi.getText()
                        );


                RentalController.sewaKonsol(
                        pelangganId,
                        konsolId,
                        durasi
                );


                JOptionPane.showMessageDialog(
                        this,
                        "Sewa berhasil!"
                );


                loadCombo();
                loadTable();


            }catch(InputTidakValidException |
                    StokKosongException |
                    SQLException |
                    NumberFormatException ex){


                JOptionPane.showMessageDialog(
                        this,
                        "Error : "+ex.getMessage()
                );

            }

        });
        
                // ===========================
        // EVENT KEMBALI
        // ===========================

        btnKembali.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row < 0){

                JOptionPane.showMessageDialog(
                        this,
                        "Pilih konsol di tabel!"
                );

                return;
            }


            int konsolId = Integer.parseInt(
                    model.getValueAt(row,0).toString()
            );


            try{

                RentalController.kembalikanKonsol(
                        konsolId
                );


                JOptionPane.showMessageDialog(
                        this,
                        "Konsol dikembalikan!"
                );


                loadCombo();
                loadTable();


            }catch(SQLException ex){

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );

            }

        });

    }



    // ===========================
    // LOAD COMBO
    // ===========================

    private void loadCombo(){

        try{

            pelangganList =
                    DatabaseHelper.getAllPelanggan();

            konsolList =
                    DatabaseHelper.getAllKonsol();


            cmbPelanggan.removeAllItems();
            cmbKonsol.removeAllItems();


            for(Pelanggan p : pelangganList){

                cmbPelanggan.addItem(
                        p.getNama()
                );

            }


            for(UnitPlayStation u : konsolList){

                if("Tersedia".equals(u.getStatus())){

                    cmbKonsol.addItem(
                            u.getKode()
                            +" - "
                            +u.getTipe()
                    );

                }

            }


        }catch(SQLException ex){

            JOptionPane.showMessageDialog(
                    this,
                    "Error load combo : "
                    +ex.getMessage()
            );

        }

    }




    // ===========================
    // LOAD TABLE
    // ===========================

    private void loadTable(){

        try{

            model.setRowCount(0);


            List<UnitPlayStation> list =
                    DatabaseHelper.getAllKonsol();


            for(UnitPlayStation u : list){

                model.addRow(new Object[]{

                    u.getId(),
                    u.getKode(),
                    u.getTipe(),
                    u.getStatus()

                });

            }


        }catch(SQLException ex){

            JOptionPane.showMessageDialog(
                    this,
                    "Error load table : "
                    +ex.getMessage()
            );

        }

    }




    // ===========================
    // STYLE BUTTON
    // ===========================

    private void styleButton(
            JButton btn,
            Color color){

        btn.setBackground(color);
        btn.setForeground(Color.WHITE);

        btn.setFocusPainted(false);
        btn.setBorderPainted(false);

        btn.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

    }




    // ===========================
    // STYLE TEXTFIELD
    // ===========================

    private void styleTextField(
            JTextField txt){

        txt.setBackground(
                new Color(51,65,85)
        );

        txt.setForeground(Color.WHITE);

        txt.setCaretColor(Color.WHITE);


        txt.setBorder(
                BorderFactory.createLineBorder(
                        new Color(71,85,105)
                )
        );


        txt.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

    }




    // ===========================
    // STYLE COMBOBOX
    // ===========================

    private void styleCombo(
            JComboBox<String> combo){

        combo.setBackground(
                new Color(51,65,85)
        );

        combo.setForeground(Color.WHITE);

        combo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

    }


}
