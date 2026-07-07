package com.ikenobo.ecommerce.exception;

public class ProductoNoEncontradoException
    extends RuntimeException {

        public ProductoNoEncontradoException(
            String mensaje) {

        super(mensaje);
    }
}