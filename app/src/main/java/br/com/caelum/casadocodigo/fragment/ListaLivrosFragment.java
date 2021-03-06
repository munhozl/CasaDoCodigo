package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapter;
import br.com.caelum.casadocodigo.listener.EndlessListListener;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.server.WebClient;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by android7392 on 07/04/18.
 */

public class ListaLivrosFragment extends Fragment {

    private List<Livro> livros = new ArrayList<>();

    @BindView(R.id.lista_livros)
    RecyclerView recyclerView ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setAdapter(new LivroAdapter(livros));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;


    }

    public void populaListaCom(final List<Livro> livros) {
// this.livros.clear();  <-- apague esta linha
        this.livros.addAll(livros);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.addOnScrollListener(new EndlessListListener() {
            @Override
            public void carregaMaisItens() {
                //new WebClient().getLivros(livros.size(), 10); // ROLANDO INFINITAMENTE OS ULTIMOS 10
                new WebClient().getLivros(ListaLivrosFragment.this.livros.size(), 10); // PORQUE 10?
            }
        });
    }
}
