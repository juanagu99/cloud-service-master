package co.com.poli.servicebooking.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private Object data;
    private Integer status;
}
