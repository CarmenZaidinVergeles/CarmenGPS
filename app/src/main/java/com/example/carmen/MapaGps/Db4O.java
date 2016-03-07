package com.example.carmen.MapaGps;


import android.content.Context;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import java.util.ArrayList;


public class Db4O {

    private ObjectContainer bd;

    public Db4O(Context ctx) {
        bd = Db4oEmbedded.openFile(
                Db4oEmbedded.newConfiguration(), ctx.getExternalFilesDir(null) +
                        "/bd.db4o");
    }

    public ArrayList<Posicion> consultar(){
        Query consulta = bd.query();
        consulta.constrain(Posicion.class);
        ObjectSet<Posicion> pos = consulta.execute();
        ArrayList<Posicion> lista = new ArrayList<Posicion>();
        for(Posicion posicion: pos){
            lista.add(posicion);
        }
        return lista;
    }

    public void insertar(Posicion pos){
        bd.store(pos);
        bd.commit();
    }

    public void close(){
        bd.close();
    }


}
