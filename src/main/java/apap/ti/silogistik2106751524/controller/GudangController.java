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
import org.springframework.web.bind.annotation.RequestParam;

import apap.ti.silogistik2106751524.dto.GudangMapper;
import apap.ti.silogistik2106751524.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106751524.model.Barang;
import apap.ti.silogistik2106751524.model.Gudang;
import apap.ti.silogistik2106751524.model.GudangBarang;
import apap.ti.silogistik2106751524.service.BarangService;
import apap.ti.silogistik2106751524.service.GudangBarangService;
import apap.ti.silogistik2106751524.service.GudangService;
import apap.ti.silogistik2106751524.service.KaryawanService;
import apap.ti.silogistik2106751524.service.PermintaanPengirimanService;
import jakarta.validation.Valid;

@Controller
public class GudangController {

    @Autowired
    GudangService gudangService;

    @Autowired
    GudangMapper gudangMapper;

    @Autowired
    GudangBarangService gudangBarangService;

    @Autowired
    BarangService barangService;

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    KaryawanService karyawanService;
    
    //Home
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("page", "Beranda");
        model.addAttribute("listGudang", gudangService.getAllGudang());
        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("listPermintaanPengiriman", permintaanPengirimanService.getAllPermintaanPengiriman());
        model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());
        return "home";
    }
    //Daftar gudang
    @GetMapping("/gudang")
    public String viewAllGudang(Model model){
        List<Gudang> listGudang = gudangService.getAllGudang();

        model.addAttribute("listGudang", listGudang);
        model.addAttribute("page", "Gudang");
        return "viewall-gudang";
    }
    //Detail gudang
    @GetMapping("/gudang/{idGudang}")
    public String viewGudang(@PathVariable("idGudang") long idGudang, Model model){
        var gudang = gudangService.getGudangById(idGudang);
        var listGudangBarang = gudangBarangService.getAllGudangBarangByGudang(idGudang);

        model.addAttribute("gudang", gudang);
        model.addAttribute("listGudangBarang", listGudangBarang);
        return "view-gudang";
    }
    //Restock gudang
    @GetMapping("/gudang/{idGudang}/restock-barang")
    public String formRestockGudang(@PathVariable("idGudang") long idGudang, Model model){
        var gudang = gudangService.getGudangById(idGudang);
        var gudangDTO = gudangMapper.gudangToReadGudangDTO(gudang);
        List<Barang> listBarang = barangService.getAllBarang();
        
        model.addAttribute("gudangDTO", gudangDTO);
        model.addAttribute("gudang", gudang);
        model.addAttribute("listBarang", listBarang);

        return "restock-gudang";
    }
    //Add row barang
    @PostMapping(value="/gudang/{idGudang}/restock-barang", params={"addRow"})
    public String addRowBarang(@PathVariable("idGudang") long idGudang, @ModelAttribute UpdateGudangRequestDTO gudangDTO, Model model){

        if (gudangDTO.getListGudangBarang() == null || gudangDTO.getListGudangBarang().size()==0) {
            gudangDTO.setListGudangBarang(new ArrayList<>());
        }
        gudangDTO.getListGudangBarang().add(new GudangBarang());
        
        model.addAttribute("gudangDTO", gudangDTO);
        model.addAttribute("listBarang", barangService.getAllBarang());

        return "restock-gudang";
    }
    //Sukses restock gudang
    @PostMapping(value = "/gudang/{idGudang}/restock-barang")
    public String restockGudang(@Valid @PathVariable("idGudang") long idGudang, @ModelAttribute UpdateGudangRequestDTO gudangDTO, BindingResult bindingResult, Model model){
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
        var gudangFromDTO = gudangMapper.updateGudangRequestDTOToGudang(gudangDTO);
        gudangService.restockGudang(idGudang, gudangFromDTO);
        
        model.addAttribute("gudangFromDTO", gudangFromDTO);
        model.addAttribute("listBarang", barangService.getAllBarang());

        return "success-restock-gudang";
    }
    //Cari barang
    @GetMapping("gudang/cari-barang")
    public String formCariBarang(Model model){
        var listBarang = barangService.getAllBarang();
        var barangSorted = barangService.sortListBarang(listBarang);
        
        model.addAttribute("listBarang", barangSorted);
        return "cari-barang";
    }
    //Sukses cari barang
    @GetMapping(value = "gudang/cari-barang", params={"sku"})
    public String cariBarang(@RequestParam(value = "sku", required = false) String sku, Model model){
        var barang = barangService.getBarangById(sku);
        var listGudangBarang = barang.getListGudangBarang();
        var listGudang = barangService.getListGudangSebuahBarang(listGudangBarang, sku);

        var listBarang = barangService.getAllBarang();
        var findBarang = barangService.sortListBarang(listBarang);

        String selectedSku = sku;
        model.addAttribute("selectedSku", selectedSku);
        model.addAttribute("listBarang", findBarang);
        model.addAttribute("listGudang", listGudang);
        model.addAttribute("listGudangBarang", listGudangBarang);
        return "cari-barang";
    }
}
