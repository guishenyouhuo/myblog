package com.wf.blog.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * 配置 Hibernate Validator
 *
 * @author guishenyouhuo
 */
@Configuration
public class ValidatorConfig {

  @Bean(name = "validator")
  public LocalValidatorFactoryBean getValidator() {
    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
    bean.setProviderClass(HibernateValidator.class);
    return bean;
  }
}
