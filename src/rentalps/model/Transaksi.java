package rentalps.model;

import java.util.Date;

public class Transaksi {
    private int id;
    private int pelangganId;
    private int konsolId;
    private Date tanggalSewa;
    private int durasiJam;
    private double totalBiaya;
    private String status;

    public Transaksi() {}

    public Transaksi(int id, int pelangganId, int konsolId, Date tanggalSewa, int durasiJam, double totalBiaya, String status) {
        this.id = id;
        this.pelangganId = pelangganId;
        this.konsolId = konsolId;
        this.tanggalSewa = tanggalSewa;
        this.durasiJam = durasiJam;
        this.totalBiaya = totalBiaya;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPelangganId() { return pelangganId; }
    public void setPelangganId(int pelangganId) { this.pelangganId = pelangganId; }
    public int getKonsolId() { return konsolId; }
    public void setKonsolId(int konsolId) { this.konsolId = konsolId; }
    public Date getTanggalSewa() { return tanggalSewa; }
    public void setTanggalSewa(Date tanggalSewa) { this.tanggalSewa = tanggalSewa; }
    public int getDurasiJam() { return durasiJam; }
    public void setDurasiJam(int durasiJam) { this.durasiJam = durasiJam; }
    public double getTotalBiaya() { return totalBiaya; }
    public void setTotalBiaya(double totalBiaya) { this.totalBiaya = totalBiaya; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
