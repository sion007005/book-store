package sion.bookstore.domain.member.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AddressRepository {
    Integer create(Address address);
    Address findOne(Integer memberId, String name);
//    List<Address> findAllByCondition( id);
}
