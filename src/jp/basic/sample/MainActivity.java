package jp.basic.sample;

import jp.basicinc.gamefeat.android.sdk.controller.GameFeatAppController;
import jp.basicinc.gamefeat.android.sdk.view.GameFeatIconView;
import jp.basicinc.gamefeat.android.sdk.view.listener.GameFeatPopupListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	GameFeatAppController gfAppController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// GFコントローラ
		gfAppController = new GameFeatAppController();

		// GF ウォール起動
		((Button) findViewById(R.id.gf_wall)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				gfAppController.show(MainActivity.this);
			}
		});

		// GF 全画面起動
		((Button) findViewById(R.id.load_popup)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// リスナー設定しない場合
				// gfAppController.showPopupAdDialog(MainActivity.this);

				// 全画面広告n回に1回表示する設定
				gfAppController.setPopupProbability(1);
				gfAppController.showPopupAdDialog(MainActivity.this, new GameFeatPopupListener() {

					@Override
					public void onViewSuccess() {
					}

					@Override
					public void onViewError() {
					}

					@Override
					public void onClickFinished() {
						finish();
					}

					@Override
					public void onClickClosed() {
					}
				});
			}
		});

		// 次のViewへ
		((Button) findViewById(R.id.btn_next)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Sub1Activity.class);
				startActivity(intent);
			}
		});

		// カスタム版広告
		((Button) findViewById(R.id.btn_custom)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Sub3Activity.class);
				startActivity(intent);
			}
		});

		// アイコン非表示
		((Button) findViewById(R.id.btn_invisible)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				findViewById(R.id.gf_icon1).setVisibility(View.GONE);
			}
		});

		// アイコン表示
		((Button) findViewById(R.id.btn_visible)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				findViewById(R.id.gf_icon1).setVisibility(View.VISIBLE);
			}
		});

		gfAppController.setRefreshInterval(10);
		((GameFeatIconView) findViewById(R.id.gf_icon1)).addLoader(gfAppController);
		((GameFeatIconView) findViewById(R.id.gf_icon2)).addLoader(gfAppController);
		((GameFeatIconView) findViewById(R.id.gf_icon3)).addLoader(gfAppController);
		((GameFeatIconView) findViewById(R.id.gf_icon4)).addLoader(gfAppController);
	}

	@Override
	public void onStart() {
		super.onStart();

		// 広告設定初期化
		gfAppController.activateGF(MainActivity.this, true, true, true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onResume() {
		super.onResume();
		gfAppController.startIconAd();
	}

	@Override
	public void onStop() {
		super.onStop();
		gfAppController.stopIconAd();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			gfAppController.showExitPopupAdDialog(MainActivity.this);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
