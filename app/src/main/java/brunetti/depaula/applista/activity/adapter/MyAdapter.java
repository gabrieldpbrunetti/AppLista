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
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = itens.get(position);

        View v = holder.itemView;
        ImageView imvfoto = v.findViewById(R.id.imvfoto);
        imvfoto.setImageURI(myItem.photo);

        TextView titulo = v.findViewById(R.id.titulo);
        titulo.setText(myItem.title);

        TextView descricao = v.findViewById(R.id.descricao);
        descricao.setText(myItem.description);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
