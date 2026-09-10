package rentalps.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import rentalps.model.Pelanggan;
import rentalps.model.UnitPlayStation;
import rentalps.model.PS3;
import rentalps.model.PS4;
import rentalps.model.PS5;

public class DatabaseHelper {

    public static List<Pelanggan> getAllPelanggan() throws SQLException {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Pelanggan(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("no_hp"),
                    rs.getString("alamat")
                ));
            }
        }
        return list;
    }

    public static void addPelanggan(Pelanggan p) throws SQLException {
        String sql = "INSERT INTO pelanggan (nama, no_hp, alamat) VALUES (?, ?, ?)";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getNama());
            ps.setString(2, p.getNoHp());
            ps.setString(3, p.getAlamat());
            ps.executeUpdate();
        }
    }

    public static void updatePelanggan(Pelanggan p) throws SQLException {
        String sql = "UPDATE pelanggan SET nama=?, no_hp=?, alamat=? WHERE id=?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getNama());
            ps.setString(2, p.getNoHp());
            ps.setString(3, p.getAlamat());
            ps.setInt(4, p.getId());
            ps.executeUpdate();
        }
    }

    public static void deletePelanggan(int id) throws SQLException {
        String sql = "DELETE FROM pelanggan WHERE id=?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public static List<UnitPlayStation> getAllKonsol() throws SQLException {
        List<UnitPlayStation> list = new ArrayList<>();
        String sql = "SELECT * FROM konsol";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String tipe = rs.getString("tipe");
                int id = rs.getInt("id");
                String kode = rs.getString("kode");
                double tarif = rs.getDouble("tarif_per_jam");
                String status = rs.getString("status");
                UnitPlayStation u;
                if ("PS3".equals(tipe)) u = new PS3(id, kode, tarif, status);
                else if ("PS5".equals(tipe)) u = new PS5(id, kode, tarif, status);
                else u = new PS4(id, kode, tarif, status);
                list.add(u);
            }
        }
        return list;
    }

    public static void addKonsol(UnitPlayStation u) throws SQLException {
        String sql = "INSERT INTO konsol (kode, tipe, tarif_per_jam, status) VALUES (?, ?, ?, ?)";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getKode());
            ps.setString(2, u.getTipe());
            ps.setDouble(3, u.getTarifPerJam());
            ps.setString(4, u.getStatus());
            ps.executeUpdate();
        }
    }

    public static void updateKonsol(UnitPlayStation u) throws SQLException {
        String sql = "UPDATE konsol SET kode=?, tipe=?, tarif_per_jam=?, status=? WHERE id=?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getKode());
            ps.setString(2, u.getTipe());
            ps.setDouble(3, u.getTarifPerJam());
            ps.setString(4, u.getStatus());
            ps.setInt(5, u.getId());
            ps.executeUpdate();
        }
    }

    public static void deleteKonsol(int id) throws SQLException {
        String sql = "DELETE FROM konsol WHERE id=?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public static void addTransaksi(int pelangganId, int konsolId, int durasiJam, double totalBiaya) throws SQLException {
        String sql = "INSERT INTO transaksi (pelanggan_id, konsol_id, tanggal_sewa, durasi_jam, total_biaya, status) VALUES (?, ?, NOW(), ?, ?, 'Sewa')";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, pelangganId);
            ps.setInt(2, konsolId);
            ps.setInt(3, durasiJam);
            ps.setDouble(4, totalBiaya);
            ps.executeUpdate();
        }
    }

    public static void updateStatusKonsol(int konsolId, String status) throws SQLException {
        String sql = "UPDATE konsol SET status=? WHERE id=?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, konsolId);
            ps.executeUpdate();
        }
    }
    
    public static int getTotalKonsol() throws SQLException {
    String sql = "SELECT COUNT(*) FROM konsol";
    try (Connection c = Koneksi.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getInt(1);
        }
    }
    return 0;
}
    
    public static int getTotalTersedia() throws SQLException {
    String sql = "SELECT COUNT(*) FROM konsol WHERE status='Tersedia'";
    try (Connection c = Koneksi.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getInt(1);
        }
    }
    return 0;
}
    
    public static int getTotalDisewa() throws SQLException {
    String sql = "SELECT COUNT(*) FROM konsol WHERE status='Disewa'";
    try (Connection c = Koneksi.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getInt(1);
        }
    }
    return 0;
}
    
    public static double getPendapatan() throws SQLException {
    String sql = "SELECT IFNULL(SUM(total_biaya),0) FROM transaksi";
    try (Connection c = Koneksi.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getDouble(1);
        }
    }
    return 0;
}
    
    public static int getTotalTransaksi() throws SQLException {
    String sql = "SELECT COUNT(*) FROM transaksi";

    try (Connection c = Koneksi.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getInt(1);
        }
    }

    return 0;
}
    
    public static ResultSet cariPelanggan(String keyword) throws SQLException {
    String sql = "SELECT * FROM pelanggan WHERE nama LIKE ?";
    Connection conn = Koneksi.getConnection();
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, "%" + keyword + "%");
    return ps.executeQuery();
}
    public static ResultSet getRiwayatTransaksi() throws SQLException {
    String sql =
        "SELECT p.nama, k.kode, k.tipe, t.durasi_jam, t.total_biaya " +
        "FROM transaksi t " +
        "JOIN pelanggan p ON t.pelanggan_id = p.id " +
        "JOIN konsol k ON t.konsol_id = k.id " +
        "ORDER BY t.id DESC";

    Connection conn = Koneksi.getConnection();
    PreparedStatement ps = conn.prepareStatement(sql);

    return ps.executeQuery();
}
}
