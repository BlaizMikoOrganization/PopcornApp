package com.blaizmiko.popcornapp.data.models;

import com.blaizmiko.popcornapp.data.models.images.ImagesResponseModel;
import com.blaizmiko.popcornapp.data.models.videos.VideosResponseModel;

public interface Info {

    String getPosterPath();
    void setPosterPath(String posterPath);

    String getBackdropPath();
    void setBackdropPath(String backdropPath);

    String getOverview();
    void setOverview(String overview);

    ImagesResponseModel getImages();
    void setImages(ImagesResponseModel images);

    VideosResponseModel getVideos();
    void setVideos(VideosResponseModel videos);




}
