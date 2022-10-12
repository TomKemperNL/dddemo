package nl.tomkemper.dddemo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CustomerController {
    public record UpdateCustomerDTO(String newEmail) {
    }

    public record CustomerResponseDTO(Long id, String email) {
        public static CustomerResponseDTO fromCustomer(Customer c) {
            return new CustomerResponseDTO(
                    c.getId().getValue(), c.getEmailAddress().getValue());
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity updateCustomer(@PathVariable Long id,
                                         @RequestBody UpdateCustomerDTO newCustomerData) {
        Optional<Customer> maybeCustomer = customerRepository.get(new CustomerId(id));
        if (maybeCustomer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<EmailAddress> submittedAddress = EmailAddress.parse(newCustomerData.newEmail());
        if (submittedAddress.isEmpty()) {
            return ResponseEntity.badRequest().body("Ongeldig emailadres");
        }
        Customer foundCustomer = maybeCustomer.get();
        foundCustomer.setEmailAddress(submittedAddress.get());

        return ResponseEntity.ok(CustomerResponseDTO.fromCustomer(foundCustomer));
    }


    @Autowired
    private CustomerRepository customerRepository;
}
