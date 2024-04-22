package apap.ti.silogistik2106751524.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106751524.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751524.model.PermintaanPengiriman;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    //Permintaan pengiriman DTO to permintaan pengiriman
    PermintaanPengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);
}
