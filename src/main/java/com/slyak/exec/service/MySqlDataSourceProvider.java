package com.slyak.exec.service;

import com.google.inject.Provider;
import com.jolbox.bonecp.BoneCPDataSource;

import javax.sql.DataSource;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:stormning@163.com">stormning</a>
 * @version V1.0, 2015/1/7
 */
public class MySqlDataSourceProvider implements Provider<DataSource> {

    @Override
    public DataSource get() {
        final BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://192.168.88.213:3306/msc?useUnicode=true&amp;characterEncoding=utf8&amp;autoReconnect=true");
        dataSource.setUsername("msc");
        dataSource.setPassword("msc");
        return dataSource;
    }

}
