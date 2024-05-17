package org.example.ebankingbackend;

import org.example.ebankingbackend.dtos.BankAccountDTO;
import org.example.ebankingbackend.dtos.CurrentBankAccountDTO;
import org.example.ebankingbackend.dtos.CustomerDTO;
import org.example.ebankingbackend.dtos.SavingBankAccountDTO;
import org.example.ebankingbackend.entities.*;
import org.example.ebankingbackend.repositories.AccountOperationRepository;
import org.example.ebankingbackend.enums.AccountStatus;
import org.example.ebankingbackend.enums.OperationType;
import org.example.ebankingbackend.exceptions.BalanceNotSufficientException;
import org.example.ebankingbackend.exceptions.CustomerNotFoundException;
import org.example.ebankingbackend.repositories.BankAccountRepository;
import org.example.ebankingbackend.repositories.CustomerRepository;
import org.example.ebankingbackend.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbankingBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BankAccountService bankAccountService) {
		return args -> {
			Stream.of("Wiam","Yassine", "Houda").forEach(name -> {
				CustomerDTO customer = new CustomerDTO();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				bankAccountService.saveCustomer(customer);
			});
			//pour chaque customer on va creer un compte courant et un compte epargne
			bankAccountService.listCustomer().forEach(cust ->{
				try {
					bankAccountService.saveCurrentBankAccount(Math.random()*10000,1000, cust.getId());
					bankAccountService.saveSavingBankAccount(Math.random()*10000,5.5, cust.getId());


				} catch (CustomerNotFoundException e) {
					e.printStackTrace();
				}
			});
			List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
			for (BankAccountDTO bankAccount : bankAccounts){
				for (int i = 0; i < 10; i++) {
					String accountId;
					if (bankAccount instanceof SavingBankAccountDTO){
						accountId= ((SavingBankAccountDTO) bankAccount).getId();
					}else {
						accountId = ((CurrentBankAccountDTO) bankAccount).getId();
					}
					//bankAccountService.credit(bankAccount.getId(),Math.random()*1000,"Credit");
					bankAccountService.credit(accountId,Math.random()*1000,"Credit");
					bankAccountService.debit(accountId,Math.random()*1000,"Debit");
				}
			}
		};
	}


/*	@Bean
	CommandLineRunner commandLineRunner(BankService bankService) {
		return args -> {
			bankService.consulter();
		};
	}*/


	// premiere partie du test
//@Bean
CommandLineRunner start(CustomerRepository customerRepository,
						BankAccountRepository bankAccountRepository,
						AccountOperationRepository accountOperationRepository) {
	return args -> {
		Stream.of("Wiam","Yassine", "Houda").forEach(name -> {
			Customer customer = new Customer();
			customer.setName(name);
			customer.setEmail(name+"@gmail.com");
			customerRepository.save(customer);
		});
		customerRepository.findAll().forEach(cust ->{
			//creation des comptes courants
			CurrentAccount currentAccount = new CurrentAccount();
			currentAccount.setId(UUID.randomUUID().toString());
			currentAccount.setBalance(Math.random()*10000);
			currentAccount.setCreatedAt(new Date());
			currentAccount.setStatus(AccountStatus.CREATED);
			currentAccount.setOverDraft(1000);
			currentAccount.setCustomer(cust);
			bankAccountRepository.save(currentAccount);

			SavingAccount savingAccount = new SavingAccount();
			//parcequ'on a Id de type String
			savingAccount.setId(UUID.randomUUID().toString());
			savingAccount.setBalance(Math.random()*10000);
			savingAccount.setCreatedAt(new Date());
			savingAccount.setStatus(AccountStatus.CREATED);
			savingAccount.setInterestRate(5.5);
			savingAccount.setCustomer(cust);
			bankAccountRepository.save(savingAccount);
		});
		bankAccountRepository.findAll().forEach(acc ->{
			for (int i = 0; i < 10; i++) {
				AccountOperation accountOperation = new AccountOperation();
				accountOperation.setAmount(Math.random()*1000);
				accountOperation.setOperationDate(new Date());
				accountOperation.setType(Math.random()>0.5? OperationType.DEBIT :OperationType.CREDIT);
				accountOperation.setBankAccount(acc);
				accountOperationRepository.save(accountOperation);
			}
				}

		);
	};
}
}