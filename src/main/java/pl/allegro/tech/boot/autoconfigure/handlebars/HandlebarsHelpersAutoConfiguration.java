package pl.allegro.tech.boot.autoconfigure.handlebars;

import com.github.jknack.handlebars.HumanizeHelper;
import com.github.jknack.handlebars.Jackson2Helper;
import com.github.jknack.handlebars.MarkdownHelper;
import com.github.jknack.handlebars.helper.AssignHelper;
import com.github.jknack.handlebars.helper.IncludeHelper;
import com.github.jknack.handlebars.helper.JodaHelper;
import com.github.jknack.handlebars.helper.NumberHelper;
import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConditionalOnClass(HandlebarsViewResolver.class)
@ConditionalOnWebApplication
public class HandlebarsHelpersAutoConfiguration {

    @Configuration
    @ConditionalOnClass(Jackson2Helper.class)
    static class JsonHelperAutoConfiguration {

        @Autowired
        private HandlebarsViewResolver handlebarsViewResolver;

        @PostConstruct
        public void registerHelper() {
            handlebarsViewResolver.registerHelper("json", Jackson2Helper.INSTANCE);
        }
    }

    @Configuration
    @ConditionalOnClass(AssignHelper.class)
    static class AssignHelperAutoConfiguration {

        @Autowired
        private HandlebarsViewResolver handlebarsViewResolver;

        @PostConstruct
        public void registerHelper() {
            handlebarsViewResolver.registerHelper("assign", AssignHelper.INSTANCE);
        }
    }

    @Configuration
    @ConditionalOnClass(IncludeHelper.class)
    static class IncludeHelperAutoConfiguration {

        @Autowired
        private HandlebarsViewResolver handlebarsViewResolver;

        @PostConstruct
        public void registerHelper() {
            handlebarsViewResolver.registerHelper("include", IncludeHelper.INSTANCE);
        }
    }

    @Configuration
    @ConditionalOnClass(MarkdownHelper.class)
    static class MarkdownHelperAutoConfiguration {

        @Autowired
        private HandlebarsViewResolver handlebarsViewResolver;

        @PostConstruct
        public void registerHelper() {
            handlebarsViewResolver.registerHelper("md", MarkdownHelper.INSTANCE);
        }
    }

    @Configuration
    @ConditionalOnClass(NumberHelper.class)
    static class NumberHelpersAutoConfiguration {

        @Autowired
        private HandlebarsViewResolver handlebarsViewResolver;

        @PostConstruct
        public void registerHelpers() {
            NumberHelper.register(handlebarsViewResolver.getHandlebars());
        }
    }

    @Configuration
    @ConditionalOnClass(HumanizeHelper.class)
    static class HumanizeHelpersAutoConfiguration {

        @Autowired
        private HandlebarsViewResolver handlebarsViewResolver;

        @PostConstruct
        public void registerHelpers() {
            HumanizeHelper.register(handlebarsViewResolver.getHandlebars());
        }
    }

    @Configuration
    @ConditionalOnClass({JodaHelper.class, DateTimeFormat.class})
    static class JodaHelpersAutoConfiguration {

        @Autowired
        private HandlebarsViewResolver handlebarsViewResolver;

        @PostConstruct
        public void registerHelpers() {
            handlebarsViewResolver.registerHelpers(JodaHelper.class);
        }
    }
}
