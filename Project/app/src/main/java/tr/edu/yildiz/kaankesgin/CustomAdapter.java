package tr.edu.yildiz.kaankesgin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

   private Context context;
    private ArrayList arr_quest_id,arr_question,arr_o1,arr_o2,arr_o3,arr_o4,arr_o5, rightAnsw;

    CustomAdapter(Context context,ArrayList arr_quest_id,ArrayList question, ArrayList o1, ArrayList o2, ArrayList o3, ArrayList o4, ArrayList o5, ArrayList rightAnsw){
        this.context = context;
        this.arr_quest_id = arr_quest_id;
        this.arr_question = question;
        this.arr_o1 = o1;
        this.arr_o2 = o2;
        this.arr_o3 = o3;
        this.arr_o4= o4;
        this.arr_o5 = o5;
        this.rightAnsw = rightAnsw;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
       View view =  inflater.inflate(R.layout.row,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.quest_id.setText(String.valueOf(arr_quest_id.get(position)));
        holder.quest.setText(String.valueOf(arr_question.get(position)));
        holder.o1.setText(String.valueOf(arr_o1.get(position)));
        holder.o2.setText(String.valueOf(arr_o2.get(position)));
        holder.o3.setText(String.valueOf(arr_o3.get(position)));
        holder.o4.setText(String.valueOf(arr_o4.get(position)));
        holder.o5.setText(String.valueOf(arr_o5.get(position)));
        holder.rightAnswerText.setText(String.valueOf(rightAnsw.get(position)));

        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UpdateActivity.class);
                intent.putExtra("idofquestid",String.valueOf(arr_quest_id.get(position)));
                intent.putExtra("idofquestion",String.valueOf(arr_question.get(position)));
                intent.putExtra("idofo1",String.valueOf(arr_o1.get(position)));
                intent.putExtra("idofo2",String.valueOf(arr_o2.get(position)));
                intent.putExtra("idofo3",String.valueOf(arr_o3.get(position)));
                intent.putExtra("idofo4",String.valueOf(arr_o4.get(position)));
                intent.putExtra("idofo5",String.valueOf(arr_o5.get(position)));
                intent.putExtra("idofrightAnswer",String.valueOf(rightAnsw.get(position)));
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return arr_question.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView quest_id,quest,o1,o2,o3,o4,o5,rightAnswerText;
        LinearLayout rowLayout ;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            quest_id = itemView.findViewById(R.id.questID);
            quest = itemView.findViewById(R.id.rowQuest);
            o1  = itemView.findViewById(R.id.rowO1);
            o2  = itemView.findViewById(R.id.rowO2);
            o3  = itemView.findViewById(R.id.rowO3);
            o4  = itemView.findViewById(R.id.rowO4);
            o5  = itemView.findViewById(R.id.rowO5);
            rightAnswerText = itemView.findViewById(R.id.rightAnswerTextView);
            rowLayout = itemView.findViewById(R.id.rowLinearLayout);


        }
    }
}
