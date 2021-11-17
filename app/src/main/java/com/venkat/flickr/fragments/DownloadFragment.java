package com.venkat.flickr.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.venkat.flickr.R;


public class DownloadFragment extends Fragment {

    private OnDownloadFragmentInteractionListener mListener;

    Button cancelButton,downloadButton;
    ImageView imageView;
    View rootView;
    String imageUrl,imageTitle;
    TextView titleView;


    public DownloadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_download, container, false);

        if (getArguments() != null) {
            imageUrl = getArguments().getString("url");
            imageTitle = getArguments().getString("title");
        }
        System.out.println("from fragment: "+imageUrl);

        imageView = rootView.findViewById(R.id.dialogImage);
        Glide.with(getContext()).load(imageUrl).into(imageView);

        titleView = rootView.findViewById(R.id.dialogTitle);
        titleView.setText(imageTitle);

        cancelButton = rootView.findViewById(R.id.dialogCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.killDownloadFragment();
                }
            }
        });

        downloadButton = rootView.findViewById(R.id.dialogDownload);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save to gallery

                //saveToGallery();

                if (mListener != null) {
                    mListener.saveImageToGallery();
                }


            }
        });

        if (mListener != null) {
            mListener.stopProgressBar();
        }

        return rootView;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDownloadFragmentInteractionListener) {
            mListener = (OnDownloadFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDownloadFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnDownloadFragmentInteractionListener {

        void killDownloadFragment();
        void saveImageToGallery();
        void stopProgressBar();
    }

}
