package com.dkupadhy.hospitalManagement.dto;

import com.dkupadhy.hospitalManagement.entity.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodGroupCountResponseEntity {

    private BloodGroupType bloodGroupType;
    private Long count;
}
