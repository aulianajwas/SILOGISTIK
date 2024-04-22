package apap.ti.silogistik2106751524.dto.request;

import java.util.List;

import apap.ti.silogistik2106751524.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GudangToReadGudangDTO {
    private long idGudang;
    private String namaGudang;
    private String alamatGudang;
    private List<GudangBarang> listGudangBarang;
}


