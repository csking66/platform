package sos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName: SOSApplication
* @Description: 
* @date 2018年11月15日
*
*/
@SpringBootApplication
@RestController
public class SOSApplication {

	@Value("${configClientKey}")
    String configClientKey;
    
    @RequestMapping(value = "/getConf")
    public String getConf(){
        return configClientKey;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(SOSApplication.class, args);
    }
}
