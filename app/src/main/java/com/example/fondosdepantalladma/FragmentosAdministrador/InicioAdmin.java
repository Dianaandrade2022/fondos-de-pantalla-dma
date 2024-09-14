package com.example.fondosdepantalladma.FragmentosAdministrador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fondosdepantalladma.R;

import java.util.ArrayList;
import java.util.List;


public class InicioAdmin extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_inicio_admin, container, false);
        RecyclerView recyclerView2 = vista.findViewById(R.id.recyclerViewProductos);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView2.setLayoutManager(gridLayoutManager);



        List<Producto> listaProductos = obtenerListaProducto();
        ProductoAdapter adapter = new ProductoAdapter(listaProductos);
        recyclerView2.setAdapter(adapter);
        return vista;


    }
    private List<Producto> obtenerListaProducto() {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("producto 1", R.drawable.product));
        productos.add(new Producto("producto 2", R.drawable.product));
        productos.add(new Producto("producto 3", R.drawable.product));
        productos.add(new Producto("producto 4", R.drawable.product));
        productos.add(new Producto("producto 5", R.drawable.product));

        return productos;
    }
}

