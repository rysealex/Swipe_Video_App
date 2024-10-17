package com.example.swipevideoapp;

// imports
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// VideoAdapter class
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{
    // declare ArrayList of type VideoItem to videoItems
    private List<VideoItem> videoItems;

    /**
     * VideoAdapter constructor to initialize videoItems.
     *
     * @param videoItems The VideoItem that is being displayed in the program.
     */
    public VideoAdapter(List<VideoItem> videoItems) {
        this.videoItems = videoItems; // set this videoItems to new videoItems
    }

    /**
     * onCreateViewHolder method overridden from RecyclerView.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return VideoViewHolder, containing the item_container_video layout.
     */
    @NonNull
    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_video,parent,false)
        );
    }

    /**
     * onBindViewHolder method overridden from RecyclerView.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.VideoViewHolder holder, int position) {
        holder.setVideoData(videoItems.get(position)); // set the VideoViewHolder to correct position
    }

    /**
     * getItemCount method overridden from RecyclerView.
     *
     * @return int, the number of videos in videoItems.
     */
    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    // VideoViewHolder class
    static class VideoViewHolder extends RecyclerView.ViewHolder {
        // declare three TextView's for video title, description, and ID
        TextView textVideoTitle1, textVideoDescription, textVideoID;
        VideoView videoView;
        ProgressBar progressBar;

        /**
         * VideoViewHolder constructor initializes the key features of the display:
         * video view, title, description, ID, and progress bar.
         *
         * @param itemView The current View that is being displayed in the program.
         */
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView); // get current itemView

            // initialize each key feature from current itemView
            videoView = itemView.findViewById(R.id.videoView);
            textVideoTitle1 = itemView.findViewById(R.id.textVideoTitle);
            textVideoDescription = itemView.findViewById(R.id.textVideoDescription);
            textVideoID = itemView.findViewById(R.id.textVideoID);
            progressBar = itemView.findViewById(R.id.videoProgressBar);
        }

        /**
         * setVideoData method sets text display for video title, description, and ID.
         * Sets the video path URL. Creates new MediaPlayer from setOnPreparedListener.
         * Overrides the onPrepared method from MediaPlayer for playing videos.
         *
         * @param videoItem The current VideoItem being displayed to the user.
         */
        void setVideoData(VideoItem videoItem) {
            // set text for video title, description, and ID
            textVideoTitle1.setText(videoItem.videoTitle);
            textVideoDescription.setText(videoItem.videoDescription);
            textVideoID.setText(videoItem.videoID);

            videoView.setVideoPath(videoItem.videoURL); // sets current video URL

            // create a new MediaPlayer from setOnPreparedListener for playing videos
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                /**
                 * onPrepared method overridden, called when the user swipes for a new video.
                 *
                 * @param mp the MediaPlayer that is ready for playback
                 */
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // disable progress bar when video successfully loaded
                    progressBar.setVisibility(View.GONE);
                    mp.start(); // start playing video from MediaPlayer

                    // calculate the video and screen ratio
                    float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                    float screenRatio = videoView.getWidth() / (float) videoView.getHeight();

                    // calculate and set the scale for the videoView
                    float scale = videoRatio / screenRatio;
                    if (scale >= 1f) {
                        videoView.setScaleX(scale);
                    } else {
                        videoView.setScaleY(1f / scale);
                    }
                }

            });

            // set videoView to CompletionListener when video ends
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                /**
                 * onCompletion method overridden, restarts the video when ends.
                 *
                 * @param mp the MediaPlayer that reached the end of the file
                 */
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start(); // start the video again from MediaPlayer
                }
            });
        }
    }
}
