package com.colabora.soluciones.convocatoriafeyac.web.aplicaciones;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.colabora.soluciones.convocatoriafeyac.LoginActivity;
import com.colabora.soluciones.convocatoriafeyac.MainActivity;
import com.colabora.soluciones.convocatoriafeyac.Modelos.pagWebs;
import com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebAplicaciones.paginaWebAplicaciones;
import com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebComida.paginaWebComida;
import com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebModa.paginaWebModa;
import com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebProductos.paginaWebProductos;
import com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebSalud.paginaWebSalud;
import com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebServicios.paginaWebServicios;
import com.colabora.soluciones.convocatoriafeyac.R;
import com.colabora.soluciones.convocatoriafeyac.web.MenuPagWebActivity;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class WebsAppsSeccion5 extends AppCompatActivity {

    private Button btnSiguiente;
    private Button btnBackMenu;
    private TextInputEditText editFacebook;
    private TextInputEditText editTwitter;
    private TextInputEditText editTitulo;
    private TextInputEditText editInstagram;

    private String facebook = "";
    private String instagram = "";
    private String twitter = "";
    private String titulo = "";

    private ProgressDialog progressDialog;
    private FirebaseFirestore db;

    private Map<String, Object> web;
    private Map<String, Object> banner;
    private Map<String, Object> contacto;
    private Map<String, Object> descargas;
    private Map<String, Object> home;
    private Map<String, Object> servicios;

    // variables servicios
    private String servicios_titulo1 = "";
    private String servicios_titulo2 = "";
    private String servicios_titulo3 = "";
    private String servicios_titulo4 = "";
    private String servicios_descripcion1 = "";
    private String servicios_descripcion2 = "";
    private String servicios_descripcion3 = "";
    private String servicios_descripcion4 = "";
    private String servicios_imagen = "";
    private String servicios_titulo = "";

    private String home_titulo = "";
    private String home_imagen = "";
    private String home_nav = "";

    private String descargas_google = "";
    private String descargas_apple = "";
    private String descargas_titulo = "";
    private String descargas_subtitulo = "";

    private String contacto_facebook = "";
    private String contacto_twitter = "";
    private String contacto_google = "";
    private String contacto_titulo = "";

    private String banner_titulo = "";
    private SharedPreferences sharedPreferences;
    private String idUser;
    private pagWebs miPagina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webs_apps_seccion5);

        btnSiguiente = (Button)findViewById(R.id.btn_web_app_siguiente_seccion5);
        editFacebook = (TextInputEditText)findViewById(R.id.txt_web_apps_seccion5_facebook);
        editTwitter = (TextInputEditText)findViewById(R.id.txt_web_apps_seccion5_twitter);
        editTitulo = (TextInputEditText)findViewById(R.id.txt_web_apps_seccion5_titulo);
        editInstagram = (TextInputEditText)findViewById(R.id.txt_web_apps_seccion5_instagram);
        btnBackMenu = (Button)findViewById(R.id.btn_web_app_finalizar_seccion5);

        sharedPreferences = getSharedPreferences("misDatos", 0);
        editFacebook.setText(sharedPreferences.getString("web_apps_facebook_seccion_5", ""));
        editTwitter.setText(sharedPreferences.getString("web_apps_twitter_seccion_5", ""));
        editTitulo.setText(sharedPreferences.getString("web_apps_titulo_seccion_5", ""));
        editInstagram.setText(sharedPreferences.getString("web_apps_instagram_seccio_5",""));

        editTitulo.setSelection(editTitulo.getText().length());
        editTwitter.setSelection(editTwitter.getText().length());
        editFacebook.setSelection(editFacebook.getText().length());
        editInstagram.setSelection(editInstagram.getText().length());



        db = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(WebsAppsSeccion5.this);

        progressDialog.setTitle("Subiendo Información");
        progressDialog.setMessage("Espere un momento mientras el sistema sube su información a la base de datos");

        btnBackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(WebsAppsSeccion5.this);
                builder.setTitle("Aviso");
                builder.setMessage("¿Estas seguro de querer volver al menú principal?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent intent = new Intent(getApplicationContext(), MenuPagWebActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                // Create the AlertDialog object and return it
                builder.create();
                builder.show();
            }
        });


        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(WebsAppsSeccion5.this);
                builder.setTitle("Aviso");
                builder.setMessage("¿Estas seguro de finalizar?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                facebook = editFacebook.getText().toString();
                                twitter = editTwitter.getText().toString();
                                titulo = editTitulo.getText().toString();
                                instagram = editInstagram.getText().toString();

                                if(titulo.length() > 0){
                                    // *********** Guardamos los principales datos de los nuevos usuarios *************
                                    sharedPreferences = getSharedPreferences("misDatos", 0);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("web_apps_facebook_seccion_5", facebook);
                                    editor.putString("web_apps_twitter_seccion_5", twitter);
                                    editor.putString("web_apps_titulo_seccion_5", titulo);
                                    editor.putString("web_apps_instagram_seccio_5",instagram);
                                    editor.commit();
                                    // ******************************************************************************
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Debes introducir un título para continuar", Toast.LENGTH_SHORT).show();
                                    return;
                                }


                                progressDialog.show();

                                web = new HashMap<>();
                                home = new HashMap<>();
                                banner = new HashMap<>();
                                contacto = new HashMap<>();
                                descargas = new HashMap<>();
                                servicios = new HashMap<>();

                                idUser = sharedPreferences.getString("UUID", "");

                                home_titulo = sharedPreferences.getString("web_apps_titulo_home", "");
                                home_imagen = sharedPreferences.getString("web_apps_img_seccion_1", "");
                                home_nav = "Inicio";

                                servicios_titulo1 = sharedPreferences.getString("web_apps_titulo_1_seccion_3","");
                                servicios_titulo2 = sharedPreferences.getString("web_apps_titulo_2_seccion_3","");
                                servicios_titulo3 = sharedPreferences.getString("web_apps_titulo_3_seccion_3","");
                                servicios_titulo4 = sharedPreferences.getString("web_apps_titulo_4_seccion_3","");
                                servicios_descripcion1 = sharedPreferences.getString("web_apps_subtitulo_1_seccion_3", "");
                                servicios_descripcion2 = sharedPreferences.getString("web_apps_subtitulo_2_seccion_3", "");
                                servicios_descripcion3 = sharedPreferences.getString("web_apps_subtitulo_3_seccion_3", "");
                                servicios_descripcion4 = sharedPreferences.getString("web_apps_subtitulo_4_seccion_3", "");
                                servicios_imagen = sharedPreferences.getString("web_apps_img_seccion_3", "");
                                servicios_titulo = sharedPreferences.getString("web_apps_titulo_seccion_3", "");

                                descargas_google = sharedPreferences.getString("web_apps_google_seccion_2","");
                                descargas_apple = sharedPreferences.getString("web_apps_apple_seccion_2","");
                                descargas_titulo = sharedPreferences.getString("web_apps_titulo_seccion_2","");
                                descargas_subtitulo = sharedPreferences.getString("web_apps_subtitulo_seccion_2","");

                                contacto_facebook = sharedPreferences.getString("web_apps_facebook_seccion_5","");
                                contacto_twitter = sharedPreferences.getString("web_apps_twitter_seccion_5","");
                                contacto_google = sharedPreferences.getString("web_apps_instagram_seccio_5","");
                                contacto_titulo = sharedPreferences.getString("web_apps_titulo_seccion_5","");

                                banner_titulo = sharedPreferences.getString("web_apps_titulo_seccion_4","");

                                banner.put("titulo", banner_titulo);

                                contacto.put("facebook", contacto_facebook);
                                contacto.put("twitter", contacto_twitter);
                                contacto.put("google", contacto_google);
                                contacto.put("titulo", contacto_titulo);

                                descargas.put("botonaps", descargas_apple);
                                descargas.put("botonplay", descargas_google);
                                descargas.put("subtitulo", descargas_subtitulo);
                                descargas.put("titulo", descargas_titulo);

                                home.put("imagen", home_imagen);
                                home.put("titulo", home_titulo);
                                home.put("navbar",home_nav);

                                servicios.put("imagen", servicios_imagen);
                                servicios.put("serviciocuatro", servicios_titulo4);
                                servicios.put("serviciodos", servicios_titulo2);
                                servicios.put("serviciotres", servicios_titulo3);
                                servicios.put("serviciuno", servicios_titulo1);
                                servicios.put("subtitulocuatro", servicios_descripcion4);
                                servicios.put("subtitulotres", servicios_descripcion3);
                                servicios.put("subtitulodos", servicios_descripcion2);
                                servicios.put("subtitulouno", servicios_descripcion1);
                                servicios.put("titulo", servicios_titulo);

                                web.put("banner", banner);
                                web.put("contacto", contacto);
                                web.put("descargas", descargas);
                                web.put("home", home);
                                web.put("servicios", servicios);

                                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String url = sharedPreferences.getString("nombrePagWeb", "");
                                pagWebs pag = new pagWebs("",web, 6, user.getUid(), url);

                                db.collection("webs").document(sharedPreferences.getString("nombrePagWeb", ""))
                                        .set(pag)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                if(progressDialog.isShowing()){
                                                    progressDialog.dismiss();
                                                }
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putString(user.getUid() + "-tipo_mi_pag_web", "6");
                                                editor.commit();
                                                Toast.makeText(getApplicationContext(),"¡Página web creada exitosamente!", Toast.LENGTH_LONG).show();
                                                String url = "http://apps.solucionescolabora.com/u/" + sharedPreferences.getString("nombrePagWeb", "");
                                                Intent i = new Intent(Intent.ACTION_VIEW);
                                                i.setData(Uri.parse(url));
                                                startActivity(i);
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                if(progressDialog.isShowing()){
                                                    progressDialog.dismiss();
                                                }
                                                Toast.makeText(getApplicationContext(),"Ha ocurrido un error, favor de volver a intentar", Toast.LENGTH_LONG).show();
                                            }
                                        });


                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                // Create the AlertDialog object and return it
                builder.create();
                builder.show();


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(WebsAppsSeccion5.this, WebsAppsSeccion4.class);
        startActivity(i);
        finish();
    }
}
