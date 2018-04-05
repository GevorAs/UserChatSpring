package com.javaprogrammer.userchatspring;

import com.javaprogrammer.userchatspring.model.ActiveStatus;
import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.model.UserStatus;
import com.javaprogrammer.userchatspring.model.UserType;
import com.javaprogrammer.userchatspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Properties;

@SpringBootApplication
@EnableWebMvc
public class UserchatspringApplication extends WebMvcConfigurerAdapter implements CommandLineRunner {

    @Value("${spring.mail.host}")
    String mailHost;
    @Value("${spring.mail.port}")
    int mailPort;
    @Value("${spring.mail.username}")
    String mailUsername;
    @Value("${spring.mail.password}")
    String mailPassword;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    String mailSmtpAuth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    String mailSmtpStarttlsEnable;
    @Value("${adminEmail}")
    String adminEmail;
    @Value("${adminPassword}")
    String adminPassword;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(UserchatspringApplication.class, args);
    }

    @Bean
    public PasswordEncoder encoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public ViewResolver resolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


    @Bean
    EmbeddedServletContainerCustomizer containerCustomizer() throws Exception {
        return (ConfigurableEmbeddedServletContainer container) -> {
            if (container instanceof TomcatEmbeddedServletContainerFactory) {
                TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
                tomcat.addConnectorCustomizers(
                        (connector) -> {
                            connector.setMaxPostSize(300000000); // 10 MB
                        }
                );
            }
        };
    }

    @Bean(name = "mailSender")
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);

        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", mailSmtpAuth);
        props.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Override
    public void run(String... strings) throws Exception {
        User oneByEmail = userRepository.findUserByEmail(adminEmail);
        if (oneByEmail == null) {
            userRepository.save(User.builder()
                    .name("Manager")
                    .surname("Manager")
                    .email(adminEmail)
                    .password(new BCryptPasswordEncoder().encode(adminPassword))
                    .userType(UserType.ADMIN)
                    .activeStatus(ActiveStatus.DELETED)
                    .userStatus(UserStatus.OFFLINE)
                    .build());
        }
    }


}
