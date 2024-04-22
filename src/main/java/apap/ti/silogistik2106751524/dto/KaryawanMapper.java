package apap.ti.silogistik2106751524.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106751524.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751524.model.Karyawan;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
    //Karyawan DTO to karyawan
    Karyawan createKaryawanRequestDTOToKaryawan(CreateKaryawanRequestDTO karyawanDTO);
}
