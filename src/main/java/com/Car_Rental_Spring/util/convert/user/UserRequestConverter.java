package com.Car_Rental_Spring.util.convert.user;

import com.Car_Rental_Spring.entity.MRoles;
import com.Car_Rental_Spring.exceptions.ArgumentOfMethodNotValidException;
import com.Car_Rental_Spring.exceptions.ConversionException;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import com.Car_Rental_Spring.util.convert.EntityConverter;
import com.Car_Rental_Spring.controller.requests.user.UserCreateRequest;
import com.Car_Rental_Spring.entity.User;

import javax.persistence.NoResultException;

public abstract class UserRequestConverter<S, T> extends EntityConverter<S, T> {

    protected User doConvert(User user, UserCreateRequest request) {

        user.setFirstName(request.getUserFirstName());
        user.setLastName(request.getUserLastName());
        user.setUserLogin(request.getLogin());
        user.setUserCreated(request.getCreated());
        user.setUserPass(request.getPassword());
        user.setUserEmail(request.getEmail());
        user.setUserPhone(request.getPhone());
        user.setUserCity(request.getCity());
        return user;
    }

    MRoles findRole(Class<?> sClass, int idRole){
        MRoles roles;
        try{
            roles = entityManager.createQuery("select r from MRoles r where r.id_roles =:name", MRoles.class)
                    .setParameter("name", idRole)
                    .getSingleResult();
        }catch (NumberFormatException e) {
            throw new ConversionException(sClass, User.class, idRole, new ArgumentOfMethodNotValidException(MRoles.class,idRole));
        } catch (NoResultException e) {
            throw new ConversionException (sClass, User.class, idRole,
                    new EntityNotFoundException(" name = " + idRole, MRoles.class));
        }
        return roles;
    }
}
