package com.wuzhicheng.pigstore.config;

import com.wuzhicheng.pigstore.common.JacksonObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-06-30 22:45
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 添加静态资源目录
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

    /**
     * 扩展消息转换器，即将后端的数据转换为前端
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter mc=new MappingJackson2HttpMessageConverter();
        //设置对象转换器，在该转换器中将long型的数据转换为字符串，避免精度丢失
        mc.setObjectMapper(new JacksonObjectMapper());
        //将上面设置的消息转换器对象追加到mvc框架的转换器集合中，并置于最前
        converters.add(0,mc);
    }
}
