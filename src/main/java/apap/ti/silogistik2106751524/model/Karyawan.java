package apap.ti.silogistik2106751524.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "karyawan")
public class Karyawan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long idKaryawan;

    @NotNull
    @Size(max = 30)
    @Column(name = "nama_karyawan", nullable = false)
    private String namaKaryawan;

    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private int jenisKelamin;

    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    private LocalDate tanggalLahir;
    
}
