package com.Car_Rental_Spring.domain;

import java.util.Objects;

public class MRoles {
    private Long id_roles;
    private String Name_roles;

    public MRoles(){}

    public MRoles(Long id_roles, String name_roles) {
        this.id_roles = id_roles;
        Name_roles = name_roles;
    }

    public Long getId_roles() {
        return id_roles;
    }

    public void setId_roles(Long id_roles) {
        this.id_roles = id_roles;
    }

    public String getName_roles() {
        return Name_roles;
    }

    public void setName_roles(String name_roles) {
        Name_roles = name_roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MRoles MRoles = (MRoles) o;
        return Objects.equals(id_roles, MRoles.id_roles) &&
                Objects.equals(Name_roles, MRoles.Name_roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_roles, Name_roles);
    }
}
