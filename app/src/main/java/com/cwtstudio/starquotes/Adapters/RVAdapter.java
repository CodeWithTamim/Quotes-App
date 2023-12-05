package com.cwtstudio.starquotes.Adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cwtstudio.starquotes.Models.QuoteModel;
import com.cwtstudio.starquotes.R;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    Context context;
    List<QuoteModel> quoteList;

    public RVAdapter(Context context, List<QuoteModel> quoteList) {
        this.context = context;
        this.quoteList = quoteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.quote_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtQuote.setText(quoteList.get(position).getContent());
        holder.txtAuthor.setText(quoteList.get(position).getAuthor());
        holder.btnCopy.setOnClickListener(new View.OnClickListener() {
            String quote = quoteList.get(position).getContent();
            String author = quoteList.get(position).getAuthor();

            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Quote",quote+"\n"+author);
                clipboardManager.setPrimaryClip(clipData);


                Toast.makeText(context, "Copied To Clipboard", Toast.LENGTH_SHORT).show();



            }
        });



    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtQuote,txtAuthor;
        ImageView btnCopy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtQuote = itemView.findViewById(R.id.txtQuote);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            btnCopy = itemView.findViewById(R.id.btnCopy);



        }
    }




}
