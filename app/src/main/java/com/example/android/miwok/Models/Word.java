package com.example.android.miwok.Models;

import android.content.Context;

import static android.R.string.no;

/**
 * Created by kristenwoodward on 11/27/16.
 */

public class Word {

    // miwok word
    private String mMiwokTranslation;

    // translation of miwok word
    private String mDefaultTranslation;

    // context of the app
    private Context mContext;

    // image for the word
    private int mImageId = NOT_PROVIDED;

    // media file for word pronunciation
    private int mAudioId = NOT_PROVIDED;

    // constant that is used if no resource is provided
    private static int NOT_PROVIDED = -1;

    /*
    create a word with miwok and default translations, an imageId, and a pronunciation media file
     */
    public Word(int audioId, String defaultTranslation, String miwokTranslation, int imageId) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mImageId = imageId;
        mAudioId = audioId;
    }

    /*
    create a word with miwok and default translations, and an imageId
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageId) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mImageId = imageId;
    }

    /*
    create a word with miwok and default translations, and a pronunciation media file
     */
    public Word(int audioId, String defaultTranslation, String miwokTranslation) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mAudioId = audioId;
    }

    /*
    constructor to create a word with miwok and default translations without an imageId
     */
    public Word(String defaultTranslation, String miwokTranslation) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
    }

    /*
    get miwok translation of a word

    @return current miwok translation of word as String
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    /*
    get default translation of a word

    @return current default translation of word as String
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }


    // get image ID, if there is no image ID, method will return -1
    public int getImageId() { return mImageId; }

    // get audio ID; if there is no audio ID, method will return -1
    public int getAudioId() { return mAudioId; }

    // return false if no image has been provided for the word
    public boolean hasImage() { return mImageId != NOT_PROVIDED; }

    // return false if no audio file is provided for the word
    public boolean hasAudio() { return mAudioId != NOT_PROVIDED; }

}
