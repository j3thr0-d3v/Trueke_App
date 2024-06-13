package com.j3thr0.trueke.truekeapi.dto;

import com.j3thr0.trueke.truekeapi.model.Association;
import lombok.Builder;

import java.util.UUID;

public interface AssociationDTO {

    public record UpdateAssociationRequest(
            String name,
            String address,
            String mission
    ){}

    @Builder
    public record AssociationResponse(
            UUID id,
            String name,
            String address,
            String mission,
            String cif,
            String banner,
            String logo

    ){
        public static AssociationResponse of(Association association){
            return AssociationResponse.builder()
                    .id(association.getId())
                    .name(association.getName())
                    .address(association.getAddress())
                    .mission(association.getMission())
                    .cif(association.getCif())
                    .banner(association.getBannerImg())
                    .logo(association.getLogoImg())
                    .build();
        }
    }
}
