package com.zhouer.azurhoooos.beans;

import com.zhouer.azurhoooos.entity.RegistrationRecord;
import lombok.Data;

import java.util.List;
@Data
public class RegistrationResult {
    private String msg;
    private boolean isSuccess;
    private String statusCode;
    private List<RegistrationRecord> registrationRecords;
}
