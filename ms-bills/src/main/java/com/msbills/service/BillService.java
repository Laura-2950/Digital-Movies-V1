package com.msbills.service;

import com.msbills.models.Bill;
import com.msbills.models.DTO.BillDTO;
import com.msbills.models.DTO.UserDTO;
import com.msbills.repositories.BillRepository;
import com.msbills.repositories.FeignUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

  private final BillRepository repository;
  private final FeignUsersRepository usersRepository;

  public List<Bill> getAllBill() {
    return repository.findAll();
  }

  public Bill saveBill(Bill bill) {
    return repository.save(bill);
  }

  public Bill findByCustomer(String customer) {
    return repository.findByCustomerBill(customer).orElse(null);
  }

  public BillDTO findByUserName(String username) {
    Bill bill= repository.findByCustomerBill(username).orElse(null);
    ResponseEntity<UserDTO> userDTO= usersRepository.findByUserName(username);
    BillDTO billDTO = null;
    if (bill != null) {
      billDTO.setIdBill(bill.getIdBill());
      billDTO.setBillingDate(bill.getBillingDate());
      billDTO.setTotalPrice(bill.getTotalPrice());
      billDTO.setUserDTO(userDTO.getBody());

    }
    return billDTO;
  }

}
