/**
 *
 *
 * @author Jose Rose
 * 2011-08-05
 */
package com.cristal.storm.prototype.client.util;

import java.util.Random;

/**
 * Contains only static functions to use accross the client.
 *
 */
public final class UtilFunc {
    
    private static Random randomGenerator = new Random(0 /*IMPORTANT TO USE THE SAME SEED TO BE REPRODUCTIBLE*/);

    public static int generateUID() {
        
        // Randomly generate an integer
        int randResult = randomGenerator.nextInt();
        
        return randResult;
    }

}
