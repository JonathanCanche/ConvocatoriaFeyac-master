package com.colabora.soluciones.convocatoriafeyac.web.productos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.colabora.soluciones.convocatoriafeyac.Modelos.caracteristicas_web;
import com.colabora.soluciones.convocatoriafeyac.Modelos.itemSimple;
import com.colabora.soluciones.convocatoriafeyac.R;
import com.colabora.soluciones.convocatoriafeyac.web.salud.WebsSaludSeccion2;
import com.colabora.soluciones.convocatoriafeyac.web.salud.WebsSaludSeccion3;
import com.colabora.soluciones.convocatoriafeyac.web.servicios.WebsServiciosSeccion2;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WebsProductosSeccion3 extends AppCompatActivity {

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
                    adapter = new WebsProductosSeccion3.DataConfigAdapter(itemSimpleList, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }
            });

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = getAdapterPosition();

                    final AlertDialog.Builder builder = new AlertDialog.Builder(WebsProductosSeccion3.this);

                    // Get the layout inflater
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View formElementsView = inflater.inflate(R.layout.dialog_item_simple,
                            null, false);

                    itemTitulo = (TextInputEditText) formElementsView.findViewById(R.id.editItemSimpleTitulo);
                    itemDescripcion = (TextInputEditText) formElementsView.findViewById(R.id.editItemSimpleDescripcion);


                    itemTitulo.setText(itemSimpleList.get(position).getTitulo());
                    itemDescripcion.setText(itemSimpleList.get(position).getDescripcion());



                    itemTitulo.setSelection(itemTitulo.getText().length());
                    itemDescripcion.setSelection(itemDescripcion.getText().length());

                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    builder.setTitle("Servicios");
                    builder.setMessage("Por favor, introduce un título y descripción de tus servicios");
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
                                adapter = new WebsProductosSeccion3.DataConfigAdapter(itemSimpleList, getApplicationContext());
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


    private class DataConfigAdapter extends RecyclerView.Adapter<WebsProductosSeccion3.DataConfigHolder> {

        private List<itemSimple> itemSimples;
        Context ctx;

        public DataConfigAdapter(List<itemSimple> itemSimples, Context ctx ){
            this.itemSimples = itemSimples;
            this.ctx = ctx;
        }

        @Override
        public WebsProductosSeccion3.DataConfigHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_simple, parent, false);
            return new WebsProductosSeccion3.DataConfigHolder(view, ctx, itemSimples);
        }

        @Override
        public void onBindViewHolder(final WebsProductosSeccion3.DataConfigHolder holder, final int position) {
            holder.bindConfig(itemSimples.get(position));

        }

        @Override
        public int getItemCount() {
            return itemSimples.size();
        }

    }

    private WebsProductosSeccion3.DataConfigAdapter adapter;

    // ******************************************************************


    private TextInputEditText txtTitulo;

    private TextInputEditText itemTitulo;
    private TextInputEditText itemDescripcion;

    private RecyclerView recyclerView;
    private Button addCaracteristica;
    private String nombre_web;
    private Button btnSiguiente;
    private boolean imgUpoloaded = false;
    private List<itemSimple> itemSimpleList = new ArrayList<itemSimple>();
    private SharedPreferences sharedPreferences;

    private String imagen_ = "";
    private String titulo = "";
    private String descripcion = "";
    private ImageView imgPortada;

    private String charactersForbiden = ",";

    private List<caracteristicas_web> servicio = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webs_productos_seccion3);

        txtTitulo = (TextInputEditText)findViewById(R.id.txt_web_producto_seccion3_texto1);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerProductosSeccion3);
        addCaracteristica = (Button) findViewById(R.id.btnProductosSeccion3AddCaracteristica);
        btnSiguiente = (Button) findViewById(R.id.btnProductoSeccion3Siguiente);
        imgPortada = (ImageView)findViewById(R.id.img_web_producto_seccion3_portada);

        imgPortada.setImageResource(R.drawable.web_producto_seccion_3);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        sharedPreferences = getSharedPreferences("misDatos", 0);
        nombre_web = sharedPreferences.getString("nombrePagWeb","");


        txtTitulo.setText(sharedPreferences.getString("web_productos_seccion_3_titulo", ""));

        txtTitulo.setSelection(txtTitulo.getText().length());

        if(sharedPreferences.getString("web_productos_seccion_3_recycler","").equals("1")){
            imagen_ = "";
            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica1_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica1_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            for (int i = 0; i < servicio.size(); i++){
                itemSimpleList.add(new itemSimple(servicio.get(i).getTitulo(), servicio.get(i).getDescripcion()));
            }

            // *********** LLENAMOS EL RECYCLER VIEW *****************************
            adapter = new WebsProductosSeccion3.DataConfigAdapter(itemSimpleList, getApplicationContext());
            recyclerView.setAdapter(adapter);
        }
        else if(sharedPreferences.getString("web_productos_seccion_3_recycler","").equals("2")){
            imagen_ = "";
            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica1_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica1_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            imagen_ = "";
            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica2_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica2_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            for (int i = 0; i < servicio.size(); i++){
                itemSimpleList.add(new itemSimple(servicio.get(i).getTitulo(), servicio.get(i).getDescripcion()));
            }

            // *********** LLENAMOS EL RECYCLER VIEW *****************************
            adapter = new WebsProductosSeccion3.DataConfigAdapter(itemSimpleList, getApplicationContext());
            recyclerView.setAdapter(adapter);

        }

        else if(sharedPreferences.getString("web_productos_seccion_3_recycler","").equals("3")){
            imagen_ = "";
            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica1_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica1_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            imagen_ = "";
            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica2_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica2_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica3_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica3_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            for (int i = 0; i < servicio.size(); i++){
                itemSimpleList.add(new itemSimple(servicio.get(i).getTitulo(), servicio.get(i).getDescripcion()));
            }

            // *********** LLENAMOS EL RECYCLER VIEW *****************************
            adapter = new WebsProductosSeccion3.DataConfigAdapter(itemSimpleList, getApplicationContext());
            recyclerView.setAdapter(adapter);

        }

        else if(sharedPreferences.getString("web_productos_seccion_3_recycler","").equals("4")){
            imagen_ = "";
            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica1_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica1_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            imagen_ = "";
            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica2_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica2_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica3_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica3_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica4_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica4_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            for (int i = 0; i < servicio.size(); i++){
                itemSimpleList.add(new itemSimple(servicio.get(i).getTitulo(), servicio.get(i).getDescripcion()));
            }

            // *********** LLENAMOS EL RECYCLER VIEW *****************************
            adapter = new WebsProductosSeccion3.DataConfigAdapter(itemSimpleList, getApplicationContext());
            recyclerView.setAdapter(adapter);

        }

        else if(sharedPreferences.getString("web_productos_seccion_3_recycler","").equals("5")){
            imagen_ = "";
            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica1_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica1_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            imagen_ = "";
            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica2_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica2_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica3_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica3_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica4_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica4_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica5_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica5_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            for (int i = 0; i < servicio.size(); i++){
                itemSimpleList.add(new itemSimple(servicio.get(i).getTitulo(), servicio.get(i).getDescripcion()));
            }

            // *********** LLENAMOS EL RECYCLER VIEW *****************************
            adapter = new WebsProductosSeccion3.DataConfigAdapter(itemSimpleList, getApplicationContext());
            recyclerView.setAdapter(adapter);

        }

        else if(sharedPreferences.getString("web_productos_seccion_3_recycler","").equals("6")){
            imagen_ = "";
            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica1_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica1_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            imagen_ = "";
            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica2_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica2_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica3_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica3_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica4_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica4_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica5_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica5_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            titulo = sharedPreferences.getString("web_productos_seccion_3_caracteristica6_titulo","");
            descripcion = sharedPreferences.getString("web_productos_seccion_3_caracteristica6_descripcion","");

            servicio.add(new caracteristicas_web(imagen_, titulo, descripcion));

            for (int i = 0; i < servicio.size(); i++){
                itemSimpleList.add(new itemSimple(servicio.get(i).getTitulo(), servicio.get(i).getDescripcion()));
            }

            // *********** LLENAMOS EL RECYCLER VIEW *****************************
            adapter = new WebsProductosSeccion3.DataConfigAdapter(itemSimpleList, getApplicationContext());
            recyclerView.setAdapter(adapter);

        }

        addCaracteristica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemSimpleList.size() < 6){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(WebsProductosSeccion3.this);

                    // Get the layout inflater
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View formElementsView = inflater.inflate(R.layout.dialog_item_simple,
                            null, false);

                    itemTitulo = (TextInputEditText) formElementsView.findViewById(R.id.editItemSimpleTitulo);
                    itemDescripcion = (TextInputEditText) formElementsView.findViewById(R.id.editItemSimpleDescripcion);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                    builder.setTitle("Servicio");
                    builder.setMessage("Por favor, introduce un título y descripción de tus servicios");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            String titulo = itemTitulo.getText().toString();
                            String descripcion = itemDescripcion.getText().toString();

                            if(titulo.length() > 0 && descripcion.length() > 0){
                                itemSimple itemSimple_ = new itemSimple(titulo, descripcion);
                                itemSimpleList.add(itemSimple_);

                                // *********** LLENAMOS EL RECYCLER VIEW *****************************
                                adapter = new WebsProductosSeccion3.DataConfigAdapter(itemSimpleList, getApplicationContext());
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
                    Toast.makeText(getApplicationContext(), "Sólo se permiten máximo 6 características", Toast.LENGTH_LONG).show();
                }

            }
        });


        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = txtTitulo.getText().toString();

                if(titulo.length() == 0){
                    Toast.makeText(getApplicationContext(), "Debes introducir el título de esta sección", Toast.LENGTH_LONG).show();
                    return;
                }


                if(itemSimpleList.size() < 3){
                    Toast.makeText(getApplicationContext(), "Debes introducir por lo menos tres de tus servicios", Toast.LENGTH_LONG).show();
                    return;
                }

                // *********** Guardamos los principales datos de los nuevos usuarios *************
                sharedPreferences = getSharedPreferences("misDatos", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("web_productos_seccion_3_titulo", titulo);

                if(itemSimpleList.size() == 1){
                    editor.putString("web_productos_seccion_3_recycler", "1");
                    editor.putString("web_productos_seccion_3_caracteristica1_titulo", itemSimpleList.get(0).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica1_descripcion", itemSimpleList.get(0).getDescripcion());
                }
                else if(itemSimpleList.size() == 2){
                    editor.putString("web_productos_seccion_3_recycler", "2");
                    editor.putString("web_productos_seccion_3_caracteristica1_titulo", itemSimpleList.get(0).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica1_descripcion", itemSimpleList.get(0).getDescripcion());
                    editor.putString("web_productos_seccion_3_caracteristica2_titulo", itemSimpleList.get(1).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica2_descripcion", itemSimpleList.get(1).getDescripcion());
                }
                else if(itemSimpleList.size() == 3){
                    editor.putString("web_productos_seccion_3_recycler", "3");
                    editor.putString("web_productos_seccion_3_caracteristica1_titulo", itemSimpleList.get(0).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica1_descripcion", itemSimpleList.get(0).getDescripcion());
                    editor.putString("web_productos_seccion_3_caracteristica2_titulo", itemSimpleList.get(1).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica2_descripcion", itemSimpleList.get(1).getDescripcion());
                    editor.putString("web_productos_seccion_3_caracteristica3_titulo", itemSimpleList.get(2).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica3_descripcion", itemSimpleList.get(2).getDescripcion());
                }
                else if(itemSimpleList.size() == 4){
                    editor.putString("web_productos_seccion_3_recycler", "4");
                    editor.putString("web_productos_seccion_3_caracteristica1_titulo", itemSimpleList.get(0).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica1_descripcion", itemSimpleList.get(0).getDescripcion());
                    editor.putString("web_productos_seccion_3_caracteristica2_titulo", itemSimpleList.get(1).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica2_descripcion", itemSimpleList.get(1).getDescripcion());
                    editor.putString("web_productos_seccion_3_caracteristica3_titulo", itemSimpleList.get(2).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica3_descripcion", itemSimpleList.get(2).getDescripcion());
                    editor.putString("web_productos_seccion_3_caracteristica4_titulo", itemSimpleList.get(3).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica4_descripcion", itemSimpleList.get(3).getDescripcion());
                }
                else if(itemSimpleList.size() == 5){
                    editor.putString("web_productos_seccion_3_recycler", "5");
                    editor.putString("web_productos_seccion_3_caracteristica1_titulo", itemSimpleList.get(0).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica1_descripcion", itemSimpleList.get(0).getDescripcion());
                    editor.putString("web_productos_seccion_3_caracteristica2_titulo", itemSimpleList.get(1).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica2_descripcion", itemSimpleList.get(1).getDescripcion());
                    editor.putString("web_productos_seccion_3_caracteristica3_titulo", itemSimpleList.get(2).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica3_descripcion", itemSimpleList.get(2).getDescripcion());
                    editor.putString("web_productos_seccion_3_caracteristica4_titulo", itemSimpleList.get(3).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica4_descripcion", itemSimpleList.get(3).getDescripcion());
                    editor.putString("web_productos_seccion_3_caracteristica5_titulo", itemSimpleList.get(4).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica5_descripcion", itemSimpleList.get(4).getDescripcion());
                }
                else if(itemSimpleList.size() == 6){
                    editor.putString("web_productos_seccion_3_recycler", "6");
                    editor.putString("web_productos_seccion_3_caracteristica1_titulo", itemSimpleList.get(0).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica1_descripcion", itemSimpleList.get(0).getDescripcion());
                    editor.putString("web_productos_seccion_3_caracteristica2_titulo", itemSimpleList.get(1).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica2_descripcion", itemSimpleList.get(1).getDescripcion());
                    editor.putString("web_productos_seccion_3_caracteristica3_titulo", itemSimpleList.get(2).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica3_descripcion", itemSimpleList.get(2).getDescripcion());
                    editor.putString("web_productos_seccion_3_caracteristica4_titulo", itemSimpleList.get(3).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica4_descripcion", itemSimpleList.get(3).getDescripcion());
                    editor.putString("web_productos_seccion_3_caracteristica5_titulo", itemSimpleList.get(4).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica5_descripcion", itemSimpleList.get(4).getDescripcion());
                    editor.putString("web_productos_seccion_3_caracteristica6_titulo", itemSimpleList.get(5).getTitulo());
                    editor.putString("web_productos_seccion_3_caracteristica6_descripcion", itemSimpleList.get(5).getDescripcion());
                }
                editor.commit();
                // ******************************************************************************

                Intent i = new Intent(WebsProductosSeccion3.this, WebsProductosSeccion4.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(WebsProductosSeccion3.this, WebsProductosSeccion2.class);
        startActivity(i);
        finish();
    }
}
