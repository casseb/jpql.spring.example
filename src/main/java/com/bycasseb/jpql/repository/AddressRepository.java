package com.bycasseb.jpql.repository;

import com.bycasseb.jpql.model.Address;
import org.springframework.data.repository.CrudRepository;

/**
 * @author felipe-casseb
 * @version : $<br/>
 * : $
 * @since 18/09/19 17:25
 */
public interface AddressRepository extends CrudRepository<Address, Long> {
}
