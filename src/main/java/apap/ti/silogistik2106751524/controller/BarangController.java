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
import apap.ti.silogistik2106751524.dto.BarangMapper;
import apap.ti.silogistik2106751524.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751524.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751524.model.Barang;
import apap.ti.silogistik2106751524.model.Gudang;
import apap.ti.silogistik2106751524.service.BarangService;
import apap.ti.silogistik2106751524.service.GudangBarangService;
import jakarta.validation.Valid;

@Controller
public class BarangController {

    @Autowired
    BarangService barangService;

    @Autowired
    BarangMapper barangMapper;

    @Autowired
    GudangBarangService gudangBarangService;

    //Daftar barang
    @GetMapping("/barang")
    public String viewAllBarang(Model model){
        List<Barang> listBarang = barangService.getAllBarang();
        List<Integer> listStokBarang = barangService.listStokBarang(listBarang);
        
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listStokBarang", listStokBarang);
        model.addAttribute("page", "Barang");
        return "viewall-barang";
    }
    //Detail barang
    @GetMapping("barang/{idBarang}")
    public String viewBarang(@PathVariable("idBarang") String idBarang, Model model){
        var barang = barangService.getBarangById(idBarang);
        var listGudangBarang = barangService.getAllGudangBarangByBarang(barang);

        List<Gudang> listGudang = barangService.getListGudangSebuahBarang(listGudangBarang, idBarang);

        model.addAttribute("barang", barang);
        model.addAttribute("totalStok", barangService.getTotalStok(barang));
        model.addAttribute("listGudangBarang", listGudangBarang);
        model.addAttribute("listGudang", listGudang);

        return "view-barang";
    }
    //Tambah barang
    @GetMapping("barang/tambah")
    public String formAddBarang(Model model){
        var barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);
        
        return "tambah-barang";
    }
    //Sukses tambah barang
    @PostMapping("barang/tambah")
    public String tambahBarang(@Valid @ModelAttribute CreateBarangRequestDTO barangDTO, BindingResult bindingResult, Model model){
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
        
        var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);
        barangService.createBarang(barang);

        model.addAttribute("sku", barang.getSku());
        model.addAttribute("merkBarang", barang.getMerkBarang());
        model.addAttribute("tipeBarang", barang.getTipeBarang());
        model.addAttribute("hargaBarang", barang.getHargaBarang());
        return "success-tambah-barang";
    }
    //Ubah barang
    @GetMapping("barang/{idBarang}/ubah")
    public String formUbahBarang(@PathVariable("idBarang") String sku, Model model){
        var barang = barangService.getBarangById(sku);
        var barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);

        model.addAttribute("barangDTO", barangDTO);
        model.addAttribute("idBarang", barang.getSku());
        return "ubah-barang";  
    }
    //Sukses ubah barang
    @PostMapping("barang/{idBarang}/ubah")
    public String updateBarang(@Valid @ModelAttribute UpdateBarangRequestDTO barangDTO, BindingResult result, Model model){
        if (result.hasErrors()) {
            List<String> errorMessage = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMessage.add(error.getDefaultMessage());
            }
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }
        var barangFromDTO = barangMapper.updateBarangRequestDTOToBarang(barangDTO);
        var barang = barangService.ubahBarang(barangFromDTO);

        model.addAttribute("sku", barang.getSku());
        return "success-ubah-barang";
    }
}
