package org.example.ebankingbackend.services;

import jakarta.transaction.Transactional;
import org.example.ebankingbackend.entities.BankAccount;
import org.example.ebankingbackend.entities.CurrentAccount;
import org.example.ebankingbackend.entities.SavingAccount;
import org.example.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount =
                bankAccountRepository.findById("978e2b77-c4da-4646-8419-e3c50e0e6585").get();
        if (bankAccount != null){
            System.out.println("-----------------");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());

            if (bankAccount instanceof CurrentAccount){
                System.out.println("Overdraft: "+((CurrentAccount) bankAccount).getOverDraft());}
            else if (bankAccount instanceof SavingAccount){
                System.out.println("Interest Rate: "+((SavingAccount) bankAccount).getInterestRate());
            }
            bankAccount.getAccountOperations().forEach(op ->{
                System.out.println(op.getType()+" \t "+op.getOperationDate() +"\t"+op.getAmount());
            });
        }


    }
}
