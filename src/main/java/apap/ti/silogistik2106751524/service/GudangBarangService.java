package apap.ti.silogistik2106751524.service;

import java.util.List;

import apap.ti.silogistik2106751524.model.Barang;
import apap.ti.silogistik2106751524.model.GudangBarang;

public interface GudangBarangService {
    //Cari daftar barang dari sebuah gudang barang
    List<Barang> getAllBarangFromGudang(Long idGudang);

    //Cari gudang barang berdasarkan id gudang barang
    List<GudangBarang> getAllGudangBarangByGudang(Long idGudang);
}
