package rentalps.controller;

import java.sql.SQLException;
import java.util.List;
import rentalps.db.DatabaseHelper;
import rentalps.model.Pelanggan;
import rentalps.model.UnitPlayStation;
import rentalps.util.InputTidakValidException;
import rentalps.util.StokKosongException;

public class RentalController {

    public static void sewaKonsol(int pelangganId, int konsolId, int durasiJam)
            throws SQLException, InputTidakValidException, StokKosongException {

        if (durasiJam <= 0) {
            throw new InputTidakValidException("Durasi sewa harus lebih dari 0 jam");
        }

        List<UnitPlayStation> konsolList = DatabaseHelper.getAllKonsol();
        UnitPlayStation target = null;
        for (UnitPlayStation u : konsolList) {
            if (u.getId() == konsolId) {
                target = u;
                break;
            }
        }

        if (target == null) {
            throw new InputTidakValidException("Konsol tidak ditemukan");
        }

        if (!"Tersedia".equals(target.getStatus())) {
            throw new StokKosongException("Konsol sedang disewa atau tidak tersedia");
        }

        double total = target.hitungSewa(durasiJam);
        DatabaseHelper.addTransaksi(pelangganId, konsolId, durasiJam, total);
        DatabaseHelper.updateStatusKonsol(konsolId, "Disewa");
    }

    public static void kembalikanKonsol(int konsolId) throws SQLException {
        DatabaseHelper.updateStatusKonsol(konsolId, "Tersedia");
    }
}
