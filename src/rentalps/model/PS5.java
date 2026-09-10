package rentalps.model;

public class PS5 extends UnitPlayStation {
    public PS5(int id, String kode, double tarifPerJam, String status) {
        super(id, kode, "PS5", tarifPerJam, status);
    }

    @Override
    public double hitungSewa(int jam) {
        return tarifPerJam * jam * 1.1; // premium 10%
    }
}
