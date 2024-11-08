package top.warmwind.master;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import top.warmwind.master.core.config.SysConfigProperties;
import top.warmwind.master.core.utils.JwtSubject;
import top.warmwind.master.core.utils.JwtUtil;
import top.warmwind.master.system.entity.SysUser;
import top.warmwind.master.system.mapper.SysUserMapper;

import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;

@SpringBootTest
class MasterApplicationTests {

    @Autowired
    private SysConfigProperties sysConfigProperties;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @SneakyThrows
    @Test
    void contextLoads() {
        System.out.println(bCryptPasswordEncoder.encode("warmwind623"));
        // List<SysUser> users1 = sysUserMapper.selectList(null);
        // users1.forEach(System.out::println);

        // List<SysUser> users = sysUserMapper.selectTest("; delete sys_user where id = 2 ;");
        // System.out.println(users.toString());

        // SecretKey secretKey = JwtUtil.randomKey();
        // System.out.println(JwtUtil.encodeKey(secretKey));

        // JwtSubject jwtSubject = new JwtSubject("qjf1");
        // String s = JwtUtil.buildToken(sysConfigProperties.getIssuer(),
        //         jwtSubject,
        //         sysConfigProperties.getTokenExpireTime(),
        //         sysConfigProperties.getBase64EncodedKey());


        // Field[] publicFields = SysUser.class.getDeclaredFields();
        // for (Field f : publicFields) {
        //     f.setAccessible(true);
        //     System.out.println(f.getName());
        // }


        // System.out.println(s);
    }

}
