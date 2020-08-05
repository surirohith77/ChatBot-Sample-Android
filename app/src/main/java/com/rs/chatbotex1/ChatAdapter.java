package com.rs.chatbotex1;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Activity activity;
    private int SELF = 100;
    private ArrayList<Message> messageArrayList;
    Context context;


    public ChatAdapter(ArrayList<Message> messageArrayList) {
        this.messageArrayList = messageArrayList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        context = parent.getContext();
        // view type is to identify where to render the chat message
        // left or right
        if (viewType == SELF) {
            // self message
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_item_self, parent, false);
        } else {
            // WatBot message
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_item_watson, parent, false);
        }


        return new ViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageArrayList.get(position);
        if (message.getId() != null && message.getId().equals("1")) {
            return SELF;
        }

        return position;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Message message = messageArrayList.get(position);
        switch (message.type) {
            case TEXT:
                ((ViewHolder) holder).message.setText(message.getMessage());

                if (message.getMessage().equals("Select Type of Insurance")){

                    ((ViewHolder) holder).linearInsurance.setVisibility(View.VISIBLE);

                    ((ViewHolder) holder).cardLic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        //    Toast.makeText(context, "Card LIC", Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context,InsuranceFormActivity.class));
                        }
                    });

                    ((ViewHolder) holder).cardBusinessLoan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                          //  Toast.makeText(context, "Card Business", Toast.LENGTH_SHORT).show();

                            context.startActivity(new Intent(context,InsuranceFormActivity.class));
                        }
                    });

                    ((ViewHolder) holder).cardMotor.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            //Toast.makeText(context, "Card Motor", Toast.LENGTH_SHORT).show();

                            context.startActivity(new Intent(context,InsuranceFormActivity.class));
                        }
                    });

                    ((ViewHolder) holder).cardInvestment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                           // Toast.makeText(context, "Card Investment", Toast.LENGTH_SHORT).show();

                            context.startActivity(new Intent(context,InsuranceFormActivity.class));
                        }
                    });


                }else {

                    ((ViewHolder) holder).linearInsurance.setVisibility(View.GONE);
                }

               /* ((ViewHolder) holder).message.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                    }
                });
*/
             /*   if (message.getMessage().equals("we required your documents Aadhar, Pan and Contact  No")){

                    Toast.makeText(activity, "New Insurance", Toast.LENGTH_SHORT).show();
                }
               else if (message.getMessage().equals("Enter your insurance no")){

                    Toast.makeText(activity, "Existing Insurance", Toast.LENGTH_SHORT).show();
                }*/

                break;
            case IMAGE:
                ((ViewHolder) holder).message.setVisibility(View.GONE);
                ImageView iv = ((ViewHolder) holder).image;
                Glide
                        .with(iv.getContext())
                        .load(message.getUrl())
                        .into(iv);
        }
    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        ImageView image;
        LinearLayout linearInsurance;
        CardView cardLic,cardBusinessLoan,cardMotor, cardInvestment;
        public ViewHolder(View view) {
            super(view);
            message = (TextView) itemView.findViewById(R.id.message);
            image = (ImageView) itemView.findViewById(R.id.image);
            linearInsurance = (LinearLayout) itemView.findViewById(R.id.linearInsurance);

            cardLic = (CardView) itemView.findViewById(R.id.cardLic);
            cardBusinessLoan = (CardView) itemView.findViewById(R.id.cardBusinessLoan);
            cardMotor = (CardView) itemView.findViewById(R.id.cardMotor);
            cardInvestment = (CardView) itemView.findViewById(R.id.cardInvestment);

            //TODO: Uncomment this if you want to use a custom Font
            /*String customFont = "Montserrat-Regular.ttf";
            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), customFont);
            message.setTypeface(typeface);*/

        }
    }


}