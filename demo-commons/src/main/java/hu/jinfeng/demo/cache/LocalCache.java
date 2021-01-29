package hu.jinfeng.demo.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 本来缓存
 *
 * @Author hujinfeng  @Date 2021/1/9
 **/
public class LocalCache {
    public static void main(String[] args) {
        putUserBusinessRole(1,-1, RoleEnum.COMPANY);
        RoleEnum role = getUserBusinessRole(1L,-1L);
        System.out.println(role == RoleEnum.VIP);
    }

    private final static Cache<String, RoleEnum> userBusinessRole =
            CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.MINUTES).maximumSize(2000).build();

    private static String getUserKey(long userId, long companyId) {
        return userId + "-" + companyId;
    }

    public static RoleEnum getUserBusinessRole(Long userId, Long companyId) {
        return userBusinessRole.getIfPresent(getUserKey(userId, companyId));
    }
    public static void putUserBusinessRole(long userId, long companyId, RoleEnum val) {
        userBusinessRole.put(getUserKey(userId, companyId), val);
    }
}
