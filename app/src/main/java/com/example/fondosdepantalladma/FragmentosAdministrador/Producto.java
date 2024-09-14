package com.example.fondosdepantalladma.FragmentosAdministrador;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Producto extends RecyclerView.Adapter {
    private String producto;
    private int imagenResId;

    public Producto(String producto, int imagenResId) {
        this.producto = producto;
        this.imagenResId = imagenResId;
    }

    public String getProducto() {
        return producto;
    }

    public int getImagenResId() {
        return imagenResId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
