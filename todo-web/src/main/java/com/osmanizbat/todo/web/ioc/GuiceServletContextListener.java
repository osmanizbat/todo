package com.osmanizbat.todo.web.ioc;

import com.osmanizbat.todo.bootstrap.TodoMockDataModule;
import javax.servlet.annotation.WebListener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.osmanizbat.todo.bootstrap.TodoCoreModule;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@WebListener
public class GuiceServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext ctx = event.getServletContext();
        Injector inject = Guice.createInjector(new TodoCoreModule());
        ctx.setAttribute(Injector.class.getName(), inject);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ServletContext ctx = event.getServletContext();
        ctx.removeAttribute(Injector.class.getName());
    }

}
