package com.itv.action;

import com.itv.service.ManageService;
import com.itv.util.CheckUtil;
import com.itv.util.Crypt;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.Cookie;
import java.util.Date;

/**
 * User: xiajun
 * Date: 13-7-14
 * Time: 下午2:06
 */
public class ManageAction {
    private final static Logger log=Logger.getLogger(ManageAction.class);
    private String name;
    private String pwd;
    private ManageService manageService;

    /**
     * 管理员登录
     * @return
     */
    public String login(){
        this.name=CheckUtil.replaceAll(name);
        this.pwd=CheckUtil.replaceAll(pwd);
        try {
            boolean islogin=this.manageService.login(this.name,this.pwd);
            if(islogin){
                Long time= new Date().getTime();
                String msg=Crypt.parseByte2HexStr(Crypt.encrypt(name+":"+pwd+":"+time, "1234567887654321"));
                Cookie user = new Cookie("user",msg);
                user.setMaxAge(-1);//当浏览器关闭时清除
                ServletActionContext.getResponse().addCookie(user);
                return "login";
            }
        } catch (Exception e) {
            log.error("",e);
        }
        return "reLogin";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setManageService(ManageService manageService) {
        this.manageService = manageService;
    }
}
