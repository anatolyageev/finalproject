package ua.nure.ageev.finaltask4.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;


//@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(SessionLocaleFilter.class);
    private Enumeration<String> localeFilter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Filter initialization starts");

        LOG.debug("Filter initialization finished");
    }

    /**
     * Execution method for locale filter.
     * if attribute sessionLocale change then locale change too.
     */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        LOG.debug("Filter starts");

        // получаем сессию
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        // получаем объект name
        String name = (String) session.getAttribute("currentLocale");
        String lang = req.getParameter("lang");

        if (lang != null) {
            session.setAttribute("currentLocale", lang);
        }

        if (name == null) {
            Locale userPreferredLocale = req.getLocale();
            Enumeration userPreferredLocales = req.getLocales();
            LOG.debug("Preferred Locale: " + userPreferredLocale.toString().substring(0, 2));
            session.setAttribute("currentLocale", userPreferredLocale.toString().substring(0, 2));
            ((HttpServletResponse) servletResponse).setHeader("Content-Language", "ru");
        }

        name = (String) session.getAttribute("currentLocale");

        LOG.debug("Current locale: " + name);

        LOG.debug("Filter finished");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        LOG.debug("Filter destruction starts");
        // no op
        LOG.debug("Filter destruction finished");
    }
}
