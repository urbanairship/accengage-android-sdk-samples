package com.accengage.samples.custominapps.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.accengage.samples.custominapps.R;
import com.ad4screen.sdk.InApp;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Template used: popup_offer
 *
 * Associated custom parameters:
 * - inapp_title: Title text
 * - inapp_text1: Content text
 * - inapp_cta: Button 1 text
 * - inapp_cta2: Button 2 text
 * - inapp_img: Url of a distant image
 */
public class PopupOfferView extends CustomInAppLayout {

    private TextView mTvTitle;
    private TextView mTvDesc;
    private TextView mTvCTA;
    private TextView mTvCTA2;
    private ImageView mImageView;
    private ImageView mCloseButton;
    private View mPopupCoreView;

    public PopupOfferView(Context context) {
        super(context);
        initViews();
    }

    public PopupOfferView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public PopupOfferView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        inflate(getContext(), R.layout.popup_offer, this);

        mTvTitle = (TextView) findViewById(R.id.title);
        mTvDesc = (TextView) findViewById(R.id.text1);
        mTvCTA = (TextView) findViewById(R.id.cta);
        mTvCTA2 = (TextView) findViewById(R.id.cta2);
        mImageView = (ImageView) findViewById(R.id.background);
        mCloseButton = (ImageView) findViewById(R.id.close_popup);
        mPopupCoreView = findViewById(R.id.popup_core);
    }

    @Override
    public void populate(InApp inApp) {
        super.populate(inApp);

        HashMap<String, String> customParameters = inApp.getCustomParameters();

        mTvTitle.setText(customParameters.get("inapp_title"));
        mTvDesc.setText(customParameters.get("inapp_text1"));
        mTvCTA.setText(customParameters.get("inapp_cta"));
        mTvCTA2.setText(customParameters.get("inapp_cta2"));

        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInApp.dismiss();
            }
        });

        mTvCTA.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mInApp.setClickZone("Btn1");
                mInApp.handleClick();
                Toast.makeText(getContext(), "PopupOffer was clicked button 1", Toast.LENGTH_SHORT).show();
            }
        });

        mTvCTA2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mInApp.setClickZone("Btn2");
                mInApp.handleClick();
                Toast.makeText(getContext(), "BannerDown was clicked button 2", Toast.LENGTH_SHORT).show();
            }
        });

        Picasso.with(getContext())
                .load(customParameters.get("inapp_img"))
                .into(mImageView);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mInApp.dismiss();
            }
        });

        mPopupCoreView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Nothing
            }
        });
    }
}