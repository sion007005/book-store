package sion.bookstore.domain.member.repository;

import java.util.Date;

public class AddressMock {
    public static Address getAddress(String name) {
        Address address = new Address();
        address.setMemberId(1);
        address.setName(name);
        address.setAddressBasic("testbasic");
        address.setAddressDetail("testdetail");
        address.setZipCode(12345);
        address.setCreatedAt(new Date());
        address.setCreatedBy("test");
        address.setModifiedAt(new Date());
        address.setModifiedBy("test");

        return address;
    }
}
