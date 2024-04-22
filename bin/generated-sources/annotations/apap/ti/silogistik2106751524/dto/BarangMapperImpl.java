package apap.ti.silogistik2106751524.dto;

import apap.ti.silogistik2106751524.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751524.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751524.model.Barang;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-22T15:08:37+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240325-1403, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class BarangMapperImpl implements BarangMapper {

    @Override
    public Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO) {
        if ( createBarangRequestDTO == null ) {
            return null;
        }

        Barang barang = new Barang();

        barang.setHargaBarang( createBarangRequestDTO.getHargaBarang() );
        barang.setMerkBarang( createBarangRequestDTO.getMerkBarang() );
        barang.setTipeBarang( createBarangRequestDTO.getTipeBarang() );

        return barang;
    }

    @Override
    public UpdateBarangRequestDTO barangToUpdateBarangRequestDTO(Barang barang) {
        if ( barang == null ) {
            return null;
        }

        UpdateBarangRequestDTO updateBarangRequestDTO = new UpdateBarangRequestDTO();

        updateBarangRequestDTO.setHargaBarang( barang.getHargaBarang() );
        updateBarangRequestDTO.setMerkBarang( barang.getMerkBarang() );
        updateBarangRequestDTO.setTipeBarang( barang.getTipeBarang() );
        updateBarangRequestDTO.setSku( barang.getSku() );

        return updateBarangRequestDTO;
    }

    @Override
    public Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO) {
        if ( updateBarangRequestDTO == null ) {
            return null;
        }

        Barang barang = new Barang();

        barang.setHargaBarang( updateBarangRequestDTO.getHargaBarang() );
        barang.setMerkBarang( updateBarangRequestDTO.getMerkBarang() );
        barang.setSku( updateBarangRequestDTO.getSku() );
        barang.setTipeBarang( updateBarangRequestDTO.getTipeBarang() );

        return barang;
    }
}
