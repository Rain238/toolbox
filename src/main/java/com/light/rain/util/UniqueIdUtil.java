package com.light.rain.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

import java.security.SecureRandom;

/**
 * @Author: LightRain
 * @Description: 唯一ID标识工具
 * @DateTime: 22/2/2023 下午 1:56
 * @Version：1.0
 **/
public class UniqueIdUtil {
    private static final Snowflake SNOW_FLAKE = new Snowflake(1, 2);
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 1:32
     * @Param: []
     * @Return: java.lang.String
     * @Description: 获取简单uuid唯一标识
     */
    public static String getUUID() {
        return IdUtil.simpleUUID();
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 1:45
     * @Param: []
     * @Return: java.lang.String
     * @Description: 获取快速uuid唯一标识
     */
    public static String getFastUUID() {
        return IdUtil.fastUUID();
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 1:45
     * @Param: []
     * @Return: java.lang.String
     * @Description: 获取快速简单uuid唯一标识
     */
    public static String getFastSimpleUUID() {
        return IdUtil.fastSimpleUUID();
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 1:47
     * @Param: []
     * @Return: java.lang.String
     * @Description: 获取随机uuid唯一标识
     */
    public static String getRandomUUID() {
        return IdUtil.randomUUID();
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 1:33
     * @Param: []
     * @Return: java.lang.Long
     * @Description: 雪花id唯一标识
     */
    public static Long getSnowflakesId() {
        Long uid = null;
        for (int i = 0; i < RANDOM.nextInt(2) + 1; i++) {
            uid = SNOW_FLAKE.nextId();
        }
        return uid;
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 1:36
     * @Param: []
     * @Return: void
     * @Description: ObjectId是MongoDB数据库的一种唯一ID生成策略</ br>
     * 它是UUID version1的变种
     */
    public static String getObjectId() {
        return IdUtil.objectId();
    }
}
