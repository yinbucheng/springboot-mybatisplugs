package cn.bucheng.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * @author ：yinchong
 * @create ：2019/7/4 9:00
 * @description：shiro测试使用
 * @modified By：
 * @version:
 */
public class ShiroTest {

    @Test
    public void testSimpleShiro(){
        SimpleAccountRealm realm = new SimpleAccountRealm();
        realm.addAccount("yinchong","123456");
        //构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm);
        //主体提交让认证
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("yinchong","123456");
        //登入
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        //登出
        subject.logout();
        System.out.println(subject.isAuthenticated());

    }

    @Test
    public void testTextShiro() {
        // 创建SecurityManager工厂。通过ini配置文件创建securityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro-first.ini");

        // 创建SecurityManager
        SecurityManager securityManager = factory.getInstance();

        // 将securityManager设置到当前的运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        // 从SecurityUtils中创建一个subject
        Subject subject = SecurityUtils.getSubject();

        // 在认证提交前准备token(令牌)
        // 这里的账号和密码 将来是由用户输入进去的
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");

        //执行认证提交
        try {
            //执行认证提交
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        // 是否认证通过
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证通过：" + isAuthenticated);

        //退出操作
        subject.logout();

        // 是否认证通过
        isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证通过：" + isAuthenticated);
    }
}
