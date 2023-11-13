package airline.airlinemidterm4458.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
    public class SwaggerConfig {
        public Docket api() {

            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("airline.airlinemidterm4458"))
                    .paths(PathSelectors.regex("/api.*"))
                    .build().apiInfo(info());

        }

    private ApiInfo info() {

        return new ApiInfoBuilder().title("Company Project api").description("Api documents")
                .contact(new Contact("Eylül Özatman" , "" , ""))
                .license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0").build();
    }

}


