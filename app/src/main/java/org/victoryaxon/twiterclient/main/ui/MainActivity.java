package org.victoryaxon.twiterclient.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.twitter.sdk.android.Twitter;
import org.victoryaxon.twiterclient.LoginActivity;
import org.victoryaxon.twiterclient.R;
import org.victoryaxon.twiterclient.hashtags.ui.HashtagsFragment;
import org.victoryaxon.twiterclient.images.ui.ImagesFragment;
import org.victoryaxon.twiterclient.main.ui.adapters.MainSectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.container)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setupAdaper();

        setTitle(String.format(getString(R.string.hello_user),
                Twitter.getSessionManager().getActiveSession().getUserName()));
    }

    private void setupAdaper() {
        Fragment[] fragments = new Fragment[]{new ImagesFragment(), new HashtagsFragment()};
        String[] titles = new String[]{getString(R.string.main_header_images), getString(R.string.main_header_hashtags)};
        MainSectionsPagerAdapter adapter = new MainSectionsPagerAdapter(getSupportFragmentManager() ,titles, fragments);
        viewPager.setAdapter(adapter);

        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout)
            logout();
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        Twitter.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
