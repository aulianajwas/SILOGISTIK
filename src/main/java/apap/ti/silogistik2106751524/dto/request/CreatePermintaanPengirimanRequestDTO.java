package apap.ti.silogistik2106751524.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import apap.ti.silogistik2106751524.model.Karyawan;
import apap.ti.silogistik2106751524.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanRequestDTO {
    private Long idPengiriman;
    private String nomorPengiriman;
    private boolean isCancelled;
    private String namaPenerima;
    private String alamatPenerima;
    private LocalDate tanggalPengiriman;
    private int biayaPengiriman;
    private int jenisLayanan;
    private LocalDateTime waktuPermintaan;
    private Karyawan karyawan;
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
