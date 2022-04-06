package com.xprodmvc.model;

public class products {
    private int IDPROD;
    private String NOM;
    private String CATEGORIE;
    private Double PRIX;
    private String IMAGE;

    public products(int iDPROD, String nOM, String cATEGORIE, Double pRIX, String iMAGE) {
        IDPROD = iDPROD;
        NOM = nOM;
        CATEGORIE = cATEGORIE;
        PRIX = pRIX;
        IMAGE = iMAGE;
    }

    public int getIDPROD() {
        return IDPROD;
    }

    public void setIDPROD(int iDPROD) {
        IDPROD = iDPROD;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String nOM) {
        NOM = nOM;
    }

    public String getCATEGORIE() {
        return CATEGORIE;
    }

    public void setCATEGORIE(String cATEGORIE) {
        CATEGORIE = cATEGORIE;
    }

    public Double getPRIX() {
        return PRIX;
    }

    public void setPRIX(Double pRIX) {
        PRIX = pRIX;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String iMAGE) {
        IMAGE = iMAGE;
    }

    public products() {
        super();
    }

} 