package rentalps.model;

public abstract class UnitPlayStation {
    protected int id;
    protected String kode;
    protected String tipe;
    protected double tarifPerJam;
    protected String status;

    public UnitPlayStation(int id, String kode, String tipe, double tarifPerJam, String status) {
        this.id = id;
        this.kode = kode;
        this.tipe = tipe;
        this.tarifPerJam = tarifPerJam;
        this.status = status;
    }

    public abstract double hitungSewa(int jam);

    public int getId() { return id; }
    public String getKode() { return kode; }
    public String getTipe() { return tipe; }
    public double getTarifPerJam() { return tarifPerJam; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
