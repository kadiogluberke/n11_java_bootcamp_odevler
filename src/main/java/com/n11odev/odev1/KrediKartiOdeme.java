package com.n11odev.odev1;

import com.n11odev.odev1.interfaces.OdemeYapabilir;

public class KrediKartiOdeme implements OdemeYapabilir{

    @Override
    public void odemeYap(double miktar){

        System.out.println("Kredi Karti ile odeme yapildi, miktar: " + miktar);

    }

}
