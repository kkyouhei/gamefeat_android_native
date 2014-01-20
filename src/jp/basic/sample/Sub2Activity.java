package jp.basic.sample;

import jp.basicinc.gamefeat.android.sdk.controller.GameFeatAppController;
import jp.basicinc.gamefeat.android.sdk.view.GameFeatIconView;
import android.app.Activity;
import android.os.Bundle;

public class Sub2Activity extends Activity {

	GameFeatAppController gfAppController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub2);
		// GFコントローラ
		gfAppController = new GameFeatAppController();
		gfAppController.init(Sub2Activity.this);
		gfAppController.setRefreshInterval(10);

		((GameFeatIconView) findViewById(R.id.gf_icon1)).addLoader(gfAppController);
		((GameFeatIconView) findViewById(R.id.gf_icon2)).addLoader(gfAppController);
		((GameFeatIconView) findViewById(R.id.gf_icon3)).addLoader(gfAppController);
		((GameFeatIconView) findViewById(R.id.gf_icon4)).addLoader(gfAppController);
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

}
