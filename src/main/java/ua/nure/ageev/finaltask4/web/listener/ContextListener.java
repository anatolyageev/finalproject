package ua.nure.ageev.finaltask4.web.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.debug("Servlet context initialization starts");
        // no op
        LOG.debug("Servlet context initialization finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.debug("Servlet context destruction starts");

        LOG.debug("Servlet context destruction finished");
    }
}
