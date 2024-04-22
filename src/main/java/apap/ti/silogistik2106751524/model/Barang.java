package apap.ti.silogistik2106751524.model;

import java.util.List;

import jakarta.persistence.*;
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
@Table(name = "barang")

public class Barang {
    @Id
    @Size(max=20)
    @Column(name = "sku")
    private String sku;

    @NotNull
    @Column(name = "tipe_barang", nullable = false)
    private int tipeBarang;

    @NotNull
    @Column(name = "merk", nullable = false)
    private String merkBarang;

    @NotNull
    @Column(name = "harga_barang", nullable = false)
    private long hargaBarang;

    @OneToMany(mappedBy = "barang", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<GudangBarang> listGudangBarang;
}
