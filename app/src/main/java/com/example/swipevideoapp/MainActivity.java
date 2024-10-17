// Alex Ryse
package com.example.swipevideoapp;

// imports
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

// MainActivity class
public class MainActivity extends AppCompatActivity {

    /**
     * onCreate method overridden, initialized essential components of application.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // initialize new final ViewPager2 from videosViewPager ID to display the videos
        final ViewPager2 videoViewPager = findViewById(R.id.videosViewPager);

        // ArrayList videoItemsList holds the videos (VideoItems)
        List<VideoItem> videoItemsList = new ArrayList<>();

        // create VideoItem and initialized information for the first video
        VideoItem video1 = new VideoItem();
        video1.videoURL = "https://firebasestorage.googleapis.com/v0/b/fir-1-46223.appspot.com/o/SwipeAppVid1.mp4?alt=media&token=8b333584-89e3-4ef1-a3e1-cfce4e876b93";
        video1.videoTitle = "Hōkoku-ji Temple";
        video1.videoDescription = "Located in Kamakura, Japan";
        video1.videoID = "ID: 07120984";
        videoItemsList.add(video1); // add to videoItemsList

        // create VideoItem and initialized information for the second video
        VideoItem video2 = new VideoItem();
        video2.videoURL = "https://firebasestorage.googleapis.com/v0/b/fir-1-46223.appspot.com/o/SwipeAppVid2.mp4?alt=media&token=44fa865a-a92f-40f3-9bba-252a031c2cb4";
        video2.videoTitle = "Shibuya Sky";
        video2.videoDescription = "Observation deck in Shibuya, Japan";
        video2.videoID = "ID: 43985120";
        videoItemsList.add(video2); // add to videoItemsList

        // create VideoItem and initialized information for the third video
        VideoItem video3 = new VideoItem();
        video3.videoURL = "https://firebasestorage.googleapis.com/v0/b/fir-1-46223.appspot.com/o/SwipeAppVid3.mp4?alt=media&token=a02e28ad-e76e-4d0e-ae34-062686acca43";
        video3.videoTitle = "Sumida River Firework Festival";
        video3.videoDescription = "Located in Sumida City, Tokyo Japan";
        video3.videoID = "ID: 34207548";
        videoItemsList.add(video3); // add to videoItemsList

        // set new adapter to videoViewPager to include the three videos in videoItemsList
        videoViewPager.setAdapter(new VideoAdapter(videoItemsList));
    }
}