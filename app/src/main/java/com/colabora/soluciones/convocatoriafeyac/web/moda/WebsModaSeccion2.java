package com.colabora.soluciones.convocatoriafeyac.web.moda;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.colabora.soluciones.convocatoriafeyac.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.ByteArrayOutputStream;

public class WebsModaSeccion2 extends AppCompatActivity {

    private Button btnSiguiente;
    private Button btnSubirFoto;
    private TextInputEditText editTitulo;
    private TextInputEditText editSubtitulo;
    private TextInputEditText editDescripcion;
    private ImageView img;
    private String nombre_web = "";
    private String titulo = "";
    private String subtitulo = "";
    private String descripcion = "";
    private boolean imgUpoloaded = false;
    private ImageView portada_seccion2;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    private ProgressDialog progressDialog;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webs_moda_seccion2);

        btnSiguiente = (Button)findViewById(R.id.btnModaSeccion2Siguiente);
        btnSubirFoto = (Button)findViewById(R.id.btnModaSeccion2);
        img = (ImageView)findViewById(R.id.imgModaSeccion2);
        editTitulo = (TextInputEditText)findViewById(R.id.txt_web_moda_seccion2_texto1);
        editSubtitulo = (TextInputEditText)findViewById(R.id.txt_web_moda_seccion2_subitutlo_1);
        editDescripcion = (TextInputEditText)findViewById(R.id.txt_web_moda_seccion2_descripcion_1);
        portada_seccion2 = (ImageView)findViewById(R.id.portada_moda_seccion2);

        portada_seccion2.setImageResource(R.drawable.web_moda_seccion_2);

        progressDialog = new ProgressDialog(WebsModaSeccion2.this);

        progressDialog.setTitle("Subiendo Información");
        progressDialog.setMessage("Espere un momento mientras el sistema sube su información a la base de datos");

        sharedPreferences = getSharedPreferences("misDatos", 0);
        nombre_web = sharedPreferences.getString("nombrePagWeb","");


        editTitulo.setText(sharedPreferences.getString("web_moda_titulo_seccion_2", ""));
        editSubtitulo.setText(sharedPreferences.getString("web_moda_subtitulo_seccion_2", ""));
        editDescripcion.setText(sharedPreferences.getString("web_moda_descripcion_seccion_2", ""));

        editTitulo.setSelection(editTitulo.getText().length());
        editSubtitulo.setSelection(editSubtitulo.getText().length());
        editDescripcion.setSelection(editDescripcion.getText().length());

        if (sharedPreferences.getString("web_moda_img_seccion_2","").length() > 1){
            imgUpoloaded = true;
            Picasso.get().load(sharedPreferences.getString("web_moda_img_seccion_2","")).placeholder(R.drawable.progress_animation).into(img);
        }

        btnSubirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(new PickSetup().setTitle("Escoge una opción")
                        .setCameraButtonText("Cámara")
                        .setGalleryButtonText("Galería"))
                        .setOnPickResult(new IPickResult() {
                            @Override
                            public void onPickResult(PickResult r) {
                                //TODO: do what you have to...
                                img.setImageBitmap(r.getBitmap());
                                progressDialog.show();

                                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                r.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                                byte[] data = outputStream.toByteArray();

                                StorageReference refSubirImagen = storage.getReferenceFromUrl("gs://pyme-assistant.appspot.com/web/userid").child(nombre_web + "imagen2");

                                UploadTask uploadTask = refSubirImagen.putBytes(data);
                                uploadTask.addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        if (progressDialog.isShowing()){
                                            progressDialog.dismiss();
                                        }
                                        Toast.makeText(getApplicationContext(), "Ha ocurrido un error, por favor vuelva a intentarlo", Toast.LENGTH_LONG).show();
                                    }
                                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        if (progressDialog.isShowing()){
                                            progressDialog.dismiss();
                                        }
                                        Toast.makeText(getApplicationContext(), "Imagen subida exitosamente", Toast.LENGTH_LONG).show();
                                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                                        while(!uri.isComplete());
                                        Uri url = uri.getResult();
                                        // *********** Guardamos los principales datos de los nuevos usuarios *************
                                        sharedPreferences = getSharedPreferences("misDatos", 0);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("web_moda_img_seccion_2", url.toString());
                                        editor.commit();
                                        imgUpoloaded = true;
                                        // ******************************************************************************
                                    }
                                });

                            }
                        })
                        .setOnPickCancel(new IPickCancel() {
                            @Override
                            public void onCancelClick() {
                                //TODO: do what you have to if user clicked cancel
                            }
                        }).show(getSupportFragmentManager());
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titulo = editTitulo.getText().toString();
                subtitulo = editSubtitulo.getText().toString();
                descripcion = editDescripcion.getText().toString();

                if(!imgUpoloaded){
                    Toast.makeText(getApplicationContext(), "Para continuar debes subir la imagen que irá en está sección", Toast.LENGTH_LONG).show();
                    return;
                }

                if(titulo.length() > 0 && subtitulo.length() > 0){
                    // *********** Guardamos los principales datos de los nuevos usuarios *************
                    sharedPreferences = getSharedPreferences("misDatos", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("web_moda_titulo_seccion_2", titulo);
                    editor.putString("web_moda_subtitulo_seccion_2", subtitulo);
                    editor.putString("web_moda_descripcion_seccion_2", descripcion);
                    editor.commit();
                    // ******************************************************************************
                }
                else{
                    Toast.makeText(getApplicationContext(), "Para continuar debes escribir el titulo y la descripción que llevará está sección", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent(WebsModaSeccion2.this, WebsModaSeccion3.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        portada_seccion2.setImageDrawable(null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(WebsModaSeccion2.this, WebsModaSeccion1.class);
        startActivity(i);
        finish();
    }
}
