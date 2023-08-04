package com.example.mehmetakifersoy211130012;

import java.io.Serializable;
import java.time.LocalDate;

public class Seyahat implements Serializable {
    private String adSoyad ,kalkis,varis,otobusTuru;
    private Integer biletNo,rezervasyonAdet;
    private LocalDate seyahatTarih;

    public Seyahat(String adSoyad, String kalkis, String varis, String otobusTuru, Integer biletNo, Integer rezervasyonAdet, LocalDate seyahatTarih) {
        super();
        this.adSoyad = adSoyad;
        this.kalkis = kalkis;
        this.varis = varis;
        this.otobusTuru = otobusTuru;
        this.biletNo = biletNo;
        this.rezervasyonAdet = rezervasyonAdet;
        this.seyahatTarih = seyahatTarih;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getKalkis() {
        return kalkis;
    }

    public void setKalkis(String kalkis) {
        this.kalkis = kalkis;
    }

    public String getVaris() {
        return varis;
    }

    public void setVaris(String varis) {
        this.varis = varis;
    }

    public String getOtobusTuru() {
        return otobusTuru;
    }

    public void setOtobusTuru(String otobusTuru) {
        this.otobusTuru = otobusTuru;
    }

    public Integer getBiletNo() {
        return biletNo;
    }

    public void setBiletNo(Integer biletNo) {
        this.biletNo = biletNo;
    }

    public Integer getRezervasyonAdet() {
        return rezervasyonAdet;
    }

    public void setRezervasyonAdet(Integer rezervasyonAdet) {
        this.rezervasyonAdet = rezervasyonAdet;
    }

    public LocalDate getSeyahatTarih() {
        return seyahatTarih;
    }

    public void setSeyahatTarih(LocalDate seyahatTarih) {
        this.seyahatTarih = seyahatTarih;
    }
}
