package com.sos.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.domain.sos.User;

/**
* @ClassName: LoginRepository
* @Description: 
* @date 2018年11月15日
*
*/
@Repository
public interface LoginRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User>{

	User findByAccoutAndPassword(String accout, String password);
}
