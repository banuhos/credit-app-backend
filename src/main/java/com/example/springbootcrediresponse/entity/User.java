package com.example.springbootcrediresponse.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Users")
public class User {

    @Id
    private String id;
    private String name;
    private String surname;
    private String identificationNumber;
    private String phoneNumber;
    private double mounthlyIncome;
    private double credit;

    private User(Builder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.identificationNumber = builder.identificationNumber;
        this.phoneNumber = builder.phoneNumber;
        this.mounthlyIncome = builder.mounthlyIncome;
        this.credit = builder.credit;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String surname;
        private String identificationNumber;
        private String phoneNumber;
        private double mounthlyIncome;
        private double credit;

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }
        public Builder identificationNumber(String identificationNumber) {
            this.identificationNumber = identificationNumber;
            return this;
        }
        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder mounthlyIncome(double mounthlyIncome) {
            this.mounthlyIncome = mounthlyIncome;
            return this;
        }
        public Builder credit(double credit) {
            this.credit = credit;
            return this;
        }

        public User build() {
            User build = new User(this);
            return build;
        }
    }
}
