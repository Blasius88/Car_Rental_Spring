package com.Car_Rental.util.convert.user;

import com.Car_Rental.controller.requests.user.UserCreateRequest;
import com.Car_Rental.entity.Roles;
import com.Car_Rental.entity.User;
import com.Car_Rental.exceptions.ConversionException;
import com.Car_Rental.exceptions.EntityNotFoundException;
import com.Car_Rental.exceptions.ArgumentOfMethodNotValidException;
import com.Car_Rental.util.convert.EntityConverter;

import javax.persistence.NoResultException;

public abstract class UserRequestConverter<S, T> extends EntityConverter<S, T> {

    private static final Roles DEFAULT_ROLE =
            Roles.builder()
                    .id_roles(2L)
                    .nameRoles("ROLE_USER")
                    .build();

    protected User doConvert(User user, UserCreateRequest request) {

        user.setFirstName(request.getUserFirstName());
        user.setLastName(request.getUserLastName());
        user.setLogin(request.getLogin());
        user.setUserCreated(request.getCreated());
        user.setPassword(request.getPassword());
        user.setUserEmail(request.getEmail());
        user.setUserPhone(request.getPhone());
        user.setUserCity(request.getCity());
        return user;
    }

    Roles findRole(Class<?> sClass, int idRole){
        Roles roles;
        try{
            roles = entityManager.createQuery("select r from Roles r where r.id_roles =:name", Roles.class)
                    .setParameter("name", idRole)
                    .getSingleResult();
        }catch (NumberFormatException e) {
            throw new ConversionException(sClass, User.class, idRole, new ArgumentOfMethodNotValidException(Roles.class,idRole));
        } catch (NoResultException e) {
            throw new ConversionException (sClass, User.class, idRole,
                    new EntityNotFoundException(" name = " + idRole, Roles.class));
        }
        return roles;
    }
}
