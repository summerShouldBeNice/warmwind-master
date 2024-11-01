package top.warmwind.master;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.warmwind.master.core.config.SysConfigProperties;
import top.warmwind.master.core.utils.JwtSubject;
import top.warmwind.master.core.utils.JwtUtil;

import javax.crypto.SecretKey;
import java.util.Scanner;

@SpringBootTest
class MasterApplicationTests {

    @Autowired
    private SysConfigProperties sysConfigProperties;

    @Test
    void contextLoads() {
        // SecretKey secretKey = JwtUtil.randomKey();
        // System.out.println(JwtUtil.encodeKey(secretKey));

        JwtSubject jwtSubject = new JwtSubject("qjf1");
        String s = JwtUtil.buildToken(sysConfigProperties.getIssuer(),
                jwtSubject,
                sysConfigProperties.getTokenExpireTime(),
                sysConfigProperties.getBase64EncodedKey());
        System.out.println(s);
    }

}
