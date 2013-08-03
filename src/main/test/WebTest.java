import com.itv.action.IndexAction;
import com.itv.action.ManageAction;
import com.itv.dao.IBaseDao;
import com.itv.pojo.MovieBean;
import com.itv.pojo.MovieFocusMap;
import com.itv.service.ManageService;
import com.itv.service.PlayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiajun
 * Date: 13-6-29
 * Time: 下午6:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class WebTest {
    @Resource
    IBaseDao baseDao;
    @Resource
    IndexAction indexAction;
    @Resource
    PlayService playService;
    @Resource
    ManageAction manageAction;
    @Resource
    private ManageService manageService;
    @Test
    public void tdao() {
        try {
            MovieBean list = (MovieBean) this.baseDao.findOne("com.itv.movie.findMovieInfo", "4e047cd0509cf");
            System.out.println(list.getName()+" ===== "+list.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void taction() {
        try {
            manageService.pluginAdd("http://v.youku.com/v_show/id_XNTgxMzQzOTIw.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
