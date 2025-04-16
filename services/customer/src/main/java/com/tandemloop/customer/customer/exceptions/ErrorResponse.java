package com.tandemloop.customer.customer.exceptions;

import java.util.Map;

public record ErrorResponse (
     Map<String,String> error
){

}
