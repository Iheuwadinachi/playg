package nl.saxion.playground.template.lumberjack_simulator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lumberjack_simulator.local_lib.Price;

/**
 * @author Mark is too lazy to do any comments here right now.
 * If you don`t understand anything -> contact me :)
 * <p>
 * I will do all the comments, but not now
 */

public class BuyView extends RelativeLayout {

    /*
    1)  Drop +1 coin
    2) Buy new Axe
    2.1) Upgrade coin value
    3) Buy Chainsaw (possible after buying all axes
    4) Buy Fuel A-95 (opens after 3)
    5) buy new place for worker
    6) buy mini Lumberjack
     */



    private TextView buyNewCoin;
    private TextView buyNewAxe;
    private TextView buyChainsaw;
    private TextView buyFuel;

    private Game game;

    private Price[] prices;

    private Bitmap bitmap;
    /*Bitmap shopBackground = BitmapFactory.decodeResource(getResources(),R.drawable.shop_view);*/

    //    private double[] priceIncreasement;
//    private byte[] indexOfPriceIncreasement;
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

    private void init() {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_buy, this);
        setBackgroundColor(Color.rgb(172, 235, 152));



        buyNewCoin = findViewById(R.id.priceNewCoin);
        buyNewAxe = findViewById(R.id.priceNewAxe);
        buyChainsaw = findViewById(R.id.priceChainsawAxe);
        buyFuel = findViewById(R.id.priceFuel);

        prices = new Price[NUMBER_OF_UPDATES];

        buyNewCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedBuyNewCoin();
            }
        });


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private void proceedBuyNewCoin() {
        int currentPrice;
        try {
            currentPrice = Integer.parseInt(buyNewCoin.getText().toString());
        } catch (NumberFormatException e) {
            makeToast("This was maximum upgraded", 0);
            return;
        }

        if (game.getCoinsEarned() >= currentPrice) {
            if (prices[0] == null) prices[0] = new Price(10, 15);
            game.setCoinsEarned(game.getCoinsEarned() - currentPrice);
            buyNewCoin.setText(prices[0].getNewPrice());
            game.addCoinToSpawn();
            Log.d("extra_info", "Bought new coin");
        } else {
            makeToast("Not enought coins", 0);
        }
    }

    private void makeToast(String text, int duration) {
        Toast.makeText(getContext(), text, duration).show();
    }

    public void transparent(String command) {
        String proceed = command.toLowerCase();

        if (proceed.equals("visible")) {
            setVisibility(VISIBLE);
            Log.d("extra_info", "VISIBLE BUY VIEW");
        } else if (proceed.equals("invisible")) {
            setVisibility(INVISIBLE);
            Log.d("extra_info", "INVISIBLE BUY VIEW");
        } else if (proceed.equals("gone")) {
            setVisibility(GONE);
            Log.d("extra_info", "GONE BUY VIEW");
        }

    }
    /*@Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(shopBackground, 0,0,null);

        *//*float bgWidth = (float) bitmap.getWidth() / (float) bitmap.getHeight() * game.getHeight();

        for (int i = 0; i <= Math.ceil(game.getWidth() / bgWidth); i++) {

            //gv.drawBitmap(bitmap, (float) i * bgWidth, 0, bgWidth, game.getHeight());
        }
*//*        super.onDraw(canvas);
    }*/
}
