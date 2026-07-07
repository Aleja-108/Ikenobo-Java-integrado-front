package com.ikenobo.ecommerce.exception;

public class PrecioInvalidoException 
    extends RuntimeException {
        
        public PrecioInvalidoException(
            String mensaje) {
            
        super(mensaje);
        }
}
