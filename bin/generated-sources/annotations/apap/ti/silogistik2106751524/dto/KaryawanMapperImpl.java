package apap.ti.silogistik2106751524.dto;

import apap.ti.silogistik2106751524.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751524.model.Karyawan;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-22T15:08:37+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240325-1403, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class KaryawanMapperImpl implements KaryawanMapper {

    @Override
    public Karyawan createKaryawanRequestDTOToKaryawan(CreateKaryawanRequestDTO karyawanDTO) {
        if ( karyawanDTO == null ) {
            return null;
        }

        Karyawan karyawan = new Karyawan();

        karyawan.setJenisKelamin( karyawanDTO.getJenisKelamin() );
        karyawan.setNamaKaryawan( karyawanDTO.getNamaKaryawan() );
        karyawan.setTanggalLahir( karyawanDTO.getTanggalLahir() );

        return karyawan;
    }
}
