package com.n11odev.odev1;

import com.n11odev.odev1.interfaces.OdemeYapabilir;

public class PayPalOdeme implements OdemeYapabilir{

    @Override
    public void odemeYap(double miktar) {

        System.out.println("PayPal ile odeme yapildi, miktar: " + miktar);
    }

}
