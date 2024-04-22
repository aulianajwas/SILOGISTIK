package apap.ti.silogistik2106751524.service;

import java.util.List;

import apap.ti.silogistik2106751524.model.Barang;
import apap.ti.silogistik2106751524.model.Gudang;
import apap.ti.silogistik2106751524.model.GudangBarang;

public interface BarangService {
    //Cari list barang
    List<Barang> getAllBarang();

    //Generate sku barang
    String generateSku(int kode);

    //Buat barang
    Barang createBarang(Barang barang);

    //Cari barang berdasarkan sku
    Barang getBarangById(String idBarang);

    //Cari gudang barang dari sebuah barang
    List<GudangBarang> getAllGudangBarangByBarang(Barang barang);

    //Ubah barang
    Barang ubahBarang(Barang barangFromDTO);

    //Cari list stok barang
    List<Integer> listStokBarang(List<Barang> listBarang);

    //Cari total stok barang
    int getTotalStok(Barang barang);

    //Cari gudang dari sebuah barang
    List<Gudang> getListGudangSebuahBarang(List<GudangBarang> listGudangBarang, String id);

    //Sort list barang secara alfabetikal
    List<Barang> sortListBarang(List<Barang> listBarang);
}
