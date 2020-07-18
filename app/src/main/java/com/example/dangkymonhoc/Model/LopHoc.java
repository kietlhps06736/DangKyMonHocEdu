package com.example.dangkymonhoc.Model;

public class LopHoc {
    String idSV;
    int idLopHoc;
    String LopHoc;
    String TenMonHoc;
    String GiangVien;
    String CaHoc;
    public  LopHoc(){

    }

    public LopHoc(String idSV, int idLopHoc, String lopHoc, String tenMonHoc, String giangVien, String caHoc) {
        this.idSV = idSV;
        this.idLopHoc = idLopHoc;
        LopHoc = lopHoc;
        TenMonHoc = tenMonHoc;
        GiangVien = giangVien;
        CaHoc = caHoc;
    }

    public String getIdSV() {
        return idSV;
    }

    public void setIdSV(String idSV) {
        this.idSV = idSV;
    }

    public int getIdLopHoc() {
        return idLopHoc;
    }

    public void setIdLopHoc(int idLopHoc) {
        this.idLopHoc = idLopHoc;
    }

    public String getLopHoc() {
        return LopHoc;
    }

    public void setLopHoc(String lopHoc) {
        LopHoc = lopHoc;
    }

    public String getTenMonHoc() {
        return TenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        TenMonHoc = tenMonHoc;
    }

    public String getGiangVien() {
        return GiangVien;
    }

    public void setGiangVien(String giangVien) {
        GiangVien = giangVien;
    }

    public String getCaHoc() {
        return CaHoc;
    }

    public void setCaHoc(String caHoc) {
        CaHoc = caHoc;
    }
}
