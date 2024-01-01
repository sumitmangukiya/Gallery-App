package com.example.galleryapp.video.model;

import java.io.Serializable;
import java.util.ArrayList;

public class VideoAlbum implements Serializable {

    String str_folder;
    ArrayList<String> al_imagepath;

    public String getStr_vfolder() {
        return str_folder;
    }

    public void setStr_vfolder(String str_folder) {
        this.str_folder = str_folder;
    }

    public ArrayList<String> getAl_videopath() {
        return al_imagepath;
    }

    public void setAl_videopath(ArrayList<String> al_imagepath) {
        this.al_imagepath = al_imagepath;
    }
}
