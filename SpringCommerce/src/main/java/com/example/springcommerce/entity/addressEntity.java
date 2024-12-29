package com.example.springcommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "address")
@Getter
@Setter
@Data
@NoArgsConstructor
public class addressEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 3, message = "Street name must contains atleast 3 Characters")
    private String street;

    @NotBlank
    @Size(min = 3, message = "Building name must contains atleast 3 Characters")
    private String buildingName;

    @NotBlank
    @Size(min = 3, message = "City name must contains atleast 3 Characters")
    private String city;

    @NotBlank
    @Size(min = 3, message = "State name must contains atleast 3 Characters")
    private String state;

    @NotBlank
    @Size(min = 3, message = "Country name must contains atleast 3 Characters")
    private String country;

    @NotBlank
    @Size(min = 6, message = "Pin code must contains atleast 3 Characters")
    private String pinCode;


    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private userEntity user;

    public addressEntity(String pinCode, String country, String state, String city, String buildingName, String street) {
        this.pinCode = pinCode;
        this.country = country;
        this.state = state;
        this.city = city;
        this.buildingName = buildingName;
        this.street = street;
    }

}
