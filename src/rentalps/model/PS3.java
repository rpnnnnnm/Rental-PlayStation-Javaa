package rentalps.model;

public class PS3 extends UnitPlayStation {
    public PS3(int id, String kode, double tarifPerJam, String status) {
        super(id, kode, "PS3", tarifPerJam, status);
    }

    @Override
    public double hitungSewa(int jam) {
        return tarifPerJam * jam * 0.9; // diskon 10%
    }
}
