package com.ikenobo.ecommerce.exception;

public class CategoriaNoEncontradaException
    extends RuntimeException {
    
        public CategoriaNoEncontradaException(
            String mensaje) {
            
        super(mensaje);
    }
    
}