package apap.ti.silogistik2106751524.dto.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Valid
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateBarangRequestDTO {
    private String merkBarang;
    private int tipeBarang;
    private long hargaBarang;
}
