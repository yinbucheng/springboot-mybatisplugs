package cn.bucheng.cat;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;

/**
 * @author buchengyin
 * @create 2019/7/7 10:49
 * @describe web相关工具类
 */
public class WebUtils {

    private static ServletRequestAttributes getAttribute(){
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static void saveCookie(String name,String value){
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        getAttribute().getResponse().addCookie(cookie);
    }

    public static String getValueFromCookie(String name){
        Cookie[] cookies = getAttribute().getRequest().getCookies();
        if(cookies==null){
            return null;
        }

        for(Cookie cookie:cookies){
            if(name.equals(cookie.getName())){
                return cookie.getValue();
            }
        }

        return null;
    }

}
