package brunetti.depaula.applista.activity;

import androidx.activity.result.ActivityResult;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import brunetti.depaula.applista.R;
import brunetti.depaula.applista.activity.adapter.MyAdapter;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;
    List<MyItem> itens = new ArrayList<>();
    MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//apresenta a activity na tela

        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);//captura o botao flutuante

        //adicionando o evento de clique ao botao flutuante
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NewActivity.class);//cria uma intent que abre a NewActivity
                startActivityForResult(i, NEW_ITEM_REQUEST);//inicia a intent esperando um resultado
            }
        });

        RecyclerView rvItens = findViewById(R.id.rvItens);//captura o RecyclerView

        myAdapter = new MyAdapter(this, itens);//inicializa o myAdapter
        rvItens.setAdapter(myAdapter);//define o myAdapter como o adapter de rvItens

        rvItens.setHasFixedSize(true);//define que o RecycleView tera tamanho fixo

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        //verifica se o codigo de requisicao e igual ao codigo definido
        if(requestCode == NEW_ITEM_REQUEST) {
            //verifica se o resultado da activty esta correto
            if(resultCode == Activity.RESULT_OK){
                MyItem myItem = new MyItem();//inicializa um objeto MyItem
                myItem.title = data.getStringExtra("title");//define o title de myItem como o title extraido da intent
                myItem.description = data.getStringExtra("description");//define a description de myItem como a description extraida da intent
                myItem.photo = data.getData();//define a photo de myItem como a foto extraida da intent
                itens.add(myItem);//adiciona o myItem a lista de itens
                myAdapter.notifyItemInserted(itens.size()-1);
            }
        }
    }

}