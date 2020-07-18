package com.example.dangkymonhoc.Model;

public class SinhVien {
    int idSv;
    String maSv;
    String tenSv;
    String gioiTinh;
    String phone;
    String email;
    String diaChi;
    String nganh;
    String hocKy;

    public SinhVien() {

    }

    public SinhVien(int idSv, String maSv, String tenSv, String gioiTinh, String phone, String email, String diaChi, String nganh, String hocKy) {
        this.idSv = idSv;
        this.maSv = maSv;
        this.tenSv = tenSv;
        this.gioiTinh = gioiTinh;
        this.phone = phone;
        this.email = email;
        this.diaChi = diaChi;
        this.nganh = nganh;
        this.hocKy = hocKy;
    }

    public int getIdSv() {
        return idSv;
    }

    public void setIdSv(int idSv) {
        this.idSv = idSv;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getTenSv() {
        return tenSv;
    }

    public void setTenSv(String tenSv) {
        this.tenSv = tenSv;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public String getHocKy() {
        return hocKy;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }
}
