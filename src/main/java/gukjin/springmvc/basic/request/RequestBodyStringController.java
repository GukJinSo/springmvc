package gukjin.springmvc.basic.request;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Controller
@Slf4j
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// 바이트 코드를 문자로 변경

        log.info("messageBody ={}", messageBody);
        resp.getWriter().write("hello");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringInputStream(InputStream inputStream, Writer writer) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// 바이트 코드를 문자로 변경

        log.info("messageBody ={}", messageBody);
        writer.write("hello");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringHttpEntity(HttpEntity<String> httpEntity) throws IOException {

        log.info("messageBody ={}", httpEntity.getBody());
        return new HttpEntity<>(HttpStatus.OK.toString());
    }

    @PostMapping("/request-body-string-v4")
    @ResponseBody
    public String requestBodyStringRequestBody(@RequestBody String messageBody) throws IOException {

        log.info("messageBody ={}", messageBody);
        return "OK";
    }



}
