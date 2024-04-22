package apap.ti.silogistik2106751524.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751524.model.Barang;
import apap.ti.silogistik2106751524.model.GudangBarang;
import apap.ti.silogistik2106751524.repository.GudangBarangDb;

@Service
public class GudangBarangServiceImpl implements GudangBarangService {
    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public List<Barang> getAllBarangFromGudang(Long idGudang) {
        List<Barang> listBarang = new ArrayList<>();
        for (GudangBarang gudangBarang : gudangBarangDb.findByGudangIdGudang(idGudang)) {
            listBarang.add(gudangBarang.getBarang());
        }
        return listBarang;
    }

    @Override
    public List<GudangBarang> getAllGudangBarangByGudang(Long idGudang) {
        return gudangBarangDb.findByGudangIdGudang(idGudang);
    }
    
}
