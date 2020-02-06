package com.Car_Rental_Spring.domain;

import java.util.Objects;

public class m_roles {
    private Long id_roles;
    private String Name_roles;

    public m_roles(){}

    public m_roles(Long id_roles, String name_roles) {
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
        m_roles m_roles = (m_roles) o;
        return Objects.equals(id_roles, m_roles.id_roles) &&
                Objects.equals(Name_roles, m_roles.Name_roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_roles, Name_roles);
    }
}
