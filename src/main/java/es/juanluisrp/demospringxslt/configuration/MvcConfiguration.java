package es.juanluisrp.demospringxslt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.xslt.XsltView;
import org.springframework.web.servlet.view.xslt.XsltViewResolver;

/**
 * Created by juanl on 08/09/2016.
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver getXSLTViewResolver() {
        XsltViewResolver xsltResolver = new XsltViewResolver();
        xsltResolver.setOrder(1);
        xsltResolver.setSourceKey("xmlSource");

        xsltResolver.setViewClass(XsltView.class);
        xsltResolver.setViewNames("*");
        xsltResolver.setPrefix("/WEB-INF/xsl/");
        xsltResolver.setSuffix(".xsl");
        return xsltResolver;
    }

}
