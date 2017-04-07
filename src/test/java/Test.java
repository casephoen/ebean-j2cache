import net.oschina.j2cache.CacheObject;
import net.oschina.j2cache.J2Cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lizhijun on 2017/4/5.
 */
public class Test {

    public static void main(String[] args) {

        Map<String, Object> objectMap = new HashMap<String, Object>() {{
            put("username", "zhangyw");
            put("email", "alemcity@foxmail.com");
            put("git_link", "http://git.oschina.net/git-zyw");
        }};

        J2Cache.getChannel().set("user_cache", "user", objectMap);
        J2Cache.getChannel().set("user_cache2", "user2", objectMap);
        J2Cache.getChannel().set("user_cache2", "user21", objectMap);

        J2Cache.getChannel().set(111, objectMap);
        J2Cache.getChannel().set(222, objectMap);
        J2Cache.getChannel().set(333, objectMap);

        CacheObject object = J2Cache.getChannel().get("user_cache", "user");
        Map map = (Map) object.getValue();
        System.out.println(map.get("git_link"));

        J2Cache.getChannel().close();
    }
}
