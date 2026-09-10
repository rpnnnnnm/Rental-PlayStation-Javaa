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

import rentalps.db.DatabaseHelper;
import rentalps.model.PS3;
import rentalps.model.PS4;
import rentalps.model.PS5;
import rentalps.model.UnitPlayStation;

public class KonsolFrame extends JFrame {

    private JTextField txtKode;
    private JTextField txtTarif;

    private JComboBox<String> cmbTipe;
    private JComboBox<String> cmbStatus;

    private JTable table;
    private DefaultTableModel model;

    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;

    private int selectedId = -1;

    public KonsolFrame() {

        setTitle("Kelola Konsol");
        setSize(760,540);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(new Color(15,23,42));

        // ===========================
        // TITLE
        // ===========================

        JLabel title = new JLabel("KELOLA KONSOL");
        title.setBounds(25,20,300,35);
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

        JLabel lblKode = new JLabel("Kode");
        lblKode.setForeground(Color.WHITE);
        lblKode.setBounds(20,20,80,20);
        form.add(lblKode);

        txtKode = new JTextField();
        txtKode.setBounds(90,18,180,30);
        styleTextField(txtKode);
        form.add(txtKode);

        JLabel lblTipe = new JLabel("Tipe");
        lblTipe.setForeground(Color.WHITE);
        lblTipe.setBounds(350,20,80,20);
        form.add(lblTipe);

        cmbTipe = new JComboBox<>(
                new String[]{
                    "PS3",
                    "PS4",
                    "PS5"
                });

        cmbTipe.setBounds(410,18,180,30);
        styleCombo(cmbTipe);
        form.add(cmbTipe);

        JLabel lblTarif = new JLabel("Tarif");
        lblTarif.setForeground(Color.WHITE);
        lblTarif.setBounds(20,75,80,20);
        form.add(lblTarif);

        txtTarif = new JTextField();
        txtTarif.setBounds(90,73,180,30);
        styleTextField(txtTarif);
        form.add(txtTarif);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setForeground(Color.WHITE);
        lblStatus.setBounds(350,75,80,20);
        form.add(lblStatus);

        cmbStatus = new JComboBox<>(
                new String[]{
                    "Tersedia",
                    "Disewa"
                });

        cmbStatus.setBounds(410,73,180,30);
        styleCombo(cmbStatus);
        form.add(cmbStatus);

        btnAdd = new JButton("Tambah");
        btnAdd.setBounds(600, 15, 90, 30);
        styleButton(btnAdd,new Color(59,130,246));
        form.add(btnAdd);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(600, 52, 90, 30);
        styleButton(btnUpdate,new Color(139,92,246));
        form.add(btnUpdate);

        btnDelete = new JButton("Hapus");
        btnDelete.setBounds(600, 89, 90, 30);
        styleButton(btnDelete,new Color(239,68,68));
        form.add(btnDelete);

        // ===========================
        // TABLE
        // ===========================

        model = new DefaultTableModel(
                new String[]{
                    "ID",
                    "Kode",
                    "Tipe",
                    "Tarif/Jam",
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
        scroll.setBackground(new Color(30,41,59));
        scroll.setBorder(BorderFactory.createLineBorder(new Color(51,65,85)));

        add(scroll);

        loadData();
        
        // ===========================
        // EVENT TAMBAH
        // ===========================

        btnAdd.addActionListener(e -> {

            try{

                String tipe = cmbTipe.getSelectedItem().toString();
                double tarif = Double.parseDouble(txtTarif.getText());
                String status = cmbStatus.getSelectedItem().toString();

                UnitPlayStation u;

                if(tipe.equals("PS3")){

                    u = new PS3(
                            0,
                            txtKode.getText(),
                            tarif,
                            status);

                }else if(tipe.equals("PS5")){

                    u = new PS5(
                            0,
                            txtKode.getText(),
                            tarif,
                            status);

                }else{

                    u = new PS4(
                            0,
                            txtKode.getText(),
                            tarif,
                            status);

                }

                DatabaseHelper.addKonsol(u);

                loadData();
                clearForm();

            }catch(Exception ex){

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage());

            }

        });

        // ===========================
        // EVENT UPDATE
        // ===========================

        btnUpdate.addActionListener(e -> {

            if(selectedId==-1)
                return;

            try{

                String tipe = cmbTipe.getSelectedItem().toString();
                double tarif = Double.parseDouble(txtTarif.getText());
                String status = cmbStatus.getSelectedItem().toString();

                UnitPlayStation u;

                if(tipe.equals("PS3")){

                    u = new PS3(
                            selectedId,
                            txtKode.getText(),
                            tarif,
                            status);

                }else if(tipe.equals("PS5")){

                    u = new PS5(
                            selectedId,
                            txtKode.getText(),
                            tarif,
                            status);

                }else{

                    u = new PS4(
                            selectedId,
                            txtKode.getText(),
                            tarif,
                            status);

                }

                DatabaseHelper.updateKonsol(u);

                loadData();
                clearForm();

            }catch(Exception ex){

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage());

            }

        });

        // ===========================
        // EVENT DELETE
        // ===========================

        btnDelete.addActionListener(e -> {

            if(selectedId==-1)
                return;

            try{

                DatabaseHelper.deleteKonsol(selectedId);

                loadData();
                clearForm();

            }catch(SQLException ex){

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage());

            }

        });

        // ===========================
        // PILIH ROW
        // ===========================

        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if(row>=0){

                selectedId =
                        Integer.parseInt(
                                model.getValueAt(row,0).toString());

                txtKode.setText(
                        model.getValueAt(row,1).toString());

                cmbTipe.setSelectedItem(
                        model.getValueAt(row,2).toString());

                txtTarif.setText(
                        model.getValueAt(row,3).toString());

                cmbStatus.setSelectedItem(
                        model.getValueAt(row,4).toString());

            }

        });

        }
    // ===========================
    // LOAD DATA
    // ===========================

    private void loadData() {

        try {

            model.setRowCount(0);

            List<UnitPlayStation> list =
                    DatabaseHelper.getAllKonsol();

            for(UnitPlayStation u : list){

                model.addRow(new Object[]{

                    u.getId(),
                    u.getKode(),
                    u.getTipe(),
                    u.getTarifPerJam(),
                    u.getStatus()

                });

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage());

        }

    }

    // ===========================
    // CLEAR FORM
    // ===========================

    private void clearForm(){

        txtKode.setText("");
        txtTarif.setText("");

        cmbTipe.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);

        selectedId = -1;

    }

    // ===========================
    // STYLE BUTTON
    // ===========================

    private void styleButton(JButton btn, Color color){

        btn.setBackground(color);
        btn.setForeground(Color.WHITE);

        btn.setFocusPainted(false);
        btn.setBorderPainted(false);

        btn.setFont(new Font("Segoe UI",Font.BOLD,13));

    }

    // ===========================
    // STYLE TEXTFIELD
    // ===========================

    private void styleTextField(JTextField txt){

        txt.setBackground(new Color(51,65,85));
        txt.setForeground(Color.WHITE);

        txt.setCaretColor(Color.WHITE);

        txt.setBorder(
            BorderFactory.createLineBorder(
                new Color(71,85,105)
            )
        );

        txt.setFont(new Font("Segoe UI",Font.PLAIN,13));

    }

    // ===========================
    // STYLE COMBOBOX
    // ===========================

    private void styleCombo(JComboBox<String> combo){

        combo.setBackground(new Color(51,65,85));
        combo.setForeground(Color.WHITE);

        combo.setFont(new Font("Segoe UI",Font.PLAIN,13));

    }
}