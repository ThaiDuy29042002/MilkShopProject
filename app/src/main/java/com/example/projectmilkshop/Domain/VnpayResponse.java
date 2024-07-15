package com.example.projectmilkshop.Domain;

public class VnpayResponse {
    private String vnpay_link;

    public VnpayResponse(String vnpay_link) {
        this.vnpay_link = vnpay_link;
    }

    public String getVnpay_link() {
        return vnpay_link;
    }

    public void setVnpay_link(String vnpay_link) {
        this.vnpay_link = vnpay_link;
    }
}
