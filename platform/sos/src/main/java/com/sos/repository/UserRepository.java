package com.sos.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.domain.sos.User;

/**
* @ClassName: UserRepository
* @Description: 
* @date 2018年11月14日
*
*/
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User>{
	
	User findAccountAndPassword(String accout, String password);

}
