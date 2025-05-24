package dobackaofront.seguranca_autenticacao_autorizacao.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class AppController {

    @GetMapping({"/", "/home"})
    public String home() {
        // Retorna a página inicial (pública)
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        // A lógica de erro e logout é tratada via parâmetros na página, não no controller
        return "login";
    }

    @GetMapping("/user")
    public String userPage() {
        // Retorna a página do usuário comum (acessível se logado com ROLE_USER ou ROLE_ADMIN)
        return "user-page";
    }

    @GetMapping("/admin")
    public String adminPage() {
        // Retorna a página do admin (acessível se logado com ROLE_ADMIN)
        return "admin-page";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage(Model model) {
        // Podemos adicionar uma mensagem customizada
        model.addAttribute("msg", "Você não tem permissão para acessar esta página.");
        return "access-denied";
    }
}
