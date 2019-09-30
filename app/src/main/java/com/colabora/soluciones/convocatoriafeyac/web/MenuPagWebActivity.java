package com.colabora.soluciones.convocatoriafeyac.web;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.widget.ImageView;
import android.widget.Toast;

import com.colabora.soluciones.convocatoriafeyac.Modelos.EspecialidadesComida;
import com.colabora.soluciones.convocatoriafeyac.Modelos.Horario;
import com.colabora.soluciones.convocatoriafeyac.Modelos.MenuComida;
import com.colabora.soluciones.convocatoriafeyac.Modelos.Usuario;
import com.colabora.soluciones.convocatoriafeyac.Modelos.WebImagen;
import com.colabora.soluciones.convocatoriafeyac.Modelos.caracteristicas_web;
import com.colabora.soluciones.convocatoriafeyac.Modelos.caracteristicas_web_dos;
import com.colabora.soluciones.convocatoriafeyac.Modelos.pagWebs;
import com.colabora.soluciones.convocatoriafeyac.R;
import com.colabora.soluciones.convocatoriafeyac.web.aplicaciones.WebAppsSeccion1Activity;
import com.colabora.soluciones.convocatoriafeyac.web.comida.WebsComidaSeccion1;
import com.colabora.soluciones.convocatoriafeyac.web.moda.WebsModaSeccion1;
import com.colabora.soluciones.convocatoriafeyac.web.moda.WebsModaSeccion7;
import com.colabora.soluciones.convocatoriafeyac.web.productos.WebsProductosSeccion1;
import com.colabora.soluciones.convocatoriafeyac.web.salud.WebsSaludSeccion1;
import com.colabora.soluciones.convocatoriafeyac.web.servicios.WebsServiciosSeccion1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1beta1.Value;

import java.lang.reflect.Array;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebModa.paginaWebModa;
import com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebServicios.paginaWebServicios;
import com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebProductos.paginaWebProductos;
import com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebComida.paginaWebComida;
import com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebSalud.paginaWebSalud;
import com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebAplicaciones.paginaWebAplicaciones;

public class MenuPagWebActivity extends AppCompatActivity {

    private ImageView imgServicios;
    private ImageView imgProductos;
    private ImageView imgSalud;
    private ImageView imgAplicaciones;
    private ImageView imgModa;
    private ImageView imgComida;
    private FabSpeedDial speedDialView;

    private FirebaseFirestore db;
    private SharedPreferences sharedPreferences;
    private Usuario miUsuario;

    private ProgressDialog progressDialog;
    private pagWebs miPagina;
    private QueryDocumentSnapshot documentopagina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pag_web);
        setTitle("Crear mi página web");

        imgServicios = (ImageView)findViewById(R.id.imgWebServicios);
        imgProductos = (ImageView)findViewById(R.id.imgWebProductos);
        imgSalud = (ImageView)findViewById(R.id.imgWebSalud);
        imgAplicaciones = (ImageView)findViewById(R.id.imgWebApps);
        imgModa = (ImageView)findViewById(R.id.imgWebModa);
        imgComida = (ImageView)findViewById(R.id.imgWebComida);
        speedDialView = (FabSpeedDial)findViewById(R.id.speedDial);

        sharedPreferences = getSharedPreferences("misDatos", 0);
        db = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(MenuPagWebActivity.this);
        progressDialog.setMessage("Checando información en la base de datos");

        imgServicios.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgProductos.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgSalud.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgAplicaciones.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgModa.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgComida.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        progressDialog.show();

        db.collection("webs")
                .whereEqualTo("idUsuario", user.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                miPagina = document.toObject(pagWebs.class);
                                documentopagina = document;
                            }
                            if(miPagina != null){

                                Toast.makeText(getApplicationContext(),"Tu página web se ha cargado correctamente, puedes editarla o visualizarla oprimiendo el botón verde",Toast.LENGTH_LONG).show();

                                if(miPagina.getTipo() == 1){

                                    paginaWebComida paginaWebComida_ = documentopagina.toObject(paginaWebComida.class);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.clear();

                                    editor.putString(user.getUid()+"-tipo_mi_pag_web", "1");
                                    editor.putString("nombrePagWeb", paginaWebComida_.getUrl());

                                    //COMIDA



                                    if(paginaWebComida_.getSecciones().getHome().getBanners().size() == 1){

                                        editor.putString("web_comida_titulo_1_home", paginaWebComida_.getSecciones().getHome().getBanners().get(0).getTexto());
                                        editor.putString("web_comida_img_1_seccion_1", paginaWebComida_.getSecciones().getHome().getBanners().get(0).getImg());
                                    }
                                    else if(paginaWebComida_.getSecciones().getHome().getBanners().size() == 2){

                                        editor.putString("web_comida_titulo_1_home", paginaWebComida_.getSecciones().getHome().getBanners().get(0).getTexto());
                                        editor.putString("web_comida_img_1_seccion_1", paginaWebComida_.getSecciones().getHome().getBanners().get(0).getImg());

                                        editor.putString("web_comida_titulo_2_home", paginaWebComida_.getSecciones().getHome().getBanners().get(1).getTexto());
                                        editor.putString("web_comida_img_2_seccion_1", paginaWebComida_.getSecciones().getHome().getBanners().get(1).getImg());
                                    }
                                    else if(paginaWebComida_.getSecciones().getHome().getBanners().size() == 3){

                                        editor.putString("web_comida_titulo_1_home", paginaWebComida_.getSecciones().getHome().getBanners().get(0).getTexto());
                                        editor.putString("web_comida_img_1_seccion_1", paginaWebComida_.getSecciones().getHome().getBanners().get(0).getImg());

                                        editor.putString("web_comida_titulo_2_home", paginaWebComida_.getSecciones().getHome().getBanners().get(1).getTexto());
                                        editor.putString("web_comida_img_2_seccion_1", paginaWebComida_.getSecciones().getHome().getBanners().get(1).getImg());

                                        editor.putString("web_comida_titulo_3_home", paginaWebComida_.getSecciones().getHome().getBanners().get(2).getTexto());
                                        editor.putString("web_comida_img_3_seccion_1", paginaWebComida_.getSecciones().getHome().getBanners().get(2).getImg());
                                    }

                                    // variables about

                                    editor.putString("web_comida_descripcion_seccion_4", paginaWebComida_.getSecciones().getAbout().getText());
                                    editor.putString("web_comida_img_seccion_4", paginaWebComida_.getSecciones().getAbout().getImg());
                                    editor.putString("web_comida_titulo_seccion_4",paginaWebComida_.getSecciones().getAbout().getNavbar());

                                    // variables yellow

                                    editor.putString("web_comida_titulo_1_seccion_2", paginaWebComida_.getSecciones().getYellow().getTitulo1());
                                    editor.putString("web_comida_titulo_2_seccion_2", paginaWebComida_.getSecciones().getYellow().getTitulo2());
                                    editor.putString("web_comida_titulo_3_seccion_2", paginaWebComida_.getSecciones().getYellow().getTitulo3());
                                    editor.putString("web_comida_descripcion_1_seccion_2", paginaWebComida_.getSecciones().getYellow().getDescripcion1());
                                    editor.putString("web_comida_descripcion_2_seccion_2", paginaWebComida_.getSecciones().getYellow().getDescripcion2());
                                    editor.putString("web_comida_descripcion_3_seccion_2", paginaWebComida_.getSecciones().getYellow().getDescripcion3());

                                    // variables specials

                                    editor.putString("web_comida_titulo_seccion_3",paginaWebComida_.getSecciones().getSpecials().getNavbar());


                                    if (paginaWebComida_.getSecciones().getSpecials().getItems().size() == 1){

                                        editor.putString("web_comida_seccion_3_recycler", "1");
                                        editor.putString("web_comida_seccion_3_caracteristica1_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica1_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica1_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica1_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getPrecio());

                                    }
                                    else if (paginaWebComida_.getSecciones().getSpecials().getItems().size() == 2){

                                        editor.putString("web_comida_seccion_3_recycler", "1");
                                        editor.putString("web_comida_seccion_3_caracteristica1_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica1_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica1_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica1_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getPrecio());

                                        editor.putString("web_comida_seccion_3_recycler", "2");
                                        editor.putString("web_comida_seccion_3_caracteristica2_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica2_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica2_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica2_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getPrecio());

                                    }
                                    else if (paginaWebComida_.getSecciones().getSpecials().getItems().size() == 3){


                                        editor.putString("web_comida_seccion_3_recycler", "1");
                                        editor.putString("web_comida_seccion_3_caracteristica1_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica1_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica1_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica1_precio",  paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getPrecio());

                                        editor.putString("web_comida_seccion_3_recycler", "2");
                                        editor.putString("web_comida_seccion_3_caracteristica2_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica2_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica2_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica2_precio",  paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getPrecio());

                                        editor.putString("web_comida_seccion_3_recycler", "3");
                                        editor.putString("web_comida_seccion_3_caracteristica3_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(2).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica3_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(2).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica3_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(2).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica3_precio",  paginaWebComida_.getSecciones().getSpecials().getItems().get(2).getPrecio());

                                    }
                                    else if (paginaWebComida_.getSecciones().getSpecials().getItems().size() == 4){


                                        editor.putString("web_comida_seccion_3_recycler", "1");
                                        editor.putString("web_comida_seccion_3_caracteristica1_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica1_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica1_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica1_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getPrecio());

                                        editor.putString("web_comida_seccion_3_recycler", "2");
                                        editor.putString("web_comida_seccion_3_caracteristica2_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica2_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica2_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica2_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getPrecio());

                                        editor.putString("web_comida_seccion_3_recycler", "3");
                                        editor.putString("web_comida_seccion_3_caracteristica3_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(2).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica3_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(2).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica3_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(2).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica3_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(2).getPrecio());

                                        editor.putString("web_comida_seccion_3_recycler", "4");
                                        editor.putString("web_comida_seccion_3_caracteristica4_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(3).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica4_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(3).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica4_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(3).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica4_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(3).getPrecio());

                                    }
                                    else if (paginaWebComida_.getSecciones().getSpecials().getItems().size() == 5){

                                        editor.putString("web_comida_seccion_3_recycler", "1");
                                        editor.putString("web_comida_seccion_3_caracteristica1_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica1_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica1_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica1_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getPrecio());

                                        editor.putString("web_comida_seccion_3_recycler", "2");
                                        editor.putString("web_comida_seccion_3_caracteristica2_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica2_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica2_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica2_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getPrecio());

                                        editor.putString("web_comida_seccion_3_recycler", "3");
                                        editor.putString("web_comida_seccion_3_caracteristica3_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(2).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica3_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(2).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica3_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(2).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica3_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(2).getPrecio());

                                        editor.putString("web_comida_seccion_3_recycler", "4");
                                        editor.putString("web_comida_seccion_3_caracteristica4_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(3).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica4_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(3).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica4_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(3).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica4_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(3).getPrecio());

                                        editor.putString("web_comida_seccion_3_recycler", "5");
                                        editor.putString("web_comida_seccion_3_caracteristica5_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(4).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica5_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(4).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica5_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(4).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica5_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(4).getPrecio());

                                    }
                                    else if (paginaWebComida_.getSecciones().getSpecials().getItems().size() == 6){

                                        editor.putString("web_comida_seccion_3_recycler", "1");
                                        editor.putString("web_comida_seccion_3_caracteristica1_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica1_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica1_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica1_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(0).getPrecio());

                                        editor.putString("web_comida_seccion_3_recycler", "2");
                                        editor.putString("web_comida_seccion_3_caracteristica2_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica2_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica2_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica2_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(1).getPrecio());

                                        editor.putString("web_comida_seccion_3_recycler", "3");
                                        editor.putString("web_comida_seccion_3_caracteristica3_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(2).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica3_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(2).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica3_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(2).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica3_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(2).getPrecio());

                                        editor.putString("web_comida_seccion_3_recycler", "4");
                                        editor.putString("web_comida_seccion_3_caracteristica4_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(3).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica4_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(3).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica4_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(3).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica4_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(3).getPrecio());

                                        editor.putString("web_comida_seccion_3_recycler", "5");
                                        editor.putString("web_comida_seccion_3_caracteristica5_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(4).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica5_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(4).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica5_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(4).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica5_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(4).getPrecio());

                                        editor.putString("web_comida_seccion_3_recycler", "6");
                                        editor.putString("web_comida_seccion_3_caracteristica6_titulo", paginaWebComida_.getSecciones().getSpecials().getItems().get(5).getTitulo());
                                        editor.putString("web_comida_seccion_3_caracteristica6_descripcion", paginaWebComida_.getSecciones().getSpecials().getItems().get(5).getDescripcion());
                                        editor.putString("web_comida_seccion_3_caracteristica6_url", paginaWebComida_.getSecciones().getSpecials().getItems().get(5).getImg());
                                        editor.putInt("web_comida_seccion_3_caracteristica6_precio", paginaWebComida_.getSecciones().getSpecials().getItems().get(5).getPrecio());

                                    }

                                    // variables menu
                                    editor.putString("web_comida_titulo_seccion5",paginaWebComida_.getSecciones().getMenu().getNavbar());


                                    if (paginaWebComida_.getSecciones().getMenu().getItems().size() == 1){

                                        editor.putString("web_comida_seccion_5_recycler", "1");
                                        editor.putString("web_comida_seccion_5_caracteristica1_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(0).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica1_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(0).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica1_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(0).getPrecio());

                                    }
                                    else if (paginaWebComida_.getSecciones().getMenu().getItems().size() == 2){

                                        editor.putString("web_comida_seccion_5_recycler", "1");
                                        editor.putString("web_comida_seccion_5_caracteristica1_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(0).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica1_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(0).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica1_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(0).getPrecio());

                                        editor.putString("web_comida_seccion_5_recycler", "2");
                                        editor.putString("web_comida_seccion_5_caracteristica2_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(1).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica2_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(1).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica2_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(1).getPrecio());

                                    }
                                    else if (paginaWebComida_.getSecciones().getMenu().getItems().size() == 3){

                                        editor.putString("web_comida_seccion_5_recycler", "1");
                                        editor.putString("web_comida_seccion_5_caracteristica1_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(0).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica1_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(0).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica1_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(0).getPrecio());

                                        editor.putString("web_comida_seccion_5_recycler", "2");
                                        editor.putString("web_comida_seccion_5_caracteristica2_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(1).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica2_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(1).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica2_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(1).getPrecio());

                                        editor.putString("web_comida_seccion_5_recycler", "3");
                                        editor.putString("web_comida_seccion_5_caracteristica3_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(2).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica3_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(2).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica3_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(2).getPrecio());

                                    }
                                    else if (paginaWebComida_.getSecciones().getMenu().getItems().size() == 4){

                                        editor.putString("web_comida_seccion_5_recycler", "1");
                                        editor.putString("web_comida_seccion_5_caracteristica1_titulo",  paginaWebComida_.getSecciones().getMenu().getItems().get(0).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica1_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(0).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica1_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(2).getPrecio());

                                        editor.putString("web_comida_seccion_5_recycler", "2");
                                        editor.putString("web_comida_seccion_5_caracteristica2_titulo",  paginaWebComida_.getSecciones().getMenu().getItems().get(1).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica2_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(1).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica2_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(1).getPrecio());

                                        editor.putString("web_comida_seccion_5_recycler", "3");
                                        editor.putString("web_comida_seccion_5_caracteristica3_titulo",  paginaWebComida_.getSecciones().getMenu().getItems().get(2).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica3_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(2).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica3_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(2).getPrecio());

                                        editor.putString("web_comida_seccion_5_recycler", "4");
                                        editor.putString("web_comida_seccion_5_caracteristica4_titulo",  paginaWebComida_.getSecciones().getMenu().getItems().get(3).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica4_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(3).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica4_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(3).getPrecio());

                                    }
                                    else if (paginaWebComida_.getSecciones().getMenu().getItems().size() == 5){

                                        editor.putString("web_comida_seccion_5_recycler", "1");
                                        editor.putString("web_comida_seccion_5_caracteristica1_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(0).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica1_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(0).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica1_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(0).getPrecio());

                                        editor.putString("web_comida_seccion_5_recycler", "2");
                                        editor.putString("web_comida_seccion_5_caracteristica2_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(1).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica2_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(1).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica2_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(1).getPrecio());

                                        editor.putString("web_comida_seccion_5_recycler", "3");
                                        editor.putString("web_comida_seccion_5_caracteristica3_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(2).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica3_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(2).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica3_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(2).getPrecio());

                                        editor.putString("web_comida_seccion_5_recycler", "4");
                                        editor.putString("web_comida_seccion_5_caracteristica4_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(3).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica4_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(3).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica4_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(3).getPrecio());

                                        editor.putString("web_comida_seccion_5_recycler", "5");
                                        editor.putString("web_comida_seccion_5_caracteristica5_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(4).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica5_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(4).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica5_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(4).getPrecio());

                                    }
                                    else if (paginaWebComida_.getSecciones().getMenu().getItems().size() == 6){

                                        editor.putString("web_comida_seccion_5_recycler", "1");
                                        editor.putString("web_comida_seccion_5_caracteristica1_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(0).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica1_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(0).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica1_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(0).getPrecio());

                                        editor.putString("web_comida_seccion_5_recycler", "2");
                                        editor.putString("web_comida_seccion_5_caracteristica2_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(1).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica2_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(1).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica2_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(1).getPrecio());

                                        editor.putString("web_comida_seccion_5_recycler", "3");
                                        editor.putString("web_comida_seccion_5_caracteristica3_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(2).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica3_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(2).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica3_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(2).getPrecio());

                                        editor.putString("web_comida_seccion_5_recycler", "4");
                                        editor.putString("web_comida_seccion_5_caracteristica4_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(3).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica4_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(3).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica4_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(3).getPrecio());

                                        editor.putString("web_comida_seccion_5_recycler", "5");
                                        editor.putString("web_comida_seccion_5_caracteristica5_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(4).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica5_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(4).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica5_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(4).getPrecio());

                                        editor.putString("web_comida_seccion_5_recycler", "6");
                                        editor.putString("web_comida_seccion_5_caracteristica6_titulo", paginaWebComida_.getSecciones().getMenu().getItems().get(5).getTitulo());
                                        editor.putString("web_comida_seccion_5_caracteristica6_descripcion", paginaWebComida_.getSecciones().getMenu().getItems().get(5).getDescripcion());
                                        editor.putInt("web_comida_seccion_5_caracteristica6_precio", paginaWebComida_.getSecciones().getMenu().getItems().get(5).getPrecio());

                                    }

                                    // variables contacto

                                    editor.putString("web_comida_nombre_seccion_6", paginaWebComida_.getSecciones().getContacto().getNombre_restaurante());
                                    editor.putString("web_comida_direccion_seccion_6", paginaWebComida_.getSecciones().getContacto().getAdress());
                                    editor.putString("web_comida_correo_seccion_6", paginaWebComida_.getSecciones().getContacto().getEmail());
                                    editor.putString("web_comida_titulo_seccion_6",paginaWebComida_.getSecciones().getContacto().getNavbar());

                                    editor.putString("web_comida_telefono_seccion_6", paginaWebComida_.getSecciones().getContacto().getPhone());
                                    editor.putString("web_comida_facebook_seccion_6", paginaWebComida_.getSecciones().getContacto().getFacebook_url());
                                    editor.putString("web_comida_instagram_seccion_6", paginaWebComida_.getSecciones().getContacto().getInstagram_url());

                                    editor.commit();

                                }
                                else if(miPagina.getTipo() == 2){

                                    paginaWebProductos paginaWebProductos_ = documentopagina.toObject(paginaWebProductos.class);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.clear();

                                    editor.putString(user.getUid()+"-tipo_mi_pag_web", "2");
                                    editor.putString("nombrePagWeb", paginaWebProductos_.getUrl());

                                    //PRODUCTOS

                                    editor.putString("web_productos_titulo_home",paginaWebProductos_.getSecciones().getHome().getTitulo());
                                    editor.putString("web_productos_subtitulo_home", paginaWebProductos_.getSecciones().getHome().getSubtitulo());


                                    if (paginaWebProductos_.getSecciones().getHome().getImagen().size() == 1){

                                        editor.putString("web_productos_img_1_seccion_1", paginaWebProductos_.getSecciones().getHome().getImagen().get(0).getImagen());

                                    }
                                    else if (paginaWebProductos_.getSecciones().getHome().getImagen().size() == 2){

                                        editor.putString("web_productos_img_1_seccion_1", paginaWebProductos_.getSecciones().getHome().getImagen().get(0).getImagen());

                                        editor.putString("web_productos_img_2_seccion_1", paginaWebProductos_.getSecciones().getHome().getImagen().get(1).getImagen());
                                    }
                                    else if (paginaWebProductos_.getSecciones().getHome().getImagen().size() == 3){

                                        editor.putString("web_productos_img_1_seccion_1", paginaWebProductos_.getSecciones().getHome().getImagen().get(0).getImagen());

                                        editor.putString("web_productos_img_2_seccion_1", paginaWebProductos_.getSecciones().getHome().getImagen().get(1).getImagen());

                                        editor.putString("web_productos_img_3_seccion_1", paginaWebProductos_.getSecciones().getHome().getImagen().get(2).getImagen());

                                    }

                                    // variables about

                                    editor.putString("web_productos_img_seccion_2", paginaWebProductos_.getSecciones().getAbout().getImagen());
                                    editor.putString("web_productos_titulo_seccion_2", paginaWebProductos_.getSecciones().getAbout().getTitulo());
                                    editor.putString("web_productos_subtitulo_seccion_2", paginaWebProductos_.getSecciones().getAbout().getSubtitulo());
                                    editor.putString("web_productos_descripcion_seccion_2", paginaWebProductos_.getSecciones().getAbout().getDescripcion());

                                    // variables servicios

                                    editor.putString("web_productos_seccion_3_titulo", paginaWebProductos_.getSecciones().getServicios().getTitulo());


                                    if (paginaWebProductos_.getSecciones().getServicios().getServicio().size() == 1){

                                        editor.putString("web_productos_seccion_3_recycler", "1");
                                        editor.putString("web_productos_seccion_3_caracteristica1_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(0).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica1_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(0).getDescripcion());

                                    }
                                    else if (paginaWebProductos_.getSecciones().getServicios().getServicio().size() == 2){

                                        editor.putString("web_productos_seccion_3_recycler", "1");
                                        editor.putString("web_productos_seccion_3_caracteristica1_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(0).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica1_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(0).getDescripcion());

                                        editor.putString("web_productos_seccion_3_recycler", "2");
                                        editor.putString("web_productos_seccion_3_caracteristica2_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(1).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica2_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(1).getDescripcion());

                                    }
                                    else if (paginaWebProductos_.getSecciones().getServicios().getServicio().size() == 3){

                                        editor.putString("web_productos_seccion_3_recycler", "1");
                                        editor.putString("web_productos_seccion_3_caracteristica1_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(0).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica1_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(0).getDescripcion());

                                        editor.putString("web_productos_seccion_3_recycler", "2");
                                        editor.putString("web_productos_seccion_3_caracteristica2_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(1).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica2_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(1).getDescripcion());

                                        editor.putString("web_productos_seccion_3_recycler", "3");
                                        editor.putString("web_productos_seccion_3_caracteristica3_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(2).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica3_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(2).getDescripcion());

                                    }
                                    else if (paginaWebProductos_.getSecciones().getServicios().getServicio().size() == 4){

                                        editor.putString("web_productos_seccion_3_recycler", "1");
                                        editor.putString("web_productos_seccion_3_caracteristica1_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(0).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica1_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(0).getDescripcion());

                                        editor.putString("web_productos_seccion_3_recycler", "2");
                                        editor.putString("web_productos_seccion_3_caracteristica2_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(1).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica2_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(1).getDescripcion());

                                        editor.putString("web_productos_seccion_3_recycler", "3");
                                        editor.putString("web_productos_seccion_3_caracteristica3_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(2).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica3_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(2).getDescripcion());

                                        editor.putString("web_productos_seccion_3_recycler", "4");
                                        editor.putString("web_productos_seccion_3_caracteristica4_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(3).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica4_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(3).getDescripcion());

                                    }
                                    else if (paginaWebProductos_.getSecciones().getServicios().getServicio().size() == 5){

                                        editor.putString("web_productos_seccion_3_recycler", "1");
                                        editor.putString("web_productos_seccion_3_caracteristica1_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(0).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica1_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(0).getDescripcion());

                                        editor.putString("web_productos_seccion_3_recycler", "2");
                                        editor.putString("web_productos_seccion_3_caracteristica2_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(1).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica2_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(1).getDescripcion());

                                        editor.putString("web_productos_seccion_3_recycler", "3");
                                        editor.putString("web_productos_seccion_3_caracteristica3_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(2).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica3_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(2).getDescripcion());

                                        editor.putString("web_productos_seccion_3_recycler", "4");
                                        editor.putString("web_productos_seccion_3_caracteristica4_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(3).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica4_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(3).getDescripcion());

                                        editor.putString("web_productos_seccion_3_recycler", "5");
                                        editor.putString("web_productos_seccion_3_caracteristica5_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(4).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica5_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(4).getDescripcion());

                                    }
                                    else if (paginaWebProductos_.getSecciones().getServicios().getServicio().size() == 6){

                                        editor.putString("web_productos_seccion_3_recycler", "1");
                                        editor.putString("web_productos_seccion_3_caracteristica1_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(0).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica1_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(0).getDescripcion());

                                        editor.putString("web_productos_seccion_3_recycler", "2");
                                        editor.putString("web_productos_seccion_3_caracteristica2_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(1).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica2_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(1).getDescripcion());

                                        editor.putString("web_productos_seccion_3_recycler", "3");
                                        editor.putString("web_productos_seccion_3_caracteristica3_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(2).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica3_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(2).getDescripcion());

                                        editor.putString("web_productos_seccion_3_recycler", "4");
                                        editor.putString("web_productos_seccion_3_caracteristica4_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(3).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica4_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(3).getDescripcion());

                                        editor.putString("web_productos_seccion_3_recycler", "5");
                                        editor.putString("web_productos_seccion_3_caracteristica5_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(4).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica5_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(4).getDescripcion());

                                        editor.putString("web_productos_seccion_3_recycler", "6");
                                        editor.putString("web_productos_seccion_3_caracteristica6_titulo", paginaWebProductos_.getSecciones().getServicios().getServicio().get(5).getTitulo());
                                        editor.putString("web_productos_seccion_3_caracteristica6_descripcion", paginaWebProductos_.getSecciones().getServicios().getServicio().get(5).getDescripcion());

                                    }

                                    // variables imagencontacto

                                    editor.putString("web_productos_titulo_seccion_4", paginaWebProductos_.getSecciones().getImagencontacto().getTitulo());
                                    editor.putString("web_productos_subtitulo_seccion_4", paginaWebProductos_.getSecciones().getImagencontacto().getSubtitulo());
                                    editor.putString("web_productos_img_seccion_4", paginaWebProductos_.getSecciones().getImagencontacto().getImagen());
                                    editor.putString("web_productos_img_2_seccion_4",paginaWebProductos_.getSecciones().getImagencontacto().getImagendos());

                                    // variables galeria

                                    editor.putString("web_productos_titulo_nav_seccion5",paginaWebProductos_.getSecciones().getGaleria().getNavbar());


                                    if (paginaWebProductos_.getSecciones().getGaleria().getImagenes().size() == 1){

                                        editor.putString("web_productos_seccion_5_recycler", "1");
                                        editor.putString("web_productos_seccion_5_caracteristica1_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica1_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica1_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getImagen());

                                    }
                                    else if (paginaWebProductos_.getSecciones().getGaleria().getImagenes().size() == 2){

                                        editor.putString("web_productos_seccion_5_recycler", "1");
                                        editor.putString("web_productos_seccion_5_caracteristica1_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica1_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica1_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getImagen());

                                        editor.putString("web_productos_seccion_5_recycler", "2");
                                        editor.putString("web_productos_seccion_5_caracteristica2_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(1).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica2_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(1).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica2_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(1).getImagen());

                                    }
                                    else if (paginaWebProductos_.getSecciones().getGaleria().getImagenes().size() == 3){

                                        editor.putString("web_productos_seccion_5_recycler", "1");
                                        editor.putString("web_productos_seccion_5_caracteristica1_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica1_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica1_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getImagen());

                                        editor.putString("web_productos_seccion_5_recycler", "2");
                                        editor.putString("web_productos_seccion_5_caracteristica2_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(1).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica2_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(1).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica2_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(1).getImagen());

                                        editor.putString("web_productos_seccion_5_recycler", "3");
                                        editor.putString("web_productos_seccion_5_caracteristica3_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(2).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica3_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(2).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica3_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(2).getImagen());

                                    }
                                    else if (paginaWebProductos_.getSecciones().getGaleria().getImagenes().size() == 4){

                                        editor.putString("web_productos_seccion_5_recycler", "1");
                                        editor.putString("web_productos_seccion_5_caracteristica1_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica1_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica1_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getImagen());

                                        editor.putString("web_productos_seccion_5_recycler", "2");
                                        editor.putString("web_productos_seccion_5_caracteristica2_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(1).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica2_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(1).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica2_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(1).getImagen());

                                        editor.putString("web_productos_seccion_5_recycler", "3");
                                        editor.putString("web_productos_seccion_5_caracteristica3_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(2).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica3_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(2).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica3_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(2).getImagen());

                                        editor.putString("web_productos_seccion_5_recycler", "4");
                                        editor.putString("web_productos_seccion_5_caracteristica4_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(3).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica4_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(3).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica4_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(3).getImagen());

                                    }
                                    else if (paginaWebProductos_.getSecciones().getGaleria().getImagenes().size() == 5){

                                        editor.putString("web_productos_seccion_5_recycler", "1");
                                        editor.putString("web_productos_seccion_5_caracteristica1_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica1_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica1_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getImagen());

                                        editor.putString("web_productos_seccion_5_recycler", "2");
                                        editor.putString("web_productos_seccion_5_caracteristica2_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(1).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica2_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(1).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica2_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(1).getImagen());

                                        editor.putString("web_productos_seccion_5_recycler", "3");
                                        editor.putString("web_productos_seccion_5_caracteristica3_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(2).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica3_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(2).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica3_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(2).getImagen());

                                        editor.putString("web_productos_seccion_5_recycler", "4");
                                        editor.putString("web_productos_seccion_5_caracteristica4_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(3).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica4_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(3).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica4_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(3).getImagen());

                                        editor.putString("web_productos_seccion_5_recycler", "5");
                                        editor.putString("web_productos_seccion_5_caracteristica5_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(4).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica5_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(4).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica5_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(4).getImagen());

                                    }
                                    else if (paginaWebProductos_.getSecciones().getGaleria().getImagenes().size() == 6){

                                        editor.putString("web_productos_seccion_5_recycler", "1");
                                        editor.putString("web_productos_seccion_5_caracteristica1_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica1_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica1_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(0).getImagen());

                                        editor.putString("web_productos_seccion_5_recycler", "2");
                                        editor.putString("web_productos_seccion_5_caracteristica2_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(1).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica2_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(1).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica2_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(1).getImagen());

                                        editor.putString("web_productos_seccion_5_recycler", "3");
                                        editor.putString("web_productos_seccion_5_caracteristica3_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(2).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica3_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(2).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica3_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(2).getImagen());

                                        editor.putString("web_productos_seccion_5_recycler", "4");
                                        editor.putString("web_productos_seccion_5_caracteristica4_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(3).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica4_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(3).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica4_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(3).getImagen());

                                        editor.putString("web_productos_seccion_5_recycler", "5");
                                        editor.putString("web_productos_seccion_5_caracteristica5_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(4).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica5_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(4).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica5_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(4).getImagen());

                                        editor.putString("web_productos_seccion_5_recycler", "6");
                                        editor.putString("web_productos_seccion_5_caracteristica6_titulo", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(5).getTitulo());
                                        editor.putString("web_productos_seccion_5_caracteristica6_descripcion", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(5).getDescripcion());
                                        editor.putString("web_productos_seccion_5_caracteristica6_url", paginaWebProductos_.getSecciones().getGaleria().getImagenes().get(5).getImagen());

                                    }

                                    // variables contacto
                                    editor.putString("web_productos_ubicacion_contacto", paginaWebProductos_.getSecciones().getContacto().getLugar());
                                    editor.putString("web_productos_email_contacto", paginaWebProductos_.getSecciones().getContacto().getEmail());
                                    editor.putString("web_productos_telefono_contacto", paginaWebProductos_.getSecciones().getContacto().getTelefono());
                                    editor.putString("web_productos_titulo_nav_seccion6",paginaWebProductos_.getSecciones().getContacto().getNavbar());
                                    editor.putString("web_productos_facebook_seccion6",paginaWebProductos_.getSecciones().getContacto().getFacebook());
                                    editor.putString("web_productos_twitter_seccion6",paginaWebProductos_.getSecciones().getContacto().getTwitter());
                                    editor.putString("web_productos_instagram_seccion6",paginaWebProductos_.getSecciones().getContacto().getInstagram());

                                    editor.commit();
                                }
                                else if(miPagina.getTipo() == 3){

                                    paginaWebServicios paginaWebServicios_ = documentopagina.toObject(paginaWebServicios.class);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.clear();

                                    editor.putString(user.getUid()+"-tipo_mi_pag_web", "3");
                                    editor.putString("nombrePagWeb", paginaWebServicios_.getUrl());

                                    //SERVICIOS


                                    // variables home

                                    editor.putString("web_servicios_img_seccion_1", paginaWebServicios_.getSecciones().getHome().getImagen());
                                    editor.putString("web_servicios_logo_seccion_1",paginaWebServicios_.getSecciones().getHome().getLogo());
                                    editor.putString("web_servicios_titulo_home", paginaWebServicios_.getSecciones().getHome().getTitulo());
                                    editor.putString("web_servicios_titulo_navbar_home", paginaWebServicios_.getSecciones().getHome().getNavbar());

                                    // variables about

                                    editor.putString("web_servicios_img_seccion_2", paginaWebServicios_.getSecciones().getAbout().getImagen());
                                    editor.putString("web_servicios_seccion_2_titulo", paginaWebServicios_.getSecciones().getAbout().getTitulo());
                                    editor.putString("web_servicios_seccion_2_descripcion", paginaWebServicios_.getSecciones().getAbout().getDescripcion());
                                    editor.putString("web_servicios_seccion_2_descripcion_2", paginaWebServicios_.getSecciones().getAbout().getSdescripcion());



                                    if (paginaWebServicios_.getSecciones().getAbout().getNosotros().size() == 1){

                                        editor.putString("web_servicios_img1_seccion_3",paginaWebServicios_.getSecciones().getAbout().getNosotros().get(0).getImagen());
                                        editor.putString("web_servicios_seccion_3_titulo1", paginaWebServicios_.getSecciones().getAbout().getNosotros().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_3_descripcion1", paginaWebServicios_.getSecciones().getAbout().getNosotros().get(0).getDescripcion());

                                    }
                                    else if (paginaWebServicios_.getSecciones().getAbout().getNosotros().size() == 2){

                                        editor.putString("web_servicios_img1_seccion_3",paginaWebServicios_.getSecciones().getAbout().getNosotros().get(0).getImagen());
                                        editor.putString("web_servicios_seccion_3_titulo1", paginaWebServicios_.getSecciones().getAbout().getNosotros().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_3_descripcion1", paginaWebServicios_.getSecciones().getAbout().getNosotros().get(0).getDescripcion());

                                        editor.putString("web_servicios_img2_seccion_3",paginaWebServicios_.getSecciones().getAbout().getNosotros().get(1).getImagen());
                                        editor.putString("web_servicios_seccion_3_titulo2", paginaWebServicios_.getSecciones().getAbout().getNosotros().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_3_descripcion2", paginaWebServicios_.getSecciones().getAbout().getNosotros().get(1).getDescripcion());

                                    }



                                    if (paginaWebServicios_.getSecciones().getAbout().getCaracteristicas().size() == 1){

                                        editor.putString("web_servicios_seccion_2_recycler", "1");
                                        editor.putString("web_servicios_seccion_2_caracteristica1_titulo", paginaWebServicios_.getSecciones().getAbout().getCaracteristicas().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_2_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getAbout().getCaracteristicas().get(0).getDescripcion());

                                    }
                                    else if (paginaWebServicios_.getSecciones().getAbout().getCaracteristicas().size() == 2){

                                        editor.putString("web_servicios_seccion_2_recycler", "1");
                                        editor.putString("web_servicios_seccion_2_caracteristica1_titulo", paginaWebServicios_.getSecciones().getAbout().getCaracteristicas().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_2_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getAbout().getCaracteristicas().get(0).getDescripcion());

                                        editor.putString("web_servicios_seccion_2_recycler", "2");
                                        editor.putString("web_servicios_seccion_2_caracteristica2_titulo", paginaWebServicios_.getSecciones().getAbout().getCaracteristicas().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_2_caracteristica2_descripcion", paginaWebServicios_.getSecciones().getAbout().getCaracteristicas().get(1).getDescripcion());

                                    }
                                    else if (paginaWebServicios_.getSecciones().getAbout().getCaracteristicas().size() == 3){

                                        editor.putString("web_servicios_seccion_2_recycler", "1");
                                        editor.putString("web_servicios_seccion_2_caracteristica1_titulo", paginaWebServicios_.getSecciones().getAbout().getCaracteristicas().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_2_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getAbout().getCaracteristicas().get(0).getDescripcion());

                                        editor.putString("web_servicios_seccion_2_recycler", "2");
                                        editor.putString("web_servicios_seccion_2_caracteristica2_titulo", paginaWebServicios_.getSecciones().getAbout().getCaracteristicas().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_2_caracteristica2_descripcion", paginaWebServicios_.getSecciones().getAbout().getCaracteristicas().get(1).getDescripcion());

                                        editor.putString("web_servicios_seccion_2_recycler", "3");
                                        editor.putString("web_servicios_seccion_2_caracteristica3_titulo", paginaWebServicios_.getSecciones().getAbout().getCaracteristicas().get(2).getTitulo());
                                        editor.putString("web_servicios_seccion_2_caracteristica3_descripcion", paginaWebServicios_.getSecciones().getAbout().getCaracteristicas().get(2).getDescripcion());

                                    }

                                    // variables servicios


                                    editor.putString("web_servicios_seccion_4_titulo", paginaWebServicios_.getSecciones().getServicios().getTitulo());
                                    editor.putString("web_servicios_seccion_4_descripcion", paginaWebServicios_.getSecciones().getServicios().getDescripcion());



                                    if (paginaWebServicios_.getSecciones().getServicios().getServicio().size() == 1){

                                        editor.putString("web_servicios_seccion_4_recycler", "1");
                                        editor.putString("web_servicios_seccion_4_caracteristica1_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(0).getDescripcion());

                                    }
                                    else if (paginaWebServicios_.getSecciones().getServicios().getServicio().size() == 2){

                                        editor.putString("web_servicios_seccion_4_recycler", "1");
                                        editor.putString("web_servicios_seccion_4_caracteristica1_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(0).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "2");
                                        editor.putString("web_servicios_seccion_4_caracteristica2_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica2_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(1).getDescripcion());

                                    }
                                    else if (paginaWebServicios_.getSecciones().getServicios().getServicio().size() == 3){

                                        editor.putString("web_servicios_seccion_4_recycler", "1");
                                        editor.putString("web_servicios_seccion_4_caracteristica1_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(0).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "2");
                                        editor.putString("web_servicios_seccion_4_caracteristica2_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica2_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(1).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "3");
                                        editor.putString("web_servicios_seccion_4_caracteristica3_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(2).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica3_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(2).getDescripcion());

                                    }
                                    else if (paginaWebServicios_.getSecciones().getServicios().getServicio().size() == 4){

                                        editor.putString("web_servicios_seccion_4_recycler", "1");
                                        editor.putString("web_servicios_seccion_4_caracteristica1_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(0).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "2");
                                        editor.putString("web_servicios_seccion_4_caracteristica2_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica2_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(1).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "3");
                                        editor.putString("web_servicios_seccion_4_caracteristica3_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(2).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica3_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(2).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "4");
                                        editor.putString("web_servicios_seccion_4_caracteristica4_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(3).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica4_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(3).getDescripcion());

                                    }
                                    else if (paginaWebServicios_.getSecciones().getServicios().getServicio().size() == 5){

                                        editor.putString("web_servicios_seccion_4_recycler", "1");
                                        editor.putString("web_servicios_seccion_4_caracteristica1_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(0).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "2");
                                        editor.putString("web_servicios_seccion_4_caracteristica2_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica2_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(1).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "3");
                                        editor.putString("web_servicios_seccion_4_caracteristica3_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(2).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica3_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(2).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "4");
                                        editor.putString("web_servicios_seccion_4_caracteristica4_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(3).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica4_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(3).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "5");
                                        editor.putString("web_servicios_seccion_4_caracteristica5_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(4).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica5_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(4).getDescripcion());

                                    }
                                    else if (paginaWebServicios_.getSecciones().getServicios().getServicio().size() == 6){

                                        editor.putString("web_servicios_seccion_4_recycler", "1");
                                        editor.putString("web_servicios_seccion_4_caracteristica1_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(0).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "2");
                                        editor.putString("web_servicios_seccion_4_caracteristica2_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica2_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(1).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "3");
                                        editor.putString("web_servicios_seccion_4_caracteristica3_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(2).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica3_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(2).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "4");
                                        editor.putString("web_servicios_seccion_4_caracteristica4_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(3).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica4_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(3).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "5");
                                        editor.putString("web_servicios_seccion_4_caracteristica5_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(4).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica5_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(4).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "6");
                                        editor.putString("web_servicios_seccion_4_caracteristica6_titulo", paginaWebServicios_.getSecciones().getServicios().getServicio().get(5).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica6_descripcion", paginaWebServicios_.getSecciones().getServicios().getServicio().get(5).getDescripcion());

                                    }

                                    // variables baner

                                    editor.putString("web_servicios_seccion_5_titulo", paginaWebServicios_.getSecciones().getBanner().getTitulo());
                                    editor.putString("web_servicios_seccion_5_descripcion", paginaWebServicios_.getSecciones().getBanner().getDescripcion());


                                    if (paginaWebServicios_.getSecciones().getBanner().getCuadroInfo().size() == 1){

                                        editor.putString("web_servicios_seccion_5_recycler", "1");
                                        editor.putString("web_servicios_seccion_5_caracteristica1_titulo", paginaWebServicios_.getSecciones().getBanner().getCuadroInfo().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_5_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getBanner().getCuadroInfo().get(0).getDescripcion());

                                    }
                                    else if (paginaWebServicios_.getSecciones().getBanner().getCuadroInfo().size() == 2){

                                        editor.putString("web_servicios_seccion_5_recycler", "1");
                                        editor.putString("web_servicios_seccion_5_caracteristica1_titulo", paginaWebServicios_.getSecciones().getBanner().getCuadroInfo().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_5_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getBanner().getCuadroInfo().get(0).getDescripcion());

                                        editor.putString("web_servicios_seccion_5_recycler", "2");
                                        editor.putString("web_servicios_seccion_5_caracteristica2_titulo", paginaWebServicios_.getSecciones().getBanner().getCuadroInfo().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_5_caracteristica2_descripcion", paginaWebServicios_.getSecciones().getBanner().getCuadroInfo().get(1).getDescripcion());

                                    }
                                    else if (paginaWebServicios_.getSecciones().getBanner().getCuadroInfo().size() == 3){

                                        editor.putString("web_servicios_seccion_5_recycler", "1");
                                        editor.putString("web_servicios_seccion_5_caracteristica1_titulo", paginaWebServicios_.getSecciones().getBanner().getCuadroInfo().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_5_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getBanner().getCuadroInfo().get(0).getDescripcion());

                                        editor.putString("web_servicios_seccion_5_recycler", "2");
                                        editor.putString("web_servicios_seccion_5_caracteristica2_titulo", paginaWebServicios_.getSecciones().getBanner().getCuadroInfo().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_5_caracteristica2_descripcion", paginaWebServicios_.getSecciones().getBanner().getCuadroInfo().get(1).getDescripcion());

                                        editor.putString("web_servicios_seccion_5_recycler", "3");
                                        editor.putString("web_servicios_seccion_5_caracteristica3_titulo", paginaWebServicios_.getSecciones().getBanner().getCuadroInfo().get(2).getTitulo());
                                        editor.putString("web_servicios_seccion_5_caracteristica3_descripcion", paginaWebServicios_.getSecciones().getBanner().getCuadroInfo().get(2).getDescripcion());

                                    }

                                    // variables portafolio

                                    editor.putString("web_servicios_seccion6_tituloNav",paginaWebServicios_.getSecciones().getPortafolio().getNavbar());


                                    if (paginaWebServicios_.getSecciones().getPortafolio().getImagenes().size() == 1){

                                        editor.putString("web_servicios_seccion_6_recycler", "1");
                                        editor.putString("web_servicios_seccion_6_caracteristica1_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica1_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getImagen());

                                    }
                                    else if (paginaWebServicios_.getSecciones().getPortafolio().getImagenes().size() == 2){

                                        editor.putString("web_servicios_seccion_6_recycler", "1");
                                        editor.putString("web_servicios_seccion_6_caracteristica1_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica1_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getImagen());

                                        editor.putString("web_servicios_seccion_6_recycler", "2");
                                        editor.putString("web_servicios_seccion_6_caracteristica2_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica2_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(1).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica2_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(1).getImagen());

                                    }
                                    else if (paginaWebServicios_.getSecciones().getPortafolio().getImagenes().size() == 3){

                                        editor.putString("web_servicios_seccion_6_recycler", "1");
                                        editor.putString("web_servicios_seccion_6_caracteristica1_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica1_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getImagen());

                                        editor.putString("web_servicios_seccion_6_recycler", "2");
                                        editor.putString("web_servicios_seccion_6_caracteristica2_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica2_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(1).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica2_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(1).getImagen());

                                        editor.putString("web_servicios_seccion_6_recycler", "3");
                                        editor.putString("web_servicios_seccion_6_caracteristica3_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(2).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica3_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(2).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica3_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(2).getImagen());

                                    }
                                    else if (paginaWebServicios_.getSecciones().getPortafolio().getImagenes().size() == 4){

                                        editor.putString("web_servicios_seccion_6_recycler", "1");
                                        editor.putString("web_servicios_seccion_6_caracteristica1_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica1_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getImagen());

                                        editor.putString("web_servicios_seccion_6_recycler", "2");
                                        editor.putString("web_servicios_seccion_6_caracteristica2_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica2_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(1).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica2_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(1).getImagen());

                                        editor.putString("web_servicios_seccion_6_recycler", "3");
                                        editor.putString("web_servicios_seccion_6_caracteristica3_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(2).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica3_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(2).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica3_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(2).getImagen());

                                        editor.putString("web_servicios_seccion_6_recycler", "4");
                                        editor.putString("web_servicios_seccion_6_caracteristica4_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(3).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica4_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(3).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica4_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(3).getImagen());

                                    }
                                    else if (paginaWebServicios_.getSecciones().getPortafolio().getImagenes().size() == 5){

                                        editor.putString("web_servicios_seccion_6_recycler", "1");
                                        editor.putString("web_servicios_seccion_6_caracteristica1_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica1_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getImagen());

                                        editor.putString("web_servicios_seccion_6_recycler", "2");
                                        editor.putString("web_servicios_seccion_6_caracteristica2_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica2_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(1).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica2_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(1).getImagen());

                                        editor.putString("web_servicios_seccion_6_recycler", "3");
                                        editor.putString("web_servicios_seccion_6_caracteristica3_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(2).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica3_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(2).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica3_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(2).getImagen());

                                        editor.putString("web_servicios_seccion_6_recycler", "4");
                                        editor.putString("web_servicios_seccion_6_caracteristica4_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(3).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica4_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(3).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica4_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(3).getImagen());

                                        editor.putString("web_servicios_seccion_6_recycler", "5");
                                        editor.putString("web_servicios_seccion_6_caracteristica5_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(4).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica5_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(4).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica5_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(4).getImagen());

                                    }
                                    else if (paginaWebServicios_.getSecciones().getPortafolio().getImagenes().size() == 6){

                                        editor.putString("web_servicios_seccion_6_recycler", "1");
                                        editor.putString("web_servicios_seccion_6_caracteristica1_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica1_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica1_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(0).getImagen());

                                        editor.putString("web_servicios_seccion_6_recycler", "2");
                                        editor.putString("web_servicios_seccion_6_caracteristica2_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica2_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(1).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica2_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(1).getImagen());

                                        editor.putString("web_servicios_seccion_6_recycler", "3");
                                        editor.putString("web_servicios_seccion_6_caracteristica3_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(2).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica3_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(2).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica3_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(2).getImagen());

                                        editor.putString("web_servicios_seccion_6_recycler", "4");
                                        editor.putString("web_servicios_seccion_6_caracteristica4_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(3).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica4_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(3).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica4_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(3).getImagen());

                                        editor.putString("web_servicios_seccion_6_recycler", "5");
                                        editor.putString("web_servicios_seccion_6_caracteristica5_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(4).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica5_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(4).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica5_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(4).getImagen());

                                        editor.putString("web_servicios_seccion_6_recycler", "6");
                                        editor.putString("web_servicios_seccion_6_caracteristica6_titulo", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(5).getTitulo());
                                        editor.putString("web_servicios_seccion_6_caracteristica6_descripcion", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(5).getDescripcion());
                                        editor.putString("web_servicios_seccion_6_caracteristica6_url", paginaWebServicios_.getSecciones().getPortafolio().getImagenes().get(5).getImagen());

                                    }

                                    // variables contacto
                                    editor.putString("web_servicios_ubicacion_contacto", paginaWebServicios_.getSecciones().getContacto().getLugar());
                                    editor.putString("web_servicios_email_contacto", paginaWebServicios_.getSecciones().getContacto().getEmail());
                                    editor.putString("web_servicios_telefono_contacto", paginaWebServicios_.getSecciones().getContacto().getTelefono());
                                    editor.putString("web_servicios_titulo_nav_contacto",paginaWebServicios_.getSecciones().getContacto().getNavbar());
                                    editor.putString("web_servicios_facebook_contacto",paginaWebServicios_.getSecciones().getContacto().getFacebook());
                                    editor.putString("web_servicios_twitter_contacto",paginaWebServicios_.getSecciones().getContacto().getTwitter());
                                    editor.putString("web_servicios_instagram_contacto",paginaWebServicios_.getSecciones().getContacto().getInstagram());

                                    editor.commit();

                                }
                                else if(miPagina.getTipo() == 4){

                                    paginaWebModa paginaWebModa_ = documentopagina.toObject(paginaWebModa.class);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.clear();

                                    editor.putString(user.getUid()+"-tipo_mi_pag_web", "4");
                                    editor.putString("nombrePagWeb", paginaWebModa_.getUrl());
                                    //MODA



                                    editor.putString("web_moda_img_seccion_1", paginaWebModa_.getSecciones().getHome().getBackground());
                                    editor.putString("web_moda_titulo_home", paginaWebModa_.getSecciones().getHome().getTitle());
                                    editor.putString("web_moda_subtitulo_home", paginaWebModa_.getSecciones().getHome().getSubtitle());
                                    editor.putString("web_moda_descripcion_home", paginaWebModa_.getSecciones().getHome().getText());



                                    editor.putString("web_moda_img_seccion_2",paginaWebModa_.getSecciones().getAbout().getImg());
                                    editor.putString("web_moda_titulo_seccion_2", paginaWebModa_.getSecciones().getAbout().getTitle());
                                    editor.putString("web_moda_subtitulo_seccion_2", paginaWebModa_.getSecciones().getAbout().getSubtitle());
                                    editor.putString("web_moda_descripcion_seccion_2", paginaWebModa_.getSecciones().getAbout().getText());



                                    editor.putString("web_moda_img_seccion_4",paginaWebModa_.getSecciones().getServices().getImg());
                                    editor.putString("web_moda_titulo_nav_seccion4",paginaWebModa_.getSecciones().getServices().getNavbar());




                                    if (paginaWebModa_.getSecciones().getServices().getList().size() == 1){

                                        editor.putString("web_servicios_seccion_4_recycler", "1");
                                        editor.putString("web_servicios_seccion_4_caracteristica1_titulo", paginaWebModa_.getSecciones().getServices().getList().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica1_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(0).getDescripcion());

                                    }
                                    else if (paginaWebModa_.getSecciones().getServices().getList().size() == 2){

                                        editor.putString("web_servicios_seccion_4_recycler", "1");
                                        editor.putString("web_servicios_seccion_4_caracteristica1_titulo", paginaWebModa_.getSecciones().getServices().getList().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica1_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(0).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "2");
                                        editor.putString("web_servicios_seccion_4_caracteristica2_titulo", paginaWebModa_.getSecciones().getServices().getList().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica2_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(1).getDescripcion());

                                    }
                                    else if (paginaWebModa_.getSecciones().getServices().getList().size() == 3){

                                        editor.putString("web_servicios_seccion_4_recycler", "1");
                                        editor.putString("web_servicios_seccion_4_caracteristica1_titulo",paginaWebModa_.getSecciones().getServices().getList().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica1_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(0).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "2");
                                        editor.putString("web_servicios_seccion_4_caracteristica2_titulo", paginaWebModa_.getSecciones().getServices().getList().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica2_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(1).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "3");
                                        editor.putString("web_servicios_seccion_4_caracteristica3_titulo", paginaWebModa_.getSecciones().getServices().getList().get(2).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica3_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(2).getDescripcion());

                                    }
                                    else if (paginaWebModa_.getSecciones().getServices().getList().size() == 4){

                                        editor.putString("web_servicios_seccion_4_recycler", "1");
                                        editor.putString("web_servicios_seccion_4_caracteristica1_titulo", paginaWebModa_.getSecciones().getServices().getList().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica1_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(0).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "2");
                                        editor.putString("web_servicios_seccion_4_caracteristica2_titulo", paginaWebModa_.getSecciones().getServices().getList().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica2_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(1).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "3");
                                        editor.putString("web_servicios_seccion_4_caracteristica3_titulo", paginaWebModa_.getSecciones().getServices().getList().get(2).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica3_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(2).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "4");
                                        editor.putString("web_servicios_seccion_4_caracteristica4_titulo", paginaWebModa_.getSecciones().getServices().getList().get(3).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica4_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(3).getDescripcion());

                                    }
                                    else if (paginaWebModa_.getSecciones().getServices().getList().size() == 5){

                                        editor.putString("web_servicios_seccion_4_recycler", "1");
                                        editor.putString("web_servicios_seccion_4_caracteristica1_titulo", paginaWebModa_.getSecciones().getServices().getList().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica1_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(0).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "2");
                                        editor.putString("web_servicios_seccion_4_caracteristica2_titulo", paginaWebModa_.getSecciones().getServices().getList().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica2_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(1).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "3");
                                        editor.putString("web_servicios_seccion_4_caracteristica3_titulo", paginaWebModa_.getSecciones().getServices().getList().get(2).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica3_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(2).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "4");
                                        editor.putString("web_servicios_seccion_4_caracteristica4_titulo", paginaWebModa_.getSecciones().getServices().getList().get(3).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica4_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(3).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "5");
                                        editor.putString("web_servicios_seccion_4_caracteristica5_titulo", paginaWebModa_.getSecciones().getServices().getList().get(4).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica5_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(4).getDescripcion());

                                    }
                                    else if (paginaWebModa_.getSecciones().getServices().getList().size() == 6){

                                        editor.putString("web_servicios_seccion_4_recycler", "1");
                                        editor.putString("web_servicios_seccion_4_caracteristica1_titulo", paginaWebModa_.getSecciones().getServices().getList().get(0).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica1_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(0).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "2");
                                        editor.putString("web_servicios_seccion_4_caracteristica2_titulo", paginaWebModa_.getSecciones().getServices().getList().get(1).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica2_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(1).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "3");
                                        editor.putString("web_servicios_seccion_4_caracteristica3_titulo", paginaWebModa_.getSecciones().getServices().getList().get(2).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica3_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(2).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "4");
                                        editor.putString("web_servicios_seccion_4_caracteristica4_titulo", paginaWebModa_.getSecciones().getServices().getList().get(3).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica4_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(3).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "5");
                                        editor.putString("web_servicios_seccion_4_caracteristica5_titulo", paginaWebModa_.getSecciones().getServices().getList().get(4).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica5_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(4).getDescripcion());

                                        editor.putString("web_servicios_seccion_4_recycler", "6");
                                        editor.putString("web_servicios_seccion_4_caracteristica6_titulo", paginaWebModa_.getSecciones().getServices().getList().get(5).getTitulo());
                                        editor.putString("web_servicios_seccion_4_caracteristica6_descripcion", paginaWebModa_.getSecciones().getServices().getList().get(5).getDescripcion());

                                    }

                                    //work

                                    if (paginaWebModa_.getSecciones().getWork().size() == 1){

                                        editor.putString("web_moda_seccion_3_recycler", "1");
                                        editor.putString("web_moda_seccion_3_caracteristica1_titulo", paginaWebModa_.getSecciones().getWork().get(0).getTitulo());
                                        editor.putString("web_moda_seccion_3_caracteristica1_descripcion", paginaWebModa_.getSecciones().getWork().get(0).getDescripcion());
                                        editor.putString("web_moda_seccion_3_caracteristica1_url", paginaWebModa_.getSecciones().getWork().get(0).getImagen());
                                    }
                                    else if (paginaWebModa_.getSecciones().getWork().size() == 2){

                                        editor.putString("web_moda_seccion_3_recycler", "1");
                                        editor.putString("web_moda_seccion_3_caracteristica1_titulo", paginaWebModa_.getSecciones().getWork().get(0).getTitulo());
                                        editor.putString("web_moda_seccion_3_caracteristica1_descripcion", paginaWebModa_.getSecciones().getWork().get(0).getDescripcion());
                                        editor.putString("web_moda_seccion_3_caracteristica1_url", paginaWebModa_.getSecciones().getWork().get(0).getImagen());

                                        editor.putString("web_moda_seccion_3_recycler", "2");
                                        editor.putString("web_moda_seccion_3_caracteristica2_titulo", paginaWebModa_.getSecciones().getWork().get(1).getTitulo());
                                        editor.putString("web_moda_seccion_3_caracteristica2_descripcion", paginaWebModa_.getSecciones().getWork().get(1).getDescripcion());
                                        editor.putString("web_moda_seccion_3_caracteristica2_url", paginaWebModa_.getSecciones().getWork().get(1).getImagen());

                                    }
                                    else if (paginaWebModa_.getSecciones().getWork().size() == 3){

                                        editor.putString("web_moda_seccion_3_recycler", "1");
                                        editor.putString("web_moda_seccion_3_caracteristica1_titulo", paginaWebModa_.getSecciones().getWork().get(0).getTitulo());
                                        editor.putString("web_moda_seccion_3_caracteristica1_descripcion", paginaWebModa_.getSecciones().getWork().get(0).getDescripcion());
                                        editor.putString("web_moda_seccion_3_caracteristica1_url", paginaWebModa_.getSecciones().getWork().get(0).getImagen());

                                        editor.putString("web_moda_seccion_3_recycler", "2");
                                        editor.putString("web_moda_seccion_3_caracteristica2_titulo", paginaWebModa_.getSecciones().getWork().get(1).getTitulo());
                                        editor.putString("web_moda_seccion_3_caracteristica2_descripcion", paginaWebModa_.getSecciones().getWork().get(1).getDescripcion());
                                        editor.putString("web_moda_seccion_3_caracteristica2_url", paginaWebModa_.getSecciones().getWork().get(1).getImagen());

                                        editor.putString("web_moda_seccion_3_recycler", "3");
                                        editor.putString("web_moda_seccion_3_caracteristica3_titulo", paginaWebModa_.getSecciones().getWork().get(2).getTitulo());
                                        editor.putString("web_moda_seccion_3_caracteristica3_descripcion", paginaWebModa_.getSecciones().getWork().get(2).getDescripcion());
                                        editor.putString("web_moda_seccion_3_caracteristica3_url", paginaWebModa_.getSecciones().getWork().get(2).getImagen());

                                    }

                                    // variables galleria

                                    editor.putString("web_moda_titulo_seccion5", paginaWebModa_.getSecciones().getGallery().getTitle());
                                    editor.putString("web_moda_subtitulo_seccion5", paginaWebModa_.getSecciones().getGallery().getSubtitle());


                                    if (paginaWebModa_.getSecciones().getGallery().getImagen().size() == 1){

                                        editor.putString("web_moda_img_1_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(0).getImagen());
                                    }
                                    else if (paginaWebModa_.getSecciones().getGallery().getImagen().size() == 2){

                                        editor.putString("web_moda_img_1_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(0).getImagen());

                                        editor.putString("web_moda_img_2_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(1).getImagen());
                                    }
                                    else if (paginaWebModa_.getSecciones().getGallery().getImagen().size() == 3){

                                        editor.putString("web_moda_img_1_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(0).getImagen());

                                        editor.putString("web_moda_img_2_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(1).getImagen());

                                        editor.putString("web_moda_img_3_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(2).getImagen());

                                    }
                                    else if (paginaWebModa_.getSecciones().getGallery().getImagen().size() == 4){

                                        editor.putString("web_moda_img_1_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(0).getImagen());

                                        editor.putString("web_moda_img_2_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(1).getImagen());

                                        editor.putString("web_moda_img_3_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(2).getImagen());

                                        editor.putString("web_moda_img_4_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(3).getImagen());

                                    }
                                    else if (paginaWebModa_.getSecciones().getGallery().getImagen().size() == 5){

                                        editor.putString("web_moda_img_1_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(0).getImagen());

                                        editor.putString("web_moda_img_2_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(1).getImagen());

                                        editor.putString("web_moda_img_3_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(2).getImagen());

                                        editor.putString("web_moda_img_4_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(3).getImagen());

                                        editor.putString("web_moda_img_5_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(4).getImagen());

                                    }
                                    else if (paginaWebModa_.getSecciones().getGallery().getImagen().size() == 6){

                                        editor.putString("web_moda_img_1_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(0).getImagen());

                                        editor.putString("web_moda_img_2_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(1).getImagen());

                                        editor.putString("web_moda_img_3_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(2).getImagen());

                                        editor.putString("web_moda_img_4_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(3).getImagen());

                                        editor.putString("web_moda_img_5_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(4).getImagen());

                                        editor.putString("web_moda_img_6_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(5).getImagen());

                                    }
                                    else if (paginaWebModa_.getSecciones().getGallery().getImagen().size() == 7){

                                        editor.putString("web_moda_img_1_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(0).getImagen());

                                        editor.putString("web_moda_img_2_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(1).getImagen());

                                        editor.putString("web_moda_img_3_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(2).getImagen());

                                        editor.putString("web_moda_img_4_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(3).getImagen());

                                        editor.putString("web_moda_img_5_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(4).getImagen());

                                        editor.putString("web_moda_img_6_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(5).getImagen());

                                        editor.putString("web_moda_img_7_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(6).getImagen());

                                    }
                                    else if (paginaWebModa_.getSecciones().getGallery().getImagen().size() == 8){

                                        editor.putString("web_moda_img_1_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(0).getImagen());

                                        editor.putString("web_moda_img_2_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(1).getImagen());

                                        editor.putString("web_moda_img_3_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(2).getImagen());

                                        editor.putString("web_moda_img_4_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(3).getImagen());

                                        editor.putString("web_moda_img_5_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(4).getImagen());

                                        editor.putString("web_moda_img_6_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(5).getImagen());

                                        editor.putString("web_moda_img_7_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(6).getImagen());

                                        editor.putString("web_moda_img_8_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(7).getImagen());

                                    }
                                    else if (paginaWebModa_.getSecciones().getGallery().getImagen().size() == 9){

                                        editor.putString("web_moda_img_1_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(0).getImagen());

                                        editor.putString("web_moda_img_2_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(1).getImagen());

                                        editor.putString("web_moda_img_3_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(2).getImagen());

                                        editor.putString("web_moda_img_4_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(3).getImagen());

                                        editor.putString("web_moda_img_5_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(4).getImagen());

                                        editor.putString("web_moda_img_6_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(5).getImagen());

                                        editor.putString("web_moda_img_7_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(6).getImagen());

                                        editor.putString("web_moda_img_8_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(7).getImagen());

                                        editor.putString("web_moda_img_9_seccion_5", paginaWebModa_.getSecciones().getGallery().getImagen().get(8).getImagen());

                                    }

                                    // variables contacto

                                    editor.putString("web_moda_img_seccion_6", paginaWebModa_.getSecciones().getContact().getImg());
                                    editor.putString("web_moda_titulo_seccion6", paginaWebModa_.getSecciones().getContact().getTitle());
                                    editor.putString("web_moda_direccion_seccion6", paginaWebModa_.getSecciones().getContact().getAddress());
                                    editor.putString("web_moda_telefono_seccion6", paginaWebModa_.getSecciones().getContact().getPhone());
                                    editor.putString("web_moda_email_seccion6", paginaWebModa_.getSecciones().getContact().getEmail());

                                    // variables social

                                    editor.putString("web_moda_facebook_seccion7", paginaWebModa_.getSecciones().getSocial().getFacebook());
                                    editor.putString("web_moda_instagram_seccion7", paginaWebModa_.getSecciones().getSocial().getInstagram());
                                    editor.putString("web_moda_twitter_seccion7", paginaWebModa_.getSecciones().getSocial().getTwitter());

                                    editor.commit();

                                }
                                else if(miPagina.getTipo() == 5){

                                    paginaWebSalud paginaWebSalud_ = documentopagina.toObject(paginaWebSalud.class);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.clear();

                                    editor.putString(user.getUid()+"-tipo_mi_pag_web", "5");
                                    editor.putString("nombrePagWeb", paginaWebSalud_.getUrl());

                                    //SALUD


                                    editor.putString("web_salud_img_seccion_1", paginaWebSalud_.getSecciones().getHome().getImagen());
                                    editor.putString("web_salud_titulo_home", paginaWebSalud_.getSecciones().getHome().getTitulo());
                                    editor.putString("web_salud_subtitulo_home", paginaWebSalud_.getSecciones().getHome().getSubtitulo());


                                    editor.putString("web_salud_seccion_2_titulo", paginaWebSalud_.getSecciones().getServicios().getTitulo());
                                    editor.putString("web_salud_seccion_2_descripcion", paginaWebSalud_.getSecciones().getServicios().getDescripcion());


                                    if (paginaWebSalud_.getSecciones().getServicios().getServicio().size() == 1){

                                        editor.putString("web_salud_seccion_2_recycler", "1");
                                        editor.putString("web_salud_seccion_2_caracteristica1_titulo", paginaWebSalud_.getSecciones().getServicios().getServicio().get(0).getTitulo());
                                        editor.putString("web_salud_seccion_2_caracteristica1_descripcion", paginaWebSalud_.getSecciones().getServicios().getServicio().get(0).getDescripcion());

                                    }
                                    else if (paginaWebSalud_.getSecciones().getServicios().getServicio().size() == 2){

                                        editor.putString("web_salud_seccion_2_recycler", "1");
                                        editor.putString("web_salud_seccion_2_caracteristica1_titulo", paginaWebSalud_.getSecciones().getServicios().getServicio().get(0).getTitulo());
                                        editor.putString("web_salud_seccion_2_caracteristica1_descripcion", paginaWebSalud_.getSecciones().getServicios().getServicio().get(0).getDescripcion());

                                        editor.putString("web_salud_seccion_2_recycler", "2");
                                        editor.putString("web_salud_seccion_2_caracteristica2_titulo", paginaWebSalud_.getSecciones().getServicios().getServicio().get(1).getTitulo());
                                        editor.putString("web_salud_seccion_2_caracteristica2_descripcion", paginaWebSalud_.getSecciones().getServicios().getServicio().get(1).getDescripcion());

                                    }
                                    else if (paginaWebSalud_.getSecciones().getServicios().getServicio().size() == 3){

                                        editor.putString("web_salud_seccion_2_recycler", "1");
                                        editor.putString("web_salud_seccion_2_caracteristica1_titulo", paginaWebSalud_.getSecciones().getServicios().getServicio().get(0).getTitulo());
                                        editor.putString("web_salud_seccion_2_caracteristica1_descripcion", paginaWebSalud_.getSecciones().getServicios().getServicio().get(0).getDescripcion());

                                        editor.putString("web_salud_seccion_2_recycler", "2");
                                        editor.putString("web_salud_seccion_2_caracteristica2_titulo", paginaWebSalud_.getSecciones().getServicios().getServicio().get(1).getTitulo());
                                        editor.putString("web_salud_seccion_2_caracteristica2_descripcion", paginaWebSalud_.getSecciones().getServicios().getServicio().get(1).getDescripcion());

                                        editor.putString("web_salud_seccion_2_recycler", "3");
                                        editor.putString("web_salud_seccion_2_caracteristica3_titulo", paginaWebSalud_.getSecciones().getServicios().getServicio().get(2).getTitulo());
                                        editor.putString("web_salud_seccion_2_caracteristica3_descripcion", paginaWebSalud_.getSecciones().getServicios().getServicio().get(2).getDescripcion());

                                    }
                                    else if (paginaWebSalud_.getSecciones().getServicios().getServicio().size() == 4){

                                        editor.putString("web_salud_seccion_2_recycler", "1");
                                        editor.putString("web_salud_seccion_2_caracteristica1_titulo", paginaWebSalud_.getSecciones().getServicios().getServicio().get(0).getTitulo());
                                        editor.putString("web_salud_seccion_2_caracteristica1_descripcion", paginaWebSalud_.getSecciones().getServicios().getServicio().get(0).getDescripcion());

                                        editor.putString("web_salud_seccion_2_recycler", "2");
                                        editor.putString("web_salud_seccion_2_caracteristica2_titulo", paginaWebSalud_.getSecciones().getServicios().getServicio().get(1).getTitulo());
                                        editor.putString("web_salud_seccion_2_caracteristica2_descripcion", paginaWebSalud_.getSecciones().getServicios().getServicio().get(1).getDescripcion());

                                        editor.putString("web_salud_seccion_2_recycler", "3");
                                        editor.putString("web_salud_seccion_2_caracteristica3_titulo", paginaWebSalud_.getSecciones().getServicios().getServicio().get(2).getTitulo());
                                        editor.putString("web_salud_seccion_2_caracteristica3_descripcion", paginaWebSalud_.getSecciones().getServicios().getServicio().get(2).getDescripcion());

                                        editor.putString("web_salud_seccion_2_recycler", "4");
                                        editor.putString("web_salud_seccion_2_caracteristica4_titulo", paginaWebSalud_.getSecciones().getServicios().getServicio().get(3).getTitulo());
                                        editor.putString("web_salud_seccion_2_caracteristica4_descripcion", paginaWebSalud_.getSecciones().getServicios().getServicio().get(3).getDescripcion());

                                    }

                                    // variables horarios


                                    if (paginaWebSalud_.getSecciones().getHorario().getHora().size() == 1){

                                        editor.putString("web_salud_seccion_3_recycler", "1");
                                        editor.putString("web_salud_seccion_3_caracteristica1_titulo", paginaWebSalud_.getSecciones().getHorario().getHora().get(0).getDia());
                                        editor.putString("web_salud_seccion_3_caracteristica1_descripcion", paginaWebSalud_.getSecciones().getHorario().getHora().get(0).getDehora());

                                    }
                                    else if (paginaWebSalud_.getSecciones().getHorario().getHora().size() == 2){

                                        editor.putString("web_salud_seccion_3_recycler", "1");
                                        editor.putString("web_salud_seccion_3_caracteristica1_titulo",paginaWebSalud_.getSecciones().getHorario().getHora().get(0).getDia());
                                        editor.putString("web_salud_seccion_3_caracteristica1_descripcion",paginaWebSalud_.getSecciones().getHorario().getHora().get(0).getDehora());

                                        editor.putString("web_salud_seccion_3_recycler", "2");
                                        editor.putString("web_salud_seccion_3_caracteristica2_titulo",paginaWebSalud_.getSecciones().getHorario().getHora().get(1).getDia());
                                        editor.putString("web_salud_seccion_3_caracteristica2_descripcion",paginaWebSalud_.getSecciones().getHorario().getHora().get(1).getDehora());

                                    }
                                    else if (paginaWebSalud_.getSecciones().getHorario().getHora().size() == 3){

                                        editor.putString("web_salud_seccion_3_recycler", "1");
                                        editor.putString("web_salud_seccion_3_caracteristica1_titulo", paginaWebSalud_.getSecciones().getHorario().getHora().get(0).getDia());
                                        editor.putString("web_salud_seccion_3_caracteristica1_descripcion", paginaWebSalud_.getSecciones().getHorario().getHora().get(0).getDehora());

                                        editor.putString("web_salud_seccion_3_recycler", "2");
                                        editor.putString("web_salud_seccion_3_caracteristica2_titulo", paginaWebSalud_.getSecciones().getHorario().getHora().get(1).getDia());
                                        editor.putString("web_salud_seccion_3_caracteristica2_descripcion", paginaWebSalud_.getSecciones().getHorario().getHora().get(1).getDehora());

                                        editor.putString("web_salud_seccion_3_recycler", "3");
                                        editor.putString("web_salud_seccion_3_caracteristica3_titulo", paginaWebSalud_.getSecciones().getHorario().getHora().get(2).getDia());
                                        editor.putString("web_salud_seccion_3_caracteristica3_descripcion", paginaWebSalud_.getSecciones().getHorario().getHora().get(2).getDehora());

                                    }
                                    else if (paginaWebSalud_.getSecciones().getHorario().getHora().size() == 4){

                                        editor.putString("web_salud_seccion_3_recycler", "1");
                                        editor.putString("web_salud_seccion_3_caracteristica1_titulo", paginaWebSalud_.getSecciones().getHorario().getHora().get(0).getDia());
                                        editor.putString("web_salud_seccion_3_caracteristica1_descripcion", paginaWebSalud_.getSecciones().getHorario().getHora().get(0).getDehora());

                                        editor.putString("web_salud_seccion_3_recycler", "2");
                                        editor.putString("web_salud_seccion_3_caracteristica2_titulo", paginaWebSalud_.getSecciones().getHorario().getHora().get(1).getDia());
                                        editor.putString("web_salud_seccion_3_caracteristica2_descripcion", paginaWebSalud_.getSecciones().getHorario().getHora().get(1).getDehora());

                                        editor.putString("web_salud_seccion_3_recycler", "3");
                                        editor.putString("web_salud_seccion_3_caracteristica3_titulo", paginaWebSalud_.getSecciones().getHorario().getHora().get(2).getDia());
                                        editor.putString("web_salud_seccion_3_caracteristica3_descripcion", paginaWebSalud_.getSecciones().getHorario().getHora().get(2).getDehora());

                                        editor.putString("web_salud_seccion_3_recycler", "4");
                                        editor.putString("web_salud_seccion_3_caracteristica4_titulo", paginaWebSalud_.getSecciones().getHorario().getHora().get(3).getDia());
                                        editor.putString("web_salud_seccion_3_caracteristica4_descripcion", paginaWebSalud_.getSecciones().getHorario().getHora().get(3).getDehora());

                                    }

                                    // variables about

                                    editor.putString("web_salud_seccion_4_titulo", paginaWebSalud_.getSecciones().getAbout().getTitulo());
                                    editor.putString("web_salud_seccion_4_descripcion", paginaWebSalud_.getSecciones().getAbout().getDescripcion());


                                    if (paginaWebSalud_.getSecciones().getAbout().getCaracteristicas().size() == 1){

                                        editor.putString("web_salud_seccion_4_recycler", "1");
                                        editor.putString("web_salud_seccion_4_caracteristica1_titulo", paginaWebSalud_.getSecciones().getAbout().getCaracteristicas().get(0).getTitulo());
                                        editor.putString("web_salud_seccion_4_caracteristica1_descripcion", paginaWebSalud_.getSecciones().getAbout().getCaracteristicas().get(0).getDescripcion());

                                    }
                                    else if (paginaWebSalud_.getSecciones().getAbout().getCaracteristicas().size() == 2){

                                        editor.putString("web_salud_seccion_4_recycler", "1");
                                        editor.putString("web_salud_seccion_4_caracteristica1_titulo", paginaWebSalud_.getSecciones().getAbout().getCaracteristicas().get(0).getTitulo());
                                        editor.putString("web_salud_seccion_4_caracteristica1_descripcion", paginaWebSalud_.getSecciones().getAbout().getCaracteristicas().get(0).getDescripcion());

                                        editor.putString("web_salud_seccion_4_recycler", "2");
                                        editor.putString("web_salud_seccion_4_caracteristica2_titulo", paginaWebSalud_.getSecciones().getAbout().getCaracteristicas().get(1).getTitulo());
                                        editor.putString("web_salud_seccion_4_caracteristica2_descripcion", paginaWebSalud_.getSecciones().getAbout().getCaracteristicas().get(1).getDescripcion());

                                    }
                                    else if (paginaWebSalud_.getSecciones().getAbout().getCaracteristicas().size() == 3){

                                        editor.putString("web_salud_seccion_4_recycler", "1");
                                        editor.putString("web_salud_seccion_4_caracteristica1_titulo", paginaWebSalud_.getSecciones().getAbout().getCaracteristicas().get(0).getTitulo());
                                        editor.putString("web_salud_seccion_4_caracteristica1_descripcion", paginaWebSalud_.getSecciones().getAbout().getCaracteristicas().get(0).getDescripcion());

                                        editor.putString("web_salud_seccion_4_recycler", "2");
                                        editor.putString("web_salud_seccion_4_caracteristica2_titulo", paginaWebSalud_.getSecciones().getAbout().getCaracteristicas().get(1).getTitulo());
                                        editor.putString("web_salud_seccion_4_caracteristica2_descripcion", paginaWebSalud_.getSecciones().getAbout().getCaracteristicas().get(1).getDescripcion());

                                        editor.putString("web_salud_seccion_4_recycler", "3");
                                        editor.putString("web_salud_seccion_4_caracteristica3_titulo", paginaWebSalud_.getSecciones().getAbout().getCaracteristicas().get(2).getTitulo());
                                        editor.putString("web_salud_seccion_4_caracteristica3_descripcion", paginaWebSalud_.getSecciones().getAbout().getCaracteristicas().get(2).getDescripcion());

                                    }

                                    // variables banner

                                    editor.putString("web_salud_titulo_baner", paginaWebSalud_.getSecciones().getBanner().getTitulo());
                                    editor.putString("web_salud_frase_baner", paginaWebSalud_.getSecciones().getBanner().getDescripcion());
                                    editor.putString("web_salud_autor_baner", paginaWebSalud_.getSecciones().getBanner().getAutor());

                                    // variables contacto

                                    editor.putString("web_salud_ubicacion_contacto", paginaWebSalud_.getSecciones().getContacto().getLugar());
                                    editor.putString("web_salud_email_contacto", paginaWebSalud_.getSecciones().getContacto().getEmail());
                                    editor.putString("web_salud_telefono_contacto", paginaWebSalud_.getSecciones().getContacto().getTelefono());
                                    editor.putString("web_salud_titulo_nav_contacto",paginaWebSalud_.getSecciones().getContacto().getNavbar());

                                    // variables sociales

                                    editor.putString("web_salud_facebook_seccion_7", paginaWebSalud_.getSecciones().getSociales().getFacebook());
                                    editor.putString("web_salud_instagram_seccion_7", paginaWebSalud_.getSecciones().getSociales().getInstagram());
                                    editor.putString("web_salud_twitter_seccion_7", paginaWebSalud_.getSecciones().getSociales().getTwitter());

                                    editor.commit();

                                }
                                else if(miPagina.getTipo() == 6){

                                    paginaWebAplicaciones paginaWebAplicaciones_ = documentopagina.toObject(paginaWebAplicaciones.class);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.clear();

                                    editor.putString(user.getUid()+"-tipo_mi_pag_web", "6");
                                    editor.putString("nombrePagWeb", paginaWebAplicaciones_.getUrl());

                                    //APPS


                                    //variables home

                                    editor.putString("web_apps_img_seccion_1", paginaWebAplicaciones_.getSecciones().getHome().getImagen());
                                    editor.putString("web_apps_titulo_home", paginaWebAplicaciones_.getSecciones().getHome().getTitulo());

                                    //variables descarga

                                    editor.putString("web_apps_titulo_seccion_2", paginaWebAplicaciones_.getSecciones().getDescargas().getTitulo());
                                    editor.putString("web_apps_subtitulo_seccion_2", paginaWebAplicaciones_.getSecciones().getDescargas().getSubtitulo());
                                    editor.putString("web_apps_google_seccion_2", paginaWebAplicaciones_.getSecciones().getDescargas().getBotonplay());
                                    editor.putString("web_apps_apple_seccion_2", paginaWebAplicaciones_.getSecciones().getDescargas().getBotonaps());

                                    // variables servicios

                                    editor.putString("web_apps_img_seccion_3", paginaWebAplicaciones_.getSecciones().getServicios().getImagen());
                                    editor.putString("web_apps_titulo_seccion_3", paginaWebAplicaciones_.getSecciones().getServicios().getTitulo());
                                    editor.putString("web_apps_titulo_1_seccion_3", paginaWebAplicaciones_.getSecciones().getServicios().getServiciuno());
                                    editor.putString("web_apps_subtitulo_1_seccion_3", paginaWebAplicaciones_.getSecciones().getServicios().getSubtitulouno());
                                    editor.putString("web_apps_titulo_2_seccion_3", paginaWebAplicaciones_.getSecciones().getServicios().getServiciodos());
                                    editor.putString("web_apps_subtitulo_2_seccion_3", paginaWebAplicaciones_.getSecciones().getServicios().getSubtitulodos());
                                    editor.putString("web_apps_titulo_3_seccion_3", paginaWebAplicaciones_.getSecciones().getServicios().getServiciotres());
                                    editor.putString("web_apps_subtitulo_3_seccion_3", paginaWebAplicaciones_.getSecciones().getServicios().getSubtitulotres());
                                    editor.putString("web_apps_titulo_4_seccion_3", paginaWebAplicaciones_.getSecciones().getServicios().getServiciocuatro());
                                    editor.putString("web_apps_subtitulo_4_seccion_3", paginaWebAplicaciones_.getSecciones().getServicios().getSubtitulocuatro());

                                    //variable banner
                                    String banner_titulo = "";

                                    editor.putString("web_apps_titulo_seccion_4", paginaWebAplicaciones_.getSecciones().getBanner().getTitulo());

                                    //variable contacto

                                    editor.putString("web_apps_facebook_seccion_5", paginaWebAplicaciones_.getSecciones().getContacto().getFacebook());
                                    editor.putString("web_apps_twitter_seccion_5", paginaWebAplicaciones_.getSecciones().getContacto().getTwitter());
                                    editor.putString("web_apps_titulo_seccion_5", paginaWebAplicaciones_.getSecciones().getContacto().getTitulo());
                                    editor.putString("web_apps_instagram_seccio_5",paginaWebAplicaciones_.getSecciones().getContacto().getGoogle());

                                    editor.commit();
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Puedes crear tu página web, elige una de estas categorías",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

        speedDialView.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                //TODO: Start some activity
                int id = menuItem.getItemId();

                if(id == R.id.action_ver_mi_pag){
                    if(sharedPreferences.getString(user.getUid()+ "-tipo_mi_pag_web", "").equals("1")){
                        if(sharedPreferences.getString("nombrePagWeb","").length() > 0){
                            String url = "http://food.solucionescolabora.com/u/" + sharedPreferences.getString("nombrePagWeb", "");
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    }
                    else if(sharedPreferences.getString(user.getUid()+"-tipo_mi_pag_web", "").equals("2")){
                        if(sharedPreferences.getString("nombrePagWeb","").length() > 0){
                            String url = "http://products.solucionescolabora.com/u/" + sharedPreferences.getString("nombrePagWeb", "");
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    }
                    else if(sharedPreferences.getString(user.getUid()+"-tipo_mi_pag_web", "").equals("3")){
                        if(sharedPreferences.getString("nombrePagWeb","").length() > 0){
                            String url = "http://services.solucionescolabora.com/u/" + sharedPreferences.getString("nombrePagWeb", "");
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    }
                    else if(sharedPreferences.getString(user.getUid()+"-tipo_mi_pag_web", "").equals("4")){
                        if(sharedPreferences.getString("nombrePagWeb","").length() > 0){
                            String url = "http://fashion.solucionescolabora.com/u/" + sharedPreferences.getString("nombrePagWeb", "");
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    }
                    else if(sharedPreferences.getString(user.getUid()+"-tipo_mi_pag_web", "").equals("5")){
                        if(sharedPreferences.getString("nombrePagWeb","").length() > 0){
                            String url = "http://health.solucionescolabora.com/u/" + sharedPreferences.getString("nombrePagWeb", "");
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    }
                    else if(sharedPreferences.getString(user.getUid()+"-tipo_mi_pag_web", "").equals("6")){
                        if(sharedPreferences.getString("nombrePagWeb","").length() > 0){
                            String url = "http://apps.solucionescolabora.com/u/" + sharedPreferences.getString("nombrePagWeb", "");
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Aún no tienes desarrollada tu página web", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                else if(id == R.id.action_editar_mi_pag){
                    if(sharedPreferences.getString(user.getUid()+"-tipo_mi_pag_web", "").equals("1")){
                        Intent i = new Intent(MenuPagWebActivity.this, WebsComidaSeccion1.class);
                        startActivity(i);
                    }
                    else if(sharedPreferences.getString(user.getUid()+"-tipo_mi_pag_web", "").equals("2")){
                        Intent i = new Intent(MenuPagWebActivity.this, WebsProductosSeccion1.class);
                        startActivity(i);
                    }
                    else if(sharedPreferences.getString(user.getUid()+"-tipo_mi_pag_web", "").equals("3")){
                        Intent i = new Intent(MenuPagWebActivity.this, WebsServiciosSeccion1.class);
                        startActivity(i);
                    }
                    else if(sharedPreferences.getString(user.getUid()+"-tipo_mi_pag_web", "").equals("4")){
                        Intent i = new Intent(MenuPagWebActivity.this, WebsModaSeccion1.class);
                        startActivity(i);
                    }
                    else if(sharedPreferences.getString(user.getUid()+"-tipo_mi_pag_web", "").equals("5")){
                        Intent i = new Intent(MenuPagWebActivity.this, WebsSaludSeccion1.class);
                        startActivity(i);
                    }
                    else if(sharedPreferences.getString(user.getUid()+"-tipo_mi_pag_web", "").equals("6")){
                        Intent i = new Intent(MenuPagWebActivity.this, WebAppsSeccion1Activity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Aún no tienes desarrollada tu página web", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });

        imgComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPagWebActivity.this, WebVisualizacionPreviaActivity.class);
                i.putExtra(WebVisualizacionPreviaActivity.PAG_WEB, "comida");
                startActivity(i);
            }
        });

        imgServicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPagWebActivity.this, WebVisualizacionPreviaActivity.class);
                i.putExtra(WebVisualizacionPreviaActivity.PAG_WEB, "servicios");
                startActivity(i);
            }
        });

        imgProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPagWebActivity.this, WebVisualizacionPreviaActivity.class);
                i.putExtra(WebVisualizacionPreviaActivity.PAG_WEB, "productos");
                startActivity(i);
            }
        });

        imgSalud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPagWebActivity.this, WebVisualizacionPreviaActivity.class);
                i.putExtra(WebVisualizacionPreviaActivity.PAG_WEB, "salud");
                startActivity(i);
            }
        });

        imgAplicaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPagWebActivity.this, WebVisualizacionPreviaActivity.class);
                i.putExtra(WebVisualizacionPreviaActivity.PAG_WEB, "aplicaciones");
                startActivity(i);
            }
        });

        imgModa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPagWebActivity.this, WebVisualizacionPreviaActivity.class);
                i.putExtra(WebVisualizacionPreviaActivity.PAG_WEB, "moda");
                startActivity(i);
            }
        });
    }

    private void verificarUsername(){

        String id = FirebaseAuth.getInstance().getUid();
        miUsuario = null;
        progressDialog.show();

        db.collection("Usuarios")
                .whereEqualTo("id", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Mooch_info", document.getId() + " => " + document.getData());
                                miUsuario = document.toObject(Usuario.class);
                            }

                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }

                            if(miUsuario != null){

                                if(miUsuario.getTipoPagWeb().equals("servicios")){

                                }

                                else if(miUsuario.getTipoPagWeb().equals("servicios")){

                                }
                                else if(miUsuario.getTipoPagWeb().equals("servicios")){

                                }
                                else if(miUsuario.getTipoPagWeb().equals("servicios")){

                                }
                                else if(miUsuario.getTipoPagWeb().equals("servicios")){

                                }
                                else if(miUsuario.getTipoPagWeb().equals("servicios")){

                                }
                            }
                            else{
                                // Aqui guardo que ya no es necesario seguir buscando
                            }

                        } else {
                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                            Toast.makeText(getApplicationContext(), "Ha ocurrido un error, por favor verifique su conexión a internet", Toast.LENGTH_LONG).show();
                            Log.d("Mooch_info", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void getInfoPagWeb(String nombrePagWeb){
        db.collection("webs").document(nombrePagWeb).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                    } else {
                        //Log.d(TAG, "No such document");

                    }
                } else {
                    //Log.d(TAG, "get failed with ", task.getException());

                }
            }
        });
    }

    public static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[])obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>)obj);
        }
        return list;
    }

}
