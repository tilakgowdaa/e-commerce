package com.tandemloop.product.exception;

import lombok.Data;

@Data
public class ProductPurchaseException extends RuntimeException {

    public final String msg;

}
