/*
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.kernel.util;

import org.springframework.stereotype.Component;

/**
 * Provides general hashing algorithms we might need
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Component("hash")
public class Hash {

    /**
     * <p>rSHash.</p>
     *
     * @param str a {@link java.lang.String} object.
     * @return a long.
     */
    public long rSHash(String str) {
        int b = 378551;
        int a = 63689;
        long hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = hash * a + str.charAt(i);
            a = a * b;
        }

        return hash;
    }
   /* End Of RS Hash Function */


    /**
     * <p>jSHash.</p>
     *
     * @param str a {@link java.lang.String} object.
     * @return a long.
     */
    public long jSHash(String str) {
        long hash = 1315423911;

        for (int i = 0; i < str.length(); i++) {
            hash ^= ((hash << 5) + str.charAt(i) + (hash >> 2));
        }

        return hash;
    }
   /* End Of JS Hash Function */


    /**
     * <p>pJWHash.</p>
     *
     * @param str a {@link java.lang.String} object.
     * @return a long.
     */
    public long pJWHash(String str) {
        long BitsInUnsignedInt = (long) (4 * 8);
        long ThreeQuarters = (long) ((BitsInUnsignedInt * 3) / 4);
        long OneEighth = (long) (BitsInUnsignedInt / 8);
        long HighBits = (long) (0xFFFFFFFF) << (BitsInUnsignedInt - OneEighth);
        long hash = 0;
        long test = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = (hash << OneEighth) + str.charAt(i);

            if ((test = hash & HighBits) != 0) {
                hash = ((hash ^ (test >> ThreeQuarters)) & (~HighBits));
            }
        }

        return hash;
    }
   /* End Of  P. J. Weinberger Hash Function */


    /**
     * <p>eLFHash.</p>
     *
     * @param str a {@link java.lang.String} object.
     * @return a long.
     */
    public long eLFHash(String str) {
        long hash = 0;
        long x = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = (hash << 4) + str.charAt(i);

            if ((x = hash & 0xF0000000L) != 0) {
                hash ^= (x >> 24);
            }
            hash &= ~x;
        }

        return hash;
    }
   /* End Of ELF Hash Function */


    /**
     * <p>bKDRHash.</p>
     *
     * @param str a {@link java.lang.String} object.
     * @return a long.
     */
    public long bKDRHash(String str) {
        long seed = 131; // 31 131 1313 13131 131313 etc..
        long hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = (hash * seed) + str.charAt(i);
        }

        return hash;
    }
   /* End Of BKDR Hash Function */


    /**
     * <p>sDBMHash.</p>
     *
     * @param str a {@link java.lang.String} object.
     * @return a long.
     */
    public long sDBMHash(String str) {
        long hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
        }

        return hash;
    }
   /* End Of SDBM Hash Function */


    /**
     * <p>dJBHash.</p>
     *
     * @param str a {@link java.lang.String} object.
     * @return a long.
     */
    public long dJBHash(String str) {
        long hash = 5381;

        for (int i = 0; i < str.length(); i++) {
            hash = ((hash << 5) + hash) + str.charAt(i);
        }

        return hash;
    }
   /* End Of DJB Hash Function */


    /**
     * <p>dEKHash.</p>
     *
     * @param str a {@link java.lang.String} object.
     * @return a long.
     */
    public long dEKHash(String str) {
        long hash = str.length();

        for (int i = 0; i < str.length(); i++) {
            hash = ((hash << 5) ^ (hash >> 27)) ^ str.charAt(i);
        }

        return hash;
    }
   /* End Of DEK Hash Function */


    /**
     * <p>dPHash.</p>
     *
     * @param str a {@link java.lang.String} object.
     * @return a long.
     */
    public long dPHash(String str) {
        long hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = hash << 7 ^ str.charAt(i);
        }

        return hash;
    }
   /* End Of BP Hash Function */


    /**
     * <p>fNVHash.</p>
     *
     * @param str a {@link java.lang.String} object.
     * @return a long.
     */
    public long fNVHash(String str) {
        long fnv_prime = 0x811C9DC5;
        long hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash *= fnv_prime;
            hash ^= str.charAt(i);
        }

        return hash;
    }
   /* End Of FNV Hash Function */


    /**
     * <p>aPHash.</p>
     *
     * @param str a {@link java.lang.String} object.
     * @return a long.
     */
    public long aPHash(String str) {
        long hash = 0xAAAAAAAA;

        for (int i = 0; i < str.length(); i++) {
            if ((i & 1) == 0) {
                hash ^= ((hash << 7) ^ str.charAt(i) * (hash >> 3));
            } else {
                hash ^= (~((hash << 11) + str.charAt(i) ^ (hash >> 5)));
            }
        }

        return hash;
    }
   /* End Of AP Hash Function */
}
