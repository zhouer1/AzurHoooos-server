package com.zhouer.azurhoooos.beans;

import com.zhouer.azurhoooos.entity.RegistrationRecord;
import lombok.Data;

import java.util.List;

@Data
public class RegiPageResult {
    private Integer total;
    private List<RegistrationRecord> registrationRecordList;
}
