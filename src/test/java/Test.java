import net.oschina.j2cache.CacheObject;
import net.oschina.j2cache.J2Cache;

import java.io.Serializable;
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

        Bean bean = new Bean();
        bean.id = 1L;
        bean.typeEnum = TypeEnum.NORMAL;
        bean.name = "bName";
        bean.age = 18;
        bean.objectMap = objectMap;
        J2Cache.getChannel().set("ebean-j2cache", 55555, bean);
        CacheObject cacheObject = J2Cache.getChannel().get("ebean-j2cache", 55555);
        Object value = cacheObject.getValue();

        CacheObject object = J2Cache.getChannel().get("user_cache", "user");
        Map map = (Map) object.getValue();
        System.out.println(map.get("git_link"));

        J2Cache.getChannel().close();
    }

    static class Bean implements Serializable {
        long id;
        TypeEnum typeEnum;
        String name;
        int age;
        Map<String, Object> objectMap;
    }

    enum TypeEnum {
        NORMAL(1),
        HIGHER(2);

        public int getValue() {
            return value;
        }

        private final int value;

        TypeEnum(int value) {
            this.value = value;
        }


    }
}
