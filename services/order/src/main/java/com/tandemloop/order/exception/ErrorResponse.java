package com.tandemloop.order.exception;

import java.util.Map;

public record ErrorResponse(
     Map<String,String> error
){

}
