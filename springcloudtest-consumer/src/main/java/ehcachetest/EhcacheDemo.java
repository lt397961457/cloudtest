package ehcachetest;

import com.alibaba.fastjson.JSON;
import com.yly.consumer.entity.Product;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.boot.json.GsonJsonParser;

/**
 * Created by Administrator on 2017/12/21.
 */
public class EhcacheDemo {
    public static void main(String[] args) throws Exception {
        /**
         * 方式1：单独使用Ehcache
         */
        final CacheManager cacheManager = new CacheManager();
        final Cache cache = cacheManager.getCache("helloworld");
        final String key = "greeting";
        final Element putGreeting = new Element(key, "Hello, World!");
        cache.put(putGreeting);
        final Element getGreeting = cache.get(key);
        System.out.println(getGreeting.getObjectValue());

//        Product product = new Product();
//        product.setId(1);
//        product.setName("testProduct");
//        product.setPrice(555L);
//        String json = JSON.toJSONString(product);
//        System.out.print(json);

    }
}
