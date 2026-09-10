CREATE DATABASE IF NOT EXISTS rentalps;
USE rentalps;

CREATE TABLE IF NOT EXISTS pelanggan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    no_hp VARCHAR(20),
    alamat TEXT
);

CREATE TABLE IF NOT EXISTS konsol (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kode VARCHAR(20) NOT NULL,
    tipe VARCHAR(10) NOT NULL,
    tarif_per_jam DOUBLE NOT NULL,
    status VARCHAR(20) DEFAULT 'Tersedia'
);

CREATE TABLE IF NOT EXISTS transaksi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pelanggan_id INT,
    konsol_id INT,
    tanggal_sewa DATETIME,
    durasi_jam INT,
    total_biaya DOUBLE,
    status VARCHAR(20) DEFAULT 'Sewa',
    FOREIGN KEY (pelanggan_id) REFERENCES pelanggan(id),
    FOREIGN KEY (konsol_id) REFERENCES konsol(id)
);

INSERT INTO pelanggan (nama, no_hp, alamat) VALUES
('Budi Santoso', '081234567890', 'Bandung'),
('Ani Wijaya', '081298765432', 'Jakarta'),
('Citra Dewi', '081345678901', 'Surabaya');

INSERT INTO konsol (kode, tipe, tarif_per_jam, status) VALUES
('PS3-001', 'PS3', 5000, 'Tersedia'),
('PS4-001', 'PS4', 8000, 'Tersedia'),
('PS5-001', 'PS5', 12000, 'Tersedia'),
('PS4-002', 'PS4', 8000, 'Disewa');
