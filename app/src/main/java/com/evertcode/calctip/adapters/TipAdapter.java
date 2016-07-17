package com.evertcode.calctip.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evertcode.calctip.R;
import com.evertcode.calctip.bean.TipRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SimpleFormatter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hebert on 04/06/2016.
 */
public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder>{

    private List<TipRecord> dataSet;
    private Context context;

    private OnItemClickListener onItemClickListener;

    public TipAdapter(final Context context, final List<TipRecord> dataSet, final OnItemClickListener onItemClickListener){
        this.dataSet = dataSet;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    public TipAdapter(final Context context, final OnItemClickListener onItemClickListener){
        this.dataSet = new ArrayList<TipRecord>();
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TipRecord element = dataSet.get(position);
        String strSubTotal = String.format(context.getString(R.string.global_message_sub, element.getSubTotal()));
        String strTip = String.format(context.getString(R.string.global_message_tip, element.getTip()));
        String strTotal = String.format(context.getString(R.string.global_message_total, (element.getBill())));
        String strTotalTipByPerson = String.format(context.getString(R.string.global_message_total_by_person, element.getTipByPerson()));

        holder.txtSubTotal.setText(strSubTotal);
        holder.txtTotal.setText(strTotal);
        holder.txtContentDate.setText(element.getDateFormatted());
        holder.txtContent.setText(strTip);
        holder.txtTotalByPerson.setText(strTotalTipByPerson);

        holder.setOnItemClickListener(element, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void add(final TipRecord tip){
        dataSet.add(0, tip);
        notifyDataSetChanged();
    }

    public  void clear(){
        dataSet.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txtContent)
        TextView txtContent;

        @BindView(R.id.txtSubTotal)
        TextView txtSubTotal;

        @BindView(R.id.txtTotal)
        TextView txtTotal;

        @BindView(R.id.txtContentDate)
        TextView txtContentDate;

        @BindView(R.id.txtTotalByPerson)
        TextView txtTotalByPerson;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setOnItemClickListener(final TipRecord element, final OnItemClickListener onItemClickListener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(element);
                }
            });
        }
    }
}
