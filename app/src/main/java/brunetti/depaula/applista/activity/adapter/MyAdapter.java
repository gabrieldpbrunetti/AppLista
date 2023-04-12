package brunetti.depaula.applista.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import brunetti.depaula.applista.R;
import brunetti.depaula.applista.activity.MainActivity;
import brunetti.depaula.applista.activity.MyItem;

public class MyAdapter extends RecyclerView.Adapter{

    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem> itens) {
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mainActivity);//Inicializa o LayoutInflater que recebe o mainActivity e infla os elementos em tempo de execução
        View v = inflater.inflate(R.layout.item_list,parent,false);//Inicializa os elementos view a partir do LayoutInflater que gerou os elementos View a partir do arquivo xml MainActivity
        return new MyViewHolder(v);//
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = itens.get(position);//inicializa o item a partir de um item da lista itens

        View v = holder.itemView;//inicializa a view por meio do holder
        ImageView imvfoto = v.findViewById(R.id.imvfoto);//captura a ImageView
        imvfoto.setImageURI(myItem.photo);//define a foto como a foto obtida pelo holder

        TextView titulo = v.findViewById(R.id.titulo);//captura o textView referente ao titulo
        titulo.setText(myItem.title);//define o texto de titulo como o titulo de myItem

        TextView descricao = v.findViewById(R.id.descricao);//captura o textView referente a descricao
        descricao.setText(myItem.description);//define o texto como adescription de myItem
    }

    @Override
    public int getItemCount() {
        return itens.size();//retorna o tamanho da lista itens
    }
}
