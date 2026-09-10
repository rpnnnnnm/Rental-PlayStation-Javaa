package rentalps.model;

public class PembayaranTransfer implements Pembayaran {
    @Override
    public void bayar(double total) {
        System.out.println("Pembayaran transfer sebesar Rp " + total);
    }

    @Override
    public String getMetode() {
        return "Transfer";
    }
}
