package com.iesjuanbosco.ejemploweb.controller;

import com.iesjuanbosco.ejemploweb.entity.Producto;
import com.iesjuanbosco.ejemploweb.repository.ProductoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /*Borra un producto a partir del id de la ruta*/
    @GetMapping("/productos/del/{id}")
    public String delete(@PathVariable long id){
        //Borrar el producto usando el repositorio
        productoRepository.deleteById(id);
        //Redirigir a /productos
        return "redirect:/productos";
    }

    //Muestra un producto a partir del id de la ruta
    @GetMapping("/productos/view/{id}")
    public String view(@PathVariable Long id, Model model){
        //Obtenemos el producto de la BD a partir del id de la barra de direcciones
        Optional producto = productoRepository.findById(id);
        if(producto.isPresent()){
            //Mandamos el producto a la vista
            model.addAttribute("producto",producto.get());
            return "producto-view";
        }
        else{
            return "redirect:/productos";
        }
    }

    /*Editar un producto a partir del id de la ruta*/
    @GetMapping("/productos/edit/{id}")
    public String edit(@PathVariable long id, Model model){
        Producto producto = productoRepository.findById(id).orElse(null);
        if(producto==null){
            return "redirect:/productos";
        }
        else{
            model.addAttribute("producto", producto);
        }
        //Redirigir a /productos
        return "producto-edit";
    }

    //Muestra los cambios una vez se envian
    @PostMapping("/productos/update")
    public String update(Producto producto) {
        // Guardar el producto actualizado
        productoRepository.save(producto);

        // Redirigir a la lista de productos
        return "redirect:/productos";
    }

    //Cuando entras a la ruta muestra esta vista
    @GetMapping("/productos/new")
    public String newProducto(Model model){
        model.addAttribute("producto", new Producto());
        return "producto-new";
    }

    //Lo que se muestra cuando se envia la vista
    @PostMapping("/productos/new")
    public String newProductoInsert(Producto producto){
        //Insertamos los dtos en la BD
        productoRepository.save(producto);

        //Redirigimos a /productos
        return "redirect:/productos";
    }
}
