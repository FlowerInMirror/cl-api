package com.thinkgem.jeesite.modules.api.jmat.utils;

/**
 * 材料平台相关工具类
 * @author ljc
 * @createTime 2018-6-26 13:28:30
 */
public class MaterialUtils {

    // 档次标识 变 档子字母
    public static String levelFlagChangerLevelCharacter(int levelFlag){
        String LevelCharacter = null;
        switch (levelFlag){
            case 1: LevelCharacter = "A"; break;
            case 2: LevelCharacter = "B"; break;
            case 4: LevelCharacter = "C"; break;
        }
        return LevelCharacter;
    }

}
