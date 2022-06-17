package shop.gagagashop.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("shop.gagagashop.controller")
public class ExceptionController {

    @ExceptionHandler(IllegalArgumentException.class) // 해당 예외 발생 시, 수행!
    public String notFound(Exception exception, Model model) {
        model.addAttribute("exception", exception);
        return "errors/404-error"; // 해당 페이지를 보여 줌!
    }

}
