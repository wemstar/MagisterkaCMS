package pl.edu.agh.fis.activity.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.activity.application.template.ApplicationTemplateDetailsActivity_;
import pl.edu.agh.fis.activity.application.template.ApplicationTemplateListActivity_;
import pl.edu.agh.fis.activity.document.details.DetailsDocumentActivity_;
import pl.edu.agh.fis.utils.UserContext;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @ViewById
    FloatingActionButton fab;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    @ViewById(R.id.nav_view)
    NavigationView navigationView;

    @ViewById
    Toolbar toolbar;

    @Bean
    UserContext userContext;

    @AfterViews
    void initViews() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        userContext.reloadUser();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Click(R.id.fab)
    void floatingButtonClicked(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @OptionsItem(R.id.action_settings)
     void optionsItemClc(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_create_document) {
            Intent intent = new Intent(this, DetailsDocumentActivity_.class);
            startActivity(intent);
        } else if (id == R.id.nav_create_template) {
            Intent intent = new Intent(this, ApplicationTemplateDetailsActivity_.class);
            startActivity(intent);
        } else if (id == R.id.nav_create_application) {
            Intent intent = new Intent(this, ApplicationTemplateListActivity_.class);
            startActivity(intent);
        } /*else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
