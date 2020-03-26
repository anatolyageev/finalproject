package ua.nure.ageev.finaltask4.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;


@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(SessionLocaleFilter.class);
    private Enumeration<String> localeFilter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Filter initialization starts");
//        localeFilter = filterConfig.getInitParameterNames();
//        while (localeFilter.hasMoreElements()) {
//            String key = localeFilter.nextElement();
//            if (!key.isEmpty()) {
//                System.out.println(filterConfig.getInitParameter(key));
//            }
//        }
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
//
//        if(req.getParameter("sessionLocale") == null) {
//            Locale userPreferredLocale = req.getLocale();
//            Enumeration userPreferredLocales = req.getLocales();
//            LOG.debug("Preferred Locale: " + userPreferredLocale.toString());
//            while (userPreferredLocales.hasMoreElements()) {
//                userPreferredLocale = (Locale) userPreferredLocales.nextElement();
//            }
//            req.getSession().setAttribute("lang", userPreferredLocale.toString());
//        }
//
//        if (req.getParameter("sessionLocale") != null) {
//            req.getSession().setAttribute("lang", req.getParameter("sessionLocale"));
//        }
//        LOG.debug("Lang set: "+ servletRequest.getAttribute("sessionLocale"));

        // получаем сессию
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        // получаем объект name
        String name = (String) session.getAttribute("sessionLocale");

        System.out.println(name);

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
