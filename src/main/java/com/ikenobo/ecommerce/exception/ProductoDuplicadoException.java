package com.ikenobo.ecommerce.exception;

public class ProductoDuplicadoException
    extends RuntimeException {

        public ProductoDuplicadoException(
            String mensaje) {

        super(mensaje);
    }
}