package com.bycasseb.jpql.repository;

import com.bycasseb.jpql.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author felipe-casseb
 * @version : $<br/>
 * : $
 * @since 18/09/19 16:23
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query(
      "Select p from Person p where p.address.postcode = :postcode"
    )
    public List<Person> findWithJpqlImplicity(@Param("postcode") String postcode);

    @Query(
            "Select p from Person p left join p.address a where a.postcode = :postcode"
    )
    public List<Person> findWithJpqlExplicity(@Param("postcode") String postcode);

    @Query(
            "Select p from Person p " +
                    "where p.address.postcode = :postcode " +
                    "and p.homeAddress.postcode = p.businessAddress.postcode"
    )
    public List<Person> findWithComplexJpqlImplicity(@Param("postcode") String postcode);

    @Query(
            "Select p from Person p " +
                    "left join p.address a " +
                    "left join p.homeAddress home " +
                    "left join p.businessAddress business " +
                    "where a.postcode = :postcode " +
                    "and home.postcode = business.postcode"
    )
    public List<Person> findWithComplexJpqlExplicity(@Param("postcode") String postcode);

}
