package com.example.demo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class TenantFilter extends GenericFilterBean {

    @Value("#{'${allowed.tenants}'.split(',')}")
    private List<String> allowedTenants;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();
        if (path.startsWith("/saas/")) {
            // Allow OPTIONS requests for CORS preflight
            if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
                chain.doFilter(request, response);
                return;
            }

            String tenantId = req.getHeader("X-TENANT-ID");
            if (tenantId == null || !allowedTenants.contains(tenantId)) {
                res.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid or missing Tenant ID");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
