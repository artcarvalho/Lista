package artur.renata.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import artur.renata.lista.R;
import artur.renata.lista.activity.MainActivity;
import artur.renata.lista.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem> itens){
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity); //Esse inflador será usadopara ler o arquivo xm de layout do item e então criar os elementos de interface propriamente ditos
        View v = inflater.inflate(R.layout.item_list, parent, false); // usamos o inflador para criar os elementos de interface referentes a um item e os guardamos dentro de um objeto do tipo View
        return new MyViewHolder(v); // Esse objeto do tipo View (v) é guardado dentro de um objeto do tipo MyViewHolder, que por sua vez é retornado pela função
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) { // onBindViewholder tem como objeto preencher a UI com os dados de um item, Ele recebe dois parâretros : holder e position
        MyItem myItem = itens.get(position); // obtemos o item qe será usado para preencher a UI
        View v = holder.itemView; //Obtemos o objetodo tipo View que está guardado dentro do ViewHoloder

        ImageView imvPhoto = v.findViewById(R.id.imvfoto);
        imvPhoto.setImageBitmap(myItem.photo);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        TextView tvDesc = v.findViewById(R.id.tvdesc);
        tvDesc.setText(myItem.description);
        //nas linhas 40 a 48 preenchemos a UI com os dados do item, observe que chamamos o método findViewByld dentro do objeto View (v)
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
