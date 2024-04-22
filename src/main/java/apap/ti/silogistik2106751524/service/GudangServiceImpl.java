package apap.ti.silogistik2106751524.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751524.dto.GudangMapper;
import apap.ti.silogistik2106751524.model.Gudang;
import apap.ti.silogistik2106751524.model.GudangBarang;
import apap.ti.silogistik2106751524.repository.GudangBarangDb;
import apap.ti.silogistik2106751524.repository.GudangDb;

@Service
public class GudangServiceImpl implements GudangService{
    @Autowired
    GudangDb gudangDb;

    @Autowired
    GudangMapper gudangMapper;

    @Autowired
    GudangBarangDb gudangBarangDb;
    
    @Override
    public Gudang createGudang(Gudang gudang) {
        return gudangDb.save(gudang);
    }

    @Override
    public List<Gudang> getAllGudang() {
        return gudangDb.findAll();
    }

    @Override
    public Gudang getGudangById(Long id) {
        return gudangDb.findById(id).get();
    }

    @Override
    public void restockGudang(Long id, Gudang gudangFromDTO) {
        Gudang gudang = getGudangById(id);
        List<GudangBarang> listGudangBarang = gudang.getListGudangBarang();
        List<GudangBarang> listGudangBarangDTO = gudangFromDTO.getListGudangBarang();

        Map<String, GudangBarang> mapBarang = new HashMap<>();
        for (GudangBarang gudangBarang : listGudangBarang) {
            mapBarang.put(gudangBarang.getBarang().getSku(), gudangBarang);
            
        }

        for (GudangBarang gudangBarangDTO : listGudangBarangDTO) {
            String sku = gudangBarangDTO.getBarang().getSku();
            if (mapBarang.containsKey(sku)) {
                GudangBarang gudangBarangExisting=  mapBarang.get(sku);
                gudangBarangExisting.setStok(gudangBarangDTO.getStok());
            }
            else{
                gudangBarangDTO.setGudang(gudang);
                listGudangBarang.add(gudangBarangDTO);
            }
        }
        gudangDb.save(gudang);
    }

    
    
    
}
