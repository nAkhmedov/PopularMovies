package uz.nakhmedov.movie.ui.main

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import uz.nakhmedov.movie.R
import uz.nakhmedov.movie.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
