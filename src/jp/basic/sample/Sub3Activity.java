package jp.basic.sample;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.basicinc.gamefeat.android.sdk.controller.GameFeatAppController;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Sub3Activity extends Activity {

	GameFeatAppController gfAppController;
	getImageData getImageData;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gamefeatview);

		// GFコントローラ
		gfAppController = new GameFeatAppController();
		gfAppController.init(Sub3Activity.this);

		RelativeLayout incLayout = null;
		LinearLayout mainLayout = (LinearLayout) findViewById(R.id.ad_lists);

		// カスタム広告のデータを取得
		ArrayList<HashMap<String, String>> customArrayList = gfAppController.getCustomAds();

		for (final HashMap<String, String> map : customArrayList) {
			// LayoutInflaterの準備
			LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// incLayout = (LinearLayout) inflater.inflate(R.layout.custom_row, null);
			incLayout = (RelativeLayout) inflater.inflate(R.layout.custom_row, null);

			// アイコン画像の読み込み
			ImageView appIcon = (ImageView) incLayout.findViewById(R.id.app_icon);
			getImageData = new getImageData(map.get("app_icon_url"), appIcon);
			// getImageData = new getImageData("http://stgicon.gamefeat.net/poor.php", appIcon);
			getImageData.execute();

			// タイトルの設定
			TextView title = (TextView) incLayout.findViewById(R.id.title);
			title.setText(map.get("title"));

			// タイトルの設定
			TextView description = (TextView) incLayout.findViewById(R.id.description);
			description.setText(map.get("description"));

			// レビューボタンの設定
			Button btnReview = (Button) incLayout.findViewById(R.id.btn_review);
			if (map.get("has_entry") == "0") {
				btnReview.setVisibility(View.GONE);
			}
			btnReview.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// レビューへ
					gfAppController.onAdReviewClick(map);
				}
			});

			// DLボタンの設定
			Button btnStore = (Button) incLayout.findViewById(R.id.btn_store);
			btnStore.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// AppStoreへ
					gfAppController.onAdStoreClick(map);
				}
			});

			mainLayout.addView(incLayout);
		}
	}

}

/**
 * 画像の非同期読み込み
 * 
 * @author zaru
 * 
 */
class getImageData extends AsyncTask<String, Integer, Bitmap> {

	private ImageView imageView;
	private String imageUrl;

	public getImageData(String imageUrl, ImageView imageView) {
		super();
		this.imageView = imageView;
		this.imageUrl = imageUrl;
	}

	@Override
	protected Bitmap doInBackground(String... param) {
		Bitmap bitmap;

		try {
			URL url = new URL(this.imageUrl);
			InputStream inputStream = url.openStream();
			bitmap = BitmapFactory.decodeStream(inputStream);
			return bitmap;
		} catch (IOException ex) {
			Logger.getLogger(Sub3Activity.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		imageView.setImageBitmap(result);
	}
}
