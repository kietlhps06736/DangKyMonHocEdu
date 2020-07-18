package com.example.dangkymonhoc.Model;

public class NganhHoc  {
    String idSV;
    int id;
    String tenNganh;

    public NganhHoc() {

    }

    public NganhHoc(String idSV, int id, String tenNganh) {
        this.idSV = idSV;
        this.id = id;
        this.tenNganh = tenNganh;
    }

    public String getIdSV() {
        return idSV;
    }

    public void setIdSV(String idSV) {
        this.idSV = idSV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }
}

