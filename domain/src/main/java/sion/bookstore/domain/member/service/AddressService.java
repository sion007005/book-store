package sion.bookstore.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.BaseAuditor;
import sion.bookstore.domain.member.repository.Address;
import sion.bookstore.domain.member.repository.AddressRepository;
import sion.bookstore.domain.member.repository.Member;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public Long create(Address address) {
        BaseAuditor.setCreationInfo(address);
        return addressRepository.create(address);
    }

    public void create(Member member) {
        List<Address> addressList = member.getAddressList();
        for (Address address : addressList) {
            address.setMemberId(member.getId());
            BaseAuditor.setCreationInfo(address);

            addressRepository.create(address);
        }
    }

    public Long delete(Address address) {
        BaseAuditor.setDeletionInfo(address);
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

    public Address findDefaultAddress(Long memberId) {
        return addressRepository.findDefaultAddress(memberId);
    }
}
