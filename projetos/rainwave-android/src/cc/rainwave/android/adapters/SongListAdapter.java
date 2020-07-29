package cc.rainwave.android.adapters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cc.rainwave.android.R;
import cc.rainwave.android.Rainwave;
import cc.rainwave.android.api.Session;
import cc.rainwave.android.api.types.GenericResult;
import cc.rainwave.android.api.types.RainwaveException;
import cc.rainwave.android.api.types.Song;
import cc.rainwave.android.views.CountdownView;

public class SongListAdapter extends BaseAdapter {
	private static final String TAG = "ElectionListAdapter";
	
	private boolean mVoted = false;
	
	private ArrayList<View> mViews;
	
	private ArrayList<Song> mSongs;
	
	private Context mContext;
	
	private Session mSession;
	
	private CountdownTask mCountdownTask;
	
	private VoteTask mVoteTask;
	
	private Handler mVoteHandler;
	
	/** Last elec_entry_id */
	private int mLastVote;
	
	/** Vote deadline */
	private long mDeadline = -1;
	
	/** Item XML ID */
	private int mItemLayout;
	
	public SongListAdapter(Context ctx, int resId, Session session, ArrayList<Song> songs) {
		mContext = ctx;
		mSession = session;
		mSongs = songs;
		mViews = new ArrayList<View>();
		for(int i = 0; i < mSongs.size(); i++) {
			mViews.add(null);
		}
		mItemLayout = resId;
	}
	
	public void startCountdown(int i) {
		if(hasVoted()) return;
		
		boolean rush = rushVotes();
		
		if(!rush && mCountdownTask == null) {
			mCountdownTask = new CountdownTask(i);
			mCountdownTask.execute();
			return;
		}
		
		if(rush || mCountdownTask.getSelection() == i) {
			if(mCountdownTask != null){
				int old = mCountdownTask.getSelection();
				setRating(old);
				mCountdownTask.cancel(true);
			}
			submitVote(i);
		}
		else {
			int old = mCountdownTask.getSelection();
			setRating(old);
			mCountdownTask.cancel(true);
			mCountdownTask = null;
			startCountdown(i);
		}
	}
	
	public boolean rushVotes() {
		long utc = System.currentTimeMillis() / 1000;
		return mDeadline > 0 && (mDeadline - utc) <= 15;
	}
	
	public boolean hasVoted() {
		return mVoted || mVoteTask != null;
	}
	
	public ArrayList<Song> getSongs() {
		return mSongs;
	}

	@Override
	public int getCount() {
		return (mSongs == null) ? 0 : mSongs.size();
	}

	@Override
	public Object getItem(int i) {
		return (mSongs == null) ? null : mSongs.get(i);
	}
	
	public Song getSong(int i) {
		return (mSongs == null) ? null : mSongs.get(i);
	}

	@Override
	public long getItemId(int i) {
		return (mSongs == null) ? -1 : mSongs.get(i).song_id;
	}
	
	public void markVoted(int elec_entry_id) {
		for(int i = 0; i < mSongs.size(); i++) {
			Song s = mSongs.get(i);
			if(s.elec_entry_id == elec_entry_id) {
				mLastVote = elec_entry_id;
				setVoteStatus(true);
				return;
			}
		}
	}
	
	public void setDeadline(long utc) {
		mDeadline = utc;
	}
	
	private void setVoteStatus(boolean state) {
		mVoted = state; 
	}
	
	@Override
	public View getView(int i, View convertView, ViewGroup parent) {
		if(mViews.get(i) == null || convertView == null) {
			Song s = mSongs.get(i);
			
			Resources r = mContext.getResources();
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(mItemLayout, null);
			mViews.set(i, convertView);
			
			setTextIfExists(convertView, R.id.song, s.song_title);
			setTextIfExists(convertView, R.id.album, s.album_name);
			setTextIfExists(convertView, R.id.artist, s.collapseArtists());
			
			if(s.isRequest()) {
				setImageIfExists(convertView, R.id.accent, R.drawable.accent_song_hilight);
				setVisibilityIfExists(convertView, R.id.requestor, View.VISIBLE);
				setTextIfExists(convertView, R.id.requestor,
						String.format(r.getString(R.string.label_requestor), s.song_requestor));
			}
			
			if(s.elec_entry_id == mLastVote) {
				setVoted(((CountdownView)convertView.findViewById(R.id.circle)));
			}
			else {
				reflectSong(((CountdownView)convertView.findViewById(R.id.circle)), s);
			}
		}
		
		return convertView;
	}
	
	/**
	 * Sets the visibility if we found the View.
	 */
	private void setVisibilityIfExists(View parent, int resId, int visibility) {
		if(parent == null) return;
		View v = parent.findViewById(resId);
		if(v == null) return;
		v.setVisibility(visibility);
	}
	
	/**
	 * Attempts to find the provided view ID and sets
	 * the image source if it exists and is an ImageView.
	 */
	private void setImageIfExists(View parent, int resId, int picId) {
		if(parent == null) return;
		View v = parent.findViewById(resId);
		if(v == null || !(v instanceof ImageView)) return;
		ImageView iv = (ImageView) v;
		iv.setImageResource(picId);
	}
	
	/**
	 * Attempts to find the provided view ID and sets
	 * the text if it exists and is a TextView.
	 * @param parent, context for findViewById
	 * @param resId, the ID to find
	 * @param s, the string to set
	 */
	private void setTextIfExists(View parent, int resId, String s){
		if(parent == null) return;
		if(s == null) s = "";
		View v = parent.findViewById(resId);
		if(v == null || !(v instanceof TextView)) return;
		TextView tv = (TextView) v;
		
		tv.setText(s);
	}
	
	private CountdownView getCountdownView(int i) {
		return (CountdownView) mViews.get(i).findViewById(R.id.circle);
	}
	
	private void reflectSong(CountdownView v, Song s) {
		v.setBoth(s.song_rating_user, s.song_rating_avg);
		v.setAlternateText(R.string.label_unrated);
	}
	
	private void setRating(int i) {
		reflectSong(getCountdownView(i), mSongs.get(i));
	}
	
	private void setVoting(int i) {
		CountdownView cnt = getCountdownView(i);
		cnt.setBoth(0, 0);
		cnt.setAlternateText(R.string.label_voting);
	}
	
	private void setVoted(int i) {
		setVoted( getCountdownView(i) );
	}
	
	private void setVoted(CountdownView view) {
		if(view == null) return;
		view.setBoth(0, 0);
		view.setAlternateText(R.string.label_voted);
	}
	
	public void setOnVoteHandler(Handler handler) {
		mVoteHandler = handler;
	}
	
	public void submitVote(int selection) {
		mVoteTask = new VoteTask(selection);
		mVoteTask.execute(mSongs.get(selection));
		setVoting(selection);
	}
	
	public ArrayList<Song> moveSong(int from, int to) {
		Song s = mSongs.remove(from);
		mSongs.add(to, s);
		notifyDataSetChanged();
		return mSongs;
	}
	
	public Song removeSong(int which) {
		Song s = mSongs.remove(which);
		mViews.remove(which);
		notifyDataSetChanged();
		return s;
	}
	
	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		for(int i = 0; i < mViews.size(); i++) {
			mViews.set(i, null);
		}
	}
	
	private class VoteTask extends AsyncTask<Song, Integer, Boolean> {
		
		private CountdownView mCountdown;
		
		private Song mSong;
		
		private int mSelection;
		
		public VoteTask(int selection) {
			mCountdown = getCountdownView(selection);
			mSelection = selection;
		}
		
		protected Boolean doInBackground(Song...params) {
			mSong = params[0];
			
			try {
				GenericResult result = mSession.vote(mSong.elec_entry_id);
				return true;
			} catch (IOException e) {
				Rainwave.showError(SongListAdapter.this.mContext, e);
				Log.e(TAG, "IO Error: " + e);
			} catch (RainwaveException e) {
				Rainwave.showError(SongListAdapter.this.mContext, e);
				Log.e(TAG, "API Error: " + e);
			}
			
			return false;
		}
		
		protected void onPostExecute(Boolean result) {
			if(result) {
				setVoted(mSelection);
			}
			else {
				reflectSong(mCountdown, mSong);
				mVoteTask = null;
			}
			
			if(mVoteHandler != null) {
				Message msg = mVoteHandler.obtainMessage(CODE_VOTED);
				msg.arg1 = (result) ? CODE_SUCCESS : CODE_GENERIC_FAIL;
				msg.sendToTarget();
			}
		}
	}
	
	private class CountdownTask extends AsyncTask<Integer, Integer, Boolean> {
		private int mSelection;
		
		private CountdownView mCountdownView;
		
		private Song mSong;
		
		public CountdownTask(int selection) {
			mSelection = selection;
			View v = SongListAdapter.this.mViews.get(mSelection);
			mCountdownView = (CountdownView) v.findViewById(R.id.circle);
			mSong = mSongs.get(selection);
		}
		
		@Override
		protected Boolean doInBackground(Integer ...params) {
			mCountdownView.setMax(5.0f);
			mCountdownView.setBoth(5.0f, 0.0f);
			mCountdownView.setShowValue(true);
			mCountdownView.setAlternateText(R.string.label_voting);
			while(mCountdownView.getPrimary() > 0) {
				mCountdownView.decrementPrimary(0.1f);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					return false;
				}
			}
			
			return true;
		}
		
		protected void onPostExecute(Boolean result) {
			if(result == true) {
				submitVote(mSelection);
			}
			else {
				reflectSong(mCountdownView, mSong);
			}
			mCountdownTask = null;
		}
		
		public int getSelection() {
			return mSelection;
		}
	}
	
	public static final int
		CODE_GENERIC_FAIL = 1,
		CODE_SUCCESS = 0;
	
	public static final int
		CODE_VOTED = 0xB073D;
}
