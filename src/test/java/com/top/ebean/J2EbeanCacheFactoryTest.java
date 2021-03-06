package com.top.ebean;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.cache.ServerCache;
import com.avaje.ebean.cache.ServerCacheManager;
import com.top.ebean.domain.EFoo;
import com.top.ebean.j2cache.J2EbeanCache;
import org.junit.Test;

import java.util.List;

/*
 * 执行需加agent库：java -javaagent:yourPath\avaje-ebeanorm-agent-8.1.1.jar
 */
public class J2EbeanCacheFactoryTest {

    public static void main(String[] args) throws InterruptedException {
        J2EbeanCacheFactoryTest test = new J2EbeanCacheFactoryTest();
        test.integration();
        test.putGet();
        test.queryCache();
    }

    @Test
    public void integration() {
        ServerCacheManager cacheManager = Ebean.getServerCacheManager();
        ServerCache beanCache = cacheManager.getBeanCache(EFoo.class);

        System.out.print("beanCache instanceof J2EbeanCache: ");
        System.out.println(beanCache instanceof J2EbeanCache);

        EFoo fetch1 = Ebean.find(EFoo.class, 1);

        System.out.println("f: " + fetch1);
    }


    @Test
    public void putGet() throws InterruptedException {

        EFoo foo = new EFoo("hello");
        Ebean.save(foo);

        EFoo fetch1 = Ebean.find(EFoo.class)
                .setId(foo.getId())
                .select("name, status, notes, version")
                .findUnique();

        EFoo fetch2 = Ebean.find(EFoo.class)
                .setId(foo.getId())
                .select("name, status, notes, version")
                .findUnique();

        System.out.println("fetch1: " + fetch1);
        System.out.println("fetch2: " + fetch2);

        EFoo update = new EFoo();
        update.setId(foo.getId());
        update.setNotes("ModNotes");
        Ebean.update(update);

        Thread.sleep(20);

        EFoo fetch3 = Ebean.find(EFoo.class)
                .setId(foo.getId())
                .select("name, status, notes, version")
                .findUnique();

        System.out.println("fetch3: " + fetch3);
        System.out.println(fetch3.getNotes() + " ModNotes");
        System.out.println(fetch3.getName() + " hello");
    }

    @Test
    public void queryCache() {

        new EFoo("one1").save();
        new EFoo("one2").save();
        new EFoo("one3").save();

        Ebean.find(EFoo.class)
                .setUseQueryCache(true)
                .where().startsWith("name", "one")
                .findList();

        Ebean.find(EFoo.class)
                .setUseQueryCache(true)
                .where().startsWith("name", "one")
                .findList();

        new EFoo("one4").save();

        Ebean.find(EFoo.class)
                .setUseQueryCache(true)
                .where().startsWith("name", "one")
                .findList();

        Ebean.find(EFoo.class)
                .setUseQueryCache(true)
                .where().startsWith("name", "one")
                .findList();

        List<EFoo> list = Ebean.find(EFoo.class).findList();
        System.out.println(list);
    }
}