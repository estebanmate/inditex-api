package com.inditex.challenge.e2e;

import com.intuit.karate.junit5.Karate;

class ChallengeTest {
    
    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }
    
}
