package cn.bucheng;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@MapperScan(basePackages = {"cn.bucheng.authmanager.dao"})
@Import(FdfsClientConfig.class)
@RestController
public class BootstrapApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(BootstrapApplication.class, args);
    }


    @RequestMapping("/test2")
    public Object test(){
        return "test";
    }


}
