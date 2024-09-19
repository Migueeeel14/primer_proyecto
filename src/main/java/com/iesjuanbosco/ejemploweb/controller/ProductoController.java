package com.iesjuanbosco.ejemploweb.controller;

import com.iesjuanbosco.ejemploweb.entity.Producto;
import com.iesjuanbosco.ejemploweb.repository.ProductoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller //anotación que le indica a Spring que esta clase es un controlador
public class ProductoController {

    //Para acceder al repositorio creamos una propiedad y la asignamos en el constructor
    private ProductoRepository productoRepository;

    public ProductoController(ProductoRepository repository){
        this.productoRepository = repository;
    }

    /* Con la anotación GetMapping le indicamos a Spring que el siguiente metodo
       se va a ejecutar cuando el usuario acceda a la URL https://localhost/productos */
    @GetMapping("/productos")
    //Metodo findAll();
    public String findAll(Model model){
        List<Producto> productos = this.productoRepository.findAll();

        //model
        //Pasamos los datos a la vista
        model.addAttribute("productos",productos);
        //product-list se encuentra en resources/templates como un html.
        return "producto-list";
    }

    //Metodo par añadir productos a la lista de productos que crearemos como ArrayList
    @GetMapping("/productos/add")
    public String add(){
        //Lista de productos
        List<Producto> productos = new ArrayList<Producto>();
        //Añadimos los atributos de cada producto
        Producto p1 = new Producto(null, "producto 1",20,45.5);
        Producto p2 = new Producto(null, "producto 2",50,5.0);
        Producto p3 = new Producto(null, "producto 3",30,50.5);
        Producto p4 = new Producto(null, "producto 4",10,30.0);
        //Los añadimos a la lista de productos con .add
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);

        //guarda el array en la base de datos gracias al repositorio
        this.productoRepository.saveAll(productos);

        //Redirige al controlador /productos
        return "redirect:/productos";
    }

    @GetMapping("/productos2") //Anotacion que indica la URL localhost:8080/ meiante get
    @ResponseBody //Anotacion que indica que no pase por el motor de la plantilla thymeleaf sino que voy a devolver el HTML directamente
    public String index(){
        List<Producto> productos = this.productoRepository.findAll();
        StringBuilder HTML = new StringBuilder("<html><body>");
        productos.forEach(producto -> {
            HTML.append("<p>" + producto.getTitulo() + "</p>");
        });
        HTML.append("</body></html>");

        return HTML.toString();
    }

    /*@PostMapping("/productos")
    public String addProducto(){
        return "producto-add";
    }*/
}
