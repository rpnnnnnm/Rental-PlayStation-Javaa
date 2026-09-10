package rentalps.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import rentalps.db.DatabaseHelper;
import rentalps.model.Pelanggan;

public class PelangganFrame extends JFrame {

    private JTextField txtNama, txtHp, txtAlamat;
    private JTable table;
    private DefaultTableModel model;
    private int selectedId = -1;

    public PelangganFrame() {

    setTitle("Kelola Pelanggan");
    setSize(720, 520);
    setLocationRelativeTo(null);
    setLayout(null);
    getContentPane().setBackground(new Color(15,23,42));
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // =========================
    // Judul
    // =========================

    JLabel title = new JLabel("KELOLA PELANGGAN");
    title.setBounds(25,20,300,30);
    title.setFont(new Font("Segoe UI",Font.BOLD,24));
    title.setForeground(Color.WHITE);
    add(title);

    // =========================
    // Panel Form
    // =========================

    JPanel form = new JPanel(null);
    form.setBounds(20,65,670,140);
    form.setBackground(new Color(30,41,59));
    add(form);

    JLabel lblNama = new JLabel("Nama");
    lblNama.setForeground(Color.WHITE);
    lblNama.setBounds(20,20,80,20);
    form.add(lblNama);

    txtNama = new JTextField();
    txtNama.setBounds(90,18,180,28);
    txtNama.setBackground(new Color(51,65,85));
    txtNama.setForeground(Color.WHITE);
    txtNama.setCaretColor(Color.WHITE);
    txtNama.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(71,85,105)));
    form.add(txtNama);

    JLabel lblHp = new JLabel("No HP");
    lblHp.setForeground(Color.WHITE);
    lblHp.setBounds(320,20,80,20);
    form.add(lblHp);

    txtHp = new JTextField();
    txtHp.setBounds(380,18,180,28);
    txtHp.setBackground(new Color(51,65,85));
    txtHp.setForeground(Color.WHITE);
    txtHp.setCaretColor(Color.WHITE);
    txtHp.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(71,85,105)));
    form.add(txtHp);

    JLabel lblAlamat = new JLabel("Alamat");
    lblAlamat.setForeground(Color.WHITE);
    lblAlamat.setBounds(20,65,80,20);
    form.add(lblAlamat);

    txtAlamat = new JTextField();
    txtAlamat.setBounds(90,63,470,28);
    txtAlamat.setBackground(new Color(51,65,85));
    txtAlamat.setForeground(Color.WHITE);
    txtAlamat.setCaretColor(Color.WHITE);
    txtAlamat.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(71,85,105)));
    form.add(txtAlamat);

    // =========================
    // Tombol
    // =========================

    JButton btnAdd = new JButton("Tambah");
    btnAdd.setBounds(90, 95, 110, 32);
    styleButton(btnAdd, new Color(59,130,246));
    form.add(btnAdd);

    JButton btnUpdate = new JButton("Update");
    btnUpdate.setBounds(215, 95, 110, 32);
    styleButton(btnUpdate, new Color(139,92,246));
    form.add(btnUpdate);

    JButton btnDelete = new JButton("Hapus");
    btnDelete.setBounds(340, 95, 110, 32);
    styleButton(btnDelete, new Color(239,68,68));
    form.add(btnDelete);

    // =========================
    // Table
    // =========================

    model = new DefaultTableModel(
            new String[]{"ID","Nama","No HP","Alamat"},0);

    table = new JTable(model);

    table.setRowHeight(28);
    table.setBackground(new Color(30,41,59));
    table.setForeground(Color.WHITE);
    table.setGridColor(new Color(51,65,85));
    table.setSelectionBackground(new Color(59,130,246));
    table.setSelectionForeground(Color.WHITE);
    table.setFillsViewportHeight(true);

    table.getTableHeader().setBackground(new Color(15,23,42));
    table.getTableHeader().setForeground(Color.WHITE);
    table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,13));

    JScrollPane scroll = new JScrollPane(table);
    scroll.setBounds(20,220,670,240);

    scroll.setBackground(new Color(30,41,59));
    scroll.getViewport().setBackground(new Color(30,41,59));
    scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(51,65,85)));

    add(scroll);

    loadData();

    // =========================
    // EVENT TAMBAH
    // =========================

    btnAdd.addActionListener(e -> {

        try{

            Pelanggan p = new Pelanggan(
                    0,
                    txtNama.getText(),
                    txtHp.getText(),
                    txtAlamat.getText());

            DatabaseHelper.addPelanggan(p);

            loadData();
            clearForm();

        }catch(SQLException ex){

            JOptionPane.showMessageDialog(this,ex.getMessage());

        }

    });

    // =========================
    // EVENT UPDATE
    // =========================

    btnUpdate.addActionListener(e -> {

        if(selectedId==-1) return;

        try{

            Pelanggan p=new Pelanggan(
                    selectedId,
                    txtNama.getText(),
                    txtHp.getText(),
                    txtAlamat.getText());

            DatabaseHelper.updatePelanggan(p);

            loadData();
            clearForm();

        }catch(SQLException ex){

            JOptionPane.showMessageDialog(this,ex.getMessage());

        }

    });

    // =========================
    // EVENT DELETE
    // =========================

    btnDelete.addActionListener(e -> {

        if(selectedId==-1) return;

        try{

            DatabaseHelper.deletePelanggan(selectedId);

            loadData();
            clearForm();

        }catch(SQLException ex){

            JOptionPane.showMessageDialog(this,ex.getMessage());

        }

    });

    // =========================
    // Pilih Row
    // =========================

    table.getSelectionModel().addListSelectionListener(e -> {

        int row = table.getSelectedRow();

        if(row>=0){

            selectedId=(int)model.getValueAt(row,0);

            txtNama.setText(model.getValueAt(row,1).toString());

            txtHp.setText(model.getValueAt(row,2).toString());

            txtAlamat.setText(model.getValueAt(row,3).toString());

        }

    });

}
    private void styleButton(JButton btn, Color color){

    btn.setBackground(color);
    btn.setForeground(Color.WHITE);
    btn.setFocusPainted(false);
    btn.setBorderPainted(false);
    btn.setFont(new Font("Segoe UI",Font.BOLD,13));

}

    private void clearForm() {

    txtNama.setText("");
    txtHp.setText("");
    txtAlamat.setText("");

    selectedId = -1;

}

    private void loadData() {

    try {

        model.setRowCount(0);

        List<Pelanggan> list = DatabaseHelper.getAllPelanggan();

        for (Pelanggan p : list) {

            model.addRow(new Object[]{
                p.getId(),
                p.getNama(),
                p.getNoHp(),
                p.getAlamat()
            });

        }

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(this,
                "Error : " + ex.getMessage());

    }

}
}