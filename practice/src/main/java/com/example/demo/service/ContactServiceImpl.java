package com.example.demo.service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

	private final ContactRepository contactRepository;

	public ContactServiceImpl(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Override
	public void saveContact(ContactForm contactForm) {
		Contact contact = new Contact();
		contact.setLastName(contactForm.getLastName());
		contact.setFirstName(contactForm.getFirstName());
		contact.setEmail(contactForm.getEmail());
		contact.setPhone(contactForm.getPhone());
		contact.setZipCode(contactForm.getZipCode());
		contact.setAddress(contactForm.getAddress());
		contact.setBuildingName(contactForm.getBuildingName());
		contact.setContactType(contactForm.getContactType());
		contact.setBody(contactForm.getBody());
		contactRepository.save(contact);
	}

	@Override
	public List<Contact> findAllContacts() {
		return contactRepository.findAll();
	}

	@Override
	public Contact findContactById(Long id) {
		return contactRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));
	}

	@Override
	public void updateContact(Long id, ContactForm contactForm) { 
		Contact contact = contactRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));
		contact.setLastName(contactForm.getLastName());
		contact.setFirstName(contactForm.getFirstName());
		contact.setEmail(contactForm.getEmail());
		contact.setPhone(contactForm.getPhone());
		contact.setZipCode(contactForm.getZipCode());
		contact.setAddress(contactForm.getAddress());
		contact.setBuildingName(contactForm.getBuildingName());
		contact.setContactType(contactForm.getContactType());
		contact.setBody(contactForm.getBody());
		contactRepository.save(contact);
	}

	@Override
	public void deleteContact(Long id) {
		contactRepository.deleteById(id);
	}
}