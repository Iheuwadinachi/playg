package nl.saxion.playground.template.lumberjack_simulator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.playground.template.R;

public class BuyView extends RelativeLayout {
    
    private TextView buyNewCoin;
    private TextView buyNewAxe;
    private TextView buyChainsaw;
    private TextView buyFuel;

    private Game game;


    private byte NUMBER_OF_UPDATES = 4;

    public BuyView(Context context) {
        super(context);
        init();
    }

    public BuyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BuyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BuyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_buy,this);
        setBackgroundColor(Color.rgb(172,235,152));
        
        buyNewCoin = findViewById(R.id.priceNewCoin);
        buyNewAxe = findViewById(R.id.priceNewAxe);
        buyChainsaw = findViewById(R.id.priceChainsawAxe);
        buyFuel = findViewById(R.id.priceFuel);

        buyNewCoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                proceedBuyNewCoin();
            }
        });

    }

    public void setGame(Game game) {
        this.game = game;
    }

    private void proceedBuyNewCoin(){
        int price;
        try{
            price = Integer.parseInt(buyNewCoin.getText().toString());
        } catch (NumberFormatException e){
            Log.d("extra_info","EXCEPTION WITH BUY VIEW -> not parsed string");
            return;
        }

        if(game.getCoinsEarned() >= price){
            game.setCoinsEarned(game.getCoinsEarned() - price);
            buyNewCoin.setText("" + price*5);
            Log.d("extra_info","Bought new coin");
        } else{
            Toast.makeText(getContext(),"Not enought coins", Toast.LENGTH_SHORT).show();
        }
    }

    public void transparent(String command){
        String proceed = command.toLowerCase();

        if(proceed.equals("visible")){
            setVisibility(VISIBLE);
            Log.d("extra_info","VISIBLE BUY VIEW");
        } else if(proceed.equals("invisible")){
            setVisibility(INVISIBLE);
            Log.d("extra_info","INVISIBLE BUY VIEW");
        } else if(proceed.equals("gone")){
            setVisibility(GONE);
            Log.d("extra_info","GONE BUY VIEW");
        }

    }

}
