package apap.ti.silogistik2106751524.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106751524.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751524.dto.request.GudangToReadGudangDTO;
import apap.ti.silogistik2106751524.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106751524.model.Gudang;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    //Gudang DTO to gudang
    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);

    //Gudang to gudang DTO
    GudangToReadGudangDTO gudangToReadGudangDTO(Gudang gudang);

    //Update gudang DTO to gudang
    Gudang updateGudangRequestDTOToGudang(UpdateGudangRequestDTO gudangDTO);
}
