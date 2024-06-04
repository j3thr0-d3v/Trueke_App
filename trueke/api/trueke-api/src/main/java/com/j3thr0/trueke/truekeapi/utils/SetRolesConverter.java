package com.j3thr0.trueke.truekeapi.utils;

import com.j3thr0.trueke.truekeapi.model.enums.UserRoles;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class SetRolesConverter implements AttributeConverter<Set<UserRoles>, String> {

    private final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(Set<UserRoles> userRoles) {
        return !userRoles.isEmpty() ?
                userRoles.stream()
                        .map(UserRoles::name)
                        .collect(Collectors.joining(SEPARATOR)) : null;
    }

    @Override
    public Set<UserRoles> convertToEntityAttribute(String userRoles) {
        return userRoles != null && !userRoles.isBlank() ?
                Arrays.stream(userRoles.split(SEPARATOR))
                        .map(UserRoles::valueOf)
                        .collect(Collectors.toCollection(HashSet::new))
                : new HashSet<UserRoles>();
    }
}
