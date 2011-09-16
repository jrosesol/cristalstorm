/**
 *
 *
 * @author Jose Rose
 * 2011-08-05
 */
package com.cristal.storm.prototype.client.util;

import java.util.Random;

/**
 * Contains only static functions to use across the client.
 *
 */
public final class UtilFunc {
    
    /**
     * On the client side, we can define static values bellow BIG_INT_OFFSET and be safe
     * random wise.
     * 
     */
    private static long BIG_INT_OFFSET = 100000;
    
    private static Random randomGenerator = new Random(0 /*IMPORTANT TO USE THE SAME SEED TO BE REPRODUCTIBLE*/);

    public static long generateUID() {
        
        // Randomly generate an integer
        long randResult = BIG_INT_OFFSET + randomGenerator.nextLong();
        
        return randResult;
    }

}
