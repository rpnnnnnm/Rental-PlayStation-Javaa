package rentalps.model;

public class PS4 extends UnitPlayStation {
    public PS4(int id, String kode, double tarifPerJam, String status) {
        super(id, kode, "PS4", tarifPerJam, status);
    }

    @Override
    public double hitungSewa(int jam) {
        return tarifPerJam * jam;
    }
}
