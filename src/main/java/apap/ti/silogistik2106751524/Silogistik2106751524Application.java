package apap.ti.silogistik2106751524;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;

import apap.ti.silogistik2106751524.dto.GudangMapper;
import apap.ti.silogistik2106751524.dto.KaryawanMapper;
import apap.ti.silogistik2106751524.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751524.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751524.service.GudangService;
import apap.ti.silogistik2106751524.service.KaryawanService;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class Silogistik2106751524Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106751524Application.class, args);
	}
	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, GudangMapper gudangMapper, KaryawanService karyawanService, KaryawanMapper karyawanMapper){
		return args -> {
			//Buat dummy data gudang
			var faker = new Faker(new Locale("in-ID"));

			var gudangDTO = new CreateGudangRequestDTO();
			var fakeNamaGudang = faker.company().name();
			var fakeAlamatGudang = faker.address().streetName();

			gudangDTO.setNamaGudang(fakeNamaGudang);
			gudangDTO.setAlamatGudang(fakeAlamatGudang);

			var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
			gudang = gudangService.createGudang(gudang);

			//Buat dummy data karyawan
			var karyawanDTO = new CreateKaryawanRequestDTO();
			var fakeNamaKaryawan = faker.name().fullName();
			var fakeJenisKelamin = faker.random().nextInt(2) + 1;
			var fakeTanggalLahir = faker.date().birthday();

			karyawanDTO.setNamaKaryawan(fakeNamaKaryawan);
			karyawanDTO.setJenisKelamin(fakeJenisKelamin);
			karyawanDTO.setTanggalLahir(LocalDate.ofInstant(fakeTanggalLahir.toInstant(), ZoneId.systemDefault()));

			var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);
			karyawan = karyawanService.createKaryawan(karyawan);
		};
	}

}
