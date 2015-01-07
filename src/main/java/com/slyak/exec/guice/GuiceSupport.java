package com.slyak.exec.guice;

import com.google.common.collect.Lists;
import com.google.inject.*;
import org.apache.commons.lang.ClassUtils;
import org.reflections.Reflections;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:stormning@163.com">stormning</a>
 * @version V1.0, 2015/1/7
 */
public class GuiceSupport {

    private Reflections reflections;

    public GuiceSupport() {
        reflections = new Reflections("com.slyak");
        Injector injector = Guice.createInjector(findAllModules());
        injector.injectMembers(this);
    }

    private List<Module> findAllModules() {
        List<Module> modules = Lists.newArrayList();
        addAnnotatedModules(modules);
        addProviderModules(modules);
        return modules;
    }

    private void addProviderModules(List<Module> modules) {
        Set<Class<? extends Provider>> providers = reflections.getSubTypesOf(Provider.class);
        for (final Class<? extends Provider> pc : providers) {
            final Class clazz = (Class) ((ParameterizedType) pc.getGenericInterfaces()[0]).getActualTypeArguments()[0];
            modules.add(new Module() {
                @Override
                public void configure(Binder binder) {
                    binder.bind(clazz).toProvider(pc).in(Scopes.SINGLETON);
                }
            });
        }
    }

    private void addModule(final Class face, final Class impl, List<Module> modules) {
        modules.add(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(face).to(impl).in(Scopes.SINGLETON);
            }
        });
    }

    private void addAnnotatedModules(List<Module> modules) {
        Set<Class<?>> singletons = reflections.getTypesAnnotatedWith(Singleton.class);
        for (final Class singleton : singletons) {
            List<Class> interfaces = ClassUtils.getAllInterfaces(singleton);
            for (final Class face : interfaces) {
                addModule(face, singleton, modules);
            }
        }
    }
}
