import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideRepositorySearchTest {


    @BeforeAll
    static void setUp(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void searchJUnit5SnippetTest(){

        String snippetOfJUnit5 = "@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}";

        open("/selenide/selenide");

        $("#wiki-tab").click();

        $("#wiki-pages-filter").setValue("SoftAssertions").pressEnter();
        $(".filterable-active").shouldHave(text("SoftAssertions"));

        $(".filterable-active").$(byText("SoftAssertions")).click();
        $("#wiki-body").shouldHave(text(snippetOfJUnit5));
    }

}
