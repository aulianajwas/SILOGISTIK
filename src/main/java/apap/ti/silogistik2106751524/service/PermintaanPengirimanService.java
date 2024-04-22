package apap.ti.silogistik2106751524.service;

import java.time.LocalDateTime;
import java.util.List;

import apap.ti.silogistik2106751524.model.PermintaanPengiriman;

public interface PermintaanPengirimanService {
    //Cari list permintaan pengiriman
    List<PermintaanPengiriman> getAllPermintaanPengiriman();

    //Generate nomor pengiriman
    String generateNomorPengiriman(int kuantitas, LocalDateTime waktuPermintaan, int kode);

    //Buat permintaan pengiriman
    PermintaanPengiriman createPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);

    //Cari permintaan pengiriman berdasarkan id permintaan pengiriman
    PermintaanPengiriman getPermintaanPengirimanById(Long id);

    //Cari total harga permintaan pengiriman
    long getTotalHarga(PermintaanPengiriman permintaanPengiriman);

    //Cancel permintaan pengiriman
    void deletePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);

    //Validasi waktu request cancel
    int validateCancel(PermintaanPengiriman permintaanPengiriman);
}
