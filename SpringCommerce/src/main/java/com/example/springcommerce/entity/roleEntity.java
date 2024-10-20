package com.example.springcommerce.entity;

import com.example.springcommerce.utils.Enums.AppRoles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class roleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    //    as default Enum is Integer in Database
    @Enumerated(EnumType.STRING)
    @Column(length = 20, name = "role_name")
    private AppRoles role;

    public roleEntity(AppRoles role) {
        this.role = role;
    }
}
