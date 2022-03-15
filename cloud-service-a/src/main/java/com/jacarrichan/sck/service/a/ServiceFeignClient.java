package com.jacarrichan.sck.service.a;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Call a service via netflix feign.
 *
 * @author jacarrichan 2017/06/14
 */
@FeignClient(name = "service-b", path = "/b", fallback = ServiceFeignClient.Fallback.class)
public interface ServiceFeignClient {

    @GetMapping("/unstable")
    String unstable(@RequestParam("name") String name);

    @Component
    class Fallback implements ServiceFeignClient {
        @Override
        public String unstable(String name) {
            return String.format("[FALLBACK] '%s' called 'service-b' /unstable\n", name);
        }
    }
}
