package com.ikenobo.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import com.ikenobo.ecommerce.model.Categoria;
import com.ikenobo.ecommerce.model.Producto;
import com.ikenobo.ecommerce.service.ProductoService;
import com.ikenobo.ecommerce.service.CategoriaService;
//import com.ikenobo.ecommerce.service.ProductoService;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	CommandLineRunner cargarDatos(ProductoService productoService, CategoriaService categoriaService) {
		return (args) -> {
			if (categoriaService.listarTodas().isEmpty()) {
				Categoria gifts = categoriaService.guardar(new Categoria("Gifts", "Regalos especiales"));
				Categoria deco = categoriaService.guardar(new Categoria("Deco", "Decoración"));
				Categoria aniversario = categoriaService.guardar(new Categoria("Aniversario", "Presentes extraordinarios"));

				//GIFTS
				//productoService.guardar(new Producto("Ramo de rosas rojas", 15000, 10, gifts));
				//productoService.guardar(new Producto("Centro de mesa gerberas", 22000, 5, deco));
				//productoService.guardar(new Producto("Bouquet primaveral", 18000, 8, aniversario));
				productoService.guardar(new Producto(null,
                    "Lisiantus y eucaliptus", 150000, 10,
                    "https://res.cloudinary.com/ijwk8uzc/image/upload/f_auto,q_auto/arreglo_lisianthus_eucaliptus_nrcgok",
                    gifts));
                productoService.guardar(new Producto(null,
                    "Gerberas Ikebana", 220000, 5,
                    "https://res.cloudinary.com/ijwk8uzc/image/upload/v1783447297/gerberas__pisruf.jpg",
                    gifts));
                productoService.guardar(new Producto(null,
                    "Astromelias y base turquesa", 300000, 2,
                    "https://res.cloudinary.com/ijwk8uzc/image/upload/v1783447296/astromelias_j3t3r2.jpg", gifts));
			
			
				//DECO
				productoService.guardar(new Producto(null,
                    "Orquídeas amarillas Ikebana", 320000, 5,
                    "https://res.cloudinary.com/ijwk8uzc/image/upload/v1783447295/orqu%C3%ADdeas_ama_tgu22k.jpg",
                    deco));
                productoService.guardar(new Producto(null,
                    "Glebe Ikebana", 168000, 7,
                    "https://res.cloudinary.com/ijwk8uzc/image/upload/v1783447249/glebe_nw4yhs.jpg",
                    deco));
                productoService.guardar(new Producto(null,
                    "Arco Rosas y Peonías", 900000, 3,
                    "https://res.cloudinary.com/ijwk8uzc/image/upload/v1783447296/aro_rosas_peonias_hczzwu.jpg",
                    deco));
				productoService.guardar(new Producto(null,
                    "Alto de lisiantus y dracaena", 900000, 25,
                    "https://res.cloudinary.com/ijwk8uzc/image/upload/v1783447296/arreglo_alto_lisianthus_limonium_dracaena_dtj9h1.png",
                    deco));
				productoService.guardar(new Producto(null,
                    "Arreglo de clavelinas", 900000, 25,
                    "https://res.cloudinary.com/ijwk8uzc/image/upload/v1783447297/centro_clavelinas_lisianthus_sr5yoo.png",
                    deco));
				productoService.guardar(new Producto(null,
                    "Orquídeas rosas Ikebana", 900000, 5,
                    "https://res.cloudinary.com/ijwk8uzc/image/upload/v1783447248/orqu%C3%ADdeas_xfzcgd.jpg",
                    deco));

				//ANIVERSARIO
				productoService.guardar(new Producto(null,
                    "Rosas y Calas", 140000, 7,
                    "https://res.cloudinary.com/ijwk8uzc/image/upload/v1783447295/rosas_calas_bi7575.jpg",
                    aniversario));
                productoService.guardar(new Producto(null,
                    "Iris", 109000, 8,
                    "https://res.cloudinary.com/ijwk8uzc/image/upload/v1783447295/iris_xjsgve.jpg",
                    aniversario));
                productoService.guardar(new Producto(null,
                    "Gerberas rojas base de coco", 109000, 2,
                    "https://res.cloudinary.com/ijwk8uzc/image/upload/v1783447296/gerberas_rojas_kq9zas.jpg",
                    aniversario));
				}
							
			//service.guardar(new Producto("Ramo de rosas rojas", 15000, 10, "Gifts"));
			//service.guardar(new Producto("Centro de mesa gerberas", 22000, 5, "Deco"));
			//service.guardar(new Producto("Bouquet primaveral", 18000, 8, "Aniversario"));
		};
	}

}
