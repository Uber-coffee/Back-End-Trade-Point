//package trade_point.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@Order(1)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    public static final String[] WHITE_LIST = {
//            "/configuration/**",
//            "/swagger-resources/**",
//            "/swagger-ui.html",
//            "/v2/api-docs",
//            "/webjars/**"
//    };
//
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers(WHITE_LIST);
//    }
//}