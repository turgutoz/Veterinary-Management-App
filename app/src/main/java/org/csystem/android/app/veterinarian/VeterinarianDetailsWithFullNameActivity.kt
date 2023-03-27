package org.csystem.android.app.veterinarian

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.api.VETERINARIAN
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.VeterinarianInfoWithFullName
import org.csystem.android.app.veterinarian.databinding.ActivityVeterinarianDetailsWithFullNameBinding
import org.csystem.android.app.veterinarian.viewmodel.VeterinarianDetailsWithFullNameActivityViewModel

class VeterinarianDetailsWithFullNameActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityVeterinarianDetailsWithFullNameBinding

    private fun initVeterinarianInfo()
    {
        mBinding.viewModel!!.veterinarianInfoWithFullName = if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(VETERINARIAN) as VeterinarianInfoWithFullName
        else
            intent.getSerializableExtra(VETERINARIAN, VeterinarianInfoWithFullName::class.java)!!
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_veterinarian_details_with_full_name)
        mBinding.viewModel = VeterinarianDetailsWithFullNameActivityViewModel(this)
    }

    private fun initialize()
    {
        initBinding()
        initVeterinarianInfo()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }
}