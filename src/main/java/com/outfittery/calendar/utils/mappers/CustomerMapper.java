package com.outfittery.calendar.utils.mappers;

import com.outfittery.calendar.dto.CustomerDTO;
import com.outfittery.calendar.models.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * Utility class for mapping customer to related DTOs and vice versa.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CustomerMapper {

    public static Customer buildCustomer(CustomerDTO dto) {
        Customer customer = new Customer();

        copyProperties(dto, customer);

        return customer;
    }

    public static CustomerDTO buildCustomerDTO(Customer customer) {
        final CustomerDTO dto = new CustomerDTO();

        copyProperties(customer, dto);

        return dto;
    }
}
