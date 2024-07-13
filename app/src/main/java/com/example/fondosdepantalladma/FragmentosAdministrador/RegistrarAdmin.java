package com.example.fondosdepantalladma.FragmentosAdministrador;

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


public class RegistrarAdmin extends Fragment {

    TextView FechaRegistro;
    EditText Correo,Password,Nombres,Apellidos,Edad;
    Button Registrar;

    FirebaseAuth auth;

    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//
        View view = inflater.inflate(R.layout.fragment_registrar_admin, container, false);

        FechaRegistro = view.findViewById(R.id.FechaRegistro);
        Correo = view.findViewById(R.id.Correo);
        Password = view.findViewById(R.id.Password);
        Nombres = view.findViewById(R.id.Nombres);
        Apellidos = view.findViewById(R.id.Apellidos);
        Edad = view.findViewById(R.id.Edad);
        Registrar = view.findViewById(R.id.Registrar);

//        Inicializando firebase Authentication
        auth = FirebaseAuth.getInstance();


        Date date = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("d 'de' MMMM 'del' yyyy");

        String SFecha = fecha.format(date);

        FechaRegistro.setText(SFecha);

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Convertimos a string EditText: Correo y Password
                String correo = Correo.getText().toString();
                String pass = Password.getText().toString();

//                Validación de Correo

                if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                    Correo.setError("¡Correo inválido!");
                    Correo.setFocusable(true);
                } else if (pass.length() < 6) {
                    Password.setError("La contraseña debe ser mayor o igual a 6 carácteres");
                    Password.setFocusable(true);
                }else{
                    RegistroAdministradores(correo,pass);
                }
            }
        });

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Registrando, espere ...");
        progressDialog.setCancelable(false);


        return view;

    }

    private void RegistroAdministradores(String correo, String pass) {

        progressDialog.show();
        auth.createUserWithEmailAndPassword(correo,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    FirebaseUser user = auth.getCurrentUser();
                    assert user != null;

                    String UID = user.getUid();
                    String correo = Correo.getText().toString();
                    String pass = Password.getText().toString();
                    String nombres = Nombres.getText().toString();
                    String apellidos = Apellidos.getText().toString();
                    String edad = Edad.getText().toString();
                    int EdadInt  = Integer.parseInt(edad);

                    HashMap<Object,Object> Administradores = new HashMap<>();

                    Administradores.put("UID", UID);
                    Administradores.put("CORREO", correo);
                    Administradores.put("PASS", pass);
                    Administradores.put("NOMBRES", nombres);
                    Administradores.put("APELLIDOS", apellidos);
                    Administradores.put("EDAD", EdadInt);
                    Administradores.put("IMAGEN", "");

//                    Inicializar FirebaseDatabase

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("Bse de datos administradores9b");

                    reference.child(UID).setValue(Administradores);

                    startActivity(new Intent(getActivity(), MainActivityAdministrador.class));
                    Toast.makeText(getActivity(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
                    getActivity().finish();

                }
                else {
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