package org.example.ebankingbackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ebankingbackend.entities.BankAccount;

import java.util.List;

@Data
public class CustomerDTO {
    // je travaille seuelement par les attributs qui m'interessent
    private Long id;
    private String name;
    private String email;

}
