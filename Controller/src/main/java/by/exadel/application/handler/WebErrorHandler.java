/*package by.exadel.application.handler;

import java.io.IOException;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice("by.exadel.application")
public class WebErrorHandler {
    private final MappingJackson2HttpMessageConverter converter;

    public WebErrorHandler(MappingJackson2HttpMessageConverter converter) {
        this.converter = converter;
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public final String handleHttpClientException(HttpClientErrorException ex, Model model) {

        model.addAttribute("exception", ex.getMessage());
        model.addAttribute("response", getExceptionResponse(ex.getResponseBodyAsString()));
        return "exception";
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public final String handleHttpServerErrorException(HttpServerErrorException ex, Model model) {

        model.addAttribute("exception", ex.getMessage());
        model.addAttribute("response", getExceptionResponse(ex.getResponseBodyAsString()));
        return "exception";
    }


    private ExceptionResponse getExceptionResponse(String json) {
        try {

            return converter.getObjectMapper().readValue(json, ExceptionResponse.class);

        } catch (IOException e) {

            return new ExceptionResponse("We can't interpret response");
        }
    }

}*/
