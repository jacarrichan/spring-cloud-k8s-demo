package com.jacarrichan.sck.service.b;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Rest controller.
 *
 * @author jacarrichan 2017/06/06
 */
@RestController
@RequestMapping("/b")
public class ServiceController {

    @Value("#{T(java.net.InetAddress).getLocalHost().getHostName()}")
    private String host;

    @Value("#{T(java.net.InetAddress).getLocalHost().getHostAddress()}")
    private String ip;

    @Value("${server.port}")
    private int port;

    @Value("${spring.application.name}")
    private String myName;

    @Value("${service-b.unstable.sleepMilliseconds}")
    private long sleepMilliseconds;

    @Resource
    private ServiceFeignClient feign;

    @Resource
    private HttpServletRequest servletRequest;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return String.format("'%s' called '%s' /hello at [%s %s:%d]\n --> %s",
                name, myName, host, ip, port, feign.unstable(myName));
    }

    @GetMapping("/unstable")
    public String unstable(@RequestParam String name) throws Exception {
        if (Math.random() > 0.2) {
            //randomly sleep for triggering hystrix fallback
            TimeUnit.MILLISECONDS.sleep(sleepMilliseconds);
        }
        return String.format("'%s' called '%s' /unstable at [%s %s:%d]\n",
                name, myName, host, ip, port);
    }


    @GetMapping("/sysinfo")
    public Map<String, Object> sysinfo() {
        Map<String, Object> maps = new HashMap<>(8);
        maps.put("system", System.getenv());
        maps.put("httpRequestHeader", getHeadersInfo(servletRequest));
        return maps;
    }

    //get request headers
    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
