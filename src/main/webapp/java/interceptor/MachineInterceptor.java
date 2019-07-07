package interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MachineInterceptor implements HandlerInterceptor {
    @Value("${machineId}")
    private String machineId;
    @Value("${password}")
    private String password;

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Object o) throws Exception {
        String machineId = httpServletRequest.getHeader("machineId");
        String password = httpServletRequest.getHeader("password");
        if ((machineId.equals(this.machineId) && password.equals(this.password))) {
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
