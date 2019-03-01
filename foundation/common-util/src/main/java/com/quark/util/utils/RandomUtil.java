package com.quark.util.utils;


import java.util.Random;
import java.util.Set;

/**
 * 生成指定区间的随机数
 */
public class RandomUtil {
    /**
     * @param min
     * @param max
     * @return Random number
     */

    public static int getRandomIntInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    }

    /**
     * 生成排除{@code exclude} 在内的随机数
     *
     * @param min
     * @param max
     * @param exclude
     * @return Random number
     */
    public static int getRandomIntInRangeWithExclude(int min, int max, Set<Integer> exclude) {
        Random r = new Random();
        if (min == max) {
            throw new IllegalArgumentException("min and max can not equal");
        }
        return r.ints(min, (max + 1)).filter((i) -> !exclude.contains(i)).limit(1).findFirst().getAsInt();
    }

    /**
     * @param min
     * @param max
     * @return Random number string
     */
    public static String getRandomStringInRange(int min, int max) {
        Random r = new Random();
        return String.valueOf(r.ints(min, (max + 1)).limit(1).findFirst().getAsInt());
    }
}