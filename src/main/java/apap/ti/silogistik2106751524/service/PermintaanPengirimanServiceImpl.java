package apap.ti.silogistik2106751524.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751524.model.Barang;
import apap.ti.silogistik2106751524.model.PermintaanPengiriman;
import apap.ti.silogistik2106751524.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751524.repository.PermintaanPengirimanDb;


@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;
    
    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() {
       return permintaanPengirimanDb.findAll();
    }

    @Override
    public String generateNomorPengiriman(int kuantitas, LocalDateTime waktuPermintaan, int kode) {
        String tipe = "";
        if (kode == 1) {
            tipe = "SAM";
        }
        else if (kode == 2) {
            tipe = "KIL";
        }
        else if (kode == 3) {
            tipe = "REG";
        }
        else if (kode == 4) {
            tipe = "HEM";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return String.format("REQ%02d%s%s", kuantitas%100, tipe, waktuPermintaan.format(formatter));
    }

    @Override
    public PermintaanPengiriman createPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        int kuantitas = 0;
        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengiriman.getListPermintaanPengirimanBarang()) {
            kuantitas += permintaanPengirimanBarang.getKuantitasPengiriman();
            permintaanPengirimanBarang.setPermintaanPengiriman(permintaanPengiriman);
        }
        permintaanPengiriman.setWaktuPermintaan(LocalDateTime.now());
        String nomorPengiriman = generateNomorPengiriman(kuantitas, permintaanPengiriman.getWaktuPermintaan(), permintaanPengiriman.getJenisLayanan());
        permintaanPengiriman.setNomorPengiriman(nomorPengiriman);
    
        return permintaanPengirimanDb.save(permintaanPengiriman);
    }

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(Long id) {
        return permintaanPengirimanDb.findById(id).get();
    }

    @Override
    public long getTotalHarga(PermintaanPengiriman permintaanPengiriman) {
        long totalHarga = 0;
        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengiriman.getListPermintaanPengirimanBarang()) {
            long hargaSatuan = permintaanPengirimanBarang.getBarang().getHargaBarang();
            int kuantitas = permintaanPengirimanBarang.getKuantitasPengiriman();
            totalHarga += hargaSatuan * kuantitas;
        }
        return totalHarga;
    }

    @Override
    public void deletePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        permintaanPengirimanDb.delete(permintaanPengiriman);
    }

    @Override
    public int validateCancel(PermintaanPengiriman permintaanPengiriman) {
        Duration duration = Duration.between(permintaanPengiriman.getWaktuPermintaan(), LocalDateTime.now());
        if (duration.toHours() >= 24) {
            return 1;
        }
        return 0;
    }
    
}
