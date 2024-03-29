package com.colabora.soluciones.convocatoriafeyac.web.salud;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.colabora.soluciones.convocatoriafeyac.Modelos.caracteristicas_web;
import com.colabora.soluciones.convocatoriafeyac.Modelos.itemSimple;
import com.colabora.soluciones.convocatoriafeyac.R;
import com.colabora.soluciones.convocatoriafeyac.web.servicios.WebsServiciosSeccion2;
import com.colabora.soluciones.convocatoriafeyac.web.servicios.WebsServiciosSeccion3;
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
import java.util.ArrayList;
import java.util.List;

public class WebsSaludSeccion2 extends AppCompatActivity {

    // *************************** RECYCLER VIEW ************************

    private class DataConfigHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtTitulo;
        private TextView txtDescripcion;
        private Button btnEditar;
        private Button btnEliminar;

        private List<itemSimple> itemSimples = new ArrayList<itemSimple>();
        private Context ctx;

        public DataConfigHolder(View itemView, Context ctx, final List<itemSimple> itemSimples) {
            super(itemView);
            itemView.setOnClickListener(this);

            this.itemSimples = itemSimples;
            this.ctx = ctx;

            txtTitulo = (TextView) itemView.findViewById(R.id.itemSimpleTitulo);
            txtDescripcion = (TextView) itemView.findViewById(R.id.itemSimpleDescripcion);
            btnEditar = (Button)itemView.findViewById(R.id.dialogItemSimpleEditar);
            btnEliminar = (Button)itemView.findViewById(R.id.dialogItemSimpleEliminar);

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    itemSimpleList.remove(getAdapterPosition());
                    // *********** LLENAMOS EL RECYCLER VIEW *****************************
                    adapter = new WebsSaludSeccion2.DataConfigAdapter(itemSimpleList, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }
            });

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = getAdapterPosition();

                    final AlertDialog.Builder builder = new AlertDialog.Builder(WebsSaludSeccion2.this);

                    // Get the layout inflater
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View formElementsView = inflater.inflate(R.layout.dialog_item_simple,
                            null, false);

                    itemTitulo = (TextInputEditText) formElementsView.findViewById(R.id.editItemSimpleTitulo);
                    itemDescripcion = (TextInputEditText) formElementsView.findViewById(R.id.editItemSimpleDescripcion);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    itemTitulo.setText(itemSimpleList.get(position).getTitulo());
                    itemDescripcion.setText(itemSimpleList.get(position).getDescripcion());

                    itemTitulo.setSelection(itemTitulo.getText().length());
                    itemDescripcion.setSelection(itemDescripcion.getText().length());

                    builder.setTitle("Nueva Característica");
                    builder.setMessage("Por favor, introduce un título y descripción de las características distintivas de tus servicios");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            String titulo = itemTitulo.getText().toString();
                            String descripcion = itemDescripcion.getText().toString();

                            if(titulo.length() > 0 && descripcion.length() > 0){
                                itemSimpleList.remove(position);
                                itemSimple itemSimple_ = new itemSimple(titulo, descripcion);
                                itemSimpleList.add(itemSimple_);

                                // *********** LLENAMOS EL RECYCLER VIEW *****************************
                                adapter = new WebsSaludSeccion2.DataConfigAdapter(itemSimpleList, getApplicationContext());
                                recyclerView.setAdapter(adapter);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Debes introducir datos válidos", Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    // Inflate and set the layout for the dialog
                    // Pass null as the parent view because its going in the dialog layout
                    builder.setView(formElementsView);
                    // Add action buttons
                    builder.create();
                    builder.show();
                }
            });

        }

        public void bindConfig(final itemSimple itemSimple) {
            txtTitulo.setText(itemSimple.getTitulo());
            txtDescripcion.setText(itemSimple.getDescripcion());
        }

        @Override
        public void onClick(View v) {
            final int position = getAdapterPosition();

        }

    }


    private class DataConfigAdapter extends RecyclerView.Adapter<WebsSaludSeccion2.DataConfigHolder> {

        private List<itemSimple> itemSimples;
        Context ctx;

        public DataConfigAdapter(List<itemSimple> itemSimples, Context ctx ){
            this.itemSimples = itemSimples;
            this.ctx = ctx;
        }

        @Override
        public WebsSaludSeccion2.DataConfigHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_simple, parent, false);
            return new WebsSaludSeccion2.DataConfigHolder(view, ctx, itemSimples);
        }

        @Override
        public void onBindViewHolder(final WebsSaludSeccion2.DataConfigHolder holder, final int position) {
            holder.bindConfig(itemSimples.get(position));

        }

        @Override
        public int getItemCount() {
            return itemSimples.size();
        }

    }

    private WebsSaludSeccion2.DataConfigAdapter adapter;

    // ******************************************************************


    private TextInputEditText txtTitulo;
    private TextInputEditText txtDescripcion;

    private TextInputEditText itemTitulo;
    private TextInputEditText itemDescripcion;

    private RecyclerView recyclerView;
    private Button addCaracteristica;
    private String nombre_web;
    private Button btnSiguiente;
    private boolean imgUpoloaded = false;
    private List<itemSimple> itemSimpleList = new ArrayList<>();
    private String charactersForbiden = ",";

    FirebaseStorage storage = FirebaseStorage.getInstance();
    private SharedPreferences sharedPreferences;

    private String imagen = "";
    private String descripcion = "";
    private String titulo = "";
    private List<caracteristicas_web> servicio = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webs_salud_seccion2);

        txtTitulo = (TextInputEditText)findViewById(R.id.txt_web_salud_seccion2_texto1);
        txtDescripcion = (TextInputEditText)findViewById(R.id.txt_web_salud_seccion2_descripcion1);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerSaludSeccion2);
        addCaracteristica = (Button) findViewById(R.id.btnSaludSeccion2AddCaracteristica);
        btnSiguiente = (Button) findViewById(R.id.btnSaludSeccion2Siguiente);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        sharedPreferences = getSharedPreferences("misDatos", 0);
        nombre_web = sharedPreferences.getString("nombrePagWeb","");

        txtTitulo.setText(sharedPreferences.getString("web_salud_seccion_2_titulo", ""));
        txtDescripcion.setText(sharedPreferences.getString("web_salud_seccion_2_descripcion", ""));

        txtTitulo.setSelection(txtTitulo.getText().length());
        txtDescripcion.setSelection(txtDescripcion.getText().length());

        if(sharedPreferences.getString("web_salud_seccion_2_recycler","").equals("1")){
            imagen = "";
            titulo = sharedPreferences.getString("web_salud_seccion_2_caracteristica1_titulo","");
            descripcion = sharedPreferences.getString("web_salud_seccion_2_caracteristica1_descripcion","");

            servicio.add(new caracteristicas_web(imagen, titulo, descripcion));

            for (int i = 0; i < servicio.size(); i++){
                itemSimpleList.add(new itemSimple(servicio.get(i).getTitulo(), servicio.get(i).getDescripcion()));
            }

            // *********** LLENAMOS EL RECYCLER VIEW *****************************
            adapter = new WebsSaludSeccion2.DataConfigAdapter(itemSimpleList, getApplicationContext());
            recyclerView.setAdapter(adapter);
        }
        else if(sharedPreferences.getString("web_salud_seccion_2_recycler","").equals("2")){
            imagen = "";
            titulo = sharedPreferences.getString("web_salud_seccion_2_caracteristica1_titulo","");
            descripcion = sharedPreferences.getString("web_salud_seccion_2_caracteristica1_descripcion","");

            servicio.add(new caracteristicas_web(imagen, titulo, descripcion));

            imagen = "";
            titulo = sharedPreferences.getString("web_salud_seccion_2_caracteristica2_titulo","");
            descripcion = sharedPreferences.getString("web_salud_seccion_2_caracteristica2_descripcion","");

            servicio.add(new caracteristicas_web(imagen, titulo, descripcion));

            for (int i = 0; i < servicio.size(); i++){
                itemSimpleList.add(new itemSimple(servicio.get(i).getTitulo(), servicio.get(i).getDescripcion()));
            }

            // *********** LLENAMOS EL RECYCLER VIEW *****************************
            adapter = new WebsSaludSeccion2.DataConfigAdapter(itemSimpleList, getApplicationContext());
            recyclerView.setAdapter(adapter);
        }
        else if(sharedPreferences.getString("web_salud_seccion_2_recycler","").equals("3")){
            imagen = "";
            titulo = sharedPreferences.getString("web_salud_seccion_2_caracteristica1_titulo","");
            descripcion = sharedPreferences.getString("web_salud_seccion_2_caracteristica1_descripcion","");

            servicio.add(new caracteristicas_web(imagen, titulo, descripcion));

            imagen = "";
            titulo = sharedPreferences.getString("web_salud_seccion_2_caracteristica2_titulo","");
            descripcion = sharedPreferences.getString("web_salud_seccion_2_caracteristica2_descripcion","");

            servicio.add(new caracteristicas_web(imagen, titulo, descripcion));

            imagen = "";
            titulo = sharedPreferences.getString("web_salud_seccion_2_caracteristica3_titulo","");
            descripcion = sharedPreferences.getString("web_salud_seccion_2_caracteristica3_descripcion","");

            servicio.add(new caracteristicas_web(imagen, titulo, descripcion));

            for (int i = 0; i < servicio.size(); i++){
                itemSimpleList.add(new itemSimple(servicio.get(i).getTitulo(), servicio.get(i).getDescripcion()));
            }

            // *********** LLENAMOS EL RECYCLER VIEW *****************************
            adapter = new WebsSaludSeccion2.DataConfigAdapter(itemSimpleList, getApplicationContext());
            recyclerView.setAdapter(adapter);
        }
        else if(sharedPreferences.getString("web_salud_seccion_2_recycler","").equals("4")){
            imagen = "";
            titulo = sharedPreferences.getString("web_salud_seccion_2_caracteristica1_titulo","");
            descripcion = sharedPreferences.getString("web_salud_seccion_2_caracteristica1_descripcion","");

            servicio.add(new caracteristicas_web(imagen, titulo, descripcion));

            imagen = "";
            titulo = sharedPreferences.getString("web_salud_seccion_2_caracteristica2_titulo","");
            descripcion = sharedPreferences.getString("web_salud_seccion_2_caracteristica2_descripcion","");

            servicio.add(new caracteristicas_web(imagen, titulo, descripcion));

            imagen = "";
            titulo = sharedPreferences.getString("web_salud_seccion_2_caracteristica3_titulo","");
            descripcion = sharedPreferences.getString("web_salud_seccion_2_caracteristica3_descripcion","");

            servicio.add(new caracteristicas_web(imagen, titulo, descripcion));

            imagen = "";
            titulo = sharedPreferences.getString("web_salud_seccion_2_caracteristica4_titulo","");
            descripcion = sharedPreferences.getString("web_salud_seccion_2_caracteristica4_descripcion","");

            servicio.add(new caracteristicas_web(imagen, titulo, descripcion));

            for (int i = 0; i < servicio.size(); i++){
                itemSimpleList.add(new itemSimple(servicio.get(i).getTitulo(), servicio.get(i).getDescripcion()));
            }

            // *********** LLENAMOS EL RECYCLER VIEW *****************************
            adapter = new WebsSaludSeccion2.DataConfigAdapter(itemSimpleList, getApplicationContext());
            recyclerView.setAdapter(adapter);
        }

        addCaracteristica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemSimpleList.size() < 4){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(WebsSaludSeccion2.this);

                    // Get the layout inflater
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View formElementsView = inflater.inflate(R.layout.dialog_item_simple,
                            null, false);

                    itemTitulo = (TextInputEditText) formElementsView.findViewById(R.id.editItemSimpleTitulo);
                    itemDescripcion = (TextInputEditText) formElementsView.findViewById(R.id.editItemSimpleDescripcion);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                    builder.setTitle("Nuevo Servicio");
                    builder.setMessage("Por favor, introduce un título y descripción de las características distintivas de tus servicios");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            String titulo = itemTitulo.getText().toString();
                            String descripcion = itemDescripcion.getText().toString();

                            if(titulo.length() > 0 && descripcion.length() > 0){
                                itemSimple itemSimple_ = new itemSimple(titulo, descripcion);
                                itemSimpleList.add(itemSimple_);

                                // *********** LLENAMOS EL RECYCLER VIEW *****************************
                                adapter = new WebsSaludSeccion2.DataConfigAdapter(itemSimpleList, getApplicationContext());
                                recyclerView.setAdapter(adapter);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Debes introducir datos válidos", Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    // Inflate and set the layout for the dialog
                    // Pass null as the parent view because its going in the dialog layout
                    builder.setView(formElementsView);
                    // Add action buttons
                    builder.create();
                    builder.show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Sólo se permiten máximo 4 características", Toast.LENGTH_LONG).show();
                }

            }
        });


        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = txtTitulo.getText().toString();
                String descripcion = txtDescripcion.getText().toString();

                if(titulo.length() == 0){
                    Toast.makeText(getApplicationContext(), "Debes introducir el título de esta sección", Toast.LENGTH_LONG).show();
                    return;
                }

                if(descripcion.length() == 0){
                    Toast.makeText(getApplicationContext(), "Debes introducir la descripción de esta sección", Toast.LENGTH_LONG).show();
                    return;
                }

                if(itemSimpleList.size() < 1){
                    Toast.makeText(getApplicationContext(), "Debes introducir por lo menos una característica de tus servicios", Toast.LENGTH_LONG).show();
                    return;
                }

                // *********** Guardamos los principales datos de los nuevos usuarios *************
                sharedPreferences = getSharedPreferences("misDatos", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("web_salud_seccion_2_titulo", titulo);
                editor.putString("web_salud_seccion_2_descripcion", descripcion);
                if(itemSimpleList.size() == 1){
                    editor.putString("web_salud_seccion_2_recycler", "1");
                    editor.putString("web_salud_seccion_2_caracteristica1_titulo", itemSimpleList.get(0).getTitulo());
                    editor.putString("web_salud_seccion_2_caracteristica1_descripcion", itemSimpleList.get(0).getDescripcion());
                }
                else if(itemSimpleList.size() == 2){
                    editor.putString("web_salud_seccion_2_recycler", "2");
                    editor.putString("web_salud_seccion_2_caracteristica1_titulo", itemSimpleList.get(0).getTitulo());
                    editor.putString("web_salud_seccion_2_caracteristica1_descripcion", itemSimpleList.get(0).getDescripcion());
                    editor.putString("web_salud_seccion_2_caracteristica2_titulo", itemSimpleList.get(1).getTitulo());
                    editor.putString("web_salud_seccion_2_caracteristica2_descripcion", itemSimpleList.get(1).getDescripcion());
                }
                else if(itemSimpleList.size() == 3){
                    editor.putString("web_salud_seccion_2_recycler", "3");
                    editor.putString("web_salud_seccion_2_caracteristica1_titulo", itemSimpleList.get(0).getTitulo());
                    editor.putString("web_salud_seccion_2_caracteristica1_descripcion", itemSimpleList.get(0).getDescripcion());
                    editor.putString("web_salud_seccion_2_caracteristica2_titulo", itemSimpleList.get(1).getTitulo());
                    editor.putString("web_salud_seccion_2_caracteristica2_descripcion", itemSimpleList.get(1).getDescripcion());
                    editor.putString("web_salud_seccion_2_caracteristica3_titulo", itemSimpleList.get(2).getTitulo());
                    editor.putString("web_salud_seccion_2_caracteristica3_descripcion", itemSimpleList.get(2).getDescripcion());
                }
                else if(itemSimpleList.size() == 4){
                    editor.putString("web_salud_seccion_2_recycler", "4");
                    editor.putString("web_salud_seccion_2_caracteristica1_titulo", itemSimpleList.get(0).getTitulo());
                    editor.putString("web_salud_seccion_2_caracteristica1_descripcion", itemSimpleList.get(0).getDescripcion());
                    editor.putString("web_salud_seccion_2_caracteristica2_titulo", itemSimpleList.get(1).getTitulo());
                    editor.putString("web_salud_seccion_2_caracteristica2_descripcion", itemSimpleList.get(1).getDescripcion());
                    editor.putString("web_salud_seccion_2_caracteristica3_titulo", itemSimpleList.get(2).getTitulo());
                    editor.putString("web_salud_seccion_2_caracteristica3_descripcion", itemSimpleList.get(2).getDescripcion());
                    editor.putString("web_salud_seccion_2_caracteristica4_titulo", itemSimpleList.get(3).getTitulo());
                    editor.putString("web_salud_seccion_2_caracteristica4_descripcion", itemSimpleList.get(3).getDescripcion());
                }
                editor.commit();
                // ******************************************************************************

                Intent i = new Intent(WebsSaludSeccion2.this, WebsSaludSeccion3.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(WebsSaludSeccion2.this, WebsSaludSeccion1.class);
        startActivity(i);
        finish();
    }
}
