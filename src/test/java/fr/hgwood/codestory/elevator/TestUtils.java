package fr.hgwood.codestory.elevator;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.hamcrest.*;

import com.google.common.io.CharStreams;

public class TestUtils {
    
    public static final int TestPort = 8080;
    
    public static HttpResponseInfo requestTo(String path) throws Exception {
        URL url = new URL("http", "localhost", TestPort, path);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        try (Reader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), Charset.forName("utf-8")))) {
            return new HttpResponseInfo(connection.getResponseCode(), CharStreams.toString(reader));
        }
    }
    
    public static Matcher<HttpResponseInfo> responds(final int httpStatus) {
        return new TypeSafeMatcher<HttpResponseInfo>() {

            @Override
            public void describeTo(Description description) {
                description
                    .appendText("A HTTP response with status ")
                    .appendValue(httpStatus);
            }

            @Override
            protected boolean matchesSafely(HttpResponseInfo item) {
                return item.status == httpStatus;
            }
        };
    }
    
    public static Matcher<HttpResponseInfo> respondsOk() {
    	return responds(200);
    }
    
    public static Matcher<HttpResponseInfo> responds(final String content) {
        return new TypeSafeMatcher<HttpResponseInfo>() {

            @Override
            public void describeTo(Description description) {
                description
                .appendText("A HTTP response with content equals to ")
                .appendValue(content);
            }

            @Override
            protected boolean matchesSafely(HttpResponseInfo input) {
                return input.content.equals(content);
            }
        };
    }

}
