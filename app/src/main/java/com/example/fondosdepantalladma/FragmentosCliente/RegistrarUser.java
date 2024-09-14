package com.example.fondosdepantalladma.FragmentosCliente;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fondosdepantalladma.InicioSesion;
import com.example.fondosdepantalladma.MainActivity;
import com.example.fondosdepantalladma.MainActivityAdministrador;
import com.example.fondosdepantalladma.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class RegistrarUser extends Fragment {

    TextView FechaRegistro, IniciarSesion;
    EditText Correo, Password, Nombres, Password2, Edad;
    Button Registrar;

    FirebaseAuth auth;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_registrar_admin, container, false);

        FechaRegistro = vista.findViewById(R.id.FechaRegistro);
        Correo = vista.findViewById(R.id.Correo);
        Password = vista.findViewById(R.id.Password);
        Password2 = vista.findViewById(R.id.Password2);
        Nombres = vista.findViewById(R.id.Nombres);
        Edad = vista.findViewById(R.id.Edad);
        IniciarSesion = vista.findViewById(R.id.Login);
        Registrar = vista.findViewById(R.id.Registrar);

        auth = FirebaseAuth.getInstance(); // Inicializando Firebase Authentication

        Date date = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("d 'de' MMMM 'del' yyyy"); // 27 de Junio del 2024
        String SFecha = fecha.format(date); // Convertir fecha a un string
        FechaRegistro.setText(SFecha);

        // Al dar clic en registrar
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Convertimos a string EditText: Correo y Password
                String correo = Correo.getText().toString();
                String pass = Password.getText().toString();
                String pass2 = Password2.getText().toString();
                String nombre = Nombres.getText().toString();
                String edad = Edad.getText().toString();

                if (!pass.equals(pass2)) {
                    Toast.makeText(getActivity(), "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (correo.equals("") || pass.equals("") || nombre.equals("") || edad.equals("")) {
                    Toast.makeText(getActivity(), "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // validacion de correo
                    if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                        Correo.setError("Correo Invalido!");
                        Correo.setFocusable(true);
                    } else if (pass.length() < 6) {
                        Password.setError("La contraseña debe ser mayor a 6 caracteres");
                        Password.setFocusable(true);
                    } else {
                        RegistroUser(correo, pass);
                    }
                }
            }
        });

        // Redirigir a InicioSesion cuando se hace clic en el TextView
        IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InicioSesion.class);
                startActivity(intent);
            }
        });

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Registrando, espere por favor");
        progressDialog.setCancelable(false);
        return vista;
    }

    // Metodo para registrar Administradores
    private void RegistroUser(String correo, String pass) {
        progressDialog.show();
        auth.createUserWithEmailAndPassword(correo, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Si el administrador fue creado correctamente
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            FirebaseUser user = auth.getCurrentUser();
                            assert user != null;

                            // Convertir a cadena los datos
                            String UID = user.getUid();
                            String correo = Correo.getText().toString();
                            String pass = Password.getText().toString();
                            String nombre = Nombres.getText().toString();
                            String edad = Edad.getText().toString();
                            int EdadInt = Integer.parseInt(edad);

                            HashMap<Object, Object> Usuario = new HashMap<>();

                            Usuario.put("UID", UID);
                            Usuario.put("CORREO", correo);
                            Usuario.put("PASSWORD", pass);
                            Usuario.put("NOMBRES", nombre);
                            Usuario.put("EDAD", EdadInt);
                            Usuario.put("IMAGEN", "");

                            // Inicializar FirebaseDatabase
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference = database.getReference("BASE DE DATOS");
                            reference.child(UID).setValue(Usuario);
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
