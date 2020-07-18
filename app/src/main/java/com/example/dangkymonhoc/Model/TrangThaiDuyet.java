package com.example.dangkymonhoc.Model;

public class TrangThaiDuyet {
    private String maMonHoc;
    private String tenMonHoc;
    private String tenLopHoc;
    private String idTrangThai;
    private String trangThaiDuyet;

    public TrangThaiDuyet() {

    }

    public TrangThaiDuyet(String maMonHoc, String tenMonHoc, String tenLopHoc, String idTrangThai, String trangThaiDuyet) {
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
        this.tenLopHoc = tenLopHoc;
        this.idTrangThai = idTrangThai;
        this.trangThaiDuyet = trangThaiDuyet;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getTenLopHoc() {
        return tenLopHoc;
    }

    public void setTenLopHoc(String tenLopHoc) {
        this.tenLopHoc = tenLopHoc;
    }

    public String getIdTrangThai() {
        return idTrangThai;
    }

    public void setIdTrangThai(String idTrangThai) {
        this.idTrangThai = idTrangThai;
    }

    public String getTrangThaiDuyet() {
        return trangThaiDuyet;
    }

    public void setTrangThaiDuyet(String trangThaiDuyet) {
        this.trangThaiDuyet = trangThaiDuyet;
    }
}
