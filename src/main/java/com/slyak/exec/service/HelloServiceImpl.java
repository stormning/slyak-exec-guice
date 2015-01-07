package com.slyak.exec.service;

import com.google.inject.Singleton;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:stormning@163.com">stormning</a>
 * @version V1.0, 2015/1/7
 */
@Singleton
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHi() {
        return "hello world!";
    }
}
