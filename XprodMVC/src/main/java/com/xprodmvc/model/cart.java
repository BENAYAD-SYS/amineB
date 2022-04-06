package com.xprodmvc.model;

public class cart extends products{

    private int QUANTITY;


    public cart() {
        super();
    }


    public cart(int iDPROD, String nOM, String cATEGORIE, Double pRIX, String iMAGE, int qUANTITY) {
        super(iDPROD, nOM, cATEGORIE, pRIX, iMAGE);
        QUANTITY = qUANTITY;
    }


    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int qUANTITY) {
        QUANTITY = qUANTITY;
    }


}