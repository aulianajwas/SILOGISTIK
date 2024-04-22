package apap.ti.silogistik2106751524.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751524.model.Barang;

@Repository
public interface BarangDb extends JpaRepository<Barang, String>{
    List<Barang> findAll();
    Barang findFirstByTipeBarangOrderBySkuDesc(int kode);
    List<Barang> findAllByOrderByMerkBarangAsc();

}
