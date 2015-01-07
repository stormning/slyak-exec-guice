package com.slyak.exec;

import com.google.inject.Inject;
import com.slyak.exec.bean.Area;
import com.slyak.exec.guice.GuiceSupport;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:stormning@163.com">stormning</a>
 * @version V1.0, 2015/1/7
 */
public class SampleReport extends GuiceSupport {

    @Inject
    private DataSource dataSource;

    public void getAreas() throws SQLException {
        QueryRunner runner = new QueryRunner(dataSource);
        List<Area> areas = runner.query("Select * from m_area", new BeanListHandler<Area>(Area.class));
        for (Area area : areas) {
            System.out.println(area.getName());
        }
    }


    public static void main(String[] args) {
        try {
            new SampleReport().getAreas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
