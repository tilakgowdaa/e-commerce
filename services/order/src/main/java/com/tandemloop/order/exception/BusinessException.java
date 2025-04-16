package com.tandemloop.order.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    public final String msg;
}
