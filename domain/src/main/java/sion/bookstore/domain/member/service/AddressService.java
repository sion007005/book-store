package sion.bookstore.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.member.repository.Address;
import sion.bookstore.domain.member.repository.AddressRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public Long create(Address address) {
        address.setCreatedAt(new Date());
        address.setCreatedBy(UserContext.get().getUserEmail());
        address.setModifiedAt(new Date());
        address.setModifiedBy(UserContext.get().getUserEmail());
        address.setDeleted(false);

        return addressRepository.create(address);
    }

    public Long update(Address address) {
        address.setModifiedAt(new Date());
        address.setModifiedBy(UserContext.get().getUserEmail());

        return addressRepository.update(address);
    }

    public Address findOne(Integer memberId, String name) {
        return addressRepository.findOne(memberId, name);
    }

    public List<Address> findAllByMemberId(Long memberId) {
        return addressRepository.findAllByMemberId(memberId);
    }
}
