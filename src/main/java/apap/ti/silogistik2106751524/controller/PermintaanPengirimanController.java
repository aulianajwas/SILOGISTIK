package apap.ti.silogistik2106751524.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ti.silogistik2106751524.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106751524.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751524.model.Barang;
import apap.ti.silogistik2106751524.model.PermintaanPengiriman;
import apap.ti.silogistik2106751524.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751524.service.BarangService;
import apap.ti.silogistik2106751524.service.KaryawanService;
import apap.ti.silogistik2106751524.service.PermintaanPengirimanService;

@Controller
public class PermintaanPengirimanController {

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    BarangService barangService;

    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper;

    //Daftar permintaan pengiriman
    @GetMapping("/permintaan-pengiriman")
    public String viewAllPermintaanPengiriman(Model model){
        List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();
       
        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);
        model.addAttribute("page", "PermintaanPengiriman");
        return "viewall-permintaan-pengiriman";
    }
    //Detail permintaan pengiriman
    @GetMapping("/permintaan-pengiriman/{idPermintaanPengiriman}")
    public String viewPermintaanPengiriman(@PathVariable("idPermintaanPengiriman") long idPermintaanPengiriman, Model model){
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);

        model.addAttribute("permintaanPengiriman", permintaanPengiriman);
        model.addAttribute("totalHarga", permintaanPengirimanService.getTotalHarga(permintaanPengiriman));
        model.addAttribute("isCancelled", permintaanPengiriman.isCancelled());
        return "view-permintaan";
    }
    //Tambah permintaan pengiriman
    @GetMapping("/permintaan-pengiriman/tambah")
    public String formTambahPermintaanPengiriman(Model model){
        var permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();
        List<Barang> listBarang = barangService.getAllBarang();
        
        model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("listBarang", listBarang);
        
        return "buat-permintaan-pengiriman";
    }
    //Add row barang permintaan pengiriman
    @PostMapping(value= "/permintaan-pengiriman/tambah",  params={"addRow"})
    public String addRowBarangPermintaanPengiriman(@ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO , Model model){

        if (permintaanPengirimanDTO.getListPermintaanPengirimanBarang() == null || permintaanPengirimanDTO.getListPermintaanPengirimanBarang().size()==0) {
            permintaanPengirimanDTO.setListPermintaanPengirimanBarang(new ArrayList<>());
        }
        permintaanPengirimanDTO.getListPermintaanPengirimanBarang().add(new PermintaanPengirimanBarang());
        
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());
        model.addAttribute("listBarang", barangService.getAllBarang());

        return "buat-permintaan-pengiriman";
    }
    //Sukses tambah permintaan pengiriman
    @PostMapping("/permintaan-pengiriman/tambah")
    public String tambahPermintaanPengiriman(@ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(error -> {
                        if (error instanceof FieldError) {
                            FieldError fieldError = (FieldError) error;
                            return fieldError.getField() + ": " + error.getDefaultMessage();
                        }
                        return error.getDefaultMessage();
                    })
                    .collect(Collectors.toList());

            model.addAttribute("errors", errors);
            return "error-viewall";
        }
        var permintaanPengirimanFromDTO = permintaanPengirimanMapper.createPermintaanPengirimanRequestDTOToPermintaanPengiriman(permintaanPengirimanDTO);
        
        // var listBarangDipesan = permintaanPengirimanDTO.getListPermintaanPengirimanBarang();
        // for (PermintaanPengirimanBarang permintaanPengirimanBarang : listBarangDipesan) {
        //     var barang = permintaanPengirimanBarang.getBarang();
        //     System.out.println("AAAAAAAAALAH");
        //     System.out.println(barangService.getTotalStok(barang));
        //     System.out.println(permintaanPengirimanBarang.getKuantitasPengiriman());
        //     if (barangService.getTotalStok(barang) < permintaanPengirimanBarang.getKuantitasPengiriman()) {
        //         return "stok-tidak-cukup";
        //     }
        // }
        permintaanPengirimanService.createPermintaanPengiriman(permintaanPengirimanFromDTO);
        
        model.addAttribute("permintaanPengirimanFromDTO", permintaanPengirimanDTO);
        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("nomorPermintaan", permintaanPengirimanFromDTO.getNomorPengiriman());
        return "success-tambah-permintaan";
    }
    //Cancel permintaan pengiriman
    @GetMapping("permintaan-pengiriman/{idPermintaanPengiriman}/cancel")
    public String cancelPermintaan(@PathVariable("idPermintaanPengiriman") long idPermintaanPengiriman, Model model){
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);
        if (permintaanPengirimanService.validateCancel(permintaanPengiriman) == 1) {
            model.addAttribute("nomorPermintaan", permintaanPengiriman.getNomorPengiriman());
            return "gagal-cancel";
        }
        model.addAttribute("nomorPermintaan", permintaanPengiriman.getNomorPengiriman());
        permintaanPengirimanService.deletePermintaanPengiriman(permintaanPengiriman);
        return "success-cancel-permintaan";
    }   
    

}
