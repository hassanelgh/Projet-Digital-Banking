package com.digitalbancking.digitalbancking;

import com.digitalbancking.digitalbancking.entities.AccountOperation;
import com.digitalbancking.digitalbancking.entities.CurrentAccount;
import com.digitalbancking.digitalbancking.entities.Customer;
import com.digitalbancking.digitalbancking.entities.SavingAccount;
import com.digitalbancking.digitalbancking.enums.AccountStatus;
import com.digitalbancking.digitalbancking.enums.OperationType;
import com.digitalbancking.digitalbancking.repositories.AccountOperationRepository;
import com.digitalbancking.digitalbancking.repositories.BankAccountRepository;
import com.digitalbancking.digitalbancking.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBanckingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBanckingApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
										BankAccountRepository bankAccountRepository,
										AccountOperationRepository accountOperationRepository){
		return args -> {

			Stream.of("hassan","mahdi","e").forEach(name ->{
				Customer customer=new Customer();
				customer.setId(UUID.randomUUID().toString());
				customer.setName(name);
				customer.setEmail(name+"@gmaim.com");
				customerRepository.save(customer);
			});


			customerRepository.findAll().forEach(customer -> {
				CurrentAccount currentAccount=new CurrentAccount();
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setBalance(Math.random()*9000);
				currentAccount.setCreatedAt(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setCustomer(customer);
				currentAccount.setOverDraft(9000);
				bankAccountRepository.save(currentAccount);

				SavingAccount savingAccount=new SavingAccount();
				savingAccount.setId(UUID.randomUUID().toString());
				savingAccount.setBalance(Math.random()*9000);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(customer);
				savingAccount.setInterestRate(5.5);
				bankAccountRepository.save(savingAccount);
			});

			bankAccountRepository.findAll().forEach(bankAccount -> {

				for (int i = 0; i <10 ; i++) {
					AccountOperation accountOperation=new AccountOperation();
					accountOperation.setOperationDate(new Date());
					accountOperation.setAmount(Math.random()*9000);
					accountOperation.setType(Math.random()>0.5 ? OperationType.DEBIT: OperationType.CREDIT);
					accountOperation.setBankAccount(bankAccount);

					accountOperationRepository.save(accountOperation);
				}
			});


		};
	}
}
