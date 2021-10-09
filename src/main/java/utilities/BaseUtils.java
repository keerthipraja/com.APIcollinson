package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class BaseUtils {

    public RequestSpecification requestSpecification() throws IOException {
        PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
        RequestSpecification spec = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .setContentType(ContentType.JSON).build();
        return spec;
    }

    public ResponseSpecification responseSpecification(String statusCode) {
        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectStatusCode(Integer.parseInt((statusCode)))
                .build();
        return resSpec;
    }

    public String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/utilities/global.properties");
        prop.load(fis);
        return prop.getProperty(key);

    }


}
