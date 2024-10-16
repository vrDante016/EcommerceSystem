package com.produtos.Produtos.exceptions;

public class PriceNotValidException extends RuntimeException{

    public PriceNotValidException(String message){
        super(message);
    }

}
