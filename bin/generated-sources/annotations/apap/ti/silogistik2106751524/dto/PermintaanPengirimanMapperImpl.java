package apap.ti.silogistik2106751524.dto;

import apap.ti.silogistik2106751524.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751524.model.PermintaanPengiriman;
import apap.ti.silogistik2106751524.model.PermintaanPengirimanBarang;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-22T15:08:37+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240325-1403, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class PermintaanPengirimanMapperImpl implements PermintaanPengirimanMapper {

    @Override
    public PermintaanPengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO) {
        if ( createPermintaanPengirimanRequestDTO == null ) {
            return null;
        }

        PermintaanPengiriman permintaanPengiriman = new PermintaanPengiriman();

        permintaanPengiriman.setAlamatPenerima( createPermintaanPengirimanRequestDTO.getAlamatPenerima() );
        permintaanPengiriman.setBiayaPengiriman( createPermintaanPengirimanRequestDTO.getBiayaPengiriman() );
        permintaanPengiriman.setCancelled( createPermintaanPengirimanRequestDTO.isCancelled() );
        permintaanPengiriman.setIdPengiriman( createPermintaanPengirimanRequestDTO.getIdPengiriman() );
        permintaanPengiriman.setJenisLayanan( createPermintaanPengirimanRequestDTO.getJenisLayanan() );
        permintaanPengiriman.setKaryawan( createPermintaanPengirimanRequestDTO.getKaryawan() );
        List<PermintaanPengirimanBarang> list = createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang();
        if ( list != null ) {
            permintaanPengiriman.setListPermintaanPengirimanBarang( new ArrayList<PermintaanPengirimanBarang>( list ) );
        }
        permintaanPengiriman.setNamaPenerima( createPermintaanPengirimanRequestDTO.getNamaPenerima() );
        permintaanPengiriman.setNomorPengiriman( createPermintaanPengirimanRequestDTO.getNomorPengiriman() );
        permintaanPengiriman.setTanggalPengiriman( createPermintaanPengirimanRequestDTO.getTanggalPengiriman() );
        permintaanPengiriman.setWaktuPermintaan( createPermintaanPengirimanRequestDTO.getWaktuPermintaan() );

        return permintaanPengiriman;
    }
}
