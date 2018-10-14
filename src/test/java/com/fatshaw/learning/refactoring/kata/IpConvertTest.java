package com.fatshaw.learning.refactoring.kata;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IpConvertTest {

    @Test
    public void should_return_zero_when_ip_address_is_null() {
        assertEquals(0, IpConvert.convertIpToLong(null));
    }


    @Test
    public void should_return_zero_when_ip_address_is_empty() {
        assertEquals(0, IpConvert.convertIpToLong(""));
    }

    @Test
    public void should_return_zero_when_ip_address_zero() {
        assertEquals(0, IpConvert.convertIpToLong("0.0.0.0"));
    }

    @Test
    public void should_return_2896692481_when_ip_address_is_172_168_5_1() {
        assertEquals(2896692481L, IpConvert.convertIpToLong("172.168.5.1"));
    }

    @Test
    public void should_return_2896692482_when_ip_address_is_172__168_5_2() {
        assertEquals(2896692482L, IpConvert.convertIpToLong("172 .168.5.2"));
    }

    @Test
    public void should_return_2896692482_when_ip_address_172_168_5_2_with_multiple_spaces() {
        assertEquals(2896692482L, IpConvert.convertIpToLong("172 .    168.  5. 2"));
    }

    @Test
    public void should_return_2896692482_when_ip_address_172_168_5_2_with_spaces_in_the_end() {
        assertEquals(2896692482L, IpConvert.convertIpToLong("172 .    168.5.2  "));
    }

    @Test
    public void should_return_3221225473L_when_ip_address_192_0_0_1() {
        assertEquals(3221225473L, IpConvert.convertIpToLong("192.0.0.1"));
    }

    @Test
    public void should_return_successful_when_ip_address_is_255_255_255_255() {
        long result = (255L << 24) + (255L << 16) + (255L << 8) + 255L;
        assertEquals(result, IpConvert.convertIpToLong("255.255.255.255"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_fail_when_spaces_exist_between_numbers() {
        IpConvert.convertIpToLong("1  72.168.5.2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_fail_when_ip_address_is_greater_than_255() {
        IpConvert.convertIpToLong("256.257.255.255");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_fail_when_ip_address_has_invalid_char() {
        IpConvert.convertIpToLong("123.12.12.a");
    }

}