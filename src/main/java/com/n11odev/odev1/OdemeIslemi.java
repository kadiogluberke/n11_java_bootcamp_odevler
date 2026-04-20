package com.n11odev.odev1;

import com.n11odev.odev1.interfaces.OdemeYapabilir;

public class OdemeIslemi {

    public OdemeYapabilir yontem;
    // public double miktar;

    public OdemeIslemi(OdemeYapabilir yontem){
        this.yontem = yontem;
        // this.miktar = miktar;
    }

    public void odemeYap(double miktar) {
       this.yontem.odemeYap(miktar);
    }

}
