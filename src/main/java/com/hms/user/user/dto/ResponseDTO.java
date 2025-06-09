package com.hms.user.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    String message;

    //all args constructor for JPA
    public ResponseDTO(String message) {
        this.message = message;
    }
    //no args constructor for JPA
    public ResponseDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
