package com.jacarrichan.sck.service.a;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Rest controller.
 *
 * @author jacarrichan 2017/06/06
 */
@Slf4j
@RestController
@RequestMapping("/a")
public class ServiceController {

    @Value("#{T(java.net.InetAddress).getLocalHost().getHostName()}")
    private String host;

    @Value("#{T(java.net.InetAddress).getLocalHost().getHostAddress()}")
    private String ip;

    @Value("${server.port}")
    private int port;

    @Value("${spring.application.name}")
    private String myName;

    @Value("${service-a.unstable.sleepMilliseconds}")
    private long sleepMilliseconds;

    @Resource
    private HttpServletRequest servletRequest;

    @Autowired
    private ServiceFeignClient feign;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return String.format("'%s' called '%s' /hello at [%s %s:%d]\n --> %s",
                name, myName, host, ip, port, feign.unstable(myName));
    }

    @GetMapping("/unstable")
    public String unstable(@RequestParam String name) throws Exception {
        if (Math.random() > 0.2) {
            TimeUnit.MILLISECONDS.sleep(sleepMilliseconds); //randomly sleep for triggering hystrix fallback
        }
        return String.format("'%s' called '%s' /unstable at [%s %s:%d]\n",
                name, myName, host, ip, port);
    }

    @GetMapping("/sysinfo")
    public Map<String, Object> sysinfo() {
        log.info("{}", servletRequest);
        return feign.sysinfo();
    }
}
