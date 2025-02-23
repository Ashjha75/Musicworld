package com.example.springcommerce.entity;

import com.example.springcommerce.utils.Enums.AppRoles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
public class roleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    //    as default Enum is Integer in Database
    @ToString.Exclude
    @Enumerated(EnumType.STRING)
    @Column(length = 20, name = "role_name")
    private AppRoles roleName;

    public roleEntity(AppRoles role) {
        this.roleName = role;
    }
}
