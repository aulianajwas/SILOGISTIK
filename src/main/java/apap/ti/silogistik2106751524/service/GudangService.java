package apap.ti.silogistik2106751524.service;

import java.util.List;

import apap.ti.silogistik2106751524.model.Gudang;

public interface GudangService {
    //Buat gudang
    Gudang createGudang(Gudang gudang);

    //Cari list gudang
    List<Gudang> getAllGudang();
    
    //Cari gudang berdasarkan id gudang
    Gudang getGudangById(Long id);
    
    //Restock gudang
    void restockGudang(Long id, Gudang gudangFromDTO);

    
}
