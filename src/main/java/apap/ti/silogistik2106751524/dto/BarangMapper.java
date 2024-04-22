package apap.ti.silogistik2106751524.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106751524.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751524.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751524.model.Barang;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    //Barang DTO to barang
    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);

    //Barang to update barang DTO
    UpdateBarangRequestDTO barangToUpdateBarangRequestDTO(Barang barang);

    //Update barang DTO to barang
    Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);
}
