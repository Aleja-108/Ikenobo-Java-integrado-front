package com.ikenobo.ecommerce.exception;

public class PedidoVacioException
    extends RuntimeException {

        public PedidoVacioException(
            String mensaje) {

        super(mensaje);
    }
}
