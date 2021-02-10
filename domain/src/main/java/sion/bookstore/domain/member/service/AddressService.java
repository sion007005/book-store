package sion.bookstore.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.member.repository.Address;
import sion.bookstore.domain.member.repository.AddressRepository;
import sion.bookstore.domain.member.repository.Member;

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

    public void create(Member member) {
        List<Address> addressList = member.getAddressList();
        for (Address address : addressList) {
            address.setMemberId(member.getId());
            address.setCreatedAt(new Date());
            address.setCreatedBy(member.getEmail());
            address.setModifiedAt(new Date());
            address.setModifiedBy(member.getEmail());
            address.setDeleted(false);

            addressRepository.create(address);
        }
    }

    public Long delete(Address address) {
        address.setModifiedAt(new Date());
        address.setModifiedBy(UserContext.get().getUserEmail());
        address.setDeleted(true);

        return addressRepository.update(address);
    }

    public void update(Member member) {
        List<Address> existingAddressList = findAllByMemberId(member.getId());
        if (!existingAddressList.isEmpty()) {
            for (Address address : existingAddressList) {
                delete(address);
            }
        }

        create(member);
    }

    public Address findOne(Long memberId, String name) {
        return addressRepository.findOne(memberId, name);
    }

    public List<Address> findAllByMemberId(Long memberId) {
        return addressRepository.findAllByMemberId(memberId);
    }
}
