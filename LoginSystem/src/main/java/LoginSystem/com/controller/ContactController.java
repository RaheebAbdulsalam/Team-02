package LoginSystem.com.controller;


import LoginSystem.com.model.Contact;
import LoginSystem.com.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;


    @GetMapping("/contact")
    public String viewContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/submit-form")
    public String submitForm(@ModelAttribute Contact contact) {
        contactRepository.save(contact);
        return "redirect:/contact";
//        return "success"; // success page
    }
}
