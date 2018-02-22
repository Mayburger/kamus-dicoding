package com.nacoda.kamus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nacoda.kamus.model.Kamus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.description)
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setTitle(getString(R.string.description));

        Kamus data = (Kamus) getIntent().getSerializableExtra(getString(R.string.description));

        title.setText(data.getWord());
        description.setText(data.getDescription());

    }
}
