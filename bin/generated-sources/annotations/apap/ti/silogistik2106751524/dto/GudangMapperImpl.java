package apap.ti.silogistik2106751524.dto;

import apap.ti.silogistik2106751524.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751524.dto.request.GudangToReadGudangDTO;
import apap.ti.silogistik2106751524.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106751524.model.Gudang;
import apap.ti.silogistik2106751524.model.GudangBarang;
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
public class GudangMapperImpl implements GudangMapper {

    @Override
    public Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO) {
        if ( createGudangRequestDTO == null ) {
            return null;
        }

        Gudang gudang = new Gudang();

        gudang.setAlamatGudang( createGudangRequestDTO.getAlamatGudang() );
        List<GudangBarang> list = createGudangRequestDTO.getListGudangBarang();
        if ( list != null ) {
            gudang.setListGudangBarang( new ArrayList<GudangBarang>( list ) );
        }
        gudang.setNamaGudang( createGudangRequestDTO.getNamaGudang() );

        return gudang;
    }

    @Override
    public GudangToReadGudangDTO gudangToReadGudangDTO(Gudang gudang) {
        if ( gudang == null ) {
            return null;
        }

        GudangToReadGudangDTO gudangToReadGudangDTO = new GudangToReadGudangDTO();

        gudangToReadGudangDTO.setAlamatGudang( gudang.getAlamatGudang() );
        gudangToReadGudangDTO.setIdGudang( gudang.getIdGudang() );
        List<GudangBarang> list = gudang.getListGudangBarang();
        if ( list != null ) {
            gudangToReadGudangDTO.setListGudangBarang( new ArrayList<GudangBarang>( list ) );
        }
        gudangToReadGudangDTO.setNamaGudang( gudang.getNamaGudang() );

        return gudangToReadGudangDTO;
    }

    @Override
    public Gudang updateGudangRequestDTOToGudang(UpdateGudangRequestDTO gudangDTO) {
        if ( gudangDTO == null ) {
            return null;
        }

        Gudang gudang = new Gudang();

        gudang.setAlamatGudang( gudangDTO.getAlamatGudang() );
        if ( gudangDTO.getIdGudang() != null ) {
            gudang.setIdGudang( gudangDTO.getIdGudang() );
        }
        List<GudangBarang> list = gudangDTO.getListGudangBarang();
        if ( list != null ) {
            gudang.setListGudangBarang( new ArrayList<GudangBarang>( list ) );
        }
        gudang.setNamaGudang( gudangDTO.getNamaGudang() );

        return gudang;
    }
}
