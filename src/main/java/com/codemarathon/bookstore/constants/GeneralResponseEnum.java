package com.codemarathon.bookstore.constants;

import lombok.Getter;

@Getter
public enum GeneralResponseEnum {

    SUCCESS("000","SUCCESS","success"),
    UPDATED("00x","UPDATED","Updated successfully"),
    FAILED("900","FAILED","Operation failed"),
    REGISTRATION("000","REGISTRATION SUCCESS","Please Proceed to login"),

    LOGIN("000","LOGIN SUCCESSFUL","Login was Successful");

    private final String code;
    private final String Status;
    private final String message;

    GeneralResponseEnum(String code, String status, String message){
        this.code= code;
        this.Status = status;
        this.message=message;
    }
}
