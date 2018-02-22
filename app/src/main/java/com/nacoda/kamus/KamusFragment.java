package com.nacoda.kamus;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nacoda.kamus.db.KamusHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class KamusFragment extends Fragment {


    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;

    @BindView(R.id.title)
    TextView title;

    String type;

    @SuppressLint("ValidFragment")
    public KamusFragment(String type) {
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_kamus, container, false);
        unbinder = ButterKnife.bind(this, v);

        if (type.equals(getString(R.string.indonesia))) {
            title.setText(getString(R.string.indonesia_english));
        } else if (type.equals(getString(R.string.english))) {
            title.setText(getString(R.string.english_indonesia));
        }

        final KamusHelper helper = new KamusHelper(getActivity());

        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.equals("")) {
                    recycler.setVisibility(View.VISIBLE);
                    helper.open();
                    try {
                        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                        KamusAdapter adapter = new KamusAdapter(getActivity());
                        adapter.setData(helper.getDataByName(newText, type));
                        recycler.setAdapter(adapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    helper.close();
                } else {
                    recycler.setVisibility(View.GONE);
                }

                return true;
            }
        });

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
