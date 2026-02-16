package lk.ijse.pos_system_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // මුළු සිස්ටම් එකේම තියෙන URLs වලට ඉඩ දෙනවා
                .allowedOrigins("*") // ඕනෑම තැනක ඉඳන් එන Requests වලට ඉඩ දෙනවා (Frontend එකේ port එක වෙනස් වුනත් වැඩ)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // මේ Methods ඔක්කොටම ඉඩ දෙනවා
                .allowedHeaders("*"); // ඕනෑම Header එකකට ඉඩ දෙනවා
    }
}
