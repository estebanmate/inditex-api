package com.inditex.challenge.e2e.price;

import com.intuit.karate.junit5.Karate;

class PriceRunner {
    
    @Karate.Test
    Karate testPrice() {
        return Karate.run("price").relativeTo(getClass());
    }    

}
