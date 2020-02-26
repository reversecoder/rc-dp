package com.rc.attributionpresenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.rc.attributionpresenter.R;
import com.rc.attributionpresenter.model.Attribution;
import com.rc.attributionpresenter.model.LicenseInfo;
import com.rc.attributionpresenter.view.AnimatedImageView;
import com.rc.attributionpresenter.view.AnimatedTextView;
import com.rc.attributionpresenter.view.ArcView;

import static com.rc.attributionpresenter.util.Constants.INTENT_KEY_ATTRIBUTION;

public class LicenseDetailActivity extends AppCompatActivity {

    Attribution mAttribution;

    //toolbar
    ArcView arcMenuView;
    AnimatedImageView arcMenuImage;
    AnimatedTextView toolbarTitle;
    Toolbar toolbar;

    //License info
//    TextView tvLicenseName, tvLicenseCopyright, tvLicenseDetail;
    LinearLayout llLicenseDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license_detail);

        initView();
    }

    private void initView() {

        Intent intentData = getIntent();
        mAttribution = intentData.getParcelableExtra(INTENT_KEY_ATTRIBUTION);
        Log.d("mAttribution", mAttribution.toString());

        initToolBar();

        llLicenseDetail = (LinearLayout) findViewById(R.id.ll_license_detail);

        if (mAttribution != null) {
            showLicenseDetailView();
        }
    }

    private void initToolBar() {
        toolbarTitle = (AnimatedTextView) findViewById(R.id.toolbarTitle);
        arcMenuImage = (AnimatedImageView) findViewById(R.id.arcImage);
        arcMenuView = (ArcView) findViewById(R.id.arcView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (mAttribution != null) {
            toolbarTitle.setAnimatedText(mAttribution.getAttributionName(), 0L);
        }
        arcMenuImage.setAnimatedImage(R.drawable.arrow_left, 0L);
        arcMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void showLicenseDetailView() {

        llLicenseDetail.removeAllViews();

        for (LicenseInfo licenseInfo : mAttribution.getLicensesInfo()) {

            View inflatedView = LayoutInflater.from(LicenseDetailActivity.this).inflate(R.layout.layout_license_detail, llLicenseDetail, false);

            TextView tvLicenseName = (TextView) inflatedView.findViewById(R.id.tv_license_name);
            TextView tvLicenseCopyright = (TextView) inflatedView.findViewById(R.id.tv_license_copyright);
            TextView tvLicenseDetail = (TextView) inflatedView.findViewById(R.id.tv_license_detail);

            tvLicenseName.setText(licenseInfo.getLicense().getLicenseName());
            tvLicenseCopyright.setText(licenseInfo.getCopyrightNotice());
            tvLicenseDetail.setText(licenseInfo.getLicense().getLicenseText());

            llLicenseDetail.addView(inflatedView);
        }
    }
}
