package com.wordstalk.legal.drive.inject;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.wordstalk.legal.drive.inject.interceptor.LoginRequiredInterceptor;
import com.wordstalk.legal.drive.inject.interceptor.PartnerRequiredInterceptor;
import com.wordstalk.legal.drive.inject.provider.ConfigurationProvider;
import com.wordstalk.legal.drive.inject.provider.DataSourceProvider;
import com.wordstalk.legal.drive.utils.serializer.LocalDateTimeDeserializer;
import com.wordstalk.legal.drive.utils.serializer.LocalDateTimeSerializer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by y on 2017/12/24.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.wordstalk.legal.drive")
@Import({ConfigurationProvider.class, DataSourceProvider.class})
@EnableAspectJAutoProxy
@EnableTransactionManagement
@MapperScan(basePackages = {
        "com.wordstalk.legal.drive.access.dao.profile.mapper",
        "com.wordstalk.legal.drive.access.dao.document.mapper",
})
@PropertySource(value = {"classpath:server.properties"})
public class AppContext extends WebMvcConfigurerAdapter{

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        builder.indentOutput(true).dateFormat(dateFormat);

        SimpleModule timeModule = new SimpleModule()
                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer())
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        builder.modules(timeModule);

        /* 下载文件编码 */
        converters.add(new ByteArrayHttpMessageConverter());
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

    @Bean("multipartResolver")
    public CommonsMultipartResolver providerMultipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(20965760L);
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        return commonsMultipartResolver;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowCredentials(true).maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(partnerInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean
    public LoginRequiredInterceptor loginInterceptor(){
        return new LoginRequiredInterceptor();
    }

    @Bean
    public PartnerRequiredInterceptor partnerInterceptor(){
        return new PartnerRequiredInterceptor();
    }
}
