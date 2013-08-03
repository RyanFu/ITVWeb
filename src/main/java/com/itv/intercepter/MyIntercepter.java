package com.itv.intercepter;

import com.itv.dao.IBaseDao;
import com.itv.util.CookieUtil;
import com.itv.util.Crypt;
import com.itv.util.MemcacheUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sina.sae.memcached.SaeMemcache;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import java.util.Date;

/**
 * 管理页面连接器
 */
public class MyIntercepter extends AbstractInterceptor {
    private static Logger log = Logger.getLogger(MyIntercepter.class);
    private SaeMemcache mem = MemcacheUtil.getClient();
    private IBaseDao<String> baseDao;

    public String intercept(ActionInvocation arg0) throws Exception {
        String action = arg0.getProxy().getActionName();
        String method = arg0.getProxy().getMethod();
        if (action.equals("manageAction_login") || action.equals("manageAction_pluginAdd")) {
            return arg0.invoke();
        }
        String user = CookieUtil.getDecodeValue(CookieUtil.getCookieValue(ServletActionContext.getRequest()));
        if(user==null){
            return "reLogin";
        }
        String[] uinfo = user.split(":");
        if (uinfo != null && uinfo.length == 3) {
            long time = Long.parseLong(uinfo[2]);
            long newtime = new Date().getTime();
            if ((newtime - time) > 60 * 60000) {
                return "reLogin";
            }
            String pwd = null;
            if (mem != null) {
                pwd = mem.get(uinfo[0]);
            }
            if(pwd==null){
                pwd=baseDao.findOne("com.itv.manage.login",uinfo[0]);
                pwd=Crypt.MD5(uinfo[0]+pwd);
                if(mem!=null){
                    mem.set(uinfo[0],pwd,MemcacheUtil.TIMEOUT);
                }
            }
            if(uinfo[1].equalsIgnoreCase(pwd)){
                return arg0.invoke();
            }
        }
        return "reLogin";
    }

    public void setBaseDao(IBaseDao baseDao) {
        this.baseDao = baseDao;
    }
}
