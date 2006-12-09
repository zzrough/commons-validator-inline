/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.validator.routines.checkdigit;

import java.io.Serializable;

/**
 * Modulus 10 <b>EAN-13</b>/<b>UPC</b>/<b>ISBN-13</b> Check Digit
 * calculation/validation.
 * <p>
 * The calculation based on the following criteria:
 *
 * <ul>
 *   <li>Modulus 10.</li>
 *   <li>Odd digits weighted by one (right to left)</li>
 *   <li>Even digits weighted by three (right to left)</li>
 * </ul>
 * <p>
 * For further information see:
 * <ul>
 *   <li>EAN-13 - see 
 *       <a href="http://en.wikipedia.org/wiki/European_Article_Number">Wikipedia - 
 *       European Article Number</a>.</li>
 *   <li>UPC - see
 *       <a href="http://en.wikipedia.org/wiki/Universal_Product_Code">Wikipedia -
 *       Universal Product Code</a>.</li>
 *   <li>ISBN-13 - see 
 *       <a href="http://en.wikipedia.org/wiki/ISBN">Wikipedia - International
 *       Standard Book Number (ISBN)</a>.</li>
 * </ul>
 *
 * @version $Revision$ $Date$
 * @since Validator 1.4
 */
public final class EAN13CheckDigit extends ModulusCheckDigit implements Serializable {

    /** Static EAN-13 Check Digit instance */
    public static final CheckDigit INSTANCE = new EAN13CheckDigit();

    /** weighting given to the 'odd' digits in EAN-13 check digit calculation */
    private static final int ODD_WEIGHT  = 1;

    /** weighting given to the 'even' digits in EAN-13 check digit calculation */
    private static final int EVEN_WEIGHT = 3;

    /**
     * Construct a modulus 10 Check Digit routine for EAN/UPC.
     */
    public EAN13CheckDigit() {
        super(10);
    }

    /**
     * <p>Calculates the <i>weighted</i> value of a character in the
     * code at a specified position.</p>
     *
     * <p>For EAN-13 (from right to left) <b>odd</b> digits are weighted
     * with a factor of <b>one</b> and <b>even</b> digits with a factor
     * of <b>three</b>.</p>
     *
     * @param charValue The numeric value of the character.
     * @param position The position of a character in the code.
     * @return The weighted value of the character.
     */
    protected int weightedValue(int charValue, int position) {
        boolean oddPosition = (position % 2 == 1);
        int weight = (oddPosition  ? ODD_WEIGHT : EVEN_WEIGHT);
        return (charValue * weight);
    }
}