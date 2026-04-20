package com.n11odev.odev1;

import com.n11odev.odev1.interfaces.OdemeYapabilir;

public class NewApplePayOdeme implements OdemeYapabilir{

    @Override
    public void odemeYap(double miktar){

        System.out.println("Apple Pay ile odeme yapildi, miktar: " + miktar);

    }

}
