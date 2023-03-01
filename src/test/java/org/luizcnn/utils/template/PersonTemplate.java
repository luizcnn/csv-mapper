package org.luizcnn.utils.template;

import lombok.experimental.UtilityClass;
import org.luizcnn.testmodel.Person;

import java.math.BigDecimal;
import java.util.UUID;

@UtilityClass
public final class PersonTemplate {

  public static Person buildPerson(String name, Integer age, BigDecimal balance, boolean isRegistered) {
    return Person.builder()
            .id(UUID.nameUUIDFromBytes(name.getBytes()))
            .name(name)
            .email(name.concat("@email.com"))
            .age(age)
            .isRegistered(isRegistered)
            .balance(balance)
            .build();
  }

}
