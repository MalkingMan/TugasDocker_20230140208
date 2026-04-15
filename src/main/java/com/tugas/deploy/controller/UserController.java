package com.tugas.deploy.controller;

import com.tugas.deploy.model.User;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private static final String ADMIN_USERNAME = "array";
    private static final String ADMIN_PASSWORD = "208";

    // Penyimpanan sementara di memory, akan hilang saat aplikasi restart.
    private List<User> users = new ArrayList<>();

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "logout", required = false) String logout, Model model) {
        if (logout != null) {
            model.addAttribute("message", "Anda berhasil logout.");
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {

        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("username", username);
            return "redirect:/home";
        }

        model.addAttribute("error", "Username atau password salah.");
        return "login";
    }

    @GetMapping("/home")
    public String homePage(Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        model.addAttribute("users", users);
        model.addAttribute("websiteTitle", "MY Website 20210140019");
        model.addAttribute("identityText", "Nama: Mahasiswa DPL | NIM: 20210140019");
        return "home";
    }

    @GetMapping("/form")
    public String formPage(Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        model.addAttribute("user", new User());
        model.addAttribute("websiteTitle", "MY Website 20210140019");
        return "form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        if (user.getNama() != null) {
            user.setNama(user.getNama().trim());
        }

        if (user.getNim() != null) {
            user.setNim(user.getNim().trim());
        }

        if (user.getNama() != null && !user.getNama().isEmpty()
                && user.getNim() != null && !user.getNim().isEmpty()) {
            users.add(user);
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }

    private boolean isLoggedIn(HttpSession session) {
        Object isLoggedIn = session.getAttribute("isLoggedIn");
        return isLoggedIn instanceof Boolean && (Boolean) isLoggedIn;
    }
}

