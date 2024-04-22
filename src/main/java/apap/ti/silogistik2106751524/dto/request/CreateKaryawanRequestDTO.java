package apap.ti.silogistik2106751524.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateKaryawanRequestDTO {
    String namaKaryawan;
    int jenisKelamin;
    LocalDate tanggalLahir;
    
}
