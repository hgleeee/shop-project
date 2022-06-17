package shop.gagagashop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.gagagashop.service.LoginService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginMember(HttpServletRequest request, @RequestParam(value = "error", required = false) String error,
                              Model model) {
        String referer = request.getHeader("referer");
        if (error == null) {
            request.getSession().setAttribute("prevPage", referer);
        }
        model.addAttribute("error", error);
        return "login/loginForm";
    }

}
