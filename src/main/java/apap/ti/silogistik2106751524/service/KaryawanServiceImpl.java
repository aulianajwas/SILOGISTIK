package apap.ti.silogistik2106751524.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751524.model.Karyawan;
import apap.ti.silogistik2106751524.repository.KaryawanDb;

@Service
public class KaryawanServiceImpl implements KaryawanService{
    @Autowired
    KaryawanDb karyawanDb;
    
    @Override
    public Karyawan createKaryawan(Karyawan karyawan) {
        return karyawanDb.save(karyawan);
    }

    @Override
    public List<Karyawan> getAllKaryawan() {
        return karyawanDb.findAll();
    }
    
    
}
