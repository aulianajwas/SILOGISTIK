package apap.ti.silogistik2106751524.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751524.model.Barang;
import apap.ti.silogistik2106751524.model.Gudang;
import apap.ti.silogistik2106751524.model.GudangBarang;
import apap.ti.silogistik2106751524.repository.BarangDb;
import apap.ti.silogistik2106751524.repository.GudangBarangDb;

@Service
public class BarangServiceImpl implements BarangService{
    @Autowired
    BarangDb barangDb;

    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public List<Barang> getAllBarang() {
        return barangDb.findAll();
    }

    @Override
    public String generateSku(int kode) {
        String tipe = "";
        if (kode == 1) {
            tipe = "ELEC";
        }
        else if (kode == 2) {
            tipe = "CLOT";
        }
        else if (kode == 3) {
            tipe = "FOOD";
        }
        else if (kode == 4) {
            tipe = "COSM";
        }
        else{
            tipe = "TOOL";
        }
        Barang lastBarang = barangDb.findFirstByTipeBarangOrderBySkuDesc(kode);
        String lastSku = "";
        if (lastBarang != null && lastBarang.getSku() != null) {
            lastSku = lastBarang.getSku();  
        }
        int digit;
        if (lastSku.isEmpty()) {
            digit = 1; 
        }
        else{
            String digitAkhir = lastSku.substring(4, 7);
            digit = Integer.parseInt(digitAkhir)+1;
        }
        String sku = String.format("%s%03d",tipe, digit);
        
        return sku;
    }

    @Override
    public Barang createBarang(Barang barang) {
        String sku = generateSku(barang.getTipeBarang());
        barang.setSku(sku);
        return barangDb.save(barang);
    }

    @Override
    public Barang getBarangById(String idBarang) {
        return barangDb.findById(idBarang).get();
    }

    @Override
    public List<GudangBarang> getAllGudangBarangByBarang(Barang barang) {
        return gudangBarangDb.findByBarang(barang);
    }

    @Override
    public Barang ubahBarang(Barang barangFromDTO) {
        Barang barang = getBarangById(barangFromDTO.getSku());
        if (barang != null) {
            barang.setSku(barangFromDTO.getSku());
            barang.setTipeBarang(barangFromDTO.getTipeBarang());
            barang.setMerkBarang(barangFromDTO.getMerkBarang());
            barang.setHargaBarang(barangFromDTO.getHargaBarang());
            barangDb.save(barang);
        }
        return barang;
    }

    @Override
    public List<Integer> listStokBarang(List<Barang> listBarang) {
        List<Integer> listStok = new ArrayList<>();
        for (Barang barang : listBarang) {
            int stok = 0;
            for (GudangBarang gudangBarang : barang.getListGudangBarang()) {
                stok += gudangBarang.getStok();
            }
            listStok.add(stok);
        }
        return listStok;
    }

    @Override
    public int getTotalStok(Barang barang) {
        int totalStok = 0;
        // if (barang.getListGudangBarang() == null) {
        //     return 0;
        // }
        for (GudangBarang gudangBarang : barang.getListGudangBarang()) {
            totalStok += gudangBarang.getStok();
        }
        return totalStok;
    }

    @Override
    public List<Gudang> getListGudangSebuahBarang(List<GudangBarang> listGudangBarang, String id) {
        List<Gudang> listGudang = new ArrayList<>();
        for (GudangBarang gudangBarang : listGudangBarang) {
            if (gudangBarang.getBarang().getSku().equals(id)) {
                listGudang.add(gudangBarang.getGudang());
            }
        }
        return listGudang;
    }

    @Override
    public List<Barang> sortListBarang(List<Barang> listBarang) {
        return barangDb.findAllByOrderByMerkBarangAsc();
    }
   
}
