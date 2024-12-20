package com.example.demo.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.service.ContactService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/contacts")

public class ContactController {

	private final ContactService contactService;

	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

	@GetMapping
	public String listContacts(Model model) {
		List<Contact> contacts = contactService.findAllContacts();
		model.addAttribute("contacts", contacts);
		return "admin/contacts"; // contacts.html テンプレートを表示
	}

	@GetMapping("/{id}")
	public String viewContact(@PathVariable Long id, Model model) {
		Contact contact = contactService.findContactById(id);
		model.addAttribute("contact", contact);
		return "admin/detail"; // detail.html テンプレートを表示
	}

	@PostMapping("/detail")
	public String showDetail(@RequestParam("selectedContactId") Long contactId) {
		return "redirect:/admin/contacts/" + contactId; // 選択されたIDに基づいて詳細ページにリダイレクト
	}

	@GetMapping("/{id}/edit")
	public String editContact(@PathVariable Long id, Model model) {
		Contact contact = contactService.findContactById(id);
		ContactForm contactForm = new ContactForm(
				contact.getId(),
				contact.getLastName(),
				contact.getFirstName(),
				contact.getEmail(),
				contact.getPhone(),
				contact.getZipCode(),
				contact.getAddress(),
				contact.getBuildingName(),
				contact.getContactType(),
				contact.getBody());
		model.addAttribute("contactForm", contactForm);
		return "admin/edit";
	}

	@PostMapping("/{id}/edit")
	public String updateContact(@PathVariable Long id,
			@Valid @ModelAttribute("contactForm") ContactForm contactForm,
			BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "admin/edit";
		}
		contactService.updateContact(id, contactForm);
		return "redirect:/admin/contacts/" + id;
	}

	@GetMapping("/{id}/delete")
	public String deleteContact(@PathVariable Long id) {
		contactService.deleteContact(id);
		return "redirect:/admin/contacts";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("contactForm", new ContactForm());
		return "admin/signup";
	}

	@PostMapping("/register")
	public String registerContact(@Valid @ModelAttribute("contactForm") ContactForm contactForm,
			BindingResult result) {
		if (result.hasErrors()) {
			return "admin/signup";
		}
		contactService.saveContact(contactForm);
		return "redirect:/admin/contacts";
	}
}
