package ua.nure.ageev.finaltask4.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter(filterName = "EncodingFilter", urlPatterns = {"/*"})
public class EncodingFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(EncodingFilter.class);

    private static final String FILTERABLE_CONTENT_TYPE="application/x-www-form-urlencoded";

    private static final String ENCODING_DEFAULT = "UTF-8";

    private static final String ENCODING_INIT_PARAM_NAME = "encoding";

    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        LOG.debug("EncodingFilter initialization starts");
        encoding = config.getInitParameter(ENCODING_INIT_PARAM_NAME);
        if (encoding == null)
            encoding = ENCODING_DEFAULT;
        LOG.debug("EncodingFilter initialization finished");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        LOG.debug("EncodingFilter starts");
        HttpServletRequest httpRequest = (HttpServletRequest)req;
        LOG.trace("Request uri --> " + httpRequest.getRequestURI());
        String contentType = req.getContentType();
        LOG.trace("contentType --> " + contentType);
        if (contentType != null && contentType.startsWith(FILTERABLE_CONTENT_TYPE))
            req.setCharacterEncoding(encoding);
        LOG.debug("EncodingFilter encoding " + encoding);
        LOG.debug("EncodingFilter finished");
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        LOG.debug("Filter destruction starts");
        // no op
        LOG.debug("Filter destruction finished");
    }

}
