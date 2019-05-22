package org.iel.codesimatic.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.iel.codesimatic.Adapter.ViewHolder.LineHolder;
import org.iel.codesimatic.R;
import org.iel.codesimatic.model.Usuario;

import java.util.List;
import java.util.Locale;

/**
 * autor anderson
 *
 * essa classe será o nosso adptador que criará cada linha com os valores
 */
public class LineAdapter extends RecyclerView.Adapter<LineHolder> {

    //atributo irá receber a lista de usuários pelo construtor
    private final List<Usuario> usuarios;

    //método que recebe a lista de usuários
    public LineAdapter(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public LineHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int posicao) {
        return new LineHolder (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.linha_lista_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LineHolder lineHolder, int posicao) {
        lineHolder.tituloTextView.setText(String.format(Locale.getDefault(), "%s, %d - %s",
            usuarios.get(posicao).getNomeCompleto(),
            usuarios.get(posicao).getNomeCompleto(),
            usuarios.get(posicao).getEmail(),
            usuarios.get(posicao).getRamal(),
            usuarios.get(posicao).getSetor(),
            usuarios.get(posicao).getStatus()
        ));
//        acho que aqui colocamos os métodos para remover e atualizar
//        lineHolder.editarButton.setOnClickListener(view ->  updateItem(posicao));
//        lineHolder.removerButton.setOnClickListener(view ->  removeItem(posicao));
    }

    @Override
    public int getItemCount() {
        return usuarios !=null ?  usuarios.size() : 0;
    }
}
