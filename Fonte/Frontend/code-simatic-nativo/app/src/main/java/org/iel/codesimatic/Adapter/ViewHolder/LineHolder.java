package org.iel.codesimatic.Adapter.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.iel.codesimatic.R;

/**
 * autor anderson em 14/04/2019
 *
 * essa classe recebe os atributos e insere nos campos enquanto são criados
 */
public class LineHolder extends RecyclerView.ViewHolder {

    //campos
    public TextView tituloTextView;
    public ImageButton editarButton;
    public ImageButton removerButton;

    //construtor
    public LineHolder(@NonNull View itemView) {
        super(itemView);
        tituloTextView = (TextView) itemView.findViewById(R.id.lista_card_descricao);
        editarButton = (ImageButton) itemView.findViewById(R.id.lista_card_editar);
        removerButton = (ImageButton) itemView.findViewById(R.id.lista_card_remover);
    }
}
