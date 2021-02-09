package sion.bookstore.domain.member.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AddressRepository {
    Long create(Address address);
    Long update(Address address);
    Address findOne(Integer memberId, String name);
    List<Address> findAllByMemberId(Long memberId);
}
