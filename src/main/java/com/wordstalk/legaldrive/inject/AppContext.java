package com.wordstalk.legaldrive.inject;

import com.wordstalk.legaldrive.inject.provider.ConfigurationProvider;
import com.wordstalk.legaldrive.inject.provider.DataSourceProvider;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by y on 2017/12/24.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.wordstalk.legaldrive")
@Import({ConfigurationProvider.class, DataSourceProvider.class})
@EnableAspectJAutoProxy
@MapperScan(basePackages = {
        "com.wordstalk.legaldrive.access.dao.profile.mapper"
})
@PropertySource(value = {"classpath:server.properties"})
public class AppContext extends WebMvcConfigurerAdapter{

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        builder.indentOutput(true).dateFormat(dateFormat);
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        registry.addResourceHandler("*.css").addResourceLocations("/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        registry.viewResolver(resolver);
    }
}
