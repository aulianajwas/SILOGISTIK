package apap.ti.silogistik2106751524.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import apap.ti.silogistik2106751524.model.Barang;
import apap.ti.silogistik2106751524.model.Gudang;
import apap.ti.silogistik2106751524.model.GudangBarang;

public interface GudangBarangDb extends JpaRepository<GudangBarang, Long> {
    List<GudangBarang> findByGudangIdGudang(Long idGudang);
    List<GudangBarang> findByBarang(Barang barang);
    List<Gudang> findGudangByBarangMerkBarang(String merk);
}
