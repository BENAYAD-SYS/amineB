package com.xprodmvc.model;

import java.util.Date;

public class order extends products {
    private int ID_ORDER;
    private int ID_USER;
    private int QUANTITY;
    private String DATE;

    public order() {
        super();
    }
    public order(int iDPROD, String nOM, String cATEGORIE, Double pRIX, String iMAGE, int iD_ORDER, int iD_USER,
            int qUANTITY, String dATE) {
        super(iDPROD, nOM, cATEGORIE, pRIX, iMAGE);
        ID_ORDER = iD_ORDER;
        ID_USER = iD_USER;
        QUANTITY = qUANTITY;
        DATE = dATE;
    }

    public order( int iD_USER, int qUANTITY,
            String dATE) {
        super();
        this.ID_USER = iD_USER;
        QUANTITY = qUANTITY;
        DATE = dATE;
    }
    public int getID_ORDER() {
        return ID_ORDER;
    }
    public void setID_ORDER(int iD_ORDER) {
        ID_ORDER = iD_ORDER;
    }
    public int getID_USER() {
        return ID_USER;
    }
    public void setID_USER(int iD_USER) {
        ID_USER = iD_USER;
    }
    public int getQUANTITY() {
        return QUANTITY;
    }
    public void setQUANTITY(int qUANTITY) {
        QUANTITY = qUANTITY;
    }
    public String getDATE() {
        return DATE;
    }
    public void setDATE(String dATE) {
        DATE = dATE;
    }
 
    public String toString() {
        return "Order [ID_ORDER=" + ID_ORDER + ", ID_USER=" + ID_USER + ", QUANTITY=" + QUANTITY + ", DATE=" + DATE
                + "]";
    }

}