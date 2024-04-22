package apap.ti.silogistik2106751524.service;

import java.util.List;

import apap.ti.silogistik2106751524.model.Karyawan;

public interface KaryawanService {
    //Buat karyawan
    Karyawan createKaryawan(Karyawan karyawan);

    //Cari list karyawan
    List<Karyawan> getAllKaryawan();
}
