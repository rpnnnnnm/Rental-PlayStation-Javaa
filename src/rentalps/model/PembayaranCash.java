package rentalps.model;

public class PembayaranCash implements Pembayaran {
    @Override
    public void bayar(double total) {
        System.out.println("Pembayaran cash sebesar Rp " + total);
    }

    @Override
    public String getMetode() {
        return "Cash";
    }
}
