package top.warmwind.master.system.service.impl;

import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import top.warmwind.master.system.entity.SysLoginRecord;
import top.warmwind.master.system.mapper.SysLoginRecordMapper;
import top.warmwind.master.system.service.SysLoginRecordService;

import java.util.Objects;

/**
 * 系统登录记录服务实现类
 *
 * @author warmwind
 * @since 2024-11-08 下午2:37
 */
@Service
public class SysLoginRecordServiceImpl extends ServiceImpl<SysLoginRecordMapper, SysLoginRecord>
        implements SysLoginRecordService {

    @Override
    public void saveAsync(String username, Integer type, String comments, HttpServletRequest request) {
        if (Objects.isNull(username)) return;
        SysLoginRecord sysLoginRecord = new SysLoginRecord();
        sysLoginRecord.setUsername(username);
        sysLoginRecord.setLoginType(type);
        sysLoginRecord.setComments(comments);
        UserAgent ua = UserAgentUtil.parse(JakartaServletUtil.getHeaderIgnoreCase(request, "User-Agent"));
        if (ua != null) {
            sysLoginRecord.setOs(ua.getPlatform().toString());
            sysLoginRecord.setDevice(ua.getOs().toString());
            sysLoginRecord.setBrowser(ua.getBrowser().toString());
        }
        sysLoginRecord.setIp(JakartaServletUtil.getClientIP(request));
        save(sysLoginRecord);
    }

}
