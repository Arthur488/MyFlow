package workflow.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import workflow.models.Customer;

import java.io.IOException;
import java.util.List;

@Component
public class SettingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;

        try {
            Customer authenticatedCustomer = Utility.getCustomerByEmailFromRequest(servletRequest);
            request.setAttribute("AUTHENTICATED_USER_FULL_NAME", authenticatedCustomer.getFullName());
        } catch (NullPointerException ignored) {

        }


        filterChain.doFilter(request, response);
    }
}
