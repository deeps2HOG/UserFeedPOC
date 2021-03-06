# How to run app
Open Manifest.xml and find meta-data block
```xml
<meta-data
    android:name="JW_LICENSE_KEY"
    android:value="YOUR_KEY" />
```
1. Replace YOUR_KEY with your sdk key.
2. Buid.
3. Run.

# Using JWPlayerView with RecyclerView

To implement RecyclerView with JWPlayer SDK, first of all you should create `RecyclerView.Adapter<T>` that overrides 
```java
onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads)
```
The main reason about that is to not show blinking animation of view when it rebinds. 

Then you should create your own `RecyclerView.ViewHolder`. The holder should have 4 methods:
```java
// this method should be called when setting the player up or releasing it
public void bindViewHolder(final Object videoItem);
// lifecycle methods for stopping and resuming 
// playback when app goes backrgound / foreground 
public void onResume(); 
public void onPause();
public void onDestroy();
```
The main thing when implementing `JWPlayerView` with `RecyclerView` is not to forget about releasing player before player's view is still on the screen. So to make this happen you have to call `stop()` before view is recycled. For this case you should calculate items position and stop playback if needed. 

In most cases only one `JWPlayerView` that is currentry focused should play content. To achive this behavior you can use `LinearLayoutManager` with its `findFirstCompletelyVisibleItemPosition()`.
```java
    private void calculateTopItemAndUpdateRecycler(RecyclerView recyclerView) {
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        updateList(manager.findFirstCompletelyVisibleItemPosition());
    }

    private void updateList(final int selectedPosition) {
        if (mSelectedPosition != selectedPosition) {
            mFeedAdapter.setActivePosition(selectedPosition);
            //add payloads to not call animation
            mFeedAdapter.notifyItemChanged(selectedPosition, FeedAdapter.ACTION_PLAY);
            mFeedAdapter.notifyItemChanged(mSelectedPosition, FeedAdapter.ACTION_STOP);
            mSelectedPosition = selectedPosition;
        }
    }
```
Implementation of `RecyclerView.OnScrollListener` that stops playback in case of any scroll event and after this event is finished and `RecyclerView` is in `IDLE` state, setup and start playback for the first visible item.
```java
private class ScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                calculateTopItemAndUpdateRecycler(recyclerView);
            }

            if (newState == RecyclerView.SCROLL_STATE_SETTLING
                    || newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                updateList(FeedAdapter.POSITION_NONE);
            }
        }
    }
```

