package com.n11odev.odev1;

public class Main {
    public static void main(String[] args) {

        OdemeIslemi kkOdeme = new OdemeIslemi(new KrediKartiOdeme());
        OdemeIslemi payPalOdeme = new OdemeIslemi(new PayPalOdeme());
        OdemeIslemi applePalOdeme = new OdemeIslemi(new NewApplePayOdeme()); // Yeni Odeme Yontemi 

        // KrediKartiOdeme kkOdeme = new KrediKartiOdeme();
        // PayPalOdeme payPalOdeme = new PayPalOdeme();

        kkOdeme.odemeYap(80.0);
        payPalOdeme.odemeYap(40.0);
        applePalOdeme.odemeYap(30.0); // Yeni Odeme Yontemi 


    }

}
