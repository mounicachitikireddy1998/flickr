package com.venkat.flickr.tasks;


import com.venkat.flickr.entity.SingleImage;

import java.util.List;

public interface AsyncResponse {
    void processFinish(List<SingleImage> imageUrls);
}
