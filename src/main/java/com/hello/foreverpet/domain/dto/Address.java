package com.hello.foreverpet.domain.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// @Embedded 어노테이션과 함께 Address 타입으로 사용하시면 됩니다.
// 코드의 재사용성 측면에서 자주 사용되는 Address 를 클래스로 묶어 관리할수 있습니다.
@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
