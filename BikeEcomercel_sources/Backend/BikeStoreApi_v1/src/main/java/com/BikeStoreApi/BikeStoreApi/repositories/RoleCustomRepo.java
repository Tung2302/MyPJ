package com.BikeStoreApi.BikeStoreApi.repositories;

import com.BikeStoreApi.BikeStoreApi.entities.Role;
import com.BikeStoreApi.BikeStoreApi.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Transformer;
import java.util.List;

@Repository
public class RoleCustomRepo {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Role> getRole(User user){
        StringBuilder sql = new StringBuilder()
                .append("SELECT r.name AS name FROM user u JOIN users_roles ur ON u.user_id = ur.user_id\n " +
                        "JOIN roles r ON r.id = ur.role_id");
        sql.append("where 1=1");
        if(user.getEmail()!=null){
            sql.append(" and email = :email");
        }

        NativeQuery<Role> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());
        if(user.getEmail()!=null){
            query.setParameter("email",user.getEmail());
        }

        query.addScalar("name", StandardBasicTypes.STRING);
        query.setResultListTransformer(Transformers.aliasToBean(Role.class));
        return query.list();
    }

}
